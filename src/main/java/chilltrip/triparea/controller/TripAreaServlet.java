package chilltrip.triparea.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chilltrip.triparea.model.TripAreaService;
import chilltrip.triparea.model.TripAreaVO;

@WebServlet("/triparea")
public class TripAreaServlet extends HttpServlet{

	private TripAreaService tripAreaSvc;
	
	public void init() {
		tripAreaSvc = new TripAreaService();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if("getTripBytripArea".equals(action)) {  // 接收請求找尋行程地區的行程
			// 取得前端選擇的行程地區名稱
			String regioncontent  = req.getParameter("regioncontent");
			System.out.println("選擇的行程地區：" + regioncontent);
			
			// 根據選擇的地區查詢行程資料
			List<TripAreaVO> tripList = tripAreaSvc.getTripBytripArea(regioncontent);
			
			if (tripList != null && !tripList.isEmpty()) {
                System.out.println("找到的行程列表：");
                for (TripAreaVO tripArea : tripList) {
                    System.out.println("行程地區：" + tripArea.getRegioncontent());
                    System.out.println("行程標題：" + tripArea.getTripid().getArticle_title());
                    System.out.println("行程摘要：" + tripArea.getTripid().getTrip_abstract());
                    System.out.println("遊客數：" + tripArea.getTripid().getVisitors_number());
                    System.out.println("喜歡數：" + tripArea.getTripid().getLikes());
                    System.out.println("-------------------------------");
                }
            } else {
                System.out.println("沒有找到相關行程");
            }
			
			req.setAttribute("tripList", tripList);
			
			RequestDispatcher successView = req.getRequestDispatcher("/frontend/go.html");
			successView.forward(req, res);
			System.out.println("查詢成功轉跳篩選後頁面");
		}

	}
}
