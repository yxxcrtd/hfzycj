package com.zycj.business.park.util;

public enum PayType {
	ALIPAY(8), WECHAT(9);
	private int index;

	private PayType(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
