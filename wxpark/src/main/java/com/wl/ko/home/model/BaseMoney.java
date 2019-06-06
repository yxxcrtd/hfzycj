package com.wl.ko.home.model;

import java.util.Date;

public class BaseMoney {
	
	//open编号
	private String openid;
	
	//活动编号
	private String activtiy_id;
	
	

	//费用
	private String money;
	
	//创建时间
	private Date createtime;

	
	public String getActivtiy_id() {
		return activtiy_id;
	}

	public void setActivtiy_id(String activtiy_id) {
		this.activtiy_id = activtiy_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
