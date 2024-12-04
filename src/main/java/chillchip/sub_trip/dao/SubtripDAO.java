package chillchip.sub_trip.dao;

import java.util.List;

import chillchip.sub_trip.entity.SubtripVO;

public interface SubtripDAO {
	
	public void insert(SubtripVO subtripVO);
	public void update(SubtripVO subtripVO);
	public void delete(Integer subtripid);
	public List<SubtripVO> getAll();
	public SubtripVO getById(Integer subtrip);
}
