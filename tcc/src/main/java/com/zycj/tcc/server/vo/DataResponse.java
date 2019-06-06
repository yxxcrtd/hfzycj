package com.zycj.tcc.server.vo;



/**
 * 响应数据类
 * @author 洪捃能
 */
public class DataResponse extends Response {
	//结果数据
	private Object data;

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
