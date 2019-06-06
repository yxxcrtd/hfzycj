package com.tcc.park.api.vo;

public class AreaListVo {
	
	 private String areaId ;//区域编号
	 private String areaName;// 名称
	 private String spaceTotal;// 停车位总个数
	 private String spaceCharge;// 收费个数
	 private String spaceFree;//免费个数
	 
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getSpaceTotal() {
		return spaceTotal;
	}
	public void setSpaceTotal(String spaceTotal) {
		this.spaceTotal = spaceTotal;
	}
	public String getSpaceCharge() {
		return spaceCharge;
	}
	public void setSpaceCharge(String spaceCharge) {
		this.spaceCharge = spaceCharge;
	}
	public String getSpaceFree() {
		return spaceFree;
	}
	public void setSpaceFree(String spaceFree) {
		this.spaceFree = spaceFree;
	}
	 
	 

}
