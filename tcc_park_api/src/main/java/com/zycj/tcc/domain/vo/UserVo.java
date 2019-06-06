/**
 * 
 */
package com.zycj.tcc.domain.vo;
import java.text.SimpleDateFormat;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.domain.User;



/**
 * 会员相关
* Title: UserVo.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月10日
 */
public class UserVo extends User {
	public String getCreateTimeStr(){
		if(null!=getCreateTime()){
			return new SimpleDateFormat(Constants.DEFAULT_DATE_FORMATE).format(getCreateTime());
		}
		return  null;
	}
	public String getStatusVo(){
		if(null!=getStatus()){
			if(Constants.IS_CLOSED==getStatus()){
				return "禁用";
			}
			if(Constants.IS_NORMAL==getStatus()){
				return "启用";
			}
		}
		return null;
	}
}
