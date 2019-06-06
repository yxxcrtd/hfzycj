package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.PaymentRecord;
import com.zycj.tcc.domain.PaymentRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaymentRecordMapper {
    int countByExample(PaymentRecordExample example);

    int deleteByExample(PaymentRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PaymentRecord record);

    int insertSelective(PaymentRecord record);

    List<PaymentRecord> selectByExample(PaymentRecordExample example);

    PaymentRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PaymentRecord record, @Param("example") PaymentRecordExample example);

    int updateByExample(@Param("record") PaymentRecord record, @Param("example") PaymentRecordExample example);

    int updateByPrimaryKeySelective(PaymentRecord record);

    int updateByPrimaryKey(PaymentRecord record);
}