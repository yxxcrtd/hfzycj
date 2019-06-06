package com.zycj.test.remote.service.test.batch;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.test.remote.service.test.batch.BatchTest.ParkFeeThread;

@Sharable
public class ClientHandler_Batch extends SimpleChannelInboundHandler<String> {
	
	private NettyClient_Batch nClient;
	private String type;
	private OrderInfo oi;
	public ClientHandler_Batch(NettyClient_Batch nClient,String type){
//		System.out.println("实例了...ClientHandler");
		this.nClient=nClient;
		this.type=type;
	}
	public ClientHandler_Batch(NettyClient_Batch nClient,String type,OrderInfo oi){
		this.nClient=nClient;
		this.type=type;
		this.oi=oi;
	}
	
	//channelRead0优先于channelReadComplete
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,String msg) throws Exception {
		InetSocketAddress isa=(InetSocketAddress) ctx.channel().localAddress();
		String key=isa.getPort()+":"+isa.getHostName();
		if("test_exitOrderByEmpPos".equals(type)){
			JSONObject rootJson=JSONObject.parseObject(msg);
			JSONArray jarr=rootJson.getJSONArray("data");
			List<OrderInfo> orderList=new ArrayList<OrderInfo>();
			for (int i = 0; i < jarr.size(); i++) {
				OrderInfo oi=jarr.getObject(i, OrderInfo.class);
				orderList.add(oi);
			}
			System.out.println("订单个数："+orderList.size());
			int unit=4000;
			HashMap<Integer,ArrayList<OrderInfo>> vals=new HashMap<Integer,ArrayList<OrderInfo>>();
			for (int i = 0; i < orderList.size(); i++) {
				int vk=i/unit;
				ArrayList<OrderInfo> val=vals.get(vk);
				if(val==null){
					val=new ArrayList<OrderInfo>();
					vals.put(vk, val);
				}
				val.add(orderList.get(i));
			}
			ExecutorService es=Executors.newCachedThreadPool();
			for(Integer k:vals.keySet()){
				es.execute(new ParkFeeThread(vals.get(k)));
			}
			es.shutdown();
			while(!es.isTerminated());  
		}else if("0005".equals(type)){
			JSONObject rs=JSON.parseObject(msg);
			if("8888".equals(rs.getString("resultCode"))){
				JSONObject data=rs.getJSONObject("data");
				String amount=data.getString("amount");
				String carNo=data.getString("carNo");
				String spaceNo=data.getString("spaceNo");
				String inTime=data.getString("inTime");
				String outTime=data.getString("outTime");
				System.out.println("id:"+oi.getId()+";carNo="+carNo+";amount="+amount+";spaceNo="+spaceNo+";inTime="+inTime+";outTime="+outTime);
//				BatchTest.batch_0006(oi.getId(),amount,outTime);
			}
		}else{
			System.out.println("Server say : " + msg+"<>"+key);
		}
		ctx.close();
//		nClient.stop();
	}

	public static void main(String[] args) {
//		String str="{\"data\":{\"amount\":7.00,\"carNo\":\"皖AA1020\",\"inTime\":\"2014-09-28 11:56:20\",\"outTime\":\"2014-09-28 13:18:08\",\"spaceNo\":\"991019\"},\"resultCode\":\"8888\"}";
//		JSONObject rs=JSON.parseObject(str);
//		if("8888".equals(rs.getString("resultCode"))){
//			JSONObject data=rs.getJSONObject("data");
//			System.out.println(data.getString("amount"));
//			System.out.println(data.getString("carNo"));
//			System.out.println(data.getString("spaceNo"));
//			System.out.println(data.getString("inTime"));
//			System.out.println(data.getString("outTime"));
//		}
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		NioSocketChannel ch=(NioSocketChannel) ctx.channel();
//		System.out.println("channelReadComplete:"+ch.isInputShutdown()+":"+ch.isOutputShutdown());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		System.out.println("Client active ");
	}
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client close ");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		System.err.println("Client:exceptionCaught");
		cause.printStackTrace();
	}
}
