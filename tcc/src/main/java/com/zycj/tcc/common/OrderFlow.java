package com.zycj.tcc.common;

public enum OrderFlow {
	WAIT_PAY("驶入待支付", 0), PAY_WAIT_CONFIRM("支付待确认", 1),WAIT_REPAY("确认被拒绝待续费", 2);

	private String name;
	private int index;

	private OrderFlow(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (OrderFlow c : OrderFlow.values()) {
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
