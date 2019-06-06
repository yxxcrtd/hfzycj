package com.zycj.tcc.service;

import java.util.Date;

import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.util.FeeRegularInfo;
import com.zycj.tcc.vo.TraceInfoVo;

/**
 * 停车服务业务类
 * @author 洪捃能
 *
 */
public interface ParkService {
	/**
	 * 车辆驶入
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @param spaceNo 泊车编号
	 * @param carNo 车牌号
	 * @param carType 车类型 1小车，2大车
	 * @param reqseriesNo 交易流水号
	 * @return arrearage//欠费金额;inTime//驶入时间
	 */
	public Response carEntry(String empNo, String posNo, String spaceNo, String carNo, Integer carType, String reqseriesNo);

	/**
	 * 查询驶出时的停车费用
	 * @param orderNo 订单号
	 * @param empNo 员工编号
	 * @param posNo pos终端编号
	 * @param carNo 车牌号
	 * @param spaceNo 车位编号
	 * @return
	 */
	public Response selectParkFeeForExit(Integer orderNo, String empNo, String posNo, String carNo, String spaceNo, OrderInfo orderInfo);
	
	/**
	 * 根据车位编号和车类型获取计费规则明细类
	 * @param spaceNo 车位编号
	 * @param carType 车类型
	 * @return
	 */
	@Deprecated
	public FeeRegularInfo selectFeeRegularBySpaceNo(String spaceNo, Integer carType);

	/**
	 * 车辆确认驶出
	 * @param empNo 员工编号
	 * @param posNo 终端编号
	 * @param orderId 订单id
	 * @param reallAmount 实收金额
	 * @param amount 应收金额
	 * @param outTime 驶出时间
	 * @param outType 驶出类型
	 * @param invoiceId 发票代号
	 * @param invoice 发票号码
	 * @param reqseriesNo 交易流水号
	 * @return
	 */
	public Response carExit(String empNo, String posNo, Integer orderId,
                            String reallAmount, String amount, Date outTime, Integer outType,
                            String invoiceId, String invoice, String reqseriesNo, TraceInfoVo traceInfo, String carNo);
	
	/**
	 * 确认车辆驶出
	 * @param empNo
	 * @param posNo
	 * @param orderId
	 * @param carNo
	 * @return
	 */
	public Response carConfirmExit(String empNo, String posNo, Integer orderId, Integer status);
	
	/**
	 * 车辆费用支付业务
	 * @param orderId 订单id
	 * @param reallAmount 实收金额
	 * @param outTime 驶出时间
	 * @param outType 驶出类型
	 * @return
	 */
	public Response pay(Integer orderId, String reallAmount, Date outTime, Integer outType);

	public Response selectParkInfoByOrderId(Integer orderNo, String posNo);
	
	public int carSpecialCheck(String carNo);
	
	public boolean ticketCheck(String carNo, String sectionId);
}
