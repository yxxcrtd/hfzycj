package com.zycj.tcc.server.vo;

public class PrePayCardVo {
	private boolean success;
	private String message;
	private String realAmount;
	// 停车卡余额
	private double balance = 0;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(String realAmount) {
		this.realAmount = realAmount;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PrePayCardVo() {
	}

	public PrePayCardVo(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public PrePayCardVo(boolean success, String message, String amount) {
		super();
		this.success = success;
		this.message = message;
		this.realAmount = amount;
	}

	public PrePayCardVo(boolean success, String message, String realAmount, double balance) {
		super();
		this.success = success;
		this.message = message;
		this.realAmount = realAmount;
		this.balance = balance;
	}
	
	
}
