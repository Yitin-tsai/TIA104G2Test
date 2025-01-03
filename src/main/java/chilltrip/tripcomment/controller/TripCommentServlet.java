package chilltrip.tripcomment.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import chilltrip.tripcomment.model.TripCommentService;
import chilltrip.tripcomment.model.TripCommentVO;

@MultipartConfig
@WebServlet("/tripcomment")
public class TripCommentServlet extends HttpServlet {
	
	private TripCommentService tripCommentSvc;

	@Override
	public void init() throws ServletException {
		tripCommentSvc = new TripCommentService();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAllCommentsByTripId".equals(action)) { // 顯示行程的所有留言
			System.out.println("開始準備顯示...");
		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

			HttpSession session = req.getSession();  // 確保取得當前請求的 session
			Integer memberId = (Integer) session.getAttribute("memberId");

			System.out.println("Session ID: " + session.getId());  // 輸出 session ID
			
		    Integer tripId = Integer.valueOf(req.getParameter("tripId"));
		    
			System.out.println("tripId為:" + tripId);
		    System.out.println("memberId為:" + memberId);

		    if (memberId == null) {
		    	System.out.println("請先登入");
		        errorMsgs.add("請先登入");
		        RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member_login.html");
		        failureView.forward(req, res);
		        return;
		    }
		    
		    if (session == null || memberId == null) {
		        System.out.println("Session 已過期或未找到 memberId");
		    } else {
		        System.out.println("Session 有效，memberId: " + memberId);
		    }


		    List<TripCommentVO> tripCommentList = tripCommentSvc.getCommentsByTripId(tripId);
		    if (tripCommentList == null || tripCommentList.isEmpty()) {
		        errorMsgs.add("查無該行程留言");
		    }

		    if (!errorMsgs.isEmpty()) {
		        RequestDispatcher failureView = req.getRequestDispatcher("/frontend/go.html"); // 顯示行程頁面
		        failureView.forward(req, res);
		        return;
		    }
		    
		    req.setAttribute("tripCommentList", tripCommentList);
		    String url = "/frontend/go.html";  // 顯示行程和留言的頁面
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		    System.out.println("找到 " + tripCommentList.size() + " 則行程留言,tripId為: " + tripId);
		}

		if ("updateComment".equals(action)) { // 更新留言請求

		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    Integer memberId = (Integer) req.getSession().getAttribute("memberId");
		    if (memberId == null) {
		        errorMsgs.add("請先登入");
		        RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member_login.html");
		        failureView.forward(req, res);
		        return;
		    }

		    Integer tripCommentId = Integer.valueOf(req.getParameter("tripCommentId"));
		    
		    System.out.println("tripCommentId為: " + tripCommentId);

		    TripCommentVO tripCommentVO = tripCommentSvc.getTripComment(tripCommentId);
		    if (tripCommentVO == null) {
		        errorMsgs.add("該留言不存在");
		        System.out.println("該留言不存在");
		    } else if (!tripCommentVO.getMemberId().equals(memberId)) {
		        errorMsgs.add("您不能修改其他用戶的留言");
		        System.out.println("您不能修改其他用戶的留言");
		    }

		    if (!errorMsgs.isEmpty()) {
		        RequestDispatcher failureView = req.getRequestDispatcher("/frontend/go.html"); // 重定向到留言頁面
		        failureView.forward(req, res);
		        return;
		    }

		    Integer score = Integer.valueOf(req.getParameter("score"));
		    String content = req.getParameter("content");

		    Integer tripId = tripCommentVO.getTripId();
		    byte[] photo = tripCommentVO.getPhoto();
		    java.sql.Timestamp createTime = tripCommentVO.getCreateTime();
		    
		    System.out.println("更新留言: tripId=" + tripId + ", score=" + score + ", content=" + content);

		    tripCommentSvc.updateTripComment(tripCommentId, memberId, tripId, score, photo, createTime, content);

		    req.setAttribute("tripCommentVO", tripCommentVO);
		    String url = "/frontend/go.html";  // 顯示行程和留言的頁面
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		}


		if ("addComment".equals(action)) { // 新增留言請求

		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    Integer memberId = (Integer) req.getSession().getAttribute("memberId");
		    if (memberId == null) {
		        errorMsgs.add("請先登入");
		        // 登入檢查失敗，重定向到登入頁面
		        RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member_login.html");
		        failureView.forward(req, res);
		        return;
		    }

		    Integer tripId = Integer.valueOf(req.getParameter("tripId"));

		    TripCommentVO tripCommentVO = new TripCommentVO();

		    Integer score = null;
		    try {
		        score = Integer.valueOf(req.getParameter("score").trim());
		    } catch (NumberFormatException e) {
		        score = 0;
		        errorMsgs.add("請評分星星數");
		    }

		    // 處理圖片上傳
		    byte[] photo = null; // 初始化圖片資料為 null
		    Part part = req.getPart("photo");
		    // 若沒有圖片，photo 保持 null 或設為預設值
		    if (part != null && part.getSize() > 0) {
		        InputStream in = part.getInputStream();
		        photo = new byte[in.available()];
		        in.read(photo);
		        in.close();
		    }

		    Timestamp createTime = new Timestamp(System.currentTimeMillis()); // 生成當前時間

		    String content = null;
		    try {
		        content = String.valueOf(req.getParameter("content").trim());
		    } catch (NumberFormatException e) {
		        content = "";
		        errorMsgs.add("請填寫留言");
		    }

		    tripCommentVO.setMemberId(memberId);
		    tripCommentVO.setTripId(tripId);
		    tripCommentVO.setScore(score);
		    tripCommentVO.setPhoto(photo);
		    tripCommentVO.setCreateTime(createTime);
		    tripCommentVO.setContent(content);

		    if (!errorMsgs.isEmpty()) {
		        req.setAttribute("tripCommentVO", tripCommentVO);
		        RequestDispatcher failureView = req.getRequestDispatcher("/frontend/go.html");
		        failureView.forward(req, res);
		        return;
		    }
		    
		    System.out.println("新增行程留言: " + tripCommentVO);

		    TripCommentService tripCommentService = new TripCommentService();
		    tripCommentVO = tripCommentService.addTripComment(memberId, tripId, score, photo, createTime, content);

		    String url = "/frontend/go.html"; // 這裡可以設置留言成功後的跳轉頁面
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		}

		if ("deleteComment".equals(action)) { // 刪除留言請求

		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    Integer memberId = (Integer) req.getSession().getAttribute("memberId");
		    if (memberId == null) {
		        errorMsgs.add("請先登入");
		        RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member_login.html");
		        failureView.forward(req, res);
		        return;
		    }

		    Integer tripCommentId = Integer.valueOf(req.getParameter("tripCommentId"));
		    
		    System.out.println("刪除行程留言的tripCommentId: " + tripCommentId);

		    TripCommentVO tripCommentVO = tripCommentSvc.getTripComment(tripCommentId);
		    if (tripCommentVO == null) {
		        errorMsgs.add("該留言不存在");
		        System.out.println("該留言不存在");
		    } else if (!tripCommentVO.getMemberId().equals(memberId)) {
		        errorMsgs.add("您不能刪除其他用戶的留言");
		        System.out.println("您不能刪除其他用戶的留言");
		    }

		    if (!errorMsgs.isEmpty()) {
		        RequestDispatcher failureView = req.getRequestDispatcher("/frontend/go.html");
		        failureView.forward(req, res);
		        return;
		    }

		    tripCommentSvc.deleteTripComment(tripCommentId);
		    
		    System.out.println("成功刪除行程留言 tripCommentId: " + tripCommentId);

		    String url = "/frontend/go.html";
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		}
	}
}
