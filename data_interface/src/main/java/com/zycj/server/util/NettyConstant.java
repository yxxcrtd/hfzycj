package com.zycj.server.util;
/**
 * @description 业务处理常量
 * @author fengya
 * @date 2016-7-27 下午04:48:45
 */
public class NettyConstant {
	//系统错误的返回code
	public static final String CODE_SERVER_ERROR = "10000";
	//心跳的请求与相应
	public static final String CODE_HEART_BEAT_REQUEST = "00001";
	//泊位号状态变动code
	public static final String CODE_SPACE_STATUS_CHANGE = "10001";
	//-------------------------------合肥商业停车场数据上报
	public static final String CODE_COMMERCIAL_PARK_UPLOAD_DATA = "20001";
	public static final String CODE_COMMERCIAL_PARK_QUERY_FEE = "20002";
	public static final String CODE_COMMERCIAL_PARK_PAY_SUCCESS_RES = "20003";
}
