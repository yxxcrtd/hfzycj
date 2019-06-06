package com.zycj.tcc.exception;

public class OrderCompleteException extends Exception {

	public OrderCompleteException(Integer oid,Throwable cause) {
		super("订单id:"+oid+" 完成停车订单驶出  异常",cause);
	}
}
