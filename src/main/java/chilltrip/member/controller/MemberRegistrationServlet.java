package chilltrip.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import chilltrip.member.model.MemberService;
import chilltrip.member.model.MemberVO;

import redis.clients.jedis.Jedis;

@WebServlet("/member/member.register")
public class MemberRegistrationServlet extends HttpServlet {

	private MemberService memberSvc;

	// 使用 Redis
	private Jedis jedis = new Jedis("localhost", 6379);

	public void init() {
		memberSvc = new MemberService();
		try {
			jedis.select(5); // 指定db5資料庫
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("無法連接到 Redis，請檢查伺服器設定。");
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");

		if ("randomcode".equals(action)) { // 傳送信箱驗證碼的請求
			List<String> errorMsgs = new LinkedList<String>(); // 儲存錯誤訊息的列表
			req.setAttribute("errorMsgs", errorMsgs);
			
			// 接收請求參數，輸入格式的錯誤處理
			String email = req.getParameter("email");
			String emailReg = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
			if (email == null || email.trim().length() == 0) {
				errorMsgs.add("信箱請勿空白");
			} else if (!email.trim().matches(emailReg)) {
				errorMsgs.add("信箱格式不符合，請輸入正確信箱格式");
			}

			if (memberSvc.checkEmailExists(email)) {
				errorMsgs.add("此 email 已經註冊過，請使用其他 email");
			}
			
			if (!errorMsgs.isEmpty()) {
			    req.setAttribute("errorMsgs", errorMsgs);
			    RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member_registration.html");
			    failureView.forward(req, res);
			    return;
			}
			
			sendCode(email);
		}
		
		if("checkrandomcode".equals(action)) {  // 確認信箱驗證碼的請求
			List<String> errorMsgs = new LinkedList<String>(); // 儲存錯誤訊息的列表
			req.setAttribute("errorMsgs", errorMsgs);

			// 接收請求參數，驗證郵件驗證碼
			String emailCode = req.getParameter("emailCode");
			String email = req.getParameter("email");
			email = email.trim(); // 去除前後空格
			
			if (emailCode == null || emailCode.trim().length() == 0) {
				errorMsgs.add("信箱驗證碼請勿空白");
			}
			
			// 從 Redis db5 中取出驗證碼
			String storedCode = jedis.get("verification_code:" + email);
			System.out.println("從 Redis 取得的驗證碼：" + storedCode);  // 確保儲存的驗證碼無誤
			System.out.println("用戶輸入的驗證碼：" + emailCode);  // 確保用戶輸入的驗證碼無誤

			if (storedCode == null || !storedCode.trim().equalsIgnoreCase(emailCode.trim())) {
				System.out.println("無效的驗證碼，請重新驗證");
			    errorMsgs.add("無效的驗證碼，請重新驗證");
			}
			
			if (!errorMsgs.isEmpty()) {
				System.out.println("驗證碼確認失敗！");
			    req.setAttribute("errorMsgs", errorMsgs);
			    RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member_registration.html");
			    failureView.forward(req, res);
			    return;
			} else {
			    // 驗證成功，將結果傳遞到前端
			    req.setAttribute("successMessage", "驗證碼確認成功！");
			   
			    System.out.println("驗證碼確認成功！");
			    RequestDispatcher successView = req.getRequestDispatcher("/frontend/member_registration.html");
			    successView.forward(req, res);
			    return;
			}
		}

		if ("register".equals(action)) { // 註冊會員的請求

			List<String> errorMsgs = new LinkedList<String>(); // 儲存錯誤訊息的列表
			req.setAttribute("errorMsgs", errorMsgs);

			// 接收請求參數，輸入格式的錯誤處理
			String account = req.getParameter("email");
			String email = req.getParameter("email");
			email = email.trim(); // 去除前後空格
			String emailReg = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
			if (email == null || email.trim().length() == 0) {
				errorMsgs.add("信箱請勿空白");
			} else if (!email.trim().matches(emailReg)) {
				errorMsgs.add("信箱格式不符合，請輸入正確信箱格式");
			}

			if (memberSvc.checkEmailExists(email)) {
				errorMsgs.add("此 email 已經註冊過，請使用其他 email");
			}

			// 驗證郵件驗證碼
			String emailCode = req.getParameter("emailCode");

			// 從 Redis db5 中取出驗證碼
			String storedCode = jedis.get("verification_code:" + email);

			if (storedCode == null || !storedCode.equals(emailCode)) {
				errorMsgs.add("無效的驗證碼，請重新驗證");
			}

			String password = req.getParameter("password");
			String passwordReg = "^[(a-zA-Z0-9_)]{5,15}$";
			if (password == null || password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			} else if (!password.trim().matches(passwordReg)) {
				errorMsgs.add("密碼只能是英文字母數字和_，長度需在5~20之間");
			}

			String name = req.getParameter("name");
			String nameReg = "^[\\u4E00-\\u9FFFa-zA-Z\\s]{2,20}$";
			if (name == null || name.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			} else if (!name.trim().matches(nameReg)) {
				errorMsgs.add("姓名可以包括中文、英文、空格，但不可是特殊符號，長度需在2~20之間，且不允許開頭或結尾有空格");
			}

			String phone = req.getParameter("phone");
			String phoneReg = "^(09[0-9]{8}|0[2-8][0-9]{7,8}|0[2-8]-[0-9]{6,8})$";
			if (phone == null || phone.trim().length() == 0) {
				errorMsgs.add("聯絡電話請勿空白");
			} else if (!phone.trim().matches(phoneReg)) {
				errorMsgs.add("請輸入正確聯絡電話格式，手機或市話皆可，ex: 0912345678 或 02-12345678");
			}

			Timestamp createTime = new Timestamp(System.currentTimeMillis()); // 生成當前時間

			String nickName = req.getParameter("nickname");
			String nickNameReg = "^[\\u4E00-\\u9FFFa-zA-Z\\s]{2,20}$";
			if (nickName == null || nickName.trim().length() == 0) {
				errorMsgs.add("會員名稱請勿空白");
			} else if (!nickName.trim().matches(nickNameReg)) {
				errorMsgs.add("會員名稱可以包括中文、英文、空格，但不可是特殊符號，長度需在2~20之間，且不允許開頭或結尾有空格");
			}

			// 設置註冊後使用者的預設狀態為 0（一般狀態）
			Integer status = 0; // 0 表示一般狀態

			// 接收前端傳來的性別選擇
			String genderStr = req.getParameter("gender");

			// 初始化 gender 變數
			Integer gender = null;

			// 根據前端傳來的性別值（字串），轉換為數字
			if (genderStr != null) {
				if ("男".equals(genderStr)) {
					gender = 0; // 男性對應 0
				} else if ("女".equals(genderStr)) {
					gender = 1; // 女性對應 1
				}
			}

			Date birthday = Date.valueOf(req.getParameter("birthday"));

			String companyId = String.valueOf(req.getParameter("companyid"));
			String companyIdReg = "^\\d{8}$";
			if (!companyId.trim().matches(companyIdReg)) {
				errorMsgs.add("公司統編為 8 位數字，且不能包含字母或特殊符號");
			}

			String ereceiptCarrier = req.getParameter("ereceiptcarrier");
			String ereceiptCarrierReg = "^\\/[0-9A-Z.\\-\\+]{7}$";
			if (!ereceiptCarrier.trim().matches(ereceiptCarrierReg)) {
				errorMsgs.add("手機載具格式錯誤，總長度是 8 個字符，第一碼必須是\" / \"，後續接 7 個字符可以是數字(0-9)、大寫英文字母(A-Z)、以及特殊符號(.、-、+)");
			}

			String creditCard = req.getParameter("creditcard");
			String creditCardReg = "^(\\d{4}[-\\s]?){3}\\d{4}$|^\\d{13,19}$";
			if (!creditCard.trim().matches(creditCardReg)) {
				errorMsgs.add("信用卡號輸入格式不正確，請輸入 13 至 19 位的數字");
			}

			byte[] photo = null; // 初始化圖片資料為 null
			Part part = req.getPart("photo");
			if (part != null && part.getSize() > 5000000) { // 假設限制為 5MB
				errorMsgs.add("圖片檔案過大，請選擇小於 5MB 的檔案");
			}
			InputStream in = part.getInputStream();
			photo = in.readAllBytes(); // Java 9 的新方法
			in.close();

			// 如果有錯誤，將錯誤訊息回傳給前端
			// if (!errorMsgs.isEmpty()) {
			// JSONObject jsonRes = new JSONObject();
			// jsonRes.put("success", false);
			// jsonRes.put("errorMsgs", errorMsgs); // 將錯誤訊息放入 JSON 物件中
			// res.getWriter().write(jsonRes.toString()); // 回傳 JSON 格式的錯誤訊息
			// return;
			// }

			// 如果驗證成功，建立 MemberVO 物件並設置相應屬性
			MemberVO memberVO = new MemberVO();
			memberVO.setEmail(email);
			memberVO.setAccount(account);
			memberVO.setPassword(password);
			memberVO.setName(name);
			memberVO.setPhone(phone);
			memberVO.setCreateTime(new Timestamp(System.currentTimeMillis())); // 創建時間為當前時間
			memberVO.setNickName(nickName);
			memberVO.setStatus(status);
			memberVO.setGender(gender);
			memberVO.setBirthday(birthday);
			memberVO.setCompanyId(companyId);
			memberVO.setEreceiptCarrier(ereceiptCarrier);
			memberVO.setCreditCard(creditCard);
			memberVO.setPhoto(photo);

			// 假設註冊成功，回傳成功訊息
			// JSONObject jsonRes = new JSONObject();
			// jsonRes.put("success", true);
			// jsonRes.put("memberId", memberVO.getMemberId()); // 回傳註冊的會員 ID
			//
			// res.setContentType("application/json");
			// res.setCharacterEncoding("UTF-8");
			// res.getWriter().write(jsonRes.toString()); // 回傳成功的 JSON 格式訊息

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member_registration.html"); // 錯誤時導到原註冊頁面顯示錯誤頁面
				failureView.forward(req, res);
				return;
			}

			// 開始新增資料
			memberVO = memberSvc.addMember(memberVO);

			// 新增完成,準備轉交(Send the Success view)
			String url = "/frontend/member_login.html";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 註冊成功時導到登入頁面
			successView.forward(req, res);

		}
	}

	// 發送驗證碼並保存至 Redis
	private void sendCode(String email) {
		try {
			String code = memberSvc.generateCode();
			jedis.setex("verification_code:" + email, 300, code); // 儲存驗證碼到 Redis，5 分鐘過期
			memberSvc.sendEmail(email, code); // 發送驗證碼
			System.out.println("驗證碼已存入 Redis 並發送郵件：" + code);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("驗證碼存入 Redis 失敗！");
		}
	}

	public void destroy() {
		if (jedis != null) {
			try {
				jedis.close();
				System.out.println("Jedis 連線已關閉。");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("關閉 Jedis 時發生錯誤！");
			}
		}

	}
}
