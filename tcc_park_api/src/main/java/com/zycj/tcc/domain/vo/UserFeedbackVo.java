/**
 * 
 */
package com.zycj.tcc.domain.vo;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.domain.User;
import com.zycj.tcc.domain.UserFeedback;



/**
 * 会员相关
* Title: UserVo.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月10日
 */
public class UserFeedbackVo extends UserFeedback {
	/**
	 * 会员信息
	 */
	private User user;
	
	public String getCreateTimeStr(){
		if(null!=getCreateTime()){
			return new SimpleDateFormat(Constants.DEFAULT_DATE_FORMATE).format(getCreateTime());
		}
		return  null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getMobileVo(){
		if(null!=user){
			return user.getMobile();
		}
		return null;
	}
	public String getNameVo(){
		if(null!=user){
			return user.getName();
		}
		return null;
	}
	public String getMachineTypeVo(){
		if(StringUtils.isNotBlank(getMachineType())){
			if(Constants.ANDROID.equals(getMachineType())){
				return "Android";
			}
			else if(Constants.IOS.equals(getMachineType())){
				return "iOS";
			}else{
				return "其他";
			}
		}
		return null;
	}
}
