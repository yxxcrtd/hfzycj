package com.hfzycj.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class StringUtil extends BaseUtil {

    /**
     * 过滤微信中的特殊符号
     *
     * @param s
     * @return
     */
    public static String filterWeiXinEmojiChar(String s) {
        if (null == s || "".equals(s)) {
            return "";
        }
        return s.replaceAll("[^\\u0000-\\uFFFF]", "").trim();
    }

    /**
     * 将 Map 转换成字符串
     *
     * @param params
     * @return
     */
    public static String getParamesStr(HashMap<String, String> params) {
        String values = "";
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                values = values + entry.getKey() + "=" + entry.getValue() + "&";
            }
            values = values.substring(0, values.length() - 1);
        }
        return values;
    }

    /**
     * 获取IP地址 TODO
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        LOGGER.debug("获取到的真实IP地址：" + ip);
        return ip;
    }

    public static void main(String[] args) {
        String s = "http://wx.qlogo.cn/mmopen/wmrPF72qtRYW1S6w6bfyWPaDffoYxsdkicTQcNG8wz31w6Q5TJ6ibibQjmeOHyVJeQJeLjicuG0p8ZP3DXWuv1KicKmqZsRS4MIqr/0";
        System.out.println(s.length());
    }

}
