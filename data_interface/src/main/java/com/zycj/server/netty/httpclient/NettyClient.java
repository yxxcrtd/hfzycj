package com.zycj.server.netty.httpclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import org.springframework.util.StringUtils;

import com.zycj.server.netty.handler.HttpClientInboundHandler;

public class NettyClient {
	private String ip;
	private int port;
	private static EventLoopGroup group;
	private static Bootstrap boot;
	private Channel channel;
	private String responseResult;
	
	static{
		group = new NioEventLoopGroup();
		boot = new Bootstrap();
		boot.group(group);
		boot.channel(NioSocketChannel.class);
		boot.option(ChannelOption.SO_KEEPALIVE, true);
	}

	public NettyClient(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
		boot.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
//                ch.pipeline().addLast(new HttpResponseDecoder());
//                // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
//                ch.pipeline().addLast(new HttpRequestEncoder());
                ch.pipeline().addLast("frameDecoder",new LengthFieldBasedFrameDecoder(1024 * 1024,0, 4, 0, 4));
                ch.pipeline().addLast("inCoder", new StringDecoder(CharsetUtil.UTF_8));
                ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4));
                ch.pipeline().addLast("outCoder", new StringEncoder(CharsetUtil.UTF_8));
                ch.pipeline().addLast(new HttpClientInboundHandler(NettyClient.this));
            }
        });
	}

	public void request(String reqParam) throws InterruptedException{
		channel = boot.connect(ip, port).sync().channel();
		channel.writeAndFlush(reqParam);
		channel.closeFuture();
	}
	
	public void setResponseResult(String responseResult){
		this.responseResult = responseResult;
	}
	
	public String getResponseResult(){
		int i=0;
		while(StringUtils.isEmpty(this.responseResult)){
			try {
				Thread.sleep(10);
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(i > 1000){
				break;
			}
		}
		return this.responseResult;
	}
	
//	public static void main(String[] args) throws Exception {
//		HashMap<String, String> parms = new HashMap<String, String>();
//		parms.putAll(MapUtils.getPublicMap("0005"));
//		parms.put("car_no", "皖A8L581");
//		parms.put("space_no", "00001");
//		parms.put("order_no", "7840890");
//		parms.put("r", String.valueOf(new Date().getTime()));
//		String rsaData;
//		try {
//			rsaData = RsaCodeUtils.encryptDataByRSA64(MapUtils.getParamesStr(parms));
//			NettyClient nc = new NettyClient(NettyHttpServerConfig.getNetty_ip(), NettyHttpServerConfig.getNetty_port());
//			nc.request(rsaData);
//			String result = nc.getResponseResult();
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
////			return null;
//		}
//		try {
//			rsaData = RsaCodeUtils.encryptDataByRSA64(MapUtils.getParamesStr(parms));
//			NettyClient nc = new NettyClient(NettyHttpServerConfig.getNetty_ip(), NettyHttpServerConfig.getNetty_port());
//			nc.request(rsaData);
//			String result = nc.getResponseResult();
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
////			return null;
//		}
//		
////		long l1 = System.currentTimeMillis();
////		HashMap<String, String> parms = new HashMap<String, String>();
////		parms.putAll(MapUtils.getPublicMap("1105"));
////		String rsaData;
////		rsaData = RsaCodeUtils.encryptDataByRSA64(MapUtils.getParamesStr(parms));
////		NettyClient nc = new NettyClient(NettyHttpServerConfig.getNetty_ip(), NettyHttpServerConfig.getNetty_port());
////		nc.request(rsaData);
////		System.out.println("****************request***********************" + (System.currentTimeMillis() - l1) + "****************" + nc.getResponseResult());
//	}
	
}
