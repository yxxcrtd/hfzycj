package com.tcc.park.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	private static SimpleDateFormat FORMAT_DEFAULT = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static SimpleDateFormat FORMAT_IN = new SimpleDateFormat(
			"yyyyMMddHH");

	private static SimpleDateFormat FORMAT_ALL = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat FORMAT_IN_YEAR = new SimpleDateFormat(
			"yyMMdd");

	private static void getDate() {

		// 获取前月的第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.add(Calendar.MONTH, -1);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = FORMAT_DEFAULT.format(cal_1.getTime());
		System.out.println("-----1------firstDay:" + firstDay);
		// 获取前月的最后一天
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		String lastDay = FORMAT_DEFAULT.format(cale.getTime());
		System.out.println("-----2------lastDay:" + lastDay);

		// 获取当前月第一天：
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = FORMAT_DEFAULT.format(c.getTime());
		System.out.println("===============first:" + first);

		// 获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = FORMAT_DEFAULT.format(ca.getTime());
		System.out.println("===============last:" + last);

	}

	public static String getMonthFirstString(int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return getDateStringByFormat(c.getTime(), null);
	}

	public static String getDateStringByFormat(Date date, String formatString) {
		if (date == null)
			return null;
		if (StringUtils.isEmpty(formatString))
			return DateUtil.FORMAT_DEFAULT.format(date);
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatString);
			return format.format(date);
		} catch (Exception e) {
		}
		return null;

	}

	public static boolean compareDate(Date src, Date dst) {
		return src.before(dst);
	}

	public static Integer getIntegerDate() {
		return new Integer(FORMAT_IN.format(new Date()));
	}

	public static int getInterval(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return Integer.MAX_VALUE;
		}
		Calendar date1c = Calendar.getInstance();
		date1c.setTime(date1);
		Calendar date2c = Calendar.getInstance();
		date2c.setTime(date2);
		// System.err.println(((float)date1.getTime() - (float)date2.getTime())
		// / (24 * 60 * 60 * 1000));
		// float v = ((float)date1.getTime() - (float)date2.getTime()) / (24 *
		// 60 * 60 * 1000);
		// if (v < 1 && v > 0) {
		//
		// }
		int days = (int) ((date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000));
		long m = (date1.getTime() - date2.getTime()) % (24 * 60 * 60 * 1000);
		if (m > 0) {
			days = days + 1;
		}
		return days;// (date1c.DATE - date2c.DATE);
	}

	public static String changeFormatString(String sTime, String formateSrc,
			String formateDst) {
		String time = null;
		Date temDate;
		try {
			temDate = new SimpleDateFormat(formateSrc).parse(sTime);
			if (formateDst == null) {
				time = FORMAT_ALL.format(temDate);
			} else {
				time = new SimpleDateFormat(formateDst).format(temDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public static Date getDateByFormate(String sTime, String sFormate) {
		Date date = null;
		try {
			if (sFormate != null) {
				date = new SimpleDateFormat(sFormate).parse(sTime);
			} else {
				date = FORMAT_ALL.parse(sTime);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date addDateMin(Date date, int x) {
		if (date == null ) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, x);// 24小时制
		date = cal.getTime();
		cal = null;
//		System.err.println(FORMAT_ALL.format(date));
		return date;
	}

	public static void main(String[] args) {
		// System.err.println(getDateStringByFormat(new Date(),
		// "yyyy-MM-dd HH:mm:ss"));
		// // //System.err.println(getDateStringByFormat(new Date(), "yyMMdd"));
		// // System.err.println(getDateStringByFormat(new Date(), "yyyyMMdd"));
		// System.err.println(getDateByFormate("20131125","yyyyMMdd"));
		// System.err.println(getInterval(getDateByFormate("20140210 15:53:00","yyyyMMdd HH:mm:ss"),new
		// Date()));
		System.err.println(FORMAT_ALL.format(addDateMin(new Date(), 30)));
	}

}
