package com.zycj.tcc.common;

/**
 * 缴费类型
 * @author 洪捃能
 *
 */
public enum PaymentFeeType {

	OUT_PAYMENT("驶出缴费",1),
	MAKE_PAYMENT("欠费补缴费",2),
	ACCOUNT_PAYMENT("扎帐缴费",3);
	
	private String name;
	private int index;

	private PaymentFeeType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (PaymentFeeType c : PaymentFeeType.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
