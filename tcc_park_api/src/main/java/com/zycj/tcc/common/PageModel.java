package com.zycj.tcc.common;


/**
 * 分页相关
 * @author shandong
 *
 */
public class PageModel {
	/**
	 * 当前页
	 */
	private int currentPage = Constants.DEFAULT_PAGE;
	/**
	 * 跳转至页面
	 */
	private int page =1 ;
	/**
	 * 每页显示记录数
	 */
	private int limit = Constants.DEFAULT_LIMIT;
	/**
	 * 总记录数
	 */
	private int count;
	/**
	 * 页面展示页码数
	 */
	private int viewPages= Constants.DEFAULT_VIEW_PAGES;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getViewPages() {
		return viewPages;
	}
	public void setViewPages(int viewPages) {
		this.viewPages = viewPages;
	}
	
	public int getStartPage(){
		return (currentPage-viewPages/2)<=0?1:currentPage-viewPages/2;
	}
	
	public int getEndPage(){
		return (currentPage+viewPages/2)>=getTotalPages()?getTotalPages():currentPage+viewPages/2;
	}
	public int getTotalPages(){
		return count%limit==0?count/limit:count/limit+1;
	}
	/**
	 * @return 默认按创建时间排序
	 */
	public String getSort(){
		return Constants.DEFAULT_SORT;
	}
	/**
	 * @return 默认降序
	 */
	public String getOrder(){
		return Constants.ORDER_DOWN;
	}
	
	/**
	 * @return 记录起始索引
	 */
	public int getOffset() {
		return  (page - 1) * limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

}
