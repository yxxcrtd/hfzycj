package com.hfzycj.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Date操作工具类
 *
 * @author yxxcrtd@gmail.com
 */
public class DateUtil extends BaseUtil {

    // 标准的时间格式
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static final long ONE_SECOND = 1 * 1000L;
    private static final long ONE_MINUTE = 60 * ONE_SECOND;
    private static final long ONE_HOUR = 60 * ONE_MINUTE;
    private static final long ONE_DAY = 24 * ONE_HOUR;
    private static final long ONE_WEEK = 7 * ONE_DAY;
    private static final long ONE_MONTH = 30 * ONE_DAY; // TODO 准确性问题
    private static final long ONE_YEAR = 12 * ONE_MONTH;

    private static final String A_MOMENT_AGO = "刚刚";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_WEEK_AGO = "周前";
    private static final String ONE_MONTH_AGO = "个月前";
    private static final String ONE_YEAR_AGO = "年前";
    private static final String YESTERDAY = "昨天";
    private static final String BEFORE_YESTERDAY = "前天";

    /**
     * 显示当前时间
     *
     * @return 时间格式为：yyyy-mm-dd hh:mm:ss
     */
    public static final String getNow() {
        // Java 7
        // return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", System.currentTimeMillis());

        // For Java 8
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        return ldt.format(dtf);
    }

    /**
     * 获取系统当前时间
     *
     * @return 返回规定格式的数据，如：20140710170735665
     */
    public static final String getCurrentTime() {
        return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL", System.currentTimeMillis());
    }

    public static final String getStringDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return sdf.format(date);
    }

    /**
     * 获取系统当前时间
     *
     * @return 返回短时间格式，如：2014-07-10
     */
    public static final Date getCurruentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        String dateString = sdf.format(date);
        ParsePosition pp = new ParsePosition(0);
        return sdf.parse(dateString, pp);
    }

    public static final Date getDate(String datetime, String pattern) {
        if (StringUtils.isBlank(datetime)) {
            return null;
        }
        Date date = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            date = df.parse(datetime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static final String getRelativeTime(String beginDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        long between = 0;
        try {
            Date beginTime = sdf.parse(beginDate);
            Date endTime = sdf.parse(endDate);
            between = (endTime.getTime() - beginTime.getTime()); // 得到两者的毫秒数
        } catch (Exception e) {
            e.printStackTrace();
        }
//		long day = between / (24 * 60 * 60 * 1000);
//		long hour = (between / (60 * 60 * 1000) - day * 24);
//		long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
//		long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
//		long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
//		return day + "天" + hour + "小时" + min + "分" + s + "秒" + ms + "毫秒";
        long hour = (between / (60 * 60 * 1000));
        long min = ((between / (60 * 1000)) - hour * 60);
        return hour + "小时" + min + "分";
    }

    /**
     * 将日期格式的时间格式化：几秒前，几分钟前，几小时前，几天前，几月前，几年前
     *
     * @param date 日期类型
     * @return 美好的时间字符串
     */
    public static final String getPrettyTime(Date date) {
        if (null == date) {
            return "";
        }
        long diff = new Date().getTime() - date.getTime();
        if (diff > ONE_YEAR) {
            return (diff / ONE_YEAR) + ONE_YEAR_AGO;
        }
        if (diff > ONE_MONTH) {
            return (diff / ONE_MONTH) + ONE_MONTH_AGO;
        }
        if (diff > ONE_WEEK) {
            return (diff / ONE_WEEK) + ONE_WEEK_AGO;
        }
        if (diff > ONE_DAY) {
            return (diff / ONE_DAY) + ONE_DAY_AGO;
        }
        if (diff > ONE_HOUR) {
            return (diff / ONE_HOUR) + ONE_HOUR_AGO;
        }
        if (diff > ONE_MINUTE) {
            return (diff / ONE_MINUTE) + ONE_MINUTE_AGO;
        }
        return A_MOMENT_AGO;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getCurrentTime());
        System.out.println(getCurruentDate());
        System.out.println(getRelativeTime("2017-3-8 17:35:55", "2017-3-9 18:08:35"));
        System.out.println(getNow());
        System.out.println(getDate("2016-11-20 18:22:22", YYYY_MM_DD));
        System.out.println(getPrettyTime(getDate("2017-3-13 16:44:0", YYYY_MM_DD_HH_MM_SS)));
    }

}
