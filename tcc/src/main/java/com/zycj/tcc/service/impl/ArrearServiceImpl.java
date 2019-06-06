package com.zycj.tcc.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.zycj.tcc.common.ArrearStatus;
import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.OrderArrearStatus;
import com.zycj.tcc.common.OrderPayType;
import com.zycj.tcc.common.PaymentFeeType;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.dao.ArrearDao;
import com.zycj.tcc.dao.EmployeeDao;
import com.zycj.tcc.dao.FeeRegularDao;
import com.zycj.tcc.dao.OrderInfoDao;
import com.zycj.tcc.dao.RoadSpaceDao;
import com.zycj.tcc.domain.Arrear;
import com.zycj.tcc.domain.ArrearExample;
import com.zycj.tcc.domain.InvoicePrint;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.domain.PaymentRecord;
import com.zycj.tcc.domain.PrePayCard;
import com.zycj.tcc.domain.PrePayCardRecord;
import com.zycj.tcc.domain.PrePayCardSection;
import com.zycj.tcc.mybatis.mapper.ArrearMapper;
import com.zycj.tcc.mybatis.mapper.InvoicePrintMapper;
import com.zycj.tcc.mybatis.mapper.OrderInfoMapper;
import com.zycj.tcc.mybatis.mapper.PrePayCardSectionMapper;
import com.zycj.tcc.server.util.PrePayPropertiesUtil;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.DataResponse;
import com.zycj.tcc.server.vo.PrePayCardVo;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.service.ArrearService;
import com.zycj.tcc.service.ChargeableEngine;
import com.zycj.tcc.service.PaymentService;
import com.zycj.tcc.service.PrePayCardRecordService;
import com.zycj.tcc.service.PrePayCardService;
import com.zycj.tcc.util.ArrayUtil;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.DecimalFormatUtil;
import com.zycj.tcc.util.FeeRegularInfo;
import com.zycj.tcc.vo.ArrearGroupInfo;
import com.zycj.tcc.vo.ArrearInfo;
import com.zycj.tcc.vo.ArrearListInfo;
import com.zycj.tcc.vo.ArrearListVo;
import com.zycj.tcc.vo.ArrearNetPayListInfo;
import com.zycj.tcc.vo.ChargeableEngineResult;
import com.zycj.tcc.vo.TraceInfoVo;

@Service
public class ArrearServiceImpl implements ArrearService {
	private final static Logger log = Logger.getLogger(ArrearServiceImpl.class);
	@Autowired
	private ArrearMapper arrearMapper;
	@Autowired
	private ArrearDao arrearDao;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private InvoicePrintMapper invoicePrintMapper;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private PrePayCardService prePayCardService;
	@Autowired
	private PrePayCardRecordService prePayCardRecordService;
	@Autowired
	private PrePayCardSectionMapper prePayCardSectionMapper;
	@Autowired
	private RoadSpaceDao roadSpaceDao;
	@Autowired
	private FeeRegularDao feeRegularDao;
	@Autowired
	private ChargeableEngine chargeableEngine;

	@Override
	public Response selectAllArrearAmountByCarNo(String carNo) {
		BigDecimal bd = arrearDao.selectAllArrearAmountByCarNo(carNo);
		if (bd == null) {
			bd = new BigDecimal("0.00");
		}
		Map<String, String> data = new HashMap<String, String>();
		data.put("arrearage", DecimalFormatUtil.format_00(bd));
		data.put("isArrearage", (bd.doubleValue() > 0 ? Constants.ISARREARAGE_TRUE : Constants.ISARREARAGE_FALSE) + "");
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, data);
	}

	@Override
	public ArrearInfo getArrearInfoByCarNo(String carNo) {
		ArrearInfo ai = new ArrearInfo();
		// 判断是否有拒缴、欠费记录以及计算欠费总额
		List<ArrearGroupInfo> aginfoList = arrearDao.selectArrearGroupByCarNo(carNo);
		int arrearStatus = OrderArrearStatus.NO_ARREAR.getIndex();// 无欠费 欠费状态
		double allArrearAmount = 0;// 欠费总额
		int arrearTotal = 0;// 欠费总笔数
		if (aginfoList != null && aginfoList.size() > 0) {
			boolean flag = false;// true:表示有拒缴，false:表示欠费
			for (ArrearGroupInfo agi : aginfoList) {
				allArrearAmount += agi.getAmount().doubleValue();
				arrearTotal += agi.getCount().intValue();
				if (OrderPayType.REFUSED_TO_PAY_OUT.getIndex() == agi.getArrearType().intValue()) {
					flag = true;
				}
			}
			arrearStatus = flag ? OrderArrearStatus.REFUSED_TO_PAY.getIndex() : OrderArrearStatus.ARREARAGE.getIndex();
		}
		ai.setArrearTotal(arrearTotal);
		ai.setArrearAmountTotal(new BigDecimal(DecimalFormatUtil.format_00(allArrearAmount)));
		ai.setArrearLevel(arrearStatus);
		ai.setArrearGroupList(aginfoList);
		return ai;
	}

	@Override
	public Response getArrearListByCarNo(String carNo, int carType, int pageNo) {
		ArrearListVo vo = new ArrearListVo();
		List<Arrear> list = arrearDao.selectListByCarNo(carNo, carType, pageNo, SystemConfig.ARREAR_PAGESIZE);
		List<ArrearListInfo> listInfo = new ArrayList<ArrearListInfo>();
		for (Arrear arrear : list) {
			ArrearListInfo info = new ArrearListInfo();
			info.setArrearageId(String.valueOf(arrear.getId()));
			info.setArrearage(DecimalFormatUtil.format_00(arrear.getArrearAmount()));
			info.setCarNo(arrear.getCarNo());
			try {
				info.setInTime(DateUtil.format_YMDHMS(arrear.getInTime()));
				info.setOutTime(DateUtil.format_YMDHMS(arrear.getOutTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			info.setSectionName(arrear.getSectionName());
			listInfo.add(info);
		}
		vo.setInfo(listInfo);
		vo.setOrderNo(String.valueOf(((PageList<Arrear>) list).getPaginator().getTotalCount()));// 多少笔欠费
		vo.setTotalMoney(DecimalFormatUtil.format_00(arrearDao.selectAllArrearAmountByCarNo(carNo)));// 总欠费金额
		try {
			vo.setServerTime(DateUtil.format_YMDHMS(new Date()));
		} catch (ParseException e) {
		}
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, vo);
	}

	@Transactional
	@Override
	public Response payArrear(String ids, BigDecimal reallAmount, BigDecimal amount, String posNo, String empNo,
			Integer payType, String reqSerial, String invoiceBatch, String invoiceNo, TraceInfoVo traceInfo,
			String cardNo) throws Exception {
		return payArrearExtrand(ids, reallAmount, amount, posNo, empNo, payType, reqSerial, invoiceBatch, invoiceNo,
				traceInfo, cardNo, cardNo);
	}

	private DataResponse payArrearExtrand(String ids, BigDecimal reallAmount, BigDecimal amount, String posNo,
			String empNo, Integer payType, String reqSerial, String invoiceBatch, String invoiceNo,
			TraceInfoVo traceInfo, String cardNo, String carNo) throws Exception {
		DataResponse dataResponse = new DataResponse();
		dataResponse.setResultCode(ResultCode.SUCCESS);
		int balance = 0 ;
		try{
			balance = prePayCardService.getAmountByCardNo(cardNo);
		} catch (Exception e){
			log.error("请求通联获取欠费余额失败！", e);
			balance = -1 ;
		}
		/**
		 * 实际缴费金额
		 */
		BigDecimal realPay = new BigDecimal(0);
		List<Integer> idsList = ArrayUtil.stringToInteger(ArrayUtil.strToArray(ids));
		if (idsList == null || idsList.size() == 0) {
			dataResponse.setData(new PrePayCardVo(false, "补缴欠费的ids格式不正确！"));
			return dataResponse;
		}
		// 校验支付类型
		if (payType.intValue() != OrderPayType.CASH_OUT.getIndex()
				&& payType.intValue() != OrderPayType.CARD_OUT.getIndex()
				&& payType.intValue() != OrderPayType.KTC_OUT.getIndex()
				&& payType.intValue() != OrderPayType.ZFB_OUT.getIndex()
				&& payType.intValue() != OrderPayType.WX_OUT.getIndex()
				&& payType.intValue() != OrderPayType.YZF_OUT.getIndex()
				&& payType.intValue() != OrderPayType.YFK_OUT.getIndex()) {
			dataResponse.setData(new PrePayCardVo(false, "支付类型 错误！"));
			return dataResponse;
		}
		List<Arrear> arrearList = getArrearListByIds(idsList);
		if (arrearList == null || arrearList.size() == 0) {
			dataResponse.setData(new PrePayCardVo(false, "无id对应的相关欠费记录！"));
			return dataResponse;
		}
		// 计算每笔欠费需要的实缴费用--平均
		int[] amounts = null;
		if (SystemConfig.Arrear_Funds_Distribution_Mode == 1) {// 平均
			if (reallAmount.intValue() < arrearList.size()) {// 如果实缴金额小于欠费记录笔数，都不够付每笔欠费1元钱
				dataResponse.setData(new PrePayCardVo(false, "补缴金额不足以支付所选欠费记录！"));
				return dataResponse;
			}
			amounts = getEachRealAmount(reallAmount.intValue(), arrearList.size());
		} else {
			int sum = 0;
			for (Arrear arr : arrearList) {
				sum += arr.getArrearAmount().intValue();
			}
			if (amount.intValue() != sum) {
				// 合并应缴金额与合并的欠费金额不一致
				dataResponse.setData(new PrePayCardVo(false, "补缴的金额与欠费金额不一致！"));
				return dataResponse;
			}
		}
		String empName = employeeDao.selectEmpNameByEmpNo(empNo);// 收费员名称
		Date payTime = new Date();// 补缴支付时间
		Arrear lastArrear = null;
		boolean isKtcPay = false;
		PrePayCardVo vo = new PrePayCardVo();
		vo.setSuccess(true);
		vo.setMessage("普通收费");
		try {
			for (int i = 0; i < arrearList.size(); i++) {
				Arrear arrear = arrearList.get(i);
				if (arrearList.size() - 1 == i) {
					lastArrear = arrear;
				}
				if (arrear.getStatus().intValue() == ArrearStatus.PAID.getIndex()) {
					if (OrderPayType.KTC_OUT.getIndex() == arrear.getPayType().intValue()
							|| OrderPayType.ZFB_OUT.getIndex() == arrear.getPayType().intValue()
							|| OrderPayType.WX_OUT.getIndex() == arrear.getPayType().intValue()) {
						isKtcPay = true;
						continue;
					} else {
						dataResponse.setData(new PrePayCardVo(false, "欠费id:" + arrear.getId() + " 重复补缴"));
						return dataResponse;
					}
				} else {
					if (isKtcPay) {
						dataResponse.setData(new PrePayCardVo(false, "欠费id:" + arrear.getId() + " 重复补缴"));
						return dataResponse;
					}
				}
				BigDecimal payAmount = null;// 实缴
				if (SystemConfig.Arrear_Funds_Distribution_Mode == 1) {// 平均
					payAmount = new BigDecimal(amounts[i]);
				} else {
					// 应缴金额
					payAmount = arrear.getArrearAmount();
					/**
					 * 1.判断支付类型，是不是预付费卡 payType=10，执行以下逻辑
					 */

					/**
					 * 2.先获得支付卡类型 PrePayCard prePayCard =
					 * prePayCardRecordService.getPrePayCard(cardNo);
					 */

					/**
					 * 3.判断支付卡类型 3.1 如果是VIP卡，把payAmount更新为0 3.2 如果是月票卡 3.2.1
					 * 判断欠费记录的section是否是月票卡绑定的路段，如果是则将payAmount更新为0。
					 * (arrear.getSectionId()跟prepay_card_section 中的 section_id)
					 * 3.3 如果是新能源卡，跟场景二逻辑一致
					 */
					// 判断支付类型，是不是预付费卡 payType=10，执行以下逻辑
					if (payType == 10) {
						BigDecimal temp = payAmount;
						String tip = null;
						PrePayCard prePayCard = new PrePayCard();
						prePayCard.setEndTime(arrear.getCreateTime());
						prePayCard.setCardNo(cardNo);
						// 先获得支付卡类型
						prePayCard = prePayCardRecordService.getPrePayCard(prePayCard);
						if (prePayCard != null) {
							BigDecimal cent_amount = null;
							BigDecimal pay_amount = null;
							// 判断支付卡类型,如果是VIP卡，把payAmount更新为0
							if (prePayCard.getCardType() == PrePayCard.CARD_TYPE_VIP
									&& prePayCard.getCardCarNo().equalsIgnoreCase(carNo)) {
								payAmount = new BigDecimal(0);
								vo.setRealAmount(String.valueOf(0));
								tip = "VIP卡免费停车";
								vo.setMessage(tip);
								vo.setBalance(balance / 100.0);
							}
							// 如果是月票卡,
							// 判断欠费记录的section是否是月票卡绑定的路段，如果是则将payAmount更新为0。
							else if (prePayCard.getCardType() == PrePayCard.CARD_TYPE_TICKET
									&& prePayCard.getCardCarNo().equalsIgnoreCase(carNo)) {
								List<PrePayCardSection> prePayCardSection = prePayCardSectionMapper
										.selectByCardNo(cardNo);
								for (PrePayCardSection pp : prePayCardSection) {
									if (Integer.parseInt(arrear.getSectionNo()) == pp.getSectionId()) {
										payAmount = new BigDecimal(0);
									}
								}
								vo.setRealAmount(String.valueOf(0));
								tip = "月票卡缴费";
								vo.setMessage(tip);
								vo.setBalance(balance / 100.0);
							}
							// 如果是新能源卡
							else if (prePayCard.getCardType() == PrePayCard.CARD_TYPE_NEW_ENERGY) {
								// 新能源卡绑定的车牌号是否跟要补缴的车牌号一致
								if (prePayCard.getCardCarNo().equalsIgnoreCase(arrear.getCarNo())) {
									int times = prePayCardRecordService.countCardDeal(cardNo, arrear.getCreateTime());
									if (times < Integer.parseInt(PrePayPropertiesUtil.get("new_energy_max"))) {
										tip = "新能源卡优惠停车";
										Date inTime = arrear.getInTime();// 车辆驶入时间
										Date start = DateUtil.getLateTime(inTime, 1);// 车辆驶入时间往后推迟一小时（新能源卡免费停车一小时）
										Date end = arrear.getOutTime();// 车辆驶出时间
										int isc = (int) DateUtil.getTimeDiffByMin(start, end);// 计算计费开始时间与驶出时间的差值
										// 计费开始时间 +1小时比驶出时间大（免费停车一小时内）
										if (isc < 0) {
											payAmount = new BigDecimal(0);
											vo.setMessage(tip);
											vo.setBalance(balance / 100.0);
										} else {
											OrderInfo orderInfo = orderInfoMapper
													.selectByPrimaryKey(arrear.getOrderId());// 通过order_id得到orderInfo
											Integer regId = orderInfo.getFeeId();
											Integer carType = orderInfo.getCarType();// 获取车类型
											if (regId == null) {
												regId = roadSpaceDao.selectFeeRegularIdBySpaceNo(arrear.getSpaceNo());
											}
											FeeRegularInfo fri = feeRegularDao.selectFeeRegularInfoForCache(regId,
													carType);// 获得计费公式明细对象
											int freeTime = fri.getFreeTime();// 免费时间
											Date time = DateUtil.getBeforeFreeTime(start, freeTime);// 计费时间往前推免费时间
											ChargeableEngineResult cer = chargeableEngine.execute(time, end, fri, 1);// 计算停车所要缴纳的费用
											payAmount = cer.getAmount();
											vo.setMessage(tip);
										}
										
									} else {
										tip = "欠费补缴";
										vo.setMessage("新能源卡免费次数已经用完,进行了普通收费！");
									}
								} else {
									dataResponse.setData(new PrePayCardVo(false, "新能源卡绑定车牌不匹配"));
									return dataResponse;
								}
							}
							cent_amount = payAmount.multiply(new BigDecimal(100));
							pay_amount = temp.multiply(new BigDecimal(100));
							PrePayCardRecord record = new PrePayCardRecord();
							record.setAmount(cent_amount);
							record.setDiscount(pay_amount.subtract(cent_amount));
							record.setCardNo(cardNo);
							record.setCreateTime(arrear.getCreateTime());
							record.setEmpNo(empNo);
							record.setOrderId(prePayCardService.buildOrderId());
							record.setRecordType(PrePayCardRecord.RECORD_TYPE_PAY);	//预付费卡支付类型， 1-充值 ，2-消费
							record.setTip(tip);
							record.setChargeType(null);
							prePayCardRecordService.save(record);
						} else {
							dataResponse.setData(new PrePayCardVo(false, "卡号不存在或卡已过期"));
							return dataResponse;
						}
					}
				}
				/**
				 * 累加实际缴费金额
				 */
//				realPay += payAmount.intValue();
				realPay = realPay.add(payAmount);

				Integer id = arrear.getId();
				// update arrear info
				arrear.setId(id);
				arrear.setPayAmount(payAmount);// 实缴
				arrear.setPayTime(payTime);
				arrear.setPayType(payType);
				arrear.setChargeEmp(empNo);
				arrear.setPosNo(posNo);
				arrear.setReqSerial(reqSerial);
				arrear.setStatus(ArrearStatus.PAID.getIndex());
				arrear.setUpdateTime(new Date());
				arrearMapper.updateByPrimaryKeySelective(arrear);

				// insert payment
				PaymentRecord paymentRecord = new PaymentRecord();
				paymentRecord.setArrearId(id);
				paymentRecord.setIsCheck(Constants.CHECK_STATUS_NO);
				paymentRecord.setCarNo(arrear.getCarNo());
				paymentRecord.setChargeEmp(empNo);
				paymentRecord.setEmpName(empName);
				paymentRecord.setFeeType(PaymentFeeType.MAKE_PAYMENT.getIndex());
				paymentRecord.setInvoiceBatch(invoiceBatch);
				paymentRecord.setInvoiceNo(invoiceNo);
				paymentRecord.setOrderId(arrear.getOrderId());
				paymentRecord.setPayTime(payTime);
				paymentRecord.setPayType(payType);
				paymentRecord.setPosNo(posNo);
				paymentRecord.setToPay(arrear.getArrearAmount());
				paymentRecord.setRealPay(payAmount);// 实缴
				paymentRecord.setReqSerial(reqSerial);
				paymentRecord.setSectionName(arrear.getSectionName());
				// paymentRecord.setSectionNo(arrear.getSectionNo());
				paymentRecord.setSpaceNo(arrear.getSpaceNo());

				paymentRecord.setTraceNo(traceInfo.getTraceNo());
				paymentRecord.setReferenceNo(traceInfo.getReferenceNo());
				paymentRecord.setCardNo(traceInfo.getCardNo());

				paymentService.insertPaymentRecord(paymentRecord);
				try {
					if (StringUtils.isNotBlank(invoiceBatch) || StringUtils.isNotBlank(invoiceNo)) {
						InvoicePrint invoicePrint = new InvoicePrint();
						invoicePrint.setInvoiceBatch(invoiceBatch);
						invoicePrint.setInvoiceNo(invoiceNo);
						invoicePrint.setInvoiceAmount(payAmount);// reallAmount
						invoicePrint.setPayTime(payTime);
						invoicePrint.setPayType(payType);
						invoicePrint.setChargeEmp(empNo);
						invoicePrint.setPosNo(posNo);
						invoicePrint.setFeeType(PaymentFeeType.MAKE_PAYMENT.getIndex());
						invoicePrint.setReqSerial(reqSerial);
						//
						invoicePrint.setArrearId(arrear.getId());
						invoicePrint.setCarNo(arrear.getCarNo());
						invoicePrint.setSectionName(arrear.getSectionName());
						invoicePrint.setSpaceNo(arrear.getSpaceNo());
						invoicePrint.setInTime(arrear.getInTime());
						invoicePrint.setOutTime(arrear.getOutTime());
						invoicePrint.setOrderId(arrear.getOrderId());
						//
						invoicePrint.setPrintTimes(1);
						invoicePrintMapper.insertSelective(invoicePrint);
					}
				} catch (Exception e) {
					log.warn("欠费补缴  发票记录 异常", e);
					dataResponse.setData(new PrePayCardVo(false, "出现异常!"));
					return dataResponse;
				}
			}
			/**
			 * 将实际支付金额赋值给 应缴金额，用于在支付方法中使用
			 */
//			reallAmount = new BigDecimal(realPay);
			reallAmount = realPay;
			vo.setRealAmount(String.valueOf(reallAmount));
			dataResponse.setData(vo);
		} catch (Exception e) {
			log.warn("欠费补缴 异常", e);
			dataResponse.setData(new PrePayCardVo(false, "出现异常!"));
			return dataResponse;
		}

		if (isKtcPay) {
			dataResponse.setData(new PrePayCardVo(false, "欠费已经补缴了，由快停车补缴!"));
			return dataResponse;
		}

		try {
			// update orderinfo isArrear
			OrderInfo orderInfo = orderInfoDao.selectParkingOrderByCarNoForToday(lastArrear.getCarNo());
			if (orderInfo != null) {
				ArrearInfo arrearInfo = getArrearInfoByCarNo(lastArrear.getCarNo());
				if (arrearInfo != null) {
					OrderInfo tmpInfo = new OrderInfo();
					tmpInfo.setId(orderInfo.getId());
					orderInfo.setArrearStatus(arrearInfo.getArrearLevel());
					orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
				}
			}
		} catch (Exception e) {
			log.warn("更新 车牌号：" + lastArrear.getCarNo() + " 对应的欠费级别  异常", e);
			dataResponse.setData(new PrePayCardVo(false, "出现异常!"));
			return dataResponse;
		}
		dataResponse.setData(vo);
		return dataResponse;
	}

	@Override
	public List<Arrear> getArrearListByIds(List<Integer> idsList) {
		ArrearExample example = new ArrearExample();
		example.or().andIdIn(idsList);
		return arrearMapper.selectByExample(example);
	}

	// 2笔，共15元，7、8分
	public static int[] getEachRealAmount(int amount, int arrearCount) {
		int[] as = new int[arrearCount];
		int s = amount / arrearCount;
		int y = amount % arrearCount;
		for (int i = 0; i < as.length; i++) {
			as[i] = s;
		}
		for (int i = 0; i < y; i++) {
			as[i] = as[i] + 1;
		}
		return as;
	}

	@Deprecated
	public static BigDecimal getRealByList(List<Arrear> arrears, int index, BigDecimal real) {
		BigDecimal sum = new BigDecimal(0);
		for (Arrear big : arrears) {
			sum = sum.add(big.getArrearAmount());
		}
		return arrears.get(index).getArrearAmount().divide(sum, new MathContext(10)).multiply(real, new MathContext(3));
	}

	public String selectIdListByCarNo(String carNo, Date selectTime) {
		List<Integer> ids = arrearDao.selectIdListByCarNo(carNo, selectTime);
		return ArrayUtil.list2String(ids, ",");
	}

	@Override
	public Response getArrearNetPayByCarNo(String carNo, int pageNo) {
		ArrearListVo<ArrearNetPayListInfo> vo = new ArrearListVo<ArrearNetPayListInfo>();
		List<Arrear> list = arrearDao.selectNetPayListByCarNo(carNo, pageNo, SystemConfig.ARREAR_PAGESIZE);
		List<ArrearNetPayListInfo> listInfo = new ArrayList<ArrearNetPayListInfo>();
		for (Arrear arrear : list) {
			ArrearNetPayListInfo info = new ArrearNetPayListInfo();
			info.setArrearageId(String.valueOf(arrear.getId()));
			info.setCarNo(arrear.getCarNo());
			try {
				info.setInTime(DateUtil.format_YMDHMS(arrear.getInTime()));
				info.setOutTime(DateUtil.format_YMDHMS(arrear.getOutTime()));
				info.setPayTime(DateUtil.format_YMDHMS(arrear.getPayTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			info.setSectionName(arrear.getSectionName());
			info.setPayMoney(DecimalFormatUtil.format_00(arrear.getPayAmount()));
			info.setPayType(String.valueOf(arrear.getPayType()));
			listInfo.add(info);
		}
		vo.setInfo(listInfo);
		vo.setOrderNo(String.valueOf(((PageList<Arrear>) list).getPaginator().getTotalCount()));// 多少笔
		vo.setTotalMoney(DecimalFormatUtil.format_00(arrearDao.selectNetPayAmountByCarNo(carNo)));// 总金额
		try {
			vo.setServerTime(DateUtil.format_YMDHMS(new Date()));
		} catch (ParseException e) {
		}
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, vo);
	}

	@Override
	public Response printNetPay(String ids, String posNo, String empNo, String reqSerial, String invoiceBatch,
			String invoiceNo, Date selectTime, String carNo) throws Exception {
		List<Integer> idsList = ArrayUtil.stringToInteger(ArrayUtil.strToArray(ids));
		if (idsList == null || idsList.size() == 0) {
			return ResponseUtil.createResponse(ResultCode.ARREAR_IDS_NO_MATCH_ERROR);
		}
		ArrearExample example = new ArrearExample();
		if (ids.equals("0")) {
			example.or().andStatusEqualTo(0);
			int count = arrearMapper.countByExample(example);
			if (count == 0) {
				arrearDao.updatePrintAll(selectTime, invoiceBatch, invoiceNo, carNo);
			} else {
				return ResponseUtil.createResponse(ResultCode.ARREAR_FAILED);
			}
		} else {
			example.or().andStatusEqualTo(0).andIdIn(idsList);
			int count = arrearMapper.countByExample(example);
			if (count == 0) {
				arrearDao.updatePrintBatch(idsList, invoiceBatch, invoiceNo);
			} else {
				return ResponseUtil.createResponse(ResultCode.ARREAR_FAILED);
			}
		}
		return ResponseUtil.createResponse(ResultCode.SUCCESS);
	}

	@Override
	@Transactional
	public Response payArrearByPrePayCard(String ids, BigDecimal reallAmount, BigDecimal amount, String posNo,
			String empNo, Integer payType, String reqSerial, String invoiceBatch, String invoiceNo,
			TraceInfoVo traceInfo, String cardNo, String carNo) throws Exception {
		DataResponse payArrearExtrand = payArrearExtrand(ids, reallAmount, amount, posNo, empNo, payType, reqSerial,
				invoiceBatch, invoiceNo, traceInfo, cardNo, carNo);
		if (ResultCode.SUCCESS.equals(payArrearExtrand.getResultCode())) {
			/**
			 * 判断reallPay>0,执行缴费方法
			 */
			Object obj = payArrearExtrand.getData();
			BigDecimal reallPay = new BigDecimal(0);
			PrePayCardVo vo = null;
			if(obj != null && obj instanceof PrePayCardVo){
				vo = (PrePayCardVo)obj;
				if(vo.getRealAmount() == null || vo.getRealAmount() == ""){
					reallPay = new BigDecimal(-1);
				} else {
					reallPay = new BigDecimal(vo.getRealAmount());
				}
			}
			BigDecimal discount = amount.subtract(reallPay); // 优惠金额
			
			if (reallPay.intValue() > 0) {
				// 扣款
				try {
					boolean deal = prePayCardService.deal(cardNo, reallPay.toString(), empNo, discount.toString());
					if (!deal) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						payArrearExtrand.setData(new PrePayCardVo(false, "欠费补缴失败！"));
					}
					if (vo != null){
						try{
							int balance = prePayCardService.getAmountByCardNo(cardNo);
							vo.setBalance(balance / 100.0);
						} catch (Exception e){
							log.warn("请求通联获取欠费余额失败！", e);
							vo.setBalance(-1);
						}
					}
				} catch (Exception e) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					if ("余额不足".equals(e.getMessage())) {
						payArrearExtrand.setData(new PrePayCardVo(false, "停车卡补缴余额不足！"));
					} else {
						payArrearExtrand.setData(new PrePayCardVo(false, "欠费补缴失败！"));
					}
				}
			}
		}
		return payArrearExtrand;
	}
}
