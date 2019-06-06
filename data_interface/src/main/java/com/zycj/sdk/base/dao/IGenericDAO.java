package com.zycj.sdk.base.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;


public interface IGenericDAO {
	
	public static final int INDEX_FOUR = 4;

	public static final int INDEX_SIX = 6;
	
	/**
	 * description 清除所有的缓存对象数据
	 * @author fengya
	 * @date 2015-5-25 上午10:37:18 
	 * @param 
	 * @return void
	 */
	public void clear();
	
	/**
	 * description 刷新缓存数据，进行数据同步
	 * @author fengya
	 * @date 2015-5-25 上午10:37:58 
	 * @param 
	 * @return void
	 */
	public void flush();
	
	/**
	 * description 清除指定对象的缓存数据
	 * @author fengya
	 * @date 2015-5-25 上午10:37:37 
	 * @param 
	 * @return void
	 */
	public void evict(Object entity);
	
	/**
	 * description 保存所有对象数据
	 * @author fengya
	 * @date 2015-5-25 上午10:39:00 
	 * @param @param os 对象数组
	 * @return void
	 */
	public void save(Object... os);
	
	/**
	 * description 更新所有对象数据
	 * @author fengya
	 * @date 2015-5-25 上午10:46:48 
	 * @param @param o
	 * @return void
	 */
	public void update(Object... o);
	
	/**
	 * description 保存或者更新集合中的数据对象，如果元素有对应的ID存在，做更新操作，如果没有，做添加操作
	 * @author fengya
	 * @date 2015-5-25 上午10:40:30 
	 * @param @param list 元素对象
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void saveOrUpdateAll(List list);
	
	/**
	 * description 保存或者更新元素对象，如果元素有对应的ID存在，做更新操作，如果没有，做添加操作
	 * @author fengya
	 * @date 2015-5-25 上午10:41:12 
	 * @param @param os
	 * @return void
	 */
	public void saveOrUpdate(Object... os);
	
	/**
	 * description 删除对象，对象中的主键必须存在
	 * @author fengya
	 * @date 2015-5-25 上午10:49:52 
	 * @param @param o
	 * @return void
	 */
	public void remove(Object... o);
	
	/**
	 * description 批量删除集合中的元素
	 * @author fengya
	 * @date 2015-5-25 上午10:50:21 
	 * @param @param list
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void batchRemove(List list);
	
	
	/**
	 * description 根据ID，从某一个实体类中删除一个对象
	 * @author fengya
	 * @date 2015-5-25 上午10:50:50 
	 * @param @param entityClass
	 * @param @param ids
	 * @return void
	 */
	public <T> void removeById(Class<T> entityClass, Serializable... ids);
	
	/**
	 * description 执行SQL语句的增删改操作
	 * @author fengya
	 * @date 2015-5-25 上午10:52:24 
	 * @param @param sql
	 * @return void
	 */
	public void executeUpdate(String sql);
	
	/**
	 * description 执行SQL语句的增删改操作，带有参数传递
	 * @author fengya
	 * @date 2015-5-25 上午10:58:19 
	 * @param @param sql SQL语句
	 * @param @param pairs 参数
	 * @return void
	 */
	public void executeUpdate(String sql, List<ParamPair<?>> pairs);
	
	/**
	 * description 调用存储过程
	 * @author fengya
	 * @date 2015-5-25 上午10:59:48 
	 * @param @param sql 调用语句
	 * @param @param parameters 传递参数
	 * @return void
	 */
	public void callProcedure(String sql, Object... parameters);
	/**
	 * description 根据ID获得实体对象
	 * @author fengya
	 * @date 2015-5-25 上午10:33:55 
	 * @param @param entityClass 实体对应的类
	 * @param @param id 记录对应的主键ID
	 * @param @return
	 * @return T
	 */
	public <T> T get(Class<T> entityClass, Serializable id);
	/**
	 * description 根据数组ID获得所有的实体对象
	 * @author fengya
	 * @date 2015-5-25 上午10:34:52 
	 * @param @param entityClass 实体对应的类
	 * @param @param ids 记录对应的主键ID
	 * @param @return
	 * @return List<T>
	 */
	public <T> List<T> get(Class<T> entityClass, Serializable... ids);

	/**
	 * description 根据HQL语句，对应的参数查询所有的记录数据
	 * @author fengya
	 * @date 2015-5-25 上午11:01:05 
	 * @param @param hql
	 * @param @param values
	 * @param @return
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List find(String hql, Object... values);

	/**
	 * description 根据SQL语句查询记录数据，注：结果List中的数据不是实体对象的集合
	 * @author fengya
	 * @date 2015-5-25 上午11:02:15 
	 * @param @param sqlContext
	 * @param @param parameters
	 * @param @return
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findBySql(final String sqlContext, final Object... parameters);

	/**
	 * description 根据SQL语句和参数查询唯一一条记录，注：查询结果中有多个记录时，报错
	 * @author fengya
	 * @date 2015-5-25 上午11:03:08 
	 * @param @param queryString
	 * @param @param values
	 * @param @return
	 * @param @throws DataAccessException
	 * @return Object
	 */
	public Object findUnique(String queryString, Object... values)
			throws DataAccessException;

	/**
	 * description 根据SQL语句，进行分页查询
	 * @author fengya
	 * @date 2015-5-25 上午11:03:54 
	 * @param @param hql HQL语句
	 * @param @param pageBean 分页对象
	 * @param @param parameters 查询参数
	 * @param @return
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findAsPage(final String hql, final Page pageBean,
                           final Object... parameters);

	/**
	 * description 根据SQL语句，参数进行分页查询数据，并实现字段排序查询，以及默认的排序字段
	 * @author fengya
	 * @date 2015-5-25 上午11:04:14 
	 * @param @param hql HQL语句
	 * @param @param pageBean 分页对象
	 * @param @param sort 排序字段集合
	 * @param @param alias 排序表对应的别名
	 * @param @param isDefaultSort 默认排序的字段名（注：SQL语句中的字段名）
	 * @param @param parameters 查询参数
	 * @param @return
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findAsPage(final String hql, final Page pageBean,
                           final Sort sort, final String alias, boolean isDefaultSort,
                           final Object... parameters);

	/**
	 * description 得到SQL语句中所有的记录个数
	 * @author fengya
	 * @date 2015-5-25 上午11:06:30 
	 * @param @param sqlContext
	 * @param @param parameters
	 * @param @return
	 * @return int
	 */
	public int getSQLCount(final String sqlContext, final Object... parameters);

	/**
	 * description 得到HQL语句中所有的记录个数
	 * @author fengya
	 * @date 2015-5-25 上午11:06:53 
	 * @param @param sqlContext
	 * @param @param parameters
	 * @param @return
	 * @return int
	 */
	public int getHQLCount(String sqlContext, Object... parameters);

	/**
	 * description 根据HQL语句查询所有的记录
	 * @author fengya
	 * @date 2015-5-25 上午11:07:22 
	 * @param @param queryString
	 * @param @return
	 * @param @throws DataAccessException
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List find(String queryString) throws DataAccessException;

	/**
	 * description 根据HQL语句，和参数参加记录
	 * @author fengya
	 * @date 2015-5-25 上午11:08:16 
	 * @param @param queryString
	 * @param @param pairs
	 * @param @return
	 * @param @throws DataAccessException
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List find(String queryString, List<ParamPair<?>> pairs)
			throws DataAccessException;
	/**
	 * description 根据SQL语句，参数进行查询数据，并实现字段排序查询，以及默认的排序字段
	 * @author fengya
	 * @date 2015-5-25 上午11:04:14 
	 * @param @param hql HQL语句
	 * @param @param sort 排序字段集合
	 * @param @param alias 排序表对应的别名
	 * @param @param isDefaultSort 默认排序的字段名（注：SQL语句中的字段名）
	 * @param @param parameters 查询参数
	 * @param @return
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List find(String hql, final Sort sort, final String alias,
                     boolean isDefaultSort, Object... values);

	/**
	 * description 根据sql语句进行查询记录个数
	 * @author fengya
	 * @date 2015-5-25 上午11:09:27 
	 * @param @param sqlContext sql语句
	 * @param @param parameters 参数
	 * @param @return
	 * @return int
	 */
	public int getSQLComCount(final String sqlContext,
                              final Object... parameters);
	
}
