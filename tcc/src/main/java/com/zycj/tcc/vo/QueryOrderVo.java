package com.zycj.tcc.vo;

/**
 * 根据车牌号或泊车号查询最近一笔交易Vo
 * @author 洪捃能
 *
 */
public class QueryOrderVo {
	private Integer isIn;//是否为驶入状态  1 表示驶入 0表示未驶入状态
	private Integer orderId;//订单id
	private String parkName;//停车场
	private String carNo;//车牌号
	private String spaceNo;//泊位号,
	private String empNo;//驶入员工
	private String posNo;//驶入终端编号
	private String inTime;//驶入时间
	private String empNoOut;//驶出员工
	private String posNoOut;//驶出终端
	private String outTime;//驶出时间,
	private String account;//停车金额
	private String outType;//驶出方式
	private String arrearage;//历史欠费
	private String carType;//
	public Integer getIsIn() {
		return isIn;
	}
	public void setIsIn(Integer isIn) {
		this.isIn = isIn;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getPosNo() {
		return posNo;
	}
	public void setPosNo(String posNo) {
		this.posNo = posNo;
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}
	public String getArrearage() {
		return arrearage;
	}
	public void setArrearage(String arrearage) {
		this.arrearage = arrearage;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getEmpNoOut() {
		return empNoOut;
	}
	public void setEmpNoOut(String empNoOut) {
		this.empNoOut = empNoOut;
	}
	public String getPosNoOut() {
		return posNoOut;
	}
	public void setPosNoOut(String posNoOut) {
		this.posNoOut = posNoOut;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
}
