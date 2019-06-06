/**
 * 
 */
package com.zycj.tcc.domain.criteria;


import org.apache.commons.lang.StringUtils;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.PageModel;

/**
 * 资讯信息相关 查询条件
* Title: NewsDetailCriteria.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月9日
 */
public class NewsDetailCriteria extends PageModel {
	/**
	 * 状态：0:新添加1:上线,2:下限
	 */
	private Integer status;
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 查询开始日期
	 * @return
	 */
	private String beginDate;
	
	/**
	 * 查询结束日期
	 * @return
	 */
	private String endDate;

	public Integer getStatus() {
		return null==status?null:(status == Constants.UN_SELECT ? null : status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getTitle() {
		return StringUtils.isBlank(title) ? null : title.trim();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBeginDate() {
		return StringUtils.isBlank(beginDate) ? null : beginDate.trim();
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return StringUtils.isBlank(endDate) ? null : endDate.trim();
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


}
