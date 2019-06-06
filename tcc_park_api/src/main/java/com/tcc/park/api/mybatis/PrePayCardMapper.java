package com.tcc.park.api.mybatis;

import java.util.List;

import com.tcc.park.api.user.domain.PrePayCard;

public interface PrePayCardMapper {
	
	public List<PrePayCard> selectByPhone(String phone);
}
