package com.zycj.tcc.vo;

/**
 * 签到VO
 * @author 洪捃能
 *
 */
public class SignInVo {
	
	public SignInVo(String token, String sectionName,String userName){
		this.token=token;
		this.sectionName=sectionName;
		this.userName=userName;
	}
	

	private String token;
	private String sectionName;
	private String userName;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
