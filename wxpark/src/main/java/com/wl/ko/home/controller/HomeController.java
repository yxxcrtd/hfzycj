package com.wl.ko.home.controller;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.WXSubmit;
import com.wl.base.model.ReturnMessage;
import com.wl.base.service.IBaseService;
import com.wl.common.Constants;
import com.wl.common.utils.HttpClientUtils;
import com.wl.common.utils.JacksonUtil;
import com.wl.common.utils.SystemConfigUtil;
import com.wl.common.utils.UUIDUtil;
import com.wl.ko.home.model.BrandInfo;
import com.wl.ko.home.model.PriceInfo;
import com.wl.ko.home.model.UserOrderInfo;
import com.wl.ko.home.model.WxShare;
import com.wxpay.config.WxPayConfig;
import com.wxpay.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/calc")
@Controller
public class HomeController {

    /** Log */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IBaseService baseService;

    @RequestMapping(value = "/calcprice", produces = "application/json")
    @ResponseBody
    public ReturnMessage calcprice(String buy_price, HttpServletRequest request) {
        //查询出想要的价格信息
        try {
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("buy_price", buy_price);
            String feilv = (String) this.baseService.query("com.wl.ko.home.getPrice", paramMap);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            //求出商业险
            //求出1年的费用
            //求出3年的费用
            //求出小车嘀嗒三年的费用
            PriceInfo priceInfo = new PriceInfo();
            float didapreice = 0f;
            float oneprice = Float.parseFloat(feilv) * Float.parseFloat(buy_price);
            float threeprice = oneprice * 3;
            float oldprice = oneprice / 0.57f * 3;
            if (Float.parseFloat(buy_price) <= 100000) {
                didapreice = 850;
                priceInfo.setFlag("0");
            } else {
                didapreice = threeprice * 0.7f;
                priceInfo.setFlag("1");
            }
            priceInfo.setDidayear(decimalFormat.format(didapreice));
            priceInfo.setOneyear(decimalFormat.format(oldprice));
            priceInfo.setThreeyear(decimalFormat.format(threeprice));
            return new ReturnMessage(Constants.RETURN_SUCCESS, "返回成功", priceInfo);
        } catch (Exception e) {
            LOGGER.error(String.valueOf(e));
            return new ReturnMessage(Constants.RETURN_ERROR, "计算失败", "");
        }
    }

    /**
     * 首页显示
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/calc")
    public String index(HttpServletRequest request) {
        //查询出所有的品牌信息
        List<BrandInfo> lbinfo = (List) this.baseService.queryForList("com.wl.ko.home.getAllBrand", null);
        request.setAttribute("lbinfo", lbinfo);
        return "/calc/calc";
    }

    /**
     * 验证
     **/
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public void home(@RequestParam(value = "signature", required = true) String signature,
                     @RequestParam(value = "timestamp", required = true) String timestamp,
                     @RequestParam(value = "nonce", required = true) String nonce,
                     @RequestParam(value = "echostr", required = true) String echostr,
                     HttpServletRequest request, HttpServletResponse response
    ) {
        String token = "xishengheng20140318zhangwei";
        String[] values = {token, timestamp, nonce};
        Arrays.sort(values);// 字典序排序
        String value = values[0] + values[1] + values[2];
        String sign = DigestUtils.shaHex(value);
        PrintWriter writer = null;
        ;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (signature.equals(sign)) {// 验证成功返回ehcostr
            writer.print(echostr);
        } else {
            writer.print("error");
        }
        writer.flush();
        writer.close();
    }

    /**
     * 生成支付方式
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/payinfo")
    public String payinfo(String buy_price, HttpServletRequest request) {
        //查询出所有的品牌信息
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (buy_price == null || buy_price == "") {
            request.setAttribute("buy_price", buy_price);
        } else {
            request.setAttribute("buy_price", decimalFormat.format(Double.valueOf(buy_price)));
        }
        return "/order/pay";
    }

    /**
     * 微信支付转调节
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/payinfos")
    public String payinfos(HttpServletRequest request) {
        //查询出所有的品牌信息
        String code = request.getParameter("code");
        //String  code="001k2R3x1K0c050HgO2x1CDS3x1k2R3Z";
        System.out.println("code " + code);
        //通过http请求来获取token_access 以及api_id
        //https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        Map<String, String> sParaTemp = new HashMap<String, String>();
        ReturnMessage returnMessage = null;
        try {
            String ac = WXSubmit.buildRequest(code, "", "", sParaTemp);
            String statuss = request.getParameter("state");
            String[] stat = statuss.split("acb");
            //转换成json对象
            Map<String, String> map = (Map<String, String>) JacksonUtil.jsonToMap(ac);
            String openid = map.get("openid");
            //String openid = "oH7ARuAe6LuKv_CDZGEhTu9602KI";
            String noce_str = WxSign.getNonceStr();
            String timeStamp = WxSign.getTimeStamp();
            //执行微信的统一调用接口的方法
            //第一步： 选择商品下单
            //订单编号
            String orderid = UUIDUtil.genUUID32();
            String user_id = stat[1];
            Float moneys = Float.parseFloat(stat[0]) * 100;
            Integer totalFee = Float.valueOf(moneys).intValue();
            String ip = request.getRemoteAddr();
            //调用微信支付
            //必填
            //订单描述
            String body = "购买小车嘀嗒3年盗抢险";
            WxPaySendData data = new WxPaySendData();
            data.setAppid(WxPayConfig.APPID);
            data.setAttach(user_id);
            data.setBody(body);
            data.setMch_id(WxPayConfig.MCHID);
            data.setNonce_str(noce_str);
            data.setNotify_url(WxPayConfig.NOTIFY_URL);
            data.setOpenid(openid);
            data.setOut_trade_no(orderid);
            data.setSpbill_create_ip(ip);
            data.setTotal_fee(totalFee);//单位：分
            data.setTrade_type("JSAPI");
            //data.setDevice_info("WEB");
            String returnXml = UnifiedorderService.unifiedOrder(data, WxPayConfig.KEY);
            Map<String, String> returnobj = XMLUtil.doXMLParse(returnXml);
            String prepay_id = returnobj.get("prepay_id");
            request.setAttribute("prepay_id", prepay_id);
            SortedMap<Object, Object> payMap = new TreeMap<Object, Object>();
            payMap.put("appId", WxPayConfig.APPID);
            payMap.put("timeStamp", timeStamp);
            payMap.put("nonceStr", noce_str);
            payMap.put("signType", "MD5");
            payMap.put("package", "prepay_id=" + prepay_id);
            System.out.println("appId1=" + WxPayConfig.APPID);
            System.out.println("timeStamp1=" + timeStamp);
            System.out.println("nonceStr1=" + noce_str);
            System.out.println("package1=prepay_id=" + prepay_id);
            String paySign = WxSign.createSign(payMap, WxPayConfig.KEY);

            request.setAttribute("nonce_str", noce_str);
            request.setAttribute("paySign", paySign);
            request.setAttribute("prepay_ids", "prepay_id=" + prepay_id);
            request.setAttribute("appid", WxPayConfig.APPID);
            request.setAttribute("timeStamp", timeStamp);


            System.out.println("appId2=" + WxPayConfig.APPID);
            System.out.println("timeStamp2=" + timeStamp);
            System.out.println("nonceStr2=" + noce_str);
            System.out.println("package2=prepay_id=" + prepay_id);
            System.out.println("paySign2=" + paySign);

            request.setAttribute("buy_price", stat[0]);
            request.setAttribute("attach", user_id);
            request.setAttribute("orderid", orderid);
        } catch (Exception e) {
            LOGGER.error("支付异常", e);
        }
        //return returnMessage;
        return "/order/pay";
    }

    /**
     * 成功之后，执行相关的业务逻辑信息
     *
     * @param didaprice
     * @param attach
     * @param orderid
     * @param request
     */
    @RequestMapping(value = "/createorder", produces = "application/json")
    @ResponseBody
    public ReturnMessage createorder(String didaprice, String attach, String orderid, HttpServletRequest request) {
        //System.out.println("成功之后，执行相关的业务逻辑信息");
        ReturnMessage returnMessage = null;
        try {
            UserOrderInfo userOrderInfo = new UserOrderInfo();
            userOrderInfo.setFlag("1");
            userOrderInfo.setId(orderid);
            userOrderInfo.setPaytime(new Date(System.currentTimeMillis()));
            userOrderInfo.setStatus("1");
            userOrderInfo.setUser_id(attach);
            userOrderInfo.setCreatetime(new Date(System.currentTimeMillis()));
            userOrderInfo.setMoney(didaprice);
            this.baseService.add("com.wl.ko.home.userOrderInfo", userOrderInfo);
            request.setAttribute("orderid", orderid);
            request.setAttribute("didaprice", didaprice);
            returnMessage = new ReturnMessage(Constants.RETURN_SUCCESS, "支付成功", "");
        } catch (Exception e) {
            returnMessage = new ReturnMessage(Constants.RETURN_ERROR, "支付失败", "");
        }
        return returnMessage;
        //return "/pay/order";
    }

    /**
     * 执行跳转的操作
     *
     * @param didaprice
     * @param attach
     * @param orderid
     * @param request
     * @return
     */
    @RequestMapping(value = "/order")
    public String order(String didaprice, String orderid, HttpServletRequest request) {
        request.setAttribute("orderid", orderid);
        request.setAttribute("didaprice", didaprice);
        return "/order/order";
    }

    /**
     * 创建支付格式
     *
     * @return
     */
    @RequestMapping(value = "/createpay")
    public String createPay(String didaprice, String radiochoice, HttpServletRequest request) {
        //支付类型
        String returnurl = "";
        if ("0".equals(radiochoice)) {
            String payment_type = "1";
            //必填，不能修改
            //服务器异步通知页面路径
            String notify_url = AlipayConfig.notify_url;
            //需http://格式的完整路径，不能加?id=123这类自定义参数
            //页面跳转同步通知页面路径
            String return_url = AlipayConfig.return_url;
            //需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
            //商户订单号
            String out_trade_no = UUIDUtil.genUUID32();
            //商户网站订单系统中唯一订单号，必填
            //订单名称
            String subject = "购买小车嘀嗒车联网盒子以及3年盗抢险";
            //必填
            //付款金额
            String total_fee = didaprice;
            //必填
            //商品展示地址
            String show_url = AlipayConfig.show_url;
            //必填，需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html
            //订单描述
            String body = "购买小车嘀嗒车联网盒子以及3年盗抢险";
            //选填
            //超时时间
            String it_b_pay = "2d";
            //选填
            //////////////////////////////////////////////////////////////////////////////////
            //把请求参数打包成数组
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
            //建立请求
            String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "提交");
            request.setAttribute("sHtmlText", sHtmlText);
            //System.out.println(sHtmlText);
            returnurl = "/order/paylist";
        }
        if ("1".equals(radiochoice)) {
            //表示的是微信支付
            String ac;
            try {
                ac = WXSubmit.buildRequests(didaprice, "", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnurl;
    }

    /**
     * url验证
     *
     * @param str
     * @return
     */
    public static String urlEnodeUTF8(String str) {
        String result = str;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 填写用户信息
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping(value = "/userinfos", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
    public String userinfos(@RequestParam("upload_file") MultipartFile upload_file, String truename, String mobile, String carprice, String buytime, String address, String obdprice, HttpServletRequest request) {
        String origName = upload_file.getOriginalFilename();
        try {
            String urls = urlEnodeUTF8(WxPayConfig.REDIRECT_URL);
            //String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WxPayConfig.APPID+"&redirect_uri="+urls+"&response_type=code&scope=snsapi_base&state="+decimalFormat.format(countmoney)+"acb"+user_id+"#wechat_redirect";
            String url = "";
            request.setAttribute("url", url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/order/forward";
    }

    /**
     * 成功之后，返回执行相应的业务逻辑信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/paysuccess")
    public String paysuccess(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer buffer = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                buffer.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (buffer.toString() != null) {
                Map<String, String> map = XMLUtil.doXMLParse(buffer.toString());
                String out_trade_no = map.get("out_trade_no");
                String attach = map.get("attach");//user_id+","+flag+","+actd_id;
                String[] attachs = attach.split(",");
                String time_end = map.get("time_end");
                String flag = attachs[1];
                String actd_ids = attachs[2];
                Integer actd_id = Integer.parseInt(actd_ids);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                double price = 0;
                if ("0".equals(flag)) {
                } else if ("1".equals(flag)) {
                } else if ("2".equals(flag)) {
                }
                //直接返回给客户端
                String xmlObj = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                PrintWriter out = null;
                out = response.getWriter();
                out.print(xmlObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //执行微信返回的相关信息
        UserOrderInfo userOrderInfo = new UserOrderInfo();
        userOrderInfo.setFlag("1");
        //userOrderInfo.setId(orderid);
        userOrderInfo.setPaytime(new Date(System.currentTimeMillis()));
        userOrderInfo.setStatus("1");
        //userOrderInfo.setUser_id(attach);
        userOrderInfo.setCreatetime(new Date(System.currentTimeMillis()));
        //userOrderInfo.setMoney(didaprice);
        this.baseService.add("com.wl.ko.home.userOrderInfo", userOrderInfo);
        //request.setAttribute("orderid", orderid);
        //request.setAttribute("didaprice", didaprice);
        return null;
    }

    /***
     * 微信关注的相关业务逻辑信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/wxcar")
    public String getWxCare(HttpServletRequest request) {
        //获取到appId
        String appId = SystemConfigUtil.getValue("WX.APPID");
        String REDIRECT_URL = SystemConfigUtil.getValue("WX.REDIRECT_URL");
        String urls = urlEnodeUTF8(REDIRECT_URL);
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" + urls + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        request.setAttribute("url", url);
        //System.out.println("url===="+url);
        return "/care/forward";
    }

    /***
     * 微信获取到opnid等信息
     */
    @RequestMapping(value = "/wxcarinfo")
    public String getWxInfo(HttpServletRequest request) {
        String code = request.getParameter("code");
        String appId = SystemConfigUtil.getValue("WX.APPID");
        String serect = SystemConfigUtil.getValue("WX.SCRECRT");
        String accessbaby = HttpClientUtils.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + serect + "&code=" + code + "&grant_type=authorization_code", null);
        Map map = JacksonUtil.jsonToMap(accessbaby);
        //再次进行签名的操作
        String openid = (String) map.get("openid");
        request.setAttribute("openid", openid);
        String accessbabys = HttpClientUtils.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + serect, null);
        Map maps = JacksonUtil.jsonToMap(accessbabys);
        String access_token = (String) maps.get("access_token");
        String responseBaby = HttpClientUtils.doGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi", null);
        Map returnobj = JacksonUtil.jsonToMap(responseBaby);
        String ticket = (String) returnobj.get("ticket");
        String noncestr = create_nonce_str();
        String timeStamp = create_timestamp();
        request.setAttribute("noncestr", noncestr);
        request.setAttribute("timeStamp", timeStamp);
        request.setAttribute("appId", appId);
        String url = "http://wx.xshcar.com/calc/wxcarinfo.shtml?code=" + code + "&state=STATE";
        SortedMap<String, String> signParams = new TreeMap<String, String>();
        signParams.put("jsapi_ticket", ticket);
        signParams.put("noncestr", noncestr);
        signParams.put("timestamp", timeStamp);
        signParams.put("url", url);
        Sha1Util sha1Util = new Sha1Util();
        String sign = "";
        try {
            sign = sha1Util.createSHA1Sign(signParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("signature", sign);
        String activtiy_id = SystemConfigUtil.getValue("WX.ACTIVITYID");
        request.setAttribute("activityid", activtiy_id);
        //  String openid = "oH7ARuAe6LuKv_CDZGEhTu9602KI";
        Map<String, String> paramObj = new HashMap<String, String>();
        paramObj.put("openid", openid);
        paramObj.put("activtiy_id", activtiy_id);
        if (StringUtils.isBlank(openid)) {
        } else {
            Object wxshare = this.baseService.query("com.wl.ko.home.getOpenId", paramObj);
            if (wxshare == null) {
                request.setAttribute("flag", "0");
            } else {
                WxShare share = (WxShare) wxshare;
                if (share.getFlag() == null || share.getFlag() == "0") {
                    request.setAttribute("flag", "0");
                } else {
                    request.setAttribute("flag", share.getFlag());
                }
            }
        }
        //获取时间
        String time = SystemConfigUtil.getValue("WX.TIME");
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date starttime = (Date) dateformat.parse(time);
            Date currenttime = (Date) dateformat.parse(dateformat.format(new Date(System.currentTimeMillis())));
            if (starttime.after(currenttime)) {
                request.setAttribute("start", "0");
                long m = starttime.getTime() - currenttime.getTime();
                request.setAttribute("haomiao", m / 1000);
            } else {
                request.setAttribute("start", "1");
                request.setAttribute("haomiao", 0);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "/care/wx";
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

}