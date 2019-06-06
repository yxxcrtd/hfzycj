package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.EmployeeOnline;
import com.zycj.tcc.domain.EmployeeOnlineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeOnlineMapper {
    int countByExample(EmployeeOnlineExample example);

    int deleteByExample(EmployeeOnlineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeOnline record);

    int insertSelective(EmployeeOnline record);

    List<EmployeeOnline> selectByExample(EmployeeOnlineExample example);

    EmployeeOnline selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmployeeOnline record, @Param("example") EmployeeOnlineExample example);

    int updateByExample(@Param("record") EmployeeOnline record, @Param("example") EmployeeOnlineExample example);

    int updateByPrimaryKeySelective(EmployeeOnline record);

    int updateByPrimaryKey(EmployeeOnline record);
}