package com.zycj.tcc.async;

import org.apache.log4j.Logger;

import com.zycj.tcc.domain.LogInterface;
import com.zycj.tcc.mybatis.mapper.LogInterfaceMapper;

/**
 * 交易接口日志记录 工作线程
 * @author 洪捃能
 */
public class LogInterfaceWorder implements Runnable {
	private final static Logger log= Logger.getLogger(LogInterfaceWorder.class);
	
	public LogInterfaceWorder(LogInterfaceMapper logInterfaceMapper,LogInterface logInter){
		this.logInterfaceMapper=logInterfaceMapper;
		this.logInter=logInter;
	}
	private LogInterfaceMapper logInterfaceMapper;
	private LogInterface logInter;
	@Override
	public void run() {
		try {
			logInterfaceMapper.insert(logInter);
		} catch (Exception e) {
			log.warn("增加接口调用日志信息异常",e);
		}
	}

}
