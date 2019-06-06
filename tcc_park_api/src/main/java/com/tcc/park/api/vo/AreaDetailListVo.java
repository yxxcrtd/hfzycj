package com.tcc.park.api.vo;

public class AreaDetailListVo {

	private String parkName;// 名称
	private String spaceTotal;// 停车位总个数
	private String parkNow;// 空闲停车位个数
	private String isFree;// 是否免费 // 1 是 0 否

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getSpaceTotal() {
		return spaceTotal;
	}

	public void setSpaceTotal(String spaceTotal) {
		this.spaceTotal = spaceTotal;
	}

	public String getParkNow() {
		return parkNow;
	}

	public void setParkNow(String parkNow) {
		this.parkNow = parkNow;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

}
