package com.tcc.park.api.user.mybatis;

import com.tcc.park.api.user.domain.SmsCode;
import com.tcc.park.api.user.domain.SmsCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsCodeMapper {
    int countByExample(SmsCodeExample example);

    int deleteByExample(SmsCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsCode record);

    int insertSelective(SmsCode record);

    List<SmsCode> selectByExample(SmsCodeExample example);

    SmsCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SmsCode record, @Param("example") SmsCodeExample example);

    int updateByExample(@Param("record") SmsCode record, @Param("example") SmsCodeExample example);

    int updateByPrimaryKeySelective(SmsCode record);

    int updateByPrimaryKey(SmsCode record);
}