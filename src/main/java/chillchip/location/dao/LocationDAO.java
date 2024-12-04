package chillchip.location.dao;

import java.util.List;

import chillchip.location.entity.LocationVO;

public interface LocationDAO {

	public void insert(LocationVO LocationVO);
	public void update(LocationVO LocationVO);
	public void delete(Integer Locationid);
	public List<LocationVO> getAll();
	public LocationVO getById(Integer Locationid);
}
