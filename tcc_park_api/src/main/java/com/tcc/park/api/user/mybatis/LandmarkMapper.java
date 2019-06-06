package com.tcc.park.api.user.mybatis;

import com.tcc.park.api.user.domain.Landmark;
import com.tcc.park.api.user.domain.LandmarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LandmarkMapper {
    int countByExample(LandmarkExample example);

    int deleteByExample(LandmarkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Landmark record);

    int insertSelective(Landmark record);

    List<Landmark> selectByExample(LandmarkExample example);

    Landmark selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Landmark record, @Param("example") LandmarkExample example);

    int updateByExample(@Param("record") Landmark record, @Param("example") LandmarkExample example);

    int updateByPrimaryKeySelective(Landmark record);

    int updateByPrimaryKey(Landmark record);
}