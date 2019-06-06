package com.hfzycj.utils;

/**
 * Pager
 */
public class Pager {

    private long totalCount;

    private long pageSize = 20;

    private long pageNo = 1;

    private String pageUrl = "?p={p}&k={k}";

    public Pager() {
        pageNo = 1;
        pageSize = 15;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNo() {
        if (pageNo > getTotalPageCount()) {
            return getTotalPageCount();
        }
        return pageNo;
    }

    public long getTotalPageCount() {
        if (0 >= totalCount) {
            return 0;
        }
        if (0 >= pageSize) {
            return 0;
        }
        long i = totalCount / pageSize;

        if ( 0 != (totalCount % pageSize)) {
            i++;
        }
        return i;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public long getOffset() {
        return (pageNo - 1) * pageSize;
    }

}
