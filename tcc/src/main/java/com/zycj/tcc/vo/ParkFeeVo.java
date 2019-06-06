package com.zycj.tcc.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 停车费用Vo
 * @author Administrator
 *
 */
public class ParkFeeVo {
	private Date inTime;
	private Date outTime;
	private BigDecimal amount;
	private String carNo;
	private String spaceNo;
	private String park;
	private String arrearAmount;
	private String freeType = "0";
	private String carType;
	
	public ParkFeeVo(Date inTime, Date outTime, BigDecimal amount,
			String carNo, String spaceNo,String park,String arrearAmount,String freeType,String carType) {
		this.inTime = inTime;
		this.outTime = outTime;
		this.amount = amount;
		this.carNo = carNo;
		this.spaceNo = spaceNo;
		this.park=park;
		this.arrearAmount = arrearAmount;
		this.freeType = freeType;
		this.carType = carType;
	}
	
//	public ParkFeeVo(Date inTime, Date outTime, BigDecimal amount,
//			String carNo, String spaceNo,String park,String arrearAmount,String carType) {
//		this.inTime = inTime;
//		this.outTime = outTime;
//		this.amount = amount;
//		this.carNo = carNo;
//		this.spaceNo = spaceNo;
//		this.park=park;
//		this.arrearAmount = arrearAmount;
//		this.carType = carType;
//	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getSpaceNo() {
		return spaceNo;
	}
	public void setSpaceNo(String spaceNo) {
		this.spaceNo = spaceNo;
	}
	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public String getArrearAmount() {
		return arrearAmount;
	}
	public void setArrearAmount(String arrearAmount) {
		this.arrearAmount = arrearAmount;
	}
	public String getFreeType() {
		return freeType;
	}
	public void setFreeType(String freeType) {
		this.freeType = freeType;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}
	
}
