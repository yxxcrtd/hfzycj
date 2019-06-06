package com.zycj.sdk.base.service;

import java.io.Serializable;
import java.util.List;
/**
 * @description 业务逻辑层的基础封装
 * @author fengya
 * @date 2015-5-25 上午10:07:30
 */
public interface IBaseService {
	
	/**
	 * description 从spring容器中得到指定的bean
	 * @author fengya
	 * @date 2015-5-25 上午10:09:24 
	 * @param @param name
	 * @param @return
	 * @return Object
	 */
	public Object getBean(String name);
	
	/**
	 * description 采用get方法通过id获得实体
	 * @author fengya
	 * @date 2015-5-25 上午10:09:50 
	 * @param @param clazz
	 * @param @param id
	 * @param @return
	 * @return T
	 */
	public <T> T get(Class<T> clazz, final Serializable id);
	
	/**
	 * description 批量删除
	 * @author fengya
	 * @date 2015-5-25 上午10:13:44 
	 * @param @param list
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void batchRemove(List list);
	
	/**
	 * description 批量添加
	 * @author fengya
	 * @date 2015-5-25 上午10:14:00 
	 * @param @param list
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void batchMerge(List list);

	/**
	 * description 保存
	 * @author fengya
	 * @date 2015-5-25 上午10:14:00 
	 * @param @param list
	 * @return void
	 */
	public void save(final Object entity);

	/**
	 * description 删除
	 * @author fengya
	 * @date 2015-5-25 上午10:14:00 
	 * @param @param list
	 * @return void
	 */
	public void remove(final Object entity);

	/**
	 * description 更新
	 * @author fengya
	 * @date 2015-5-25 上午10:14:00 
	 * @param @param list
	 * @return void
	 */
	public void update(final Object entity);
	
	/**
	 * description 保存
	 * @author fengya
	 * @date 2015-5-25 上午10:14:00 
	 * @param @param list
	 * @return void
	 */
	public void saveOrUpdate(final Object entity);
	
	/**
	 * description 保存
	 * @author fengya
	 * @date 2015-5-25 上午10:14:00 
	 * @param @param list
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void save(final List entities);

	/**
	 * description 删除
	 * @author fengya
	 * @date 2015-5-25 上午10:14:00 
	 * @param @param list
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void remove(final List entities);

	/**
	 * description 更新
	 * @author fengya
	 * @date 2015-5-25 上午10:14:00 
	 * @param @param list
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void update(final List entities);
	
	/**
	 * description 保存全部
	 * @author fengya
	 * @date 2015-5-25 上午10:14:00 
	 * @param @param list
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void saveOrUpdate(final List entities);
	
	/**
	 * description 通过指定的主键移除相应的实体对象
	 * @author fengya
	 * @date 2015-5-25 上午10:15:45 
	 * @param @param clazz 删除对应的实体类
	 * @param @param id 删除对应的表记录中的ID
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void removeById(Class clazz, Serializable id);
	
	/**
	 * description 通过指定的主键移除所有相应的实体对象
	 * @author fengya
	 * @date 2015-5-25 上午10:16:53 
	 * @param @param clazz 删除对应的实体类
	 * @param @param id 删除对应的表记录中的ID
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void removeById(Class clazz, Serializable[] ids);
	
}
