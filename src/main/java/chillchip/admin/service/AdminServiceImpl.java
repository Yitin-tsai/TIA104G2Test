package chillchip.admin.service;

import java.util.List;

import chillchip.admin.dao.AdminDAO;
import chillchip.admin.dao.AdminDAOImplJDBC;
import chillchip.admin.entity.AdminVO;

public class AdminServiceImpl implements AdminService {

	private AdminDAO dao;

	public AdminServiceImpl() {
		dao = new AdminDAOImplJDBC();
	}

	@Override
	public AdminVO addAdmin(String email, String adminaccount, String adminpassword, String adminname, String phone,
			Integer status, String adminnickname) {
		AdminVO adminVO = new AdminVO();

		adminVO.setEmail(email);
		adminVO.setAdminaccount(adminaccount);
		adminVO.setAdminpassword(adminpassword);
		adminVO.setAdminname(adminname);
		adminVO.setPhone(phone);
		adminVO.setStatus(status);
		adminVO.setAdminnickname(adminnickname);
		dao.insert(adminVO);

		return adminVO;
	}

	@Override
	public AdminVO updateAdmin(Integer adminid, String email, String adminaccount, String adminpassword,
			String adminname, String phone, Integer status, String adminnickname) {
		AdminVO adminVO = new AdminVO();
		
		adminVO.setAdminid(adminid);
		adminVO.setEmail(email);
		adminVO.setAdminaccount(adminaccount);
		adminVO.setAdminpassword(adminpassword);
		adminVO.setAdminname(adminname);
		adminVO.setPhone(phone);
		adminVO.setStatus(status);
		adminVO.setAdminnickname(adminnickname);
		dao.insert(adminVO);

		return adminVO;
	}

	@Override
	public void deleteAdmin(Integer adminid) {
		dao.delete(adminid);

	}

	@Override
	public List<AdminVO> getAll() {
		return dao.getAll();
	}

	@Override
	public AdminVO getOneAdmin(Integer adminid) {
		// TODO Auto-generated method stub
		return dao.getById(adminid);
	}

}
