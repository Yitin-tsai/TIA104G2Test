package chilltrip.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import chilltrip.member.model.MemberService;
import chilltrip.member.model.MemberVO;

@WebServlet("/Member")
public class MemberServlet extends HttpServlet {

	private MemberService memberSvc;

	public void init() {
		memberSvc = new MemberService();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");

		switch (action) {
		case "addMember":
			addMember(req, res);
			break;
		// 可以根據需要處理其他 action，如更新會員、刪除會員等
		}
	}

	private void addMember(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		List<String> errorMsgs = new LinkedList<String>(); // 儲存錯誤訊息的列表
		req.setAttribute("errorMsgs", errorMsgs);

		String email = req.getParameter("email");
		String emailReg = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
		if (email == null || email.trim().length() == 0) {
			errorMsgs.add("信箱請勿空白");
		} else if (!email.trim().matches(emailReg)) {
			errorMsgs.add("信箱格式不符合，請輸入正確信箱格式");
		}

		String account = req.getParameter("account");
		String accountReg = "^[(a-zA-Z0-9_)]{5,20}$";
		if (account == null || account.trim().length() == 0) {
			errorMsgs.add("帳號請勿空白");
		} else if (!account.trim().matches(accountReg)) {
			errorMsgs.add("帳號只能是英文字母數字和_，長度需在5~20之間");
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

		Integer status = Integer.valueOf(req.getParameter("status"));

		Timestamp createTime = Timestamp.valueOf(req.getParameter("createTime"));

		String nickName = req.getParameter("nickName");
		String nickNameReg = "^[\\u4E00-\\u9FFFa-zA-Z\\s]{2,20}$";
		if (nickName == null || nickName.trim().length() == 0) {
			errorMsgs.add("會員名稱請勿空白");
		} else if (!nickName.trim().matches(nickNameReg)) {
			errorMsgs.add("會員名稱可以包括中文、英文、空格，但不可是特殊符號，長度需在2~20之間，且不允許開頭或結尾有空格");
		}

		Integer gender = Integer.valueOf(req.getParameter("gender"));

		Date birthday = Date.valueOf(req.getParameter("birthday"));

		String companyId = String.valueOf(req.getParameter("companyId"));

		String ereceiptCarrier = req.getParameter("ereceiptCarrier");
		String ereceiptCarrierReg = "^\\d{8}$";
		if (!ereceiptCarrier.trim().matches(ereceiptCarrierReg)) {
			errorMsgs.add("公司統編為 8 位數字，且不能包含字母或特殊符號");
		}

		String creditCard = req.getParameter("creditCard");
		String creditCardReg = "^(\\d{4}[-\\s]?){3}\\d{4}$|^\\d{13,19}$";
		if (!creditCard.trim().matches(creditCardReg)) {
			errorMsgs.add("信用卡號輸入格式不正確，請輸入 13 至 19 位的數字");
		}

		Integer trackingNumber = Integer.valueOf(req.getParameter("trackingNumber"));

		Integer fansNumber = Integer.valueOf(req.getParameter("fansNumber"));

		byte[] photo = null; // 初始化圖片資料為 null
		Part part = req.getPart("photo");
		InputStream in = part.getInputStream();
		photo = new byte[in.available()]; // byte[] buf = in.readAllBytes(); // Java 9 的新方法
		in.read(photo);
		in.close();

		// 如果有錯誤，將錯誤訊息回傳給前端
		if (!errorMsgs.isEmpty()) {
			JSONObject jsonRes = new JSONObject();
			jsonRes.put("success", false);
			jsonRes.put("errorMsgs", errorMsgs); // 將錯誤訊息放入 JSON 物件中
			res.getWriter().write(jsonRes.toString()); // 回傳 JSON 格式的錯誤訊息
			return;
		}

		// 如果驗證成功，建立 MemberVO 物件並設置相應屬性
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail(email);
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setName(name);
		memberVO.setPhone(phone);
		memberVO.setStatus(status);
		memberVO.setCreateTime(new Timestamp(System.currentTimeMillis())); // 創建時間為當前時間
		memberVO.setNickName(nickName);
		memberVO.setGender(gender);
		memberVO.setBirthday(birthday);
		memberVO.setCompanyId(companyId);
		memberVO.setEreceiptCarrier(ereceiptCarrier);
		memberVO.setCreditCard(creditCard);
		memberVO.setTrackingNumber(trackingNumber);
		memberVO.setFansNumber(fansNumber);
		memberVO.setPhoto(photo);

		// 儲存會員資料到資料庫
		memberSvc.addMember(memberVO);

		// 假設註冊成功，回傳成功訊息
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("success", true);
		jsonRes.put("memberId", memberVO.getMemberId()); // 回傳註冊的會員 ID
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonRes.toString()); // 回傳成功的 JSON 格式訊息

	}
}
