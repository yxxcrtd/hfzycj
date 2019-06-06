package com.wl.base.service;

import java.util.List;

import com.wl.base.model.BaseBean;
import com.wl.base.model.Page;


public interface IBaseService {

    /**
     * 添加
     *
     * @param sqlId
     * @param paramObj
     * @return
     */
    public int add(String sqlId, Object paramObj);

    /**
     * 修改
     *
     * @param sqlId
     * @param paramObj
     * @return
     */
    public int modify(String sqlId, Object paramObj);

    /**
     * 删除
     *
     * @param sqlId
     * @param paramObj
     * @return
     */
    public int remove(String sqlId, Object paramObj);

    /**
     * 返回一个对象
     *
     * @param sqlId
     * @param paramObj
     * @return
     */
    public Object query(String sqlId, Object paramObj);

    /**
     * 返回一个对象列表
     *
     * @param sqlId
     * @param paramObj
     * @return
     */
    public List<Object> queryForList(String sqlId, Object paramObj);

    /**
     * 得到Page对象
     *
     * @param sqlId
     * @param paramObj         查询参数
     * @param pageIndex        翻页索引
     * @param pageSize         每页显示的行数
     * @param isWithTotalCount 是否查询总行数
     * @return page
     */
    public Page queryForPage(String sqlId, Object paramObj, int pageIndex, int pageSize, boolean isWithTotalCount);

    /**
     * 得到Page对象
     *
     * @param sqlId
     * @param paramObj  查询参数
     * @param pageIndex 翻页索引
     * @param pageSize  每页显示的行数
     * @return page
     */
    public Page queryForPage(String sqlId, Object paramObj, int pageIndex, int pageSize);

    /**
     * 得到分页对象
     *
     * @param sqlId
     * @param bean  其他Bean继承该Bean进行分页
     * @return
     */
    public Page queryForPage(String sqlId, BaseBean bean);

    /**
     * 把ids格式化成以逗号分隔的字符串,供sql的in查询
     *
     * @param ids
     * @return
     */
    public String format2InSQL(String ids);

    /**
     * 根据业务ID删除文件
     *
     * @param ref_id
     */
    public void delFileByRefId(String ref_id);

    /**
     * url验证
     *
     * @param str
     * @return
     */
    public String urlEnodeUTF8(String str);

}
