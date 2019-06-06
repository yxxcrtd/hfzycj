package com.tcc.park.api.vo;

import java.util.List;

public class ListResult<T> extends AppResult {
	
	private int pageNum; 
	private List<T> info;

	public List<T> getInfo() {
		return info;
	}

	public void setInfo(List<T> info) {
		this.info = info;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageCount) {
		this.pageNum = pageCount;
	}


}
