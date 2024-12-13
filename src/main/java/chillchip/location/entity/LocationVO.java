package chillchip.location.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import chilltrip.locationcomment.model.LocationCommentVO;

@Entity

@Table(name = "location")
public class LocationVO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id", updatable = false)
	private Integer Locationid;
	
	@Column(name = "address", updatable = false)
	@Lob
	private String address;
	
	@Column(name = "create_time", updatable = false)
	private Timestamp create_time;
	
	@Column(name = "comments_number", updatable = false)
	private String comments_number;
	
	@Column(name = "score", updatable = false)
	private String score;
	
	@Column(name = "location_name", updatable = false)
	private String location_name;
	
	@OneToMany(mappedBy= "locationvo",cascade = CascadeType.ALL)
	@OrderBy("createTime desc")
	private Set<LocationCommentVO> locationCommit;
	
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

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
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
