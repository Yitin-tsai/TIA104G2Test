package chillchip.location.dao;

import java.util.List;
import java.util.Map;

import chillchip.location.entity.LocationVO;

public interface LocationDAO {

	public void insert(LocationVO locationVO);
	public void update(LocationVO locationVO);
	public void delete(Integer locationid);
	public List<LocationVO> getAll();
	public List<LocationVO> getAll(int currentPage);
	public List<Map<String, Object>> getAllPro();
	public LocationVO getById(Integer locationid);
	public List<Map<String, Object>> getByLocationName(String location_name);

}