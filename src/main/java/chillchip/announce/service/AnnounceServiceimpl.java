package chillchip.announce.service;

import java.util.List;
import java.util.Map;

import chillchip.announce.dao.AnnounceDAO;
import chillchip.announce.dao.AnnounceDAOimpl;
import chillchip.announce.entity.AnnounceVO;

public class AnnounceServiceimpl implements AnnounceService {
 
	private AnnounceDAO dao;
	 
	public AnnounceServiceimpl() {
		 dao = new AnnounceDAOimpl();
	}
	
	@Override
	public AnnounceVO addannounce(AnnounceVO announceVO) {
		// TODO Auto-generated method stub
		dao.insert(announceVO);
		return announceVO;
	}

	@Override
	public AnnounceVO updateannounce(AnnounceVO announceVO) {
		// TODO Auto-generated method stub
		dao.update(announceVO);
		return announceVO;
	}

	@Override
	public void deleteannounce(AnnounceVO announceVO) {
		// TODO Auto-generated method stub
		dao.delete(announceVO.getAnnounceid());
		
	}

	@Override
	public AnnounceVO getAnnounceByAdminid(Integer adminid) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<AnnounceVO> getAllAnnounce(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AnnounceVO> getAnnounceByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
