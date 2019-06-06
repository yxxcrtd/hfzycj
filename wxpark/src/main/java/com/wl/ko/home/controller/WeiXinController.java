package com.wl.ko.home.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.wl.base.controller.BaseController;
import com.wl.base.model.ReturnMessage;
import com.wl.base.service.IBaseService;
import com.wl.common.Constants;
import com.wl.common.utils.SystemConfigUtil;
import com.wl.ko.home.model.AccesstokenInfo;
import com.wl.ko.home.model.ParkVo;
import com.wl.ko.home.model.ParkVoDetail;
import com.wl.ko.home.model.UserWeiXin;
import com.wl.ko.home.service.IWeiXinService;
import com.wxpay.config.WxPayConfig;
import com.wxpay.util.UnifiedorderService;
import com.wxpay.util.WxPaySendData;
import com.wxpay.util.WxSign;
import com.wxpay.util.XMLUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/weixin")
public class WeiXinController {

    /** Log */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	private IBaseService baseService;

	@Autowired
	private IWeiXinService weiXinService;

	/***
	 * 通过open_id来查询出所有车辆信息
	 * 
	 * @param paramObj
	 * @param pageno 当前页
	 * @param device_type 渠道
	 * @param dataStatus 0 表示立即缴费 1 表示的是历史缴费
	 * @param carNo 用户车牌
	 * @param flag
	 * @return
	 */
	public String getUserCarInfo(HttpServletRequest request, Map<String, String> paramObj, int pageno,
			String device_type, String dataStatus, String carNo, String flag) {
		try {
			request.setAttribute("flag", flag);
			request.setAttribute("dataStatus", dataStatus);
			// 通过open_id来查询出所有的车辆信息
			List<UserWeiXin> userWeiXinList = (List) this.baseService.queryForList("com.wl.weixin.getAllCarNoThroughtOpenId", paramObj);
			if (userWeiXinList == null ||userWeiXinList.size() == 0) {// 表示的是没有车辆信息，那么需要添加车辆
				return "/weixin/addcar";
			} else {
				// 表示的是存在车辆，再次做一个判断，如果是只有一辆车辆，那么对该车辆进行现有的进行缴费查询
				if (carNo != null) {
					// 新增完成之后查询出该车当前的停车情况
					// ParkVo parkVo = this.baseService.query("", paramObj);
					if (dataStatus == "" || dataStatus == null) {
						dataStatus = "0";
					}
					ParkVo parkVo = this.weiXinService.getUserCurrentPark(carNo, pageno, device_type, dataStatus);
					if (parkVo.isSuccess()) {
						// 表示的是服务器请求成功
						if (parkVo.getData() == null || parkVo.getData().size() == 0) {
							// 表示的是没有欠费记录
							request.setAttribute("carNo", carNo);
							return "/weixin/notpay";
						} else {
							// 表示的是存在记录，那么需要对其进行想要的封装
							request.setAttribute("parkVo", parkVo);
							return "/weixin/pay";
						}
					} else {
						// 表示的是服务器请求而失败
						return "/weixin/error";
					}
				} else {
					if (userWeiXinList.size() == 1) {
						// UserWeiXin userWeiXin = new UserWeiXin();
						UserWeiXin exitUserWeiXin = userWeiXinList.get(0);
						// userWeiXin.setCar_no(exitUserWeiXin.getCar_no());
						// userWeiXin.setOpen_id(paramObj.get("open_id").trim().toString());
						// 新增完成之后查询出该车当前的停车情况
						ParkVo parkVo = this.weiXinService.getUserCurrentPark(exitUserWeiXin.getCar_no(), pageno,
								device_type, dataStatus);
						if (parkVo.isSuccess()) {
							// 表示的是服务器请求成功
							if (parkVo.getData() == null || parkVo.getData().size() == 0) {
								// 表示的是没有欠费记录
								request.setAttribute("carNo", exitUserWeiXin.getCar_no());
								return "/weixin/notpay";
							} else {
								// 表示的是存在记录，那么需要对其进行想要的封装
								request.setAttribute("parkVo", parkVo);
								return "weixin/pay";
							}
						} else {
							// 表示的是服务器请求而失败
							return "/weixin/error";
						}
					} else {
						// 表示的是有多条记录 让用户自己去选择车牌进行想要信息的查询
						request.setAttribute("userWeiXinList", userWeiXinList);
						return "/weixin/allcar";
					}
				}
			}
		} catch (Exception e) {
            LOGGER.error("获取车辆信息失败", e);
			// 表示的是服务器请求而失败
			return "/weixin/error";
		}
	}

	/****
	 * 解除绑定
	 * 
	 * @param car_no
	 * @param flag
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/del")
	public String del(String car_no, String flag, HttpServletRequest request) {
		String openid = "";
		if ("1".equals(flag)) { // 表示的是支付宝
			// 表示的是支付宝流程
			openid = (String) request.getSession().getAttribute(Constants.CURRENT_USERID);
			if (openid == null) {
				return this.alipayurl(flag, request);
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("open_id", openid);
				map.put("car_no", car_no);
				this.baseService.remove("com.wl.weixin.delUserWeiXin", map);
				Map<String, String> paramObj = new HashMap<String, String>();
				paramObj.put("open_id", openid);
				// List<UserWeiXin> userWeiXinList =
				// weiXinService.getAllCarNoThroughtOpenId(paramObj);
				return this.getUserCarInfo(request, paramObj, 1, "3", "0", null, flag);
			}
		} else {// 表示的是微信
				// 表示的是微信支付流程
			openid = (String) request.getSession().getAttribute(Constants.CURRENT_OPENID);
			if (openid == null) {
				return this.wxurl(request);
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("open_id", openid);
				map.put("car_no", car_no);
				this.baseService.remove("com.wl.weixin.delUserWeiXin", map);
				Map<String, String> paramObj = new HashMap<String, String>();
				paramObj.put("open_id", openid);
				// List<UserWeiXin> userWeiXinList =
				// weiXinService.getAllCarNoThroughtOpenId(paramObj);
				return this.getUserCarInfo(request, paramObj, 1, "3", "0", null, flag);
			}
		}
		// return this.getUserCarInfo(request, paramObj, pageno, device_type,
		// dataStatus, carNo, flag);
	}

	/****
	 * 微信授权回调函数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/author")
	public String author(HttpServletRequest request) {
		try {
			String code = request.getParameter("code");
			AccesstokenInfo tokenInfo = this.weiXinService.getAcccessTokenByCode(code);
			if (tokenInfo == null) {
				// 表示的是授权失败
				return "/weixin/author";
			} else {
				String open_id = tokenInfo.getOpenid();
				request.getSession().setAttribute(Constants.CURRENT_OPENID, open_id);
				// 所有授权页面全部都跳转到查询
				// 判断该open_id是否存在在系统中
				Map<String, String> paramObj = new HashMap<String, String>();
				paramObj.put("open_id", open_id);
				// List<UserWeiXin> userWeiXinList =
				// weiXinService.getAllCarNoThroughtOpenId(paramObj);
				return this.getUserCarInfo(request, paramObj, 1, "3", "0", null, null);
			}
		} catch (Exception e) {
            LOGGER.error("授权出错了", e);
		}
		return "/weixin/authors";
	}

	/***
	 * 执行停车业务逻辑的查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/parkingfee")
	public String index(String flag, String pageno, HttpServletRequest request) {
		if ("1".equals(flag)) {
			// 表示的是支付宝流程
			String CURRENT_USERID = (String) request.getSession().getAttribute(Constants.CURRENT_USERID);
			// CURRENT_USERID="aaaaaaa";
			if (pageno == null || pageno == "") {
				pageno = "1";
			}
			if (CURRENT_USERID == null) {
				// 表示的是没有openid，那么就需要进行相应 的授权操作
				// 执行授权，然后进行车牌的输入操作
				return this.alipayurl(flag, request);
			} else {
				// 如果存在就直接执行授权的操作 存在直接执行相关的业务逻辑信息
				Map<String, String> paramObj = new HashMap<String, String>();
				paramObj.put("open_id", CURRENT_USERID);
				paramObj.put("flag", flag);
				// 查询出对应的车辆信息
				return this.getUserCarInfo(request, paramObj, Integer.valueOf(pageno), "3", "0", null, flag);
			}
		} else {
			// 表示的是微信支付流程
			String openid = (String) request.getSession().getAttribute(Constants.CURRENT_OPENID);
			// openid="om4NcwGiBsBVHkc_0Fshif4xignc";
			// request.getSession().setAttribute(Constants.CURRENT_OPENID,
			// "om4NcwGiBsBVHkc_0Fshif4xignc");
			if (pageno == null || pageno == "") {
				pageno = "1";
			}
			if (openid == null) {
				// 表示的是没有openid，那么就需要进行相应 的授权操作
				// 执行授权，然后进行车牌的输入操作
				this.wxurl(request);
				return "/weixin/author";
			} else {
				// 如果存在就直接执行授权的操作 存在直接执行相关的业务逻辑信息
				Map<String, String> paramObj = new HashMap<String, String>();
				paramObj.put("open_id", openid);
				// 查询出对应的车辆信息
				return this.getUserCarInfo(request, paramObj, Integer.valueOf(pageno), "3", "0", null, null);
			}
		}
	}

	/***
	 * 新增车牌信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addCar")
	public String addCar(String carNo, String flag, HttpServletRequest request) {
		if ("1".equals(flag)) {
			// 支付宝相关业务逻辑信息
			String openid = (String) request.getSession().getAttribute(Constants.CURRENT_USERID);
			if (openid == null) {
				// 授权
				return this.alipayurl(flag, request);
			} else {
				// 表示的是添加

				Map<String, String> paramObj = new HashMap<String, String>();
				paramObj.put("open_id", openid);
				// 把车辆信息添加到数据库中
				if (StringUtils.isBlank(carNo)) {
					return "/weixin/carnoerror";
				}
				paramObj.put("carNo", carNo);
				UserWeiXin userWeiXin = new UserWeiXin();
				userWeiXin.setCar_no(carNo);
				userWeiXin.setOpen_id(openid);
				// Integer
				// exitUserWeiXin=this.weiXinService.getUserWeiXin(userWeiXin);
				Integer exitUserWeiXin = (Integer) this.baseService.query("com.wl.weixin.getUserWeiXin", userWeiXin);
				if (exitUserWeiXin == null || exitUserWeiXin == 0) {
					/// this.weiXinService.addCar(userWeiXin);
					this.baseService.add("com.wl.weixin.addCar", userWeiXin);
				}
				String dataStatus = request.getParameter("dataStatus");
				if (dataStatus == null) {
					dataStatus = "0";
				}
				return this.getUserCarInfo(request, paramObj, 1, "3", dataStatus, carNo, flag);
			}
		} else {
			// 微信相关业务逻辑信息
			try {
				String openid = (String) request.getSession().getAttribute(Constants.CURRENT_OPENID);
				// openid="aaaaaaa";
				if (openid == null) {
					// 表示的是没有openid，那么就需要进行相应 的授权操作
					// 执行授权，然后进行车牌的输入操作
					return this.wxurl(request);
				} else {
					Map<String, String> paramObj = new HashMap<String, String>();
					paramObj.put("open_id", openid);
					// 把车辆信息添加到数据库中
					if (StringUtils.isBlank(carNo)) {
						return "/weixin/carnoerror";
					}
					paramObj.put("carNo", carNo);
					UserWeiXin userWeiXin = new UserWeiXin();
					userWeiXin.setCar_no(carNo);
					userWeiXin.setOpen_id(openid);
					// Integer
					// exitUserWeiXin=this.weiXinService.getUserWeiXin(userWeiXin);
					Integer exitUserWeiXin = (Integer) this.baseService.query("com.wl.weixin.getUserWeiXin",
							userWeiXin);
					if (exitUserWeiXin == null || exitUserWeiXin == 0) {
						/// this.weiXinService.addCar(userWeiXin);
						this.baseService.add("com.wl.weixin.addCar", userWeiXin);
					}
					String dataStatus = request.getParameter("dataStatus");
					if (dataStatus == null) {
						dataStatus = "0";
					}
					return this.getUserCarInfo(request, paramObj, 1, "3", dataStatus, carNo, flag);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/***
	 * 历史欠缴费
	 */
	@RequestMapping(value = "/history")
	public String history(String car_no, String dataStatus, String flag, HttpServletRequest request) {
		if ("1".equals(flag)) {
			// 表示的是支付宝的相关业务逻辑
			try {
				String openid = (String) request.getSession().getAttribute(Constants.CURRENT_USERID);
				if (openid == null) {
					// 表示的是没有openid，那么就需要进行相应 的授权操作
					return this.alipayurl(flag, request);
				} else {
					Map<String, String> paramObj = new HashMap<String, String>();
					paramObj.put("open_id", openid);
					if (dataStatus == null) {
						dataStatus = "1";
					}
					return getUserCarInfo(request, paramObj, 1, "3", dataStatus, car_no, flag);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 表示的是微信的相关的业务逻辑信息
			try {
				String openid = (String) request.getSession().getAttribute(Constants.CURRENT_OPENID);
				if (openid == null) {
					// 表示的是没有openid，那么就需要进行相应 的授权操作
					return this.wxurl(request);
				} else {
					Map<String, String> paramObj = new HashMap<String, String>();
					paramObj.put("open_id", openid);
					if (dataStatus == null) {
						dataStatus = "1";
					}
					// return this.getUserCarInfo(request, paramObj, 1, "3",
					// "1",car_no);
					return getUserCarInfo(request, paramObj, 1, "3", dataStatus, car_no, flag);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/***
	 * 订单详情
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/orderDetail")
	public String orderDetail(String id, String dataType, String dataStatus, String carNo, String inTime,
			String parkName, String flag, HttpServletRequest request) {
		request.setAttribute("order_id",id);
		if ("1".equals(flag)) {
			// 表示的是支付宝
			try {
				String openid = (String) request.getSession().getAttribute(Constants.CURRENT_USERID);
				if (openid == null) {
					// 表示的是没有openid，那么就需要进行相应 的授权操作
					return this.alipayurl("1", request);
				} else {
					Map<String, String> paramObj = new HashMap<String, String>();
					paramObj.put("id", id);
					paramObj.put("dataType", dataType);
					paramObj.put("device_type", "3");
					paramObj.put("version_code", "33");
					String dataStatusa = "0";
					if (dataStatus != null) {
						dataStatusa = dataStatusa;
					}
					paramObj.put("dataStatus", dataStatusa);
					ParkVoDetail parkVo = this.weiXinService.getUserOrderDetail(paramObj);
					request.setAttribute("parkvo", parkVo);
					request.setAttribute("carNo", carNo);
					request.setAttribute("inTime", inTime);
					request.setAttribute("parkName", parkName);
					request.setAttribute("flag", "1");
					return "/weixin/detail";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 表示的值微信
			try {
				String openid = (String) request.getSession().getAttribute(Constants.CURRENT_OPENID);
				if (openid == null) {
					// 表示的是没有openid，那么就需要进行相应 的授权操作
					return this.wxurl(request);
				} else {
					Map<String, String> paramObj = new HashMap<String, String>();
					paramObj.put("id", id);
					paramObj.put("dataType", dataType);
					paramObj.put("device_type", "3");
					paramObj.put("version_code", "33");
					String dataStatusa = "0";
					if (dataStatus != null) {
						dataStatusa = dataStatus;
					}
					paramObj.put("dataStatus", dataStatusa);
					ParkVoDetail parkVo = this.weiXinService.getUserOrderDetail(paramObj);
					request.setAttribute("parkvo", parkVo);
					request.setAttribute("carNo", carNo);
					request.setAttribute("inTime", inTime);
					request.setAttribute("parkName", parkName);
					request.setAttribute("flag", null);
					return "/weixin/detail";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/***
	 * 车牌绑定
	 */
	@RequestMapping(value = "/bindplate")
	public String bindPlate(String flag, HttpServletRequest request) {
		if ("1".equals(flag)) {
			// 支付宝
			String openid = (String) request.getSession().getAttribute(Constants.CURRENT_USERID);
			if (openid == null) {
				// 表示的是没有openid，那么就需要进行相应 的授权操作
				return this.alipayurl(flag, request);
			} else {
				return "/weixin/addcar";
			}
		} else {
			// 微信
			String openid = (String) request.getSession().getAttribute(Constants.CURRENT_OPENID);
			// openid = "aaaaaaa";
			if (openid == null) {
				// 表示的是没有openid，那么就需要进行相应 的授权操作
				return this.wxurl(request);
			} else {
				return "/weixin/addcar";
			}
		}
	}

	/******************************************* 下面是执行支付宝的相关的业务逻辑信息 ********************************************************/
	/****
	 * 支付宝授权回调函数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/alipayauthor")
	public String alipayauthor(HttpServletRequest request) {
		String flag = request.getParameter("state");
		String auth_code = request.getParameter("auth_code");// 获取用户的
		String url = "https://openapi.alipay.com/gateway.do";
		AlipayClient alipayClient = new DefaultAlipayClient(url, SystemConfigUtil.getValue("ALIPAY.APPID"),
				SystemConfigUtil.getValue("ALIAPY.PRIVATEKEY"), "json", "GBK",
				SystemConfigUtil.getValue("ALIPAY.PUBLICKEY"));
		AlipaySystemOauthTokenRequest requests = new AlipaySystemOauthTokenRequest();
		requests.setGrantType("authorization_code");
		requests.setCode(auth_code);
		requests.setRefreshToken("201208134b203fe6c11548bcabd8da5bb087a83b");
		AlipaySystemOauthTokenResponse response = null;
		String user_id = "";
		String return_value = "";
		try {
			response = alipayClient.execute(requests);
			user_id = response.getUserId();
			LOGGER.error("user_id" + user_id);
			request.getSession().setAttribute(Constants.CURRENT_USERID, user_id);
			// 所有授权页面全部都跳转到查询
			// 判断该open_id是否存在在系统中
			Map<String, String> paramObj = new HashMap<String, String>();
			paramObj.put("open_id", user_id);
			// List<UserWeiXin> userWeiXinList =
			// weiXinService.getAllCarNoThroughtOpenId(paramObj);
			return_value = this.getUserCarInfo(request, paramObj, 1, "3", "0", null, flag);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return return_value;
	}

	/***
	 * 支付宝的请求
	 * 
	 * @param flag
	 * @param request
	 */
	public String alipayurl(String flag, HttpServletRequest request) {
		String appId = SystemConfigUtil.getValue("ALIPAY.APPID");
		String REDIRECT_URL = SystemConfigUtil.getValue("ALIPAY.REDIRECT_URI");
		String urls = this.baseService.urlEnodeUTF8(REDIRECT_URL);
		String scope = SystemConfigUtil.getValue("ALIPAY.SCOPE");
		String url = "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=" + appId + "&scope=" + scope
				+ "&redirect_uri=" + urls + "&state=" + flag;
		// 模拟发送http请求 ，从而获取到到对应的微信的相关
		request.setAttribute("url", url);
		return "/weixin/author";
	}

	/****
	 * 微信的请求
	 * 
	 * @param request
	 */
	public String wxurl(HttpServletRequest request) {
		String appId = SystemConfigUtil.getValue("WX.APPID");
		String REDIRECT_URL = SystemConfigUtil.getValue("WX.REDIRECT_URL");
		String urls = this.baseService.urlEnodeUTF8(REDIRECT_URL);
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" + urls
				+ "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		// 模拟发送http请求 ，从而获取到到对应的微信的相关
		request.setAttribute("url", url);
		return "/weixin/author";
	}
	
	/***
	 * 微信支付
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/wxpay")
	@ResponseBody
	public ReturnMessage wxPay(HttpServletRequest request) {
		// 微信
		String openid = (String) request.getSession().getAttribute(Constants.CURRENT_OPENID);
		String total_fee  = request.getParameter("total_fee");
		String out_trade_no = request.getParameter("order_id");
		if (openid == null) {
			// 表示的是没有openid，那么就需要进行相应 的授权操作
			return new ReturnMessage(Constants.RETURN_ERROR,"获取支付信息失败请重新操作", null);
		} else {
			try {
				// 表示的是微信支付
				String noce_str = WxSign.getNonceStr();
				String timeStamp =  WxSign.getTimeStamp();
				String body = "支付停车费用";
				//执行微信的统一调用接口的方法
				//第一步： 选择商品下单
				//订单编号
				Float moneys = Float.parseFloat(total_fee)*100;
				Integer totalFee = Float.valueOf(moneys).intValue();
				String ip = request.getRemoteAddr();
				//订单描述
				WxPaySendData data = new WxPaySendData();
				data.setAppid(WxPayConfig.APPID);
				data.setBody(body);
				data.setMch_id(WxPayConfig.MCHID);
				data.setNonce_str(noce_str);
				data.setNotify_url(WxPayConfig.NOTIFY_URL);
				data.setOpenid(openid);
				data.setOut_trade_no(out_trade_no);
				data.setSpbill_create_ip(ip);
				data.setTotal_fee(totalFee);//单位：分
				data.setTrade_type("JSAPI");
				String returnXml = UnifiedorderService.unifiedOrder(data,WxPayConfig.KEY);
				Map<String,String> returnobj  = XMLUtil.doXMLParse(returnXml);
				String prepay_id = returnobj.get("prepay_id");
				//request.setAttribute("prepay_id",prepay_id);
				
				SortedMap<Object,Object> payMap = new TreeMap<Object,Object>();
				payMap.put("appId", WxPayConfig.APPID);
				payMap.put("timeStamp", timeStamp);
				payMap.put("nonceStr",noce_str);
				payMap.put("signType", "MD5");
				payMap.put("package", "prepay_id="+prepay_id);
				String paySign = WxSign.createSign(payMap,WxPayConfig.KEY);
				payMap.put("prepay_ids", "prepay_id="+prepay_id);
				payMap.put("paySign",paySign);
				
				return new ReturnMessage(Constants.RETURN_SUCCESS, "获取支付信息成功", payMap);
			} catch (Exception e) {
                LOGGER.error("微信支付异常", e);
			}
		}
		return null;
	}
	
	
	/**
	 * 创建支付格式
	 * @return
	 */
	@RequestMapping(value = "/createpay")
	public String createPay(String radiochoice, HttpServletRequest request) {
		// 支付类型
		String returnurl = "";
		// 商户订单号
		String out_trade_no = request.getParameter("order_id");
		// 付款金额
		String total_fee = request.getParameter("arrearAmounts");
		//停车内容
		String body = "支付停车费用";
		if ("0".equals(radiochoice)) {
			String openid = (String) request.getSession().getAttribute(Constants.CURRENT_USERID);
			if (openid == null) {
				// 表示的是没有openid，那么就需要进行相应 的授权操作
				return this.alipayurl("1", request);
			} else {
				String payment_type = "1";
				String notify_url = AlipayConfig.notify_url;
				// 页面跳转同步通知页面路径
				String return_url = AlipayConfig.return_url;
				
				// 订单名称
				String subject = "购买小车嘀嗒车联网盒子以及3年盗抢险";
			
				// 商品展示地址
				String show_url = AlipayConfig.show_url;
				
				// 选填
				String it_b_pay = "2d";
				// 选填
				// 把请求参数打包成数组
				Map<String, String> sParaTemp = new HashMap<String, String>();
				sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
				sParaTemp.put("partner", AlipayConfig.partner);
				sParaTemp.put("_input_charset", AlipayConfig.input_charset);
				sParaTemp.put("seller_id", AlipayConfig.seller_id);
				sParaTemp.put("payment_type", payment_type);
				sParaTemp.put("notify_url", notify_url);
				sParaTemp.put("return_url", return_url);
				sParaTemp.put("out_trade_no", out_trade_no);
				sParaTemp.put("subject", subject);
				sParaTemp.put("total_fee", total_fee);
				sParaTemp.put("body", body);
				sParaTemp.put("show_url", show_url);
				sParaTemp.put("it_b_pay", it_b_pay);
				String ac = AlipaySubmit.buildRequestMysigns(sParaTemp);
				sParaTemp.put("sign", ac);
				// 建立请求
				String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "提交");
				request.setAttribute("sHtmlText", sHtmlText);
				// System.out.println(sHtmlText);
				returnurl = "/weixin/paylist";
			}
		}
		return null;
	}
	
}