package com.zycj.tcc.domain;

import java.util.Date;

public class AppUpdate {
    private Integer id;

    private Integer versionCodeMin;

    private Integer versionCodeMax;

    private String versionName;

    private String updateDescription;

    private String apkUrl;

    private String apkSize;

    private Date createTime;

    private Date updateTime;

    private Integer isForce;

    private Integer status;

    private Integer appType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersionCodeMin() {
        return versionCodeMin;
    }

    public void setVersionCodeMin(Integer versionCodeMin) {
        this.versionCodeMin = versionCodeMin;
    }

    public Integer getVersionCodeMax() {
        return versionCodeMax;
    }

    public void setVersionCodeMax(Integer versionCodeMax) {
        this.versionCodeMax = versionCodeMax;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public String getUpdateDescription() {
        return updateDescription;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription == null ? null : updateDescription.trim();
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl == null ? null : apkUrl.trim();
    }

    public String getApkSize() {
        return apkSize;
    }

    public void setApkSize(String apkSize) {
        this.apkSize = apkSize == null ? null : apkSize.trim();
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

    public Integer getIsForce() {
        return isForce;
    }

    public void setIsForce(Integer isForce) {
        this.isForce = isForce;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}