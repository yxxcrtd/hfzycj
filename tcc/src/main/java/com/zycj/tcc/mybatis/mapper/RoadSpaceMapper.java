package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.RoadSpace;
import com.zycj.tcc.domain.RoadSpaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoadSpaceMapper {
    int countByExample(RoadSpaceExample example);

    int deleteByExample(RoadSpaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoadSpace record);

    int insertSelective(RoadSpace record);

    List<RoadSpace> selectByExample(RoadSpaceExample example);

    RoadSpace selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoadSpace record, @Param("example") RoadSpaceExample example);

    int updateByExample(@Param("record") RoadSpace record, @Param("example") RoadSpaceExample example);

    int updateByPrimaryKeySelective(RoadSpace record);

    int updateByPrimaryKey(RoadSpace record);
}