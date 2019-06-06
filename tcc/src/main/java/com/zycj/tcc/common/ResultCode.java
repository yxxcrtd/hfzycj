package com.zycj.tcc.common;

/**
 * 接口响应状态码
 * @author 洪捃能
 *
 */
public class ResultCode {

	public static final String SUCCESS = "8888";//成功
//	public static final String FAILED = "9999";//失败
	public static final String SYSTEM_ERROR = "0000";//服务器系统错误,未知错误，需要查看错误日志确定--操作失败，请重试
//	public static final String BIZ_EXECUTE_EXCEPTION_ERROR="1111";//业务执行异常,未知错误，需要查看错误日志确定
	
	public static final String INTERFACE_PARAM_ERROR = "111101";//参数错误
	public static final String INTERFACE_NO_FOUND_ERROR = "11110102";//接口不存在
	public static final String EMPLOYEENO_ISBLANK_ERROR = "11110103";//用户编号为空
	public static final String POSNO_ISBLANK_ERROR = "11110104";//pos终端编号为空
	public static final String TOKEN_ISBLANK_ERROR = "11110105";//token为空
	public static final String PWD_ISBLANK_ERROR = "11110106";//密码为空
	public static final String CARNO_ISBLANK_ERROR = "11110107";//车牌号为空
	public static final String SECTIONNO_ISBLANK_ERROR = "11110108";//路段编号为空
	public static final String SPACENO_ISBLANK_ERROR = "11110109";//泊车编号为空
	public static final String CAR_TYPE_ISBLANK_ERROR = "11110110";//车类型为空
	public static final String ORDERNO_ISBLANK_ERROR = "11110111";//订单号为空
	public static final String REALL_AMOUNT_ISBLANK_ERROR = "11110112";//实收金额为空
	public static final String AMOUNT_ISBLANK_ERROR = "11110113";//应收金额为空
	public static final String OUTTIME_ISBLANK_ERROR = "11110114";//驶出时间为空
	public static final String OUTTYPE_ISBLANK_ERROR = "11110115";//驶出类型为空 支付类型为空
	public static final String INVOICEID_ISBLANK_ERROR = "11110116";//发票代号为空
	public static final String INVOICE_ISBLANK_ERROR = "11110117";//发票号码为空
	public static final String QUERY_CONDITION_ISBLANK_ERROR = "11110118";//查询条件为空
	public static final String QUERY_TYPE_ISBLANK_ERROR = "11110119";//查询类型为空
	public static final String ORDERID_IS_NOT_NUMBER_ERROR = "11110120";//订单id必须是数字
	public static final String OUTTYPE_IS_NOT_NUMBER_ERROR = "11110121";//驶出类型必须是数字
	public static final String CHECK_DATE_ISBLANK_ERROR = "11110122";//扎帐日期为空
	public static final String CHECK_AMOUNT_ISBLANK_ERROR = "11110123";//扎帐金额为空
	public static final String CHECK_ISTODAY_ISBLANK_ERROR = "11110124";//扎帐是否今天为空
	public static final String ROAD_SECTION_ISBLANK_ERROR = "11110125";//路段主键为空
	public static final String TICKETNO_ISBLANK_ERROR = "11110126";//月票编号为空
	public static final String CARNO_REG_ERROR = "11110127";//车牌号规则错误
	
	
	public static final String CHECK_LOGIN_ERROR = "111102";//校验是否登录程序 异常
	
	public static final String NO_LOGIN_ERROR = "111103";//用户未签到
	public static final String DATA_SEND_REPEAT_CHECK_ERROR="11110301";//接口重发校验未通过
	
	public static final String EMPLOYEE_NO_EXIST_ERROR="111104";//员工不存在
	public static final String EMPLOYEE_STATUS_LEAVE_ERROR="111105";//员工已离职
	public static final String EMPLOYEE_PASSWORD_ERROR = "111106";//密码错误
	
	public static final String POS_NO_EXIST_ERROR="111107";//POS终端不存在
	public static final String POS_STATUS_BROKEN_ERROR="111108";//POS终端不可用
	
	public static final String ORDER_NO_EXIST_ERROR="111301";//订单不存在
	public static final String ORDER_CARNO_NO_MATCH_ERROR="111302";//订单的车牌号与客户端传来的车牌号不匹配
	public static final String ORDER_SPACENO_NO_MATCH_ERROR="111303";//订单的车位编号与客户端传来的车位编号不匹配
	public static final String ORDER_HAS_COMPLETED="111304";//订单已经完成驶出
	public static final String ORDER_ID_FORMAT_ERROR="111305";//订单id不是数字
	public static final String ORDER_HAS_COMPLETED_KTC="111306";//订单已经由快停车完成驶出
	
	public static final String FEE_REGULAR_NO_EXIST_ERROR="111401";//计费规则不存在
	
	public static final String ROAD_SPACE_NO_EXIST_ERROR="111501";//车位不存在
	public static final String ROAD_SPACE_UPDATE_CARENTRY_ERROR="111502";//车辆驶入时 更新停车位异常
	public static final String ROAD_SPACE_UPDATE_CAREXIT_ERROR="111503";//车辆驶出时 更新停车位异常
	public static final String ROAD_SPACE_STATUS_CLOSE_ERROR="111504";//车位不可用
	
	public static final String CAR_TYPE_FORMAT_ERROR="111601";//车类型不是数字
	public static final String CAR_TYPE_NO_MATCH_ERROR="111602";//车类型不匹配，不是大型车或小型车
	
	//计费规则计算停车费相关错误
	public static final String Chargeable_Engine_Exception="333301";//计费引擎执行异常
	public static final String Chargeable_Engine_InTime_IsBlank="333302";//驶入时间为空
	public static final String Chargeable_Engine_OutTime_IsBlank="333303";//驶出时间为空
	public static final String Chargeable_Engine_FeeRegular_IsBlank="333304";//计费规则为空
	public static final String Chargeable_Engine_IntervalTime_IsBlank="333305";//计费间隔时间为空
	public static final String Chargeable_Engine_FreeTime_IsBlank="333306";//免费时间为空
	public static final String Chargeable_Engine_FirstChargeTime_IsBlank="333307";//免费后第一次收费时间为空
	public static final String Chargeable_Engine_InOutTime_NOT_TODAY="333308";//驶入时间与驶出时间不是同一天
	public static final String Chargeable_Engine_InTime_IsEarly="333309";//驶入时间比计费开始时间早
	public static final String Chargeable_Engine_InTime_IsLast="333310";//驶入时间比计费结束时间晚
	public static final String Chargeable_Engine_OutTime_IsEarly="333311";//驶出时间比计费开始时间早
	public static final String Chargeable_Engine_OutTime_Before_InTime="333312";//驶出时间比驶入时间早
	//接口相关返回
	public static final String EMPLOYEE_LOGIN_FAILED="000101";//用户登录失败，可能是更新员工在线状态未成功
	public static final String EMPLOYEE_LOGIN_POS_NO_SECTION_ERROR="000102";//签到时pos机没有绑定路段
	//车辆驶入
	public static final String CAR_ENTRY_FAILED="000301";//车辆驶入失败，查看错误日志确定
	public static final String CAR_HAS_ENTERED_ERROR="000302";//车辆重复驶入，已经有一笔该车牌号的车辆处于驶入状态
	public static final String CAR_ENTRY_ADD_ORDERINFO_ERROR="000303";//车辆驶入生成订单失败
	public static final String CAR_ENTRY_TIME_NOT_IN_FEEREG_TIME="000304";//车辆驶入时间不在计费时间段内
	public static final String CAR_ENTRY_TIME_CHECK_EXCEPTION="000305";//判断车辆驶入时间是否在计费时间段内 异常
	public static final String CAR_ENTRY_ROADSPACE_ISPARKED_ERROR="000306";//车位已经停车
	
	//车辆使出费用查询
	public static final String PARK_FEE_EXECUTE_FAILED="000501";//根据停车信息计算停车费失败
	public static final String PARK_FEE_INTIME_NOT_TODAY_ERROR="000502";//订单对应的驶入时间与驶出时间不是同一天
	public static final String PARK_FEE_ORDERSEARCH_FAILED="000503";//根据订单号查询订单信息失败
	
	
	//确认车辆驶出
	public static final String CAR_EXIT_FAILED="000601";//车辆驶出失败 查看错误日志确定原因
	public static final String CAR_EXIT_OUTTIME_FORMAT_ERROR="000602";//日期格式错误
	public static final String CAR_EXIT_OUTTYPE_ERROR="000603";//驶出类型错误，无相应驶出类型
	public static final String CAR_EXIT_UPDATE_ORDER_EXCEPTION="0000604";//车辆驶出时，把订单更新为驶出时异常
	public static final String CAR_EXIT_ADD_PAYMENT_RECORD_EXCEPTION="0000605";//车辆驶出时，增加缴费记录时异常
	public static final String CAR_EXIT_ADD_ARREAR_EXCEPTION="0000606";//车辆驶出时，增加欠费记录时异常
	
	//欠费补缴
	public static final String ARREAR_FAILED="000801";//欠费补缴失败  查看错误日志确定原因
	public static final String ARREAR_IDS_ISBLANK_ERROR="000802";//补缴欠费的ids为空
	public static final String ARREAR_AMOUNT_NO_MATCH_ERROR="000803";//补缴欠费金额格式不正确或金额<=0
	public static final String ARREAR_IDS_NO_MATCH_ERROR="000804";//补缴欠费的ids格式不正确
	public static final String ARREAR_PAY_TYPE_ERROR="000805";//支付类型 错误
	public static final String ARREAR_NO_EXIST_ERROR="000806";//无id对应的相关欠费记录
	public static final String ARREAR_AMOUNT_NOT_ENOUGH_ERROR="000807";//补缴金额不足以支付所选欠费记录
	public static final String ARREAR_HAS_COMPLETED_ERROR="000808";//欠费已经补缴了，重复补缴
	public static final String ARREAR_AMOUNT_ATYPISM_ERROR="000809";//补缴的金额与欠费金额不一致
	public static final String ARREAR_HAS_COMPLETED_KTC="000810";//欠费已经补缴了，由快停车补缴
	public static final String ARREAR_CARD_MONEY_NOT_ENOUGH = "0000811";//停車卡補繳餘額不足
	//员工修改密码
	public static final String UPDATE_PWD_FAILED = "001101";//修改密码失败 查看错误日志确定原因
	public static final String NEW_PWD_ISBLANK_ERROR = "001102";//新密码为空
	
	//签退查询
	public static final String LOGOUT_QUERY_FAILED="001201";//签退查询失败 查看错误日志确定原因
	
	//提交收费员扎帐信息
	public static final String CHECK_FAILED = "001001";//扎帐失败 查看错误日志确定原因
	public static final String CHECK_DATE_FORMAT_ERROR = "001002";//日期格式错误
	public static final String CHECK_AMOUNT_FORMAT_ERROR = "001003";//扎帐金额格式错误，不是数字
	public static final String CHECK_ID_ISBLANK_ERROR = "001004";//扎帐id为空
	public static final String CHECK_ID_FORMAT_ERROR = "001005";//历史未扎帐id不是数字
	public static final String CHECK_REPEAT_TODAY_FAILED = "001006";//重发今天扎帐失败，今天指定时间段内的缴费已经扎帐了
	public static final String CHECK_REPEAT_HISTORY_FAILED = "001007";//重发 历史缴费扎帐 失败，已经扎帐完成了
	public static final String CHECK_NO_EXIST_ERROR="001008";//无id对应的相关扎帐记录
	//数据驶入驶出
	public static final String QUERY_TYPE_FAILED="001301";//数据驶入驶出 查询失败 查看错误日志确定原因
	public static final String NO_RECENTLY_ORDER="001302";//无当天相关的交易信息
	public static final String QUERY_TYPE_NO_MATCH_ERROR="001303";//查询类型不匹配
	
	//获取pos对应路段的车位
	public static final String LOAD_ROADSPACE_ERROR="001601";//获取车位失败  查看错误日志确定原因
	public static final String LOAD_ROADSPACE_TYPE_ISBLANK_ERROR="001602";//获取车位的数据类型为空或数据类型不匹配
	public static final String LOAD_ROADSPACE_TYPE_NO_MATCH_ERROR="001603";//获取车位的数据类型不匹配
	//日期信息数据查询
	public static final String QUERY_DATE_FAILED="001901";//日期信息数据查询 查询失败 查看错误日志确定原因
	public static final String QUERY_DATE_NO_MATCH_ERROR="001902";//查询日期不匹配
	
	//校验IP地址、端口号、终端编号
	public static final String SYSTEM_CHECK_IPPORT_POS_EXCEPTION="002001";//校验 终端编号时 获取并更新路段名称异常
	
	//重新打印发票日志信息上传接口
	public static final String INVOICE_REPRINT_FAILED="002101";//重新打印发票日志失败 查看错误日志确定原因
	public static final String INVOICE_REPRINT_TYPE_ISBLANK_ERROR="002102";//发票类型为空
	public static final String INVOICE_REPRINT_TYPE_NO_MATCH_ERROR="002103";//发票类型不匹配 不是数字类型或不是业务指定的值
	public static final String INVOICE_REPRINT_PRICE_ISBLANK_ERROR="002104";//发票金额为空
	public static final String INVOICE_REPRINT_INVOICEBATCH_ISBLANK_ERROR="002105";//发票批次号为空
	public static final String INVOICE_REPRINT_INVOICENO_ISBLANK_ERROR="002106";//发票编号为空
	public static final String INVOICE_REPRINT_PRINTNUM_ISBLANK_ERROR="002107";//发票打印次数为空
	public static final String INVOICE_REPRINT_PRINTNUM_FORMAT_ERROR="002108";//发票打印次数不是数字
	public static final String INVOICE_REPRINT_PRICE_NO_MATCH_ERROR="002109";//发票金额格式不正确或金额<=0
	
	//设置首页车位显示
	public static final String HOMEPAGESET_ROADSPACE_FAILED="002301";//设置首页车位失败 查看错误日志确定原因
	public static final String SPACENO_LIST_ISBLANK_ERROR="002302";//选中的车位集合为空
	
	/**督查机 返回码 start*/
	
	/**督查机 返回码 end*/
	
	//月票查询
	public static final String QUERY_TICKETINFO_FAILED = "002601";//月票查询失败
	public static final String TICKETINFO_NO_EXIST_ERROR = "002602";//月票不存在
	public static final String TICKETINFO_OVERDUE_ERROR = "002603";//月票已失效
	
	//发票校验接口
	public static final String INVOICE_CHECK_ERROR="002701";//发票校验异常  查看错误日志确定原因
	public static final String INVOICE_CHECK_FAILED="002702";//发票编号无效
	public static final String INVOICE_BATCH_CHECK_FAILED="002703";//发票批次号无效
	public static final String INVOICE_CHECK_DUPLICATE="002704";//重复设置
	
}
