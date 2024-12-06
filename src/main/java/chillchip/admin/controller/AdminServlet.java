package chillchip.admin.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chillchip.admin.entity.AdminVO;
import chillchip.admin.service.AdminServiceImpl;

@WebServlet("/admin/admin.do")
public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		switch (action)
		{
			case "getOne_For_Display":
			{
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("adminid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請勿空白");
				}
	
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/admin/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
	
				Integer adminid = null;
				try {
					adminid = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("請輸入數字");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/admin/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// 查詢資料
				AdminServiceImpl adminSvc = new AdminServiceImpl();
				AdminVO adminVO = adminSvc.getOneAdmin(adminid);
				if (adminVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/admin/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// 轉送資料回網頁
				req.setAttribute("adminVO", adminVO);
				String url = "/admin/listOneAdmin.jsp";
				RequestDispatcher succesView = req.getRequestDispatcher(url);
				succesView.forward(req, res);
				break;
				
				}
			
			case "getOne_For_Update":
			{
				
				Integer adminid = Integer.valueOf(req.getParameter("adminid"));
				
				AdminServiceImpl adminSvc = new AdminServiceImpl();
				AdminVO adminVO = adminSvc.getOneAdmin(adminid);
				
				req.setAttribute("adminVO", adminVO);
				String url = "/admin/update_admin_input.jsp";
				RequestDispatcher succesView = req.getRequestDispatcher(url);
				succesView.forward(req, res);
				break;
				
			}
			case "update":
			{
				List<String> errorMsgs = new LinkedList<String>();
				
				req.setAttribute("errorMsgs", errorMsgs);
				
				
				//輸入格式的錯誤處理
				Integer adminid = Integer.valueOf(req.getParameter("adminid").trim());
				
				String adminaccount = req.getParameter("adminaccount");
				String adminacReg =  "^[(a-zA-Z0-9_)]{5,20}$";
				if(adminaccount==null||adminaccount.trim().length() ==0) {
					errorMsgs.add("管理員帳號請勿空白");
				}else if (!adminaccount.trim().matches(adminacReg)) {
					errorMsgs.add("管理員帳號只能是英文字母數字和_，長度需在5~20之間");
				}
				
				String adminpassword = req.getParameter("adminpassword");
				String adminpsReg =  "^[(a-zA-Z0-9_)]{5,15}$";
				if(adminpassword==null||adminpassword.trim().length() ==0) {
					errorMsgs.add("管理員密碼請勿空白");
				}else if (!adminpassword.trim().matches(adminpsReg)) {
					errorMsgs.add("管理員密碼只能是英文字母數字和_，長度需在5~20之間");
				}

				String email = req.getParameter("email");
				String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{5,50})?$";
				if(email==null||email.trim().length() ==0) {
					errorMsgs.add("管理員密碼請勿空白");
				}else if (!email.trim().matches(emailReg)) {
					errorMsgs.add("信箱格式不符合");
				}
				
				
				String adminname = req.getParameter("adminname");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if(adminname==null||adminname.trim().length() ==0) {
					errorMsgs.add("管理員名稱請勿空白");
				}else if (!adminname.trim().matches(nameReg)) {
					errorMsgs.add("管理員名稱只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}
				
				String phone = req.getParameter("phone");
				String phoneReg = "^[((0-9_)]{9,13}$";
				if(phone==null||phone.trim().length() ==0) {
					errorMsgs.add("電話號碼請勿空白");
				}else if (!phone.trim().matches(phoneReg)) {
					errorMsgs.add("電話號碼只能是數字, 且長度必需是9到13之間");
				}
				
				Integer status = null;
				status = Integer.valueOf(req.getParameter("adminid").trim());
				
				if(status<0 || status >2) {
					errorMsgs.add("請填入0,1,2 三種狀態");
				}
				try {
					status = Integer.valueOf(req.getParameter("status").trim());
				}catch(NumberFormatException e) {
					status = 0;
					errorMsgs.add("狀態請填入0,1,2 三種狀態");
				}
				
				String adminnickname = req.getParameter("adminnickname");
				String adminnicknameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if(adminnickname==null||adminnickname.trim().length() ==0) {
					errorMsgs.add("管理員暱稱請勿空白");
				}else if (!adminnickname.trim().matches(adminnicknameReg)) {
					errorMsgs.add("管理員暱稱只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}
				
				AdminVO adminVO = new AdminVO();
				adminVO.setAdminaccount(adminaccount);
				adminVO.setAdminpassword(adminpassword);
				adminVO.setEmail(email);
				adminVO.setAdminname(adminname);
				adminVO.setAdminnickname(adminnickname);
				adminVO.setPhone(phone);
				adminVO.setStatus(status);
				adminVO.setAdminid(adminid);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminVO", adminVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/admin/update_admin_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				//開始修改資料
				AdminServiceImpl adminSvc = new AdminServiceImpl();
				adminVO = adminSvc.updateAdmin(adminid, email, adminaccount, adminpassword, adminname, phone, status, adminnickname);
				//修改完成準備轉交
				req.setAttribute("adminVO", adminVO);
				String url = "/admin/listOneAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				break;
			}
			case"insert":
			{
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				
				
				String adminaccount = req.getParameter("adminaccount");
				String adminacReg =  "^[(a-zA-Z0-9_)]{5,20}$";
				if(adminaccount==null||adminaccount.trim().length() ==0) {
					errorMsgs.add("管理員帳號請勿空白");
				}else if (!adminaccount.trim().matches(adminacReg)) {
					errorMsgs.add("管理員帳號只能是英文字母數字和_，長度需在5~20之間");
				}
				
				String adminpassword = req.getParameter("adminpassword");
				String adminpsReg =  "^[(a-zA-Z0-9_)]{5,15}$";
				if(adminpassword==null||adminpassword.trim().length() ==0) {
					errorMsgs.add("管理員密碼請勿空白");
				}else if (!adminpassword.trim().matches(adminpsReg)) {
					errorMsgs.add("管理員密碼只能是英文字母數字和_，長度需在5~20之間");
				}

				String email = req.getParameter("email");
				String emailReg = "^[(a-zA-Z0-9_)]{5,50}$";
				if(email==null||email.trim().length() ==0) {
					errorMsgs.add("管理員密碼請勿空白");
				}else if (!email.trim().matches(emailReg)) {
					errorMsgs.add("管理員密碼只能是英文字母數字和_，長度需在5~20之間");
				}
				
				
				String adminname = req.getParameter("adminname");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if(adminname==null||adminname.trim().length() ==0) {
					errorMsgs.add("管理員名稱請勿空白");
				}else if (!adminname.trim().matches(nameReg)) {
					errorMsgs.add("管理員名稱只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}
				
				String phone = req.getParameter("phone");
				String phoneReg = "^[((0-9_)]{9,13}$";
				if(phone==null||phone.trim().length() ==0) {
					errorMsgs.add("電話號碼請勿空白");
				}else if (!phone.trim().matches(phoneReg)) {
					errorMsgs.add("電話號碼只能是數字, 且長度必需是9到13之間");
				}
				
				Integer status = null;
				status = Integer.valueOf(req.getParameter("status").trim());
				
				if(status<0 || status >2) {
					errorMsgs.add("請填入0,1,2 三種狀態");
				}
				try {
					status = Integer.valueOf(req.getParameter("status").trim());
				}catch(NumberFormatException e) {
					status = 0;
					errorMsgs.add("狀態請填入0,1,2 三種狀態");
				}
				
				String adminnickname = req.getParameter("adminnickname");
				String adminnicknameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if(adminnickname==null||adminnickname.trim().length() ==0) {
					errorMsgs.add("管理員暱稱請勿空白");
				}else if (!adminnickname.trim().matches(adminnicknameReg)) {
					errorMsgs.add("管理員暱稱只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}	
				
				AdminVO adminVO = new AdminVO();
				adminVO.setAdminaccount(adminaccount);
				adminVO.setAdminpassword(adminpassword);
				adminVO.setEmail(email);
				adminVO.setAdminname(adminname);
				adminVO.setAdminnickname(adminnickname);
				adminVO.setPhone(phone);
				adminVO.setStatus(status);
				//開始新增資料
				AdminServiceImpl adminSvc = new AdminServiceImpl();
				adminVO = adminSvc.addAdmin(email, adminaccount, adminpassword, adminname, phone, status, adminnickname);
				//新增完成準備轉交
				
				String url = "/admin/listAllAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				break;

			}	
			
			case"delete":
			{
				List<String> errorMsgs = new LinkedList<String>();
				
				req.setAttribute("errorMsgs", errorMsgs);
				//接受請求
				Integer adminid = Integer.valueOf(req.getParameter("adminid"));
				//開始刪除
				AdminServiceImpl adminSvc = new AdminServiceImpl();
				adminSvc.deleteAdmin(adminid);;
				//刪除完成 轉交頁面
				String url = "/admin/listAllAdmin.jsp";
				RequestDispatcher succesView = req.getRequestDispatcher(url);
				succesView.forward(req, res);
				
				
				
				break;
			}
			
			
			
			
		}

	}

}
