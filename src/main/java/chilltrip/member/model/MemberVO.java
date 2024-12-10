package chilltrip.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class MemberVO {
	private Integer memberId;
	private String email;
	private String account;
	private String password;
	private String name;
	private String phone;
	private Integer status;
	private Timestamp createTime;
	private String nickName;
	private Integer gender;
	private Date birthday;
	private String companyId;
	private String ereceiptCarrier;
	private String creditCard;
	private Integer trackingNumber;
	private Integer fansNumber;
	private byte[] photo;
	
	public Integer getMemberId() {
		return memberId;
	}
	public String getEmail() {
		return email;
	}
	public String getAccount() {
		return account;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public Integer getStatus() {
		return status;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public String getNickName() {
		return nickName;
	}
	public Integer getGender() {
		return gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public String getCompanyId() {
		return companyId;
	}
	public String getEreceiptCarrier() {
		return ereceiptCarrier;
	}
	public String getCreditCard() {
		return creditCard;
	}
	public Integer getTrackingNumber() {
		return trackingNumber;
	}
	public Integer getFansNumber() {
		return fansNumber;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public void setEreceiptCarrier(String ereceiptCarrier) {
		this.ereceiptCarrier = ereceiptCarrier;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	public void setTrackingNumber(Integer trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public void setFansNumber(Integer fansNumber) {
		this.fansNumber = fansNumber;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", email=" + email + ", account=" + account + ", password=" + password
				+ ", name=" + name + ", phone=" + phone + ", status=" + status + ", createTime=" + createTime
				+ ", nickName=" + nickName + ", gender=" + gender + ", birthday=" + birthday + ", companyId="
				+ companyId + ", ereceiptCarrier=" + ereceiptCarrier + ", creditCard=" + creditCard
				+ ", trackingNumber=" + trackingNumber + ", fansNumber=" + fansNumber + ", photo="
				+ Arrays.toString(photo) + "]";
	}
	
}
