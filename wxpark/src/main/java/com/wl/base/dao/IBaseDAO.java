package com.wl.base.dao;

import java.util.List;

import com.wl.base.model.Page;

public interface IBaseDAO {

    /**
     * 插入
     *
     * @param sqlId
     * @param paramObj
     * @return int
     */
    public int insert(String sqlId, Object paramObj);

    /**
     * 修改
     *
     * @param sqlId
     * @param paramObj
     * @return int
     */
    public int update(String sqlId, Object paramObj);

    /**
     * 删除
     *
     * @param sqlId
     * @param paramObj
     * @return int
     */
    public int delete(String sqlId, Object paramObj);

    /**
     * 返回一个对象
     *
     * @param sqlId
     * @param paramObj
     * @return Object
     */
    public Object selectOne(String sqlId, Object paramObj);

    /**
     * 返回对象列表(不含分页)
     *
     * @param sqlId
     * @param paramObj
     * @return List<Object>
     */
    public List<Object> selectList(String sqlId, Object paramObj);

    /**
     * 返回分页对象列表
     */
    public Page selectPageList(String sqlId, Object paramObj, int pageIndex, int pageSize, boolean isWithTotalCount);

}
