package chillchip.location.dao;

import java.util.List;
import java.util.Map;

import chillchip.location.entity.LocationVO;

public interface LocationDAO {

	public void insert(LocationVO LocationVO);
	public void update(LocationVO LocationVO);
	public void delete(Integer Locationid);
	public List<LocationVO> getAll();
	public List<Map<String, Object>> getAllPro();
	public LocationVO getById(Integer Locationid);
	public List<Map<String, Object>> getByLocationName(String location_name);
}
