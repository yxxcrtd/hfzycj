package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.AppError;
import com.zycj.tcc.domain.AppErrorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppErrorMapper {
    int countByExample(AppErrorExample example);

    int deleteByExample(AppErrorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppError record);

    int insertSelective(AppError record);

    List<AppError> selectByExample(AppErrorExample example);

    AppError selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppError record, @Param("example") AppErrorExample example);

    int updateByExample(@Param("record") AppError record, @Param("example") AppErrorExample example);

    int updateByPrimaryKeySelective(AppError record);

    int updateByPrimaryKey(AppError record);
}