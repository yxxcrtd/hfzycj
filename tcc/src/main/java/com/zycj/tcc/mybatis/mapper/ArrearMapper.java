package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.Arrear;
import com.zycj.tcc.domain.ArrearExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArrearMapper {
    int countByExample(ArrearExample example);

    int deleteByExample(ArrearExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Arrear record);

    int insertSelective(Arrear record);

    List<Arrear> selectByExample(ArrearExample example);

    Arrear selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Arrear record, @Param("example") ArrearExample example);

    int updateByExample(@Param("record") Arrear record, @Param("example") ArrearExample example);

    int updateByPrimaryKeySelective(Arrear record);

    int updateByPrimaryKey(Arrear record);
}