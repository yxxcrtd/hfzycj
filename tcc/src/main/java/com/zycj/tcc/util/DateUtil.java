package com.zycj.tcc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * @author 洪捃能
 *
 */
public class DateUtil {
	public static Pattern datePattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");

//	public static final SimpleDateFormat SDF_YMDHMS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	public static final SimpleDateFormat SDF_YMD=new SimpleDateFormat("yyyy-MM-dd");
//	public static final SimpleDateFormat SDF_HMS=new SimpleDateFormat("HH:mm:ss");
//	public static final SimpleDateFormat SDF_HM=new SimpleDateFormat("HH:mm");
	
	private static final String format_YMDHMS="yyyy-MM-dd HH:mm:ss";
	private static final String format_YMD="yyyy-MM-dd";
	private static final String format_HMS="HH:mm:ss";
	private static final String format_HM="HH:mm";
	
	private static ThreadLocal<SimpleDateFormat> YMDHMS = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> YMD = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> HMS = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> HM = new ThreadLocal<SimpleDateFormat>();
	
	/**以下时间都是基于毫秒的*/
	public static final int MS=1000;//毫秒
	public static final int SEC_MS=1*MS;//1秒=1000毫秒
	public static final int SEC=1;//1秒
	public static final int MIN_MS=60*SEC_MS;//1分钟
	public static final int MIN=60;//1分钟=60秒
	public static final int QR_MS=15*MIN_MS;//一刻钟
	public static final int QR=15*MIN;//一刻钟
	public static final int HALF_H_MS=30*MIN_MS;//半小时
	public static final int HALF_H=30*MIN;//半小时
	public static final int H_MS=60*MIN_MS;//一小时
	public static final int H=60*MIN;//一小时
	
	public static SimpleDateFormat getYMDHMS(){
		SimpleDateFormat sdf = YMDHMS.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_YMDHMS);
			YMDHMS.set(sdf);
		}
		return sdf;
	}
	public static SimpleDateFormat getYMD(){
		SimpleDateFormat sdf = YMD.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_YMD);
			YMD.set(sdf);
		}
		return sdf;
	}
	public static SimpleDateFormat getHMS(){
		SimpleDateFormat sdf = HMS.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_HMS);
			HMS.set(sdf);
		}
		return sdf;
	}
	public static SimpleDateFormat getHM(){
		SimpleDateFormat sdf = HM.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_HM);
			HM.set(sdf);
		}
		return sdf;
	}
	public static Date parse_YMDHMS(String ds) throws ParseException{
		return getYMDHMS().parse(ds);
	}
	public static Date parse_YMD(String ds) throws ParseException{
		return getYMD().parse(ds);
	}
	public static Date parse_HMS(String ds) throws ParseException{
		return getHMS().parse(ds);
	}
	public static Date parse_HMS(Date d) throws ParseException{
		return parse_HMS(getHMS().format(d));
	}
	public static String format_YMD(Date d) throws ParseException{
		return getYMD().format(d);
	}
	public static String format_YMDHMS(Date d) throws ParseException{
		return getYMDHMS().format(d);
	}
	public static String format_YMDHMSx(Date d){
		try {
			return getYMDHMS().format(d);
		} catch (Exception e) {
			return getYMDHMS().format(new Date());
		}
	}
	public static String format_HMS(Date d) throws ParseException{
		return getHMS().format(d);
	}
	public static String format_HM(Date d) throws ParseException{
		return getHM().format(d);
	}
	
	public static Date parseByFormat(String dateStr,String format) throws ParseException{
		return new SimpleDateFormat(format).parse(dateStr);
	}
	
	public static Date getLastTimeForDay() throws ParseException{
		return parse_YMDHMS(format_YMD(new Date())+" 23:30:00");
	}
	public static String getSFM(long ms){
		int ms_y=(int) (ms%MS);
		int ms_s=(int) (ms/MS);
		int sec=ms_s%MIN;
		int min=ms_s/MIN;
		return min+"分"+sec+"秒"+ms_y+"毫秒";
	}
	//秒毫秒
	public static String getSMS(long ms){
		int ms_y=(int) (ms%MS);
		int sec=(int) (ms/MS);
		return sec+"秒"+ms_y+"毫秒";
	}
	/**
	 * 判断日期是否是今天
	 * @param target 需要校验的时间
	 * @return true:是今天;false：不是今天
	 * @throws ParseException 日期转换异常
	 */
	public static boolean isToday(Date target) throws ParseException{
		return format_YMD(target).equals(format_YMD(new Date()));
	}
	/**
	 * 判断两个日期是否同一天
	 * @param s
	 * @param e
	 * @return
	 * @throws ParseException
	 */
	public static boolean isSameDay(Date s,Date e) throws ParseException{
		return format_YMD(s).equals(format_YMD(e));
	}
	/**
	 * 获得时间差 精确到分钟
	 * @param sd 开始时间
	 * @param ed 结束时间
	 * @return 时间差值多少秒
	 */
	public static long getTimeDiffByMin(Date sd,Date ed){
		return getTimeDiffByUnit(sd,ed,MIN_MS);
	}
	/**
	 * 获得时间差 精确到秒
	 * @param sd 开始时间
	 * @param ed 结束时间
	 * @return 时间差值多少秒
	 */
	public static long getTimeDiffBySec(Date sd,Date ed){
		return getTimeDiffByUnit(sd,ed,SEC_MS);
	}
	public static long getTimeDiffBySec(String sd,String ed) throws ParseException{
		return getTimeDiffBySec(parse_YMDHMS(sd),parse_YMDHMS(ed));
	}
	/**
	 * 获得时间差
	 * @param sd 开始时间
	 * @param ed 结束时间
	 * @param unit 单位
	 * @return 根据单位获得的时间差值
	 */
	public static long getTimeDiffByUnit(Date sd,Date ed,int unit){
		Calendar cal1=Calendar.getInstance();
		cal1.setTime(sd);
		long s=cal1.getTimeInMillis()/unit;
		Calendar cal2=Calendar.getInstance();
		cal2.setTime(ed);
		long e=cal2.getTimeInMillis()/unit;
		cal1=null;
		cal2=null;
		return e-s;
	}
	public static long getTimeInMillis(Date d){
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		Long s=cal.getTimeInMillis();
		cal=null;
		return s;
	}
	/**
	 * 分钟转成秒
	 * @param m 多少分钟
	 * @return 多少秒
	 */
	public static long getSecByMin(long m){
		return m*SEC;
	}
	/**
	 * 小时转成秒
	 * @param h 多少小时
	 * @return 多少秒
	 */
	public static long getSecByHour(long h){
		return h*H;
	}
	
	/**
	 * 匹配日期格式
	 * @param dateStr 日期字符串
	 * @return ture:有效的日期格式；false:无效的日期格式
	 */
	public static boolean matches(String dateStr){
		return datePattern.matcher(dateStr).matches();
	}
	
	/**
	 * 新能源卡免费停车时间
	 * @param d
	 * @return
	 */
	public static Date getLateTime(Date d, int hour){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.HOUR_OF_DAY,hour);//小时加两个小时
        Date re = cal.getTime();
		return re;
	}
	
	/**
	 * //计费时间往前推免费时间
	 * @param d
	 * @param freeTime
	 * @return
	 */
	public static Date getBeforeFreeTime(Date d,int freeTime){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE,freeTime *(-1) );
        Date re = cal.getTime();
		return re;
	}
	
}
