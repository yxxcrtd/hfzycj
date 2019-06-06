package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.InvoiceSetting;
import com.zycj.tcc.domain.InvoiceSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoiceSettingMapper {
    int countByExample(InvoiceSettingExample example);

    int deleteByExample(InvoiceSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InvoiceSetting record);

    int insertSelective(InvoiceSetting record);

    List<InvoiceSetting> selectByExample(InvoiceSettingExample example);

    InvoiceSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InvoiceSetting record, @Param("example") InvoiceSettingExample example);

    int updateByExample(@Param("record") InvoiceSetting record, @Param("example") InvoiceSettingExample example);

    int updateByPrimaryKeySelective(InvoiceSetting record);

    int updateByPrimaryKey(InvoiceSetting record);
}