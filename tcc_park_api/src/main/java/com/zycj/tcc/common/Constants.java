/**
 * 
 */
package com.zycj.tcc.common;

/**
 * 常量
 * Title: Constants.java
 * Description: 城泊二期
 * Company: zycj
 * @author wangy
 * @date 2015年3月26日
 */
public interface Constants {
	/**
	 * 操作员初始操作密码
	 */
	String DEFAULT_PWD = "123456";
	/**
	 * 初始操作员登录次数
	 */
	int DEFAULT_LOGIN_TOTAL = 0;
	/**
	 * 操作数据库中某一条记录时，add/delete/update 时，返回rows 值
	 */
	int ONE_RETURN_ROWS = 1; 
	/**
	 * 请求处理失败
	 */
	int FAIL_RESULT = 0;
	/**
	 * 请求处理成功
	 */
	int SUCCESS_RESULT =1;
	/**
	 * 一级菜单
	 */
	int SUPER_MENU = 1;
	/**
	 * 二级菜单
	 */
	int SUB_MENU = 2;
	/**
	 * 生成唯一文件时，使用的日期格式
	 */
	String UNIQUE_FILE_NAME_TIME_FORMATE = "yyyyMMddHHmmss";
	/**
	 * 时间类型格式
	 */
	String DEFAULT_TIME_FORMATE = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期类型格式
	 */
	String DEFAULT_DATE_FORMATE = "yyyy-MM-dd";
	/**
	 * 一天时间最大值
	 */
	String MAX_DAY_TIME_STR="23:59:59";
	/**
	 * 默认页
	 */
	int DEFAULT_PAGE = 1;
	/**
	 * 默认每页显示条数
	 */
	int DEFAULT_LIMIT = 10;
	/**
	 * 页面中默认显示页码数
	 */
	int DEFAULT_VIEW_PAGES = 10;
	
	/**
	 * 分页查询时，默认的排序，
	 */
	String DEFAULT_SORT = "CREATE_TIME";
	/**
	 * 升序
	 */
	String ORDER_UP = "ASC";
	/**
	 * 降序
	 */
	String ORDER_DOWN ="DESC";
	/**
	 * 平台管理员、角色、会员等相关状态
	 */
	int IS_NORMAL = 1;//正常
	int IS_CLOSED = 0;//关闭的或者禁用的
	int UN_SELECT = -1;//查询条件中，下拉框为选择时
	/**
	 * 资讯
	 */
	int NEWS_IS_NEW = 0;//新生成
	int NEWS_IS_ONLINE=1;//上线
	int NEWS_IS_OUTLINE= 2;//下线
	
	/**
	 * 操作日志中，操作类型
	 */
	int ADD = 1;//新增记录
	int EDIT = 2;//修改记录
	int DELETE = 3;//删除记录
	int UPDATE_STATUS = 4;//变更状态
	int RESET_PWD = 5;//重置密码
	/**
	 * 初始的超级管理员和超级管理员角色是否展示
	 */
	boolean IS_SHOW = true;//展示
	boolean IS_NOT_SHOW = false;//不展示
	/**
	 * 客户端类型
	 */
	String ANDROID = "1";
	String IOS = "2";
}
