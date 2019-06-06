package com.zycj.business.park.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "payment_record")
@SuppressWarnings("serial")
public class PayMentRecord implements Serializable {
	
	private Integer id;
	private Date payTime;
	private Integer payType;
	private BigDecimal realPay;
	private Integer orderId;
	
	private String serialNo;
	private String chargeEmp;
	private String empName;
	private String carNo;
	private String posNo;
    private String spaceNo;
    private String sectionNo;
    private String sectionName;
    private Integer feeType;
    private String invoiceBatch;
    private String invoiceNo;
    private Integer isCheck;
	

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "pay_time", insertable = true, updatable = true)
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "pay_type", insertable = true, updatable = true)
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@Column(name = "real_pay", insertable = true, updatable = true)
	public BigDecimal getRealPay() {
		return realPay;
	}

	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
	}

	@Column(name = "order_id", insertable = true, updatable = true)
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "serial_no", insertable = true, updatable = true)
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "charge_emp", insertable = true, updatable = true)
	public String getChargeEmp() {
		return chargeEmp;
	}

	public void setChargeEmp(String chargeEmp) {
		this.chargeEmp = chargeEmp;
	}

	@Column(name = "emp_name", insertable = true, updatable = true)
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Column(name = "car_no", insertable = true, updatable = true)
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "pos_no", insertable = true, updatable = true)
	public String getPosNo() {
		return posNo;
	}

	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}

	@Column(name = "space_no", insertable = true, updatable = true)
	public String getSpaceNo() {
		return spaceNo;
	}

	public void setSpaceNo(String spaceNo) {
		this.spaceNo = spaceNo;
	}

	@Column(name = "section_no", insertable = true, updatable = true)
	public String getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}

	@Column(name = "section_name", insertable = true, updatable = true)
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	@Column(name = "fee_type", insertable = true, updatable = true)
	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	@Column(name = "invoice_batch", insertable = true, updatable = true)
	public String getInvoiceBatch() {
		return invoiceBatch;
	}

	public void setInvoiceBatch(String invoiceBatch) {
		this.invoiceBatch = invoiceBatch;
	}

	@Column(name = "invoice_no", insertable = true, updatable = true)
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "is_check", insertable = true, updatable = true)
	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
}
