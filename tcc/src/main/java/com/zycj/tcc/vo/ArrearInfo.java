package com.zycj.tcc.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 欠费信息类
 * @author 洪捃能
 *
 */
public class ArrearInfo {
	private Integer arrearTotal;//总共欠费多少笔
	private BigDecimal arrearAmountTotal;//总欠费金额
	private Integer arrearLevel;//欠费级别，拒缴>欠费>无欠费      优先显示：从高到底
	private List<ArrearGroupInfo> arrearGroupList;//各个欠费类型的欠费笔数及金额
	public Integer getArrearTotal() {
		return arrearTotal;
	}
	public void setArrearTotal(Integer arrearTotal) {
		this.arrearTotal = arrearTotal;
	}
	public BigDecimal getArrearAmountTotal() {
		return arrearAmountTotal;
	}
	public void setArrearAmountTotal(BigDecimal arrearAmountTotal) {
		this.arrearAmountTotal = arrearAmountTotal;
	}
	public Integer getArrearLevel() {
		return arrearLevel;
	}
	public void setArrearLevel(Integer arrearLevel) {
		this.arrearLevel = arrearLevel;
	}
	public List<ArrearGroupInfo> getArrearGroupList() {
		return arrearGroupList;
	}
	public void setArrearGroupList(List<ArrearGroupInfo> arrearGroupList) {
		this.arrearGroupList = arrearGroupList;
	}
	
}
