package chilltrip.member.model;

import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import chilltrip.tripcomment.model.TripCommentVO;
import redis.clients.jedis.Jedis;

public class MemberService {

	private MemberDAO_interface dao;
	// 使用 Redis
    private Jedis jedis = new Jedis("localhost", 6379);
	
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
    
	// 用來生成隨機驗證碼
    public String generateCode() {
    	
    	StringBuilder verificationCode = new StringBuilder();
        
        for (int i = 0; i < 6; i++) {
            int randomDigit = (int)(Math.random() * 9) + 1;  // 生成 1 到 9 之間的隨機數字
            verificationCode.append(randomDigit);  // 將隨機數字加入驗證碼
        }
        
        return verificationCode.toString();  // 返回生成的六位數驗證碼
    }

    // 發送驗證碼郵件
    public void sendEmail(String email, String code) {
        String from = "s9408375@gmail.com";  // 發送者的信箱
        String password = "gqnpjuoolferejzq";  // 信箱密碼

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("您的信箱驗證碼");
            message.setText("親愛的用戶您好,您的信箱驗證碼為: " + code + "，請於 5 分鐘內輸入驗證碼完成註冊。");

            Transport.send(message);
            System.out.println("郵件已發送至: " + email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // 驗證信箱的驗證碼
    public boolean verifyEmailCode(String email, String emailCode) {
    	
        String storedCode = jedis.get("verifyEmailCode:" + email);
        return storedCode != null && storedCode.equals(emailCode);
    }
}
