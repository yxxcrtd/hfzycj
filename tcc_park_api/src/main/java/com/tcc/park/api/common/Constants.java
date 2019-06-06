package com.tcc.park.api.common;

import java.util.Locale;
import java.util.ResourceBundle;

public class Constants {
	
	public static final int PAGE_SIZE = 10;// 分页时的每页记录数
	public static final String APP_PUBLIC_KEY_URL;// 公钥文件
	public static final String APP_PRIVATE_KEY_URL;// 私钥文件
	
	
	static {
		Locale locale = new Locale("zh", "CN");
		ResourceBundle bundle = ResourceBundle.getBundle("baseconfig", locale);
		APP_PUBLIC_KEY_URL = bundle.getString("app_public_key_url");
		APP_PRIVATE_KEY_URL = bundle.getString("app_private_key_url");
	}
}
