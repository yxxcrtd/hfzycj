package com.zycj.sdk.base.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * <p> Title: 公共平台-Page.java </p>
 * <p> Description: Information manage System </p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company: yaln </p>
 * @author fengya
 * @version 1.0
 * @date  2014-10-8 下午03:15:44
 * @param <T>
 */
public class Page<T> implements Serializable{
	private static final long serialVersionUID = -637461860820111335L;
	
	/**
	 * 当前第几页，从第1页开始。
	 */
	protected int pageNo = 1;
	/**
	 * 每页显示的数据个数
	 */
	protected int pageSize = 10;
	/**
	 * 查询结果集
	 */
	protected List<T> result = new ArrayList<T>();
	
	/**
	 * 查询总记录数
	 */
	protected long totalCount = 0;
	/**
	 * 总页数
	 */
	protected int totalPages;
	

	public List<T> getResult() {
		return result;
	}

	public void setResult(final List<T> result) {
		this.result = result;
	}
	

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}
	
	public void setTotalPages(final int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}
}