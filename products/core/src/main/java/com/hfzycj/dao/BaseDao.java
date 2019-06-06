package com.hfzycj.dao;

import com.hfzycj.util.Pager;

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO
 */
public interface BaseDao<T, PK extends Serializable> {

	T findById(PK id);

	int findAllCount(T t, String whereString);

	List<T> findAllList(String whereString, String orderbyString, Integer limit);

	List<T> findAllListById(int id);

	List<T> findByPager(Pager pager);

	List<T> findByPager(Pager pager, String whereString, String orderbyString);

	List<T> findListByLeftJoin(Pager pager, String keyword);

	Long save(T t);

	void update(T t);

	void delete(PK id);

	void delete(T t);

	Long lPush(String key, String value);

	boolean zAdd(String key, long orderby, String value);

	void set(String key, String value, Long liveTime);

	List<String> get(String key);

	Long del(List<String> keys);

}
