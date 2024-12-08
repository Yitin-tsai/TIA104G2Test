package chillchip.announce.dao;

import java.util.List;
import java.util.Map;

import chillchip.announce.entity.AnnounceVO;

public interface AnnounceDAO {
	
	public void insert(AnnounceVO annouceVO);
	public void update(AnnounceVO annouceVO);
	public void delete(Integer annouceid);
	public List<AnnounceVO> getAll();
	public List<AnnounceVO>  getByCompositeQuery(Map<String, String> map);
	public List<AnnounceVO> getAll(int currentPage);

}
