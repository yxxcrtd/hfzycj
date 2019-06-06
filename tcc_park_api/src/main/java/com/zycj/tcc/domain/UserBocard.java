package com.zycj.tcc.domain;

import java.math.BigDecimal;
import java.util.Date;

public class UserBocard {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard.id
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard.user_id
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard.card_no
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private String cardNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard.card_type
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private Integer cardType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard.card_balance
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private BigDecimal cardBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard.create_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_bocard.modify_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard.id
     *
     * @return the value of user_bocard.id
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard.id
     *
     * @param id the value for user_bocard.id
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard.user_id
     *
     * @return the value of user_bocard.user_id
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard.user_id
     *
     * @param userId the value for user_bocard.user_id
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard.card_no
     *
     * @return the value of user_bocard.card_no
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard.card_no
     *
     * @param cardNo the value for user_bocard.card_no
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard.card_type
     *
     * @return the value of user_bocard.card_type
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard.card_type
     *
     * @param cardType the value for user_bocard.card_type
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard.card_balance
     *
     * @return the value of user_bocard.card_balance
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard.card_balance
     *
     * @param cardBalance the value for user_bocard.card_balance
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard.create_time
     *
     * @return the value of user_bocard.create_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard.create_time
     *
     * @param createTime the value for user_bocard.create_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_bocard.modify_time
     *
     * @return the value of user_bocard.modify_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_bocard.modify_time
     *
     * @param modifyTime the value for user_bocard.modify_time
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}