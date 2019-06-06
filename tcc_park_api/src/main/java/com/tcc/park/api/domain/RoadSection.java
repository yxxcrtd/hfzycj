package com.tcc.park.api.domain;

import java.math.BigDecimal;
import java.util.Date;

public class RoadSection {
    private Integer id;

    private String areaCode;

    private String roadName;

    private String roadCode;

    private String sectionName;

    private String sectionNo;

    private Integer total;

    private Integer used;

    private Integer feeType;

    private Date feeStart;

    private Date feeEnd;

    private String spaceStart;

    private String spaceEnd;

    private String spaceOthers;

    private BigDecimal startLo;

    private BigDecimal startLa;

    private BigDecimal endLo;

    private BigDecimal endLa;

    private String pointOthers;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName == null ? null : roadName.trim();
    }

    public String getRoadCode() {
        return roadCode;
    }

    public void setRoadCode(String roadCode) {
        this.roadCode = roadCode == null ? null : roadCode.trim();
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public Date getFeeStart() {
        return feeStart;
    }

    public void setFeeStart(Date feeStart) {
        this.feeStart = feeStart;
    }

    public Date getFeeEnd() {
        return feeEnd;
    }

    public void setFeeEnd(Date feeEnd) {
        this.feeEnd = feeEnd;
    }

    public String getSpaceStart() {
        return spaceStart;
    }

    public void setSpaceStart(String spaceStart) {
        this.spaceStart = spaceStart == null ? null : spaceStart.trim();
    }

    public String getSpaceEnd() {
        return spaceEnd;
    }

    public void setSpaceEnd(String spaceEnd) {
        this.spaceEnd = spaceEnd == null ? null : spaceEnd.trim();
    }

    public String getSpaceOthers() {
        return spaceOthers;
    }

    public void setSpaceOthers(String spaceOthers) {
        this.spaceOthers = spaceOthers == null ? null : spaceOthers.trim();
    }

    public BigDecimal getStartLo() {
        return startLo;
    }

    public void setStartLo(BigDecimal startLo) {
        this.startLo = startLo;
    }

    public BigDecimal getStartLa() {
        return startLa;
    }

    public void setStartLa(BigDecimal startLa) {
        this.startLa = startLa;
    }

    public BigDecimal getEndLo() {
        return endLo;
    }

    public void setEndLo(BigDecimal endLo) {
        this.endLo = endLo;
    }

    public BigDecimal getEndLa() {
        return endLa;
    }

    public void setEndLa(BigDecimal endLa) {
        this.endLa = endLa;
    }

    public String getPointOthers() {
        return pointOthers;
    }

    public void setPointOthers(String pointOthers) {
        this.pointOthers = pointOthers == null ? null : pointOthers.trim();
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