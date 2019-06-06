package com.wl.ko.home.model;

public class UserWeiXin {
	/**编号**/
	private Integer id;
	
	/**车牌号**/
	private String car_no;
	
	/***微信openid**/
	private String open_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCar_no() {
		return car_no;
	}

	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
}
