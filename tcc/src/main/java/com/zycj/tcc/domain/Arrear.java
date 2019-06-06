package com.zycj.tcc.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Arrear {
    private Integer id;

    private String serialNo;

    private Integer orderId;

    private String carNo;

    private Integer carType;

    private String sectionNo;
    
    private String sectionName;

    private String spaceNo;

    private String inEmp;

    private Date inTime;

    private Date outTime;

    private BigDecimal arrearAmount;

    private Integer arrearType;

    private Integer status;

    private String chargeEmp;

    private String posNo;

    private Date payTime;

    private Integer payType;

    private BigDecimal payAmount;

    private Date createTime;

    private Date updateTime;

    private String reqSerial;

    private BigDecimal oldAmount;

    private String modifyRemark;


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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public String getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(String sectionNo) {
        this.sectionNo = sectionNo == null ? null : sectionNo.trim();
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName == null ? null : sectionName.trim();
    }

    public String getSpaceNo() {
        return spaceNo;
    }

    public void setSpaceNo(String spaceNo) {
        this.spaceNo = spaceNo == null ? null : spaceNo.trim();
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

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public BigDecimal getArrearAmount() {
        return arrearAmount;
    }

    public void setArrearAmount(BigDecimal arrearAmount) {
        this.arrearAmount = arrearAmount;
    }

    public Integer getArrearType() {
        return arrearType;
    }

    public void setArrearType(Integer arrearType) {
        this.arrearType = arrearType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getChargeEmp() {
        return chargeEmp;
    }

    public void setChargeEmp(String chargeEmp) {
        this.chargeEmp = chargeEmp == null ? null : chargeEmp.trim();
    }

    public String getPosNo() {
        return posNo;
    }

    public void setPosNo(String posNo) {
        this.posNo = posNo == null ? null : posNo.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
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

    public BigDecimal getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(BigDecimal oldAmount) {
        this.oldAmount = oldAmount;
    }

    public String getModifyRemark() {
        return modifyRemark;
    }

    public void setModifyRemark(String modifyRemark) {
        this.modifyRemark = modifyRemark == null ? null : modifyRemark.trim();
    }
}