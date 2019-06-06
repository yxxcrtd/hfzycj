package com.zycj.tcc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zycj.mqtt.dto.MqttMessage;
import com.zycj.mqtt.server.MqttSendData;
import com.zycj.mqtt.util.MessageCode;
import com.zycj.mqtt.util.MqttConfig;
import com.zycj.tcc.async.AsyncExecutor;
import com.zycj.tcc.async.RoadSectionSpaceUsedWorker;
import com.zycj.tcc.common.OrderPayType;
import com.zycj.tcc.common.OrderStatus;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.dao.ArrearDao;
import com.zycj.tcc.dao.EmployeeDao;
import com.zycj.tcc.dao.FeeRegularDao;
import com.zycj.tcc.dao.InvoicePrintDao;
import com.zycj.tcc.dao.OrderInfoDao;
import com.zycj.tcc.dao.PaymentRecordDao;
import com.zycj.tcc.dao.RoadSectionDao;
import com.zycj.tcc.dao.RoadSpaceDao;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.exception.ArrearForOrderException;
import com.zycj.tcc.exception.OrderAddException;
import com.zycj.tcc.exception.OrderCompleteException;
import com.zycj.tcc.exception.PaymentForOrderException;
import com.zycj.tcc.exception.RoadSpaceUpdateForCarEntryException;
import com.zycj.tcc.exception.RoadSpaceUpdateForCarExitException;
import com.zycj.tcc.mybatis.mapper.OrderInfoMapper;
import com.zycj.tcc.scheduler.job.ParkClearJob;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.DataResponse;
import com.zycj.tcc.service.ChargeableEngine;
import com.zycj.tcc.service.OrderInfoService;
import com.zycj.tcc.service.ParkService;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.FeeRegularInfo;
import com.zycj.tcc.vo.ChargeableEngineResult;
import com.zycj.tcc.vo.TraceInfoVo;

/**
 * 停车订单服务类
 * @author 洪捃能
 *
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
	private final static Logger log = Logger.getLogger(OrderInfoServiceImpl.class); 
	private final static Logger rzlog = Logger.getLogger(ParkClearJob.class);
	
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private RoadSpaceDao roadSpaceDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ArrearDao arrearDao;
	@Autowired
	private PaymentRecordDao paymentRecordDao;
	@Autowired
	private InvoicePrintDao invoicePrintDao;
	@Autowired
	private FeeRegularDao feeRegularDao;
	@Autowired
	private ChargeableEngine chargeableEngine;
	@Autowired
	private RoadSectionDao roadSectionDao;
	@Autowired
	private ParkService parkService;
	
	@Transactional(rollbackFor={Exception.class})
	@Override
	public OrderInfo addOrderInfo(String empNo,String posNo,String sectionName,String spaceNo, String carNo, Integer carType,Date parkTime,Integer feeType,Integer arrearStatus,String reqseriesNo,Integer sectionId) 
			throws OrderAddException, RoadSpaceUpdateForCarEntryException{
		OrderInfo orderInfo = null;
		try {
			orderInfo = orderInfoDao.addOrderInfo(sectionName, spaceNo, carNo, carType, empNo, posNo, parkTime, feeType, arrearStatus, reqseriesNo,sectionId);
			if (orderInfo == null || orderInfo.getId() == null) {
				return null;
			}
		} catch (Exception e) {
			OrderAddException oae=new OrderAddException(e);
			log.warn("车辆驶入时",oae);
			throw oae;
		}
		try {
			roadSpaceDao.updateRoadSpaceBySpaceNoForCarEntry(spaceNo, carNo, parkTime);
		} catch (Exception e) {
			RoadSpaceUpdateForCarEntryException rsu=new RoadSpaceUpdateForCarEntryException(e);
			log.warn("车辆驶入时",rsu);
			throw rsu;
		}
		roadSectionDao.addSpace(spaceNo);
		return orderInfo;
	}

	@Transactional(rollbackFor={Exception.class})
	@Override
	public void changeOrderStatus(final OrderInfo oldOrder, String empNo, String posNo,Integer status) throws OrderCompleteException,
			PaymentForOrderException, ArrearForOrderException, RoadSpaceUpdateForCarExitException {
		Date outTime = new Date();//驶出时间
		try {
			orderInfoDao.updateOrderForCarExit(oldOrder.getId(),empNo,posNo,outTime,status);
		} catch (Exception e) {
			OrderCompleteException oce=new OrderCompleteException(oldOrder.getId(),e);
			log.warn("车辆驶出时",oce);
			throw oce;
		}
		//更新停车位信息
		try {
			if(status.equals(1)){
				roadSpaceDao.updateRoadSpaceBySpaceNoForCarExit(oldOrder.getSpaceNo(), outTime);
			}
		} catch (Exception e) {
			RoadSpaceUpdateForCarExitException rsce=new RoadSpaceUpdateForCarExitException(e);
			log.warn("车辆驶出时",rsce);
			throw rsce;
		}
		// 当拒绝驶出时，消息提醒手机移动端
		if(!status.equals(1)){
			new Thread(){
				public void run() {
					MqttMessage message = new MqttMessage();
					message.setBusinessID(String.valueOf(new Date().getTime()));
					message.setSendTopic(MqttConfig.getClientId());
					message.setCode(MessageCode.CAR_REFUSE_OUT.getCode());
					try {
						log.info("app-" + oldOrder.getCarNo() + "--------------------" + message.getCode());
						// 推送APP提醒
						MqttMessage result = MqttSendData.sendData("app-" + oldOrder.getCarNo(), message);
//						MqttMessage result = MqttSendData.sendData("app-123456", message);
						if(result == null){
							log.warn("路边停车场车辆缴费后拒绝驶出操作，提醒给客户端（无响应结果）！");
						} else {
							log.info("路边停车场车辆缴费后拒绝驶出操作，提醒给客户端（已完成）！");
						}
					} catch (Exception e) {
						log.error("路边停车场车辆缴费后拒绝驶出操作，提醒给客户端出现异常！",e);
					}
				};
			}.start();
		}
//		try {
//			AsyncExecutor.execute(new RoadSectionSpaceUsedWorker(OrderStatus.OUT.getIndex(),roadSectionDao,oldOrder.getSpaceNo()));
//		} catch (Exception e) {
//		}
	}
	
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void toCompleteOrder(OrderInfo oldOrder,String empNo, String posNo,String reallAmount, 
			String amount, Date outTime, Integer outType,String invoiceId, String invoice,String reqseriesNo,TraceInfoVo traceInfo) 
					throws OrderCompleteException, PaymentForOrderException, ArrearForOrderException, RoadSpaceUpdateForCarExitException{
		Date operateTime=new Date();//操作时间
		BigDecimal amount_bd=new BigDecimal(amount);
		BigDecimal reallAmount_bd=new BigDecimal(reallAmount);
		try {
			if(OrderPayType.CASH_OUT.getIndex()!=outType.intValue()
					&&OrderPayType.CARD_OUT.getIndex()!=outType.intValue()
					&&OrderPayType.YFK_OUT.getIndex()!=outType.intValue()
					&&OrderPayType.YZF_OUT.getIndex()!=outType.intValue()){
				invoice=null;
				invoiceId=null;
			}
			orderInfoDao.updateOrderForCarExit(oldOrder.getId(),outType,empNo,posNo,outTime,
					empNo,posNo,operateTime,amount_bd,reallAmount_bd,invoice,invoiceId);
		} catch (Exception e) {
			OrderCompleteException oce=new OrderCompleteException(oldOrder.getId(),e);
			log.warn("车辆驶出时",oce);
			throw oce;
		}
//			CASH_OUT("现金驶出",1),
//			CARD_OUT("刷卡驶出",2),
//			FREE_OUT("免费驶出",3),
//			REFUSED_TO_PAY_OUT("拒缴费驶出",4),
//			ARREARAGE_OUT("欠费驶出",5),
		if(OrderPayType.CASH_OUT.getIndex()==outType.intValue()
				||OrderPayType.CARD_OUT.getIndex()==outType.intValue()
				||OrderPayType.KTC_OUT.getIndex()==outType.intValue()
				||OrderPayType.ZFB_OUT.getIndex()==outType.intValue()
				||OrderPayType.WX_OUT.getIndex()==outType.intValue()
				||OrderPayType.YFK_OUT.getIndex()==outType.intValue()
				||OrderPayType.YZF_OUT.getIndex()==outType.intValue()){
			try {
				//增加缴费记录
				String empName=employeeDao.selectEmpNameByEmpNo(empNo);//收费员名称
				paymentRecordDao.addPaymentRecordForCarExit(oldOrder.getId(), amount_bd, reallAmount_bd, 
						operateTime, outType.intValue(), empNo, empName, oldOrder.getCarNo(), posNo, 
						oldOrder.getSpaceNo(), oldOrder.getSectionName(), invoiceId, invoice, reqseriesNo,traceInfo);
			} catch (Exception e) {
				PaymentForOrderException poe=new PaymentForOrderException(e);
				log.warn("车辆驶出时",poe);
				throw poe;
			}
			if(StringUtils.isNotBlank(invoiceId)||StringUtils.isNotBlank(invoice)){
				//记录发票打印信息
				try {
					invoicePrintDao.addInvoicePrintForCarExit(invoiceId, invoice, reallAmount_bd, oldOrder.getSectionName(), 
							oldOrder.getSpaceNo(), oldOrder.getInTime(), outTime, operateTime, 
							outType.intValue(), empNo, posNo, oldOrder.getCarNo(), oldOrder.getId(), reqseriesNo);
				} catch (Exception e) {
					log.warn("车辆驶出时，发票打印 异常",e);
				}
			}
		}else if(OrderPayType.ARREARAGE_OUT.getIndex()==outType.intValue()
				||OrderPayType.REFUSED_TO_PAY_OUT.getIndex()==outType.intValue()){
			//增加欠费记录
			try {
				arrearDao.addArrearForCarExit(oldOrder.getId(), oldOrder.getCarNo(), oldOrder.getCarType(), 
						oldOrder.getSectionName(), oldOrder.getSpaceNo(), oldOrder.getInEmp(), oldOrder.getInTime(), 
						outTime, amount_bd, outType.intValue(), reqseriesNo, oldOrder.getSectionNo());
			} catch (Exception e) {
				ArrearForOrderException aoe=new ArrearForOrderException(e);
				log.warn("车辆驶出时",aoe);
				throw aoe;
			}
		}
		//更新停车位信息
		try {
			roadSpaceDao.updateRoadSpaceBySpaceNoForCarExit(oldOrder.getSpaceNo(), outTime);
		} catch (Exception e) {
			RoadSpaceUpdateForCarExitException rsce=new RoadSpaceUpdateForCarExitException(e);
			log.warn("车辆驶出时",rsce);
			throw rsce;
		}
		try {
			AsyncExecutor.execute(new RoadSectionSpaceUsedWorker(OrderStatus.OUT.getIndex(),roadSectionDao,oldOrder.getSpaceNo()));
		} catch (Exception e) {
		}
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void clearOrderBySystem(OrderInfo oi,boolean isUpdateSpace) throws Exception{
		try {
			//获取计费规则
			FeeRegularInfo fri=feeRegularDao.selectFeeRegularInfoForCache(oi.getFeeId(), oi.getCarType());
			//设置驶出时间为计费结束时间
			String outTimeStr=DateUtil.format_YMD(oi.getInTime())+" "+DateUtil.format_HMS(fri.getEndTime());
			Date outTime=DateUtil.parse_YMDHMS(outTimeStr);
			ChargeableEngineResult cer=chargeableEngine.execute(oi.getInTime(), outTime, fri,2);
			if(!ResultCode.SUCCESS.equals(cer.getCode())){
				rzlog.warn(oi.getId()+" 计算停车费失败,清算失败");
				return ;
			}
			
			String carNo = oi.getCarNo();
			int freeCarType = parkService.carSpecialCheck(carNo);
			if (freeCarType != 0) {
				cer.setAmount(new BigDecimal(0.00));
				log.info("newcarNo="+carNo);
			}else {
				if (parkService.ticketCheck(carNo,oi.getSectionNo())) {
					cer.setAmount(new BigDecimal(0.00));
					log.info("monthcarNo="+carNo);
				}
			}
			
			//更新停车订单
			orderInfoDao.updateOrderForSystemClear(oi.getId(),outTime,cer.getAmount());
			//如果停车费为0，既免费的情况，过滤，无需增加欠费表或缴费表
			if(cer.getAmount().intValue()==0){
				rzlog.warn(oi.getId()+" 免费驶出");
				return ;
			}
			//增加日终欠费记录
			arrearDao.addArrearForCarExit(oi.getId(), oi.getCarNo(), oi.getCarType(), 
					oi.getSectionName(), oi.getSpaceNo(), oi.getInEmp(), oi.getInTime(), 
					outTime, cer.getAmount(), OrderPayType.END_ARREARAGE_OUT.getIndex(), null, oi.getSectionNo());
			if(isUpdateSpace){
				//更新停车位信息
				roadSpaceDao.updateRoadSpaceBySpaceNoForCarExit(oi.getSpaceNo(), outTime);
			}
//			try {
//				roadSectionDao.subtractSpace(oi.getSpaceNo());
//			} catch (Exception e) {
//			}
			rzlog.warn(oi.getId()+" 停车费:"+cer.getAmount());
		} catch (Exception e) {
			rzlog.warn(oi.getId()+" 清算 异常",e);
			throw e;
		}
	}

	@Override
	public DataResponse checkIsKtcOut(Integer orderId) throws Exception {
		OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
		if (orderInfo == null) {
			return ResponseUtil.createDataResponse(ResultCode.ORDER_NO_EXIST_ERROR,"");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		if (orderInfo.getOutTime() != null) {
			data.put("outTime", DateUtil.format_YMDHMS(orderInfo.getOutTime()));
		}else {
			data.put("outTime", "");
		}
		data.put("amount", orderInfo.getFeeTotal());
		
		if (OrderPayType.KTC_OUT.getIndex() == orderInfo.getPayType().intValue()
				|| OrderPayType.ZFB_OUT.getIndex() == orderInfo.getPayType().intValue()
				|| OrderPayType.WX_OUT.getIndex() == orderInfo.getPayType().intValue()) {
			data.put("orderStatus", "1");
		}else if (OrderPayType.NO_PAY.getIndex() == orderInfo.getPayType().intValue()) {
			data.put("orderStatus", "0");
		}else {
			data.put("orderStatus", "2");
		} 
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS,data);
	}
}
