/**
 * 
 */
package com.zycj.tcc.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.dao.LogSystemMapper;
import com.zycj.tcc.domain.criteria.LogSystemCriteria;
import com.zycj.tcc.domain.vo.LogSystemVo;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.exception.TccException;
import com.zycj.tcc.service.LogService;

/**
 * Title: LogServiceImpl.java
 * Description: 城泊二期
 * Company: zycj
 * @author wangy
 * @date 2015年4月13日
 */
@Service("LogService")
public class LogServiceImpl implements LogService {
	private static final Logger logger = LoggerFactory
			.getLogger(LogServiceImpl.class);
	@Autowired
	private LogSystemMapper logMapper;
	@Override
	public void add(SysAdminVo currAdmin, LogSystemVo log) throws TccException,
			Exception {
		if(null==log){
			throw new TccException("操作日志数据不完整，操作失败");
		}
		Date date = new Date();
		log.setIp(currAdmin.getIp());
		log.setCreateTime(date);
		log.setOperatorId(currAdmin.getId());
		log.setOperatorName(currAdmin.getAdminName());
		log.setCreateTime(date);
		int rows = logMapper.insertSelective(log);
		if(Constants.ONE_RETURN_ROWS!=rows){
			throw new TccException("操作数据库出错，操作失败");
		}
	}

	@Override
	public List<LogSystemVo> list(SysAdminVo currAdmin,
			LogSystemCriteria criteria) {
		logger.info("LogService list" + currAdmin + "--" + criteria);
		// 获取 总记录数
				int count = logMapper.count(criteria);
				criteria.setCount(count);
				if (count > 0) {
					criteria.setCurrentPage(criteria.getPage());
					return logMapper.list(criteria);
				}
				return null;
	}

}
