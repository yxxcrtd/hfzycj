package com.tcc.park.api.mybatis;

import com.tcc.park.api.domain.ParkLot;

public interface ParkLotMapper {
    int deleteByPrimaryKey(Integer parkId);

    int insert(ParkLot record);

    int insertSelective(ParkLot record);

    ParkLot selectByPrimaryKey(Integer parkId);

    int updateByPrimaryKeySelective(ParkLot record);

    int updateByPrimaryKey(ParkLot record);
}