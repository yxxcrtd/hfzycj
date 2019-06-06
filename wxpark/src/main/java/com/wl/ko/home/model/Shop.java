package com.wl.ko.home.model;

public class Shop {
	/***全名**/
	private String s_fullname;
	/**省份编号**/
	private int s_provice;
	/***城市**/
	private int s_city;
	/**地址**/
	private String s_detailaddress;
	/***编号**/
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getS_fullname() {
		return s_fullname;
	}
	public void setS_fullname(String s_fullname) {
		this.s_fullname = s_fullname;
	}
	public int getS_provice() {
		return s_provice;
	}
	public void setS_provice(int s_provice) {
		this.s_provice = s_provice;
	}
	public int getS_city() {
		return s_city;
	}
	public void setS_city(int s_city) {
		this.s_city = s_city;
	}
	public String getS_detailaddress() {
		return s_detailaddress;
	}
	public void setS_detailaddress(String s_detailaddress) {
		this.s_detailaddress = s_detailaddress;
	}
}
