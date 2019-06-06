package com.zycj.business.park.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "commercial_park_record")
@SuppressWarnings("serial")
public class CommercialParkRecord implements Serializable {
	private Integer id;
	private String parkId; // 停车场编号
	private String inCameraId; // 进口车牌识别相机编码
	private String outCameraId; // 出口车牌识别相机编码
	private String carNo; // 车牌号
	private Date inTime; // 驶入时间
	private Date outTime; // 驶出时间
	private Double cost; // 停车费,单位元
	private String payMethod; // 支付方式：1,现金  2 刷卡
	private Date payTime; // 支付时间
	private String inCollectorNo; // 收费员编号
	private String outCollectorNo; // 收费员编号
	private String isPrintInvoice; // 是否打印发票
	private String invoiceNo; // 发票编号
	private Integer isFinish = 0;
	private Integer dataStatus = 0; // 数据状态，0（驶入），1（驶出），2（完成）

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "is_finish", insertable = true, updatable = true)
	public Integer getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	@Column(name = "park_id", insertable = true, updatable = true)
	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	@Column(name = "in_camera_id", insertable = true, updatable = true)
	public String getInCameraId() {
		return inCameraId;
	}

	public void setInCameraId(String inCameraId) {
		this.inCameraId = inCameraId;
	}

	@Column(name = "out_camera_id", insertable = true, updatable = true)
	public String getOutCameraId() {
		return outCameraId;
	}

	public void setOutCameraId(String outCameraId) {
		this.outCameraId = outCameraId;
	}

	@Column(name = "in_collector_no", insertable = true, updatable = true)
	public String getInCollectorNo() {
		return inCollectorNo;
	}

	public void setInCollectorNo(String inCollectorNo) {
		this.inCollectorNo = inCollectorNo;
	}

	@Column(name = "out_collector_no", insertable = true, updatable = true)
	public String getOutCollectorNo() {
		return outCollectorNo;
	}

	public void setOutCollectorNo(String outCollectorNo) {
		this.outCollectorNo = outCollectorNo;
	}

	@Column(name = "car_no", insertable = true, updatable = true)
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "in_time", insertable = true, updatable = true)
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	@Column(name = "out_time", insertable = true, updatable = true)
	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Column(name = "pay_method", insertable = true, updatable = true)
	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	@Column(name = "pay_time", insertable = true, updatable = true)
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "is_print_invoice", insertable = true, updatable = true)
	public String getIsPrintInvoice() {
		return isPrintInvoice;
	}

	public void setIsPrintInvoice(String isPrintInvoice) {
		this.isPrintInvoice = isPrintInvoice;
	}

	@Column(name = "invoice_no", insertable = true, updatable = true)
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "data_status", insertable = true, updatable = true)
	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

}
