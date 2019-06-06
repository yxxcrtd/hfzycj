package com.zycj.tcc.common;

/**
 * 欠费状态
 * @author 洪捃能
 */
public enum ArrearStatus {

	UNPAID("未缴费", 0), PAID("已缴费", 1), DELETED("已删除", 2);

	private String name;
	private int index;

	private ArrearStatus(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (ArrearStatus c : ArrearStatus.values()) {
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
