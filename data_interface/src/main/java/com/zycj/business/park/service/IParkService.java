package com.zycj.business.park.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.zycj.business.park.model.CommercialParkOrder;
import com.zycj.business.park.model.Park;
import com.zycj.business.park.model.PayMentRecord;
import com.zycj.sdk.base.dao.Page;
import com.zycj.sdk.base.service.IBaseService;

import net.sf.json.JSONObject;

public interface IParkService extends IBaseService{

	/**
	 * @description 对停车场上报的出入数据进行保存
	 * @author fengya
	 * @date 2016-7-28 下午05:22:15
	 * @param cpo
	 * @throws ParseException
	 * @return void
	 */
	void saveParkRecord(CommercialParkOrder cpo) throws ParseException;
	
	/**
	 * @description 获得所有停车场记录
	 * @author fengya
	 * @date 2016-7-29 下午02:17:22
	 * @return
	 * @return List<Park>
	 */
	List<Park> findPark();
	
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
	 * @description 微信支付完成接口通知
	 * @author fengya
	 * @date 2016-8-5 下午06:10:39
	 * @param parameters
	 * @throws Exception
	 * @return void
	 */
	void wxNotify(SortedMap<String, String> parameters) throws Exception;
	
	/**
	 * @description 支付宝支付完成接口通知
	 * @author fengya
	 * @date 2016-8-5 下午06:10:39
	 * @param params
	 * @throws Exception
	 * @return void
	 */
	void aliNotify(Map<String, String> params) throws Exception;
	
	/**
	 * 移动端驶出逻辑
	 * @param params
	 * @return
	 */
	JSONObject finishPayedWithMobile(Map<String, String> params) ;
}
