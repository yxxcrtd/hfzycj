package com.wl.ko.home.model;

public class PriceInfo {
	/**状态表示**/
	private String flag;
	/**第一年车险**/
	private String oneyear;
	
	/**三年盗抢险**/
	private String threeyear;
	
	/**小车嘀嗒3年**/
	private String didayear;

	public String getOneyear() {
		return oneyear;
	}

	public void setOneyear(String oneyear) {
		this.oneyear = oneyear;
	}

	public String getThreeyear() {
		return threeyear;
	}

	public void setThreeyear(String threeyear) {
		this.threeyear = threeyear;
	}

	public String getDidayear() {
		return didayear;
	}

	public void setDidayear(String didayear) {
		this.didayear = didayear;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
