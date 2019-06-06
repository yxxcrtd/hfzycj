package com.zycj.tcc.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zycj.mqtt.dto.MqttMessage;
import com.zycj.mqtt.server.MqttSendData;
import com.zycj.mqtt.util.MessageCode;
import com.zycj.mqtt.util.MqttConfig;
import com.zycj.tcc.async.AsyncExecutor;
import com.zycj.tcc.async.OrderInfoWorker;
import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.OrderArrearStatus;
import com.zycj.tcc.common.OrderFlow;
import com.zycj.tcc.common.OrderPayType;
import com.zycj.tcc.common.OrderStatus;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.dao.FeeRegularDao;
import com.zycj.tcc.dao.OrderInfoDao;
import com.zycj.tcc.dao.PaymentRecordDao;
import com.zycj.tcc.dao.PosDao;
import com.zycj.tcc.dao.RoadSpaceDao;
import com.zycj.tcc.domain.CarSpecial;
import com.zycj.tcc.domain.CarSpecialExample;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.domain.PaymentRecord;
import com.zycj.tcc.domain.Pos;
import com.zycj.tcc.domain.RoadSection;
import com.zycj.tcc.domain.RoadSectionExample;
import com.zycj.tcc.domain.TicketInfo;
import com.zycj.tcc.exception.ArrearForOrderException;
import com.zycj.tcc.exception.OrderCompleteException;
import com.zycj.tcc.exception.PaymentForOrderException;
import com.zycj.tcc.exception.RoadSpaceUpdateForCarEntryException;
import com.zycj.tcc.exception.RoadSpaceUpdateForCarExitException;
import com.zycj.tcc.mybatis.mapper.CarSpecialMapper;
import com.zycj.tcc.mybatis.mapper.OrderInfoMapper;
import com.zycj.tcc.mybatis.mapper.RoadSectionMapper;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.ArrearService;
import com.zycj.tcc.service.ChargeableEngine;
import com.zycj.tcc.service.OrderInfoService;
import com.zycj.tcc.service.ParkService;
import com.zycj.tcc.service.TicketInfoService;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.DecimalFormatUtil;
import com.zycj.tcc.util.FeeRegularInfo;
import com.zycj.tcc.vo.ArrearInfo;
import com.zycj.tcc.vo.CarEntryVo;
import com.zycj.tcc.vo.ChargeableEngineResult;
import com.zycj.tcc.vo.ParkFeeVo;
import com.zycj.tcc.vo.RoadSpaceSectionInfo;
import com.zycj.tcc.vo.TraceInfoVo;

@Service
public class ParkServiceImpl implements ParkService {
	private final static Logger log = Logger.getLogger(ParkServiceImpl.class); 
	
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private PosDao posDao;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private RoadSpaceDao roadSpaceDao;
	@Autowired
	private PaymentRecordDao paymentRecordDao;
	@Autowired
	private ChargeableEngine chargeableEngine;
	@Autowired
	private FeeRegularDao feeRegularDao;
	@Autowired
	private ArrearService arrearService;
	@Autowired
	private  TicketInfoService ticketInfoService;
	@Autowired
	private CarSpecialMapper carSpecialMapper;
	@Autowired
	private RoadSectionMapper roadSectionMapper;
	
	//0003 车辆驶入
	@Override
	public synchronized Response carEntry(String empNo, String posNo,String spaceNo, String carNo, Integer carType,String reqseriesNo) {
		try{
			if(carType.intValue()!=1&&carType.intValue()!=2){
				return ResponseUtil.createResponse(ResultCode.CAR_TYPE_NO_MATCH_ERROR);
			}
			if (StringUtils.isNotBlank(carNo)) {
				if (carNo.startsWith("皖")) {
					if (carNo.indexOf("I") != -1 || carNo.indexOf("O") != -1) {
						return ResponseUtil.createResponse(ResultCode.CARNO_REG_ERROR);
					}
				}
			}
			//判断车位是否存在，并且获取计费规则id、车位停车信息
			RoadSpaceSectionInfo rssi=roadSpaceDao.selectRoadSpaceSectionInfoBySpaceNo(spaceNo);
			if(rssi==null){
				return ResponseUtil.createResponse(ResultCode.ROAD_SPACE_NO_EXIST_ERROR);
			}
			//判断车位是否可用
			if(rssi.getSpaceStatus()==null||rssi.getSpaceStatus()!=Constants.ROADSPACE_STATUS_OPEN){
				return ResponseUtil.createResponse(ResultCode.ROAD_SPACE_STATUS_CLOSE_ERROR);
			}
			//判断计费规则是否存在
			FeeRegularInfo fri=feeRegularDao.selectFeeRegularInfoForCache(rssi.getFeeType(), carType);
			if(fri==null){
				return ResponseUtil.createResponse(ResultCode.FEE_REGULAR_NO_EXIST_ERROR);
			}
			//生成停车时间
		 	Date parkTime=new Date();
			try {
				//判断当前驶入时间是否在计费时间段内
				if(checkParkingTime(fri.getStartTime(),fri.getEndTime(),parkTime)){
					return ResponseUtil.createResponse(ResultCode.CAR_ENTRY_TIME_NOT_IN_FEEREG_TIME);
				}
			} catch (Exception e) {
				log.warn("判断驶入时间是否是否在计费时间段内 异常",e);
				return ResponseUtil.createResponse(ResultCode.CAR_ENTRY_TIME_CHECK_EXCEPTION);
			}
			//判断停车位是否可用，如果不可用判断是不是今天的日期
			if(rssi.getIsParked().intValue()==Constants.ROADSPACE_ISPARKED_YES){//如果车位已停车
				//判断此车位是不是当天停入的，以确保日终清算程序异常导致车位未清占用状态，导致隔天的停车业务无法进行
				if(rssi.getParkTime()!=null&&DateUtil.isToday(rssi.getParkTime())){
					return ResponseUtil.createResponse(ResultCode.CAR_ENTRY_ROADSPACE_ISPARKED_ERROR);
				}else{
					//如果存在日终异常没有清算的车位及订单，做单笔清算
					try{
						AsyncExecutor.execute(new OrderInfoWorker(orderInfoDao,orderInfoService,spaceNo));
					}catch(Exception e){
					}
				}
			}
			//校验该车牌号是否已经有驶入操作了
			OrderInfo oldoi=orderInfoDao.selectParkingOrderByCarNoForToday(carNo);
			if(oldoi!=null){
				String msg=oldoi.getCarNo()+" 已于"+DateUtil.format_YMDHMS(oldoi.getInTime())+" 在 "+oldoi.getSectionName()+" 驶入!";
				Map<String,Object> result=new HashMap<String,Object>();
				result.put("msg", msg);
				result.put("orderId", oldoi.getId());
				result.put("spaceNo", oldoi.getSpaceNo());
				return ResponseUtil.createDataResponse(ResultCode.CAR_HAS_ENTERED_ERROR, result);
			}
			//查询是否有拒缴、欠费记录以及计算欠费总额
			ArrearInfo arrearInfo=arrearService.getArrearInfoByCarNo(carNo);
			//生成订单
			OrderInfo orderInfo = null;
			try{
				orderInfo = orderInfoService.addOrderInfo(empNo,posNo,rssi.getSectionName(),spaceNo,carNo,carType,parkTime,rssi.getFeeType(),arrearInfo.getArrearLevel(),reqseriesNo,rssi.getSectionId());
			}catch (RoadSpaceUpdateForCarEntryException e) {
				return ResponseUtil.createResponse(ResultCode.ROAD_SPACE_UPDATE_CARENTRY_ERROR);
			}catch(Exception e){
				return ResponseUtil.createResponse(ResultCode.CAR_ENTRY_ADD_ORDERINFO_ERROR);
			}
			if (orderInfo == null || orderInfo.getId()==null) {
				return ResponseUtil.createResponse(ResultCode.CAR_ENTRY_ADD_ORDERINFO_ERROR);
			}
			
			int freeCarType = 0;
			freeCarType = carSpecialCheck(carNo);
			if (freeCarType == 0 && ticketCheck(carNo,rssi.getSectionId()+"")) {
				freeCarType = 1;
			}
			final OrderInfo oi = orderInfo;
			new Thread(){
				public void run() {
					MqttMessage message = new MqttMessage();
					message.setBusinessID(String.valueOf(new Date().getTime()));
					message.setSendTopic(MqttConfig.getClientId());
					message.setCode(MessageCode.CAR_ENTRY.getCode());
					//message.setData(JSONObject.fromObject(oi).toString());
					message.setData(JSONObject.toJSONString(oi));
					try {
						log.info("app-" + oi.getCarNo() + "--------------------" + JSONObject.toJSONString(oi));
						// 推送APP提醒
						MqttMessage result = MqttSendData.sendData("app-" + oi.getCarNo(), message);
//						MqttMessage result = MqttSendData.sendData("app-123456", message);
						if(result == null){
							log.warn("路边停车场车辆驶入后，提醒给客户端可以进行自主缴费功能（无响应结果）！");
						} else {
							log.info("路边停车场车辆驶入后，提醒给客户端可以进行自主缴费功能（已完成）！");
						}
					} catch (Exception e) {
						log.error("路边停车场车辆驶入后，提醒给客户端可以进行自主缴费功能出现异常！",e);
					}
				};
			}.start();
			return ResponseUtil.createDataResponse(ResultCode.SUCCESS, new CarEntryVo(DecimalFormatUtil.format_00(arrearInfo.getArrearAmountTotal()),parkTime,String.valueOf(orderInfo.getId()),String.valueOf(freeCarType)));
		}catch(Exception e){
			log.warn("车辆驶入失败",e);
			return ResponseUtil.createResponse(ResultCode.CAR_ENTRY_FAILED);
		}
	}
	
	//0005 车辆驶出费用查询
	@Override
	public Response selectParkFeeForExit(Integer orderNo, String empNo,String posNo, String carNo, String spaceNo,OrderInfo orderInfo) {
		try{
			OrderInfo oi = orderInfo;
			if (oi == null) {
				oi = orderInfoMapper.selectByPrimaryKey(orderNo);
			}
			if(oi==null){
				return ResponseUtil.createResponse(ResultCode.ORDER_NO_EXIST_ERROR);
			}
			final Date outTime=new Date();//驶出时间，临时，用于计算停车费
			//判断此订单是不是当天的
//			if(!DateUtil.isSameDay(oi.getInTime(),outTime)){
//				return ResponseUtil.createResponse(ResultCode.PARK_FEE_INTIME_NOT_TODAY_ERROR);
//			}
			//判断订单是否已完成驶出
			if(oi.getOrderStatus().intValue()!=OrderStatus.IN.getIndex()){
				return ResponseUtil.createResponse(ResultCode.ORDER_HAS_COMPLETED);
			}
//			if(!oi.getCarNo().equals(carNo)){
//				return ResponseUtil.createResponse(ResultCode.ORDER_CARNO_NO_MATCH_ERROR);
//			}
//			if(!oi.getSpaceNo().equals(spaceNo)){
//				return ResponseUtil.createResponse(ResultCode.ORDER_SPACENO_NO_MATCH_ERROR);
//			}
			
			
			Integer carType=oi.getCarType();//获取车类型
			if(carType==null){
				return ResponseUtil.createResponse(ResultCode.CAR_TYPE_ISBLANK_ERROR);
			}
			Date inTime=oi.getInTime();//驶入时间
			
			int freeCarType = carSpecialCheck(carNo);
			if (freeCarType != 0) {
				log.info("newcarNo="+carNo);
				return ResponseUtil.createDataResponse(ResultCode.SUCCESS, new ParkFeeVo(inTime,outTime,new BigDecimal("0.00"),carNo,spaceNo,oi.getSectionName(),"0.00",String.valueOf(freeCarType),String.valueOf(oi.getCarType())));
			}
			
			if (ticketCheck(carNo,oi.getSectionNo())) {
				log.info("monthcarNo="+carNo);
				return ResponseUtil.createDataResponse(ResultCode.SUCCESS, new ParkFeeVo(inTime,outTime,new BigDecimal("0.00"),carNo,spaceNo,oi.getSectionName(),"0.00","1",String.valueOf(oi.getCarType())));
			}
			
			Integer regId=oi.getFeeId();
			if(regId==null){
				regId=roadSpaceDao.selectFeeRegularIdBySpaceNo(spaceNo);
				if(regId==null){
					return ResponseUtil.createResponse(ResultCode.FEE_REGULAR_NO_EXIST_ERROR);
				}
			}
			FeeRegularInfo fri=feeRegularDao.selectFeeRegularInfoForCache(regId, carType);//获得计费公式明细对象
			if(fri==null){
				return ResponseUtil.createResponse(ResultCode.FEE_REGULAR_NO_EXIST_ERROR);
			}
			ChargeableEngineResult cer=chargeableEngine.execute(inTime,outTime,fri,2);//计算停车时间
			if(ResultCode.SUCCESS.equals(cer.getCode())){
				List<PaymentRecord> payList = paymentRecordDao.findPaymentRecordByOrderId(oi.getId());
				double payAmount = 0;
				for(PaymentRecord pr : payList){
					payAmount += pr.getRealPay().doubleValue();
				}
				return ResponseUtil.createDataResponse(ResultCode.SUCCESS, new ParkFeeVo(inTime,outTime,new BigDecimal(cer.getAmount().doubleValue() - payAmount),carNo,spaceNo,oi.getSectionName(),"0.00","0",String.valueOf(oi.getCarType())));
			}else{
				return ResponseUtil.createResponse(cer.getCode());
			}
		}catch(Exception e){
			log.warn("驶出停车费用计算异常",e);
			return ResponseUtil.createResponse(ResultCode.PARK_FEE_EXECUTE_FAILED);
		}
	}
	@Override
	@Deprecated
	public FeeRegularInfo selectFeeRegularBySpaceNo(String spaceNo,Integer carType){
		try {
			Integer regId=roadSpaceDao.selectFeeRegularIdBySpaceNo(spaceNo);
			if(regId==null){
				return null;
			}
			FeeRegularInfo fri=feeRegularDao.selectFeeRegularInfoForCache(regId, carType);//取缓存
			if(fri==null){
				return null;
			}
			return fri;
		} catch (Exception e) {
			log.warn("获取计费规则并格式化异常",e);
			return null;
		}
	}

	/**
	 * 判断驶入时间是否是否在计费时间段内
	 * @param startTime 计费开始时间
	 * @param endTime 计费结束时间
	 * @param parkTime 驶入时间
	 * @return true:不是在计费时间段内，false:在计费时间段内
	 * @throws ParseException 时间转换异常
	 */
	public boolean checkParkingTime(Date startTime,Date endTime,Date parkTime) throws ParseException{
		Date parkTime_hms=DateUtil.parse_HMS(parkTime);
		int time=(int) DateUtil.getTimeDiffBySec(startTime, parkTime_hms);
		int luxuryTime_sec=SystemConfig.Car_Entry_Luxury_Time*DateUtil.MIN;
		if(time+luxuryTime_sec<0){
			return true;
		}
		time=(int) DateUtil.getTimeDiffBySec(endTime, parkTime_hms);
		if(time>0){
			return true;
		}
		return false;
	}
	
	//0055 支付成功
	@Override
	public Response pay(Integer orderId, String reallAmount, Date outTime,Integer outType) {
		synchronized (this) {
			//获取订单
			final OrderInfo oldOrder=orderInfoMapper.selectByPrimaryKey(orderId);
			if(oldOrder==null){
				return ResponseUtil.createResponse(ResultCode.ORDER_NO_EXIST_ERROR);
			}
			try {
				orderInfoDao.updateOrderForPay(oldOrder.getId(),OrderFlow.PAY_WAIT_CONFIRM.getIndex());
				BigDecimal reallAmount_bd=new BigDecimal(reallAmount);
				paymentRecordDao.addPaymentRecordForPay(oldOrder.getId(), reallAmount_bd,outTime, outType.intValue());
				// TODO 根据停车的车牌号，获得该停车场中所有的POS机（需求待确认）
				final List<Pos> posList = posDao.findPos(Integer.parseInt(oldOrder.getSectionNo()));
				log.info(posList);
				new Thread(){
					@Override
					public void run() {
						//消息推送到POS机，进行缴费成功确认
						MqttMessage message = new MqttMessage();
						message.setBusinessID(String.valueOf(new Date().getTime()));
						message.setSendTopic(MqttConfig.getClientId());
						message.setCode(MessageCode.PAY_FINISH_REMIND.getCode());
						message.setData(JSONObject.toJSONString(oldOrder));
						for(Pos pos : posList){
							try {
								log.info("pay-pos-" + pos.getPosNo() + "--------------------" + JSONObject.toJSONString(oldOrder));
								// 推送APP提醒
								MqttMessage result = MqttSendData.sendData("pos-" + pos.getPosNo(), message);
								if(result == null){
									log.warn("路边停车场车辆费用支付成功后，向POS机推送消息（失败）！");
								} else {
									log.info("路边停车场车辆费用支付成功后，向POS机推送消息（已完成）！");
								}
							} catch (Exception e) {
								log.error("路边停车场车辆费用支付成功后，向POS机推送消息（出现异常）！",e);
							}
						}
					}
				}.start();
			} catch (Exception e) {
				log.error("车辆驶出时 更新停车位异常",e);
				return ResponseUtil.createResponse(ResultCode.ROAD_SPACE_UPDATE_CAREXIT_ERROR);
			}
			return ResponseUtil.createResponse(ResultCode.SUCCESS);
		}
	}
	
	//1006 车辆确认驶出
	@Override
	public Response carConfirmExit(String empNo, String posNo, Integer orderId,Integer status) {
			//获取订单
		synchronized (this) {
			OrderInfo oldOrder=orderInfoMapper.selectByPrimaryKey(orderId);
			if(oldOrder==null){
				return ResponseUtil.createResponse(ResultCode.ORDER_NO_EXIST_ERROR);
			}
			//判断订单是否已完成驶出
			if(oldOrder.getOrderStatus().intValue()!=OrderStatus.IN.getIndex()){
				if (OrderPayType.KTC_OUT.getIndex() == oldOrder.getPayType().intValue()) {
					return ResponseUtil.createResponse(ResultCode.ORDER_HAS_COMPLETED_KTC);
				}
				return ResponseUtil.createResponse(ResultCode.ORDER_HAS_COMPLETED);
			}
			try {
				orderInfoService.changeOrderStatus(oldOrder,empNo,posNo,status);
				return ResponseUtil.createResponse(ResultCode.SUCCESS);
			} catch (OrderCompleteException e) {
				return ResponseUtil.createResponse(ResultCode.CAR_EXIT_UPDATE_ORDER_EXCEPTION);
			} catch (PaymentForOrderException e) {
				return ResponseUtil.createResponse(ResultCode.CAR_EXIT_ADD_PAYMENT_RECORD_EXCEPTION);
			} catch (ArrearForOrderException e) {
				return ResponseUtil.createResponse(ResultCode.CAR_EXIT_ADD_ARREAR_EXCEPTION);
			} catch (RoadSpaceUpdateForCarExitException e) {
				return ResponseUtil.createResponse(ResultCode.ROAD_SPACE_UPDATE_CAREXIT_ERROR);
			}catch(Exception e){
				log.warn("车辆确认驶出异常",e);
				return ResponseUtil.createResponse(ResultCode.CAR_EXIT_FAILED);
			}
		}
	}

	//0006 车辆确认驶出
	@Override
	public Response carExit(String empNo, String posNo, Integer orderId,
			String reallAmount, String amount, Date outTime, Integer outType,
			String invoiceId, String invoice,String reqseriesNo,TraceInfoVo traceInfo,String carNo) {
		synchronized (this) {
			//校验驶出类型
			if(outType.intValue()!=OrderPayType.CASH_OUT.getIndex()&&
					outType.intValue()!=OrderPayType.CARD_OUT.getIndex()&&
					outType.intValue()!=OrderPayType.FREE_OUT.getIndex()&&
					outType.intValue()!=OrderPayType.REFUSED_TO_PAY_OUT.getIndex()&&
					outType.intValue()!=OrderPayType.ARREARAGE_OUT.getIndex()
					&&outType.intValue()!=OrderPayType.KTC_OUT.getIndex()
					&&outType.intValue()!=OrderPayType.WX_OUT.getIndex()
					&&outType.intValue()!=OrderPayType.ZFB_OUT.getIndex()
					&&outType.intValue()!=OrderPayType.YFK_OUT.getIndex()
					&&outType.intValue()!=OrderPayType.YZF_OUT.getIndex()){
				return ResponseUtil.createResponse(ResultCode.CAR_EXIT_OUTTYPE_ERROR);
			}
			//获取订单
			OrderInfo oldOrder=orderInfoMapper.selectByPrimaryKey(orderId);
			if(oldOrder==null){
				return ResponseUtil.createResponse(ResultCode.ORDER_NO_EXIST_ERROR);
			}
			//判断订单是否已完成驶出
			if(oldOrder.getOrderStatus().intValue()!=OrderStatus.IN.getIndex()){
				if (OrderPayType.KTC_OUT.getIndex() == oldOrder.getPayType().intValue()) {
					return ResponseUtil.createResponse(ResultCode.ORDER_HAS_COMPLETED_KTC);
				}
				return ResponseUtil.createResponse(ResultCode.ORDER_HAS_COMPLETED);
			}
			
			if (StringUtils.isNotBlank(carNo) && !carNo.equals(oldOrder.getCarNo())) {
				return ResponseUtil.createResponse(ResultCode.ORDER_CARNO_NO_MATCH_ERROR);
			}
			
			try {
				orderInfoService.toCompleteOrder(oldOrder,empNo,posNo,reallAmount,amount,outTime,outType,invoiceId,invoice,reqseriesNo,traceInfo);
				return ResponseUtil.createResponse(ResultCode.SUCCESS);
			} catch (OrderCompleteException e) {
				return ResponseUtil.createResponse(ResultCode.CAR_EXIT_UPDATE_ORDER_EXCEPTION);
			} catch (PaymentForOrderException e) {
				return ResponseUtil.createResponse(ResultCode.CAR_EXIT_ADD_PAYMENT_RECORD_EXCEPTION);
			} catch (ArrearForOrderException e) {
				return ResponseUtil.createResponse(ResultCode.CAR_EXIT_ADD_ARREAR_EXCEPTION);
			} catch (RoadSpaceUpdateForCarExitException e) {
				return ResponseUtil.createResponse(ResultCode.ROAD_SPACE_UPDATE_CAREXIT_ERROR);
			}catch(Exception e){
				log.warn("车辆确认驶出异常",e);
				return ResponseUtil.createResponse(ResultCode.CAR_EXIT_FAILED);
			}
		}
	}
	
	
	//0028 
		@Override
		public Response selectParkInfoByOrderId(Integer orderNo,String posNo) {
			try{
				OrderInfo oi=orderInfoMapper.selectByPrimaryKey(orderNo);
				if(oi==null){
					return ResponseUtil.createResponse(ResultCode.ORDER_NO_EXIST_ERROR);
				}
				//判断订单是否已完成驶出
				if(oi.getOrderStatus().intValue()!=OrderStatus.IN.getIndex()){
					return ResponseUtil.createResponse(ResultCode.ORDER_HAS_COMPLETED);
				}
				String carNo = oi.getCarNo();
				if (oi.getArrearStatus() != OrderArrearStatus.NO_ARREAR.getIndex()) {
					//查询是否有拒缴、欠费记录以及计算欠费总额
					ArrearInfo arrearInfo=arrearService.getArrearInfoByCarNo(oi.getCarNo());
//					Map<String,Object> res=new HashMap<String,Object>();
//					res.put("carNo",carNo);
//					res.put("arrearAmount", DecimalFormatUtil.format_00(arrearInfo.getArrearAmountTotal()));
					if (arrearInfo == null || arrearInfo.getArrearAmountTotal() == null) {
						return ResponseUtil.createResponse(ResultCode.PARK_FEE_ORDERSEARCH_FAILED);
					}
					return ResponseUtil.createDataResponse(ResultCode.SUCCESS,
							new ParkFeeVo(null,null,null,carNo,oi.getSpaceNo(),oi.getSectionName(),DecimalFormatUtil.format_00(arrearInfo.getArrearAmountTotal()),"0",String.valueOf(Constants.CAR_TYPE_SMALL)));
				}else {
					return selectParkFeeForExit(orderNo, oi.getInEmp(), posNo, carNo, oi.getSpaceNo(),oi);
				}
				
			}catch(Exception e){
				log.warn("根据订单号查询订单信息异常",e);
				return ResponseUtil.createResponse(ResultCode.PARK_FEE_ORDERSEARCH_FAILED);
			}
		}
		
		public boolean ticketCheck(String carNo,String sectionId) {
			if (StringUtils.isEmpty(carNo) || StringUtils.isEmpty(sectionId)) {
				return false;
			}
			List<TicketInfo> infos = ticketInfoService.getTicketInfoByCarNo(carNo);
			if (infos == null) {
				return false;
			}
			RoadSectionExample roadSectionExample = new RoadSectionExample();
			roadSectionExample.or().andIdEqualTo(Integer.parseInt(sectionId));
			List<RoadSection> selectByExample = roadSectionMapper.selectByExample(roadSectionExample);
			if(selectByExample==null || selectByExample.size()==0){
				return false;
			}
			RoadSection roadSection = selectByExample.get(0);
			for (TicketInfo ticketInfo : infos) {
				if (ticketInfo == null ) {
					continue;
				}
				if (ticketInfo.getCarNo() ==null || !ticketInfo.getCarNo().equals(carNo)) {
					continue;
				}
				if(ticketInfo.getCompanyId() != roadSection.getCompanyId()){
					continue;
				}
				Date start = ticketInfo.getStartDate();
				Date end = ticketInfo.getEndDate();
				if (start == null || end == null) {
					continue;
				}
				if (start.after(new Date()) || end.before(new Date())) {
					continue;
				}
				String sids = ticketInfo.getSectionId();
				if (sids != null) {
					if ("-1".equals(sids)) {
						return true;
					}
					String[] aIds = sids.split(",");
					for (int i = 0; i < aIds.length; i++) {
						if (sectionId.equals(aIds[i])) {
							return true;
						}
					}
				}
			}
			return false;
		}
		
		public int carSpecialCheck(String carNo) {
			CarSpecialExample example = new CarSpecialExample();
			example.or().andCarNoEqualTo(carNo).andStatusEqualTo(1)
				.andStartDateLessThanOrEqualTo(new Date())
				.andEndDateGreaterThanOrEqualTo(new Date());
			List<CarSpecial> list = carSpecialMapper.selectByExample(example);
			if (list == null || list.size() <= 0) {
				return 0;
			}
			CarSpecial carSpecial = list.get(0);
			if (carSpecial != null) {
				//0 默认正常免费，1 月票，2 新能源,3 特殊车
				Integer carType = carSpecial.getCarType();//1-新能源车,2-特殊车
				if (carType!=null) {
					if (carType == 1) {
						return 2;
					}else if (carType == 2) {
						return 3;
					}
				}
				return 2;
			}
			return 0;
		}
}
