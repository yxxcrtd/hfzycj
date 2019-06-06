package com.zycj.tcc.vo;


/**
 * 签退查询vo
 * @author 洪捃能
 *
 */
public class LogoutQueryVo {
	private String time;//当前服务器时间
	private Integer isAccount;//是否扎帐
	private String cutoffTime;//收费截止日期
	
	public Integer getIsAccount() {
		return isAccount;
	}
	public void setIsAccount(Integer isAccount) {
		this.isAccount = isAccount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCutoffTime() {
		return cutoffTime;
	}
	public void setCutoffTime(String cutoffTime) {
		this.cutoffTime = cutoffTime;
	}
}
