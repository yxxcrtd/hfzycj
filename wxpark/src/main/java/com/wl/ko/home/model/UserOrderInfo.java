package com.wl.ko.home.model;

import java.util.Date;

public class UserOrderInfo {
	/**订单编号**/
	private String id;
	/**订单生成时间**/
	private Date createtime;
	/**支付时间**/
	private Date paytime;
	/**购买人账户**/
	private String buyaccount;
	/**订单状态  0  审核中  1 支付完成**/
	private String status;//订单状态  0  审核中  1 支付完成
	/**用户编号 **/
	private String user_id;
	/**支付类型 0:支付宝  1 威信 **/
	private String flag;
	/**金额**/
	private String money;
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public String getBuyaccount() {
		return buyaccount;
	}
	public void setBuyaccount(String buyaccount) {
		this.buyaccount = buyaccount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
