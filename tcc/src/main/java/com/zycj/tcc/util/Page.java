package com.zycj.tcc.util;

/**
 * 分页信息类
 * 
 * @author 洪捃能
 * 
 */
public class Page {
	// 当前页
	private int currentPage;
	// 每页显示记录数
	private int pageSize;
	// 总页数
	private int totalPage;

	public Page() {
	}

	public Page(int currentPage,int pageSize,int totalPage) {
		this.currentPage=currentPage;
		this.pageSize=pageSize;
		this.totalPage=totalPage;
	}
	public Page(int currentPage,int pageSize) {
		this.currentPage=currentPage;
		this.pageSize=pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
