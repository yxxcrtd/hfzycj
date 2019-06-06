package com.zycj.tcc.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ArrayUtil {
	public static List<Integer> stringToInteger(List<String> sArray){
		if (sArray !=null) {
			List<Integer> iList = new ArrayList<Integer>(sArray.size());
			for (String string : sArray) {
				try {
					iList.add(Integer.parseInt(string));
				} catch (Exception e) {
					return null;
				}
			}
			return iList;
		}
		return null;
	}
	public static List<String> strToArray(String str){
		return strToArray(str,",");
	}
	public static List<String> strToArray(String str,String separator){
		String sep=separator==null?",":separator;
		if(StringUtils.isNotBlank(str)){
			String[] strs=str.split(sep);
			List<String> rs=new ArrayList<String>();
			for (String s:strs) {
				if(StringUtils.isNotBlank(s)){
					rs.add(s);
				}
			}
			return rs;
		}
		return null;
	}
	
	public static String list2String(List<Integer> list,String no) {
		StringBuilder builder = new StringBuilder("");
		for (Integer value : list) {
			builder.append(value).append(no);
		}
		String s = builder.toString();
		if (s.indexOf(no)>0) {
			s = s.substring(0, s.length()-1);
		}
		return s;
	}
}
