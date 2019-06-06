package com.zycj.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.zycj.test.vo.Msg;

public class ByteBufTest {

	ByteBuf bb=null;
	
	@Before
	public void before(){
		bb=Unpooled.buffer();
	}
	
	@Test
	public void test1(){
		Msg m=new Msg();
		m.setMsgType((short) 1001);
		m.setArgs(new Object[]{"name=hjn","age=26"});
		m.setDesc("买买提");
		String mjson=JSON.toJSONString(m);
		System.out.println("json字符串是："+mjson);
		byte[] bs=mjson.getBytes(CharsetUtil.UTF_8);
		System.out.println("byte.length:"+bs.length);
		bb=Unpooled.buffer();
		System.out.println("capacity:"+bb.capacity()+";readerIndex:"+bb.readerIndex()+";writerIndex:"+bb.writerIndex()+";readableBytes:"+bb.readableBytes());
		bb.writeBytes(bs);
		System.out.println("capacity:"+bb.capacity()+";readerIndex:"+bb.readerIndex()+";writerIndex:"+bb.writerIndex()+";readableBytes:"+bb.readableBytes());
		bb.readByte();
		System.out.println("capacity:"+bb.capacity()+";readerIndex:"+bb.readerIndex()+";writerIndex:"+bb.writerIndex()+";readableBytes:"+bb.readableBytes());
		System.out.println(bb.toString(CharsetUtil.UTF_8));
		byte[] tmp=new byte[bb.readableBytes()];
		bb.readBytes(tmp);
		System.out.println(new String(tmp,CharsetUtil.UTF_8));
		System.out.println("capacity:"+bb.capacity()+";readerIndex:"+bb.readerIndex()+";writerIndex:"+bb.writerIndex()+";readableBytes:"+bb.readableBytes());
//		bb.skipBytes(3);//设置可读字节数中的坐标，readerIndex<值<=readableBytes
//		System.out.println("capacity:"+bb.capacity()+";readerIndex:"+bb.readerIndex()+";writerIndex:"+bb.writerIndex()+";readableBytes:"+bb.readableBytes());
	}
	
	@Test
	public void test2(){
		ByteBuf bb=Unpooled.buffer(10);//创建一个容量是10的bytebuf
		bb.writeBytes("aaaaa".getBytes());//写入数据
		System.out.println(bb.capacity()+"="+bb.readerIndex()+"="+bb.writerIndex());
		bb.readBytes(2);
		System.out.println(bb.capacity()+"="+bb.readerIndex()+"="+bb.writerIndex());
		bb.discardReadBytes();
		System.out.println(bb.capacity()+"="+bb.readerIndex()+"="+bb.writerIndex());
		bb.clear();
		System.out.println(bb.capacity()+"="+bb.readerIndex()+"="+bb.writerIndex());
	}
}
