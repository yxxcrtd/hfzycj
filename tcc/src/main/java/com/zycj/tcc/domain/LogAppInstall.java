package com.zycj.tcc.domain;

import java.util.Date;

public class LogAppInstall {
    private Integer id;

    private String posNo;

    private Integer currentCode;

    private Integer formerCode;

    private Date installTime;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosNo() {
        return posNo;
    }

    public void setPosNo(String posNo) {
        this.posNo = posNo == null ? null : posNo.trim();
    }

    public Integer getCurrentCode() {
        return currentCode;
    }

    public void setCurrentCode(Integer currentCode) {
        this.currentCode = currentCode;
    }

    public Integer getFormerCode() {
        return formerCode;
    }

    public void setFormerCode(Integer formerCode) {
        this.formerCode = formerCode;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}