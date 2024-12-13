package chilltrip.locationcomment.model;

import java.util.List;

import chillchip.location.entity.LocationVO;

public interface LocationCommentDAO {
	
	public void insert(LocationCommentVO locationCommentVO);
	public void update(LocationCommentVO locationCommentVO);
	public void delete(LocationCommentVO locationCommentVO);
	public List<LocationCommentVO> getByLocation(Integer locationId);
	public List<LocationCommentVO> getByMember(Integer memberId);
	
}
