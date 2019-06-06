package com.zycj.tcc.util;

/**
 * 分页信息辅助类
 * @author 洪捃能
 */
public class PageUtil {
	
	
	/**
	 * 获得Page实例
	 */
	public static Page createPage(int currentPage,int pageSize) {
		currentPage = getCurrentPage(currentPage);
		pageSize=getPageSize(pageSize);
		return new Page(currentPage,pageSize);
	}
	
	/**
	 * 获得当前页
	 */
	public static int getCurrentPage(int currentPage) {
		return currentPage == 0 ? 1 : currentPage;
	}
	
	/**
	 * 获得每页显示记录数
	 */
	public static int getPageSize(int everyPage) {
		return everyPage == 0 ? 20 : everyPage; 
	}
	
	/**
	 * 获得总页数
	 */
	public static int getTotalPage(int totalCount, int pageSize) {
		int totalPage = 0;
		if(totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		} else {
			totalPage = totalCount / pageSize + 1;
		}
		return totalPage;
	}
}
