package com.wxpay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tenpay.protocol.pay_protocol.OrderPayReqData;
import com.tenpay.service.OrderPayService;
import com.tenpay.util.HttpClientUtil;
import com.tenpay.util.XMLUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class UnifiedorderService {
	private final static Logger logger = LoggerFactory.getLogger(UnifiedorderService.class);
    
	  public static String unifiedOrder(WxPaySendData data,String key){
	    //统一下单支付
	    String returnXml = null;
	    try {
	      //生成sign签名
	      SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
	      parameters.put("appid", data.getAppid()); 
	      parameters.put("attach", data.getAttach());
	      parameters.put("body", data.getBody());
	     // parameters.put("device_info", data.getDevice_info());
	      parameters.put("mch_id", data.getMch_id());
	      parameters.put("nonce_str", data.getNonce_str());
	      parameters.put("notify_url", data.getNotify_url());
	      parameters.put("openid", data.getOpenid());
	      parameters.put("out_trade_no", data.getOut_trade_no());
	      parameters.put("spbill_create_ip", data.getSpbill_create_ip());
	      parameters.put("total_fee", data.getTotal_fee());
	      parameters.put("trade_type", data.getTrade_type());
	      data.setSign(WxSign.createSign(parameters,key));
	     // XStream xs = new XStream(new DomDriver("UTF-8",new XmlFriendlyNameCoder("-_", "_")));
	      //xs.alias("xml", WxPaySendData.class);
	      //String xml = xs.toXML(data);
	      //logger.info("统一下单xml为:\n" + xml);
	      //System.out.println("xml=" + xml);
	      /*
	      HttpClientUtil util = HttpClientUtil.getInstance();
	      returnXml = util.doPostForString("https://api.mch.weixin.qq.com/pay/unifiedorder", null, xml);
	      //returnXml=testPost("https://api.mch.weixin.qq.com/pay/unifiedorder",xml);
	      logger.info("返回结果:" + returnXml);
	      */
	      OrderPayService payService = new OrderPayService();
	      returnXml = payService.request(data);
	      //调用XMLUTil来转换成map
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return returnXml;
	  }
	  
	  public static String testPost(String urlStr,String xmlInfo) {  
		  StringBuffer buffer = new StringBuffer();
	        try { 
	            URL url = new URL(urlStr);  
	            URLConnection con = url.openConnection();  
	            con.setDoOutput(true);  
	            con.setRequestProperty("Pragma:", "no-cache");  
	            con.setRequestProperty("Cache-Control", "no-cache");  
	            con.setRequestProperty("Content-Type", "text/xml");  
	  
	            OutputStreamWriter out = new OutputStreamWriter(con  
	                    .getOutputStream());      
	            out.write(new String(xmlInfo.getBytes("ISO-8859-1")));  
	            out.flush();  
	            out.close();  
	            BufferedReader br = new BufferedReader(new InputStreamReader(con  
	                    .getInputStream()));  
	            String line = "";  
	            for (line = br.readLine(); line != null; line = br.readLine()) {  
	                //System.out.println(line);
	            	buffer.append(line);
	            }  
	        } catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        return buffer.toString();
	    } 
	    
	}