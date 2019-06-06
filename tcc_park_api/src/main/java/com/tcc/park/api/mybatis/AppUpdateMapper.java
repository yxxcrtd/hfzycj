package com.tcc.park.api.mybatis;

import com.tcc.park.api.domain.AppUpdate;

public interface AppUpdateMapper {
    int deleteByPrimaryKey(Integer appUpdateId);

    int insert(AppUpdate record);

    int insertSelective(AppUpdate record);

    AppUpdate selectByPrimaryKey(Integer appUpdateId);

    int updateByPrimaryKeySelective(AppUpdate record);

    int updateByPrimaryKey(AppUpdate record);
}