package chillchip.trip.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.json.JSONArray;
import org.json.JSONObject;

import chillchip.trip.dao.TripDAO;
import chillchip.trip.dao.TripDAOImplJDBC;
import chillchip.trip.model.TripService;
import chillchip.trip.model.TripVO;

@WebServlet("/trip/trip.do")
public class TripServlet extends HttpServlet {

	private TripService tripSvc;

	public void init() {
		tripSvc = new TripService();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		switch (action) {

		case "addTrip":
			try {
				addTrip(req, res);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "updateTrip":
			updateTrip(req, res);
			break;
		case "deleteTrip":
			deleteTrip(req, res);
			break;
		case "getAllTrip":
			getAllTrip(req, res);
			break;
		case "getAllTripPro":
			getAllTripPro(req, res);
			break;
		case "getByMemberId":
			getByMemberId(req,res);
			break;
		case "changeTripStatus":
			changeTripStatus(req,res);
			break;
		case "getMemberTripByStatus":
			getMemberTripByStatus(req,res);
			break;
		case "getTripByLocation":
			getTripByLocation(req,res);
			break;
		case "getById":
			getById(req,res);
			break;
		case "getTripByName":
			getTripByName(req,res);
			break;
		}
	}

	// 新增行程
	private String addTrip(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SerialException, SQLException {

		Integer memberid = Integer.valueOf(req.getParameter("member_id"));
		String tripabstract = req.getParameter("abstrsct");

		String str_createtime = req.getParameter("trip_create_time");
//			Timestamp createtime = Timestamp.valueOf(req.getParameter("trip_create_time")); 不建議這樣寫，前端格式不對會一直拋出例外
		Integer collections = Integer.valueOf(req.getParameter("collections"));
		Integer status = Integer.valueOf(req.getParameter("status"));
		Integer overallscore = Integer.valueOf(req.getParameter("overall_score"));
		Integer overallscoredpeople = Integer.valueOf(req.getParameter("overall_scored_people"));
		Integer locationnumber = Integer.valueOf(req.getParameter("location_number"));
		String articletitle = req.getParameter("article_title");
		Integer visitorsnumber = Integer.valueOf(req.getParameter("visitors_number"));
		Integer likes = Integer.valueOf(req.getParameter("likes"));

		TripVO tripVO = new TripVO();
		Timestamp tripcreatetime = null;

		tripVO.setMemberId(memberid);
		tripVO.setTrip_abstract(tripabstract);
		// 處理時間格式
		try {
			if (str_createtime != null && !str_createtime.trim().isEmpty()) {
				tripcreatetime = Timestamp.valueOf(str_createtime);
			}
		} catch (IllegalArgumentException e) {
			// 處理時間格式錯誤
			System.out.println("時間格式錯誤：" + e.getMessage());
		}
		tripVO.setCreate_time(tripcreatetime);
		tripVO.setCollections(collections);
		tripVO.setStatus(status);
		tripVO.setOverall_score(overallscore);
		tripVO.setOverall_scored_people(overallscoredpeople);
		tripVO.setLocation_number(locationnumber);
		tripVO.setArticle_title(articletitle);
		tripVO.setVisitors_number(visitorsnumber);
		tripVO.setLikes(likes);
		tripSvc.addTrip(tripVO);

		return "新增成功！";
	}

	// 編輯行程
	private String updateTrip(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Integer tripid = Integer.valueOf(req.getParameter("trip_id"));
		Integer memberid = Integer.valueOf(req.getParameter("member_id"));
		String tripabstract = req.getParameter("abstrsct");
		String str_createtime = req.getParameter("trip_create_time");
		Integer collections = Integer.valueOf(req.getParameter("collections"));
		Integer status = Integer.valueOf(req.getParameter("status"));
		Integer overallscore = Integer.valueOf(req.getParameter("overall_score"));
		Integer overallscoredpeople = Integer.valueOf(req.getParameter("overall_scored_people"));
		Integer locationnumber = Integer.valueOf(req.getParameter("location_number"));
		String articletitle = req.getParameter("article_title");
		Integer visitorsnumber = Integer.valueOf(req.getParameter("visitors_number"));
		Integer likes = Integer.valueOf(req.getParameter("likes"));

//		TripDAO dao = new TripDAOImplJDBC();
//		TripVO tripVO = dao.getById(tripid);
		TripVO tripVO = tripSvc.getById(tripid);
		Timestamp tripcreatetime = null;
		
		tripVO.setMemberId(memberid);
		tripVO.setTrip_abstract(tripabstract);
		// 處理時間格式
		try {
			if (str_createtime != null && !str_createtime.trim().isEmpty()) {
				tripcreatetime = Timestamp.valueOf(str_createtime);
			}
		} catch (IllegalArgumentException e) {
			// 處理時間格式錯誤
			System.out.println("時間格式錯誤：" + e.getMessage());
		}
		tripVO.setCreate_time(tripcreatetime);
		tripVO.setCollections(collections);
		tripVO.setStatus(status);
		tripVO.setOverall_score(overallscore);
		tripVO.setOverall_scored_people(overallscoredpeople);
		tripVO.setLocation_number(locationnumber);
		tripVO.setArticle_title(articletitle);
		tripVO.setVisitors_number(visitorsnumber);
		tripVO.setLikes(likes);
		tripSvc.updateTrip(tripVO);

		return "修改成功！";
	}

	// 刪除行程
	private String deleteTrip(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Integer tripid = Integer.valueOf(req.getParameter("trip_id"));
		tripSvc.deleteTrip(tripid);

		res.setContentType("text/plain");
		res.setCharacterEncoding("UTF-8");

		// 回傳成功訊息給前端
		res.getWriter().write("success");

		return "刪除成功！";
	}

	// 獲取行程列表-->按照時間順序排列
	private void getAllTrip(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<TripVO> triplist = tripSvc.getAllTrip();

		JSONArray jsonArray = new JSONArray();
		for (TripVO trip : triplist) {
			JSONObject jsonRes = new JSONObject();
			jsonRes.put("tripid", trip.getTrip_id());
			jsonRes.put("memberid", trip.getMemberId());
			jsonRes.put("abstract", trip.getTrip_abstract());
			jsonRes.put("createtime", trip.getCreate_time());
			jsonRes.put("collections", trip.getCollections());
			jsonRes.put("status", trip.getStatus());
			jsonRes.put("overallscore", trip.getOverall_score());
			jsonRes.put("overallscoredpeople", trip.getOverall_scored_people());
			jsonRes.put("locationnumber", trip.getLocation_number());
			jsonRes.put("articletitle", trip.getArticle_title());
			jsonRes.put("visitorsnumber", trip.getVisitors_number());
			jsonRes.put("likes", trip.getLikes());

			jsonArray.put(jsonRes);
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonArray.toString());
	}

	// 用list的格式拿到行程列表-->按照時間順序排列
	private void getAllTripPro(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<Map<String, Object>> triplist = tripSvc.getAllTripPro();

		JSONArray jsonArray = new JSONArray();

		for (Map<String, Object> trip : triplist) {
			JSONObject jsonRes = new JSONObject();
			for (String key : trip.keySet()) {
				jsonRes.put(key, trip.get(key));
			}
			jsonArray.put(jsonRes);
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonArray.toString());
	}
	
	// 獲取某位用戶所有的文章（包含私人的與公開的）
	private void getByMemberId (HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		Integer memberid = Integer.valueOf(req.getParameter("member_id"));
		
		List<Map<String, Object>> triplist = tripSvc.getByMemberId(memberid);
		JSONArray jsonArray = new JSONArray();

		for (Map<String, Object> trip : triplist) {
			JSONObject jsonRes = new JSONObject();
			for (String key : trip.keySet()) {
				jsonRes.put(key, trip.get(key));
			}
			jsonArray.put(jsonRes);
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonArray.toString());
	}
	
	// 更改文章狀態 Ex:文章下架-->某用戶刪除文章或是文章被檢舉遭到管理員下架
	private String changeTripStatus (HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		Integer tripid = Integer.valueOf(req.getParameter("trip_id"));
		Integer status = Integer.valueOf(req.getParameter("status"));
		
		TripVO tripVO = tripSvc.getById(tripid);
		tripVO.setTrip_id(tripid);
		tripVO.setStatus(status);
		tripSvc.changeTripStatus(tripVO);
		
		return "文章狀態修改成功";
	}
	
	// 拿到某用戶的私人/公開/下架文章 GET_MEMBER_TRIP_BY_STATUS
	private void getMemberTripByStatus (HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		Integer memberid = Integer.valueOf(req.getParameter("member_id"));
		Integer status = Integer.valueOf(req.getParameter("status"));
		
		List<Map<String, Object>> triplist = tripSvc.getMemberTripByStatus(memberid, status);
		JSONArray jsonArray = new JSONArray();

		for (Map<String, Object> trip : triplist) {
			JSONObject jsonRes = new JSONObject();
			for (String key : trip.keySet()) {
				jsonRes.put(key, trip.get(key));
			}
			jsonArray.put(jsonRes);
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonArray.toString());
	}
	
	//用景點查詢行程
	private void getTripByLocation (HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		String locationname = req.getParameter("location_name");
		
		List<Map<String, Object>> triplist = tripSvc.getTripByLocation(locationname);
		JSONArray jsonArray = new JSONArray();

		for (Map<String, Object> trip : triplist) {
			JSONObject jsonRes = new JSONObject();
			for (String key : trip.keySet()) {
				jsonRes.put(key, trip.get(key));
			}
			jsonArray.put(jsonRes);
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonArray.toString());
	}
	
	private void getById (HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		Integer tripid = Integer.valueOf(req.getParameter("trip_id"));
		
		TripVO tripVO = tripSvc.getById(tripid);
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("tripid", tripVO.getTrip_id());
		jsonRes.put("memberid", tripVO.getMemberId());
		jsonRes.put("abstract", tripVO.getTrip_abstract());
		jsonRes.put("createtime", tripVO.getCreate_time());
		jsonRes.put("collections", tripVO.getCollections());
		jsonRes.put("status", tripVO.getStatus());
		jsonRes.put("overallscore", tripVO.getOverall_score());
		jsonRes.put("overallscoredpeople", tripVO.getOverall_scored_people());
		jsonRes.put("locationnumber", tripVO.getLocation_number());
		jsonRes.put("articletitle", tripVO.getArticle_title());
		jsonRes.put("visitorsnumber", tripVO.getVisitors_number());
		jsonRes.put("likes", tripVO.getLikes());
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonRes.toString());
		}
	
	//根據文章標題查詢文章
	private void getTripByName (HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		String articletitle = req.getParameter("article_title");
		System.out.println(articletitle);
		
		List<Map<String, Object>> triplist = tripSvc.getTripByName(articletitle);
		
		System.out.println(triplist);
		JSONArray jsonArray = new JSONArray();

		for (Map<String, Object> trip : triplist) {
			JSONObject jsonRes = new JSONObject();
			for (String key : trip.keySet()) {
				jsonRes.put(key, trip.get(key));
			}
			jsonArray.put(jsonRes);
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonArray.toString());
	}
	

}
