package com.hfzycj.dao.impl;

import com.hfzycj.dao.ItemDao;
import com.hfzycj.domain.Item;
import org.springframework.stereotype.Repository;


/**
 * Item Dao Implementation
 */
@Repository
public class ItemDaoImpl extends BaseDaoImpl<Item, Integer, String, String> implements ItemDao{

}

