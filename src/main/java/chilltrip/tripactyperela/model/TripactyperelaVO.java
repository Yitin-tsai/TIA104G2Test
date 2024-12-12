package chilltrip.tripactyperela.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import chilltrip.tripactype.model.TripactypeVO;

@Entity
@Table(name = "itinerary_activity_type_relationship")
public class TripactyperelaVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itinerary_activity_relationship_id", updatable = false, insertable = false)
	private Integer eventtyperelaid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trip_id", referencedColumnName = "trip_id")
	private TripVO tripid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_type_id", referencedColumnName = "event_type_id")
	private TripactypeVO eventtypeid;

	public Integer getEventtyperelaid() {
		return eventtyperelaid;
	}

	public TripVO getTripid() {
		return tripid;
	}

	public void setTripid(TripVO tripid) {
		this.tripid = tripid;
	}

	public void setEventtyperelaid(Integer eventtyperelaid) {
		this.eventtyperelaid = eventtyperelaid;
	}

	public TripactypeVO getEventtypeid() {
		return eventtypeid;
	}

	public void setEventtypeid(TripactypeVO eventtypeid) {
		this.eventtypeid = eventtypeid;
	}

	@Override
	public String toString() {
		return "TripactyperelaVO [eventtyperelaid=" + eventtyperelaid + ", eventtypeid=" + eventtypeid + "]";
	}
	
}
