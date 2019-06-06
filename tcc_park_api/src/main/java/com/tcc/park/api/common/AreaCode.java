package com.tcc.park.api.common;

public enum AreaCode {

	LUYANG("庐阳区", 1), BAOHE("包河区", 2), SHUSHAN("蜀山区", 3), YAOHAI("瑶海区", 4);

	private String name;
	private int index;

	private AreaCode(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (AreaCode c : AreaCode.values()) {
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
