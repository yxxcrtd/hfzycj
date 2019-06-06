package com.tcc.park.api.user.mybatis;

import com.tcc.park.api.user.domain.UserBocardDetail;
import com.tcc.park.api.user.domain.UserBocardDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBocardDetailMapper {
    int countByExample(UserBocardDetailExample example);

    int deleteByExample(UserBocardDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBocardDetail record);

    int insertSelective(UserBocardDetail record);

    List<UserBocardDetail> selectByExample(UserBocardDetailExample example);

    UserBocardDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserBocardDetail record, @Param("example") UserBocardDetailExample example);

    int updateByExample(@Param("record") UserBocardDetail record, @Param("example") UserBocardDetailExample example);

    int updateByPrimaryKeySelective(UserBocardDetail record);

    int updateByPrimaryKey(UserBocardDetail record);
}