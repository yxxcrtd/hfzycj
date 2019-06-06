package com.tcc.park.api.vo;

import java.math.BigDecimal;

public class LandmarkVo implements Comparable<LandmarkVo> {
	private Integer id;
	private Integer typeId;
	private String name;
	private String addr;
	private String longitude;// 经度
	private String latitude;// 纬度
	private String distance;// 距离
	private Integer total; // 停车场总车位数
	private Integer surplus; // 停车场空闲车位数
	private String code; // 停车场编码
	private boolean isUse;

	public boolean isUse() {
		return isUse;
	}

	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(LandmarkVo o) {
		BigDecimal srcDis = new BigDecimal(this.distance);
		BigDecimal dstDis = new BigDecimal(o.getDistance());
		return srcDis.compareTo(dstDis);
	}
}
