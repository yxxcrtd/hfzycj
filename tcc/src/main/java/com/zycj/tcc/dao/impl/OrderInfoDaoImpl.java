package com.zycj.tcc.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.OrderPayType;
import com.zycj.tcc.common.OrderStatus;
import com.zycj.tcc.dao.BaseDao;
import com.zycj.tcc.dao.OrderInfoDao;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.mybatis.mapper.OrderInfoMapper;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.RandomUtil;

@Repository
public class OrderInfoDaoImpl extends BaseDao implements OrderInfoDao {

	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	@Override
	public OrderInfo addOrderInfo(String sectionName,String spaceNo,String carNo,Integer carType,
			String empNo,String posNo,Date parkTime,Integer feeType,Integer arrearStatus,String reqseriesNo,Integer sectionId) {
		OrderInfo oi=new OrderInfo();
		oi.setSerialNo(RandomUtil.getSrialNoByDate());
		oi.setSectionName(sectionName);
		oi.setSpaceNo(spaceNo);
		oi.setCarNo(carNo);
		oi.setCarType(carType);
		oi.setOrderStatus(OrderStatus.IN.getIndex());
		oi.setPayType(OrderPayType.NO_PAY.getIndex());
		oi.setInEmp(empNo);
		oi.setInPos(posNo);
		oi.setInTime(parkTime);
		oi.setInTimestr(DateUtil.format_YMDHMSx(parkTime));
//		oi.setUpdateTime(parkTime);
		oi.setFeeId(feeType);
		oi.setArrearStatus(arrearStatus);//欠费状态
		oi.setReqSerial(reqseriesNo);
		oi.setSectionNo(String.valueOf(sectionId));
		orderInfoMapper.insertSelective(oi);
		return oi;
	}
	
	@Override
	public int updateOrderForCarExit(Integer orderId, String outEmpNo, String outPosNo, Date outTime,Integer status) {
		OrderInfo oi=new OrderInfo();
		oi.setId(orderId);
		if(status.equals(1)){
			oi.setOrderStatus(OrderStatus.OUT.getIndex());
		} else {
			oi.setOrderFlow(2);
		}
		oi.setOutEmp(outEmpNo);
		oi.setOutPos(outPosNo);
		oi.setOutTime(outTime);
		oi.setUpdateTime(new Date());
		return orderInfoMapper.updateByPrimaryKeySelective(oi);
	}
	
	@Override
	public int updateOrderForCarExit(Integer orderId,Integer payType,String outEmpNo,String outPosNo,Date outTime,
			String chargeEmp,String chargePos,Date chargeTime,BigDecimal amount,BigDecimal reallAmount,String invoice,String invoiceId) {
		OrderInfo oi=new OrderInfo();
		oi.setId(orderId);
		oi.setOrderStatus(OrderStatus.OUT.getIndex());
		oi.setPayType(payType);
		oi.setIsFree(OrderPayType.FREE_OUT.getIndex()==payType.intValue()?Constants.ORDER_ISFREE_TRUE:Constants.ORDER_ISFREE_FALSE);
		oi.setOutEmp(outEmpNo);
		oi.setOutPos(outPosNo);
		oi.setOutTime(outTime);
		oi.setChargeEmp(chargeEmp);
		oi.setChargePos(chargePos);
		oi.setChargeTime(chargeTime);
		oi.setFeeTotal(amount);
		oi.setFeeReal(reallAmount);
		oi.setInvoiceNo(invoice);
		oi.setInvoiceBatch(invoiceId);
		oi.setUpdateTime(new Date());
		return orderInfoMapper.updateByPrimaryKeySelective(oi);
	}
	
	@Override
	public int updateOrderForPay(Integer orderId, Integer orderFlow) {
		OrderInfo oi=new OrderInfo();
		oi.setId(orderId);
		oi.setOrderFlow(orderFlow);
		return orderInfoMapper.updateOrderFlow(oi);
	}
	
	public int updateOrderForSystemClear(Integer orderId,Date outTime,BigDecimal amount){
		OrderInfo upOi=new OrderInfo();
		upOi.setId(orderId);
		upOi.setPayType(OrderPayType.END_ARREARAGE_OUT.getIndex());
		upOi.setOrderStatus(OrderStatus.OUT.getIndex());
		upOi.setOutTime(outTime);
		upOi.setFeeTotal(amount);
		upOi.setIsFree(amount.intValue()==0?Constants.ORDER_ISFREE_TRUE:Constants.ORDER_ISFREE_FALSE);
		upOi.setUpdateTime(new Date());
		return orderInfoMapper.updateByPrimaryKeySelective(upOi);
	}
	@Override
	public OrderInfo selectParkingOrderByCarNoForToday(String carNo) {
		return (OrderInfo) query("OrderInfoDao.selectParkingOrderByCarNoForToday", carNo);
	}

	@Override
	public List<OrderInfo> selectParkingOrderByToday() {
		return queryList("OrderInfoDao.selectParkingOrderByToday", null);
	}

	@Override
	public OrderInfo selectRecentlyOrderByCarNo(String carNo) {
		return (OrderInfo) query("OrderInfoDao.selectRecentlyOrderByCarNo", carNo);
	}

	@Override
	public OrderInfo selectRecentlyOrderBySpaceNo(String spaceNo) {
		return (OrderInfo) query("OrderInfoDao.selectRecentlyOrderBySpaceNo", spaceNo);
	}

	@Override
	public List<OrderInfo> selectOrderHistoryBySpaceNo(String spaceNo) {
		return queryList("OrderInfoDao.selectOrderHistoryBySpaceNo", spaceNo);
	}
	
	@Override
	public OrderInfo selectLastOrderNoInvoiceByCarNo(String carNo){
		return (OrderInfo) query("OrderInfoDao.selectLastOrderNoInvoiceByCarNo", carNo);
	}
	
}
