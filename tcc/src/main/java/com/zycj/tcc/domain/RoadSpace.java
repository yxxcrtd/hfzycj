package com.zycj.tcc.domain;

import java.util.Date;

public class RoadSpace {
    private Integer id;

    private Integer sectionId;

    private String spaceNo;

    private Integer spaceStatus;

    private Integer spaceType;

    private Date createTime;

    private Date updateTime;

    private Integer isParked;

    private String carNo;

    private Date parkTime;

    private Date driveTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSpaceNo() {
        return spaceNo;
    }

    public void setSpaceNo(String spaceNo) {
        this.spaceNo = spaceNo == null ? null : spaceNo.trim();
    }

    public Integer getSpaceStatus() {
        return spaceStatus;
    }

    public void setSpaceStatus(Integer spaceStatus) {
        this.spaceStatus = spaceStatus;
    }

    public Integer getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(Integer spaceType) {
        this.spaceType = spaceType;
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

    public Integer getIsParked() {
        return isParked;
    }

    public void setIsParked(Integer isParked) {
        this.isParked = isParked;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Date getParkTime() {
        return parkTime;
    }

    public void setParkTime(Date parkTime) {
        this.parkTime = parkTime;
    }

    public Date getDriveTime() {
        return driveTime;
    }

    public void setDriveTime(Date driveTime) {
        this.driveTime = driveTime;
    }
}