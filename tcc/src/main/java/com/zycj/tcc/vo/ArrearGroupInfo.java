package com.zycj.tcc.vo;

import java.math.BigDecimal;

/**
 * 以欠费类型分组的方式类
 * @author 洪捃能
 *
 */
public class ArrearGroupInfo {
	private Integer arrearType;//以“欠费类型”分组
	private Integer count;//分组个数
	private BigDecimal amount;//分组总金额
	public Integer getArrearType() {
		return arrearType;
	}
	public void setArrearType(Integer arrearType) {
		this.arrearType = arrearType;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
