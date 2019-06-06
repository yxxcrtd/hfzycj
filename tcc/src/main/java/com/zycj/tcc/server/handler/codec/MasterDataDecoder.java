package com.zycj.tcc.server.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

import org.apache.log4j.Logger;

import com.zycj.tcc.server.util.HandlerUtil;
import com.zycj.tcc.server.vo.Request;
import com.zycj.tcc.util.ParamsUtil;
import com.zycj.tcc.util.RSACoder;

/**
 * 数据解码器-服务端使用
 * 把终端传送过来格式为key1=val1&key2=val2形式的数据转换成请求数据类型
 * 不可以设置为单例模式
 * @author 洪捃能
 */
public class MasterDataDecoder extends ByteToMessageDecoder {
	private final static Logger log= Logger.getLogger(MasterDataDecoder.class);
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf,List<Object> out) throws Exception {
		//如果向out中添加对象，将会触发read()方法
		if(buf==null||(buf instanceof EmptyByteBuf)||buf.readableBytes()==0){
//			System.out.println(">>>>>>>>>>>>>>>>接收到一个空的ByteBuf");
			return;
		}
		if(log.isDebugEnabled()){
			log.debug("接收到"+HandlerUtil.getClientIP_Port(ctx)+"的参数..."+buf.readableBytes());
		}
		byte[] tmp=new byte[buf.readableBytes()];
		buf.readBytes(tmp);
		String parm=new String(tmp,CharsetUtil.UTF_8);
		parm=RSACoder.decryptDataByRSA64(parm);
		Request rd=ParamsUtil.getRequestData(parm);
		out.add(rd);
	}
}
