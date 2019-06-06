package com.wl.ko.home.model;

import java.util.ArrayList;
import java.util.List;

public class ParkVoDetail {
	/**数据集合**/
	private ParkDetail data = new ParkDetail();

	/**分页信息对象**/
	private PageUtil page;
	
	/**成功是否**/
	private boolean success;
	
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ParkDetail getData() {
		return data;
	}

	public void setData(ParkDetail data) {
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
