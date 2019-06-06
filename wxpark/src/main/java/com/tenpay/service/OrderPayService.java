package com.tenpay.service;

import com.tenpay.common.*;
import com.tenpay.protocol.pay_protocol.OrderPayReqData;
import com.tenpay.protocol.pay_protocol.ScanPayReqData;
import com.wxpay.util.WxPaySendData;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:03
 */
public class OrderPayService extends BaseService{
	
    public OrderPayService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.ORDER_API);
    }
    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(OrderPayReqData orderPayReqData) throws Exception {
        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(orderPayReqData);
        return responseString;
    }
    
    /**
     *请求支付服务器
     **/
    public String request(WxPaySendData data) throws Exception {
    	 //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(data);
        return responseString;
    }
    
}
