package com.wl.common;
/**
 * 系统常量类
 * @author Administrator
 *
 */
public class Constants {
	/** 当前登录用户session标识 */
	public static final String CURRENT_OPENID = "open_id";
	
	/**支付宝的相关的业务流程**/
	public static final String CURRENT_USERID="userId";
	
	/**返给客户端状态信息，正常：200，异常：500*/
	public static final int RETURN_SUCCESS=200;
	/**返给客户端状态信息，正常：200，异常：500*/
	public static final int RETURN_ERROR=500;
	/**返给客户端状态信息， 服务器session失效*/
	public static final int RETURN_SESSION_ERROR=520;
	public static final int RETURN_CODE_ERROR=201;
	/**页面提示信息 保存成功*/
	public static final String RETURN_SUCCESS_ADD="保存成功";
	/**页面提示信息 删除成功*/
	public static final String RETURN_SUCCESS_DEL="删除成功";
	/**页面提示信息 修改成功*/
	public static final String RETURN_SUCCESS_UPDATE="修改成功";
	/**页面提示信息 保存失败*/
	public static final String RETURN_ERROR_ADD="保存失败";
	/**页面提示信息 删除失败*/
	public static final String RETURN_ERROR_DEL="删除失败";
	/**页面提示信息 修改失败*/
	public static final String RETURN_ERROR_UPDATE="修改失败";
	/**页面提示信息 请求参数错误*/
	public static final String RETURN_ERROR_REQUEST="请求参数错误";
	/**状态 0:禁用，1：启用*/
	public static final String STATUS_ON="1";
	/**状态 0:禁用，1：启用*/
	public static final String STATUS_OFF="0";
	
	/**10M*/
	public static long FILE_MAX_SIZE=10485760;
	/**用户默认密码*/
	public static final String USER_DEFUATL_PASSWD="123456";
	
	/** 当前websocket用户session标识 */
	public static final String WEBSOCKET_USERNAME = "websocket_user_profile";
	/**flash代理请求websocket端口号*/
	public static final int FlashPolicy_PORT=843;
	
	/**普通	0	普通的发言消息*/
	public static final byte MSG_TYPE_0=0;
	/**系统	1	系统消息*/
	public static final byte MSG_TYPE_1=1;
	/**主播	2	主播的发言消息*/
	public static final byte MSG_TYPE_2=2;
	/**游客消息	3	游客的发言*/
	public static final byte MSG_TYPE_3=3;
	/**随机码变量*/
	public static final String RANDOM_CODE_SESSION="randomCode";
	/**手机验证码获取地址*/
	//public static final String PHONE_CHECK_CODE_URL="http://qxt.ko.cn:8080/ICTGWS/getCheckCode.do";
	/**手机短信验证码*/
	//public static final String PHONE_SMS_CODE_URL="http://qxt.ko.cn:8080/ICTGWS/sendsms.do";
	/**短信模板特征串*/
	public static final String PHONE_SMS_CODE_SIGNATURE="ZDJZB000S99";
	/**短信模板编号*/
	public static final String PHONE_SMS_CODE_TEMPLATEID="model1";
	/**模板中对应参数串，多个间使用;;_;;分隔*/
	public static final String PHONE_SMS_CODE_SPLITE=";;_;;";
	/**随机码*/
	public static final String PHONE_RANDOM_CODE="randomCode";
	
	/** 当前登录用户登录的房间标识 */
	public static final String CURRENT_ROOM_ID = "room_id";
	/**主播来源ID，web端*/
	public static final String ANCHOR_FROM_WEB_ID="anchor_from_web_id";
	
	/** cms房间ID */
	public static final String CMS_ROOM_ID = "0000";
}
