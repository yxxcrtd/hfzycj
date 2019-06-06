package com.zycj.test.remote.service.test.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.OrderPayType;
import com.zycj.tcc.domain.OrderInfo;
import com.zycj.tcc.domain.Pos;
import com.zycj.tcc.domain.RoadSpace;
import com.zycj.tcc.util.RSACoder;

public class BatchTest {

	public BatchTest(){
		init();
	}
	public static String host;
	public static int port;
	public static String delimit;
	
	private void init(){
		host="114.215.192.17";//114.215.192.17 //192.168.0.118
		port = 7878;
		delimit="end";
	}
	public static boolean flag=false;
	private static String emp_no="000053";
	private static String pos_no="000057";
	private static String token="000053_b58038a8-9667-4d8b-b8a7-7855ed8784cb";
	/**获取收费员和pos对应的驶入中的订单*/
	public void test_exitOrderByEmpPos() throws Exception{
		NettyClient_Batch nc=new NettyClient_Batch(host,port,"test_exitOrderByEmpPos");
		StringBuffer sb=new StringBuffer("t_code=test_exitOrderByEmpPos");
		sb.append("&emp_no="+emp_no);
		sb.append("&pos_no="+pos_no);
		sb.append("&token="+token);
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()));
	}
	/**删除handler*/
	public void delete_handler() throws Exception{
		NettyClient_Batch nc=new NettyClient_Batch(host,port,"delete_handler");
		StringBuffer sb=new StringBuffer("t_code=delete_handler");
		sb.append("&emp_no="+emp_no);
		sb.append("&pos_no="+pos_no);
		sb.append("&token="+token);
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()));
	}
	/**修改系统配置*/
	public void updateSystemConfig() throws Exception{
		ExecutorService es=Executors.newCachedThreadPool();
		List<int[]> spList=new ArrayList<int[]>();
		int s=900001;
		int e=920000;
		int unit=1000;
		for (int i = s; i <= e;) {
			int a=i;
			i+=unit;
			int b=i-1;
			spList.add(new int[]{a,b});
		}
		for (int i = 0; i < spList.size(); i++) {
			int[] se=spList.get(i);
			es.execute(new updateSystemConfigThread(se[0],se[1]));
		}
		es.shutdown();
		while(!es.isTerminated());  
	}
	/**签到 --2000 收费员签到*/
	public void batch_0001() throws Exception{
//		for (int i = 1; i <= 2000; i++) {
			NettyClient_Batch nc=new NettyClient_Batch(host,port,"0001");
			StringBuffer sb=new StringBuffer("t_code=0001");
			sb.append("&emp_no="+emp_no);
			sb.append("&pos_no="+pos_no);
			sb.append("&pwd=96E79218965EB72C92A549DD5A330112");//密码
			nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()));
//		}
	}
	/**车辆驶入接口--2001车位不断驶入*/
	public void batch_0003() throws Exception{
		for (int i = 1; i <= 2001; i++) {
			NettyClient_Batch nc=new NettyClient_Batch(host,port,"0003");
			StringBuffer sb=new StringBuffer("t_code=0003");
			sb.append("&emp_no="+emp_no);
			sb.append("&pos_no="+pos_no);
			sb.append("&token="+token);
			sb.append("&space_no="+(990999+i));//车位编号991000
			sb.append("&car_no=皖AA"+(1000+i));//车牌号
			sb.append("&car_type="+Constants.CAR_TYPE_SMALL);//车类型
			nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()));
		}
	}
	/**车辆欠费查询接口*/
	public void batch_0004() throws Exception{
		for (int i = 1; i <= 2001; i++) {
			NettyClient_Batch nc=new NettyClient_Batch(host,port,"0004");
			StringBuffer sb=new StringBuffer("t_code=0004");
			sb.append("&emp_no="+emp_no);
			sb.append("&pos_no="+pos_no);
			sb.append("&token="+token);
			sb.append("&car_no=皖AA"+(1000+i));//车牌号
			nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()));
		}
	}
	/**驶出费用查询接口--2001车位查询停车费用*/
	public static void batch_0005(OrderInfo oi) throws Exception{
			NettyClient_Batch nc=new NettyClient_Batch(host,port,"0005",oi);
			StringBuffer sb=new StringBuffer("t_code=0005");
			sb.append("&emp_no="+emp_no);
			sb.append("&pos_no="+pos_no);
			sb.append("&token="+token);
			sb.append("&space_no="+oi.getSpaceNo());//车位编号
			sb.append("&car_no="+oi.getCarNo());//车牌号
			sb.append("&order_no="+oi.getId());//订单id
			nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()));
	}
	/**确认驶出 2001驶出*/
	public static void batch_0006(Integer orderid,String amount,String outTime) throws Exception{
//		CASH_OUT("现金驶出",1),
//		CARD_OUT("刷卡驶出",2),
//		FREE_OUT("免费驶出",3),
//		REFUSED_TO_PAY_OUT("拒缴费驶出",4),
//		ARREARAGE_OUT("欠费驶出",5),
		NettyClient_Batch nc=new NettyClient_Batch(host,port,"0006");
		StringBuffer sb=new StringBuffer("t_code=0006");
		sb.append("&emp_no="+emp_no);
		sb.append("&pos_no="+pos_no);
		sb.append("&token="+token);
		sb.append("&order_id="+orderid);//订单id
		sb.append("&amount="+amount);//应收金额
		sb.append("&reall_amount="+amount);//实收金额
		sb.append("&out_time="+outTime);//驶出时间
		sb.append("&out_type="+OrderPayType.ARREARAGE_OUT.getIndex());//驶出类型
		sb.append("&invoice_id=1111"+orderid);//发票代号批次
		sb.append("&invoice=111101"+orderid);//发票号码
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()));
	}
	
	public static void MoreThreadBatch_0003(){
		ExecutorService es=Executors.newCachedThreadPool();
		List<int[]> spList=new ArrayList<int[]>();
		int s=900001;
		int e=920000;
		int unit=20000;
		for (int i = s; i <= e;) {
			int a=i;
			i+=unit;
			int b=i-1;
			spList.add(new int[]{a,b});
		}
		for (int i = 0; i < spList.size(); i++) {
			int[] se=spList.get(i);
			es.execute(new CarEntryThread(se[0],se[1]));
		}
		es.shutdown();
		while(!es.isTerminated());  
	}
	public static void main(String[] args) throws Exception {
		BatchTest bt=new BatchTest();
//		bt.batch_0001();
//		bt.MoreThreadBatch_0003();//批量收入
		bt.test_exitOrderByEmpPos();//批量驶出 0005->0006
//		bt.updateSystemConfig();
	}
	
	//车辆驶入线程
	public static class CarEntryThread implements Runnable{
		public CarEntryThread(int s,int e){
			this.s=s;
			this.e=e;
		}
		private int s;
		private int e;
		@Override
		public void run() {
			NettyClient_Batch nc=new NettyClient_Batch(host,port,"0003");
			for (int i = s; i <= e; i++) {
				StringBuffer sb=new StringBuffer("t_code=0003");
				sb.append("&emp_no="+emp_no);
				sb.append("&pos_no="+pos_no);
				sb.append("&token="+token);
				sb.append("&space_no="+i);//车位编号991000
				sb.append("&car_no=皖"+i);//车牌号
				sb.append("&car_type="+Constants.CAR_TYPE_SMALL);//车类型
				try {
					nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	//停车费用查询线程
	public static class ParkFeeThread implements Runnable{
		public ParkFeeThread(ArrayList<OrderInfo> val){
			this.val=val;
		}
		private ArrayList<OrderInfo> val;
		@Override
		public void run() {
			for (OrderInfo oi:val) {
				try {
					BatchTest.batch_0005(oi);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	//修改系统配置线程
	public static class updateSystemConfigThread implements Runnable{
		public updateSystemConfigThread(int s,int e){
			this.s=s;
			this.e=e;
		}
		private int s;
		private int e;
		@Override
		public void run() {
			for (int i = s; i <= e; i++) {
				NettyClient_Batch nc=new NettyClient_Batch(host,port,"999999");
				StringBuffer sb=new StringBuffer("t_code=999999");
				sb.append("&checkNo=afeb6d588909");
				sb.append("&fieldName=Car_Entry_Luxury_Time");
				sb.append("&fieldVal=5");
				try {
					nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
}
