package com.tenpay.service;

import com.tenpay.common.Configure;
import com.tenpay.common.HttpsRequest;
import com.tenpay.common.RandomStringGenerator;
import com.tenpay.common.Signature;
import com.tenpay.protocol.refund_protocol.RefundReqData;

public class RefundService extends BaseService{

    public RefundService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.REFUND_API);
    }

    /**
     * 请求退款服务
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public String request(RefundReqData refundReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(refundReqData);

        return responseString;
    }

}
