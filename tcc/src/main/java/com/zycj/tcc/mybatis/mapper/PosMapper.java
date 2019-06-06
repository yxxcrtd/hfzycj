package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.Pos;
import com.zycj.tcc.domain.PosExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PosMapper {
    int countByExample(PosExample example);

    int deleteByExample(PosExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pos record);

    int insertSelective(Pos record);

    List<Pos> selectByExample(PosExample example);

    Pos selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pos record, @Param("example") PosExample example);

    int updateByExample(@Param("record") Pos record, @Param("example") PosExample example);

    int updateByPrimaryKeySelective(Pos record);

    int updateByPrimaryKey(Pos record);
}