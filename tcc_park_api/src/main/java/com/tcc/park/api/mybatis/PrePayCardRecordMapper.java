package com.tcc.park.api.mybatis;

import java.util.List;

import com.tcc.park.api.user.domain.PrePayCardRecord;

public interface PrePayCardRecordMapper {
	
	public List<PrePayCardRecord> selectByCard(String card);
}
