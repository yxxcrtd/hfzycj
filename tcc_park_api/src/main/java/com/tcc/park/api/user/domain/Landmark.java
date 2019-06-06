package com.tcc.park.api.user.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Landmark {
    private Integer id;

    private Integer landmarkTypeId;
    
    private String code;

    private String name;

    private String addr;

    private BigDecimal lo;

    private BigDecimal lat;

    private Integer status;

    private String comments;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLandmarkTypeId() {
        return landmarkTypeId;
    }

    public void setLandmarkTypeId(Integer landmarkTypeId) {
        this.landmarkTypeId = landmarkTypeId;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public BigDecimal getLo() {
        return lo;
    }

    public void setLo(BigDecimal lo) {
        this.lo = lo;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	@Override
	public String toString() {
		return "Landmark [id=" + id + ", landmarkTypeId=" + landmarkTypeId
				+ ", name=" + name + ", addr=" + addr + ", lo=" + lo + ", lat="
				+ lat + ", status=" + status + ", comments=" + comments
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ "]";
	}
    
}