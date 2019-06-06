package com.hfzycj.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Fastjson 工具类
 *
 * @author yxxcrtd@gmail.com
 */
public class FastjsonUtil extends BaseUtil {

	/**
	 * 解析 JSONObject
	 *
	 * @param json
	 * @return
	 */
	public static JSONObject parseObject(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseObject(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析对象
	 *
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(String json, Class<T> clazz) {
		if (StringUtils.isEmpty(json) || null == clazz) {
			return null;
		}
		try {
			return JSON.parseObject(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析简单无序的 MAP
	 *
	 * @param json json串
	 *
	 * @return HashMap<String, Object>
	 */
	public static HashMap<String, Object> parseSimpleMap(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseObject(json, HashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析简单有序的 MAP
	 *
	 * @param json json串
	 *
	 * @return LinkedHashMap<String, String>
	 */
	public static LinkedHashMap<String, String> parseSimpleLinkedHashMap(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseObject(json, LinkedHashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析有序的 MAP
	 *
	 * @param json json串
	 *
	 * @return LinkedHashMap<?, ?>
	 */
	public static LinkedHashMap<?, ?> parseLinkedMap(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseObject(json, LinkedHashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析无序的 MAP
	 *
	 * @param json json串
	 *
	 * @return HashMap<?, ?>
	 */
	public static Map<?, ?> parseMap(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseObject(json, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析 List
	 *
	 * @param json
	 * @return
	 */
	public static List<String> parseSimpleList(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseArray(json, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将 JSON 转化解析成 泛型（支持简单数组、简单集合）
	 *
	 * @param json json串
	 * @param objClass 对象类型
	 * @return
	 */
	public static <T> List<T> parseList(String json, Class<T> objClass) {
		if (StringUtils.isEmpty(json) || null == objClass) {
			return null;
		}
		try {
			return JSON.parseArray(json, objClass);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对象转化成 JSON
	 *
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		if (null == obj) {
			return "";
		}
		try {
			return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteNonStringKeyAsString);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 解析返回的 JSON 字符串：{ "code" : "000", "success" : true, "data" : {"id" : 1, "time" : "2016-12-12 12:12:12", "name" : "张三" } } <br>
	 * 已知的 JSON 中包含：code 和 data，最终是将 data 中的数据转换成 Map 返回 <br>
	 * singleData 是否是单数据 <br>
	 */
	public static HashMap<String, Object> json2Map(String url, String code, Boolean singleData) {
		LOGGER.info("请求的URL是：{}，code值是：{}", url, code);

		HashMap<String, Object> map = new HashMap<>();
		try {
			String body = Jsoup.connect(url).timeout(5000).ignoreContentType(true).execute().body();
			JSONObject jsonObject = JSON.parseObject(body);
			if (null != jsonObject && code.equals(jsonObject.get("code"))) {
				String dataString = String.valueOf(jsonObject.get("data"));

				// 如果不是单数据的话，把 dataString 当作一个数组，TODO 暂时支持只获取数组的第一条数据
				if (!singleData) {
					JSONArray jsonArray = JSON.parseArray(dataString);
                    if (0 < jsonArray.size()) {
                        JSONObject dataObject = JSON.parseObject(jsonArray.get(0).toString());
                        dataString = String.valueOf(dataObject);
                    } else {
						return null;
                    }
				}
				map = (HashMap<String, Object>) parseMap(dataString);
			}
			LOGGER.info("最后map：{}", map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}


	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "{\"amount\":0.0,\"arrearAmount\":0.0,\"breakPay\":false,\"carNo\":\"皖A10333\",\"dataType\":0,\"id\":9230665,\"inTime\":\"2016-11-15 09:40:52\",\"outTime\":\"\",\"parkName\":\"芜湖路(金寨路-梅山路)北侧\",\"payRecord\":[],\"payTotal\":0.0,\"spaceNo\":\"\"}";
		System.out.println("==============" + parseMap(s));
	}

}
