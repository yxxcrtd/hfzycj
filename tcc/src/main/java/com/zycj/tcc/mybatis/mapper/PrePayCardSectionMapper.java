package com.zycj.tcc.mybatis.mapper;

import com.zycj.tcc.domain.PrePayCardSection;

import java.util.List;

public interface PrePayCardSectionMapper {
	public List<PrePayCardSection> selectByCardNo(String cardNo);
	public void update(PrePayCardSection cardSection);
	public void create(PrePayCardSection cardSection);
	public PrePayCardSection getByCardNoSectionName(String cardNo, String sectionName);
}
