package com.zycj.tcc.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.domain.PaymentRecord;
import com.zycj.tcc.mybatis.mapper.PaymentRecordMapper;
import com.zycj.tcc.service.PaymentService;
import com.zycj.tcc.util.RandomUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRecordMapper paymentRecordMapper;
	
	@Override
	public Integer insertPaymentRecord(PaymentRecord paymentRecord) {
		if (paymentRecord == null ) {
			return 0;
		}
		paymentRecord.setId(null);
		paymentRecord.setSerialNo(RandomUtil.getSrialNoByDate());
		paymentRecord.setCreateTime(new Date());
		return paymentRecordMapper.insertSelective(paymentRecord);
	}

}
