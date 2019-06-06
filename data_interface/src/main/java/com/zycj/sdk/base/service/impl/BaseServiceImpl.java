package com.zycj.sdk.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.sdk.base.dao.IGenericDAO;
import com.zycj.sdk.base.service.IBaseService;
import com.zycj.sdk.util.SpringContextUtil;

/**
 * @description 基本业务类的封装
 * @author fengya
 * @date 2015-5-25 上午11:41:28
 */
@Service("baseService")
public class BaseServiceImpl implements IBaseService {
	
	@Autowired
	protected IGenericDAO genericDao;
	
	protected IGenericDAO getGenericDao(){
		return genericDao;
	}
	
	public Object getBean(String name){
		return SpringContextUtil.getBean(name);
	}

	
	@SuppressWarnings("unchecked")
	public void batchMerge(List list){
		if(list == null){
			return;
		}
		getGenericDao().saveOrUpdateAll(list);
	}
	
	@SuppressWarnings("unchecked")
	public void batchRemove(List list){
		if(list == null){
			return;
		}
		getGenericDao().batchRemove(list);
	}

	public void remove(Object entity) {
		if(entity == null){
			return;
		}
		getGenericDao().remove(entity);
	}

	@SuppressWarnings("unchecked")
	public void remove(List entities) {
		if(entities == null){
			return;
		}
		getGenericDao().remove(entities.toArray());
	}


	@SuppressWarnings("unchecked")
	public void removeById(Class clazz, Serializable id) {
		if(id == null){
			return;
		}
		getGenericDao().removeById(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public void removeById(Class clazz, Serializable[] ids) {
		if(ids == null){
			return;
		}
		getGenericDao().removeById(clazz, ids);
	}

	public <T> T get(Class<T> clazz, Serializable id) 
	{
		if(id == null){
			return null;
		}
		return (T)getGenericDao().get(clazz, id);
	}

	public void save(Object entity) {
		if(entity == null){
			return;
		}
		getGenericDao().save(entity);
	}

	@SuppressWarnings("unchecked")
	public void save(List entities) {
		if(entities == null){
			return;
		}
		getGenericDao().save(entities.toArray());
	}

	public void saveOrUpdate(Object entity) {
		if(entity == null){
			return;
		}
		getGenericDao().saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	public void saveOrUpdate(List entities) {
		if(entities == null){
			return;
		}
		getGenericDao().saveOrUpdate(entities.toArray());
	}

	public void update(Object entity) {
		if(entity == null){
			return;
		}
		getGenericDao().update(entity);
	}


	@SuppressWarnings("unchecked")
	public void update(List entities) {
		if(entities == null){
			return;
		}
		getGenericDao().update(entities.toArray());
	}

	
	
}
