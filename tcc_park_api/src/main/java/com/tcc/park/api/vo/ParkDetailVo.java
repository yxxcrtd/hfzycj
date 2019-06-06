package com.tcc.park.api.vo;

public class ParkDetailVo {

	private String parkName;// 名称
	private String parkTotal;// 停车位总个数
	private String parkNow;// 当前停车位个数
	
	private String parkLocation;
	private String businessTime;
	private String feeDescription;
	private String signDescription;
	private String isFree;// 是否免费
	
	
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
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
	public String getParkLocation() {
		return parkLocation;
	}
	public void setParkLocation(String parkLocation) {
		this.parkLocation = parkLocation;
	}
	public String getBusinessTime() {
		return businessTime;
	}
	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}
	public String getFeeDescription() {
		return feeDescription;
	}
	public void setFeeDescription(String feeDescription) {
		this.feeDescription = feeDescription;
	}
	public String getSignDescription() {
		return signDescription;
	}
	public void setSignDescription(String signDescription) {
		this.signDescription = signDescription;
	}
	public String getIsFree() {
		return isFree;
	}
	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}
	
}
