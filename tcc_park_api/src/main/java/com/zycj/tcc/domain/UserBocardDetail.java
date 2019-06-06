package com.zycj.tcc.domain;

import java.math.BigDecimal;
import java.util.Date;

public class UserBocardDetail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard_detail.id
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard_detail.card_no
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private String cardNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard_detail.balance_before
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private BigDecimal balanceBefore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard_detail.recharge_amount
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private BigDecimal rechargeAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard_detail.balance_after
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private BigDecimal balanceAfter;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard_detail.recharge_type
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private Integer rechargeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard_detail.ip
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard_detail.account
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private String account;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard_detail.create_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard_detail.modify_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard_detail.id
     *
     * @return the value of user_bocard_detail.id
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard_detail.id
     *
     * @param id the value for user_bocard_detail.id
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard_detail.card_no
     *
     * @return the value of user_bocard_detail.card_no
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard_detail.card_no
     *
     * @param cardNo the value for user_bocard_detail.card_no
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard_detail.balance_before
     *
     * @return the value of user_bocard_detail.balance_before
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public BigDecimal getBalanceBefore() {
        return balanceBefore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard_detail.balance_before
     *
     * @param balanceBefore the value for user_bocard_detail.balance_before
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setBalanceBefore(BigDecimal balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard_detail.recharge_amount
     *
     * @return the value of user_bocard_detail.recharge_amount
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard_detail.recharge_amount
     *
     * @param rechargeAmount the value for user_bocard_detail.recharge_amount
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard_detail.balance_after
     *
     * @return the value of user_bocard_detail.balance_after
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard_detail.balance_after
     *
     * @param balanceAfter the value for user_bocard_detail.balance_after
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setBalanceAfter(BigDecimal balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard_detail.recharge_type
     *
     * @return the value of user_bocard_detail.recharge_type
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public Integer getRechargeType() {
        return rechargeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard_detail.recharge_type
     *
     * @param rechargeType the value for user_bocard_detail.recharge_type
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setRechargeType(Integer rechargeType) {
        this.rechargeType = rechargeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard_detail.ip
     *
     * @return the value of user_bocard_detail.ip
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard_detail.ip
     *
     * @param ip the value for user_bocard_detail.ip
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard_detail.account
     *
     * @return the value of user_bocard_detail.account
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard_detail.account
     *
     * @param account the value for user_bocard_detail.account
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard_detail.create_time
     *
     * @return the value of user_bocard_detail.create_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard_detail.create_time
     *
     * @param createTime the value for user_bocard_detail.create_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard_detail.modify_time
     *
     * @return the value of user_bocard_detail.modify_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard_detail.modify_time
     *
     * @param modifyTime the value for user_bocard_detail.modify_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}