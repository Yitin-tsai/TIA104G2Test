package chillchip.announce.service;

import java.util.List;
import java.util.Map;

import chillchip.announce.entity.AnnounceVO;

public interface AnnounceService {
		AnnounceVO addannounce(AnnounceVO announceVO);
		
		AnnounceVO updateannounce(AnnounceVO announceVO);
		
		void deleteannounce(AnnounceVO announceVO);
		
		AnnounceVO getAnnounceByAdminid(Integer adminid);
		
		List<AnnounceVO> getAllAnnounce(int currentPage);
		
		int getPageTotal();
		
		List<AnnounceVO> getAnnounceByCompositeQuery(Map<String, String[]> map);
	
		
		
		
		
}
