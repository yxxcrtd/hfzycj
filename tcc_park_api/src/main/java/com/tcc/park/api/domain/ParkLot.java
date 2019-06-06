package com.tcc.park.api.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ParkLot {
    private Integer parkId;

    private Integer companyId;

    private String parkName;

    private Integer parkTotal;

    private Integer parkNow;

    private BigDecimal longitudeStart;

    private BigDecimal latitudeStart;

    private BigDecimal longitudeEnd;

    private BigDecimal latitudeEnd;

    private String pointsOther;

    private Integer parkType;

    private String parkLocation;

    private String contactInfo;

    private String businessTime;

    private Integer feeType;

    private String chargeExplain;

    private Date updateTime;

    private Date createTime;

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName == null ? null : parkName.trim();
    }

    public Integer getParkTotal() {
        return parkTotal;
    }

    public void setParkTotal(Integer parkTotal) {
        this.parkTotal = parkTotal;
    }

    public Integer getParkNow() {
        return parkNow;
    }

    public void setParkNow(Integer parkNow) {
        this.parkNow = parkNow;
    }

    public BigDecimal getLongitudeStart() {
        return longitudeStart;
    }

    public void setLongitudeStart(BigDecimal longitudeStart) {
        this.longitudeStart = longitudeStart;
    }

    public BigDecimal getLatitudeStart() {
        return latitudeStart;
    }

    public void setLatitudeStart(BigDecimal latitudeStart) {
        this.latitudeStart = latitudeStart;
    }

    public BigDecimal getLongitudeEnd() {
        return longitudeEnd;
    }

    public void setLongitudeEnd(BigDecimal longitudeEnd) {
        this.longitudeEnd = longitudeEnd;
    }

    public BigDecimal getLatitudeEnd() {
        return latitudeEnd;
    }

    public void setLatitudeEnd(BigDecimal latitudeEnd) {
        this.latitudeEnd = latitudeEnd;
    }

    public String getPointsOther() {
        return pointsOther;
    }

    public void setPointsOther(String pointsOther) {
        this.pointsOther = pointsOther == null ? null : pointsOther.trim();
    }

    public Integer getParkType() {
        return parkType;
    }

    public void setParkType(Integer parkType) {
        this.parkType = parkType;
    }

    public String getParkLocation() {
        return parkLocation;
    }

    public void setParkLocation(String parkLocation) {
        this.parkLocation = parkLocation == null ? null : parkLocation.trim();
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime == null ? null : businessTime.trim();
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public String getChargeExplain() {
        return chargeExplain;
    }

    public void setChargeExplain(String chargeExplain) {
        this.chargeExplain = chargeExplain == null ? null : chargeExplain.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}