package chilltrip.tripactype.model;

import java.util.List;

public interface TripactypeDAO_interface {
	
	public void insert(TripactypeVO itineraryActivityTypeVO);
	public void update(TripactypeVO itineraryActivityTypeVO);
	public void delete(Integer eventtypeid);
	public List<TripactypeVO> getAll();
	public TripactypeVO getById(Integer eventtypeid);
	
}
