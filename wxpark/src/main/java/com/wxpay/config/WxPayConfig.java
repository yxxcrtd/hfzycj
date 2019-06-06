package com.wxpay.config;

import com.wxpay.util.GetWeiXinCode;

public class WxPayConfig {	
		//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
		public static String APPID="wxc7eba1098cbad0e2";
		//受理商ID，身份标识  商户号
		public static String MCHID = "1402168302";
		//商户支付密钥Key。审核通过后，在微信发送的邮件中查看
		public static String KEY = "8936e7d15453e98796ef98DSE74ds35e";
		//JSAPI接口中获取openid，审核后在公众平台开启开发模式后可查看
		public static String APPSECERT="a9a3d8d8d6dc49d6d322c14225029f6";
		public static String REDIRECT_URL = "http://zhouqiubo.vicp.cc/wx/calc/payinfos.shtml";
		//public static String NOTIFY_URL = "http://zhouqiubo.vicp.cc/wx/calc/paysuccess.shtml";
		public static String NOTIFY_URL = "http://wx.xshcar.com/calc/paysuccess.shtml";
}