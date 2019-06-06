package com.zycj.tcc.server.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

import com.alibaba.fastjson.JSON;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;

/**
 * 数据编码器-服务端使用
 * 把数据对象转换成Json字符串
 * @author 洪捃能
 */
//@Component("masterDataEncoder")
//@Sharable
public class MasterDataEncoder extends MessageToByteEncoder<Response> {
	@Override
	protected void encode(ChannelHandlerContext ctx, Response res, ByteBuf out)throws Exception {
		if(!ctx.channel().isOpen()){
			return;
		}
		String json =JSON.toJSONString(res,ResponseUtil.sfs);
		/**
		String json ="";
		if(res instanceof TcodeResponse){
			TcodeResponse tr=(TcodeResponse)res;
			json = JSON.toJSONString(tr.getResponse(),ResponseUtil.sfs);
			if(SystemConfig.Data_Compression){
				if(SystemConfig.Data_Compression_Interface.contains("all")
						||SystemConfig.Data_Compression_Interface.contains(tr.getTcode())){
//					System.out.println("压缩前："+json.getBytes().length);
					json=CompressionUtil.compress(json);
//					System.out.println("压缩后："+json.getBytes().length);
				}
			}
		}else{
			json = JSON.toJSONString(res,ResponseUtil.sfs);
		}
		*/
		
		byte[] bs = json.getBytes(CharsetUtil.UTF_8);
		out.writeInt(bs.length);
		out.writeBytes(bs);
	}
	public static void main(String[] args) {
		
	}
}
