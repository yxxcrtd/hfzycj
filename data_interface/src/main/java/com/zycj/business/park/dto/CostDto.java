package com.zycj.business.park.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zycj.business.park.model.PayMentRecord;

public class CostDto {
	private Integer id;
	private String parkName;
	private String carNo;
	private Date inTime;
	private int dataType; // 0:路边停车，1：商业停车场
	private Date outTime;
	private Double payTotal;
	private Double amount;
	private Double arrearAmount;
	private String spaceNo;
	private boolean breakPay;
	private List<PayMentRecord> payRecord = new ArrayList<PayMentRecord>();
	
	public List<PayMentRecord> getPayRecord() {
		return payRecord;
	}

	public void setPayRecord(List<PayMentRecord> payRecord) {
		this.payRecord = payRecord;
	}

	public boolean isBreakPay() {
		return breakPay;
	}

	public void setBreakPay(boolean breakPay) {
		this.breakPay = breakPay;
	}

	public Double getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(Double payTotal) {
		this.payTotal = payTotal;
	}

	public Double getArrearAmount() {
		return arrearAmount;
	}

	public void setArrearAmount(Double arrearAmount) {
		this.arrearAmount = arrearAmount;
	}

	public String getSpaceNo() {
		return spaceNo;
	}

	public void setSpaceNo(String spaceNo) {
		this.spaceNo = spaceNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
