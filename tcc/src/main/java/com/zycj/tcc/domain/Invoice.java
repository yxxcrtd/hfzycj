package com.zycj.tcc.domain;

import java.util.Date;

public class Invoice {
    private Integer id;

    private String invoiceName;

    private Integer invoiceRoll;

    private Integer number;

    private String invoiceBatch;

    private String invoiceStart;

    private String invoiceEnd;

    private Date getTime;

    private Integer type;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String receiver;

    private String giver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName == null ? null : invoiceName.trim();
    }

    public Integer getInvoiceRoll() {
        return invoiceRoll;
    }

    public void setInvoiceRoll(Integer invoiceRoll) {
        this.invoiceRoll = invoiceRoll;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getGiver() {
        return giver;
    }

    public void setGiver(String giver) {
        this.giver = giver == null ? null : giver.trim();
    }
}