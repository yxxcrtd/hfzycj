package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.LogInterface;
import com.zycj.tcc.domain.LogInterfaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogInterfaceMapper {
    int countByExample(LogInterfaceExample example);

    int deleteByExample(LogInterfaceExample example);

    int deleteByPrimaryKey(String id);

    int insert(LogInterface record);

    int insertSelective(LogInterface record);

    List<LogInterface> selectByExample(LogInterfaceExample example);

    LogInterface selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LogInterface record, @Param("example") LogInterfaceExample example);

    int updateByExample(@Param("record") LogInterface record, @Param("example") LogInterfaceExample example);

    int updateByPrimaryKeySelective(LogInterface record);

    int updateByPrimaryKey(LogInterface record);
}