package com.wl.ko.home.model;

public class CityInfo {
	/**城市编号**/
	private int city_id;
	/***父类编号**/
	private int city_parentId;
	/**城市名称**/
	private String city_name;
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public int getCity_parentId() {
		return city_parentId;
	}
	public void setCity_parentId(int city_parentId) {
		this.city_parentId = city_parentId;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	
	
	
}
