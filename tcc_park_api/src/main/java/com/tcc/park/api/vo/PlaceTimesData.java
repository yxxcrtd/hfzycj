package com.tcc.park.api.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PlaceTimesData implements Serializable {
	private String id;
	private String placeId;
	private Integer total = 0;
	private Integer surplus = 0;
	private Date sysTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getSurplus() {
		return surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}

}
