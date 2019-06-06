/**
 * 
 */
package com.zycj.tcc.exception;

/**
 * 没有访问权限
* Title: NoAuthorityException.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年3月25日
 */
public class NoAuthorityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9021901443543409019L;

	public NoAuthorityException() {
		super();
	}

	public NoAuthorityException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoAuthorityException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoAuthorityException(String message) {
		super(message);
	}

	public NoAuthorityException(Throwable cause) {
		super(cause);
	}

}
