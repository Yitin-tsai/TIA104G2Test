package chilltrip.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import chilltrip.member.model.MemberService;
import chilltrip.member.model.MemberVO;

@WebServlet("/member/member.login")
public class MemberLoginServlet extends HttpServlet {

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
		
		if("login".equals(action)) {  // 登入請求處理
			List<String> errorMsgs = new LinkedList<String>();  // 儲存錯誤訊息的列表
			req.setAttribute("errorMsgs", errorMsgs);
			
			// 接收請求參數
			// 取得登入表單中的帳號和密碼
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            // 驗證帳號和密碼
            if (email == null || email.trim().length() == 0) {
                errorMsgs.add("請輸入信箱");
            }
            if (password == null || password.trim().length() == 0) {
                errorMsgs.add("請輸入密碼");
            }
            
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member_login.html");  // 錯誤時導到原登入頁面顯示錯誤頁面
                failureView.forward(req, res);
                return;
            }
			
			// 開始查詢資料
			MemberVO memberVO = memberSvc.login(email, password);
			
			if (memberVO == null) {
                errorMsgs.add("帳號或密碼錯誤");
                RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member_login.html");
                failureView.forward(req, res);
                return;
            }
			
			// 登入成功，將會員資料放入 session 中
            req.getSession().setAttribute("memberVO", memberVO);
            
//            // 將會員照片轉換為 Base64 字串
//            String photoBase64 = memberSvc.encodePhotoToBase64(memberVO);
//            req.setAttribute("photoBase64", photoBase64);  // 將 Base64 字串傳遞給前端頁面
            
            // 登入成功，導向到會員個人頁面
            String url = "/frontend/personal_homepage.html";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
		}
	}
}
