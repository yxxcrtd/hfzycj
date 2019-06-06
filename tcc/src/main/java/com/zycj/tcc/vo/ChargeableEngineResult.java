package com.zycj.tcc.vo;

import java.math.BigDecimal;

/**
 * 计费引擎执行结果
 * @author 洪捃能
 *
 */
public class ChargeableEngineResult {

	private BigDecimal amount;
	private String code;
	
	public ChargeableEngineResult(){}
	public ChargeableEngineResult(String code) {
		this.code = code;
	}
	public ChargeableEngineResult(BigDecimal amount, String code) {
		this.amount = amount;
		this.code = code;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
