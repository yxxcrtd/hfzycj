package com.tcc.park.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class CarGuideSysUtil {
	private static final Logger log = Logger.getLogger(CarGuideSysUtil.class);
	private static String guideSysUrl;
	
	static{
		InputStream is = null;
		is = CarGuideSysUtil.class.getClassLoader().getResourceAsStream("car_guide_sys.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
			guideSysUrl = prop.getProperty("car_guide_sys_url");
		} catch (IOException e) {
			log.error("加载配置文件信息出现异常！",e);
		}
	}

	public static String getGuideSysUrl() {
		return guideSysUrl;
	}
	
}
