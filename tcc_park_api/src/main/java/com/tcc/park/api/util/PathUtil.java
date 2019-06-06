package com.tcc.park.api.util;

import org.apache.commons.lang.StringUtils;

import com.tcc.park.api.common.Constants;

public class PathUtil {

	public static String imageAddUrl(String urlHead ,String preUrls) {
		if (StringUtils.isBlank(preUrls)) {
			return "";
		}
		String[] urlArray = preUrls.split(";");
		StringBuilder urls = new StringBuilder();
		for (String path : urlArray) {
			urls.append(urlHead).append(path).append(";");
		}
		return StringUtils.removeEnd(urls.toString(), ";");
	}
	
	
	public static String changeName(String preName,String fix) {
		if (StringUtils.isBlank(preName)) {
			return null;
		}
		if (StringUtils.isBlank(fix)) {
			fix = "_small";
		}
		String extension = preName.substring(preName.lastIndexOf("."));
		String oldName = preName.substring(0, preName.lastIndexOf("."));
		return oldName+fix+extension;
	}
	
	public static String getChangePath(String urlHead, String preName,String fix) {
		return imageAddUrl(urlHead,changeName(preName, fix));
	}
	
	public static void main(String[] args) {
//		System.err.println(getChangePath(Constants.IMAGE_URL, "/product/20140707/1404717160092.jpg", null));
	}
}
