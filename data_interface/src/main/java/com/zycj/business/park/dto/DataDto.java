package com.zycj.business.park.dto;


public class DataDto {
	private boolean success;
	
	private Object data;
	
	private Object page = new Object();
	
	private String code;
	
	private String msg;
	

	public DataDto() {
		super();
	}

	public DataDto(boolean success, String code) {
		super();
		this.success = success;
		this.code = code;
	}
	
	

	public DataDto(boolean success, String code, Object data, String msg) {
		super();
		this.success = success;
		this.data = data;
		this.code = code;
		this.msg = msg;
	}
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getPage() {
		return page;
	}

	public void setPage(Object page) {
		this.page = page;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
