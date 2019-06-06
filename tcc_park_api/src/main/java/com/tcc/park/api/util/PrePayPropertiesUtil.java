package com.tcc.park.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 
* @ClassName: PropertiesUtil 
* @Description: properties文件工具类 
* @author xiechanglei 
* @date 2015年11月17日 上午11:04:35 
*  
*/
public class PrePayPropertiesUtil {
	
	
	/** 
	* @Fields properties : properties, 保存根目录下setting.properties文件中保存的键值对 
	*/ 
	private static  Properties properties = null;
	
	
	/** 
	* @Fields FILE_NAME : 配置文件名称
	*/ 
	private static final String FILE_NAME = "prepaycard.properties";
	
	
	/** 
	* @Title: get 
	* @Description: 根据Key 获取properties中的对应的值
	* @param key 键
	* @return String 返回值 
	* 
	* @author xiechanglei
	* @time 2015-11-17 11:01:58
	*/
	public static String get(String key){
		if(properties == null){
			synchronized(PrePayPropertiesUtil.class){
				if(properties == null){
					loadProperties();
				}
			}
		}
		return properties.getProperty(key);
	}
	
	
	/** 
	* @Title: loadProperties 
	* @Description: 从配置文件中加载key-values   
	*
	* @author xiechanglei
	* @time 2015年11月17日 上午11:02:18 
	*/
	private static void loadProperties() {
		properties = new Properties();
		InputStream resourceAsStream = PrePayPropertiesUtil.class.getClassLoader().getResourceAsStream(FILE_NAME);
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			resourceAsStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
