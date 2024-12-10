package chilltrip.member.model;

import java.util.List;
import java.util.Set;

import chilltrip.tripcomment.model.TripCommentVO;

public class MemberService {

	private MemberDAO_interface dao;
	
	public MemberService(){
		dao = new MemberJDBCDAO();
	}
	
	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
	public MemberVO getOneMember(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}
	
	public Set<TripCommentVO> getTripCommentByMember(Integer memberId){
		return dao.getTripCommentByMember(memberId);
	}
	
	public void deleteMember(Integer memberId) {
		dao.delete(memberId);
	}
}
