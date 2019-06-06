package com.tcc.park.api.user.mybatis;

import com.tcc.park.api.user.domain.NewsDetail;
import com.tcc.park.api.user.domain.NewsDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsDetailMapper {
    int countByExample(NewsDetailExample example);

    int deleteByExample(NewsDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NewsDetail record);

    int insertSelective(NewsDetail record);

    List<NewsDetail> selectByExampleWithBLOBs(NewsDetailExample example);

    List<NewsDetail> selectByExample(NewsDetailExample example);

    NewsDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NewsDetail record, @Param("example") NewsDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") NewsDetail record, @Param("example") NewsDetailExample example);

    int updateByExample(@Param("record") NewsDetail record, @Param("example") NewsDetailExample example);

    int updateByPrimaryKeySelective(NewsDetail record);

    int updateByPrimaryKeyWithBLOBs(NewsDetail record);

    int updateByPrimaryKey(NewsDetail record);
}