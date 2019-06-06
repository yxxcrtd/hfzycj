package com.wl.base.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.wl.base.model.Page;


/**
 * Mybatis - 通用分页拦截器
 * 此类参照作者liuzh/abel533/isea533分页实现，略有改动.
 * @author changwt
 * @version 3.2.2
 * @url http://git.oschina.net/free/Mybatis_PageHelper
 */
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class PageInterceptor implements Interceptor{
	
	public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    public static final ReflectorFactory DEFUALT_REFLECTOR_FACTORY=new DefaultReflectorFactory();
    
    private static final String BOUND_SQL = "sqlSource.boundSql.sql";
    
    public static final ThreadLocal<Page> LOCAL_PAGE = new ThreadLocal<Page>();
    
    private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList<ResultMapping>(0);
    
    //SQL缓存
    private static final Cache<Integer, String> COUNT_CACHE = CacheBuilder.newBuilder().maximumSize(500).expireAfterWrite(30, TimeUnit.MINUTES).build();
    
   //SQL反解析
    //private static final UnParser UNPARSER = new UnParser();
    
    //数据库方言
    private static String dialect = "";
    
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		final Object[] args = invocation.getArgs();
        RowBounds rowBounds = (RowBounds) args[2];
        //分页信息
        Page page=LOCAL_PAGE.get();
        //LOCAL_PAGE.remove();
        /*if ( page== null && rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }*/
        if ( page== null) {
            return invocation.proceed();
        }
        //忽略RowBounds-否则会进行Mybatis自带的内存分页
        args[2] = RowBounds.DEFAULT;
        MappedStatement ms = (MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = ms.getBoundSql(parameterObject);
      //创建一个新的MappedStatement
        MappedStatement qs = newMappedStatement(ms, new BoundSqlSqlSource(boundSql));
        //将参数中的MappedStatement替换为新的qs，防止并发异常
        args[0] = qs;
        MetaObject msObject = forObject(qs);
        String sql = (String) msObject.getValue(BOUND_SQL);
        //判断是否进行totalCount查询
        if (page.isWithTotalCount()) {
            //求count - 重写sql
            msObject.setValue(BOUND_SQL, getCountSql(sql));
            //查询总数
            Object result = invocation.proceed();
            //设置总数
            page.setTotalCount((Integer) ((List<?>) result).get(0));
            if (page.getTotalCount() == 0) {
                return new ArrayList<Object>();
            }
        }
        //分页sql - 重写sql
        msObject.setValue(BOUND_SQL, getPageSql(sql, page));
        //恢复类型
        msObject.setValue("resultMaps", ms.getResultMaps());
        //执行分页查询
        //Object result = invocation.proceed();
        //得到处理结果
        //page.addAll((List) result);
        //返回结果
        return invocation.proceed();
	}
	/**
     * 反射对象，增加对低版本Mybatis的支持
     *
     * @param object
     * @return
     */
    public static MetaObject forObject(Object object) {
        //return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    	return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFUALT_REFLECTOR_FACTORY);
    }

	private class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
	 /**
     * 由于MappedStatement是一个全局共享的对象，因而需要复制一个对象来进行操作，防止并发访问导致错误
     *
     * @param ms
     * @param newSqlSource
     * @return
     */
    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        //由于resultMaps第一次需要返回int类型的结果，所以这里需要生成一个resultMap - 防止并发错误
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), int.class, EMPTY_RESULTMAPPING).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }
    
    /**
     * 获取总数sql - 如果要支持其他数据库，修改这里就可以
     *
     * @param sql
     * @return
     */
    private String getCountSql(final String sql) {
        try {
            return COUNT_CACHE.get(sql.hashCode(), new Callable<String>() {
                @Override
                public String call() throws Exception {
                    if (sql.toUpperCase().contains("ORDER")) {
                        //return "select count(0) from (" + UNPARSER.removeOrderBy(sql) + ") tab_count";
                    	return "select count(0) from (" + removeOrderBy(sql) + ") tab_count";
                    } else {
                        return "select count(0) from (" + sql + ") tab_count";
                    }
                }
            });
        } catch (Exception e) {
            return "select count(0) from (" + sql + ") tab_count";
        }
    }
    /**
     * 解析 - 去掉order by 语句
     * 太麻烦 ,而且" select t.id from user t" 经过转换后结果："select t.id id from user as t" oracle不支持
     */
    /*private static class UnParser extends NodeToString {
        private static final SQLParser PARSER = new SQLParser();

        public String removeOrderBy(String sql) throws StandardException {
            StatementNode stmt = PARSER.parseStatement(sql);
            String newSql=toString(stmt);
            return newSql.replace("AS", "");
        }

        @Override
        protected String orderByList(OrderByList node) throws StandardException {
            return "";
        }
    }*/
    private String removeOrderBy(String sql){
    	if(sql==null){
    		return sql;
    	}
    	//去除多余的空格、回车、换行
    	Pattern p = Pattern.compile("\\s+|\t|\r|\n");
    	Matcher  m=p.matcher(sql+" ");
    	//格式化成标准格式的sql,select id,name from table order by id asc 
    	String usql=m.replaceAll(" ").toLowerCase();
    	int w=usql.lastIndexOf(" where ");
    	int o=usql.lastIndexOf(" order by ");
    	int asc=usql.lastIndexOf(" asc ");
    	int desc=usql.lastIndexOf(" desc ");
    	int endIdx=asc;
    	if(desc>asc){
    		endIdx=desc;
    	}
    	String subStr=null;
    	if(o > w && endIdx > (o+7)){
    		subStr=usql.substring(o, endIdx+5);
    		usql=usql.replace(subStr, " ");
    	}
    	return usql;
    }
    /**
     * 获取分页sql - 如果要支持其他数据库，修改这里就可以
     *
     * @param sql
     * @param rowBounds
     * @return
     */
    private String getPageSql(String sql, Page page) {
        StringBuilder pageSql = new StringBuilder(200);
        int startRow=page.getStartRow();
        int pageSize=page.getPageSize();
        int endRow=startRow+pageSize;
        if ("mysql".equals(dialect)) {
            pageSql.append(sql);
            pageSql.append(" limit " + startRow + "," + pageSize);
        } else if ("hsqldb".equals(dialect)) {
            pageSql.append(sql);
            pageSql.append(" LIMIT " + pageSize + " OFFSET " + startRow);
        } else if ("oracle".equals(dialect)) {
            pageSql.append("select * from ( select tab_.*, rownum rownum_ from ( ");
            pageSql.append(sql);
            pageSql.append(" ) tab_ where rownum <= ").append(endRow);
            pageSql.append(") where rownum_ > ").append(startRow);
        }
        return pageSql.toString();
    }
	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
	}
	/**
     * 设置属性值
     *
     * @param p
     */
	@Override
	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
        if (dialect == null || "".equals(dialect)) {
            throw new RuntimeException("Mybatis分页插件无法获取dialect参数!");
        }
	}

}
