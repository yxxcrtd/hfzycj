package com.zycj.tcc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zycj.tcc.common.OrderStatus;
import com.zycj.tcc.common.ResultCode;
import com.zycj.tcc.domain.Arrear;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.domain.OrderInfoExample;
import com.zycj.tcc.domain.Pos;
import com.zycj.tcc.domain.PosExample;
import com.zycj.tcc.domain.RoadSpace;
import com.zycj.tcc.domain.RoadSpaceExample;
import com.zycj.tcc.mybatis.mapper.ArrearMapper;
import com.zycj.tcc.mybatis.mapper.OrderInfoMapper;
import com.zycj.tcc.mybatis.mapper.PosMapper;
import com.zycj.tcc.mybatis.mapper.RoadSpaceMapper;
import com.zycj.tcc.server.util.ResponseUtil;
import com.zycj.tcc.server.vo.Response;

@Component
public class TestServiceImpl {
	private final static Logger log= Logger.getLogger(TestServiceImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PosMapper posMapper;
	@Autowired
	private RoadSpaceMapper roadSpaceMapper;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private ArrearMapper arrearMapper;
	
	public Response test_DateInsert() {
//		Date mydate=new Date();
//		Map<String,String> val=new HashMap<String,String>();
//		try {
//			System.out.println("test程序时间："+DateUtil.format_YMDHMS(mydate));
//			String sql="insert into test(mydate) values(?)";
//			Thread.sleep(3000);
//			jdbcTemplate.update(sql,new Object[]{mydate});
//			Thread.sleep(3000);
//			val.put("mydate", DateUtil.format_YMDHMS(mydate));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ResponseUtil.createDataResponse(ResultCode.SUCCESS, val);
//		Thread[] thread = findAllThreads(); 
//		for ( int i = 0; i < thread.length; i++ ) {  
//            Thread t = thread[i];  
//            log.warn(
//            		"id:"+t.getId()+
//            		",Priority:"+t.getPriority()+
//            		",isAlive:"+t.isAlive()+
//            		",isDaemon:"+t.isDaemon()+
//            		",isInterrupted:"+t.isInterrupted()+
//            		",ThreadGroupName:"+t.getThreadGroup().getName()+
//            		",Name:"+t.getName());
//        } 
		List<Arrear> arrList=null;
		for (int i = 0; i < 6; i++) {
			arrList=arrearMapper.selectByExample(null);
			System.out.println(arrList.size());
		}
		return ResponseUtil.createResponse(ResultCode.SUCCESS);
	}
	
	public List<RoadSpace> test_getRoadSpaceByPos(String posNo){
		PosExample pe=new PosExample();
		pe.or().andPosNoEqualTo(posNo);
		List<Pos> posList=posMapper.selectByExample(pe);
		Pos pos=posList.get(0);
		RoadSpaceExample rse=new RoadSpaceExample();
		rse.or().andSectionIdEqualTo(pos.getSectionId());
		List<RoadSpace> roadSpaceList=roadSpaceMapper.selectByExample(rse);
		return roadSpaceList;
	}

	public List<OrderInfo> test_getOrderByEmpPos(String empNo, String posNo) {
		OrderInfoExample oie=new OrderInfoExample();
		oie.or().andInEmpEqualTo(empNo)
		.andInPosEqualTo(posNo)
		.andOrderStatusEqualTo(OrderStatus.IN.getIndex());
		return orderInfoMapper.selectByExample(oie);
	}
	public static Thread[] findAllThreads() {
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		ThreadGroup topGroup = group;

		// 遍历线程组树，获取根线程组
		while (group != null) {
			topGroup = group;
			group = group.getParent();
		}
		// 激活的线程数加倍
		int estimatedSize = topGroup.activeCount() * 2;
		Thread[] slackList = new Thread[estimatedSize];
		// 获取根线程组的所有线程
		int actualSize = topGroup.enumerate(slackList);
		// copy into a list that is the exact size
		Thread[] list = new Thread[actualSize];
		System.arraycopy(slackList, 0, list, 0, actualSize);
		return list;
	}
}
