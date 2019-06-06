package com.zycj.tcc.exception;

public class ArrearForOrderException extends Exception {

	public ArrearForOrderException(Throwable cause) {
		super("增加欠费信息 异常",cause);
	}
}
