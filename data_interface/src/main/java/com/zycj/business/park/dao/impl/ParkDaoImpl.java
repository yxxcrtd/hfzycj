package com.zycj.business.park.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zycj.business.park.dao.IParkDao;
import com.zycj.business.park.dto.CostDto;
import com.zycj.business.park.model.CommercialParkRecord;
import com.zycj.business.park.model.Park;
import com.zycj.business.park.model.PayMentRecord;
import com.zycj.business.park.model.TradeAli;
import com.zycj.business.park.model.TradeWeix;
import com.zycj.sdk.base.dao.HibernateGenericDAO;
import com.zycj.sdk.base.dao.Page;
@Repository("parkDao")
public class ParkDaoImpl extends HibernateGenericDAO implements IParkDao{

	public CommercialParkRecord getCommercialParkRecord(String parkId, String carNo, Date inTime) {
		String hql = "from CommercialParkRecord r where r.parkId=? and r.carNo=? and r.inTime=?";
		Object obj = this.findUnique(hql, parkId,carNo,inTime);
		return obj == null?null:(CommercialParkRecord)obj;
	}

	@SuppressWarnings("unchecked")
	public List<Park> findPark() {
		return this.find("from Park p");
	}

	public void findWaitCostRecord(Page page, String carNo, Integer carType) {
		String sql = "select t.id,t.parkName,t.carNo,t.inTime,t.dataType from (select r.id,p.park_name as parkName,r.car_no as carNo,r.in_time as inTime,1 as dataType from commercial_park_record r join commercial_park p on r.park_id=p.park_id and r.car_no=? and r.is_finish=0 "
					+ "UNION "
					+ "select o.id,o.section_name as parkName,o.car_no as carNo,o.in_time as inTime,0 as dataType from order_info o where o.order_status=1 and o.order_flow!=1 and o.car_no=? and o.car_type =?) as t";
		this.findPageByComSql(CostDto.class, sql, page, carNo, carNo, carType);
	}
	
	public void findCostRecord(Page page, String carNo, Integer carType) {
		String sql = "select t.id,t.parkName,t.carNo,t.inTime,t.dataType from (select r.id,p.park_name as parkName,r.car_no as carNo,r.in_time as inTime,1 as dataType from commercial_park_record r join commercial_park p on r.park_id=p.park_id and r.car_no=? and r.is_finish=1 "
			+ "UNION "
			+ "select o.id,o.section_name as parkName,o.car_no as carNo,o.in_time as inTime,0 as dataType from order_info o where o.order_status=0 and o.car_no=? and o.car_type =?) as t order by t.id desc ";
		this.findPageByComSql(CostDto.class, sql, page, carNo, carNo, carType);
	}

	public TradeWeix getTradeWeix(String tradeNo) {
		Object obj = this.findUnique("from TradeWeix tw where tw.tradeNo=?", tradeNo);
		return obj == null?null:(TradeWeix)obj;
	}

	public TradeAli getTradeAli(String tradeNo) {
		Object obj = this.findUnique("from TradeAli a where a.tradeNo=?", tradeNo);
		return obj == null?null:(TradeAli)obj;
	}

	public List<PayMentRecord> findPayMentRecord(Integer orderId) {
		return this.find("from PayMentRecord p where p.orderId=?", orderId);
	}
	
}
