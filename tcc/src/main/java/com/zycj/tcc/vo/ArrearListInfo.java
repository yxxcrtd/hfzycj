package com.zycj.tcc.vo;

public class ArrearListInfo {
	private String carNo;//车牌号
	private String inTime;//驶入时间
	private String outTime;//驶出时间
	private String sectionName;//路段名称
	private String arrearage;//欠费金额
	private String arrearageId;//欠费主键
	
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getArrearage() {
		return arrearage;
	}
	public void setArrearage(String arrearage) {
		this.arrearage = arrearage;
	}
	public String getArrearageId() {
		return arrearageId;
	}
	public void setArrearageId(String arrearageId) {
		this.arrearageId = arrearageId;
	}
	
	
}
