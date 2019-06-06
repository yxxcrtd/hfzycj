package com.zycj.tcc.server.service.impl;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.zycj.tcc.async.AsyncExecutor;
import com.zycj.tcc.async.LogInterfaceWorder;
import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.domain.LogInterface;
import com.zycj.tcc.mybatis.mapper.LogInterfaceMapper;
import com.zycj.tcc.server.domain.TransactionData;
import com.zycj.tcc.server.util.HandlerUtil;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.util.TransactionDataHelper;
import com.zycj.tcc.util.RandomUtil;

//@Component("serverWriteFutureListener")
@Deprecated
public class ServerWriteFutureListener implements ChannelFutureListener{
	private final static Logger log= Logger.getLogger(ServerWriteFutureListener.class);
	
	@Autowired
	private LogInterfaceMapper logInterfaceMapper;

	@Override
	public void operationComplete(ChannelFuture future) throws Exception {
		String key=HandlerUtil.getClientIP_Port(future.channel());
		TransactionData td=TransactionDataHelper.remove(key);
		if(td!=null){
			if(SystemConfig.No_Write_LogInterfaces.contains(td.getRequest().getT_code())){
				if(log.isDebugEnabled()){
					log.debug("返回给客户端x:《"+td.getRequest().toString()+"》《"+JSON.toJSONString(td.getResponse(),ResponseUtil.sfs)+"》  发送"+key+(future.isSuccess()?"成功！":"失败！"));
				}
				return ;
			}
			LogInterface logInter=new LogInterface();
			if(td.getRequest()!=null){
				logInter.setParams(td.getRequest().toString());
			}
			logInter.setId(RandomUtil.getSrialNoByDate());
			logInter.setReqTime(td.getReqTime());
			logInter.setResult(JSON.toJSONString(td.getResponse(),ResponseUtil.sfs));
			logInter.setSendStatus(future.isSuccess()+"");
			logInter.setRespTime(new Date());
			AsyncExecutor.execute(new LogInterfaceWorder(logInterfaceMapper,logInter));
			
			if(log.isDebugEnabled()){
				log.debug("返回给客户端:《"+logInter.getParams()+"》《"+logInter.getResult()+"》  发送"+key+(future.isSuccess()?"成功！":"失败！"));
			}
		}
	}
	
}
