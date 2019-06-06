package com.hfzycj.dao.impl;

import org.springframework.stereotype.Repository;

import com.hfzycj.dao.CategoryDao;
import com.hfzycj.domain.Category;

/**
 * Category Dao Implementation
 */
@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category, Integer, String, String> implements CategoryDao {

}
