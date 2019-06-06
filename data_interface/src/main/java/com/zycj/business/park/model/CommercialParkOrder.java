package com.zycj.business.park.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "commercial_park_order")
@SuppressWarnings("serial")
public class CommercialParkOrder implements Serializable {
	private Integer id;
	private String parkingId;// 停车场Id
	private String cameraId;// 车牌识别相机编号
	private String carNo;// 车牌号
	private String type;// 驶入还是驶出 0：驶入，1，驶出
	private String driveTime;// 驶入驶出时间
	private String inTime;// 驶入驶出时间
	private String amount;// 停车费
	private String chargeType;// 缴费方式
	private String chargeTime;// 缴费时间
	private String chargeEmp;// 收费员编号
	private String isPrintInvoice;// 是否打印发票
	private String invoiceNo;// 发票编号
	private String attach;
	private String recordId; 

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "parking_id", insertable = true, updatable = true)
	public String getParkingId() {
		return parkingId;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}

	@Column(name = "camera_id", insertable = true, updatable = true)
	public String getCameraId() {
		return cameraId;
	}

	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}

	@Column(name = "car_no", insertable = true, updatable = true)
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "drive_time", insertable = true, updatable = true)
	public String getDriveTime() {
		return driveTime;
	}

	public void setDriveTime(String driveTime) {
		this.driveTime = driveTime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name = "charge_type", insertable = true, updatable = true)
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	@Column(name = "charge_time", insertable = true, updatable = true)
	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	@Column(name = "charge_emp", insertable = true, updatable = true)
	public String getChargeEmp() {
		return chargeEmp;
	}

	public void setChargeEmp(String chargeEmp) {
		this.chargeEmp = chargeEmp;
	}

	@Column(name = "is_printInvoice", insertable = true, updatable = true)
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

	@Transient
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Column(name = "in_time", insertable = true, updatable = true)
	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	@Column(name = "record_id", insertable = true, updatable = true)
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
}
