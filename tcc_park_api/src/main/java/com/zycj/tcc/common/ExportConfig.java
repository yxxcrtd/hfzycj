/**
 * 
 */
package com.zycj.tcc.common;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 数据导出相关
 * Title: PathConfig.java
 * Description: 城泊二期
 * Company: zycj
 * @author wangy
 * @date 2015年4月9日
 */
public class ExportConfig {
	public static final  ResourceBundle EXPORT_BUNDLE ;
	/**
	 * 会员信息报表
	 */
	public static  String USER_REPORT_NAME;
	public static final String[] USER_REPORT_HEADERS={"name","mobile","carNo","machineNo","statusVo","createTimeStr"};
	/**
	 * 会员反馈报表
	 */
	public static  String USER_FEEDBACK_REPORT_NAME;
	public static final String[] USER_FEEDBACK_REPORT_HEADERS={"nameVo","mobileVo","machineTypeVo","content","createTimeStr"};
	
	static {
		EXPORT_BUNDLE = ResourceBundle.getBundle("reportConfig", Locale.US);
		try {
			//properties文件时utf-8
			USER_REPORT_NAME = new String(EXPORT_BUNDLE.getString("user_report_name").getBytes("ISO-8859-1"),"UTF-8");
			USER_FEEDBACK_REPORT_NAME = new String(EXPORT_BUNDLE.getString("user_feedback_report_name").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
