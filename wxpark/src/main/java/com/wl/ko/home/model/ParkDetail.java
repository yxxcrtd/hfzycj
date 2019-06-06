package com.wl.ko.home.model;

import java.util.ArrayList;
import java.util.List;

public class ParkDetail {
	/**停车费用**/
	private double amount;
	
	/***欠费金额**/
	private double arrearAmount;
	
	/**是否显示出支付按钮信息**/
	private boolean breakPay;
	
	/**车牌***/
	private String carNo;
	
	/***停车路段**/
	private String parkName;
	
	/**驶入时间**/
	private String inTime;
	
	/**驶出时间**/
	private String outTime;
	
	/**订单类型**/
	private String dataType;
	
	/**车位号*/
	private String spaceNo;
	
	/**订单编号**/
	private String id;
	
	/***支付总的金额***/
	private String payTotal;
	
	/***支付记录**/
	private List<PayRecord> payRecord = new ArrayList<PayRecord>();
	
	public boolean isBreakPay() {
		return breakPay;
	}

	public void setBreakPay(boolean breakPay) {
		this.breakPay = breakPay;
	}

	public String getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(String payTotal) {
		this.payTotal = payTotal;
	}

	public List<PayRecord> getPayRecord() {
		return payRecord;
	}

	public void setPayRecord(List<PayRecord> payRecord) {
		this.payRecord = payRecord;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getArrearAmount() {
		return arrearAmount;
	}

	public void setArrearAmount(double arrearAmount) {
		this.arrearAmount = arrearAmount;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getSpaceNo() {
		return spaceNo;
	}

	public void setSpaceNo(String spaceNo) {
		this.spaceNo = spaceNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
