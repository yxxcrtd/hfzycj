package com.zycj.server.netty.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zycj.sdk.util.StringUtil;
import com.zycj.server.netty.dto.ParkCost;
import com.zycj.server.netty.dto.Result;
import com.zycj.server.netty.dto.ResultData;
import com.zycj.server.util.NettyConstant;
import com.zycj.server.util.ThreadMessageUtil;

/**
 * @description 数据接收和发送业务实现类
 * @author fengya
 * @date 2016-7-27 下午04:46:47
 */
public class TcpServerHandler extends ChannelHandlerAdapter {
	private static Logger logger = Logger.getLogger(TcpServerHandler.class);
	private BusinessHandler handler;
	public static ObjectMapper om = new ObjectMapper();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 业务处理出现异常的处理结果
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (ctx != null) {
			cause.printStackTrace();
			Result result = new Result();
			result.setCode(NettyConstant.CODE_SERVER_ERROR);
			result.setData(om.writeValueAsString(new ResultData("服务器错误", dateFormat.format(new Date()))));
			logger.error("商业停车场连接出现中断异常",cause);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		this.handler = new BusinessHandler();
		Result result = om.readValue(msg.toString(), Result.class);
		logger.info(msg);
		if (result != null) {
			logger.info("执行请求!");
			String serverTime = "";
			if (NettyConstant.CODE_HEART_BEAT_REQUEST.equals(result.getCode())) {
				// 心跳业务处理
				Result rs = new Result();
				rs.setCode(result.getCode());
				rs.setQueryId(result.getQueryId());
				try {
					serverTime = this.handler.doHeartBeat(result, ctx, om);
					rs.setData(om.writeValueAsString(new ResultData("success", serverTime)));
				} catch (Exception e) {
					rs.setData(om.writeValueAsString(new ResultData("failed", serverTime)));
					logger.error("心跳业务处理异常",e);
				}
				ctx.writeAndFlush(om.writeValueAsString(rs));
				ctx.writeAndFlush("\n");
			} else if (NettyConstant.CODE_COMMERCIAL_PARK_UPLOAD_DATA.equals(result.getCode())) {
				// 停车场上报停车数据业务处理
				Result rs = new Result();
				rs.setCode(result.getCode());
				rs.setQueryId(result.getQueryId());
				try {
					serverTime = this.handler.uploadParkData(result, ctx, om);
					rs.setData(om.writeValueAsString(new ResultData("success", serverTime)));
				} catch (Exception e) {
					e.printStackTrace();
					rs.setData(om.writeValueAsString(new ResultData("failed", serverTime)));
					logger.error("停车场上报停车数据业务处理出现异常",e);
				}
				ctx.writeAndFlush(om.writeValueAsString(rs));
				ctx.writeAndFlush("\n");
			} else if (NettyConstant.CODE_COMMERCIAL_PARK_QUERY_FEE.equals(result.getCode())) {
				if(!StringUtil.isEmpty(result.getQueryId())){
					// android请求获取车牌号对应的车辆欠费金额
					ParkCost parkCost = om.readValue(result.getData(), ParkCost.class);
					ThreadMessageUtil.push(Long.parseLong(result.getQueryId().trim()), parkCost);
				}
			} else if (NettyConstant.CODE_COMMERCIAL_PARK_PAY_SUCCESS_RES.equals(result.getCode())){
				if(!StringUtil.isEmpty(result.getQueryId())){
					JSONObject jsonData = JSONObject.fromObject(result.getData());
					ThreadMessageUtil.push(Long.parseLong(result.getQueryId().trim()), jsonData);
				}
			}
		} else {
			logger.info("message is null");
			ctx.close();
		}
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
	}

}
