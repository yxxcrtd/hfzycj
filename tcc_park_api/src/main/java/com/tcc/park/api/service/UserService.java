package com.tcc.park.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.park.api.common.ResultCode;
import com.tcc.park.api.user.domain.User;
import com.tcc.park.api.user.domain.UserExample;
import com.tcc.park.api.user.domain.UserLogin;
import com.tcc.park.api.user.domain.UserLoginExample;
import com.tcc.park.api.user.mybatis.UserLoginMapper;
import com.tcc.park.api.user.mybatis.UserMapper;
import com.tcc.park.api.vo.LoginResultVo;
import com.tcc.park.api.vo.ObjectResult;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserLoginMapper userLoginMapper;
	@Autowired
	private SmsService smsService;
	
	public String reg(String mobile,String password,String smsCode) {
		if (checkUserIsReg(mobile)) {
			return ResultCode.MOBILE_REGISTERED_ERROR;
		}
		String smsResult = smsService.checkCode(mobile, smsCode, 1);
		if (!ResultCode.CODE_OK.equals(smsResult)) {
			return smsResult;
		}
		insertUser(mobile, password);
		
		return ResultCode.APP_SUCCESS;
	}
	
	public Integer insertUser(String mobile,String password) {
		User user = new User();
		user.setMobile(mobile);
		user.setPassword(password);
		userMapper.insertSelective(user);
		return user.getId();
	}
	
	public boolean checkUserIsReg(String mobile) {
		UserExample example = new UserExample();
		example.or().andMobileEqualTo(mobile);
		List<User> userList = userMapper.selectByExample(example);
		return userList != null && userList.size() > 0;
	}
	
	public User selectUserByMobile(String mobile) {
		UserExample example = new UserExample();
		example.or().andMobileEqualTo(mobile);
		List<User> userList = userMapper.selectByExample(example);
		if(userList != null && userList.size() > 0){
			return userList.get(0);
		}
		return null;
	}
	
	public ObjectResult<LoginResultVo> login(String mobile,String password,Integer loginFrom,String ip) {
		ObjectResult<LoginResultVo> result = new ObjectResult<LoginResultVo>();
		User user = selectUserByMobile(mobile);
		if (user == null) {
			result.setResultCode(ResultCode.MOBILE_UNREGISTER_ERROR);
			return result;
		}
		if (user.getStatus().intValue() != 1) {
			result.setResultCode(ResultCode.MOBILE_INVALID);
			return result;
		}
		String userPwd = user.getPassword();
		if (StringUtils.isBlank(userPwd) || !userPwd.equals(password)) {
			result.setResultCode(ResultCode.PASSWORD_ERROR);
			return result;
		}
		String token = UUID.randomUUID().toString();
		addLogin(ip, mobile, token, loginFrom, user.getId());
		LoginResultVo vo = new LoginResultVo();
		vo.setCarNo(user.getCarNo());
		vo.setId(String.valueOf(user.getId()));
		vo.setName(user.getName());
		vo.setToken(token);
		vo.setVin(user.getMachineNo());
		vo.setCarType(user.getCarType());
		result.setObj(vo);
		result.setResultCode(ResultCode.APP_SUCCESS);
		return result;
	}
	
	public void addLogin(String ip,String mobile,String token,Integer loginFrom,Integer userId) {
		
		UserLoginExample example = new UserLoginExample();
		example.or().andMobileEqualTo(mobile).andUserIdEqualTo(userId);
		List<UserLogin> list = userLoginMapper.selectByExample(example);
		if (list!= null && list.size()>0) {
			UserLogin oldLogin = list.get(0);
			oldLogin.setToken(token);
			oldLogin.setLoginTime(new Date());
			oldLogin.setActiveTime(new Date());
			oldLogin.setIp(ip);
			oldLogin.setLoginFrom(loginFrom);
			oldLogin.setIsActive(1);
			userLoginMapper.updateByPrimaryKeySelective(oldLogin);
			return;
		}
		
		UserLogin login = new UserLogin();
		login.setActiveTime(new Date());
		login.setIp(ip);
		login.setLoginFrom(loginFrom);
		login.setLoginTime(new Date());
		login.setMobile(mobile);
		login.setToken(token);
		login.setUserId(userId);
		userLoginMapper.insertSelective(login);
	}
	
	public String updateByParam(String mobile,String password,String name,String carNo,String machineNo, Integer carType) {
		User user = selectUserByMobile(mobile);
		if (user == null) {
			return ResultCode.MOBILE_UNREGISTER_ERROR;
		}
		user.setPassword(password);
		user.setName(name);
		user.setCarNo(carNo);
		user.setMachineNo(machineNo);
		user.setCarType(carType);
		userMapper.updateByPrimaryKeySelective(user);
		return ResultCode.APP_SUCCESS;
	}

//	public static void main(String[] args) throws Exception {
//		String s = "皖A12345";
//
//		String s1 = URLEncoder.encode(s, "UTF-8");
//		System.out.println(s1);
//
//		String s2 = URLDecoder.decode(s1, "UTF-8");
//		System.out.println(s2);
//	}
	
	
	public String updatePassword(String mobile,String password,String oldPwd) {
		User user = selectUserByMobile(mobile);
		if (user == null) {
			return ResultCode.MOBILE_UNREGISTER_ERROR;
		}
		String dbPwd = user.getPassword();
		if (StringUtils.isBlank(dbPwd) || !dbPwd.equals(oldPwd)) {
			return ResultCode.PASSWORD_ERROR;
		}
		user.setPassword(password);
		userMapper.updateByPrimaryKeySelective(user);
		return ResultCode.APP_SUCCESS;
	}
	
	public String isLogin(String mobile,String token) {
		User user = selectUserByMobile(mobile);
		if (user == null) {
			return ResultCode.MOBILE_UNREGISTER_ERROR;
		}
		if (user.getStatus() != 1) {
			return ResultCode.MOBILE_INVALID;
		}
		UserLogin login = selectUserLoginByMobile(mobile);
		if (login == null) {
			return ResultCode.NO_LOGIN_ERROR;
		}
		if(StringUtils.isBlank(login.getToken()) || !login.getToken().equals(token)){
			return ResultCode.TOKEN_ERROR;
		}
		return ResultCode.APP_SUCCESS;
	}
	
	public UserLogin selectUserLoginByMobile(String mobile) {
		UserLoginExample example = new UserLoginExample();
		example.or().andMobileEqualTo(mobile);
		List<UserLogin> loginList = userLoginMapper.selectByExample(example);
		if(loginList != null && loginList.size() > 0){
			return loginList.get(0);
		}
		return null;
	}

}
