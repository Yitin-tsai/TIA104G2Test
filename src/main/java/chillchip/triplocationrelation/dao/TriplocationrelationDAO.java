package chillchip.triplocationrelation.dao;

import java.util.List;
import java.util.Map;
import chillchip.triplocationrelation.model.TriplocationrelationVO;

public interface TriplocationrelationDAO {
	
	public void insert (TriplocationrelationVO triplocationrelationVO);
	public void update (TriplocationrelationVO triplocationrelationVO);
	public void delete (Integer trip_location_relation_id);
	public TriplocationrelationVO getTriplocationrelationById(Integer trip_location_relation_id);
	public List<TriplocationrelationVO> getAllTriplocationrelationByTrip();
	public List<Map<String, Object>> getAllTriplocationrelationByTripPro();

}
