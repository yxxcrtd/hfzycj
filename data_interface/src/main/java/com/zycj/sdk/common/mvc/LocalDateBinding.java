package com.zycj.sdk.common.mvc;

import java.text.SimpleDateFormat;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by yi.wang1 on 2015/6/1.
 */
public class LocalDateBinding implements WebBindingInitializer {


    public void initBinder(WebDataBinder binder, WebRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setLenient(false);
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        datetimeFormat.setLenient(false);
        //使用系统自带的日期绑定，只支持日期类型数据（日期时间请用java.util.Date，时间请用java.sql.Time）
//        binder.registerCustomEditor(java.util.Date.class, new org.springframework.beans.propertyeditors.CustomDateEditor(dateFormat, true));
        //使用本地的日期绑定：支持自动根据日期字符串类型转换日期，日期时间和时间类型数据，注意：1970-1-1的日期时间类型会变成时间类型
        binder.registerCustomEditor(java.sql.Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(java.sql.Time.class, new CustomTimeEditor(timeFormat, true));
        binder.registerCustomEditor(java.util.Date.class, new CustomTimestampEditor(datetimeFormat, true));
    }

}
