package com.zycj.business.park.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zycj.business.park.dto.PayModel;
@Entity
@Table(name = "trade_weix")
@SuppressWarnings("serial")
public class TradeWeix extends PayModel implements Serializable{
	private Integer id;
	private Integer bisType;
	private Integer bisId;
	private String bisData;
	private Integer bisStatus;
	private String tradeNo;
	private String spbillCreateIp;
	private String body;
	private BigDecimal totalFee;
	private Date createTime;
	private Date modifyTime;
	private Integer tradeState;
	private String bankBillNo;
	private String notifyId;
	private String transactionId;
	private String attach;
	private Date finishTime;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "bis_type", insertable = true, updatable = true)
	public Integer getBisType() {
		return bisType;
	}

	public void setBisType(Integer bisType) {
		this.bisType = bisType;
	}

	@Column(name = "bis_id", insertable = true, updatable = true)
	public Integer getBisId() {
		return bisId;
	}

	public void setBisId(Integer bisId) {
		this.bisId = bisId;
	}

	@Column(name = "bis_data", insertable = true, updatable = true)
	public String getBisData() {
		return bisData;
	}

	public void setBisData(String bisData) {
		this.bisData = bisData;
	}

	@Column(name = "bis_status", insertable = true, updatable = true)
	public Integer getBisStatus() {
		return bisStatus;
	}

	public void setBisStatus(Integer bisStatus) {
		this.bisStatus = bisStatus;
	}

	@Column(name = "trade_no", insertable = true, updatable = true)
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@Column(name = "spbill_create_ip", insertable = true, updatable = true)
	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	@Column(name = "body", insertable = true, updatable = true)
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "total_fee", insertable = true, updatable = true)
	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	@Column(name = "create_time", insertable = true, updatable = true)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "modify_time", insertable = true, updatable = true)
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "trade_state", insertable = true, updatable = true)
	public Integer getTradeState() {
		return tradeState;
	}

	public void setTradeState(Integer tradeState) {
		this.tradeState = tradeState;
	}

	@Column(name = "bank_billno", insertable = true, updatable = true)
	public String getBankBillNo() {
		return bankBillNo;
	}

	public void setBankBillNo(String bankBillNo) {
		this.bankBillNo = bankBillNo;
	}

	@Column(name = "notify_id", insertable = true, updatable = true)
	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	@Column(name = "transaction_id", insertable = true, updatable = true)
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name = "attach", insertable = true, updatable = true)
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Column(name = "time_end", insertable = true, updatable = true)
	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

}
