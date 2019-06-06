package com.zycj.tcc.dao;

import com.zycj.tcc.domain.UserBocard;
import com.zycj.tcc.domain.UserBocardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBocardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_bocard
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    int countByExample(UserBocardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_bocard
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_bocard
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    int insert(UserBocard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_bocard
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    int insertSelective(UserBocard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_bocard
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    List<UserBocard> selectByExample(UserBocardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_bocard
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    UserBocard selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_bocard
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    int updateByExampleSelective(@Param("record") UserBocard record, @Param("example") UserBocardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_bocard
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    int updateByExample(@Param("record") UserBocard record, @Param("example") UserBocardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_bocard
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    int updateByPrimaryKeySelective(UserBocard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_bocard
     *
     * @mbggenerated Wed Mar 25 11:35:19 CST 2015
     */
    int updateByPrimaryKey(UserBocard record);
}