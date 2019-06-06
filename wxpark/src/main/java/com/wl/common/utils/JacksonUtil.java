package com.wl.common.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JacksonUtil {

	/** Log */
	protected static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtil.class);
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private static SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 配置jackson映射器
	 */
	static {
		// 配置属性可见性为任意，包含private
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		// 如果是Debug模式则将输出字符串格式化
		if (LOGGER.isDebugEnabled()) {
			// 输出格式化
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		}
		mapper.setDateFormat(sf);
		// 如果属性的值为null，则在输出时不显示该属性。
		//mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		//mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}
	public static void setDateFormat(String pattern){
		if(pattern!=null && pattern!=""){
			sf.applyPattern(pattern);
		}
		mapper.setDateFormat(sf);
	}
	/**
	 * 把对象转换成json字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			LOGGER.error("对象转换成json失败", e);
			throw new RuntimeException(e);
		}
	}
	/**
	 * json字符串转换成对象
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T>T fromJson(String json,Class<T> c){
		try {
			return mapper.readValue(json, c);
		} catch (IOException e) {
			LOGGER.error("json转换成对象失败", e);
			throw new RuntimeException(e);
		}
	}
	/**
	 * json字符串转成map对象
	 * @param json
	 * @return
	 */
	public static Map<?,?> jsonToMap(String json){
		Map<?, ?> o=null;
		try {
			o = mapper.readValue(json, Map.class);
		} catch (IOException e) {
			LOGGER.error("json转换成map对象失败", e);
			throw new RuntimeException(e);
		}
		return o;
	}
	/**
	 * json字符串转成list对象
	 * @param json
	 * @return
	 */
	public static List<?> jsonToList(String json){
		List<?> o=null;
		try {
			o = mapper.readValue(json, List.class);
		} catch (IOException e) {
			LOGGER.error("json转换成map对象失败", e);
			throw new RuntimeException(e);
		}
		return o;
	}
	/**
	 * json字符串转成list泛型
	 * @param json
	 * @return
	 */
	public static List jsonToList(String json,Class<?> cs){
		List o=null;
		try {
			JavaType javaType=mapper.getTypeFactory().constructCollectionType(ArrayList.class, cs);
			o = mapper.readValue(json, javaType);
		} catch (IOException e) {
			LOGGER.error("json转换成map对象失败", e);
			throw new RuntimeException(e);
		}
		return o;
	}
}
