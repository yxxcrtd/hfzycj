package com.zycj.tcc.common;

/**
 * 标示此车牌号有无欠费，有无据缴费
 * @author 洪捃能
 *
 */
public enum OrderArrearStatus {

	NO_ARREAR("无欠费",1),
	ARREARAGE("欠费",2),
	REFUSED_TO_PAY("拒缴费",3);
	
	private String name;
	private int index;

	private OrderArrearStatus(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (OrderArrearStatus c : OrderArrearStatus.values()) {
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
