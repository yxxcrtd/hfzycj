/**
 * 
 */
package com.zycj.tcc.domain.criteria;

import org.apache.commons.lang.StringUtils;

import com.zycj.tcc.common.PageModel;

/**会员反馈
 * 
* Title: UserFeedbackCriteria.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月10日
 */
public class UserFeedbackCriteria extends PageModel {
	/**
	 * 手机号/会员账号
	 */
	private String mobile;
	
	/**
	 * 查询开始日期
	 */
	private String beginDate;
	
	/**
	 * 查询结束日期
	 */
	private String endDate;


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

	public String getMobile() {
		return StringUtils.isBlank(mobile) ? null : mobile.trim();
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
