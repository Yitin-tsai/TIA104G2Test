package chillchip.announce.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import chillchip.admin.model.AdminDAO;
import chillchip.admin.model.AdminDAOImplJDBC;
import chillchip.admin.model.AdminVO;
import chillchip.announce.modal.AnnounceService;
import chillchip.announce.modal.AnnounceVO;

@WebServlet("/announce/announce.do")
public class AnnounceServelet extends HttpServlet {
	
	private AnnounceService announceSvc;

	public void init() {
		announceSvc = new AnnounceService();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch(action) {
			case"getAll":
				forwardPath = getAllAnnounce(req,res);
				break;
			case"getAnnounceByAdmin":
				forwardPath = getByAdmin(req,res);
				break;
			case"delete":
				forwardPath = deleteAnnounce(req,res);
				break;
			case"addAnnounce":
				forwardPath = addAnnounce(req,res);
				break;
			case"compositeQuery":
				forwardPath = getCompositeQuery(req,res);
				break;
			case"upadate":
				forwardPath = update(req,res);
				break;
		}
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	
	
	private String getAllAnnounce(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		
		List<AnnounceVO> announceList = announceSvc.getAllAnnounce(currentPage);
		
		if(req.getSession().getAttribute("announcePageQty") == null) {
			int announcePageQty = announceSvc.getPageTotal();
			req.getSession().setAttribute("announcePageQty", announcePageQty);
		}
		
		req.setAttribute("announceList", announceList);
		req.setAttribute("currentPage", currentPage);
		
			
		return "/announce/listAllAnnounce.jsp";
	}
	
	private String getByAdmin(HttpServletRequest req, HttpServletResponse res) {
		String admin = req.getParameter("adminid");
		int adminid = Integer.parseInt(admin);
		List<AnnounceVO> AnnounceList = announceSvc.getAnnounceByAdminid(adminid);
		
		req.setAttribute("announceList", AnnounceList);
		
		return "/announce/AdminAnnounce.jsp";
	}
	
	private String deleteAnnounce(HttpServletRequest req, HttpServletResponse res) {
		
		Integer announceid = Integer.valueOf(req.getParameter("announceid"));
		announceSvc.deleteannounce(announceid);		
		return "/announce/listAllAnnounce.jsp";
	}
	private String addAnnounce(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		Integer adminid = Integer.valueOf(req.getParameter("adminid"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		Date starttime = Date.valueOf(req.getParameter("starttime").trim());
		Date endtime = Date.valueOf(req.getParameter("endtime").trim());
		
		byte[] photo = null; // 初始化圖片資料為 null
		Part part = req.getPart("photo");
		InputStream in = part.getInputStream();
		photo = new byte[in.available()]; // byte[] buf = in.readAllBytes(); // Java 9 的新方法
		in.read(photo);
		in.close();
		
	    AdminDAO dao = new AdminDAOImplJDBC();
	    AdminVO  adminvo = dao.getById(adminid);
		
		AnnounceVO announce = new AnnounceVO();
		
		announce.setAdminvo(adminvo);
		announce.setContent(content);
		announce.setTitle(title);
		announce.setStarttime(starttime);
		announce.setEndtime(endtime);
		announce.setCoverphoto(photo);
		announceSvc.addannounce(announce);
		
		
		return "/announce/AdminAnnounceList.jsp";
	}
	private String getCompositeQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();
		
		if (map!= null) {
			List<AnnounceVO> announceList = announceSvc.getAnnounceByCompositeQuery(map);
			req.setAttribute("anounceList", announceList);
		}else {
			return"/frontend/index.html";
		}
					
		return "/announce/listCompositeQueryAnnounce.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		Integer adminid = Integer.valueOf(req.getParameter("adminid"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		Date starttime = Date.valueOf(req.getParameter("starttime").trim());
		Date endtime = Date.valueOf(req.getParameter("endtime").trim());
		
		byte[] photo = null; // 初始化圖片資料為 null
		Part part = req.getPart("photo");
		InputStream in = part.getInputStream();
		photo = new byte[in.available()];
		in.read(photo);
		in.close();
		
	    AdminDAO dao = new AdminDAOImplJDBC();
	    AdminVO  adminvo = dao.getById(adminid);
		
		AnnounceVO announce = new AnnounceVO();
		
		announce.setAdminvo(adminvo);
		announce.setContent(content);
		announce.setTitle(title);
		announce.setStarttime(starttime);
		announce.setEndtime(endtime);
		announce.setCoverphoto(photo);
		announceSvc.updateannounce(announce);
		
		
		
		
		return "/announce/listAllAnnounce.jsp";
	}
}
