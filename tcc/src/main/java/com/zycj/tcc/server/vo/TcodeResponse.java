package com.zycj.tcc.server.vo;

/**
 * 带接口编号数据响应类
 * @author 洪捃能
 *
 */
public class TcodeResponse extends Response {

	private Response response;
	private String tcode;
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public String getTcode() {
		return tcode;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	
}
