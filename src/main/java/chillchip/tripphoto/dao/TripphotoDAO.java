package chillchip.tripphoto.dao;

import java.util.List;
import java.util.Map;

import chillchip.tripphoto.model.TripphotoVO;

public interface TripphotoDAO {
	
	public void insert(TripphotoVO tripphotoVO);
	public void update(TripphotoVO tripphotoVO);
	public void delete(Integer trip_photo_id);
	public List<Map<String, Object>> getOneTripPhotoByType(Integer tripid,Integer triptype);
	public TripphotoVO getTripphotoById (Integer trip_photo_id);
}
