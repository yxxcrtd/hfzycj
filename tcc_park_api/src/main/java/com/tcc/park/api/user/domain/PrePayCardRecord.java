package com.tcc.park.api.user.domain;

import java.util.Date;

public class PrePayCardRecord {
	private Integer id;
	private String cardNo;
	private Integer recordType;
	private String orderId;
	private Integer amount;
	private Date createTime;
	private String empNo;
	private String tip;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public PrePayCardRecord(Integer id, String cardNo, Integer recordType, String orderId, Integer amount,
			Date createTime, String empNo, String tip) {
		super();
		this.id = id;
		this.cardNo = cardNo;
		this.recordType = recordType;
		this.orderId = orderId;
		this.amount = amount;
		this.createTime = createTime;
		this.empNo = empNo;
		this.tip = tip;
	}
	public PrePayCardRecord() {
	}
	
}
