package chilltrip.triparea.model;

import java.util.List;
import java.util.Set;

public class TripAreaService {

	private TripAreaDAO_interface dao;
	
	public TripAreaService() {
		dao = new TripAreaJDBCDAO();
	}
	
	public TripAreaVO addTripArea(TripAreaVO tripAreaVO) {
		dao.insert(tripAreaVO);
		return tripAreaVO;
	}
	
	public TripAreaVO updateTripArea(TripAreaVO tripAreaVO) {
		dao.update(tripAreaVO);
		return tripAreaVO;
	}
	
	public List<TripAreaVO> getAll(){
		return dao.getAll();
	}
	
	public List<TripAreaVO> getTripBytripArea(String regioncontent){
		return dao.getTripBytripArea(regioncontent);
	}
	
	public void deleteTripArea(Integer triplocationid) {
		dao.delete(triplocationid);
	}
}
