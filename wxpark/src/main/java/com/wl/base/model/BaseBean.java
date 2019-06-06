package com.wl.base.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础bean,其他的Bean可以继承该Bean
 *
 * @author Administrator
 */
public class BaseBean {
    private static final int PAGE_SIZE = 20;//默认每页显示20行
    private static final int PAGE_INDEX = 1;//默认翻页索引从1开始
    private static final int IS_COUNT = 1;//默认要查询总行数

    /**
     * 当前页索引
     */
    private int page = PAGE_INDEX;

    /**
     * 每页显示行数
     */
    private int rows = PAGE_SIZE;

    /**
     * 排序字段
     */
    //private String sidx;
    private String sort;

    /**
     * 排序方向asc(升序),desc(降序)
     */
    //private String sord;
    private String order;

    /**
     * 是否要查询总行数
     * 0:否，1:是
     *
     * @return
     */
    private int isCount = IS_COUNT;

    /**
     * 参数map
     *
     * @return
     */
    private Map<String, Object> paramMap = new HashMap<String, Object>();

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public static int getPageIndex() {
        return PAGE_INDEX;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page > 0) {
            this.page = page;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows > 0)
            this.rows = rows;
    }

    public int getIsCount() {
        return isCount;
    }

    public void setIsCount(int isCount) {
        if (isCount == 0) {
            this.isCount = 0;
        }
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

}
