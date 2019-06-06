package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.TicketInfo;
import com.zycj.tcc.domain.TicketInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TicketInfoMapper {
    int countByExample(TicketInfoExample example);

    int deleteByExample(TicketInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TicketInfo record);

    int insertSelective(TicketInfo record);

    List<TicketInfo> selectByExample(TicketInfoExample example);

    TicketInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TicketInfo record, @Param("example") TicketInfoExample example);

    int updateByExample(@Param("record") TicketInfo record, @Param("example") TicketInfoExample example);

    int updateByPrimaryKeySelective(TicketInfo record);

    int updateByPrimaryKey(TicketInfo record);
}