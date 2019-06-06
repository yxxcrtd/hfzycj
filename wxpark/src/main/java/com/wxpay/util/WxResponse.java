package com.wxpay.util;

public class WxResponse {
	private String appid;// = returnobj.get("appid");
	private String mach_id;// = returnobj.get("mch_id");
	private String prepay_id;// = returnobj.get("prepay_id");
	private String paySign;// = returnobj.get("sign");
	private String nonce_str;// = returnobj.get("nonce_str");
	private String signType;// = "MD5";
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMach_id() {
		return mach_id;
	}
	public void setMach_id(String mach_id) {
		this.mach_id = mach_id;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
}
