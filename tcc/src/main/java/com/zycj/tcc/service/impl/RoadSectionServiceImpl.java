package com.zycj.tcc.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.domain.RoadSection;
import com.zycj.tcc.domain.RoadSectionExample;
import com.zycj.tcc.mybatis.mapper.RoadSectionMapper;
import com.zycj.tcc.service.RoadSectionService;
import com.zycj.tcc.util.ArrayUtil;

@Service
public class RoadSectionServiceImpl implements RoadSectionService {

	@Autowired
	private RoadSectionMapper roadSectionMapper;
	
	@Override
	public List<RoadSection> getSectionListByIds(String ids) {
		RoadSectionExample example = new RoadSectionExample();
		example.or().andIdIn(ArrayUtil.stringToInteger((Arrays.asList(ids.split(",")))));
		return roadSectionMapper.selectByExample(example);
	}

}
