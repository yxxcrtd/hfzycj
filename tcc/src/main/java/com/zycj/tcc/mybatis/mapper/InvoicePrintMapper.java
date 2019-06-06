package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.InvoicePrint;
import com.zycj.tcc.domain.InvoicePrintExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoicePrintMapper {
    int countByExample(InvoicePrintExample example);

    int deleteByExample(InvoicePrintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InvoicePrint record);

    int insertSelective(InvoicePrint record);

    List<InvoicePrint> selectByExample(InvoicePrintExample example);

    InvoicePrint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InvoicePrint record, @Param("example") InvoicePrintExample example);

    int updateByExample(@Param("record") InvoicePrint record, @Param("example") InvoicePrintExample example);

    int updateByPrimaryKeySelective(InvoicePrint record);

    int updateByPrimaryKey(InvoicePrint record);
}