package com.zycj.tcc.vo;

/**
 * @author Think
 *
 */
public class PlaceOrderVo {
	private boolean success;
	private PlaceOrderDetail result;
	private String errorCode;
	private String errorMsg;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public PlaceOrderDetail getResult() {
		return result;
	}
	public void setResult(PlaceOrderDetail result) {
		this.result = result;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public PlaceOrderVo() {
		
	}
}
