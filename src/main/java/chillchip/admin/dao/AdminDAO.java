package chillchip.admin.dao;

import java.util.List;

import chillchip.admin.entity.AdminVO;

public interface AdminDAO {

	public void insert(AdminVO adminVO);
	public void update(AdminVO adminVO);
	public void delete(Integer adminid);
	public List<AdminVO> getAll();
	public AdminVO getById(Integer adminid);
}
