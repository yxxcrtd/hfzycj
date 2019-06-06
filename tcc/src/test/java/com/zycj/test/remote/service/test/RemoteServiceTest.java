package com.zycj.test.remote.service.test;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.OrderPayType;
import com.zycj.tcc.util.RSACoder;
import com.zycj.test.client.NettyClient;



public class RemoteServiceTest {

	public RemoteServiceTest(){
		init();
	}
	public static String host;
	public static int port;
	public static String delimit;
	
	private void init(){
		host="127.0.0.1";//192.168.0.118 //114.215.192.17 //127.0.0.1
		port = 9090;
		delimit="end";
	}
	private static String token="000053_ba860bf6-8f89-40ff-9664-7e14d211bc16";
	private static String emp_no="000053";
	private static String pos_no="000057";
	private static String baseParams=null;
	static{
//		emp_no="000016";
//		pos_no="000016";
//		token="000016_832a9ae3-9baa-4590-a6bf-2ae1ec308253";
		StringBuffer sb=new StringBuffer();
		sb.append("&emp_no="+emp_no);
		sb.append("&pos_no="+pos_no);
		sb.append("&token="+token);
		sb.append("&space_no=-1");//车位编号
		sb.append("&car_no=皖AA1005");//车牌号
		sb.append("&order_no=13320");//订单id
		sb.append("&order_id=13320");//订单id
		baseParams=sb.toString();
	}
	
	/**修改系统配置*/
	public void test_updateSystemConfig() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		nc.sendData(RSACoder.encryptDataByRSA64("t_code=999999&checkNo=afeb6d588909&fieldName=POS_Parking_PageSize&fieldVal=10"));
	}
	/**校验是否登录*/
	public void test_checkLogin() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		nc.sendData(RSACoder.encryptDataByRSA64(baseParams));
	}
	/**测试 时间插入数据库*/
	public void test_DateInsert() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		nc.sendData(RSACoder.encryptDataByRSA64("t_code=test_DateInsert"));//&sleep=11
	}
	/**刷新计费规则缓存
	 * @throws Exception */
	public void updateCache_FeeRegular() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		nc.sendData(RSACoder.encryptDataByRSA64("t_code=updateCache_FeeRegular"));
	}
	/**签到 */
	public void test_0001() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0001");
		sb.append("&pwd=96E79218965EB72C92A549DD5A330112");//密码
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**获取全部车位*/
	public void test_0016() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0016");
		sb.append("&type=1");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**获取分页车位*/
	public void test_0002(int type) throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0002");
		sb.append("&page_no=1");//页数
		sb.append("&type="+type);
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**车辆驶入接口*/
	public void test_0003() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0003");
//		sb.append("&space_no=288086");//车位编号
//		sb.append("&car_no=皖A33333");//车牌号
		sb.append("&car_type="+Constants.CAR_TYPE_SMALL);//车类型
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**车辆欠费查询接口*/
	public void test_0004() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0004");
//		sb.append("&car_no=皖A33333");//车牌号
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**驶出费用查询接口*/
	public void test_0005() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0005");
//		sb.append("&car_no=皖A33333");//车牌号
//		sb.append("&space_no=288086");//车位编号
//		sb.append("&order_no=12");//订单id
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**确认驶出*/
	public void test_0006() throws Exception{
//		CASH_OUT("现金驶出",1),
//		CARD_OUT("刷卡驶出",2),
//		FREE_OUT("免费驶出",3),
//		REFUSED_TO_PAY_OUT("拒缴费驶出",4),
//		ARREARAGE_OUT("欠费驶出",5),
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0006");
//		sb.append("&order_id=12");//订单id
		sb.append("&amount=19");//应收金额
		sb.append("&reall_amount=0");//实收金额
		sb.append("&out_time=2014-09-23 11:45:07");//驶出时间
		sb.append("&out_type="+OrderPayType.ARREARAGE_OUT.getIndex());//驶出类型
		sb.append("&invoice_id=111111");//发票代号批次
		sb.append("&invoice=11111101");//发票号码
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**员工修改登录密码*/
	public void test_0011() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0011");
		sb.append("&old_pwd=111122");//旧密码
		sb.append("&new_pwd=111111");//新密码
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**签退查询*/
	public void test_0012() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0012");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**签退*/
	public void test_0017() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0017");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**校验IP地址、端口号、终端编号*/
	public void test_0020() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0020");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**车辆欠费列表查询分页*/
	public void test_0007() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0007");
		sb.append("&page_no=1");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**车辆欠费补缴*/
	public void test_0008() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0008");
		sb.append("&reall_amount=2.05").append("&amount=3.00").append("&id_list=1").append("&out_type=1")
		.append("&invoice_id=2342345345").append("&invoice=9999999999");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**收费员扎帐查询*/
	public void test_0009() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0009");
//		sb.append("&reall_amount=2.05").append("&amount=3.00").append("&id_list=1").append("&out_type=1")
//		.append("&invoice_id=2342345345").append("&invoice=9999999999");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**根据日期查询收费记录*/
	public void test_0019() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0019");
		sb.append("&key=20140925");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**查询当天的驶入驶出信息*/
	public void test_0013() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0013");
		sb.append("&key=皖A00002");
		sb.append("&type=1");// 1表示车牌号查询 2表示泊位号查询
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**提交收费员扎帐信息接口*/
	public void test_0010() throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0010");
		sb.append("&date=2014-09-10").append("&amount=3.00").append("&request_time=2014-09-19 16:53:57").append("&is_today=0")
		.append("&invoice_id=2342345345").append("&invoice=000000000").append("&check_id=1");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**重新打印发票日志信息上传接口*/
	public void test_0021()throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0021");
		sb.append("&invoice_id=2342345345").append("&invoice=000000000").append("&check_id=1");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**获取设置首页车位数据*/
	public void test_0022()throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0022");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**设置首页车位显示*/
	public void test_0023()throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0023");
		sb.append("&emp_no="+emp_no);
		sb.append("&pos_no="+pos_no);
		sb.append("&token="+token);
		sb.append("&space_no=,220001,220002,220003,220004,220005,220006,220007,220008,220009,220010,,220011");//车位编号
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()));
	}
	/**督查机-根据泊车位查询获取所属路段所有泊车位*/
	public void test_0024(int type)throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0024");
		sb.append("&road_id=42");
		sb.append("&type="+type);
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**督查机-根据泊车位查询获取所属路段泊车位 分页*/
	public void test_0025(int type)throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0025");
		sb.append("&road_id=49");
		sb.append("&space_no=-1");//车位编号
		sb.append("&page_no=1");//页数
		sb.append("&type="+type);
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**版本更新*/
	public void test_0014()throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0014");
		sb.append("&version_code=1").append("&app_type=2");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**月票查询*/
	public void test_0026()throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0026");
		sb.append("&key_code=12222");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	/**发票校验*/
	public void test_0027()throws Exception{
		NettyClient nc=new NettyClient(host,port);
		StringBuffer sb=new StringBuffer("t_code=0027");
		sb.append("&invoice_id=123456789011");
		sb.append("&invoice_start=00000006");
		sb.append("&invoice_end=00000777");
		nc.sendData(RSACoder.encryptDataByRSA64(sb.toString()+baseParams));
	}
	public static void main(String[] args) throws Exception {
		RemoteServiceTest rst=new RemoteServiceTest();
		rst.test_0027();
	}
}
