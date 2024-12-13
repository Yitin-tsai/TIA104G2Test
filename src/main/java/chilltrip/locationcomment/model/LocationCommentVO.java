package chilltrip.locationcomment.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import chillchip.location.entity.LocationVO;
import chilltrip.member.model.MemberVO;

@Entity

@Table(name = "location_comment")
public class LocationCommentVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_comment_id", updatable = false)
	private Integer locationCommitId;
	
	@ManyToOne
	@JoinColumn(name = "location_id", referencedColumnName = "location_id" )
	private LocationVO locationvo;
	
	@ManyToOne
	@JoinColumn(name = "member_id",  referencedColumnName = "member_id")
	private MemberVO membervo;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "photo")
	private byte[] photo;
	
	@Column(name = "score")
	private Integer score;
	
	@Column(name = "create_time")
	private Timestamp createTime;

}
