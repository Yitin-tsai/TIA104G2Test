package chillchip.location.entity;

import java.io.Serializable;

public class LocationVO  implements Serializable{
		private Integer Locationid;
		private String address;
		private String create_time;
		private String comments_number;
		private String score;
		private String location_name;

		public Integer getLocationid() {
			return Locationid;
		}
		public void setLocationid(Integer Locationid) {
			this.Locationid = Locationid;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCreate_time() {
			return create_time;
		}
		public void setCreate_time(String create_time) {
			this.create_time = create_time;
		}
		public String getComments_number() {
			return comments_number;
		}
		public void setComments_number(String comments_number) {
			this.comments_number = comments_number;
		}
		public String getScore() {
			return score;
		}
		public void setScore(String score) {
			this.score = score;
		}
		public String getLocation_name() {
			return location_name;
		}
		public void setLocation_name(String location_name) {
			this.location_name = location_name;
		}

		@Override
		public String toString() {
			return "LocationVO [Locationid=" + Locationid + ", address=" + address + ", create_time=" + create_time
					+ ", comments_number=" + comments_number + ", score=" + score + ", location_name=" + location_name
					+ "]";
		}
		
}
