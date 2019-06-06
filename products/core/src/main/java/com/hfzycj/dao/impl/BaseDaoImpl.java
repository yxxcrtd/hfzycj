package com.hfzycj.dao.impl;

import com.hfzycj.dao.BaseDao;
import com.hfzycj.dao.RedisDao;
import com.hfzycj.util.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseDaoImpl<T, PK extends Serializable, K, V> extends RedisDao<K, V> implements BaseDao<T, PK> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoImpl.class);
	private static final int SQL_FLAG_INSERT = 1;
	private static final int SQL_FLAG_UPDATE = 2;
	private static final int SQL_FLAG_DELETE = 3;
	private Class<T> clazz = null;
	private String tableName = "";

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
		tableName = clazz.getSimpleName().toLowerCase();
	}

	/**
	 * 根据主键查找对象
	 * @param id
	 * @return
	 */
	public T findById(PK id) {
		String sql = new StringBuffer("SELECT * FROM t_").append(tableName).append(" WHERE ").append(tableName).append("_id = ?").toString();
		LOGGER.info(sql + " : " + id);
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(clazz);
		List<T> list = jdbcTemplate.query(sql, rowMapper, new Object[] { id });
		return (null != list && !list.isEmpty() && 0 < list.size()) ? list.get(0) : null;
	}

	/**
	 * 有事务的时候调用这个
	 * @param id
	 * @return
	 */
	public T getById(PK id) {
		String sql = new StringBuffer("SELECT * FROM t_").append(tableName).append(" WHERE ").append(tableName).append("_id = ?").append(" FOR UPDATE").toString();
		LOGGER.info(sql + " : " + id);
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(clazz);
		List<T> list = jdbcTemplate.query(sql, rowMapper, new Object[] { id });
		return (null != list && !list.isEmpty() && 0 < list.size()) ? list.get(0) : null;
	}


	/**
	 * 分页总数
	 * @param t
	 * @param whereString
	 * @return
	 */
	public int findAllCount(T t, String whereString) {
		whereString = (!"".equals(whereString) && null != whereString && 0 < whereString.length()) ? " WHERE " + whereString : "";
		String sql = new StringBuffer("SELECT count(1) AS cnt FROM t_").append(tableName).append(whereString).toString();
		LOGGER.info(sql);
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		return Integer.valueOf(map.get("cnt").toString());
	}

	@Override
	public List<T> findByPager(Pager pager) {
		String sql = new StringBuffer("SELECT * FROM t_").append(tableName).append("\" ORDER BY \"").append(tableName).append("Id\" DESC LIMIT ").append(pager.getPageSize()).append(" OFFSET ").append(pager.getOffset()).toString();
		LOGGER.info(sql + " : " + pager.getPageSize() + " , " + pager.getOffset());
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(clazz));
	}

	/**
	 * 分页
	 * @param pager
	 * @param whereString
	 * @param orderbyString
	 * @return
	 */
	public List<T> findByPager(Pager pager, String whereString, String orderbyString) {
		whereString = (!"".equals(whereString) && null != whereString && 0 < whereString.length()) ? " WHERE " + whereString : "";
		orderbyString = (!"".equals(orderbyString) && null != orderbyString && 0 < orderbyString.length()) ? " ORDER BY " + orderbyString : "";
		String sql = new StringBuffer("SELECT * FROM t_").append(tableName).append(whereString).append(orderbyString).append(" LIMIT ").append(pager.getPageSize()).append(" OFFSET ").append(pager.getOffset()).toString();
		LOGGER.info(sql + " : " + pager.getPageSize() + " , " + pager.getOffset());
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(clazz));
	}

	@Override
	public List<T> findAllList(String whereString, String orderbyString, Integer limit) {
		whereString = (!"".equals(whereString) && null != whereString && 0 < whereString.length()) ? " WHERE " + whereString : "";
		orderbyString = (!"".equals(orderbyString) && null != orderbyString && 0 < orderbyString.length()) ? " ORDER BY " + orderbyString : "";
		String limitString = null == limit ? "" : " LIMIT " + Integer.valueOf(limit);
		String sql = new StringBuffer("SELECT * FROM t_").append(tableName).append(whereString).append(orderbyString).append(limitString).toString();
		LOGGER.info(sql);
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(clazz));
	}

	@Override
	public List<T> findAllListById(int id) {
		return null;
	}

	/**
	 * 保存
	 * @param t
	 * @return
	 */
	public Long save(T t) {
		String sql = setSql(SQL_FLAG_INSERT);
		LOGGER.info(sql);
		Object[] args = setArgs(t, SQL_FLAG_INSERT);
		int[] argTypes = setArgTypes(t, SQL_FLAG_INSERT);
		return (long) jdbcTemplate.update(sql, args, argTypes);
	}

	/**
	 * 修改
	 * @param t
	 */
	public void update(T t) {
		String sql = setSql(SQL_FLAG_UPDATE);
		LOGGER.info(sql);
		Object[] args = setArgs(t, SQL_FLAG_UPDATE);
		int[] argTypes = setArgTypes(t, SQL_FLAG_UPDATE);
		jdbcTemplate.update(sql, args, argTypes);
	}

	/**
	 * 根据主键删除
	 * @param id
	 */
	public void delete(PK id) {
		String sql = new StringBuffer("DELETE FROM t_").append(tableName).append(" WHERE ").append(tableName).append("_id = ?").toString();
		LOGGER.info(sql + " : " + id);
		jdbcTemplate.update(sql, id);
	}

	/**
	 * 删除对象
	 * @param t
	 */
	public void delete(T t) {
		String sql = setSql(SQL_FLAG_DELETE);
		LOGGER.info(sql);
		Object[] args = setArgs(t, SQL_FLAG_DELETE);
		int[] argTypes = setArgTypes(t, SQL_FLAG_DELETE);
		jdbcTemplate.update(sql, args, argTypes);
	}

	/**
	 * Set SQL
	 */
	private String setSql(int flag) {
		StringBuffer sql = new StringBuffer();
		Field[] fields = clazz.getDeclaredFields();
		switch (flag) {
			case SQL_FLAG_INSERT:
				sql.append("INSERT INTO t_" + tableName + " (");
				for (int i = 1; null != fields && i < fields.length; i++) {
					fields[i].setAccessible(true);
					String column = fields[i].getName();
					LOGGER.debug(column);
					if (!column.equals("" + tableName + "_id")) {
						sql.append(column).append(",");
					}
				}
				sql = sql.deleteCharAt(sql.length() - 1);
				sql.append(") VALUES (");
				for (int i = 1; null != fields && i < fields.length; i++) {
					sql.append("?,");
				}
				sql = sql.deleteCharAt(sql.length() - 1);
				sql.append(")");
				break;
			case SQL_FLAG_UPDATE:
				sql.append("UPDATE t_" + tableName + " SET ");
				for (int i = 1; null != fields && i < fields.length; i++) {
					fields[i].setAccessible(true);
					String column = fields[i].getName();
					LOGGER.debug(column);
					if (!column.equals("" + tableName + "_id")) {
						sql.append(column).append(" = ?,");
					}
				}
				sql = sql.deleteCharAt(sql.length() - 1);
				sql.append(" WHERE ").append(tableName).append("_id = ?");
				break;
			case SQL_FLAG_DELETE:
				sql.append("DELETE FROM t_").append(tableName).append(" WHERE ").append(tableName).append("_id = ?");
				break;
			default:
				break;
		}
		return sql.toString();
	}

	/**
	 * Set Object Args
	 *
	 * @param t
	 * @param flag
	 * @return
	 */
	private Object[] setArgs(T t, int flag) {
		Field[] fields = clazz.getDeclaredFields();
		int len = fields.length;
		Object[] args = null;
		switch (flag) {
			case SQL_FLAG_INSERT:
				args = new Object[len - 1];
				try {
					for (int i = 1; null != fields && i < len; i++) {
						fields[i].setAccessible(true);
						args[i - 1] = fields[i].get(t);
						LOGGER.debug(String.valueOf(fields[i].get(t)));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case SQL_FLAG_UPDATE:
				args = new Object[len];
				Object[] tempArr = new Object[len];
				try {
					for (int i = 0; null != fields && i < len; i++) {
						fields[i].setAccessible(true);
						tempArr[i] = fields[i].get(t);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.arraycopy(tempArr, 1, args, 0, tempArr.length - 1);
				args[args.length - 1] = tempArr[0];
				break;
			case SQL_FLAG_DELETE:
				args = new Object[1];
				try {
					fields[0].setAccessible(true);
					args[0] = fields[0].get(t);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		return args;
	}

	/**
	 * Set Object Args Array
	 *
	 * @param t
	 * @param flag
	 * @return
	 */
	private int[] setArgTypes(T t, int flag) {
		Field[] fields = clazz.getDeclaredFields();
		int len = fields.length;
		int[] argTypes = null;
		switch (flag) {
			case SQL_FLAG_INSERT:
				argTypes = new int[len - 1];
				try {
					for (int i = 1; null != fields && i < len; i++) {
						fields[i].setAccessible(true);
						LOGGER.debug(fields[i].get(t).getClass().getName());
						if ("java.lang.Integer".equals(fields[i].get(t).getClass().getName())) {
							argTypes[i - 1] = Types.INTEGER;
						} else if ("java.lang.Long".equals(fields[i].get(t).getClass().getName())) {
							argTypes[i - 1] = Types.BIGINT;
						} else if ("java.util.Date".equals(fields[i].get(t).getClass().getName())) {
							argTypes[i - 1] = Types.TIMESTAMP;
						} else if ("java.lang.Boolean".equals(fields[i].get(t).getClass().getName())) {
							argTypes[i - 1] = Types.BOOLEAN;
						} else if ("java.lang.Float".equals(fields[i].get(t).getClass().getName())) {
							argTypes[i - 1] = Types.FLOAT;
						} else if ("java.lang.Double".equals(fields[i].get(t).getClass().getName())) {
							argTypes[i - 1] = Types.DOUBLE;
						} else if ("java.math.BigDecimal".equals(fields[i].get(t).getClass().getName())) {
							argTypes[i - 1] = Types.DECIMAL;
						} else if ("java.sql.Timestamp".equals(fields[i].get(t).getClass().getName())) {
							argTypes[i - 1] = Types.TIMESTAMP;
						} else {
							argTypes[i - 1] = Types.VARCHAR;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case SQL_FLAG_UPDATE:
				argTypes = new int[len];
				int[] tempArgTypes = new int[len];
				try {
					for (int i = 0; i < tempArgTypes.length; i++) {
						fields[i].setAccessible(true);
						LOGGER.debug(fields[i].get(t).getClass().getName());
						if ("java.lang.Integer".equals(fields[i].get(t).getClass().getName())) {
							tempArgTypes[i] = Types.INTEGER;
						} else if ("java.lang.Long".equals(fields[i].get(t).getClass().getName())) {
							tempArgTypes[i] = Types.BIGINT;
						} else if ("java.util.Date".equals(fields[i].get(t).getClass().getName())) {
							tempArgTypes[i] = Types.TIMESTAMP;
						} else if ("java.lang.Boolean".equals(fields[i].get(t).getClass().getName())) {
							tempArgTypes[i] = Types.BOOLEAN;
						} else if ("java.lang.Float".equals(fields[i].get(t).getClass().getName())) {
							tempArgTypes[i] = Types.FLOAT;
						} else if ("java.lang.Double".equals(fields[i].get(t).getClass().getName())) {
							tempArgTypes[i] = Types.DOUBLE;
						} else if ("java.math.BigDecimal".equals(fields[i].get(t).getClass().getName())) {
							tempArgTypes[i] = Types.DECIMAL;
						} else if ("java.sql.Timestamp".equals(fields[i].get(t).getClass().getName())) {
							tempArgTypes[i] = Types.TIMESTAMP;
						} else {
							tempArgTypes[i] = Types.VARCHAR;
						}
					}
					System.arraycopy(tempArgTypes, 1, argTypes, 0, tempArgTypes.length - 1);
					argTypes[argTypes.length - 1] = tempArgTypes[0];
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case SQL_FLAG_DELETE:
				argTypes = new int[1];
				try {
					fields[0].setAccessible(true);
					if ("java.lang.Integer".equals(fields[0].get(t).getClass().getName())) {
						argTypes[0] = Types.INTEGER;
					} else {
						argTypes[0] = Types.VARCHAR;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		return argTypes;
	}

	// 根据业务需要重写该方法
	public List<T> findListByLeftJoin(Pager pager, String whereString) {
		return null;
	}


	public Long lPush(final String key, final String value) {
		Long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisSerializer();
            byte[] keyId = serializer.serialize(key);
            byte[] name = serializer.serialize(value);
            return connection.lPush(keyId, name);
        });
		return result;
	}

	public boolean zAdd(final String key, final long i, final String value) {
		boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = getRedisSerializer();
            byte[] keyId = serializer.serialize(key);
            byte[] name = serializer.serialize(value);
            return connection.zAdd(keyId, i, name);
        });
		return result;
	}

	public void set(final String key, final String value, final Long liveTime) {
		redisTemplate.execute((RedisCallback<Object>) connection -> {
			connection.set(key.getBytes(), value.getBytes());
			if (0 < liveTime) {
				connection.expire(key.getBytes(), liveTime);
			}
			return 1L;
		});
	}

	public List<String> get(final String key) {
		final List<String> list = new ArrayList<>();
		redisTemplate.execute((RedisCallback<List<String>>) connection -> {
            RedisSerializer<String> serializer = getRedisSerializer();
            byte[] keyId = serializer.serialize(key);
            Set<byte[]> l = connection.zRange(keyId, 0, -1);
            for (byte[] s : l) {
                list.add(new String(s));
            }
            return list;
        });
		return list;
	}

	public Long del(final List<String> keys) {
		return redisTemplate.execute((RedisCallback<Long>) connection -> {
            long result = 0;
            for (int i = 0; i < keys.size(); i++) {
                result = connection.del(keys.get(i).getBytes());
            }
            return result;
        });
	}

}
