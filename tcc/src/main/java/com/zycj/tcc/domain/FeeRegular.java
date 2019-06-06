package com.zycj.tcc.domain;

import java.util.Date;

public class FeeRegular {
    private Integer id;

    private String regName;

    private String regCode;

    private String regFormula;

    private String regExplain;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName == null ? null : regName.trim();
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    public String getRegFormula() {
        return regFormula;
    }

    public void setRegFormula(String regFormula) {
        this.regFormula = regFormula == null ? null : regFormula.trim();
    }

    public String getRegExplain() {
        return regExplain;
    }

    public void setRegExplain(String regExplain) {
        this.regExplain = regExplain == null ? null : regExplain.trim();
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