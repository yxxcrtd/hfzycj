package com.zycj.business.park.dao;

import java.util.Date;
import java.util.List;

import com.zycj.business.park.model.CommercialParkRecord;
import com.zycj.business.park.model.Park;
import com.zycj.business.park.model.PayMentRecord;
import com.zycj.business.park.model.TradeAli;
import com.zycj.business.park.model.TradeWeix;
import com.zycj.sdk.base.dao.Page;

public interface IParkDao {
	
	/**
	 * @description 获得所有停车场记录
	 * @author fengya
	 * @date 2016-7-29 下午02:17:22
	 * @return
	 * @return List<Park>
	 */
	List<Park> findPark();
	
	/**
	 * @description 根据车牌号查询路边停车场和商业停车场中的所有的待缴费记录
	 * @author fengya
	 * @date 2016-8-2 下午05:09:50
	 * @param carNo
	 * @return
	 * @return List<CostDto>
	 */
	void findWaitCostRecord(Page page, String carNo, Integer carType);
	
	/**
	 * @description 根据车牌号查询路边停车场和商业停车场中的所有的已缴费记录
	 * @author fengya
	 * @date 2016-8-9 下午07:00:59
	 * @param page
	 * @param carNo
	 * @return void
	 */
	void findCostRecord(Page page, String carNo, Integer carType);
	
	/**
	 * @description 根据订单号获得该订单的支付记录
	 * @author fengya
	 * @date 2016-8-17 下午05:43:48
	 * @param orderId
	 * @return
	 * @return List<PayMentRecord>
	 */
	List<PayMentRecord> findPayMentRecord(Integer orderId);

	/**
	 * @description 根据停车场ID和车牌号、车辆驶入时间获得驶入的记录数据
	 * @author fengya
	 * @date 2016-7-28 上午11:37:50
	 * @param parkId
	 * @param carNo
	 * @param inTime
	 * @return
	 * @return CommercialParkRecord
	 */
	CommercialParkRecord getCommercialParkRecord(String parkId, String carNo, Date inTime);
	
	/**
	 * @description 
	 * @author fengya
	 * @date 2016-8-4 上午11:36:49
	 * @param tradeNo
	 * @return
	 * @return TradeWeix
	 */
	TradeWeix getTradeWeix(String tradeNo);
	
	/**
	 * @description 
	 * @author fengya
	 * @date 2016-8-8 下午03:32:28
	 * @param tradeNo
	 * @return
	 * @return TradeAli
	 */
	TradeAli getTradeAli(String tradeNo);
	
}
