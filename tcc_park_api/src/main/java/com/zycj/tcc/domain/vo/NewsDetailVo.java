/**
 * 
 */
package com.zycj.tcc.domain.vo;

import java.text.SimpleDateFormat;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.domain.NewsDetail;



/**
 * 资讯信息
* Title: NewsDetailVo.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月9日
 */
public class NewsDetailVo extends NewsDetail {
	private String editorValue;
	
	private String upTimeStr;

	public String getEditorValue() {
		return editorValue;
	}

	public void setEditorValue(String editorValue) {
		this.editorValue = editorValue;
	}

	public String getUpTimeStr() {
		if(null!=getUpTime()){
			return new SimpleDateFormat(Constants.DEFAULT_DATE_FORMATE).format(getUpTime());
		}
		return upTimeStr;
	}

	public void setUpTimeStr(String upTimeStr) {
		this.upTimeStr = upTimeStr;
	}
	
	
}
