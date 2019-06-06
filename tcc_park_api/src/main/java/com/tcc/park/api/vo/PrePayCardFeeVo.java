package com.tcc.park.api.vo;

public class PrePayCardFeeVo {
	private String title;
	private String amount;
	private String date;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public PrePayCardFeeVo() {

	}
	public PrePayCardFeeVo(String title, String ammount, String date) {
		super();
		this.title = title;
		this.amount = ammount;
		this.date = date;
	}
	
}
