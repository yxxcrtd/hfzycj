package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.LogAppInstall;
import com.zycj.tcc.domain.LogAppInstallExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogAppInstallMapper {
    int countByExample(LogAppInstallExample example);

    int deleteByExample(LogAppInstallExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LogAppInstall record);

    int insertSelective(LogAppInstall record);

    List<LogAppInstall> selectByExample(LogAppInstallExample example);

    LogAppInstall selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LogAppInstall record, @Param("example") LogAppInstallExample example);

    int updateByExample(@Param("record") LogAppInstall record, @Param("example") LogAppInstallExample example);

    int updateByPrimaryKeySelective(LogAppInstall record);

    int updateByPrimaryKey(LogAppInstall record);
}