package com.zycj.tcc.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public abstract class BaseDao extends SqlSessionDaoSupport{

	public Integer insert(String statementName, Object paraterObject) {
		return getSqlSession().insert(statementName, paraterObject);
	}

	public int delete(String statementName, Object parameterObject) {
		return getSqlSession().delete(statementName, parameterObject);
	}

	public int update(String statementName, Object parameterObject) {
		return getSqlSession().update(statementName, parameterObject);
	}

	public <E> Object query(String statementName, Object parameterObject) {
		return getSqlSession().selectOne(statementName,parameterObject);
	}

	public <E>List<E> queryList(String statementName, Object parameterObject) {
		return getSqlSession().selectList(statementName,parameterObject);
	}

	public <E>List<E> queryListByPage(String statementName,Object parameterObject, PageBounds pb) {
		return getSqlSession().selectList(statementName,parameterObject,pb);
	}
}
