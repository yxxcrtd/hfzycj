package com.zycj.server.netty.dto;

import io.netty.channel.ChannelHandlerContext;

/**
 * @description 数据查询参数
 * @author fengya
 * @date 2016-7-27 下午04:55:47
 */
public class Param {
	private ChannelHandlerContext context;
	private long lastTime;
	private String empNo;
	private String posNo;
	private String parkId;

	public Param() {
		super();
	}

	public Param(ChannelHandlerContext context, long lastTime, String empNo, String posNo, String parkId) {
		super();
		this.context = context;
		this.lastTime = lastTime;
		this.empNo = empNo;
		this.posNo = posNo;
		this.parkId = parkId;
	}

	public ChannelHandlerContext getContext() {
		return context;
	}

	public void setContext(ChannelHandlerContext context) {
		this.context = context;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
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

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

}
