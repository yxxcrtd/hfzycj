package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.CarSpecial;
import com.zycj.tcc.domain.CarSpecialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarSpecialMapper {
    int countByExample(CarSpecialExample example);

    int deleteByExample(CarSpecialExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarSpecial record);

    int insertSelective(CarSpecial record);

    List<CarSpecial> selectByExample(CarSpecialExample example);

    CarSpecial selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarSpecial record, @Param("example") CarSpecialExample example);

    int updateByExample(@Param("record") CarSpecial record, @Param("example") CarSpecialExample example);

    int updateByPrimaryKeySelective(CarSpecial record);

    int updateByPrimaryKey(CarSpecial record);
}