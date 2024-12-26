package chillchip.triplocationrelation.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import chillchip.triplocationrelation.model.TriplocationrelationService;
import chillchip.triplocationrelation.model.TriplocationrelationVO;

@WebServlet("/triplocationrelation/triplocationrelation.do")
public class TriplocationrelationServlet extends HttpServlet {

	private TriplocationrelationService triplocationrelationSvc;

	public void init() {
		triplocationrelationSvc = new TriplocationrelationService();
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

		case "addTriplocationrelation":
			addTriplocationrelation(req, res);
			break;
		case "updateTriplocaionrelation":
			updateTriplocaionrelation(req, res);
			break;
		case "delete":
			delete(req,res);
			break;
		case "getAllTriplocationrelationByTrip":
			getAllTriplocationrelationByTrip(req,res);
			break;
		case "getAllTriplocationrelationByTripPro":
			getAllTriplocationrelationByTripPro(req,res);
			break;
		case "getTriplocationrelationById":
			getTriplocationrelationById(req,res);
			break;
		}
	}

	// 新增
	private String addTriplocationrelation(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Integer subtripid = Integer.valueOf(req.getParameter("subtrip_id"));
		Integer locationid = Integer.valueOf(req.getParameter("location_id"));
		Integer index = Integer.valueOf(req.getParameter("triplocationrelation_index"));
		String timestart = req.getParameter("time_start");
		String timeend = req.getParameter("time_end");

		TriplocationrelationVO triplocationrelationVO = new TriplocationrelationVO();
		Timestamp tlr_timestart = null;
		Timestamp tlr_timeend = null;

		triplocationrelationVO.setSub_trip_id(subtripid);
		triplocationrelationVO.setLocation_id(locationid);
		triplocationrelationVO.setIndex(index);
		// 處理開始時間格式
		try {
			if (timestart != null && !timestart.trim().isEmpty()) {
				tlr_timestart = Timestamp.valueOf(timestart);
			}
		} catch (IllegalArgumentException e) {
			// 處理時間格式錯誤
			System.out.println("開始時間格式錯誤：" + e.getMessage());
		}
		triplocationrelationVO.setTime_start(tlr_timestart);
		// 處理結束時間格式
		try {
			if (timeend != null && !timeend.trim().isEmpty()) {
				tlr_timeend = Timestamp.valueOf(timeend);
			}
		} catch (IllegalArgumentException e) {
			// 處理時間格式錯誤
			System.out.println("結束時間格式錯誤：" + e.getMessage());
		}
		triplocationrelationVO.setTime_end(tlr_timeend);
		triplocationrelationSvc.addTriplocationrelation(triplocationrelationVO);

		return "新增成功！";
	}
	
	//修改
	private String updateTriplocaionrelation(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Integer triplocationrelationid = Integer.valueOf(req.getParameter("trip_location_relation_id"));
		Integer subtripid = Integer.valueOf(req.getParameter("subtrip_id"));
		Integer locationid = Integer.valueOf(req.getParameter("location_id"));
		Integer index = Integer.valueOf(req.getParameter("triplocationrelation_index"));
		String timestart = req.getParameter("time_start");
		String timeend = req.getParameter("time_end");

		TriplocationrelationVO triplocationrelationVO = triplocationrelationSvc
				.getTriplocationrelationById(triplocationrelationid);
		Timestamp tlr_timestart = null;
		Timestamp tlr_timeend = null;

		triplocationrelationVO.setSub_trip_id(subtripid);
		triplocationrelationVO.setLocation_id(locationid);
		triplocationrelationVO.setIndex(index);
		// 處理開始時間格式
		try {
			if (timestart != null && !timestart.trim().isEmpty()) {
				tlr_timestart = Timestamp.valueOf(timestart);
			}
		} catch (IllegalArgumentException e) {
			// 處理時間格式錯誤
			System.out.println("開始時間格式錯誤：" + e.getMessage());
		}
		triplocationrelationVO.setTime_start(tlr_timestart);
		// 處理結束時間格式
		try {
			if (timeend != null && !timeend.trim().isEmpty()) {
				tlr_timeend = Timestamp.valueOf(timeend);
			}
		} catch (IllegalArgumentException e) {
			// 處理時間格式錯誤
			System.out.println("結束時間格式錯誤：" + e.getMessage());
		}
		triplocationrelationVO.setTime_end(tlr_timeend);
		triplocationrelationSvc.updateTriplocaionrelation(triplocationrelationVO);
		return "修改成功！";
	}
	
	//刪除
	private String delete (HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Integer triplocationrelationid = Integer.valueOf(req.getParameter("trip_location_relation_id"));
		triplocationrelationSvc.delete(triplocationrelationid);
		res.setContentType("text/plain");
		res.setCharacterEncoding("UTF-8");

		// 回傳成功訊息給前端
		res.getWriter().write("success");
		return "刪除成功！";
	}
	
	// 列出某子行程中的所有景點行程關係(按照index排序）
	private void getAllTriplocationrelationByTrip (HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		List<TriplocationrelationVO> triplocationrelationlist = triplocationrelationSvc.getAllTriplocationrelationByTrip();
		JSONArray jsonArray = new JSONArray();
		for (TriplocationrelationVO triplocationrelation : triplocationrelationlist) {
			JSONObject jsonRes = new JSONObject();
			jsonRes.put("triplocationrelationid", triplocationrelation.getTrip_location_relation_id());
			jsonRes.put("subtripid", triplocationrelation.getSub_trip_id());
			jsonRes.put("locationid", triplocationrelation.getLocation_id());
			jsonRes.put("index", triplocationrelation.getIndex());
			jsonRes.put("timestart", triplocationrelation.getTime_start());
			jsonRes.put("timeend", triplocationrelation.getTime_end());
			
			jsonArray.put(jsonRes);
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonArray.toString());		
	}
	
	private void getAllTriplocationrelationByTripPro (HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		List<Map<String, Object>> triplocationrelationlist = triplocationrelationSvc.getAllTriplocationrelationByTripPro();
		System.out.println(triplocationrelationlist);
		
		JSONArray jsonArray = new JSONArray();
		
		for (Map<String, Object> triplocationrelation : triplocationrelationlist) {
			JSONObject jsonRes = new JSONObject();
			for (String key : triplocationrelation.keySet()) {
				jsonRes.put(key, triplocationrelation.get(key));
			}
			jsonArray.put(jsonRes);
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonArray.toString());
	}
	
	
	private void getTriplocationrelationById  (HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Integer triplocationrelationid = Integer.valueOf(req.getParameter("trip_location_relation_id"));
		TriplocationrelationVO triplocationrelationVO = triplocationrelationSvc.getTriplocationrelationById(triplocationrelationid);
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("triplocationrelationid", triplocationrelationVO.getTrip_location_relation_id());
		jsonRes.put("subtripid", triplocationrelationVO.getSub_trip_id());
		jsonRes.put("locationid", triplocationrelationVO.getLocation_id());
		jsonRes.put("index", triplocationrelationVO.getIndex());
		jsonRes.put("timestart", triplocationrelationVO.getTime_start());
		jsonRes.put("timeend", triplocationrelationVO.getTime_end());
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonRes.toString());
	}
	

}
