package com.zycj.tcc.server.vo;

/**
 * 分页数据响应类
 * 
 * @author 洪捃能
 * 
 */
public class PagingResponse extends DataResponse {

	// 分页明细
	private Object Paging;

	public Object getPaging() {
		return Paging;
	}

	public void setPaging(Object paging) {
		Paging = paging;
	}
}
