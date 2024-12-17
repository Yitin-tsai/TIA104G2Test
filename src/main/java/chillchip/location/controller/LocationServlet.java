package chillchip.location.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chillchip.location.entity.LocationVO;
import chillchip.location.model.LocationService;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/location/location.do")
public class LocationServlet extends HttpServlet{
	
	private LocationService locationSvc;
	
	
	public void init() {
		locationSvc = new LocationService();
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
		
		switch (action) {
		case "getAllLocation":
			getAllLocation(req,res);
			break;
		case "getLocationById":
			getLocationById(req,res);
			break;
		
		}
		
	}
	
	
	//拿到景點列表，按照pk（ID）排序
	private void getAllLocation (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		List<LocationVO> locationlist = locationSvc.getAllLocation();
			
		JSONArray jsonArray = new JSONArray();
		
		for(LocationVO location : locationlist) {
			JSONObject jsonRes = new JSONObject();
			jsonRes.put("locationid",location.getLocationid());
			jsonRes.put("address",location.getAddress());
			jsonRes.put("create_time", location.getCreate_time());
			jsonRes.put("comments_number", location.getComments_number());
			jsonRes.put("score", location.getScore());
			jsonRes.put("location_name", location.getLocation_name());
			
			jsonArray.put(jsonRes);
			
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonArray.toString());
	}
	
	
	//單一查詢（用ID查詢）
	private void getLocationById (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		
	}
	
	
	

}
