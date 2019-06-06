package com.zycj.tcc.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zycj.tcc.domain.FeeRegular;
import com.zycj.tcc.mybatis.mapper.FeeRegularMapper;
import com.zycj.tcc.scheduler.job.ParkClearJob;
import com.zycj.tcc.util.FeeRegularAdapter;
import com.zycj.tcc.util.FeeRegularInfo;

/**
 * 计费规则缓存类
 * @author 洪捃能
 *
 */
public class FeeRegularCache {
	private static Map<String,FeeRegularInfo> feeRegCache=new HashMap<String,FeeRegularInfo>();
	private final static Logger rzlog = Logger.getLogger(ParkClearJob.class);
	public static FeeRegularMapper feeRegularMapper;
	
	public static void init(){
		init(feeRegularMapper.selectByExample(null));
	}
	public static void init(List<FeeRegular> feeRegList){
		for (int i = 0; i < feeRegList.size(); i++) {
			FeeRegular fr=feeRegList.get(i);
			feeRegCache.putAll(FeeRegularAdapter.format(fr));
		}
		rzlog.info("初始化规则个数："+feeRegCache.size());
	}
	public static void reset(){
		reset(feeRegularMapper.selectByExample(null));
	}
	public static void reset(List<FeeRegular> feeRegList){
		feeRegCache.clear();
		init(feeRegList);
	}
	/**
	 * 获取缓存中的规则明细对象
	 * @param key FeeRegular.id+"-"+FeeRegular.carType
	 * @return
	 */
	public static FeeRegularInfo get(String key){
		return feeRegCache.get(key);
	}
	public static FeeRegularInfo get(Integer regId,Integer carType){
		return feeRegCache.get(regId.intValue()+"-"+carType.intValue());
	}
	public static Map<String,FeeRegularInfo> put(FeeRegular fr){
		Map<String,FeeRegularInfo> friMap=FeeRegularAdapter.format(fr);
		feeRegCache.putAll(friMap);
		return friMap;
	}
}
