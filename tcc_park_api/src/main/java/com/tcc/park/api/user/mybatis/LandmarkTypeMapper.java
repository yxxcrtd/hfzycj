package com.tcc.park.api.user.mybatis;

import com.tcc.park.api.user.domain.LandmarkType;
import com.tcc.park.api.user.domain.LandmarkTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LandmarkTypeMapper {
    int countByExample(LandmarkTypeExample example);

    int deleteByExample(LandmarkTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LandmarkType record);

    int insertSelective(LandmarkType record);

    List<LandmarkType> selectByExample(LandmarkTypeExample example);

    LandmarkType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LandmarkType record, @Param("example") LandmarkTypeExample example);

    int updateByExample(@Param("record") LandmarkType record, @Param("example") LandmarkTypeExample example);

    int updateByPrimaryKeySelective(LandmarkType record);

    int updateByPrimaryKey(LandmarkType record);
}