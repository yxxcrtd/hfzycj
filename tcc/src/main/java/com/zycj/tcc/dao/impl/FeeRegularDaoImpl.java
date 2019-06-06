package com.zycj.tcc.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zycj.tcc.cache.FeeRegularCache;
import com.zycj.tcc.dao.FeeRegularDao;
import com.zycj.tcc.domain.FeeRegular;
import com.zycj.tcc.mybatis.mapper.FeeRegularMapper;
import com.zycj.tcc.util.FeeRegularInfo;

@Repository
public class FeeRegularDaoImpl implements FeeRegularDao {
	private final static Logger log = Logger.getLogger(FeeRegularDaoImpl.class); 
	@Autowired
	private FeeRegularMapper feeRegularMapper;
	@Override
	public FeeRegularInfo selectFeeRegularInfoForCache(Integer regId,Integer carType) {
		try {
			FeeRegularInfo fri=FeeRegularCache.get(regId, carType);//取缓存
			if(fri!=null){
				return fri;
			}
			FeeRegular fr=feeRegularMapper.selectByPrimaryKey(regId);
			if(fr==null){
				return null;
			}
			log.info("更新计费规则,ID为："+regId);
			FeeRegularCache.put(fr);//保存到缓存
			return FeeRegularCache.get(regId,carType);
		} catch (Exception e) {
			log.warn("获取计费规则并格式化异常",e);
			return null;
		}
	}
}
