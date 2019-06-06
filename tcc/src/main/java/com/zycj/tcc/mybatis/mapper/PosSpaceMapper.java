package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.PosSpace;
import com.zycj.tcc.domain.PosSpaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PosSpaceMapper {
    int countByExample(PosSpaceExample example);

    int deleteByExample(PosSpaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PosSpace record);

    int insertSelective(PosSpace record);

    List<PosSpace> selectByExample(PosSpaceExample example);

    PosSpace selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PosSpace record, @Param("example") PosSpaceExample example);

    int updateByExample(@Param("record") PosSpace record, @Param("example") PosSpaceExample example);

    int updateByPrimaryKeySelective(PosSpace record);

    int updateByPrimaryKey(PosSpace record);
}