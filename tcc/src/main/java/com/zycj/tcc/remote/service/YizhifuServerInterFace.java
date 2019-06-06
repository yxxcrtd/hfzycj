package com.zycj.tcc.remote.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.server.annotation.ServiceClass;
import com.zycj.tcc.server.annotation.ServiceMethod;
import com.zycj.tcc.server.util.HttpUtil;
import com.zycj.tcc.server.util.MD5Util;
import com.zycj.tcc.server.util.YzfPropertiesUtil;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.vo.PlaceOrderVo;

/**
 * 提供给翼支付的接口,包括下單，沖正
 * @author xiechanglei
 */
@ServiceClass
@Service
public class YizhifuServerInterFace {
	private final static Logger log = Logger.getLogger(YizhifuServerInterFace.class); 
	
	@ServiceMethod(type="0040",note="翼支付下單")
	public Response createOrder(Map<String, String> params){
		String amt = params.get("orderAmt"); //翼支付的用户账号
		double parseDouble = Double.parseDouble(amt);
		parseDouble = parseDouble * 100;
		int ammount = (int) parseDouble;
		String orderNo = params.get("orderNo");//订单号
		String barcode = params.get("barcode");//翼支付的用户二维码
		String orderReqNo = params.get("orderReqNo");//订单流水号
		//
		String orderDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String queryDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Map<String, Object> post_data = new HashMap<String, Object>();
		post_data.put("merchantId", YzfPropertiesUtil.get("merchantId"));
		post_data.put("barcode",barcode);
		post_data.put("orderNo",orderNo+orderDate);
		post_data.put("orderReqNo", orderReqNo+orderDate);
		post_data.put("channel", YzfPropertiesUtil.get("channel"));
		post_data.put("busiType",YzfPropertiesUtil.get("busiType"));
		post_data.put("orderDate", orderDate);
		post_data.put("orderAmt", ammount);
		post_data.put("productAmt", ammount);
		post_data.put("attachAmt", "0");
		post_data.put("goodsName", YzfPropertiesUtil.get("goodsName"));
		post_data.put("backUrl", YzfPropertiesUtil.get("backUrl"));
		post_data.put("storeId", "9527");//该字段不能为空
		//校验码
		String mac_place = "MERCHANTID="+post_data.get("merchantId")+"&ORDERNO="+post_data.get("orderNo")+"&ORDERREQNO="+post_data.get("orderReqNo")+"&ORDERDATE="+orderDate+"&BARCODE="+post_data.get("barcode")+"&ORDERAMT="+post_data.get("orderAmt")+"&KEY="+YzfPropertiesUtil.get("key");
		
		String mac_query = "MERCHANTID="+post_data.get("merchantId")+"&ORDERNO="+post_data.get("orderNo")+"&ORDERREQNO="+post_data.get("orderReqNo")+"&ORDERDATE="+queryDate+"&KEY="+YzfPropertiesUtil.get("key");
		post_data.put("mac", MD5Util.Md532(mac_place).toUpperCase());
		
		String result = HttpUtil.postHttps(YzfPropertiesUtil.get("placeUrl"), post_data, "UTF-8");
		log.info(result);
		
		PlaceOrderVo json = JSON.parseObject(result, PlaceOrderVo.class);
		if(json!=null && json.getResult()!=null){
			json.getResult().setMac(MD5Util.Md532(mac_query).toUpperCase());
			json.getResult().setOrderDate(queryDate);
		}
		
		
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, json);
	}
	
	@ServiceMethod(type="0041",note="翼支付冲正")
	public Response chongZheng(Map<String, String> params){
		Map<String, Object> post_data = new HashMap<String, Object>();
		post_data.put("merchantId", YzfPropertiesUtil.get("merchantId"));
		post_data.put("merchantPwd", YzfPropertiesUtil.get("password"));
		post_data.put("oldOrderNo", params.get("orderNo"));//订单号
		post_data.put("oldOrderReqNo", params.get("orderReqNo"));//订单
		String amt = params.get("orderAmt");
		double parseDouble = Double.parseDouble(amt);
		parseDouble = parseDouble * 100;
		int ammount = (int) parseDouble;
		post_data.put("transAmt", ammount);//订单金额
		post_data.put("refundReqNo", params.get("orderReqNo_e"));
		post_data.put("refundReqDate", new SimpleDateFormat("yyyyMMdd").format(new Date()));
		post_data.put("channel", YzfPropertiesUtil.get("channel"));
		
		String mac_source = "MERCHANTID="+post_data.get("merchantId")+"&MERCHANTPWD="+post_data.get("merchantPwd")+"&OLDORDERNO="+post_data.get("oldOrderNo")+"&OLDORDERREQNO="+post_data.get("oldOrderReqNo")+"&REFUNDREQNO="+post_data.get("refundReqNo")+"&REFUNDREQDATE="+post_data.get("refundReqDate")+"&TRANSAMT="+post_data.get("transAmt")+"&KEY="+YzfPropertiesUtil.get("key");
		String mac = MD5Util.Md532(mac_source);
		post_data.put("mac",mac);
		String result = HttpUtil.postHttps(YzfPropertiesUtil.get("chengzhengUrl"),post_data, "UTF-8");
		PlaceOrderVo json = JSON.parseObject(result, PlaceOrderVo.class);
		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, json);
	}
}
