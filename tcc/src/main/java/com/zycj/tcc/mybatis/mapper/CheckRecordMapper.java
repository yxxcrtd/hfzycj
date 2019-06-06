package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.CheckRecord;
import com.zycj.tcc.domain.CheckRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckRecordMapper {
    int countByExample(CheckRecordExample example);

    int deleteByExample(CheckRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CheckRecord record);

    int insertSelective(CheckRecord record);

    List<CheckRecord> selectByExample(CheckRecordExample example);

    CheckRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CheckRecord record, @Param("example") CheckRecordExample example);

    int updateByExample(@Param("record") CheckRecord record, @Param("example") CheckRecordExample example);

    int updateByPrimaryKeySelective(CheckRecord record);

    int updateByPrimaryKey(CheckRecord record);
}