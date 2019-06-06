package com.tcc.park.api.user.mybatis;

import com.tcc.park.api.user.domain.RechargeOrder;
import com.tcc.park.api.user.domain.RechargeOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargeOrderMapper {
    int countByExample(RechargeOrderExample example);

    int deleteByExample(RechargeOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RechargeOrder record);

    int insertSelective(RechargeOrder record);

    List<RechargeOrder> selectByExample(RechargeOrderExample example);

    RechargeOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RechargeOrder record, @Param("example") RechargeOrderExample example);

    int updateByExample(@Param("record") RechargeOrder record, @Param("example") RechargeOrderExample example);

    int updateByPrimaryKeySelective(RechargeOrder record);

    int updateByPrimaryKey(RechargeOrder record);
}