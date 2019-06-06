package com.zycj.tcc.vo;

import java.math.BigDecimal;

/**
 * 分类的缴费金额，用于按日期查询缴费情况
 * @author 洪捃能
 */
public class PaymentAmountGroupInfo {
	private Integer type; //支付方式或缴费类型
	private BigDecimal amount;//金额
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
