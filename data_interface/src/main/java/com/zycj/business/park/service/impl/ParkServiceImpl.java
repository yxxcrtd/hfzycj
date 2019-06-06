package com.zycj.business.park.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import javax.transaction.Transactional;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zycj.business.http.httpclient.HttpCallBack;
import com.zycj.business.http.httpclient.HttpHandler;
import com.zycj.business.http.httpclient.RSA;
import com.zycj.business.park.dao.IParkDao;
import com.zycj.business.park.dto.DataDto;
import com.zycj.business.park.dto.PayModel;
import com.zycj.business.park.dto.PrePayParamDto;
import com.zycj.business.park.model.CommercialParkOrder;
import com.zycj.business.park.model.CommercialParkRecord;
import com.zycj.business.park.model.Park;
import com.zycj.business.park.model.PayMentRecord;
import com.zycj.business.park.model.TradeAli;
import com.zycj.business.park.model.TradeWeix;
import com.zycj.business.park.service.IParkService;
import com.zycj.business.park.util.BisStatus;
import com.zycj.business.park.util.PayConfig;
import com.zycj.business.park.util.PayType;
import com.zycj.mqtt.dto.MqttMessage;
import com.zycj.mqtt.server.MqttSendData;
import com.zycj.mqtt.util.MessageCode;
import com.zycj.mqtt.util.MqttConfig;
import com.zycj.sdk.base.dao.Page;
import com.zycj.sdk.base.service.impl.BaseServiceImpl;
import com.zycj.sdk.util.StringUtil;
import com.zycj.server.netty.dto.ChannelConnection;
import com.zycj.server.netty.handler.TcpServerHandler;
import com.zycj.server.netty.httpclient.NettyClient;
import com.zycj.server.util.NettyHttpServerConfig;
import com.zycj.server.util.RsaCodeUtils;
import com.zycj.server.util.TcpConnectionPoolUtil;
import com.zycj.server.util.ThreadMessageUtil;

import net.sf.json.JSONObject;

@Service("parkService")
public class ParkServiceImpl extends BaseServiceImpl implements IParkService {

	private final static Logger log= Logger.getLogger(ParkServiceImpl.class);
	@Autowired
	private IParkDao parkDao;

	public void saveParkRecord(CommercialParkOrder cpo) throws ParseException {
		if (!StringUtil.isEmpty(cpo.getInTime())) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date inTime = dateFormat.parse(cpo.getInTime());
			CommercialParkRecord record = this.parkDao.getCommercialParkRecord(cpo.getParkingId(), cpo.getCarNo(), inTime);
			if (record == null) {
				record = new CommercialParkRecord();
				record.setCarNo(cpo.getCarNo());
				if (!StringUtil.isEmpty(cpo.getAmount())) {
					Integer cost = Integer.parseInt(cpo.getAmount());
					record.setCost(cost / 100.0);
				}
				if (!StringUtil.isEmpty(cpo.getInTime())) {
					record.setInTime(dateFormat.parse(cpo.getInTime()));
				}
				if ("1".equals(cpo.getType())) {
					record.setOutCollectorNo(cpo.getChargeEmp());
					record.setOutCameraId(cpo.getCameraId());
					record.setOutTime(dateFormat.parse(cpo.getDriveTime()));
					record.setDataStatus(1);
					record.setIsFinish(1);
				} else {
					record.setInCollectorNo(cpo.getChargeEmp());
					record.setInCameraId(cpo.getCameraId());
					record.setDataStatus(0);
				}
				record.setInvoiceNo(cpo.getInvoiceNo());
				record.setIsPrintInvoice(cpo.getIsPrintInvoice());
				record.setParkId(cpo.getParkingId());
				record.setPayMethod(cpo.getChargeType());
				if (!StringUtil.isEmpty(cpo.getChargeTime())) {
					record.setPayTime(dateFormat.parse(cpo.getChargeTime()));
				}
				this.save(record);
			} else {
				if (!StringUtil.isEmpty(cpo.getAmount())) {
					Integer cost = Integer.parseInt(cpo.getAmount());
					record.setCost(cost / 100.0);
				}
				if ("1".equals(cpo.getType())) {
					record.setOutCollectorNo(cpo.getChargeEmp());
					record.setOutCameraId(cpo.getCameraId());
					record.setOutTime(dateFormat.parse(cpo.getDriveTime()));
					if (record.getDataStatus().equals(0)) {
						record.setDataStatus(2);
					} else {
						record.setDataStatus(1);
					}
					record.setIsFinish(1);
				} else {
					record.setInCollectorNo(cpo.getChargeEmp());
					record.setInCameraId(cpo.getCameraId());
					if (record.getDataStatus().equals(1)) {
						record.setDataStatus(2);
					} else {
						record.setDataStatus(0);
					}
				}
				record.setInvoiceNo(cpo.getInvoiceNo());
				record.setIsPrintInvoice(cpo.getIsPrintInvoice());
				record.setPayMethod(cpo.getChargeType());
				if (!StringUtil.isEmpty(cpo.getChargeTime())) {
					record.setPayTime(dateFormat.parse(cpo.getChargeTime()));
				}
				this.saveOrUpdate(record);
			}
		}
	}

	public List<Park> findPark() {
		return this.parkDao.findPark();
	}

	public void findWaitCostRecord(Page page, String carNo, Integer carType) {
		this.parkDao.findWaitCostRecord(page, carNo, carType);
	}

	@Transactional
	public void wxNotify(SortedMap<String, String> parameters) throws Exception {
		// 商户订单号
		String out_trade_no = MapUtils.getString(parameters, "out_trade_no", null);
		// 交易客户端类型
		String app = MapUtils.getString(parameters, "app", null);
		TradeWeix tw = this.parkDao.getTradeWeix(out_trade_no);
		if (tw == null) {
			throw new Exception("微信交易记录[" + tw.getTradeNo() + "]不存在");
		}
		if (tw.getBisStatus().intValue() != BisStatus.UNPAY.getIndex()) {
			throw new Exception("微信交易记录[" + out_trade_no + "]已经被处理，当前状态为：" + BisStatus.getName(tw.getBisStatus()));
		}
		tw.setBisStatus(BisStatus.PAY.getIndex());
		tw.setFinishTime(new Date());
		this.saveOrUpdate(tw);

		// 把支付完成通知红门停车场
		this.sendPark(tw, out_trade_no,PayType.WECHAT.getIndex(),app);
	}

	@Transactional
	public void aliNotify(Map<String, String> params) throws Exception {
		// 商户订单号
		String out_trade_no = MapUtils.getString(params, "out_trade_no", null);
		// 交易状态
		String trade_status = MapUtils.getString(params, "trade_status", null);
		// 交易客户端类型
		String app = MapUtils.getString(params, "app", null);
		Entry<String,String> entry = null;
		Iterator<Entry<String,String>> paramIt = params.entrySet().iterator();
		while(paramIt.hasNext()){
			entry = paramIt.next();
			log.info(entry.getKey() + "----" + entry.getValue());
		}
		if (this.validateNotify(params)) {// 验证成功
			if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
				try {
					// 商户订单号
					TradeAli ali = this.parkDao.getTradeAli(out_trade_no);
					if (ali == null) {
						throw new Exception("【商业停车场】支付宝交易记录[" + out_trade_no + "]不存在");
					}
					if (ali.getBisStatus().intValue() != BisStatus.UNPAY.getIndex()) {
						throw new Exception("【商业停车场】支付宝交易记录[" + out_trade_no + "]已经被处理，当前状态为：" + BisStatus.getName(ali.getBisStatus()));
					}
					ali.setBisStatus(BisStatus.PAY.getIndex());
					ali.setFinishTime(new Date());
					this.saveOrUpdate(ali);
					// 把支付完成通知红门停车场
					this.sendPark(ali, out_trade_no,PayType.ALIPAY.getIndex(),app);
					log.warn("支付宝异步通知支付成功，tcc_pay处理成功");
				} catch (Exception e) {
					log.warn("支付宝异步通知支付成功，tcc_pay处理异常TradeNo[" + out_trade_no + "],请人工检查处理", e);
				}
			}
		}
	}

	/**
	 * 获取远程服务器ATN结果,验证返回URL
	 * 
	 * @param notify_id
	 *            通知校验ID
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 * @throws Exception
	 */
	private String verifyResponse(String notify_id) throws Exception {
		// 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
		String partner = PayConfig.getAliPartner();
		String veryfy_url = PayConfig.getAlipayVerifyUrl() + "partner=" + partner + "&notify_id=" + notify_id;
		return HttpHandler.get(veryfy_url, null, new HttpCallBack<String>() {
			public String todo(String result) throws Exception {
				return result;
			}
		});
	}

	private boolean validateNotify(Map<String, String> params) throws Exception {
		// 判断responsetTxt是否为true，isSign是否为true
		// responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		// isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String responseTxt = "true";
		if (params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id);
		}
//		String sign = "";
//		if (params.get("sign") != null) {
//			sign = params.get("sign");
//		}
//		log.info("%%%%%%%%%%%%%%%%3--" + sign);
//		boolean isSign = getSignVeryfy(params, sign);
//		log.info("%%%%%%%%%%%%%%%%4");
//		// 写日志记录（若要调试，请取消下面两行注释）
//		String sWord = "responseTxt=" + responseTxt + "<<>>isSign=" + isSign + "<<>>返回回来的参数：" + createLinkString(params);
//		log.info("alipay>>>>>4......" + sWord);
//		log.info("%%%%%%%%%%%%%%%%" + isSign + "," + responseTxt);
//		if (isSign && "true".equals(responseTxt)) {
		if ("true".equals(responseTxt)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据反馈回来的信息，生成签名结果
	 * 
	 * @param Params
	 *            通知返回来的参数数组
	 * @param sign
	 *            比对的签名结果
	 * @return 生成的签名结果
	 */
	private boolean getSignVeryfy(Map<String, String> Params, String sign) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = paraFilter(Params);
		log.info("alipay>>>>>2......" + sParaNew);
		// 获取待签名字符串
		String preSignStr = createLinkString(sParaNew);
		log.info("alipay>>>>>3......" + preSignStr);
		// 获得签名验证结果
		return RSA.verify(preSignStr, sign, PayConfig.getAliPublicKey(), "gbk");
	}

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public Map<String, String> paraFilter(Map<String, String> sArray) {
		Map<String, String> result = new HashMap<String, String>();
		if (sArray == null || sArray.size() <= 0) {
			return result;
		}
		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}
	
	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	/**
	 * @description 把商业停车场的支付完成记录通知商业停车场
	 * @author fengya
	 * @date 2016-8-5 下午06:01:05
	 * @param payType
	 * @param out_trade_no
	 * @throws Exception
	 * @return void
	 */
	private void sendPark(PayModel model, String out_trade_no,Integer payType,String app) throws Exception {
		if (!StringUtils.isEmpty(model.getBisId())) {
			CommercialParkRecord record = this.get(CommercialParkRecord.class, model.getBisId());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			PrePayParamDto params = new PrePayParamDto();
			params.setCarNo(record.getCarNo());
			params.setInTime(dateFormat.format(record.getInTime()));
			params.setOrderId(String.valueOf(record.getId()));
			params.setPayAmount(String.valueOf((int) (model.getTotalFee().doubleValue() * 100)));
			params.setPayTime(dateFormat.format(model.getFinishTime()));
			long currentDate = new Date().getTime();
			params.setPayType("1");
			ChannelConnection conn = TcpConnectionPoolUtil.getChannelConnection(record.getParkId());
			if (conn == null) {
				throw new Exception("无法获得该停车场的数据连接通道【" + record.getParkId() + "】");
			}
			Map<String, Object> reqParam = new HashMap<String, Object>();
			reqParam.put("code", "20003");
			reqParam.put("data", JSONObject.fromObject(params).toString());
			reqParam.put("queryId", String.valueOf(currentDate));
			try {
				conn.getChannel().writeAndFlush(TcpServerHandler.om.writeValueAsString(reqParam));
				conn.getChannel().writeAndFlush("\n");
			} catch (Exception e) {
				throw new Exception("对商业停车场进行支付通知时，出现了异常！orderNo【" + record.getId() + "】");
			}
			ThreadMessageUtil.push(currentDate, null);
			Object dataResult = null;
			for (int i = 0; i < ThreadMessageUtil.WAIT_TIME; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				dataResult = ThreadMessageUtil.getValue(currentDate);
				if (dataResult != null) {
					break;
				}
			}
			if (dataResult == null) {
				throw new Exception("当前可能是网络不正常，无法获取停车场响应的数据！orderNo【" + record.getId() + "】");
			}
			JSONObject jsonData = (JSONObject) dataResult;
			if (!"success".equals(jsonData.getString("message"))) {
				throw new Exception("商业停车场进行支付通知时，商业停车场给予失败处理！orderNo【" + record.getId() + "】");
			}
			
			PayMentRecord pay = new PayMentRecord();
			pay.setOrderId(record.getId());
			pay.setPayTime(model.getFinishTime());
			pay.setRealPay(model.getTotalFee());
			pay.setPayType(payType);
			this.saveOrUpdate(pay);
			record.setIsFinish(1);
			this.saveOrUpdate(record);
			
			MqttMessage message = new MqttMessage();
			message.setBusinessID(String.valueOf(new Date().getTime()));
			message.setSendTopic(MqttConfig.getClientId());
			message.setCode(MessageCode.PAY_FINISH_REMIND.getCode());
			message.setData(JSONObject.fromObject(params).toString());
			try {
				MqttMessage result = MqttSendData.sendData(app + record.getCarNo(), message);
				if(result == null){
					throw new Exception("商业停车场缴费完成后，提醒给客户端后，无法获得响应结果数据！【" + out_trade_no + "】");
				}
			} catch (Exception e) {
				throw new Exception("商业停车场缴费完成后，提醒给客户端出现异常！【" + out_trade_no + "】");
			}
		} else {
			throw new Exception("商业停车场缴费对应的业务ID为空！【" + out_trade_no + "】");
		}
	}

	public void findCostRecord(Page page, String carNo, Integer carType) {
		this.parkDao.findCostRecord(page, carNo, carType);
	}
	
	public List<PayMentRecord> findPayMentRecord(Integer orderId) {
		return this.parkDao.findPayMentRecord(orderId);
	}

	/**
	 * 	判断订单种类，占道停车还是商业停车，
	 * 		占道停车时更改order_info表order_flow状态，添加payment_record一条缴费记录，通知pos端车辆支付成功.  与ktc-0055接口逻辑类似
	 * 		商业停车场 调用sendPark方法
	 */
	public JSONObject finishPayedWithMobile(Map<String, String> params) {
		
		// 初始化响应
		DataDto result = new DataDto(false, "0000", "未知错误！", "未知错误!");
		// 订单类型，占道或者商业停车场
		String dataType = MapUtils.getString(params, "data_type", null);
		// 实收金额 
		String reallAmount = MapUtils.getString(params, "reall_amount", null);;
		// 商户订单号
		String outTradeNo = MapUtils.getString(params, "out_trade_no", null);
		// 交易状态
		String tradeStatus = MapUtils.getString(params, "trade_status", null);
		// 车辆流水表id
		int orderId = MapUtils.getIntValue(params, "order_id");
		// 交易客户端类型
		String app = MapUtils.getString(params, "app", null);
		// 支付类型
		int payType = MapUtils.getIntValue(params, "pay_type");
		// 车牌号
		String carNo = MapUtils.getString(params, "car_no");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 驶出时间
		String outTime = dateFormat.format(new Date());
		int feeType = 11;	// 默认 11 ，表示自主缴费
		
		
		String paymentType = payType == 1 ? "8" : "7"; // TODO 7 微信 ；8 支付宝
		double payAmount = 0.00 ;
		
		if (reallAmount != null) {
			payAmount = Double.parseDouble(reallAmount);
		} 
		if (payAmount < 0) {
			result.setData("缴费金额不合法!");
			result.setMsg("缴费金额不合法!");
		} 
		
		if ("0".equals(dataType)) {
			// 占道停车, 调用KtcInterfaceService 0055接口
			String data = finishParkOrder(orderId, String.valueOf(payAmount), paymentType, outTime) ;
			if (data != null && !"0000".equals(data)) {
				result.setCode("8888");
				result.setSuccess(true);
				result.setData(data);

			} else {
				result.setData(data);
			}
		} else if ("1".equals(dataType)) {
			//商业停车场
			CommercialParkRecord record = this.get(CommercialParkRecord.class, orderId);
			try {
				finishCommercialOrder(record, payAmount, String.valueOf(payType));
				result.setCode("8888");
				result.setSuccess(true);
				result.setData("缴费成功");
				result.setMsg("缴费成功");
				savePaymentRecord(orderId, payAmount, payType, carNo, feeType);
			} catch (Exception e) {
				result.setData(e.getMessage());
			}
		} else {
			// 返回未知的停车类型错误
			result.setData("不明确的停车类型！");
			result.setMsg("不明确的停车类型！");
		}


		// TODO 添加 支付记录（2张表，trade_weix, trade_ali）



		
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 保存支付记录
	 * @param orderId
	 * @param payAmount
	 * @param payType
	 */
	private void savePaymentRecord(int orderId, double payAmount, int payType, String carNo, int feeType) {
		PayMentRecord pay = new PayMentRecord();
		pay.setOrderId(orderId);
		pay.setPayTime(new Date());
		pay.setRealPay(new BigDecimal(payAmount).setScale(2, RoundingMode.CEILING));
		pay.setPayType(payType);
		pay.setCarNo(carNo);
		pay.setChargeEmp("mobile");
		pay.setEmpName("mobile");
		pay.setFeeType(feeType);
		this.saveOrUpdate(pay);
	}
	
	/**
	 * 结束商业停车场订单
	 * @param payType
	 * @throws Exception 
	 */
	private void finishCommercialOrder(CommercialParkRecord record, double totalFee, String payType) throws Exception {
		record.setIsFinish(1);
		this.saveOrUpdate(record);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PrePayParamDto params = new PrePayParamDto();
		params.setCarNo(record.getCarNo());
		params.setInTime(dateFormat.format(record.getInTime()));
		params.setOrderId(String.valueOf(record.getId()));
		params.setPayAmount(String.valueOf((int) (totalFee * 100)));
		params.setPayTime(dateFormat.format(new Date()));
		params.setPayType(payType);		// 1-微信，2-支付宝
		
		if (totalFee > 0) {
			// 实际产生费用后，才向停车场系统发送缴费信息
			sendMessageToCommercialPark(record, params, totalFee);
		}
		// 向手机端发送缴费成功通知
		sendMessageToMobile(params, "app", record.getCarNo());
	}
	
	private String finishParkOrder(int orderId, String payAmount, String outType, String outTime) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.putAll(com.zycj.server.util.MapUtils.getPublicMap("0055"));
		param.put("order_id", String.valueOf(orderId));
		param.put("reall_amount", payAmount);
		param.put("out_type", outType);
		param.put("data_type", "1");
		param.put("out_time", outTime);
		String rsaData;
		try {
			rsaData = RsaCodeUtils.encryptDataByRSA64(com.zycj.server.util.MapUtils.getParamesStr(param));
			NettyClient nc = new NettyClient(NettyHttpServerConfig.getNetty_ip(), NettyHttpServerConfig.getNetty_port());
			nc.request(rsaData);
			return nc.getResponseResult();
		} catch (Exception e) {
			log.error("请求0055接口出现异常。",e);
			return null;
		}
	}
	
	/**
	 * 向移动端发送通知
	 * @throws Exception 
	 */
	private void sendMessageToMobile(PrePayParamDto params, String app, String carNo) throws Exception {
		MqttMessage message = new MqttMessage();
		message.setBusinessID(String.valueOf(new Date().getTime()));
		message.setSendTopic(MqttConfig.getClientId());
		message.setCode(MessageCode.PAY_FINISH_REMIND.getCode());
		message.setData(JSONObject.fromObject(params).toString());
		try {
			MqttMessage result = MqttSendData.sendData(app + carNo, message);
			if(result == null){
				throw new Exception("商业停车场缴费完成后，提醒给客户端后，无法获得响应结果数据！【" + carNo + "】");
			}
		} catch (Exception e) {
			throw new Exception("商业停车场缴费完成后，提醒给客户端出现异常！【" + carNo + "】");
		}
	}
	
	
	/**
	 * 向商业停车场系统发送缴费通知
	 * @param record
	 * @param params
	 * @param totalFee
	 * @throws Exception
	 */
	private void sendMessageToCommercialPark(CommercialParkRecord record, PrePayParamDto params, double totalFee) throws Exception {
		long currentDate = new Date().getTime();
		ChannelConnection conn = TcpConnectionPoolUtil.getChannelConnection(record.getParkId());
		if (conn == null) {
			throw new Exception("无法获得该停车场的数据连接通道【" + record.getParkId() + "】");
		}
		Map<String, Object> reqParam = new HashMap<String, Object>();
		reqParam.put("code", "20003");
		reqParam.put("data", JSONObject.fromObject(params).toString());
		reqParam.put("queryId", String.valueOf(currentDate));
		try {
			conn.getChannel().writeAndFlush(TcpServerHandler.om.writeValueAsString(reqParam));
			conn.getChannel().writeAndFlush("\n");
		} catch (Exception e) {
			throw new Exception("对商业停车场进行支付通知时，出现了异常！orderNo【" + record.getId() + "】");
		}
		ThreadMessageUtil.push(currentDate, null);
		Object dataResult = null;
		for (int i = 0; i < ThreadMessageUtil.WAIT_TIME; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			dataResult = ThreadMessageUtil.getValue(currentDate);
			if (dataResult != null) {
				break;
			}
		}
		if (dataResult == null) {
			throw new Exception("当前可能是网络不正常，无法获取停车场响应的数据！orderNo【" + record.getId() + "】");
		}
		JSONObject jsonData = (JSONObject) dataResult;
		if (!"success".equals(jsonData.getString("message"))) {
			throw new Exception("商业停车场进行支付通知时，商业停车场给予失败处理！orderNo【" + record.getId() + "】");
		}
	}

}
