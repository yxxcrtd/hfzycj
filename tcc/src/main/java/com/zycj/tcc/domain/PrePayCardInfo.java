package com.zycj.tcc.domain;

public class PrePayCardInfo {
	private boolean success;
	private String cardNo;
	private Integer amount;
	private boolean chargeAble = false;
	
	
	public boolean isChargeAble() {
		return chargeAble;
	}
	public void setChargeAble(boolean chargeAble) {
		this.chargeAble = chargeAble;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public PrePayCardInfo() {
		
	}
	public PrePayCardInfo(String cardNo, Integer amount) {
		super();
		this.cardNo = cardNo;
		this.amount = amount;
	}
	public PrePayCardInfo(boolean success, String cardNo, Integer amount) {
		super();
		this.success = success;
		this.cardNo = cardNo;
		this.amount = amount;
	}
	
	
}
