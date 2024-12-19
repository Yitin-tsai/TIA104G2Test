package chilltrip.member.model;

import java.util.Base64;
import java.util.List;
import java.util.Set;

import chilltrip.tripcomment.model.TripCommentVO;

public class MemberService {

	private MemberDAO_interface dao;
	
	public MemberService(){
		dao = new MemberJDBCDAO();
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		dao.insert(memberVO);
		return memberVO;
	}
	
	public MemberVO updateMember(MemberVO memberVO) {
		dao.update(memberVO);
		return memberVO;
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
	
	// 根據 email 查詢會員並驗證密碼
    public MemberVO login(String email, String password) {
        // 查詢會員資料
        MemberVO memberVO = dao.findByEmail(email);
        
        // 如果會員存在，檢查密碼是否正確
        if (memberVO != null && memberVO.getPassword().equals(password)) {
            return memberVO; // 登入成功，返回會員資料
        }
        
        return null; // 登入失敗，返回 null
    }
    
    // 將圖片轉換為Base64
    public String encodePhotoToBase64(MemberVO memberVO) {
        byte[] photo = memberVO.getPhoto();
        if (photo != null) {
            return Base64.getEncoder().encodeToString(photo); // 返回Base64字串
        }
        return null; // 若沒有照片則返回null
    }
    
    public boolean checkEmailExists(String email) {
        // 實作查詢資料庫的邏輯，假設有一個 MemberDAO 類別來處理 SQL 查詢
        return dao.isEmailExist(email);
    }
}
