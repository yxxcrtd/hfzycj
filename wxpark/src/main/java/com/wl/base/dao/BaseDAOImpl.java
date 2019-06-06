package com.wl.base.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wl.base.interceptor.PageInterceptor;
import com.wl.base.model.Page;


@Repository
public class BaseDAOImpl implements IBaseDAO {

    @Autowired
    private SqlSession sqlSessionTemplate;

    /**
     * 插入
     *
     * @param sqlId
     * @param paramObj
     * @return int
     */
    public int insert(String sqlId, Object paramObj) {
        return this.sqlSessionTemplate.insert(sqlId, paramObj);
    }

    /**
     * 修改
     *
     * @param sqlId
     * @param paramObj
     * @return int
     */
    public int update(String sqlId, Object paramObj) {
        return this.sqlSessionTemplate.update(sqlId, paramObj);
    }

    /**
     * 删除
     *
     * @param sqlId
     * @param paramObj
     * @return int
     */
    public int delete(String sqlId, Object paramObj) {
        return this.sqlSessionTemplate.delete(sqlId, paramObj);
    }

    /**
     * 返回一个对象
     *
     * @param sqlId
     * @param paramObj
     * @return Object
     */
    public Object selectOne(String sqlId, Object paramObj) {
        return this.sqlSessionTemplate.selectOne(sqlId, paramObj);
    }

    /**
     * 返回对象列表(不含分页)
     *
     * @param sqlId
     * @param paramObj
     * @return List<Object>
     */
    public List<Object> selectList(String sqlId, Object paramObj) {
        return this.sqlSessionTemplate.selectList(sqlId, paramObj);
    }

    /**
     * 返回分页对象列表
     */
    public Page selectPageList(String sqlId, Object paramObj, int pageIndex, int pageSize, boolean isWithTotalCount) {
        //设置分页对象,线程安全
        PageInterceptor.LOCAL_PAGE.set(new Page(pageIndex, pageSize, isWithTotalCount));
        Page page = PageInterceptor.LOCAL_PAGE.get();
        page.setResultList(this.sqlSessionTemplate.selectList(sqlId, paramObj));
        //设置总页数
        if (page.getTotalCount() == 0) {
            page.setTotalPage(1);
        } else {
            page.setTotalPage((page.getTotalCount() + page.getPageSize() - 1) / page.getPageSize());
        }
        //在当前线程中清除Page对象
        PageInterceptor.LOCAL_PAGE.remove();
        return page;
    }
}
