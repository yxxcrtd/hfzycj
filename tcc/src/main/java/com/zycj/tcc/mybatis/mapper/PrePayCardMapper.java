package com.zycj.tcc.mybatis.mapper;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zycj.tcc.domain.PrePayCard;

public interface PrePayCardMapper {
	public Integer countByCardNo(String cardNo);
	public void createCard(PrePayCard card);
	public List<PrePayCard> getListByQuery(PrePayCard card, PageBounds pageBounds);
	public int getCountByQuery(PrePayCard card);
	public PrePayCard getCardByCardNo(String cardNo);
	public PrePayCard getCardBySelDate(PrePayCard prePayCard);
}
