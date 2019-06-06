package com.alipay.util;

import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;

import com.alipay.config.AlipayConfig;
import com.alipay.util.httpClient.HttpProtocolHandler;
import com.alipay.util.httpClient.HttpRequest;
import com.alipay.util.httpClient.HttpResponse;
import com.alipay.util.httpClient.HttpResultType;
import com.wxpay.config.WxPayConfig;

public class WXSubmit {
    
    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     */
    //private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
	private static final String ALIPAY_GATEWAY_NEW="https://api.weixin.qq.com/sns/oauth2/access_token?"; 
	
   
    /**
     * 建立请求，以模拟远程HTTP的POST请求方式构造并获取支付宝的处理结果
     * 如果接口中没有上传文件参数，那么strParaFileName与strFilePath设置为空值
     * 如：buildRequest("", "",sParaTemp)
     * @param strParaFileName 文件类型的参数名
     * @param strFilePath 文件路径
     * @param sParaTemp 请求参数数组
     * @return 支付宝处理结果
     * @throws Exception
     */
    public static String buildRequest(String code,String strParaFileName, String strFilePath,Map<String, String> sParaTemp) throws Exception {
        //待请求参数数组
        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(AlipayConfig.input_charset);
        request.setParameters(generatNameValuePair(sParaTemp));
        request.setUrl(ALIPAY_GATEWAY_NEW+"appid="+WxPayConfig.APPID+"&secret="+WxPayConfig.APPSECERT+"&code="+code+"&grant_type=authorization_code");
        HttpResponse response = httpProtocolHandler.execute(request,strParaFileName,strFilePath);
        if (response == null) {
            return null;
        }
        String strResult = response.getStringResult();
        return strResult;
    }   
    
    public static String buildRequests(String price,String strParaFileName, String strFilePath) throws Exception {
        //待请求参数数组
        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(AlipayConfig.input_charset);
        String urls =  urlEnodeUTF8(WxPayConfig.REDIRECT_URL);
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WxPayConfig.APPID+"&redirect_uri="+urls+"&response_type=code&scope=snsapi_base&state="+price+"#wechat_redirect";
        request.setUrl(url);
        HttpResponse response = httpProtocolHandler.execute(request,strParaFileName,strFilePath);
        if (response == null){
            return null;
        }
        String strResult = response.getStringResult();
        return strResult;
    }
    
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
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }
}
