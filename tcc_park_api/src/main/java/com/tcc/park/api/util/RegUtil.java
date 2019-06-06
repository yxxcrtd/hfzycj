package com.tcc.park.api.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
	public static boolean isMobile(String mobiles) {
		Pattern p = Pattern.compile("^(13|14|15|18|17)\\d{9}$");
		//"^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static void main(String[] args) throws IOException {
		System.out.println(isMobile("17156586585"));
	}
}
