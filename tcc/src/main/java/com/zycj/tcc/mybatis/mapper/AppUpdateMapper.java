package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.AppUpdate;
import com.zycj.tcc.domain.AppUpdateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppUpdateMapper {
    int countByExample(AppUpdateExample example);

    int deleteByExample(AppUpdateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppUpdate record);

    int insertSelective(AppUpdate record);

    List<AppUpdate> selectByExample(AppUpdateExample example);

    AppUpdate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppUpdate record, @Param("example") AppUpdateExample example);

    int updateByExample(@Param("record") AppUpdate record, @Param("example") AppUpdateExample example);

    int updateByPrimaryKeySelective(AppUpdate record);

    int updateByPrimaryKey(AppUpdate record);
}