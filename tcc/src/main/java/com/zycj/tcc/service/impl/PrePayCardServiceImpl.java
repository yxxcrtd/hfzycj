package com.zycj.tcc.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.dao.FeeRegularDao;
import com.zycj.tcc.dao.RoadSpaceDao;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.domain.PrePayCard;
import com.zycj.tcc.domain.PrePayCardRecord;
import com.zycj.tcc.mybatis.mapper.OrderInfoMapper;
import com.zycj.tcc.server.util.HttpUtil;
import com.zycj.tcc.server.util.PrePayCardHelper;
import com.zycj.tcc.server.util.PrePayPropertiesUtil;
import com.zycj.tcc.server.vo.DataResponse;
import com.zycj.tcc.server.vo.PrePayCardVo;
import com.zycj.tcc.service.ChargeableEngine;
import com.zycj.tcc.service.PrePayCardRecordService;
import com.zycj.tcc.service.PrePayCardService;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.DecimalFormatUtil;
import com.zycj.tcc.util.FeeRegularInfo;
import com.zycj.tcc.vo.ChargeableEngineResult;

@Service
public class PrePayCardServiceImpl implements PrePayCardService{
	
	// 最大充值的金额
	public static final int MAX_AMOUNT = 100000;
	public static final int MAX_EMP_AMOUNT = 200000;
	@Autowired
	private PrePayCardRecordService prePayCardRecordService;
	@Autowired
	private ChargeableEngine chargeableEngine;
	@Autowired
	private FeeRegularDao feeRegularDao;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private RoadSpaceDao roadSpaceDao;
	
	private static final String encode = "UTF-8";
	
	public static Logger log = Logger.getLogger(PrePayCardService.class);
	
	@Override
	public DataResponse pay(Map<String, String> params){
		DataResponse dataResponse = new DataResponse();
		dataResponse.setResultCode(ResultCode.SUCCESS);
		String amount = null;
		BigDecimal cent_amount = null;
		BigDecimal pay_amount = null;
		String tip = "普通收费";
		int balance = 0 ;
		try{
			balance = this.getAmountByCardNo(params.get("cardNo"));
		} catch (Exception e){
			log.error("请求通联获取欠费余额失败！", e);
			balance = -1 ;
		}
		//除合肥地区其他地区暂时不能使用
		if(!"0".equals(params.get("emp_no").substring(0, 1))){
			dataResponse.setData(new PrePayCardVo(false, "该地区暂时无法使用"));
			return dataResponse;
		}
		Map<String, Object> pp_params = PrePayCardHelper.buildBaseParam("allinpay.ppcs.cardpaywithoutpassword.add");
		pp_params.put("card_id", params.get("cardNo"));// 消费卡号
		

		PrePayCard prePayCard = new PrePayCard();
		Date date = new Date();
		prePayCard.setEndTime(date);
		prePayCard.setCardNo(params.get("cardNo"));
		prePayCard = prePayCardRecordService.getPrePayCard(prePayCard);
		// 卡号不存在
		if (prePayCard == null) {
			dataResponse.setData(new PrePayCardVo(false, "卡号不存在或卡已过期"));
			return dataResponse;
		}
		
		// 新能源卡免费停车
		if (prePayCard.getCardType() == PrePayCard.CARD_TYPE_NEW_ENERGY) {
			if (prePayCard.getCardCarNo() == null || !prePayCard.getCardCarNo().equalsIgnoreCase(params.get("carNo"))) {
				dataResponse.setData(new PrePayCardVo(false, "绑定车牌不匹配"));
				return dataResponse;
			}
			// 本日停车次数
			int times = prePayCardRecordService.countCardDealToday(params.get("cardNo"));
			if (times < Integer.parseInt(PrePayPropertiesUtil.get("new_energy_max"))) {
				OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(Integer.parseInt(params.get("orderNo")));//通过orderNo（order_id）得到orderInfo
				Date inTime = orderInfo.getInTime();//车辆驶入时间
				Date start = DateUtil.getLateTime(inTime,1);//车辆驶入时间往后推迟一小时（新能源卡免费停车一小时）
				Date end= new Date();//获取当前时间
				int isc = (int) DateUtil.getTimeDiffByMin(start,end);//计算计费开始时间与驶出时间的差值
				//计费开始时间 +1小时比驶出时间大（免费停车一小时内）并且在本日免费停车次数之内
				if(isc < 0){
					setNoMoney(params, pp_params);
					save(pp_params, PrePayCardRecord.RECORD_TYPE_PAY, "新能源卡免费停车", null);
					dataResponse.setData(new PrePayCardVo(true, "新能源卡免费停车", "0", balance / 100.0));
					return dataResponse;
				} else {
					Integer regId = orderInfo.getFeeId();
					Integer carType = orderInfo.getCarType();//获取车类型
					if(regId==null){
						regId = roadSpaceDao.selectFeeRegularIdBySpaceNo(params.get("space_no"));
					}
					FeeRegularInfo fri = feeRegularDao.selectFeeRegularInfoForCache(regId, carType);//获得计费公式明细对象
					int freeTime = fri.getFreeTime();//免费时间
					Date time = DateUtil.getBeforeFreeTime(start,freeTime);//计费时间往前推免费时间
					ChargeableEngineResult cer = chargeableEngine.execute(time,end,fri,1);//计算停车所要缴纳的费用
					amount = String.valueOf(cer.getAmount());
				}
			} else {
				tip = "新能源卡优惠次数已使用完";
			}
		}

		// VIP全路段免费停车
		if (prePayCard.getCardType() == PrePayCard.CARD_TYPE_VIP) {
			if (prePayCard.getCardCarNo() == null || !prePayCard.getCardCarNo().equalsIgnoreCase(params.get("carNo"))) {
				dataResponse.setData(new PrePayCardVo(false, "绑定车牌不匹配"));
				return dataResponse;
			}
			setNoMoney(params, pp_params);
			save(pp_params, PrePayCardRecord.RECORD_TYPE_PAY, "VIP卡免费停车", null);
			dataResponse.setData(new PrePayCardVo(true, "VIP卡免费停车", "0", balance / 100.0));
			return dataResponse;
		}
		
		// 月票卡对应路段的话免费停车
		if (prePayCard.getCardType() == PrePayCard.CARD_TYPE_TICKET) {
			// 判断月票卡绑定的车牌是否匹配
			if (prePayCard.getCardCarNo() != null && prePayCard.getCardCarNo().equalsIgnoreCase(params.get("carNo"))) {
				//判断月票卡绑定路段是否匹配
				if (prePayCardRecordService.existSection(params.get("sectionName"), params.get("cardNo"))) {
					setNoMoney(params, pp_params);
					save(pp_params, PrePayCardRecord.RECORD_TYPE_PAY, "月票卡免费停车", null);
					dataResponse.setData(new PrePayCardVo(true, "月票卡免费停车", "0", balance / 100.0));
					return dataResponse;
				}
			} else {
				dataResponse.setData(new PrePayCardVo(false, "绑定车牌不匹配"));
				return dataResponse;
			}
		}
		
		if (StringUtils.isNullOrEmpty(amount)) {
			amount = params.get("payAmount");// 停车费
		}
		if (!checkAmount(amount)) {
			dataResponse.setData(new PrePayCardVo(false, "金额错误"));
		} else {
			// 转单位 分
			BigDecimal realAmount = new BigDecimal(DecimalFormatUtil.format_00(Double.parseDouble(amount)));
			cent_amount = realAmount.multiply(new BigDecimal(100));//实收金额
			BigDecimal payAmount = new BigDecimal(DecimalFormatUtil.format_00(Double.parseDouble(params.get("payAmount"))));
			pay_amount = payAmount.multiply(new BigDecimal(100));//应收金额
			BigDecimal discount = pay_amount.subtract(cent_amount); // 优惠金额

			pp_params.put("order_id", buildOrderId());// 交易流水号
			pp_params.put("trans_time", getTimeStr(new Date()));// 交易时间
			pp_params.put("amount", cent_amount);
			pp_params.put("discount", discount);
			pp_params.put("pay_code", "0002");// 产品编号
			pp_params.put("mer_id", PrePayPropertiesUtil.get("mechant_id"));
			
			try {
				PrePayCardHelper.sign(pp_params, encode);
				String post = HttpUtil.get(PrePayPropertiesUtil.get("base_url"), pp_params, encode);
				log.info(post);
				JSONObject parse = (JSONObject) JSON.parse(post);
				if (parse != null) {
					parse = (JSONObject) parse.get("ppcs_cardinfopay_get_response");
					if (parse != null) {
						parse = (JSONObject) parse.get("bindcard_info");
						if (parse != null && parse.get("resp_code") != null) {
							Object object = parse.get("resp_code");
							if ("0000".equals(object)) {
								PrePayCardVo vo = new PrePayCardVo(true, tip, String.valueOf(realAmount), (balance - cent_amount.intValue()) / 100.0);
								dataResponse.setData(vo);
								pp_params.put("opr_id", params.get("emp_no"));// 操作员
								pp_params.put("order_id", params.get("orderNo"));
								save(pp_params, PrePayCardRecord.RECORD_TYPE_PAY, "停车费用", null);
							} else {
								dataResponse.setData(new PrePayCardVo(false, (String) parse.get("resp_desc"),params.get("payAmount")));
							}
						} else {
							if (post.indexOf("余额") != -1 && post.indexOf("小于") != -1) {
								dataResponse.setData(new PrePayCardVo(false, "余额不足"));
							} else {
								dataResponse.setData(new PrePayCardVo(false, "付款失败"));
							}
						}
					} else {
						if (post.indexOf("余额") != -1 && post.indexOf("小于") != -1) {
							dataResponse.setData(new PrePayCardVo(false, "余额不足"));
						} else {
							dataResponse.setData(new PrePayCardVo(false, "付款失败"));
						}
					}
				} else {
					dataResponse.setData(new PrePayCardVo(false, "请求失败"));
				}

			} catch (Exception e) {
				dataResponse.setData(new PrePayCardVo(false, "系统错误"));
				e.printStackTrace();
			}
		}
		return dataResponse;
	}
	
	@Override
	public void chongzhi(Map<String, String> params, DataResponse dataResponse, String amount) {
		//转单位
		int amount_i = (int) (Double.parseDouble(amount) * 100);
		amount = amount_i + "";
		Map<String, Object> pp_params = PrePayCardHelper.buildBaseParam("allinpay.ppcs.cardsingletopup.add");
		String orderId = params.get("orderId");
		pp_params.put("order_id", orderId);
		pp_params.put("card_id", params.get("cardNo"));// 充值卡号
		pp_params.put("trans_time", getTimeStr(new Date()));// 交易时间
		pp_params.put("prdt_no", "0002");// 产品编号
		pp_params.put("top_up_way", "1");// 充值方式
		pp_params.put("opr_id", params.get("emp_no"));// 操作员
		pp_params.put("desn", "现金充值");// 充值卡号
		pp_params.put("amount", amount);
		pp_params.put("discount", amount);
		
		//余额判断
		try {
			int yue = getAmountByCardNo(params.get("cardNo"));
			if (yue + amount_i > MAX_AMOUNT) {
				dataResponse.setData(new PrePayCardVo(false, "单张卡最多只能存"+MAX_AMOUNT/100+"元"));
				if("2".equals(params.get("chargeType"))){//银行卡充值保存错误，后期让通联退款
					saveErrorChongzhi(pp_params);
				}
				return;
			}
		} catch (Exception e1) {
			dataResponse.setData(new PrePayCardVo(false, "交易失败(查询余额发生错误)"));
			if("2".equals(params.get("chargeType"))){//银行卡充值保存错误，后期让通联退款
				saveErrorChongzhi(pp_params);
			}
			return ;
		}
		
		//重复的订单
		if (prePayCardRecordService.getPrePayCardRecordByOrderId(orderId) != null) {
			if("2".equals(params.get("chargeType"))){//银行卡充值保存错误，后期让通联退款
				saveErrorChongzhi(pp_params);
			}
			dataResponse.setData(new PrePayCardVo(false, "交易已过期"));
			return ;
		}
		save(pp_params, PrePayCardRecord.RECORD_TYPE_CHARGE, "充值", params.get("chargeType"));
		try {
			PrePayCardHelper.sign(pp_params, encode);
			String post = HttpUtil.get(PrePayPropertiesUtil.get("base_url"), pp_params, encode);
			JSONObject parse = (JSONObject) JSON.parse(post);
			if (parse != null) {
				parse = (JSONObject) parse.get("ppcs_cardsingletopup_add_response");
				if (parse != null) {
					parse = (JSONObject) parse.get("result_info");
					if (parse != null && parse.get("valid_balance") != null) {
						Object object = parse.get("valid_balance");
						String xx = object.toString();
						double parseInt = Double.parseDouble(xx);
						parseInt = parseInt / 100;
						NumberFormat instance = NumberFormat.getInstance();
						instance.setMinimumFractionDigits(2);
						instance.setMaximumFractionDigits(2);
						String format = instance.format(parseInt);
						dataResponse.setData(new PrePayCardVo(true, "充值成功,账户余额:" + format + "元"));
					} else {
						dataResponse.setData(new PrePayCardVo(true, "充值成功"));
					}
				} else {
					parse = (JSONObject) JSON.parse(post);
					parse = (JSONObject) parse.getJSONObject("error_response");
					if (parse != null && parse.getString("sub_msg") != null) {
						dataResponse.setData(new PrePayCardVo(false, parse.getString("sub_msg").toString()));
					} else {
						dataResponse.setData(new PrePayCardVo(false, "充值失败"));
					}
					delRecord(orderId);
					if("2".equals(params.get("chargeType"))){//银行卡充值保存错误，后期让通联退款
						saveErrorChongzhi(pp_params);
					}
				}
			} else {
				delRecord(orderId);
				if("2".equals(params.get("chargeType"))){//银行卡充值保存错误，后期让通联退款
					saveErrorChongzhi(pp_params);
				}
				dataResponse.setData(new PrePayCardVo(false, "请求失败"));
			}

		} catch (Exception e) {
			delRecord(orderId);
			if("2".equals(params.get("chargeType"))){//银行卡充值保存错误，后期让通联退款
				saveErrorChongzhi(pp_params);
			}
			dataResponse.setData(new PrePayCardVo(false, "系统错误"));
			e.printStackTrace();
		}
	}
	
	public String getTimeStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	private void delRecord(String orderId) {
		prePayCardRecordService.deleteByOrderId(orderId);
	}
	@Override
	public void saveErrorChongzhi(Map<String, Object> pp_params) {
		PrePayCardRecord record = new PrePayCardRecord();
		String amount = (String) pp_params.get("amount");
		record.setAmount(new BigDecimal(amount));
		record.setCardNo((String) pp_params.get("card_id"));
		record.setCreateTime(new Date());
		record.setEmpNo((String) pp_params.get("opr_id"));
		record.setOrderId((String) pp_params.get("order_id"));
		prePayCardRecordService.saveError(record);
	}
	
	private void save(Map<String, Object> pp_params, int type, String tip, String chargeType) {
		PrePayCardRecord record = new PrePayCardRecord();
		String amount = String.valueOf(pp_params.get("amount"));
		String discount = String.valueOf(pp_params.get("discount"));
		record.setAmount(new BigDecimal(amount));
		record.setDiscount(new BigDecimal(discount));
		record.setCardNo((String) pp_params.get("card_id"));
		record.setCreateTime(new Date());
		record.setEmpNo((String) pp_params.get("opr_id"));
		record.setOrderId((String) pp_params.get("order_id"));
		record.setRecordType(type);// 充值
		record.setTip(tip);
		record.setChargeType(chargeType);
		prePayCardRecordService.save(record);
	}
	
	@Override
	public int getAmountByCardNo(String cardNo) throws Exception {
		Map<String, Object> pp_params = PrePayCardHelper.buildBaseParam("allinpay.ppcs.cardinfo.get");
		pp_params.put("card_id", cardNo);// 充值卡号
		try {
			PrePayCardHelper.sign(pp_params, encode);
			String post = HttpUtil.get(PrePayPropertiesUtil.get("base_url"), pp_params, encode);
			JSONObject parse = (JSONObject) JSON.parse(post);
			if (parse != null && (parse = (JSONObject) parse.get("ppcs_cardinfo_get_response")) != null) {
				if ((parse = (JSONObject) parse.get("card_info")) != null) {
					if ((parse = (JSONObject) parse.get("card_product_info_arrays")) != null) {
						JSONArray array = (JSONArray) parse.get("card_product_info");
						if (array != null) {
							for (Object info : array) {
								JSONObject i = (JSONObject) info;
								if (i.get("product_id") != null && i.get("product_id").toString().equals("0002")) {
									String balance = i.get("valid_balance").toString();
									int parseInt = Integer.parseInt(balance);
									return parseInt;
								}
							}
						}

					}
				}
			}
			throw new Exception("查询失败");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	private void setNoMoney(Map<String, String> params, Map<String, Object> pp_params) {
		pp_params.put("opr_id", params.get("emp_no"));
		pp_params.put("order_id", params.get("orderNo"));
		pp_params.put("amount", "0");
		pp_params.put("discount", new BigDecimal(params.get("payAmount")).multiply(new BigDecimal(100)));
	}
	
	
// 产生充值的随机id,因为通联那边id最多10位
	@Override
	public String buildOrderId() {
		long time = new Date().getTime();
		Random random = new Random();
		int nextInt = random.nextInt(100);
		String de = time + "" + nextInt;
		Long num = new Long(de);
		return Long.toString(num, 32);
	}
		
		
		/**
		 * 判断金额是否合法
		 * 
		 * @param amount
		 * @return
		 */
		public boolean checkAmount(String amount) {
			try {
				double parseDouble = Double.parseDouble(amount);
				if (parseDouble <= 0) {
					return false;
				}
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}

		//消費
		@Override
		public boolean deal(String cardNo, String amount,String empNo,String discount) throws Exception{
			Map<String, Object> pp_params = PrePayCardHelper.buildBaseParam("allinpay.ppcs.cardpaywithoutpassword.add");
			pp_params.put("card_id", cardNo);
			pp_params.put("order_id", buildOrderId());// 交易流水号
			pp_params.put("trans_time", getTimeStr(new Date()));// 交易时间
			amount = (int) (Double.parseDouble(amount) * 100) + "";
			discount = (int) (Double.parseDouble(discount) * 100) + "";
			pp_params.put("amount", amount);
			pp_params.put("discount", discount);
			pp_params.put("pay_code", "0002");// 产品编号
			pp_params.put("mer_id", PrePayPropertiesUtil.get("mechant_id"));
			PrePayCardHelper.sign(pp_params, encode);
			String post = HttpUtil.get(PrePayPropertiesUtil.get("base_url"), pp_params, encode);
			JSONObject parse = (JSONObject) JSON.parse(post);
			if (parse != null) {
				parse = (JSONObject) parse.get("ppcs_cardinfopay_get_response");
				if (parse != null) {
					parse = (JSONObject) parse.get("bindcard_info");
					if (parse != null && parse.get("resp_code") != null) {
						Object object = parse.get("resp_code");
						if ("0000".equals(object)) {
							return true;
						} else {
							return false;
						}
					} else {
						if (post.indexOf("余额") != -1 && post.indexOf("小于") != -1) {
							throw new Exception("余额不足");
						} else {
							return false;
						}
					}
				} else {
					if (post.indexOf("余额") != -1 && post.indexOf("小于") != -1) {
						throw new Exception("余额不足");
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
	}
}
