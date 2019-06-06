package com.hfzycj.util;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

import static com.hfzycj.util.DateUtil.YYYY_MM_DD_HH_MM_SS;
import static com.hfzycj.util.DateUtil.getDate;
import static com.hfzycj.util.DateUtil.getPrettyTime;

/**
 * 在 Freemarker 格式化时间，转化成：几秒前，几分钟前，几小时前，几天前，几月前，几年前
 */
public class FreemarkerDateTimeFormat implements TemplateMethodModelEx {

    public Object exec(List args) throws TemplateModelException {
        String s = "";
        if (null != args && 0 < args.size()) {
            s = String.valueOf(args.get(0));
            if (!"".equals(s)) {
                s = getPrettyTime(getDate(s, YYYY_MM_DD_HH_MM_SS));
            }
        }
        return s;
    }

}
