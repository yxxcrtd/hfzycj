package com.zycj.tcc.vo;

import java.util.List;

public class ArrearListVo<T> {

	private String orderNo;
	private String totalMoney;
	private String serverTime;
	private List<T> info;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public List<T> getInfo() {
		return info;
	}
	public void setInfo(List<T> info) {
		this.info = info;
	}
	public String getServerTime() {
		return serverTime;
	}
	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}
	
	
	
}
