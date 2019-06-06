package com.wl.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * yyyy-MM-dd
	 */
	public static final String NORMAL_DATE_FORMAT="yyyy-MM-dd";
	/**
	 * HH:mm:ss
	 */
	public static final String NORMAL_TIME_FORMAT="HH:mm:ss";
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String NORMAL_DATE_TIME_FORMAT="yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyyMMddHHmmss
	 */
	public static final String 	NAME_DATE_TIME_FORMAT="yyyyMMddHHmmss";
	/**
	 * yyyyMMddHHmmssSSS
	 */
	public static final String 	NAME_DATE_TIME_MILLI_FORMAT="yyyyMMddHHmmssSSS";
	/**
	 * 得到当前日期,格式:yyyy-MM-dd
	 * @return
	 */
	public static String getCurrDateStr(){
		return getCurrDateStr(NORMAL_DATE_FORMAT);
	}
	/**
	 * 得到当前时间,格式:HH:mm:ss
	 * @return
	 */
	public static String getCurrTimeStr(){
		SimpleDateFormat sf=new SimpleDateFormat(NORMAL_TIME_FORMAT);
		return sf.format(new Date());
	}
	/**
	 * 得到当前日期完成格式,yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrDateTimeStr(){
		SimpleDateFormat sf=new SimpleDateFormat(NORMAL_DATE_TIME_FORMAT);
		return sf.format(new Date());
	}
	/**
	 * 得到当前日期
	 * @param format 自定义日期格式
	 * @return
	 */
	public static String getCurrDateStr(String format){
		SimpleDateFormat sf=new SimpleDateFormat(format);
		return sf.format(new Date());
	}
	public static Date getEnd(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_TIME_FORMAT);
		Date date2=null;
		try {
			date2= sdf.parse(formatDate(date,NORMAL_DATE_FORMAT+" 23:59:59"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return date2;
	}
	public static Date getBegin(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_TIME_FORMAT);
		Date date2=null;
		try {
			date2= sdf.parse(formatDate(date,NORMAL_DATE_FORMAT+" 00:00:00"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return date2;
	}
	
	/**
	 * 得到指定日期和格式的时间字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date,String format){
		if(date==null){
			return null;
		}
		if(format==null || "".equals(format)){
			format=NORMAL_DATE_TIME_FORMAT;
		}
		SimpleDateFormat sf=new SimpleDateFormat(format);
		return sf.format(date);
	}
	/**
	 * 格式化日期,把字符串的日期转换成日期类型
	 * @param stime
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String stime,String format){
		if(stime==null || "".equals(stime)){
			return null;
		}
		if(format==null || "".equals(format)){
			format=NORMAL_DATE_TIME_FORMAT;
		}
		SimpleDateFormat sf=new SimpleDateFormat(format);
		try {
			return sf.parse(stime);
		} catch (ParseException e) {
			throw new RuntimeException();
		}
	}
	/**
	 * 得到当前月的第一天
	 * @return
	 */
	public static Date getMonFirstDay(){
		Calendar c=Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	/**
	 * 得到当前月的最后一天
	 * @return
	 */
	public static Date getMonEndDay(){
		Calendar c=Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY,23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	} 
	/**得到某月的最后一天*/
	public static Date getLastDateOfMonth(Date date) {  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return c.getTime();  
    } 
	/**
	 * 得到某月的第一天
	 * @author liuhui
	 * @date 2014-12-19 下午5:04:45
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));  
        return c.getTime();  
     } 
	/**
	 * 得到昨天
	 * @return
	 */
	public static Date getYesterDay(){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}
	/**
	 * 得到昨天
	 * @return
	 */
	public static Date getYesterDay(Date date){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}
	/**
	 * 得到明天
	 * @return
	 */
	public static Date getTommorrow(){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	/**
	 * 获得当天开始时间
	 * 格式：2014-12-19 0:00:00
	 * Date格式：Fri Dec 19 00:00:00 CST 2014
	 * @author liuhui
	 * @date 2014-12-19 下午12:12:53
	 * @return
	 */
	public static Date getDayStart(){
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_TIME_FORMAT);
		Date date=null;
		try {
			 date= sdf.parse(formatDate(new Date(),NORMAL_DATE_FORMAT+" 00:00:00"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	public static Date getDayStart(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_TIME_FORMAT);
		Date date=null;
		try {
			date= sdf.parse(formatDate(d,NORMAL_DATE_FORMAT+" 00:00:00"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 格式yyyy-MM-dd
	 * @param d
	 * @return
	 */
	public static Date getDayStart(String d){
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_TIME_FORMAT);
		Date date=null;
		try {
			date= sdf.parse(d+" 00:00:00");
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 获得当天结束时间
	 * 格式：2014-12-19 23:59:59
	 * Date格式：Fri Dec 19 23:59:59 CST 2014
	 * @author liuhui
	 * @date 2014-12-19 下午12:12:30
	 * @return
	 */
	public static Date getDayEnd(){
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_TIME_FORMAT);
		Date date=null;
		try {
			 date= sdf.parse(formatDate(new Date(),NORMAL_DATE_FORMAT+" 23:59:59"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	public static Date getDayEnd(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_TIME_FORMAT);
		Date date=null;
		try {
			date= sdf.parse(formatDate(d,NORMAL_DATE_FORMAT+" 23:59:59"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 格式为：yyyy-MM-dd
	 * @param d
	 * @return
	 */
	public static Date getDayEnd(String d){
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_TIME_FORMAT);
		Date date=null;
		try {
			date= sdf.parse(d+" 23:59:59");
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将秒转换为天时分秒格式数组 
	 * @param millisecond 秒数
	 * @return返回天时分秒数组
	 */
	public static String splitMilliSeconds(final long millisecond) {  
	    long unitDay = 86400;  
	    long unitHour  = 3600;  
	    long unitMinute = 60;  
	    long day,hour,minute,second = 0L;  
	    long seconds = millisecond /1000;  
	    day = seconds / unitDay;  
	    hour = seconds % unitDay / unitHour ;  
	    minute = seconds % unitDay % unitHour / unitMinute;  
	    second = seconds % unitDay % unitHour % unitMinute;  
	   // long[] tArray = {day,hour,minute,second};  
	    return day+"天"+hour+"小时"+minute+"分"+second+"秒";  
	}
	
	
	/**
	 * 将秒转换为时分格式数组 
	 * @param millisecond
	 * @return
	 */
	public static String splitMilliSecondsgetHours(final long millisecond) {  
	    long unitHour  = 3600;  
	    long unitMinute = 60;  
	    long hour,minute= 0L;  
	    hour = millisecond  / unitHour ;  
	    minute = millisecond % unitHour / unitMinute;  
	   if(minute == 0){
		   if(hour == 0){
			   return "0分钟";
		   }else{
			   return hour+"小时";
		   }
	   }else{
		   if(hour==0){
			   return minute+"分钟";
		   }else{
			   return hour+"小时"+minute+"分钟";
		   }
	   }
	}
	
	
	public static String change(Long seconds){
	    long temp=0;
	    StringBuffer sb=new StringBuffer();
	    sb.append(seconds/3600+":");
	 
	    temp=seconds%3600/60;
	    sb.append((temp<10)?"0"+temp+":":""+temp+":");
	 
	    temp=seconds%3600%60;
	    sb.append((temp<10)?"0"+temp:""+temp);
	    return sb.toString();
	}
}
