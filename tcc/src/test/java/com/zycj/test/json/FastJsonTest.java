package com.zycj.test.json;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonTest {

	/**
	 *SerializerFeature.WriteDateUseDateFormat //格式化日期为yyyy-MM-dd HH:mm:ss
	 *SerializerFeature.UseSingleQuotes, // JSON是使用双引号的，javascript支持使用单引号格式的json文本
	 *SerializerFeature.WriteMapNullValue, // 输出空置字段
	 *SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
	 *SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
	 *SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
	 *SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
	 */
	Map<String,Object> map=new HashMap<String,Object>();
	
	@Before
	public void before(){
		map.put("name", "洪捃能");
		map.put("age", 23);
		map.put("date",new Date());
		map.put("waa",null);
	}
	@Test
	public void test1(){
		SerializerFeature[] sfs={
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullStringAsEmpty
		};
		
		System.out.println(JSON.toJSONStringWithDateFormat(map, "HH:mm:ss"));
		System.out.println(JSON.toJSONString(map,sfs));
	}
}
