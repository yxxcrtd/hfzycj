package com.zycj.business.park.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PayModel {
	private Integer bisId;
	private BigDecimal totalFee;
	private Date finishTime;

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public Integer getBisId() {
		return bisId;
	}

	public void setBisId(Integer bisId) {
		this.bisId = bisId;
	}
	
	
}
