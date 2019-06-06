package com.zycj.tcc.util;

import java.util.Date;

public class FeeRegularInfo {
	private Integer regid;//计费规则id
	private Integer carType;// 车类型：1=小车，2=大车
	private Date startTime;//收费开始时间
	private Date endTime;//收费结束时间
	private Integer intervalTime;//收费间隔时间
	private Double cost;//每个收费间隔时间产生的停车费
	private Double maxCost;//最高停车费
	private String timeType;//时间类型，相对于intervalTime、freeTime、firstChargeTime的时间单位(hr:小时，min：分钟,sec:秒)
	private Integer freeTime;//免费时间，在这个时间段内不产生停车费
	private Integer firstChargeTime;//过了免费时间后的第一次收费时间
	private Double firstChargeTimeCost;//过了免费时间后的第一次停车费
	
	
	public Integer getRegid() {
		return regid;
	}
	public void setRegid(Integer regid) {
		this.regid = regid;
	}
	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(Integer intervalTime) {
		this.intervalTime = intervalTime;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public Integer getFreeTime() {
		return freeTime;
	}
	public void setFreeTime(Integer freeTime) {
		this.freeTime = freeTime;
	}
	public Integer getFirstChargeTime() {
		return firstChargeTime;
	}
	public void setFirstChargeTime(Integer firstChargeTime) {
		this.firstChargeTime = firstChargeTime;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Double getMaxCost() {
		return maxCost;
	}
	public void setMaxCost(Double maxCost) {
		this.maxCost = maxCost;
	}
	public Double getFirstChargeTimeCost() {
		return firstChargeTimeCost;
	}
	public void setFirstChargeTimeCost(Double firstChargeTimeCost) {
		this.firstChargeTimeCost = firstChargeTimeCost;
	}
	
}
