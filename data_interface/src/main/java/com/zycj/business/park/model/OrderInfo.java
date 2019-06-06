package com.zycj.business.park.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "order_info")
@SuppressWarnings("serial")
public class OrderInfo implements Serializable{
	private Integer id;
	private String serialNo;
	private String sectionName;
	private String sectionNo;
	private String spaceNo;
	private Integer carType;
	private String carNo;
	private Integer orderStatus;
	private Integer payType;
	private String inPos;
	private String inEmp;
	private Date inTime;
	private String outPos;
	private String outEmp;
	private Date outTime;
	private Integer isFree;
	private String chargeEmp;
	private Date chargeTime;
	private String chargePos;
	private String invoiceBatch;
	private String invoiceNo;
	private Date createTime;
	private Date updateTime;
	private String reqSerial;
	private Integer feeId;
	private Integer arrearStatus;
	private String prePayAmount;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "serial_no", insertable = true, updatable = true)
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "section_name", insertable = true, updatable = true)
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	@Column(name = "section_no", insertable = true, updatable = true)
	public String getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}

	@Column(name = "space_no", insertable = true, updatable = true)
	public String getSpaceNo() {
		return spaceNo;
	}

	public void setSpaceNo(String spaceNo) {
		this.spaceNo = spaceNo;
	}

	@Column(name = "car_type", insertable = true, updatable = true)
	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	@Column(name = "car_no", insertable = true, updatable = true)
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "order_status", insertable = true, updatable = true)
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name = "pay_type", insertable = true, updatable = true)
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@Column(name = "in_pos", insertable = true, updatable = true)
	public String getInPos() {
		return inPos;
	}

	public void setInPos(String inPos) {
		this.inPos = inPos;
	}

	@Column(name = "in_emp", insertable = true, updatable = true)
	public String getInEmp() {
		return inEmp;
	}

	public void setInEmp(String inEmp) {
		this.inEmp = inEmp;
	}

	@Column(name = "in_time", insertable = true, updatable = true)
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	@Column(name = "out_pos", insertable = true, updatable = true)
	public String getOutPos() {
		return outPos;
	}

	public void setOutPos(String outPos) {
		this.outPos = outPos;
	}

	@Column(name = "out_emp", insertable = true, updatable = true)
	public String getOutEmp() {
		return outEmp;
	}

	public void setOutEmp(String outEmp) {
		this.outEmp = outEmp;
	}

	@Column(name = "out_time", insertable = true, updatable = true)
	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	@Column(name = "is_free", insertable = true, updatable = true)
	public Integer getIsFree() {
		return isFree;
	}

	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}

	@Column(name = "charge_emp", insertable = true, updatable = true)
	public String getChargeEmp() {
		return chargeEmp;
	}

	public void setChargeEmp(String chargeEmp) {
		this.chargeEmp = chargeEmp;
	}

	@Column(name = "charge_time", insertable = true, updatable = true)
	public Date getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(Date chargeTime) {
		this.chargeTime = chargeTime;
	}

	@Column(name = "charge_pos", insertable = true, updatable = true)
	public String getChargePos() {
		return chargePos;
	}

	public void setChargePos(String chargePos) {
		this.chargePos = chargePos;
	}

	@Column(name = "invoice_batch", insertable = true, updatable = true)
	public String getInvoiceBatch() {
		return invoiceBatch;
	}

	public void setInvoiceBatch(String invoiceBatch) {
		this.invoiceBatch = invoiceBatch;
	}

	@Column(name = "invoice_no", insertable = true, updatable = true)
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "create_time", insertable = true, updatable = true)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", insertable = true, updatable = true)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "req_serial", insertable = true, updatable = true)
	public String getReqSerial() {
		return reqSerial;
	}

	public void setReqSerial(String reqSerial) {
		this.reqSerial = reqSerial;
	}

	@Column(name = "fee_id", insertable = true, updatable = true)
	public Integer getFeeId() {
		return feeId;
	}

	public void setFeeId(Integer feeId) {
		this.feeId = feeId;
	}

	@Column(name = "arrear_status", insertable = true, updatable = true)
	public Integer getArrearStatus() {
		return arrearStatus;
	}

	public void setArrearStatus(Integer arrearStatus) {
		this.arrearStatus = arrearStatus;
	}

	@Column(name = "prePayAmount", insertable = true, updatable = true)
	public String getPrePayAmount() {
		return prePayAmount;
	}

	public void setPrePayAmount(String prePayAmount) {
		this.prePayAmount = prePayAmount;
	}

}
