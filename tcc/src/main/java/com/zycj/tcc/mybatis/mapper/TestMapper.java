package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.Test;
import com.zycj.tcc.domain.TestExample;
import java.util.List;

public interface TestMapper {
    int countByExample(TestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    int insertSelective(Test record);

    List<Test> selectByExampleWithBLOBs(TestExample example);

    List<Test> selectByExample(TestExample example);

    Test selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKeyWithBLOBs(Test record);

    int updateByPrimaryKey(Test record);
}