package com.tcc.park.api.vo;

/**
 * @author xiechanglei
 * 预付费卡 vo
 *
 */
public class PrePayCardVo {
	private String cardNo; // 卡号
	private Integer cardType; // 卡的类型 1新能源卡 2 普卡 3 vip卡 4 月票卡
	private String createTime; // 制卡的时间（）
	private String endTime; // 卡的有效期
	private String lastDealTime; // 最后一次交易的时间
	private String useEndTime; // 卡片使用的有效期
	private String cardBalance; // card 余额
	private Integer cardScore; // 卡片积分
	private Integer cardFreeTime;// 卡的免费停车时长
	private Integer cardStatus;// 卡的状态 1.正常 2禁用
	private String cardContacts; // 卡的联系人
	private String cardPhone; // 卡的联系人号码
	private String cardCarNo; // 卡的绑定车牌号

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLastDealTime() {
		return lastDealTime;
	}

	public void setLastDealTime(String lastDealTime) {
		this.lastDealTime = lastDealTime;
	}

	public String getUseEndTime() {
		return useEndTime;
	}

	public void setUseEndTime(String useEndTime) {
		this.useEndTime = useEndTime;
	}

	public String getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(String cardBalance) {
		this.cardBalance = cardBalance;
	}

	public Integer getCardScore() {
		return cardScore;
	}

	public void setCardScore(Integer cardScore) {
		this.cardScore = cardScore;
	}

	public Integer getCardFreeTime() {
		return cardFreeTime;
	}

	public void setCardFreeTime(Integer cardFreeTime) {
		this.cardFreeTime = cardFreeTime;
	}

	public Integer getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getCardContacts() {
		return cardContacts;
	}

	public void setCardContacts(String cardContacts) {
		this.cardContacts = cardContacts;
	}

	public String getCardPhone() {
		return cardPhone;
	}

	public void setCardPhone(String cardPhone) {
		this.cardPhone = cardPhone;
	}

	public String getCardCarNo() {
		return cardCarNo;
	}

	public void setCardCarNo(String cardCarNo) {
		this.cardCarNo = cardCarNo;
	}

	public PrePayCardVo() {
	}

}
