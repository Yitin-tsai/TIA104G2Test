package chillchip.location.model;

import static chillchip.util.Constants.PAGE_MAX_RESULT;

import java.util.List;

import chillchip.location.dao.LocationDAO;
import chillchip.location.dao.LocationDAOImplJDBC;
import chillchip.location.entity.LocationVO;

public class LocationService {
	
	
	private LocationDAO dao;
	
	public LocationService() {
		dao = new LocationDAOImplJDBC();
	}
	
	
	public LocationVO addlocation(LocationVO locationVO) {
		
		dao.insert(locationVO);
		return locationVO;
	}
	
	
	public LocationVO updatelocation(LocationVO locationVO) {
		
		dao.update(locationVO);
		return locationVO;
	}
	
	
	public void deletelocation(Integer locationid) {
		dao.delete(locationid);
	}
	
	public List<LocationVO> getAllLocationById () {
		return dao.getAll();
		
	}
	
	public int getLocationPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
	 
	

}
