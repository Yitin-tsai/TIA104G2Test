package chillchip.trip.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class TripVO implements Serializable{
	
	private Integer trip_id;
	private Integer memberId; //這個格式與yuki對應
	private String trip_abstract; //abstract是保留字，所以做了修改
	private Timestamp creat_time;
	private Integer collections;
	private Integer status;
	private Integer overall_score;
	private Integer overall_scored_people;
	private Integer location_number;
	private String article_title;
	private Integer visitors_number;
	private Integer likes;
	
	public Integer getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getTrip_abstract() {
		return trip_abstract;
	}
	public void setTrip_abstract(String trip_abstract) {
		this.trip_abstract = trip_abstract;
	}
	public Timestamp getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(Timestamp creat_time) {
		this.creat_time = creat_time;
	}
	public Integer getCollections() {
		return collections;
	}
	public void setCollections(Integer collections) {
		this.collections = collections;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOverall_score() {
		return overall_score;
	}
	public void setOverall_score(Integer overall_score) {
		this.overall_score = overall_score;
	}
	public Integer getOverall_scored_people() {
		return overall_scored_people;
	}
	public void setOverall_scored_people(Integer overall_scored_people) {
		this.overall_scored_people = overall_scored_people;
	}
	public Integer getLocation_number() {
		return location_number;
	}
	public void setLocation_number(Integer location_number) {
		this.location_number = location_number;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public Integer getVisitors_number() {
		return visitors_number;
	}
	public void setVisitors_number(Integer visitors_number) {
		this.visitors_number = visitors_number;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	
	

}
