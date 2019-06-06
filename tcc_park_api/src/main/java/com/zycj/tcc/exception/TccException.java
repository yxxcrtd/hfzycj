/**
 * 
 */
package com.zycj.tcc.exception;

/**
 * 业务相关异常
* Title: TccException.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年3月25日
 */
public class TccException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9021901443543409019L;

	public TccException() {
		super();
	}

	public TccException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TccException(String message, Throwable cause) {
		super(message, cause);
	}

	public TccException(String message) {
		super(message);
	}

	public TccException(Throwable cause) {
		super(cause);
	}

}
