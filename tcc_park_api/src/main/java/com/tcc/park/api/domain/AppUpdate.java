package com.tcc.park.api.domain;

public class AppUpdate {
    private Integer appUpdateId;

    private Integer versionCodeMin;

    private Integer versionCodeMax;

    private String versionName;

    private String updateDescription;

    private String apkUrl;

    private String apkSize;

    private Integer clientType;

    public Integer getAppUpdateId() {
        return appUpdateId;
    }

    public void setAppUpdateId(Integer appUpdateId) {
        this.appUpdateId = appUpdateId;
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

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }
}