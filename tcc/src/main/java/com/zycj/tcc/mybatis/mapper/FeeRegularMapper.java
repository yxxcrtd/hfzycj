package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.FeeRegular;
import com.zycj.tcc.domain.FeeRegularExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeeRegularMapper {
    int countByExample(FeeRegularExample example);

    int deleteByExample(FeeRegularExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FeeRegular record);

    int insertSelective(FeeRegular record);

    List<FeeRegular> selectByExample(FeeRegularExample example);

    FeeRegular selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FeeRegular record, @Param("example") FeeRegularExample example);

    int updateByExample(@Param("record") FeeRegular record, @Param("example") FeeRegularExample example);

    int updateByPrimaryKeySelective(FeeRegular record);

    int updateByPrimaryKey(FeeRegular record);
}