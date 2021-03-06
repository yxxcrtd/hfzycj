package com.tcc.park.api.vo;

public class WeiXinUserInfo {
	private String nickName;
	private String headImage;
	private String openId;
	private String sex;
	private String province;
	private String city;
	private String country;
	private String carNo;
	
	
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public WeiXinUserInfo() {
	}
	public WeiXinUserInfo(String nickName, String headImage, String openId) {
		super();
		this.nickName = nickName;
		this.headImage = headImage;
		this.openId = openId;
	}
	
}
