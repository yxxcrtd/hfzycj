package com.zycj.tcc.vo;

public class PlaceOrderDetail {
	private String merchantId;
	private String orderNo;
	private String orderReqNo;
	private String orderDate;
	private String ourTransNo;
	private Integer transAmt;
	private String transStatus;
	private Integer encodeType;
	private String sign;
	private String mac;
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderReqNo() {
		return orderReqNo;
	}
	public void setOrderReqNo(String orderReqNo) {
		this.orderReqNo = orderReqNo;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOurTransNo() {
		return ourTransNo;
	}
	public void setOurTransNo(String ourTransNo) {
		this.ourTransNo = ourTransNo;
	}
	public Integer getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(Integer transAmt) {
		this.transAmt = transAmt;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public Integer getEncodeType() {
		return encodeType;
	}
	public void setEncodeType(Integer encodeType) {
		this.encodeType = encodeType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
