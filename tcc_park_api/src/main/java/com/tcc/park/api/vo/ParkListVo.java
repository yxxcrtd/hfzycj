package com.tcc.park.api.vo;

import java.math.BigDecimal;

public class ParkListVo implements Comparable<ParkListVo>{

	private String parkId;
	private String parkName;// 名称
	private String contactInfo;// 联系方式
	private String longitude;// 经度
	private String latitude;// 纬度
	private String parkTotal;// 停车位总个数
	private String parkNow;// 当前停车位个数
	private String distance;// 距离
	private String isFree;// 是否免费

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getParkTotal() {
		return parkTotal;
	}

	public void setParkTotal(String parkTotal) {
		this.parkTotal = parkTotal;
	}

	public String getParkNow() {
		return parkNow;
	}

	public void setParkNow(String parkNow) {
		this.parkNow = parkNow;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

	@Override
	public int compareTo(ParkListVo o) {
		BigDecimal srcDis = new BigDecimal(this.distance);
		BigDecimal dstDis = new BigDecimal(o.getDistance());
		if (srcDis.compareTo(dstDis)==0) {
			return Integer.valueOf(o.isFree)-Integer.valueOf(isFree);
		}else {
			return new BigDecimal(this.distance).compareTo(new BigDecimal(o.getDistance()));
		}
	}

}
