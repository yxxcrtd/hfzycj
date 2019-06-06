package com.zycj.framework.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zycj.business.park.model.Park;
import com.zycj.business.park.service.IParkService;
import com.zycj.sdk.util.SpringContextUtil;

public class InitCacheData {
	private static InitCacheData instance;
	private static final Logger LOG = Logger.getLogger(InitCacheData.class);
	private Map<String, Map<String, Object>> cacheData = new HashMap<String, Map<String, Object>>();

	public enum CacheType {
		PARK
	}

	private InitCacheData() {
	}

	public static InitCacheData getInstance() {
		synchronized (InitCacheData.class) {
			if (instance == null) {
				instance = new InitCacheData();
			}
			return instance;
		}
	}

	public Map<String, Object> getCacheData(CacheType dataType) {
		return this.cacheData.get(dataType.toString());
	}

	public void initData() {
		LOG.info("************初始化机构开始******************");
		try {
			initParkData();
		} catch (Exception e) {
			LOG.info("************初始化机构失败******************", e);
		}
		LOG.info("************初始化机构完成******************");
	}
	
	private void initParkData() {
		if (cacheData.get(CacheType.PARK.toString()) != null) {
			cacheData.get(CacheType.PARK.toString()).clear();
		}
		IParkService parkService = (IParkService) SpringContextUtil.getBean("parkService");
		List<Park> parkList = parkService.findPark();
		Map<String, Object> parkMap = new HashMap<String, Object>(parkList.size());
		Iterator<Park> parkIt = parkList.iterator();
		Park park = null;
		while (parkIt.hasNext()) {
			park = parkIt.next();
			parkMap.put(park.getParkId(), park);
		}
		cacheData.put(CacheType.PARK.toString(), parkMap);
	}
}
