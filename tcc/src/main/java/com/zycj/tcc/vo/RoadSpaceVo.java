package com.zycj.tcc.vo;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.zycj.tcc.server.util.ResponseUtil;

/**
 * 停车位VO
 * 
 * @author 洪捃能
 *
 */
public class RoadSpaceVo {
	// private String spaceNo;//a-车位泊位编号
	// private Integer spaceStatus;//b-是否停车 1表示停车,0 未停车
	// private String carNo;//c-是停车 车牌号
	// private Integer orderId;//d-订单编号
	// private Integer statusCode;//e-车辆的欠费状态 1表示正常 2表示欠费 3表示拒缴费

	private String a;// a-车位泊位编号
	private Integer b;// b-是否停车 1表示停车,0 未停车
	private String c;// c-是停车 车牌号
	private Integer d;// d-订单编号
	private Integer e;// e-车辆的欠费状态 1表示正常 2表示欠费 3表示拒缴费
	private String payTime; // 支付时间
	private String inTime; // 驶入时间
	private BigDecimal amount; // 缴费金额（多次缴费的总额）
	private String sectionName; // 路边停车场名称

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public Integer getB() {
		return b;
	}

	public void setB(Integer b) {
		this.b = b;
	}

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

	public Integer getE() {
		return e;
	}

	public void setE(Integer e) {
		this.e = e;
	}

	public static void main(String[] args) {
		RoadSpaceVo rs = new RoadSpaceVo();
		String json = JSON.toJSONString(rs, ResponseUtil.sfs);
		System.out.println(json);
	}

}
