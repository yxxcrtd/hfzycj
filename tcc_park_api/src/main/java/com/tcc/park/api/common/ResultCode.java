package com.tcc.park.api.common;

public class ResultCode {
	
	public static final String APP_SUCCESS = "8888";//成功
	public static final String APP_FAILED = "9999";//失败
	public static final String APP_SYSTEM_ERROR = "0000";//系统错误
	public static final String APP_PARAM_ERROR = "0001";//参数错误
	public static final String NO_LOGIN_ERROR = "0002";//用户未登陆

	public static final String MOBILE_ERROR = "1000";//手机号格式错误
	public static final String MOBILE_REGISTERED_ERROR = "1001";//手机已注册
	public static final String MOBILE_UNREGISTER_ERROR = "1002";//手机未注册
	public static final String PASSWORD_ERROR = "1003";//密码错误
	public static final String MOBILE_INVALID = "1004";//手机被冻结
	public static final String TOKEN_ERROR = "1005";//token错误
	public static final String TOKEN_EXPIRED  = "1006";//token过期
	
	
	
	public static final String CODE_OK = "2000";//验证码OK
	public static final String CODE_UNEXIT_ERROR = "2001";//验证码不存在
	public static final String CODE_VALID_ERROR = "2002";//验证码失效
	public static final String CODE_CHECKED_ERROR = "2003";//验证码已验证

}
