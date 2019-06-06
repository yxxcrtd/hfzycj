package com.hfzycj.util;

public class WeCharPayUtil {

    /**
     * getPrepayId(调用微信统一下单接口，生成微信预支付id)
     * @param totalFee    支付金额
     * @param ipAddress   ip地址
     * @param orderNumber 订单号
     * @param body        商品或支付单简要描述
     * @param openid      trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
     * @return
     * @throws Exception
     */
//    public static Map<String, String> getPrepayId(String totalFee,String ipAddress,String orderNumber,String body,String openid) throws Exception{
//        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
//        parameters.put("appid", ConfigUtil.APPID);
//        parameters.put("mch_id", ConfigUtil.MCH_ID);
//        parameters.put("nonce_str", CreateNoncestr());
//        parameters.put("body", body);
//        parameters.put("out_trade_no", orderNumber);
//        parameters.put("total_fee", totalFee);
//        parameters.put("spbill_create_ip",ipAddress);
//        parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
//        parameters.put("trade_type", "JSAPI");
//        parameters.put("openid", openid);
//        String sign = PayCommonUtil.createSign("UTF-8", parameters);
//        parameters.put("sign", sign);
//        String requestXML = PayCommonUtil.getRequestXml(parameters);
//        String result =CommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
//        Map<String, String> map = XMLUtil.doXMLParse(result);//解析微信返回的信息，以Map形式存储便于取值
//        return map;
//    }

    /**
     * 调起支付
     * @param prepay_id
     * @return
     * @throws
     */
//    public static String createPackageValue(String prepay_id)  {
//        SortedMap<Object,Object> params = new TreeMap<Object,Object>();
//        params.put("appId", ConfigUtil.APPID);
//        params.put("timeStamp", Long.toString(new Date().getTime()));
//        params.put("nonceStr", PayCommonUtil.CreateNoncestr());
//        params.put("package", "prepay_id="+prepay_id);
//        params.put("signType", ConfigUtil.SIGN_TYPE);
//        String paySign =  PayCommonUtil.createSign("UTF-8", params);
//        params.put("packageValue", "prepay_id="+prepay_id);    //这里用packageValue是预防package是关键字在js获取值出错
//        params.put("paySign", paySign);                                                          //paySign的生成规则和Sign的生成规则一致
//        params.put("sendUrl", ConfigUtil.SUCCESS_URL);                               //付款成功后跳转的页面
//        String json = JSONObject.toJSONString(params);
//        return json;
//    }

}

