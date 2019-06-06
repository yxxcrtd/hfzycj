package com.zycj.tcc.exception;

/**
 * 补缴欠费重复补缴异常
 * @author 洪捃能
 *
 */
public class ArrearRepeatPayException extends Exception {
	public ArrearRepeatPayException(String msg){
		super(msg);
	}
}
