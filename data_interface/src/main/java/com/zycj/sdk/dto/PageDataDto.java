package com.zycj.sdk.dto;

import java.util.List;

public class PageDataDto {
	private Long total;

	private List<Object> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

}
