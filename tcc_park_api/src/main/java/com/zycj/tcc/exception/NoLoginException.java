/**
 * 
 */
package com.zycj.tcc.exception;

/**
 * 未登录或者session超时
* Title: NoLoginException.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年3月25日
 */
public class NoLoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9021901443543409019L;

	public NoLoginException() {
		super();
	}

	public NoLoginException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoLoginException(String message) {
		super(message);
	}

	public NoLoginException(Throwable cause) {
		super(cause);
	}

}
