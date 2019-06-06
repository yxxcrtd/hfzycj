package com.wl.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 系统配置文件工具类
 * 读取system.propertis文件
 * @author Administrator
 *
 */
public class SystemConfigUtil {
	
	private final static Properties sysFile=new Properties();
	
	private final static String SYSTEM_PROPERTIES_PATH= "conf/system.properties";
	
	private SystemConfigUtil(){
		
	}
	
	/**
	 * 从缓存里得到system.properties的值
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		if(key==null || "".equals(key)){
			return null;
		}
		return sysFile.getProperty(key);
	}
	/**
	 * 初始化system.properties全局配置文件
	 * @return
	 * @throws IOException
	 */
	public static void init() throws IOException{
		InputStream inStream=SystemConfigUtil.class.getClassLoader().getResourceAsStream(SYSTEM_PROPERTIES_PATH);
		sysFile.load(inStream);
	}
}
