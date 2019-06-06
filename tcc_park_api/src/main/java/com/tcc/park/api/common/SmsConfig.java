package com.tcc.park.api.common;

import java.util.Locale;
import java.util.ResourceBundle;

public class SmsConfig {

    
    //会员修改绑定手机短信内容
    public final static String USER_REG;
    
	public final static String SNAME;
	public final static String SPWD;
	public final static String SPRDID;
	public final static String URL;
	
	public final static String IMGURL;
	
	static{
		Locale locale = new Locale("zh", "CN");
		ResourceBundle bundle = ResourceBundle.getBundle("smsconfig", locale);
		SNAME = bundle.getString("sname");
		SPWD = bundle.getString("spwd");
		SPRDID = bundle.getString("sprdid");
		URL = bundle.getString("url");
		IMGURL = bundle.getString("img_url");
		//会员
		USER_REG = bundle.getString("user_reg");
	}
}
