package com.zycj.tcc.vo;

import java.util.Date;

public class RoadSpaceSectionInfo {
	private Integer sectionId;//路段id
	private Integer spaceStatus;//车位状态
	private Integer isParked;//车位是否停车
	private Date parkTime;//停车时间
	private Integer feeType;//计费规则id
	private String sectionName;//路段名称
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public Integer getSpaceStatus() {
		return spaceStatus;
	}
	public void setSpaceStatus(Integer spaceStatus) {
		this.spaceStatus = spaceStatus;
	}
	public Integer getIsParked() {
		return isParked;
	}
	public void setIsParked(Integer isParked) {
		this.isParked = isParked;
	}
	public Integer getFeeType() {
		return feeType;
	}
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	public Date getParkTime() {
		return parkTime;
	}
	public void setParkTime(Date parkTime) {
		this.parkTime = parkTime;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	
}
