/**
 * 
 */
package com.zycj.tcc.domain.vo;


import java.text.SimpleDateFormat;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.domain.LogSystem;



/**
 * 操作日志Vo
* Title: LogSystemVo.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月13日
 */
public class LogSystemVo extends LogSystem {
	public String getCreateTimeStr(){
		if(null!=getCreateTime()){
			return new SimpleDateFormat(Constants.DEFAULT_DATE_FORMATE).format(getCreateTime());
		}
		return  null;
	}
	public String getOperateTypeStr(){
		if(null!=getOperateType()){
			if(Constants.ADD==getOperateType()){
				return "新增";
			}
			if(Constants.EDIT==getOperateType()){
				return "修改";
			}
			if(Constants.DELETE==getOperateType()){
				return "删除";
			}
			if(Constants.UPDATE_STATUS==getOperateType()){
				return "更新状态";
			}
			if(Constants.RESET_PWD==getOperateType()){
				return "重置密码";
			}
		}
		return null;
	}
}
