package com.zycj.business.park.dto;

import java.util.List;

import com.zycj.business.park.model.TradeWeix;

public class WeixPayVo extends TradeWeix {
	private int deviceType;// 1：Android、2：IOS
	private String prePayData;// 支付参数
	private List<TradeAttr> attrList;

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public List<TradeAttr> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<TradeAttr> attrList) {
		this.attrList = attrList;
	}

	public String getPrePayData() {
		return prePayData;
	}

	public void setPrePayData(String prePayData) {
		this.prePayData = prePayData;
	}
}
