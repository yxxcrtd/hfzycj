package com.zycj.tcc.vo;

/**
 * 历史查询接口vo
 * @author 洪捃能
 */
public class QueryPaymentRecordVo {

	private String empNo;//员工编号
	private String date;//日期
	private String totalAccount="0.00";//收款总额
	private String cashAccount="0.00";//现金收费
	private String cardAccount="0.00";//刷卡收费
	private String normalAccount="0.00";//正常收费
	private String arrearageAccount="0.00";//欠费补缴
	private String ktcAccount="0.00";//快停车收费
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTotalAccount() {
		return totalAccount;
	}
	public void setTotalAccount(String totalAccount) {
		this.totalAccount = totalAccount;
	}
	public String getCashAccount() {
		return cashAccount;
	}
	public void setCashAccount(String cashAccount) {
		this.cashAccount = cashAccount;
	}
	public String getCardAccount() {
		return cardAccount;
	}
	public void setCardAccount(String cardAccount) {
		this.cardAccount = cardAccount;
	}
	public String getNormalAccount() {
		return normalAccount;
	}
	public void setNormalAccount(String normalAccount) {
		this.normalAccount = normalAccount;
	}
	public String getArrearageAccount() {
		return arrearageAccount;
	}
	public void setArrearageAccount(String arrearageAccount) {
		this.arrearageAccount = arrearageAccount;
	}
	public String getKtcAccount() {
		return ktcAccount;
	}
	public void setKtcAccount(String ktcAccount) {
		this.ktcAccount = ktcAccount;
	}

	
}
