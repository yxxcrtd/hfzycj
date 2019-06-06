package com.zycj.tcc.server.domain;

import java.util.Date;

import com.zycj.tcc.server.vo.Request;
import com.zycj.tcc.server.vo.Response;

/**
 * 交易数据
 * @author 洪捃能
 *
 */
public class TransactionData {
	private String ipPort;
	private Request request;
	private Response response;
	private Date reqTime;
	private long reqTimeMillis;
	private long respTimeMillis;
	
	public String getIpPort() {
		return ipPort;
	}
	public void setIpPort(String ipPort) {
		this.ipPort = ipPort;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public Date getReqTime() {
		return reqTime;
	}
	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}
	public long getReqTimeMillis() {
		return reqTimeMillis;
	}
	public void setReqTimeMillis(long reqTimeMillis) {
		this.reqTimeMillis = reqTimeMillis;
	}
	public long getRespTimeMillis() {
		return respTimeMillis;
	}
	public void setRespTimeMillis(long respTimeMillis) {
		this.respTimeMillis = respTimeMillis;
	}
	
}
