package com.zycj.server.netty.dto;
/**
 * @description 返回结果数据格式
 * @author fengya
 * @date 2016-7-27 下午04:46:15
 */
public class Result {
	private String code;
	private String data;
	private String queryId;
	
	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
