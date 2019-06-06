package com.tcc.park.api.user.mybatis;

import com.tcc.park.api.user.domain.UserBocard;
import com.tcc.park.api.user.domain.UserBocardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBocardMapper {
    int countByExample(UserBocardExample example);

    int deleteByExample(UserBocardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBocard record);

    int insertSelective(UserBocard record);

    List<UserBocard> selectByExample(UserBocardExample example);

    UserBocard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserBocard record, @Param("example") UserBocardExample example);

    int updateByExample(@Param("record") UserBocard record, @Param("example") UserBocardExample example);

    int updateByPrimaryKeySelective(UserBocard record);

    int updateByPrimaryKey(UserBocard record);
}