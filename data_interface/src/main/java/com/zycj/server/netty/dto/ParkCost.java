package com.zycj.server.netty.dto;

public class ParkCost {
	private String success;
	private String message;
	private String carNo;
	private String feeTotal;
	private String inTime;
	private String parkName;
	private Integer parkType;
	private Integer recordId;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public Integer getParkType() {
		return parkType;
	}

	public void setParkType(Integer parkType) {
		this.parkType = parkType;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getFeeTotal() {
		return feeTotal;
	}

	public void setFeeTotal(String feeTotal) {
		this.feeTotal = feeTotal;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

}
