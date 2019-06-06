package com.zycj.tcc.common;

/**
 * 订单支付方式
 * @author 洪捃能
 */
public enum OrderPayType {
	
	NO_PAY("未支付",0),
	CASH_OUT("现金驶出",1),//缴费记录-支付方式
	CARD_OUT("刷卡驶出",2),//缴费记录-支付方式
	FREE_OUT("免费驶出",3),
	REFUSED_TO_PAY_OUT("拒缴费驶出",4),//欠费记录-欠费类型
	ARREARAGE_OUT("欠费驶出",5),//欠费记录-欠费类型
	END_ARREARAGE_OUT("日终欠费驶出",6),//欠费记录-欠费类型
	KTC_OUT("快停车驶出",7),//缴费记录-支付方式
	ZFB_OUT("支付宝驶出",8),//支付宝驶出-8
	WX_OUT("微信驶出",9),//支付宝驶出-8
	YFK_OUT("预付费卡驶出",10),//预付卡驶出-10
	YZF_OUT("翼支付驶出",11);//翼支付驶出-11
	
	private String name;
	private int index;

	private OrderPayType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (OrderPayType c : OrderPayType.values()) {
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
