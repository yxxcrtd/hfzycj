package com.zycj.tcc.domain;

import java.util.Date;

public class InvoiceSetting {
    private Integer id;

    private String invoiceBatch;

    private String invoiceStart;

    private String invoiceEnd;

    private String empNo;

    private String empName;

    private String posNo;

    private Date setTime;

    private String reqSerial;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceBatch() {
        return invoiceBatch;
    }

    public void setInvoiceBatch(String invoiceBatch) {
        this.invoiceBatch = invoiceBatch == null ? null : invoiceBatch.trim();
    }

    public String getInvoiceStart() {
        return invoiceStart;
    }

    public void setInvoiceStart(String invoiceStart) {
        this.invoiceStart = invoiceStart == null ? null : invoiceStart.trim();
    }

    public String getInvoiceEnd() {
        return invoiceEnd;
    }

    public void setInvoiceEnd(String invoiceEnd) {
        this.invoiceEnd = invoiceEnd == null ? null : invoiceEnd.trim();
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo == null ? null : empNo.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getPosNo() {
        return posNo;
    }

    public void setPosNo(String posNo) {
        this.posNo = posNo == null ? null : posNo.trim();
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    public String getReqSerial() {
        return reqSerial;
    }

    public void setReqSerial(String reqSerial) {
        this.reqSerial = reqSerial == null ? null : reqSerial.trim();
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
}