package chilltrip.triparea.model;

import java.util.List;
import java.util.Set;

public interface TripAreaDAO_interface {
	public void insert(TripAreaVO tripAreaVO);
	public void update(TripAreaVO tripAreaVO);
	public void delete(Integer triplocationid);
	public TripAreaVO findByPrimaryKey(Integer triplocationid);
	public List<TripAreaVO> getAll();
	// 查詢某行程地區的行程
	public List<TripAreaVO> getTripBytripArea(String regioncontent);
}
