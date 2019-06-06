package com.tcc.park.api.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.park.api.common.ResultCode;
import com.tcc.park.api.common.SmsConfig;
import com.tcc.park.api.sms.SmsSend;
import com.tcc.park.api.user.domain.SmsCode;
import com.tcc.park.api.user.domain.SmsCodeExample;
import com.tcc.park.api.user.mybatis.SmsCodeMapper;
import com.tcc.park.api.util.DateUtil;
import com.tcc.park.api.util.RandomUtil;
import com.tcc.park.api.vo.ObjectResult;

@Service
public class SmsService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SmsCodeMapper smsCodeMapper;
	
	private final int CODE_NOCHECK = 0;
	private final int CODE_CHECKED = 1;
	private final int CODE_INVALID = 2;
	
	public ObjectResult<String> sendCode(String mobile,int type) {
		ObjectResult<String> objResult = new ObjectResult<String>();
		//type 1 reg,2 feg
		if (StringUtils.isBlank(mobile)) {
			objResult.setResultCode(ResultCode.MOBILE_ERROR);
			return objResult;
		}
		if (type == 1) {
			if (userService.checkUserIsReg(mobile)) {
				objResult.setResultCode(ResultCode.MOBILE_REGISTERED_ERROR);
				return objResult;
			}
		}else if (type == 2) {
			if (!userService.checkUserIsReg(mobile)) {
				objResult.setResultCode(ResultCode.MOBILE_UNREGISTER_ERROR);
				return objResult;
			}
		}
		
		String smsCode = RandomUtil.genRandomFixedLenth(4);
		String smsContent = SmsConfig.USER_REG.replace("#code#", smsCode);
		String result = SmsSend.sendSms(mobile, smsContent);
		insertSms(type, smsCode, mobile, result, smsContent);
		updateValidCode(mobile, smsCode, type);
		objResult.setResultCode(ResultCode.APP_SUCCESS);
		objResult.setObj(smsCode);
		return objResult;
	}
	
	public void insertSms(int type,String code,String mobile,String sendStatus,String smsContent) {
		SmsCode smsCode = new SmsCode();
		smsCode.setBisType(type);
		smsCode.setCode(code);
		smsCode.setInvalidTime(DateUtil.addDateMin(new Date(), 10));
		smsCode.setMobile(mobile);
		smsCode.setSendStatus(sendStatus);
		smsCode.setStatus(CODE_NOCHECK);
		smsCode.setSendTime(new Date());
		smsCode.setSmsContent(smsContent);
		smsCodeMapper.insertSelective(smsCode);
	}
	
	public void updateValidCode(String mobile,String code,int type) {
		SmsCodeExample example = new SmsCodeExample();
		example.or().andMobileEqualTo(mobile).andBisTypeEqualTo(type).andCodeNotEqualTo(code).andStatusEqualTo(CODE_NOCHECK);
		
		SmsCode smsCode = new SmsCode();
		smsCode.setStatus(CODE_INVALID);
		smsCode.setModifyTime(new Date());
		
		smsCodeMapper.updateByExampleSelective(smsCode, example);
	}
	
	public String checkCode(String mobile,String code,int type) {
		SmsCodeExample example = new SmsCodeExample();
		example.or().andCodeEqualTo(code).andMobileEqualTo(mobile).andBisTypeEqualTo(type);
		List<SmsCode> list = smsCodeMapper.selectByExample(example);
		if (list==null || list.size() <= 0) {
			return ResultCode.CODE_UNEXIT_ERROR;
		}
		SmsCode smsCode = list.get(0);
		if (smsCode.getInvalidTime()!=null && smsCode.getInvalidTime().before(new Date())) {
			return ResultCode.CODE_VALID_ERROR;
		}
		if (smsCode.getStatus()!=null && smsCode.getStatus() != CODE_NOCHECK) {
			return ResultCode.CODE_CHECKED_ERROR;
		}
		smsCode.setStatus(CODE_CHECKED);
		smsCode.setModifyTime(new Date());
		smsCodeMapper.updateByPrimaryKeySelective(smsCode);
		//
		return ResultCode.CODE_OK;
	}

}
