package com.zycj.server.netty.handler;

import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zycj.business.park.model.CommercialParkOrder;
import com.zycj.business.park.service.IParkService;
import com.zycj.sdk.util.SpringContextUtil;
import com.zycj.server.netty.dto.ChannelConnection;
import com.zycj.server.netty.dto.Param;
import com.zycj.server.netty.dto.Result;
import com.zycj.server.util.TcpConnectionPoolUtil;

public class BusinessHandler {
	private static Logger logger = Logger.getLogger(BusinessHandler.class);
	
	/**
	 * @description 上报停车场数据
	 * @author fengya
	 * @date 2016-7-28 上午09:54:31
	 * @param result
	 * @param ctx
	 * @param om
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @return String
	 * @throws ParseException 
	 */
	public String uploadParkData(Result result, ChannelHandlerContext ctx, ObjectMapper om) throws Exception {
		//处理停车场上报数据开始
		IParkService parkService = (IParkService) SpringContextUtil.getBean("parkService");
		String data = result.getData();
		CommercialParkOrder cp = om.readValue(data, CommercialParkOrder.class);
		parkService.save(cp);
		parkService.saveParkRecord(cp);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	/**
	 * @description 处理心跳业务
	 * @author fengya
	 * @date 2016-7-27 下午05:36:09
	 * @param result
	 * @param ctx
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @return String
	 */
	public String doHeartBeat(Result result, ChannelHandlerContext ctx, ObjectMapper om) throws JsonParseException, JsonMappingException, IOException {
		logger.info("处理心跳链接开始");
		long serverTime = 0;
		String data = result.getData();
		Param param = om.readValue(data, Param.class);
		//更新连接池信息
		if(param.getPosNo()!=null){
			serverTime = addPosConnection(param.getPosNo(), ctx, param.getEmpNo());
		}else  if(param.getParkId()!=null){
			serverTime = addParkConnection(param.getParkId() , ctx);
		}
		logger.info("处理心跳链接完毕");
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(serverTime));
	}
	
	/**
	 * @description 把POS机本次的心跳加入到最新的连接池中
	 * @author fengya
	 * @date 2016-7-27 下午05:41:52
	 * @param id
	 * @param posNo
	 * @param ctx
	 * @param empNo
	 * @return
	 * @return long
	 */
	private long addPosConnection(String posNo, ChannelHandlerContext ctx, String empNo) {
		ChannelConnection conn = TcpConnectionPoolUtil.getChannelConnection(posNo);
		logger.info("判断是否是新链接");
		//新增的连接
		if (conn == null) {
			conn = new ChannelConnection(ctx.channel(), new Date().getTime());
			TcpConnectionPoolUtil.pushPool(posNo, conn);
			logger.info(posNo + " is add!");
		} else {
			//更新链接信息
			conn.setChannel(ctx.channel());
			conn.setConnectionTime(new Date().getTime());
		}
		return conn.getConnectionTime();
	}
	
	/**
	 * @description 把停车场心跳数据加入到最新的连接池中
	 * @author fengya
	 * @date 2016-7-27 下午05:50:47
	 * @param id
	 * @param parkid
	 * @param ctx
	 * @return
	 * @return long
	 */
	private long addParkConnection(String parkid, ChannelHandlerContext ctx) {
		ChannelConnection conn = TcpConnectionPoolUtil.getChannelConnection(parkid);
		if (conn == null) {
			conn = new ChannelConnection(ctx.channel(), new Date().getTime());
			TcpConnectionPoolUtil.pushPool(parkid, conn);
		} else {
			conn.setChannel(ctx.channel());
			conn.setConnectionTime(new Date().getTime());
		}
		return conn.getConnectionTime();
	}
}
