package chillchip.sub_trip.entity;

import java.io.Serializable;
import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import chillchip.trip.entity.TripVO;

@Entity

@Table(name="sub_trip")
public class SubtripVO implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="sub_trip_id" , updatable = false)
	private Integer subtripid;
	
	@ManyToOne
	@JoinColumn(name="trip_id" ,referencedColumnName = "trip_id")
	private TripVO tripVO;
	//private Integer tripid;
	
	@Column (name = "index")
	private Integer index;
	
	@Column (name = "content")
	@Lob
	private Clob content;
	
	
	public Integer getSubtripid() {
		return subtripid;
	}
	
	public void setSubtripid(Integer subtripid) {
		this.subtripid = subtripid;
	}
	
	public TripVO getTripVO() {
		return tripVO;
	}
	
	public setTripVO (TripVO tripVO) {
		this.tripVO = tripVO;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Clob getContent() {
		return content;
	}

	public void setContect(Clob content) {
		this.content = content;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder("SubtripVO [")
				.append("subtripid=").append(subtripid)
				.append(",tripVO=").append(tripVO)
				.append(",index=").append(index)
				.append(",content=").append(content)
				.append("]").toString();
				}
}
