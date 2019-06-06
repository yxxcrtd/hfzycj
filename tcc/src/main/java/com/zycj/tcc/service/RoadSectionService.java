package com.zycj.tcc.service;

import java.util.List;

import com.zycj.tcc.domain.RoadSection;

public interface RoadSectionService {

	public List<RoadSection> getSectionListByIds(String ids);
}
