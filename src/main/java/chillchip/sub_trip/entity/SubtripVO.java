package chillchip.sub_trip.entity;

import java.io.Serializable;
import java.sql.Clob;

public class SubtripVO implements Serializable{
	
	private Integer subtripid;
	private Integer tripid;
	private Integer index;
	private Clob context;
	
	
	public Integer getSubtripid() {
		return subtripid;
	}
	
	public void setSubtripid(Integer subtripid) {
		this.subtripid = subtripid;
	}

	public Integer getTripid() {
		return tripid;
	}

	public void setTripid(Integer tripid) {
		this.tripid = tripid;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Clob getContext() {
		return context;
	}

	public void setContect(Clob context) {
		this.context = context;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder("SubtripVO [")
				.append("subtripid=").append(subtripid)
				.append(",tripid=").append(tripid)
				.append(",index=").append(index)
				.append(",context=").append(context)
				.append("]").toString();
				}
}
