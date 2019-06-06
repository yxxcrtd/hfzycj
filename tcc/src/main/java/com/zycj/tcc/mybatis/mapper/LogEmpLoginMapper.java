package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.LogEmpLogin;
import com.zycj.tcc.domain.LogEmpLoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogEmpLoginMapper {
    int countByExample(LogEmpLoginExample example);

    int deleteByExample(LogEmpLoginExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LogEmpLogin record);

    int insertSelective(LogEmpLogin record);

    List<LogEmpLogin> selectByExample(LogEmpLoginExample example);

    LogEmpLogin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LogEmpLogin record, @Param("example") LogEmpLoginExample example);

    int updateByExample(@Param("record") LogEmpLogin record, @Param("example") LogEmpLoginExample example);

    int updateByPrimaryKeySelective(LogEmpLogin record);

    int updateByPrimaryKey(LogEmpLogin record);
}