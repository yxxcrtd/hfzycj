package com.zycj.tcc.domain;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfo {
    private Integer id;

    private String serialNo;

    private String sectionName;

    private String sectionNo;

    private String spaceNo;

    private Integer carType;

    private String carNo;

    private Integer orderStatus;
    
    private Integer orderFlow;

    private Integer payType;

    private String inPos;

    private String inEmp;

    private Date inTime;
    
    private String inTimestr;

    private String outPos;

    private String outEmp;

    private Date outTime;

    private Integer isFree;

    private String chargeEmp;

    private Date chargeTime;

    private String chargePos;

    private BigDecimal feeTotal;

    private BigDecimal feeReal;

    private String invoiceBatch;

    private String invoiceNo;

    private Date createTime;

    private Date updateTime;

    private String reqSerial;

    private Integer feeId;

    private Integer arrearStatus;

    public Integer getOrderFlow() {
		return orderFlow;
	}

	public void setOrderFlow(Integer orderFlow) {
		this.orderFlow = orderFlow;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName == null ? null : sectionName.trim();
    }

    public String getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(String sectionNo) {
        this.sectionNo = sectionNo == null ? null : sectionNo.trim();
    }

    public String getSpaceNo() {
        return spaceNo;
    }

    public void setSpaceNo(String spaceNo) {
        this.spaceNo = spaceNo == null ? null : spaceNo.trim();
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getInPos() {
        return inPos;
    }

    public void setInPos(String inPos) {
        this.inPos = inPos == null ? null : inPos.trim();
    }

    public String getInEmp() {
        return inEmp;
    }

    public void setInEmp(String inEmp) {
        this.inEmp = inEmp == null ? null : inEmp.trim();
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getOutPos() {
        return outPos;
    }

    public void setOutPos(String outPos) {
        this.outPos = outPos == null ? null : outPos.trim();
    }

    public String getOutEmp() {
        return outEmp;
    }

    public void setOutEmp(String outEmp) {
        this.outEmp = outEmp == null ? null : outEmp.trim();
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public String getChargeEmp() {
        return chargeEmp;
    }

    public void setChargeEmp(String chargeEmp) {
        this.chargeEmp = chargeEmp == null ? null : chargeEmp.trim();
    }

    public Date getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Date chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getChargePos() {
        return chargePos;
    }

    public void setChargePos(String chargePos) {
        this.chargePos = chargePos == null ? null : chargePos.trim();
    }

    public BigDecimal getFeeTotal() {
        return feeTotal;
    }

    public void setFeeTotal(BigDecimal feeTotal) {
        this.feeTotal = feeTotal;
    }

    public BigDecimal getFeeReal() {
        return feeReal;
    }

    public void setFeeReal(BigDecimal feeReal) {
        this.feeReal = feeReal;
    }

    public String getInvoiceBatch() {
        return invoiceBatch;
    }

    public void setInvoiceBatch(String invoiceBatch) {
        this.invoiceBatch = invoiceBatch == null ? null : invoiceBatch.trim();
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getReqSerial() {
        return reqSerial;
    }

    public void setReqSerial(String reqSerial) {
        this.reqSerial = reqSerial == null ? null : reqSerial.trim();
    }

    public Integer getFeeId() {
        return feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    public Integer getArrearStatus() {
        return arrearStatus;
    }

    public void setArrearStatus(Integer arrearStatus) {
        this.arrearStatus = arrearStatus;
    }

	public String getInTimestr() {
		return inTimestr;
	}

	public void setInTimestr(String inTimestr) {
		this.inTimestr = inTimestr;
	}
    
}