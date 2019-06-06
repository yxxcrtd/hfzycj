package com.zycj.tcc.common;

/**
 * 月票类型
 * @author 洪捃能
 */
public enum TicketType {
	YEAR("年票", 1), HALFYEAR("半年票", 2), MONTH("月票", 3);

	private String name;
	private int index;

	private TicketType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (TicketType c : TicketType.values()) {
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
