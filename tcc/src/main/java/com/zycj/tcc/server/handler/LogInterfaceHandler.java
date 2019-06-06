package com.zycj.tcc.server.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.AttributeKey;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zycj.tcc.async.AsyncExecutor;
import com.zycj.tcc.async.LogInterfaceWorder;
import com.zycj.tcc.common.SystemConfig;
import com.zycj.tcc.domain.LogInterface;
import com.zycj.tcc.mybatis.mapper.LogInterfaceMapper;
import com.zycj.tcc.server.domain.TransactionData;
import com.zycj.tcc.server.util.HandlerUtil;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Request;
import com.zycj.tcc.server.vo.Response;
import com.zycj.tcc.server.vo.TcodeResponse;
import com.zycj.tcc.util.DateUtil;
import com.zycj.tcc.util.RandomUtil;

/**
 * 接口调用日志处理类
 * @author 洪捃能
 *
 */
@Component
@Sharable
public class LogInterfaceHandler extends ChannelDuplexHandler{
	private final static Logger log= Logger.getLogger(LogInterfaceHandler.class);
	
	@Autowired
	private LogInterfaceMapper logInterfaceMapper;
	
	private AttributeKey<TransactionData> logkey=new AttributeKey<TransactionData>("logkey");

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception {
		if(msg instanceof Request){
			TransactionData td=new TransactionData();
			td.setRequest((Request)msg);
			td.setReqTime(new Date());
			td.setReqTimeMillis(System.currentTimeMillis());
			ctx.attr(logkey).set(td);
		}
		ctx.fireChannelRead(msg);
	}
	
	@Override
	public void write(final ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		if(msg instanceof Response){
			TransactionData td=ctx.attr(logkey).get();
			if(td==null){
				ctx.write(msg, promise);
				return;
			}
			Response resp=(Response) msg;
			if(resp instanceof TcodeResponse){
				td.setResponse(((TcodeResponse)resp).getResponse());
			}else{
				td.setResponse(resp);
			}
			td.setRespTimeMillis(System.currentTimeMillis());
			ctx.write(msg, promise).addListener(new LogInterfaceListener(ctx,td));
			return ;
		}
		ctx.write(msg, promise);
	}

//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
//		System.out.println("日志。。exceptionCaught");
//		super.exceptionCaught(ctx, cause);
//	}
	
	private class LogInterfaceListener implements ChannelFutureListener{
		public LogInterfaceListener(ChannelHandlerContext ctx,TransactionData td){
			this.ctx=ctx;
			this.td=td;
		}
		private ChannelHandlerContext ctx;
		private TransactionData td;
		@Override
		public void operationComplete(ChannelFuture future) throws Exception {
			try {
				if(td.getRequest()==null){
					if(log.isDebugEnabled()){
						log.debug((DateUtil.getSMS(td.getRespTimeMillis()-td.getReqTimeMillis()))+HandlerUtil.getClientIP_Port(ctx)+"返回给客户端no1:《》《"+JSON.toJSONString(td.getResponse(),ResponseUtil.sfs)+"》"+(future.isSuccess()?"成功！":"失败！"));
					}
					return;
				}
				if(SystemConfig.No_Write_LogInterfaces.contains(td.getRequest().getT_code())||td.getRequest().getT_code().indexOf("test_")!=-1){
					if(log.isDebugEnabled()){
						log.debug((DateUtil.getSMS(td.getRespTimeMillis()-td.getReqTimeMillis()))+HandlerUtil.getClientIP_Port(ctx)+"返回给客户端no2:《"+td.getRequest()+"》《"+JSON.toJSONString(td.getResponse(),ResponseUtil.sfs)+"》"+(future.isSuccess()?"成功！":"失败！"));
					}
					return ;
				}
				
				LogInterface logInter=new LogInterface();
				logInter.setId(RandomUtil.getSrialNoByDate());
				logInter.setParams(td.getRequest().toString());
				logInter.setResult(JSON.toJSONString(td.getResponse(),ResponseUtil.sfs));
				logInter.setReqTime(td.getReqTime());
				logInter.setRespTime(new Date());
				logInter.setSendStatus(future.isSuccess()+"");
				logInter.setIp(HandlerUtil.getClientIP_Port(ctx));
				try {
					logInter.setPosNo(DateUtil.getSMS(td.getRespTimeMillis()-td.getReqTimeMillis()));
				} catch (Exception e) {
				}
				try {
					AsyncExecutor.execute(new LogInterfaceWorder(logInterfaceMapper,logInter));
				} catch (Exception e) {
				}
				if(log.isDebugEnabled()){
					log.debug(logInter.getPosNo()+HandlerUtil.getClientIP_Port(ctx)+"返回给客户端:《"+logInter.getParams()+"》《"+logInter.getResult()+"》  发送"+logInter.getIp()+(future.isSuccess()?"成功！":"失败！"));
				}
			} catch (Exception e) {
				log.warn("监听发送数据记录 异常",e);
			}
		}
	}
}
