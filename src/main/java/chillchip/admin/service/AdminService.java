package chillchip.admin.service;

import java.util.List;

import chillchip.admin.entity.AdminVO;

public interface AdminService {
	AdminVO addAdmin(String email, String adminaccount, String adminpassword, String adminname, String phone,
			Integer status, String adminnickname);

	AdminVO updateAdmin(Integer adminid,String email, String adminaccount, String adminpassword, String adminname, String phone,
			Integer status, String adminnickname);

	void deleteAdmin(Integer adminid);

	List<AdminVO> getAll();

	AdminVO getOneAdmin(Integer adminid);

}
