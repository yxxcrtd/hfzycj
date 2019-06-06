package com.zycj.server.netty.dto;
/**
 * @description 返回结果中的data数据格式
 * @author fengya
 * @date 2016-7-27 下午04:46:27
 */
public class ResultData {
	private String message;
	private String serverTime;

	public ResultData(String message, String serverTime) {
		super();
		this.message = message;
		this.serverTime = serverTime;
	}

	public ResultData() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getServerTime() {
		return serverTime;
	}

	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}

}
