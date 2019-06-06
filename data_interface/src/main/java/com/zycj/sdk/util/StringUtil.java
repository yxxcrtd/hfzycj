package com.zycj.sdk.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	private StringUtil() {
	}

	/**
	 * 判断字符串是否为一个数字
	 */
	public static boolean isNumber(String str) {
		if (str == null || "".equals(str.trim())) {
			return false;
		}
		return str.trim().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	public static boolean isInteger(String str) {
		return str.trim().matches("^[-+]?([0-9]+)$");
	}

	/**
	 * 判断字符串是否为空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static List<Integer> idsToList(String ids) {
		if (ids == null || "".equals(ids.trim())) {
			return new ArrayList<Integer>();
		}
		List<Integer> idList = new ArrayList<Integer>();
		String[] idStr = ids.split(",");
		for (int i = 0; i < idStr.length; i++) {
			if (StringUtil.isInteger(idStr[i])) {
				idList.add(Integer.parseInt(idStr[i]));
			}
		}
		return idList;
	}
}
