package com.zycj.tcc.common;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.zycj.tcc.util.PropertiesHelper;

public class SystemConfig {
	private final static Logger log= Logger.getLogger(SystemConfig.class);
	private static PropertiesHelper properties = new PropertiesHelper(new Properties());
	//停车位清算 时间
	public static String Park_Clear_Job_CronExpression;
	//停车位清算 时间 次日
	public static String Park_Clear_Job_CronExpression_Tomorrow;
	//pos终端停车位分页每页显示个数
	public static int POS_Parking_PageSize;
	//调用接口自动把参数封装到Request对象的字段，根据","分隔
	public static String No_Write_LogInterface;
	public static Set<String> No_Write_LogInterfaces;
	//不需要校验是否登录的接口编号，多个以","分隔
	public static String No_CheckLogin_Interface;
	public static Set<String> noCheckInterface;
	//支持重发的接口，多个以","分隔
	public static String repeat_Interface;
	//修改系统配置的效验码
	public static String Update_SystemConfig_CheckNo;
	//收费员做驶入操作的容错时间 单位：分钟
	public static int Car_Entry_Luxury_Time;
	//欠费记录分页每页显示个数
	public static int ARREAR_PAGESIZE;
	public static int CAR_OUT_WAIT_TIME;
	//异步程序工作线程数
	public static int Async_Executor_ThreadCount;
	//#多笔欠费补缴 金额分配方式：1：平均分、2：按比例分
	public static int Arrear_Funds_Distribution_Mode;
	//#是否开启响应数据压缩 true:开启，false:关闭
	public static boolean Data_Compression=false;
	//#需要数据压缩的交易接口，所有数据都要压缩设置为：all,所有都不需要压缩为：none,只有某个接口需要压缩为：接口编号,接口编号(多个接口以,分隔)
	public static int SYSTEM_TYPE = 0;
	public static Set<String> Data_Compression_Interface;
	static {
		try {
			properties.loadPropertiesFromClassLoader("system.properties");
			Park_Clear_Job_CronExpression=properties.getProperty("Park_Clear_Job_CronExpression","0 0 23 * * ?");
			Park_Clear_Job_CronExpression_Tomorrow=properties.getProperty("Park_Clear_Job_CronExpression_Tomorrow","0 10 00 * * ?");
			POS_Parking_PageSize=properties.getInt("POS_Parking_PageSize", 20);
			No_Write_LogInterface=","+properties.getProperty("No_Write_LogInterface","")+",";
			No_Write_LogInterfaces=getSetByStr(No_Write_LogInterface);
			No_CheckLogin_Interface=properties.getProperty("No_CheckLogin_Interface", "");
			noCheckInterface=getSetByStr(No_CheckLogin_Interface);
			Update_SystemConfig_CheckNo=properties.getProperty("Update_SystemConfig_CheckNo","zycj");
			Car_Entry_Luxury_Time=properties.getInt("Car_Entry_Luxury_Time", 0);
			ARREAR_PAGESIZE	 = properties.getInt("arrear_pagesize", 10);
			Async_Executor_ThreadCount=properties.getInt("Async_Executor_ThreadCount", 50);
			repeat_Interface=properties.getProperty("repeat_Interface","");
			Arrear_Funds_Distribution_Mode=properties.getInt("Arrear_Funds_Distribution_Mode", 2);
			Data_Compression=properties.getBoolean("Data_Compression", false);
			Data_Compression_Interface=getSetByStr(properties.getProperty("Data_Compression_Interface",""));
			SYSTEM_TYPE = properties.getInt("System_type", 0);
			CAR_OUT_WAIT_TIME = properties.getInt("car_out_wait_time", 5);
		} catch (IOException e) {
			log.warn("加载system.properties文件异常",e);
		}
	}
	public static void main(String[] args) {
		String[] s="".split(",");
		for (String a:s) {
			System.out.println(">"+a+"<");
		}
	}
	public static Set<String> getSetByStr(String code){
		Set<String> rs=new HashSet<String>();
		if(StringUtils.isBlank(code)){
			return rs;
		}
		String[] str=code.split(",");
		for (String s:str) {
			if(StringUtils.isNotBlank(s)){
				rs.add(s);
			}
		}
		return rs;
	}
}
