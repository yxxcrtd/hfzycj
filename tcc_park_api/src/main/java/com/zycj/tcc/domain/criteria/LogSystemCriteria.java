/**
 * 
 */
package com.zycj.tcc.domain.criteria;


import org.apache.commons.lang.StringUtils;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.PageModel;

/**
 *日志相关
* Title: LogSystemCriteria.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月9日
 */
public class LogSystemCriteria extends PageModel {
	/**
	 * 操作类型
	 */
	private Integer operateType;
	
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

	public Integer getOperateType() {
		return null==operateType?null:(operateType == Constants.UN_SELECT ? null : operateType);
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
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
