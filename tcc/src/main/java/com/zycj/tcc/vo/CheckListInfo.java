package com.zycj.tcc.vo;

public class CheckListInfo {

	private String isToday;//是否今天（1表示今天、0表示欠）、
	private String date;//日期(2014-09-11)
	private String amount;//金额(0.00)
	private String requstTime;//当前请求时间点
	private String id;//历史未扎帐id
	
	public String getIsToday() {
		return isToday;
	}
	public void setIsToday(String isToday) {
		this.isToday = isToday;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRequstTime() {
		return requstTime;
	}
	public void setRequstTime(String requstTime) {
		this.requstTime = requstTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
