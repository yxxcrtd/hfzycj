package com.tcc.park.api.mybatis;

import com.tcc.park.api.domain.RoadSection;
import com.tcc.park.api.domain.RoadSectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoadSectionMapper {
    int countByExample(RoadSectionExample example);

    int deleteByExample(RoadSectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoadSection record);

    int insertSelective(RoadSection record);

    List<RoadSection> selectByExample(RoadSectionExample example);

    RoadSection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoadSection record, @Param("example") RoadSectionExample example);

    int updateByExample(@Param("record") RoadSection record, @Param("example") RoadSectionExample example);

    int updateByPrimaryKeySelective(RoadSection record);

    int updateByPrimaryKey(RoadSection record);
}