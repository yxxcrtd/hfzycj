package com.wl.ko.home.model;

public class PayRecord {
	/*****支付编号*****/
	private String id;
	
	/****订单编号******/
	private String orderId;
	
	/****支付时间*****/
	private String payTime;
	
	/****支付类型***/
	private String payType;
	
	/**实际支付**/
	private String realPay;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRealPay() {
		return realPay;
	}

	public void setRealPay(String realPay) {
		this.realPay = realPay;
	}
}
