package com.zycj.tcc.async;

import java.util.List;

import org.apache.log4j.Logger;

import com.zycj.tcc.dao.OrderInfoDao;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.service.OrderInfoService;

/**
 * 清算单笔订单异步 工作线程
 * @author 洪捃能
 *
 */
public class OrderInfoWorker implements Runnable {
	private final static Logger log = Logger.getLogger(OrderInfoWorker.class);
	
	public OrderInfoWorker(OrderInfoDao orderInfoDao,OrderInfoService orderInfoService,String spaceNo){
		this.orderInfoDao=orderInfoDao;
		this.orderInfoService=orderInfoService;
		this.spaceNo=spaceNo;
	}
	private OrderInfoDao orderInfoDao;
	private OrderInfoService orderInfoService;
	private String spaceNo;
	@Override
	public void run() {
		try {
			List<OrderInfo> hisOrderList=orderInfoDao.selectOrderHistoryBySpaceNo(spaceNo);
			for(OrderInfo oi:hisOrderList){
				try {
					orderInfoService.clearOrderBySystem(oi, false);
				} catch (Exception e) {
					log.warn("异步 清算  订单id:"+oi.getId()+" 异常", e);
				}
			}
		} catch (Exception e) {
			log.warn("异步 清算  订单 异常", e);
		}
	}

}
