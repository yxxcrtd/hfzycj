package com.zycj.tcc.exception;

/**
 * 添加停车订单异常类
 * @author 洪捃能
 *
 */
public class OrderAddException extends Exception {
	public OrderAddException(Throwable cause) {
		super("增加停车订单信息 异常",cause);
	}
}
