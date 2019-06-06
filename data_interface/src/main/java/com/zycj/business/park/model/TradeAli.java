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
@Table(name = "trade_ali")
@SuppressWarnings("serial")
public class TradeAli extends PayModel implements Serializable{
	private Integer id;
	private Integer bisType;
	private Integer bisId;
	private String bisData;
	private Integer bisStatus;
	private String tradeNo;
	private String subject;
	private String body;
	private BigDecimal totalFee;
	private Date finishTime;
	private Date createTime;
	private Date modifyTime;
	private Date notifyTime;
	private Date paymentTime;
	private String notifyType;
	private String notifyId;
	private String tradeStatus;
	private String buyerId;
	private String buyerEmail;
	
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

	@Column(name = "notify_id", insertable = true, updatable = true)
	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "finish_time", insertable = true, updatable = true)
	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	@Column(name = "notify_time", insertable = true, updatable = true)
	public Date getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	@Column(name = "payment_time", insertable = true, updatable = true)
	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Column(name = "notify_type", insertable = true, updatable = true)
	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	@Column(name = "trade_status", insertable = true, updatable = true)
	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	@Column(name = "buyer_id", insertable = true, updatable = true)
	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name = "buyer_email", insertable = true, updatable = true)
	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	
	
}
