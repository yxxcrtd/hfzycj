package com.zycj.tcc.vo;

import java.util.Date;

/**
 * 车辆驶入vo
 * @author 洪捃能
 *
 */
public class CarEntryVo {
	private String arrearage;//欠费金额
	private Date inTime;//车辆驶入时间
	private String orderId;
	private String freeType;
	public String getFreeType() {
		return freeType;
	}
	public void setFreeType(String freeType) {
		this.freeType = freeType;
	}
	public CarEntryVo(){}
	public CarEntryVo(String arrearage, Date inTime,String orderId) {
		this.arrearage = arrearage;
		this.inTime = inTime;
		this.orderId = orderId;
	}
	
	public CarEntryVo(String arrearage, Date inTime, String orderId, String freeType) {
		super();
		this.arrearage = arrearage;
		this.inTime = inTime;
		this.orderId = orderId;
		this.freeType = freeType;
	}
	public String getArrearage() {
		return arrearage;
	}
	public void setArrearage(String arrearage) {
		this.arrearage = arrearage;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
