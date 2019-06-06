package com.zycj.tcc.exception;

public class PaymentForOrderException extends Exception {

	public PaymentForOrderException(Throwable cause) {
		super("增加缴费信息 异常",cause);
	}
}
