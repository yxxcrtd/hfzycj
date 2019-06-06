package com.zycj.tcc.domain;

import java.util.Date;

public class PrePayCard {
	
	public static final Integer CARD_STATUS_YES = 1;
	public static final Integer CARD_STATUS_NO = 2;
	
	public static final int CARD_TYPE_NEW_ENERGY = 1;//新能源卡
	public static final int CARD_TYPE_SIMPLE = 2;//普卡
	public static final int CARD_TYPE_VIP = 3;//VIP卡
	public static final int CARD_TYPE_TICKET = 4;//月票卡
	
	private Integer id;
	private String cardNo;
	private Integer cardType;
	private Date createTime;
	private Date endTime;
	private Date lastDealTime;
	private Date useEndTime;
	private Integer cardBalance;
	private Integer cardScore;
	private Integer cardFreeTime;
	private Integer cardStatus;
	private String cardCreateEmp;
	private String cardPhone;
	private String cardContacts;
	private String cardCarNo;
	public PrePayCard(Integer id, String cardNo, Integer cardType, Date createTime, Date endTime,
			Date lastDealTime, Date useEndTime, Integer cardBalance, Integer cardScore, Integer cardFreeTime,
			Integer cardStatus, String cardCreateEmp, String cardPhone, String cardContacts, String cardCarNo) {
		super();
		this.id = id;
		this.cardNo = cardNo;
		this.cardType = cardType;
		this.createTime = createTime;
		this.endTime = endTime;
		this.lastDealTime = lastDealTime;
		this.useEndTime = useEndTime;
		this.cardBalance = cardBalance;
		this.cardScore = cardScore;
		this.cardFreeTime = cardFreeTime;
		this.cardStatus = cardStatus;
		this.cardCreateEmp = cardCreateEmp;
		this.cardPhone = cardPhone;
		this.cardContacts = cardContacts;
		this.cardCarNo = cardCarNo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getLastDealTime() {
		return lastDealTime;
	}
	public void setLastDealTime(Date lastDealTime) {
		this.lastDealTime = lastDealTime;
	}
	public Date getUseEndTime() {
		return useEndTime;
	}
	public void setUseEndTime(Date useEndTime) {
		this.useEndTime = useEndTime;
	}
	public Integer getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(Integer cardBanlance) {
		this.cardBalance = cardBanlance;
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
	public String getCardCreateEmp() {
		return cardCreateEmp;
	}
	public void setCardCreateEmp(String cardCreateEmp) {
		this.cardCreateEmp = cardCreateEmp;
	}
	public String getCardPhone() {
		return cardPhone;
	}
	public void setCardPhone(String cardPhone) {
		this.cardPhone = cardPhone;
	}
	public String getCardContacts() {
		return cardContacts;
	}
	public void setCardContacts(String cardContacts) {
		this.cardContacts = cardContacts;
	}
	public String getCardCarNo() {
		return cardCarNo;
	}
	public void setCardCarNo(String cardCarNo) {
		this.cardCarNo = cardCarNo;
	}
	
	public PrePayCard() {
	}
}
