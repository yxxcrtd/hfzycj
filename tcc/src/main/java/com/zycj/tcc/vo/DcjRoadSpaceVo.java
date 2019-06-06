package com.zycj.tcc.vo;


/**
 * 督查机 停车位VO
 * @author 洪捃能
 *
 */
public class DcjRoadSpaceVo {
//	private String spaceNo;//c-车位泊位编号
//	private Integer spaceStatus;//d-是否停车 1表示停车,0 未停车
//	private String carNo;//e-是停车 车牌号
//	private Integer statusCode;//f-车辆的欠费状态 1表示正常 2表示欠费 3表示拒缴费
	
	private String c;//车位泊位编号
	private Integer d;//是否停车 1表示停车,0 未停车
	private String e;//是停车 车牌号
	private Integer f;//车辆的欠费状态 1表示正常 2表示欠费 3表示拒缴费
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public Integer getD() {
		return d;
	}
	public void setD(Integer d) {
		this.d = d;
	}
	public String getE() {
		return e;
	}
	public void setE(String e) {
		this.e = e;
	}
	public Integer getF() {
		return f;
	}
	public void setF(Integer f) {
		this.f = f;
	}
	
	
}
