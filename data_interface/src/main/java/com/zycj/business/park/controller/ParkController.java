package com.zycj.business.park.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zycj.business.park.dto.CostDto;
import com.zycj.business.park.dto.DataDto;
import com.zycj.business.park.model.CommercialParkRecord;
import com.zycj.business.park.model.OrderInfo;
import com.zycj.business.park.model.Park;
import com.zycj.business.park.model.PayMentRecord;
import com.zycj.business.park.service.IParkService;
import com.zycj.business.park.util.HtmlUtil;
import com.zycj.business.park.util.MD5;
import com.zycj.business.park.util.PayConfig;
import com.zycj.business.park.util.XmlUtil;
import com.zycj.framework.cache.InitCacheData;
import com.zycj.framework.cache.InitCacheData.CacheType;
import com.zycj.sdk.base.dao.Page;
import com.zycj.sdk.util.DateJsonValueProcessor;
import com.zycj.sdk.util.StringUtil;
import com.zycj.server.netty.dto.ChannelConnection;
import com.zycj.server.netty.dto.ParkCost;
import com.zycj.server.netty.handler.TcpServerHandler;
import com.zycj.server.netty.httpclient.NettyClient;
import com.zycj.server.util.MapUtils;
import com.zycj.server.util.NettyHttpServerConfig;
import com.zycj.server.util.RsaCodeUtils;
import com.zycj.server.util.TcpConnectionPoolUtil;
import com.zycj.server.util.ThreadMessageUtil;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@RequestMapping("/datainterface/park")
@Controller
public class ParkController {

	private static final Logger LOG = Logger.getLogger(ParkController.class);
	@Autowired
	private IParkService parkService;

	/**
	 * @description 获得缴费列表
	 * @author fengya
	 * @date 2016-8-1 上午09:46:18
	 * @param carNo
	 *            车牌号
	 * @param carType 车类型：1小型车，2大型车
	 * @param dataStatus
	 *            0：当前待缴费列表，1：缴费历史列表
	 * @return
	 * @throws IOException
	 * @return JSONObject
	 */
	@RequestMapping("/findCostList")
	@ResponseBody
	public JSONObject findCostList(String carNo, int carType, int dataStatus, Page page) {
		LOG.info("carNo:" + carNo + ", carType:" + carType);
		DataDto result = new DataDto();
		if (!StringUtil.isEmpty(carNo)) {
			if (dataStatus == 0) {
				this.parkService.findWaitCostRecord(page, carNo, carType);
				result.setCode("8888");
				result.setSuccess(true);
				result.setData(page.getResult());
				Map<String, Object> pageData = new HashMap<String, Object>();
				pageData.put("totalPages", page.getTotalPages());
				pageData.put("totalSize", page.getTotalCount());
				result.setPage(pageData);
			} else {
				this.parkService.findCostRecord(page, carNo, carType);
				result.setSuccess(true);
				result.setCode("8888");
				result.setData(page.getResult());
				Map<String, Object> pageData = new HashMap<String, Object>();
				pageData.put("totalSize", page.getTotalCount());
				pageData.put("totalPages", page.getTotalPages());
				result.setPage(pageData);
			}
		} else {
			result.setSuccess(false);
			result.setCode("0000");
			result.setData("车牌号不能为空！");
		}
		JsonConfig config = new JsonConfig();
		DateJsonValueProcessor processor = new DateJsonValueProcessor();
		config.registerJsonValueProcessor(Date.class, processor);
		return JSONObject.fromObject(result, config);
	}
	
	
	@RequestMapping(value = "/finishPayed", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject finishPayed(@RequestParam Map<String, String> params) {
		return parkService.finishPayedWithMobile(params);
	}

	/**
	 * @description 获取订单详情
	 * @author fengya
	 * @date 2016-8-17 下午02:13:12
	 * @param id 订单编号
	 * @param dataType 订单类型：0：路边停车场，1：商业停车场
	 * @param dataStatus 数据状态：0：当前待缴费订单，1：历史缴费订单
	 * @return
	 * @throws ParseException
	 * @return JSONObject
	 */
	@RequestMapping("/getCost")
	@ResponseBody
	public JSONObject getCost(int id, int dataType,int dataStatus) throws ParseException {
		DataDto result = new DataDto();
		CostDto cost = null;
		// 从路边停车中获得缴费信息
		OrderInfo oi = this.parkService.get(OrderInfo.class, id);
		if(dataStatus == 0){
			if (dataType == 0) {
				if (oi == null) {
					LOG.error("向路边停车场获取缴费金额失败，原因找不到对应订单记录数据【" + id + "】");
					result.setSuccess(false);
					result.setCode("0000");
					result.setData("找不到对应的数据记录！");
					return JSONObject.fromObject(result);
				}
				String costResult = this.requestWaitCost(oi.getCarNo(), oi.getSpaceNo(), String.valueOf(oi.getId()));
				if(StringUtil.isEmpty(costResult)){
					LOG.error("向路边停车场获取缴费金额失败，原因请求出现了异常，订单记录数据【" + id + "】");
					result.setSuccess(false);
					result.setCode("0000");
					result.setData("原因请求出现了异常！");
					return JSONObject.fromObject(result);
				}
				JSONObject jsonObjec = JSONObject.fromObject(costResult);
				String resultCode = jsonObjec.getString("resultCode");
				if ("8888".equals(resultCode)) {
					cost = new CostDto();
					String data = jsonObjec.getString("data");
					JSONObject jsonData = JSONObject.fromObject(data);
					cost.setAmount(Double.parseDouble(jsonData.getString("amount")));
					cost.setArrearAmount(Double.parseDouble(jsonData.getString("arrearAmount")));
					cost.setSpaceNo(jsonData.getString("spaceNo"));
					cost.setCarNo(oi.getCarNo());
					cost.setDataType(dataType);
					cost.setId(id);
					cost.setInTime(oi.getInTime());
					cost.setParkName(oi.getSectionName());
//					return JSONObject.fromObject(result, config);
				} else {
					LOG.error("向路边停车场获取缴费金额失败，原因返回结果出现异常，订单编号【" + id + "】");
					result.setSuccess(false);
					result.setCode("0000");
					result.setData(resultCode);
					return JSONObject.fromObject(result);
				}
			} else {
				// 从商业停车场获得缴费记录
				CommercialParkRecord record = this.parkService.get(CommercialParkRecord.class, id);
				if (record == null) {
					LOG.error("向商业停车场获取缴费金额失败，原因找不到对应订单记录数据【" + id + "】");
					result.setSuccess(false);
					result.setCode("0000");
					result.setData("找不到对应的数据记录！");
					return JSONObject.fromObject(result);
				}
				ChannelConnection conn = null;
				long currentDate = 0;
				// 商业停车场收费
				if (!StringUtil.isEmpty(record.getParkId()) && !record.getDataStatus().equals(2)) {
					conn = TcpConnectionPoolUtil.getChannelConnection(record.getParkId());
					if (conn == null) {
						LOG.error("向商业停车场获取缴费金额请求失败,当前可能是网络不正常，连接不到对应的停车场,订单ID：【" + id + "】！");
						result.setSuccess(false);
						result.setCode("0000");
						result.setData("当前可能是网络不正常，连接不到对应的停车场！");
						return JSONObject.fromObject(result);
					}
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("code", "20002");
					Map<String, Object> info = new HashMap<String, Object>();
					info.put("carNo", record.getCarNo());
					currentDate = new Date().getTime();
					info.put("orderId", String.valueOf(record.getId()));
					params.put("queryId", String.valueOf(currentDate));
					params.put("data", JSONObject.fromObject(info).toString());
					try {
						conn.getChannel().writeAndFlush(TcpServerHandler.om.writeValueAsString(params));
						conn.getChannel().writeAndFlush("\n");
					} catch (Exception e) {
						LOG.error("向商业停车场获取缴费金额请求失败,当前可能是网络不正常，连接停车场服务器出现了错误，订单ID：【" + id + "】！", e);
						result.setSuccess(false);
						result.setCode("0000");
						result.setData("当前可能是网络不正常，连接停车场服务器出现了错误！");
						return JSONObject.fromObject(result);
					}
					ThreadMessageUtil.push(currentDate, null);
					Object dataResult = null;
					for (int i = 0; i < ThreadMessageUtil.WAIT_TIME; i++) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						dataResult = ThreadMessageUtil.getValue(currentDate);
						if (dataResult != null) {
							break;
						}
					}
					if (dataResult == null) {
						LOG.error("向商业停车场获取缴费金额请求失败,返回结果为null，订单ID：【" + id + "】！");
						result.setSuccess(false);
						result.setCode("0000");
						result.setData("当前可能是网络不正常，无法获取停车场响应的数据！");
						return JSONObject.fromObject(result);
					}
					ParkCost parkCost = (ParkCost) dataResult;
					if ("failed".equals(parkCost.getSuccess())) {
						LOG.error("向商业停车场获取缴费金额请求失败,返回结果为【failed】，订单ID：【" + id + "】！");
						result.setSuccess(false);
						result.setCode("0000");
						result.setData(parkCost.getMessage());
						return JSONObject.fromObject(result);
					}
					cost = new CostDto();
					Object obj = InitCacheData.getInstance().getCacheData(CacheType.PARK).get(record.getParkId());
					if (obj != null) {
						cost.setParkName(((Park) obj).getParkName());
					}
					cost.setAmount(Integer.parseInt(parkCost.getFeeTotal()) / 100.0);
//					record.setCost(Integer.parseInt(parkCost.getFeeTotal()) / 100.0);
					cost.setDataType(1);
					cost.setCarNo(parkCost.getCarNo());
					cost.setId(record.getId());
					if (!StringUtil.isEmpty(parkCost.getInTime())) {
						cost.setInTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parkCost.getInTime()));
					}
				} else {
					result.setSuccess(false);
					result.setCode("0000");
					result.setData("该记录已经过期，不再欠费！");
					return JSONObject.fromObject(result);
				}
			}
		} else {
			cost = new CostDto();
			cost.setSpaceNo(oi.getSpaceNo());
			cost.setOutTime(oi.getOutTime());
		}
		// 获取该订单之前缴费的记录数据
		List<PayMentRecord> payList = this.parkService.findPayMentRecord(id);
		double payTotal = 0;
		for(PayMentRecord pay : payList){
			payTotal += pay.getRealPay().doubleValue();
		}
		if(payList.size() >= PayConfig.getPayTimes()){
			cost.setBreakPay(true);
		}
		cost.setPayRecord(payList);
		cost.setPayTotal(payTotal);
		result.setSuccess(true);
		result.setCode("8888");
		result.setData(cost);
		JsonConfig config = new JsonConfig();
		DateJsonValueProcessor processor = new DateJsonValueProcessor();
		config.registerJsonValueProcessor(Date.class, processor);
		return JSONObject.fromObject(result, config);
	}

	private String requestWaitCost(String carNo, String spaceNo, String orderNo) {
		HashMap<String, String> parms = new HashMap<String, String>();
		parms.putAll(MapUtils.getPublicMap("0005"));
		parms.put("car_no", carNo);
		parms.put("space_no", spaceNo);
		parms.put("order_no", orderNo);
		String rsaData;
		try {
			rsaData = RsaCodeUtils.encryptDataByRSA64(MapUtils.getParamesStr(parms));
			NettyClient nc = new NettyClient(NettyHttpServerConfig.getNetty_ip(), NettyHttpServerConfig.getNetty_port());
			nc.request(rsaData);
			return nc.getResponseResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/wxNotify")
	@ResponseBody
	public void wxNotify(HttpServletRequest req, HttpServletResponse resp) {
		String result = "FAIL";
		try {
			String requestBody = getPostData(req, "UTF-8");
			SortedMap<String, String> parameters=XmlUtil.decodeXmlForSort(requestBody);
			if(this.validateNotify(parameters)){
				//判断签名及结果
				if ("SUCCESS".equals(parameters.get("return_code"))&&"SUCCESS".equals(parameters.get("result_code"))) {
					String out_trade_no = org.apache.commons.collections.MapUtils.getString(parameters, "out_trade_no", null);
					try {
						this.parkService.wxNotify(parameters);
					} catch (Exception e) {
						LOG.warn("【商业停车场】微信异步通知支付成功，tcc_pay处理异常TradeNo[" + out_trade_no + "],请人工检查处理", e);
					}
				} else {
					LOG.warn("【商业停车场】微信支付失败");
				}
			result="SUCCESS";
		}
		} catch (Exception e) {
			LOG.warn("【商业停车场】处理微信异步通知异常", e);
		}
		HtmlUtil.writer(resp, "<xml><return_code>" + result + "</return_code></xml>");
	}
	
	@RequestMapping("/aliNotify")
	@ResponseBody
	public void aliNotify(HttpServletRequest req, HttpServletResponse resp) {
		String result = "FAIL";
		LOG.warn("【商业停车场】支付宝支付开始");
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map<String,String[]> requestParams = req.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = iter.next();
				String[] values = requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}
			this.parkService.aliNotify(params);
			result="success";
		} catch (Exception e) {
			LOG.warn("【商业停车场】处理支付宝异步通知异常", e);
		}
		HtmlUtil.writer(resp, "<xml><return_code>" + result + "</return_code></xml>");
	}
	
	private boolean validateNotify(SortedMap<String, String> parameters) {
		StringBuffer sb = new StringBuffer();
		Iterator<Entry<String,String>> it = parameters.entrySet().iterator();
		Entry<String,String> entry = null;
		while (it.hasNext()) {
			entry = it.next();
			if (null != entry.getValue() && !"".equals(entry.getValue()) && !"sign".equals(entry.getKey()) && !"key".equals(entry.getKey())) {
				sb.append(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		sb.append("key=" + PayConfig.getWxPartnerKey());
		// 算出摘要
		String sign="";
		try {
			sign = MD5.getMessageDigest(sb.toString().getBytes("UTF-8")).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		String tenpaySign = org.apache.commons.collections.MapUtils.getString(parameters, "sign", "");
		LOG.info("tenpaySign:"+tenpaySign+"<<>>sign:"+sign);
		return tenpaySign.equals(sign);
	}

	/**
	 * 获取request中postData中的数据
	 * 
	 * @param req
	 * @return
	 */
	public String getPostData(HttpServletRequest req, String character) {
		InputStream is = null;
		try {
			is = req.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, StringUtils.isBlank(character) ? "UTF-8" : character));
			// 读取HTTP请求内容
			String buffer = null;
			StringBuffer sb = new StringBuffer();
			while ((buffer = br.readLine()) != null) {
				sb.append(buffer);
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
				is = null;
			}
		}
	}

}
