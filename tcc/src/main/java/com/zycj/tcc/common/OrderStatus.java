package com.zycj.tcc.common;

/**
 * 订单状态
 * @author 洪捃能
 */
public enum OrderStatus {
	
	OUT("驶出", 0), IN("驶入", 1);

	private String name;
	private int index;

	private OrderStatus(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (OrderStatus c : OrderStatus.values()) {
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
