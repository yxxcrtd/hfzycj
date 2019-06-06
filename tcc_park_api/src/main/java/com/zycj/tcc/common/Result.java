/**
 * 
 */
package com.zycj.tcc.common;

/**
 * 请求处理结果 Title: Result.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月26日
 */
public class Result {
	/**
	 * 处理结果code 1:成功
	 */
	private Integer code = Constants.SUCCESS_RESULT;
	/**
	 * 异常类型名
	 */
	private String exceptionClassName;
	/**
	 * 异常信息
	 */
	private String exceptionMsg;
	/**
	 * 信息
	 */
	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getExceptionClassName() {
		return exceptionClassName;
	}

	public void setExceptionClassName(String exceptionClassName) {
		this.exceptionClassName = exceptionClassName;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", exceptionClassName="
				+ exceptionClassName + ", exceptionMsg=" + exceptionMsg
				+ ", msg=" + msg + "]";
	}

}
