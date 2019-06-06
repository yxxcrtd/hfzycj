package com.wl.ko.home.model;

import java.util.ArrayList;
import java.util.List;

public class ParkVo {
	/**数据集合**/
	private List<ParkDetail> data = new ArrayList<ParkDetail>();

	/**分页信息对象**/
	private PageUtil page;
	
	/**成功是否**/
	private boolean success;

	
	/***编码****/
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<ParkDetail> getData() {
		return data;
	}

	public void setData(List<ParkDetail> data) {
		this.data = data;
	}

	public PageUtil getPage() {
		return page;
	}

	public void setPage(PageUtil page) {
		this.page = page;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}	
