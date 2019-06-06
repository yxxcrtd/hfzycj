package com.zycj.business.park.util;

public enum BisStatus {
	UNPAY("未付款", 0), PAY("已付款", 1), CLOSE("关闭,人工处理成功", 2), COMPLETE_PAY("完成", 3), FAIL("需人工处理(第三方支付成功，城泊处理失败)", 4);

	private String name;
	private int index;

	private BisStatus(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (BisStatus c : BisStatus.values()) {
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
