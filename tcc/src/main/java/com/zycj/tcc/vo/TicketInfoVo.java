package com.zycj.tcc.vo;

/**
 * 月票查询信息
 * @author 洪捃能
 */
public class TicketInfoVo {

	private String carNo;//车牌
	private String type;//1-年票，2-半年票，3-月票
	private String userWay;//有效路段：安庆路（A路至B路段）
	private String userTime;//有效时间：
	private String userType;// 发放对象：1-个人，2-公司
	private String buyer;//购票人
	private String ticketNo;//月票编号
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserWay() {
		return userWay;
	}
	public void setUserWay(String userWay) {
		this.userWay = userWay;
	}
	public String getUserTime() {
		return userTime;
	}
	public void setUserTime(String userTime) {
		this.userTime = userTime;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	

}
