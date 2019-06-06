package com.zycj.tcc.service;

import java.util.Date;

import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.exception.ArrearForOrderException;
import com.zycj.tcc.exception.OrderAddException;
import com.zycj.tcc.exception.OrderCompleteException;
import com.zycj.tcc.exception.PaymentForOrderException;
import com.zycj.tcc.exception.RoadSpaceUpdateForCarEntryException;
import com.zycj.tcc.exception.RoadSpaceUpdateForCarExitException;
import com.zycj.tcc.server.vo.DataResponse;
import com.zycj.tcc.vo.TraceInfoVo;

public interface OrderInfoService {

	/**
	 * 增加停车订单信息
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @param sectionName 路段名称
	 * @param spaceNo 泊车编号
	 * @param carNo 车牌号
	 * @param carType 车类型
	 * @parm feeType 计费规则id
	 * @parm arrearStatus 欠费状态
	 * @param reqseriesNo 交易流水号
	 */
	public OrderInfo addOrderInfo(String empNo, String posNo, String sectionName, String spaceNo, String carNo, Integer carType,
                                  Date parkTime, Integer feeType, Integer arrearStatus, String reqseriesNo, Integer sectionId)
			throws OrderAddException, RoadSpaceUpdateForCarEntryException;

	/**
	 * 完成订单驶出
	 * @param oldOrder
	 * @param empNo
	 * @param posNo
	 * @param reallAmount
	 * @param amount
	 * @param outTime
	 * @param outType
	 * @param invoiceId
	 * @param invoice
	 * @param reqseriesNo
	 * @return
	 */
	public void toCompleteOrder(OrderInfo oldOrder, String empNo, String posNo, String reallAmount,
                                String amount, Date outTime, Integer outType, String invoiceId, String invoice, String reqseriesNo, TraceInfoVo traceInfo)
					throws OrderCompleteException,PaymentForOrderException,ArrearForOrderException,RoadSpaceUpdateForCarExitException;
	
	/**
	 * 确认车辆驶出，更改车辆状态
	 * @param oldOrder
	 * @param empNo
	 * @param posNo
	 * @throws OrderCompleteException
	 * @throws PaymentForOrderException
	 * @throws ArrearForOrderException
	 * @throws RoadSpaceUpdateForCarExitException
	 */
	public void changeOrderStatus(OrderInfo oldOrder, String empNo, String posNo, Integer status)
					throws OrderCompleteException,PaymentForOrderException,ArrearForOrderException,RoadSpaceUpdateForCarExitException;
	/**
	 * 日终清算程序 驶出当天所有未驶出的订单
	 * @param oi 当天未驶出的订单
	 * @param isUpdateSpace 是否更新停车位
	 */
	public void clearOrderBySystem(OrderInfo oi, boolean isUpdateSpace)throws Exception;
	
	public DataResponse checkIsKtcOut(Integer orderId) throws Exception;
}
