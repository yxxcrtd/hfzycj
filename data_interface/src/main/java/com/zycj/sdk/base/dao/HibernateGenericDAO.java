package com.zycj.sdk.base.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Column;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.CharacterType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.ObjectType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.Type;
import org.springframework.dao.DataAccessException;

import com.zycj.sdk.util.StringUtil;

/**
 * @author fengya
 * @description 基本的数据操作的封装
 * @date 2015-5-25 上午8:49:11
 */
public class HibernateGenericDAO implements IGenericDAO {
	private static final Logger LOG = Logger.getLogger(HibernateGenericDAO.class);

	/**
	 * 默认使用spring配置的sessionFactory做为DAO的sessionFactory
	 */
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	public void clear() {
		this.sessionFactory.getCurrentSession().clear();
	}

	@SuppressWarnings("unchecked")
	public List find(String hql, Object... values) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		setQueryParameters(query, values);
		return query.list();
	}

	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}

	public void evict(Object entity) {
		this.sessionFactory.getCurrentSession().evict(entity);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Class<T> entityClass, Serializable... ids) {
		List<T> result = new ArrayList<T>();
		if (ids != null) {
			Object obj = null;
			for (Serializable id : ids) {
				obj = this.sessionFactory.getCurrentSession().get(entityClass,
						id);
				if (obj != null) {
					result.add((T) obj);
				}
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> entityClass, Serializable id) {
		Object obj = this.sessionFactory.getCurrentSession().get(entityClass,
				id);
		if (obj != null) {
			return (T) obj;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void batchRemove(List list) {
		if (list != null) {
			for (Object obj : list) {
				this.sessionFactory.getCurrentSession().delete(obj);
			}
		}
	}

	public void remove(Object... o) {
		if (o != null) {
			for (Object obj : o) {
				this.sessionFactory.getCurrentSession().delete(obj);
			}
		}
	}

	public <T> void removeById(Class<T> entityClass, Serializable... ids) {
		if (ids != null) {
			for (Serializable id : ids) {
				this.sessionFactory.getCurrentSession().delete(
						this.sessionFactory.getCurrentSession().get(
								entityClass, id));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void saveOrUpdateAll(List list) {
		Iterator it = list.iterator();
		while (it.hasNext()) {
			this.sessionFactory.getCurrentSession().saveOrUpdate(it.next());
		}
	}

	public void save(Object... os) {
		for (Object obj : os) {
			this.sessionFactory.getCurrentSession().save(obj);
		}
	}

	public void saveOrUpdate(Object... os) {
		for (Object obj : os) {
			this.sessionFactory.getCurrentSession().saveOrUpdate(obj);
		}
	}

	public void update(Object... o) {
		this.sessionFactory.getCurrentSession().update(o);
	}

	/**
	 * @param sqlContext
	 * @param parameters
	 * @return List
	 * @方法名：findBySql
	 * @功能说明：执行SQL语句
	 * @author fengya
	 * @date 2014-2-19 下午02:44:51
	 */
	@SuppressWarnings("unchecked")
	public List findBySql(final String sqlContext, final Object... parameters) {
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(
				sqlContext);
		setQueryParameters(query, parameters);
		return query.list();
	}

	/**
	 * @param query
	 * @param paramValues
	 * @return void
	 * @方法名：
	 * @功能说明：对参数进行赋值
	 * @author fengya
	 * @date 2014-2-19 下午02:45:13
	 */
	private void setQueryParameters(Query query, Object[] paramValues) {
		if (paramValues == null
				|| (paramValues.length <= 0 || paramValues[0] == null)) {
			return;
		}
		for (int i = 0; i < paramValues.length; i++) {
			query.setParameter(i, paramValues[i]);
		}
	}

	/**
	 * @param sql
	 * @param parameters
	 * @return void
	 * @方法名：
	 * @功能说明：执行存储过程的方法
	 * @author fengya
	 * @date 2014-2-19 下午02:45:28
	 */
	public void callProcedure(String sql, Object... parameters) {
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(
				sql);
		setQueryParameters(query, parameters);
		query.executeUpdate();
	}

	/**
	 * @param sqlContext
	 * @param pageBean
	 * @param parameters
	 * @return List
	 * @方法名：findPageBySql
	 * @功能说明：分页执行SQL语句
	 * @author fengya
	 * @date 2014-2-19 下午02:45:41
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findPageBySql(Class<T> clazz, String sqlContext,
			Page pageBean, Object... parameters) {
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sqlContext);
		setQueryParameters(query, parameters);
		scalarChange(clazz, sqlContext, query);
		query.setResultTransformer(Transformers.aliasToBean(clazz));
		if (pageBean != null) {
			pageBean.setTotalCount(getSQLCount(sqlContext, parameters));
			query.setFirstResult(pageBean.getFirst() - 1);
			query.setMaxResults(pageBean.getPageSize());
			pageBean.setResult(query.list());
		} else {
			return query.list();
		}
		return pageBean.getResult();
	}

	/**
	 * 根据查询HQL,返回唯一对象
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	public Object findUnique(String queryString, Object... values)
			throws DataAccessException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				queryString);
		setQueryParameters(query, values);
		return query.uniqueResult();
	}

	/**
	 * 根据传入的SQL、转换类、参数对象 进行查询,并返回List,List中放的是转换类对象
	 * 
	 * @param clazz
	 * @param sql
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySQL(Class<T> clazz, String sql,
			Object... parameters) {
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql);
		setQueryParameters(query, parameters);
		scalarChange(clazz, sql, query);
		query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}

	/**
	 * select 字段 转换并根据类属性进行scalar设置
	 * 
	 * @param clazz
	 * @param sql
	 * @param query
	 */
	private void scalarChange(final Class<?> clazz, final String sql,
			SQLQuery query) {
		final String[] scalars = sql
				.substring(sql.indexOf("select"), sql.indexOf("from"))
				.replace("select", "").split(",");
		for (String scalar : scalars) {
			scalar = scalar.trim();
			int asIndex = scalar.lastIndexOf(" as");
			int spaceIndex = scalar.lastIndexOf(' ');
			int tabIndex = scalar.lastIndexOf('\t');
			int dotIndex = scalar.indexOf('.');
			if (asIndex != -1) { // 有as
				scalar = scalar.substring(asIndex + 3).trim();
			} else if (spaceIndex != -1 || tabIndex != -1) { // 没使用as, 使用了空格或tab
				if (spaceIndex != -1 && spaceIndex > tabIndex) {
					scalar = scalar.substring(spaceIndex).trim();
				} else {
					scalar = scalar.substring(tabIndex).trim();
				}
			} else if (dotIndex != -1) { // 没有使用alias 看看是不是使用了 .
				scalar = scalar.substring(dotIndex + 1).trim();
			} // 所有都不满足 就是scalar自己了
			query.addScalar(scalar, getScalarType(clazz, scalar));
		}
	}

	/**
	 * 得到类属性的 基本数据类型 对应的的 hibernate数据类型
	 * 
	 * @param clazz
	 *            Class
	 * @return Type
	 */
	private Type getScalarType(Class<?> clazz, String fieldName) {
		Class<?> fieldType = null;
		Field field = getClassField(clazz, fieldName);
		if (field == null) {
			throw new RuntimeException(clazz.getName() + "没有名为:" + fieldName
					+ "的属性出错");
		}
		fieldType = field.getType();
		if (fieldType == String.class) {
			return StringType.INSTANCE; // 字符串
		} else if (fieldType == Integer.class || fieldType == Integer.TYPE) {
			return IntegerType.INSTANCE; // 整形数字
		} else if (fieldType == Long.class || fieldType == Long.TYPE) {
			return LongType.INSTANCE; // 长整形数字
		} else if (fieldType == Double.class || fieldType == Double.TYPE) {
			return DoubleType.INSTANCE; // 双精度小数
		} else if (fieldType == Float.class || fieldType == Float.TYPE) {
			return FloatType.INSTANCE; // 单精度小数
		} else if (fieldType == Character.class || fieldType == Character.TYPE) {
			return CharacterType.INSTANCE; // 字符
		} else if (fieldType == java.util.Date.class
				|| (fieldType == java.sql.Date.class || fieldType == java.sql.Timestamp.class)) {
			return TimestampType.INSTANCE; // 日期参数
		} else {
			return ObjectType.INSTANCE; // 其他
		}
	}

	/**
	 * @param clazz
	 * @param fieldName
	 * @return Field
	 * @方法名：getClassField
	 * @功能说明：根据字段名获得字段属性信息
	 * @author fengya
	 * @date 2014-4-30 下午01:08:09
	 */
	private Field getClassField(Class<?> clazz, String fieldName) {
		if (clazz == null) {
			return null;
		}
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			field = getClassField(clazz.getSuperclass(), fieldName);
		}
		return field;
	}

	/**
	 * @param hql
	 *            ： HQL语句
	 * @param parameters
	 *            ： 执行HQL语句对应的参数
	 * @return int
	 * @方法名：findAsPage
	 * @功能说明：父类中封装的这个方法有问题，这里进行重写
	 * @author fengya
	 * @date 2014-03-14 下午02:15:30
	 */
	@SuppressWarnings("unchecked")
	public List findAsPage(final String hql, final Page pageBean,
			final Object... parameters) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		setQueryParameters(query, parameters);
		if (pageBean != null) {
			pageBean.setTotalCount(getHQLCount(hql, parameters));
			query.setFirstResult(pageBean.getFirst() - 1);
			query.setMaxResults(pageBean.getPageSize());
			pageBean.setResult(query.list());
		} else {
			return query.list();
		}
		return pageBean.getResult();
	}

	/**
	 * @param hql
	 *            ： 执行的SQL语句
	 * @param pageBean
	 *            ： 分页对象
	 * @param sort
	 *            ： 排序对象
	 * @param alias
	 *            ：HQL语句实体对象别名
	 * @param isDefaultSort
	 *            ： 是否需要默认排序，默认排序是根据字段createTime进行降序
	 * @param parameters
	 *            ： 查询参数对应的数据
	 * @return List ：返回的结果值
	 * @方法名：findAsPage
	 * @功能说明：对分页进行排序封装
	 * @author fengya
	 * @date 2014-4-28 下午04:57:43
	 */
	@SuppressWarnings("unchecked")
	public List findAsPage(final String hql, final Page pageBean,
			final Sort sort, final String alias, boolean isDefaultSort,
			final Object... parameters) {
		StringBuilder sbHQL = new StringBuilder(hql);
		if (sort != null && sort.getFields() == null && isDefaultSort) {
			sbHQL.append(" order by ").append(alias).append(".updateTime desc");
		} else if (sort != null) {
			if (!StringUtil.isEmpty(sort.getFields())) {
				sbHQL.append(" order by ");
				String[] fields = sort.getFields().split(",");
				String[] orders = sort.getOrders().split(",");
				for (int i = 0; i < fields.length - 1; i++) {
					sbHQL.append(alias).append('.').append(fields[i])
							.append(' ').append(orders[i]).append(',');
				}
				sbHQL.append(alias).append('.')
						.append(fields[fields.length - 1]).append(' ')
						.append(orders[orders.length - 1]);
			}
		}
		return this.findAsPage(sbHQL.toString(), pageBean, parameters);
	}

	/**
	 * @param sqlContext
	 *            ： SQL语句
	 * @param parameters
	 *            ： 执行SQL语句对应的参数
	 * @return int
	 * @方法名：getSQLCount
	 * @功能说明：在给定的SQL语句，进行统计表中的记录总数
	 * @author fengya
	 * @date 2013-11-14 下午02:15:30
	 */
	@SuppressWarnings("unchecked")
	public int getSQLCount(final String sqlContext, final Object... parameters) {
		String sqlLower = sqlContext.toLowerCase();
		int selIndex = sqlLower.indexOf("from");
		int selectIndex = sqlLower.indexOf("select");
		String selStr = sqlLower.substring(selectIndex + INDEX_SIX, selIndex);
		int oldIndex = 0;
		while (selStr.contains("select")) {
			oldIndex = selIndex;
			selIndex = sqlLower.indexOf("from", selIndex + INDEX_FOUR);
			selStr = sqlLower.substring(oldIndex + INDEX_FOUR, selIndex);
		}
		String sql = sqlLower.substring(selIndex, sqlLower.length());
		String paramStr = sqlLower.substring(0, selIndex);
		int paramCount = paramStr.split("\\?").length;
		Object[] params = null;
		if (paramCount > 1) {
			params = Arrays.copyOfRange(parameters, paramCount - 1,
					parameters.length);
		} else {
			params = parameters;
		}
		if (sql.indexOf(" order by ") != -1) {
			sql = sql.substring(0, sql.indexOf(" order by "));
		}
		sql = "select count(1) " + sql;
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(
				sql);
		setQueryParameters(query, params);
		List list = query.list();
		if (sql.contains("group by") && list != null) {
			return list.size();
		}
		if (list != null && !list.isEmpty()) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}

	/**
	 * @param sqlContext
	 * @param parameters
	 * @return int
	 * @方法名：getHQLCount
	 * @功能说明：得到HQL语句中的查询总记录数
	 * @author fengya
	 * @date 2014-3-6 下午03:14:36
	 */
	@SuppressWarnings("unchecked")
	public int getHQLCount(String sqlContext, Object... parameters) {
		String sqlLower = sqlContext.toLowerCase();
		int fromIndex = sqlLower.indexOf("from");
		int selectIndex = sqlLower.indexOf("select");
		String hql = null;
		Object[] params = parameters;
		if (selectIndex == -1 || selectIndex > fromIndex) {
			// 以from开头的HQL语句的处理
			hql = "select count(1) " + sqlContext;
		} else {
			String selStr = sqlLower.substring(selectIndex + INDEX_SIX,
					fromIndex);
			int oldIndex = 0;
			while (selStr.contains("select")) {
				oldIndex = fromIndex;
				fromIndex = sqlLower.indexOf("from", fromIndex + INDEX_FOUR);
				selStr = sqlLower.substring(oldIndex + INDEX_FOUR, fromIndex);
			}
			String sql = sqlContext.substring(fromIndex, sqlLower.length());
			String paramStr = sqlContext.substring(0, fromIndex);
			int paramCount = paramStr.split("\\?").length;
			if (paramCount > 1) {
				params = Arrays.copyOfRange(parameters, paramCount - 1,
						parameters.length);
			}
			hql = "select count(1) " + sql;
		}
		if (hql.contains(" order ")) {
			selectIndex = hql.indexOf(" order ");
			hql = hql.substring(0, selectIndex);
		}
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		setQueryParameters(query, params);
		List list = query.list();
		if (hql.contains("group by") && list != null) {
			return list.size();
		}
		if (list != null && !list.isEmpty()) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}

	/**
	 * 使用HQL语句检索数据,无参数 该方法可以进行hql的直接
	 * 
	 * @param queryString
	 * @return
	 * @throws DataAccessException
	 *             例：
	 */
	@SuppressWarnings("unchecked")
	public List find(String queryString) throws DataAccessException {
		return this.sessionFactory.getCurrentSession().createQuery(queryString)
				.list();
	}

	/**
	 * 使用带参数的hql查询数据,使用 :name 形式的占位符,好处就是在用 in (:name) 可以指定多个参数
	 * 
	 * @param queryString
	 * @param pairs
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List find(String queryString, List<ParamPair<?>> pairs)
			throws DataAccessException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				queryString);
		for (ParamPair<?> param : pairs) {
			applyNamedParameterToQuery(query, param.getName(), param.getValue());
		}
		return query.list();
	}

	/**
	 * 参数赋值
	 * 
	 * @param queryObject
	 * @param paramName
	 * @param value
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	protected void applyNamedParameterToQuery(Query queryObject,
			String paramName, Object value) throws HibernateException {
		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection) value);
		} else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		} else {
			queryObject.setParameter(paramName, value);
		}
	}

	@SuppressWarnings("unchecked")
	public List find(String hql, final Sort sort, final String alias,
			boolean isDefaultSort, Object... values) throws DataAccessException {
		StringBuilder sbHQL = new StringBuilder(hql);
		if (sort != null && sort.getFields() == null && isDefaultSort) {
			sbHQL.append(" order by ").append(alias).append(".updateTime desc");
		} else if (sort != null) {
			if (!StringUtil.isEmpty(sort.getFields())) {
				sbHQL.append(" order by ");
				String[] fields = sort.getFields().split(",");
				String[] orders = sort.getOrders().split(",");
				for (int i = 0; i < fields.length - 1; i++) {
					sbHQL.append(alias).append('.').append(fields[i])
							.append(' ').append(orders[i]).append(',');
				}
				sbHQL.append(alias).append('.')
						.append(fields[fields.length - 1]).append(' ')
						.append(orders[orders.length - 1]);
			}
		}
		return this.find(sbHQL.toString(), values);
	}

	/**
	 * 根据sql查询数据并封装成对象, 使用 :name 占位符形式 而不是 ?
	 * 
	 * @param <T>
	 * @param clazz
	 *            返回的封装对象类型
	 * @param sql
	 * @param pairs
	 *            命名的参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySQLQuery(final Class<T> clazz, String sql,
			List<ParamPair<?>> pairs) {
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(clazz));
		scalarChange(clazz, sql, query);
		for (ParamPair<?> param : pairs) {
			applyNamedParameterToQuery(query, param.getName(), param.getValue());
		}
		return query.list();
	}

	/**
	 * 使用带参数的HSQL语句检索数据
	 * 
	 * @param queryString
	 *            hql 语句
	 * @param firstResult
	 *            开始
	 * @param maxResults
	 *            每页数
	 * @return
	 * @throws DataAccessException
	 *             例：StringBuilder hql = new StringBuilder(
	 *             "from RecallActivity recallActivity where 1=1 " );
	 *             hql.append("and dateType = "+ dataHierarchyType);
	 *             this.getHql(recallActivity, recallActPropertySearchParamVo,
	 *             hql); recallActlist = this.commonDao.find(hql.toString(),
	 *             page.getStart(), page.getPageSize());
	 */
	@SuppressWarnings("unchecked")
	public List find(String queryString, int firstResult, int maxResults)
			throws DataAccessException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				queryString);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.list();
	}

	/**
	 * 根据sql查询数据并封装成对象, 使用 :name 占位符形式 而不是 ?
	 * 
	 * @param <T>
	 * @param clazz
	 *            返回的封装对象类型
	 * @param sql
	 *            查询的sql语句
	 * @param firstResult
	 * @param maxResult
	 * @param pairs
	 *            命名的参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySQLQuery(Class<T> clazz, String sql,
			int firstResult, int maxResult, List<ParamPair<?>> pairs) {
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql);
		query.setFirstResult(firstResult).setMaxResults(maxResult);
		query.setResultTransformer(Transformers.aliasToBean(clazz));
		scalarChange(clazz, sql, query);
		for (ParamPair<?> param : pairs) {
			applyNamedParameterToQuery(query, param.getName(), param.getValue());
		}
		return query.list();
	}

	/**
	 * <b>方法名</b>：executeUpdate<br>
	 * <b>功能</b>：执行自定义SQL语句，未设定返回值<br>
	 * 
	 * @param sql
	 * @author <font color='blue'>Yao.W</font>
	 * @date 2014-7-8 下午02:04:39
	 */
	public void executeUpdate(String sql) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.executeUpdate();
	}

	/**
	 * <b>方法名</b>：executeUpdate<br>
	 * <b>功能</b>：执行自定义SQL语句,带参数，未设定返回值<br>
	 * 
	 * @param sql
	 * @param pairs
	 * @author <font color='blue'>Yao.W</font>
	 * @date 2014-7-9 上午09:26:00
	 */
	public void executeUpdate(String sql, List<ParamPair<?>> pairs) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		for (ParamPair<?> param : pairs) {
			applyNamedParameterToQuery(query, param.getName(), param.getValue());
		}
		query.executeUpdate();
	}

	/**
	 * @description 根据sql语句进行分页、排序、查找功能的实现
	 * @author fengya
	 * @date 2015-6-23 上午11:37:17
	 * @param @param clazz 执行结果封装的类
	 * @param @param sql 执行的sql语句
	 * @param @param pageBean 分页对象
	 * @param @param sort 排序对象
	 * @param @param sortCls 排序对应的实体类
	 * @param @param alias 排序对应的表中的别名
	 * @param @param isDefaultSort 是否支持默认排序
	 * @param @param parameters 查询参数
	 * @param @return
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findAsPageBySql(final Class<?> clazz, final String sql,
			final Page pageBean, final Sort sort, final Class<?> sortCls,
			final String alias, boolean isDefaultSort,
			final Object... parameters) {
		StringBuilder sbHQL = new StringBuilder(sql);
		if (sort != null && sort.getFields() == null && isDefaultSort) {
			sbHQL.append(" order by ").append(alias)
					.append(".update_time desc");
		} else if (sort != null) {
			if (!StringUtil.isEmpty(sort.getFields())) {
				sbHQL.append(" order by ");
				String[] fields = sort.getFields().split(",");
				String[] orders = sort.getOrders().split(",");
				String columnName = null;
				for (int i = 0; i < fields.length - 1; i++) {
					columnName = this.getColumnName(sortCls, fields[i]);
					sbHQL.append(alias).append('.').append(columnName).append(' ').append(orders[i]).append(',');
				}
				columnName = this.getColumnName(sortCls, fields[fields.length - 1]);
				sbHQL.append(alias).append('.').append(columnName).append(' ').append(orders[orders.length - 1]);
			}
		}
		return this
				.findPageBySql(clazz, sbHQL.toString(), pageBean, parameters);
	}
	
	private String getColumnName(final Class<?> sortCls,final String fieldName){
		String fieldNameMethod = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
		String columnName = null;
		try {
			Method method = sortCls.getMethod(fieldNameMethod);
			Column col = method.getAnnotation(Column.class);
			columnName = col.name();
		} catch (Exception e) {
			LOG.debug("得到列出现了异常！",e);
		}
		
		return columnName;
	}

	/**
	 * <b>方法名</b>：findPageByComSql<br>
	 * <b>功能</b>：分页执行复杂SQL语句<br>
	 * 
	 * @param <T>
	 * @param clazz
	 * @param sqlContext
	 * @param pageBean
	 * @param parameters
	 * @return
	 * @author <font color='blue'>zhoulh</font>
	 * @date 2014-8-19 下午03:44:29
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findPageByComSql(Class<T> clazz, String sqlContext,
			Page pageBean, Object... parameters) {
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sqlContext);
		setQueryParameters(query, parameters);
		scalarChange(clazz, sqlContext, query);
		query.setResultTransformer(Transformers.aliasToBean(clazz));
		if (pageBean != null) {
			pageBean.setTotalCount(getSQLComCount(sqlContext, parameters));
			query.setFirstResult(pageBean.getFirst() - 1);
			query.setMaxResults(pageBean.getPageSize());
			pageBean.setResult(query.list());
		} else {
			return query.list();
		}
		return pageBean.getResult();
	}

	/**
	 * <b>方法名</b>：getSQLComCount<br>
	 * <b>功能</b>：得复杂查询语句中的查询总记录数<br>
	 * 
	 * @param sqlContext
	 * @param parameters
	 * @return
	 * @author <font color='blue'>zhoulh</font>
	 * @date 2014-8-19 下午05:28:36
	 */
	@SuppressWarnings("unchecked")
	public int getSQLComCount(final String sqlContext,
			final Object... parameters) {
		String sql = "select count(1) from (" + sqlContext + ") as sqlQueryAlias";
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(
				sql);
		setQueryParameters(query, parameters);
		List list = query.list();
		if (list != null && !list.isEmpty()) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}

}