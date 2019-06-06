package com.wl.ko.home.model;

import java.util.Date;

public class UserInfo {
	/**用户编号**/
	private String id;
	/**真实姓名**/
	private String user_name;
	/**电话号码**/
	private String phone;
	/**邮寄地址**/
	private String address;
	/**新车购置价**/
	private String newbuyprice;
	/**品牌**/
	private String carbrand;
	/**车系**/
	private String carline; 
	/**年款**/
	private String carmodel;
	/**新车购买时间**/
	private String buytime;
	/**保单日期**/
	private String baodanimg;
	/**行驶证**/
	private String xingshizhengimg;
	/**创建时间**/
	private Date createtime;
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNewbuyprice() {
		return newbuyprice;
	}
	public void setNewbuyprice(String newbuyprice) {
		this.newbuyprice = newbuyprice;
	}
	public String getCarbrand() {
		return carbrand;
	}
	public void setCarbrand(String carbrand) {
		this.carbrand = carbrand;
	}
	public String getCarline() {
		return carline;
	}
	public void setCarline(String carline) {
		this.carline = carline;
	}
	public String getCarmodel() {
		return carmodel;
	}
	public void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}
	public String getBuytime() {
		return buytime;
	}
	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}
	public String getBaodanimg() {
		return baodanimg;
	}
	public void setBaodanimg(String baodanimg) {
		this.baodanimg = baodanimg;
	}
	public String getXingshizhengimg() {
		return xingshizhengimg;
	}
	public void setXingshizhengimg(String xingshizhengimg) {
		this.xingshizhengimg = xingshizhengimg;
	}
}
