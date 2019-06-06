package com.zycj.tcc.vo;


public class RoadSpaceHPSetVo {
	
	private String spaceNo;//泊位号
	private int spaceChooseStatus; //车位选择状态（0，1）  1表示已选择，0 未选择
	
	public RoadSpaceHPSetVo(){}
	public RoadSpaceHPSetVo(String spaceNo,int spaceChooseStatus){
		this.spaceNo=spaceNo;
		this.spaceChooseStatus=spaceChooseStatus;
	}
	public String getSpaceNo() {
		return spaceNo;
	}
	public void setSpaceNo(String spaceNo) {
		this.spaceNo = spaceNo;
	}
	public int getSpaceChooseStatus() {
		return spaceChooseStatus;
	}
	public void setSpaceChooseStatus(int spaceChooseStatus) {
		this.spaceChooseStatus = spaceChooseStatus;
	}
	
	
}
