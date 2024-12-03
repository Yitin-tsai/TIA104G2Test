package chillchip.admin.entity;

import java.io.Serializable;
import java.sql.Date;

public class AdminVO  implements Serializable{
		private Integer adminid;
		private String email;
		private String adminaccount;
		private String adminpassword;
		private String adminname;
		private String phone;
		private Integer status ;
		private Date createtime;
		private String adminnickname;
		
		public Integer getAdminid() {
			return adminid;
		}
		public void setAdminid(Integer adminid) {
			this.adminid = adminid;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAdminaccount() {
			return adminaccount;
		}
		public void setAdminaccount(String adminaccount) {
			this.adminaccount = adminaccount;
		}
		public String getAdminpassword() {
			return adminpassword;
		}
		public void setAdminpassword(String adminpassword) {
			this.adminpassword = adminpassword;
		}
		public String getAdminname() {
			return adminname;
		}
		public void setAdminname(String adminname) {
			this.adminname = adminname;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Date getCreatetime() {
			return createtime;
		}
		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}
		public String getAdminnickname() {
			return adminnickname;
		}
		public void setAdminnickname(String adminnickname) {
			this.adminnickname = adminnickname;
		}
		@Override
		public String toString() {
			return "AdminVO [adminid=" + adminid + ", email=" + email + ", adminaccount=" + adminaccount
					+ ", adminpassword=" + adminpassword + ", adminname=" + adminname + ", phone=" + phone + ", status="
					+ status + ", createtime=" + createtime + ", adminnickname=" + adminnickname + "]";
		}
		
}
