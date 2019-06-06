package com.zycj.tcc.common;


public class Constants {
	
	public static final int EMPLOYEE_STATUS_ON=1;//收费员 在职
	public static final int EMPLOYEE_STATUS_LEAVE=0;//收费员 离职
	
	public static final int POS_STATUS_NORMAL=1;//pos终端 正常
	public static final int POS_STATUS_BROKEN=0;//pos终端 故障
	
	public static final int EMPLOYEE_ONLINE_STATUS_ON=1;//收费员 在线
	public static final int EMPLOYEE_ONLINE_STATUS_DOWN=0;//收费员 离线
	
	public static final int EMPLOYEE_LOGINOUT_TYPE_IN=1;//签到
	public static final int EMPLOYEE_LOGINOUT_TYPE_OUT=0;//签退
	public static final int EMPLOYEE_LOGINOUT_TYPE_CLEAR_OUT=2;//日终签退
	
	public static final int ROADSPACE_STATUS_OPEN=1;//车位 启用
	public static final int ROADSPACE_STATUS_CLOSE=0;//车位 关闭
	
	public static final int ROADSPACE_ISPARKED_YES=1;//车位 已停车
	public static final int ROADSPACE_ISPARKED_NO=0;//车位 未停车
	
	public static final int ROADSPACE_TYPE_BIG=1;//车位 大车位
	public static final int ROADSPACE_TYPE_SMALL=0;//车位 小车位
	
	public static final int CAR_TYPE_SMALL=1;//小车
	public static final int CAR_TYPE_BIG=2;//大车
	
	public static final int ISARREARAGE_TRUE=1;//欠费
	public static final int ISARREARAGE_FALSE=0;//未欠费
	
	public static final int ORDER_ISFREE_TRUE=1;//免费
	public static final int ORDER_ISFREE_FALSE=0;//不免费
	
	public static final int CHECK_STATUS_YES=1;//完成
	public static final int CHECK_STATUS_NO=0;//未完成
	
	public static final String Data_Send_instant="1";// 数据即时发送 
	public static final String Data_Send_repeat="2";//数据重新发送
	
	public static final int POS_SPACE_YES=1;//使用
	public static final int POS_SPACE_NO=0;//停用
	
	public static final int TICKET_STATUS_YES=1;//正常
	public static final int TICKET_STATUS_NO=2;//作废
	
	public static final int TICKET_OWNER_TYPE_PERSONAL=1;//个人
	public static final int TICKET_OWNER_TYPE_COMPANY=2;//公司
}
