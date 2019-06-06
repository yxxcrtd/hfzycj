package com.hfzycj.dao;


import com.hfzycj.domain.Menu;

/**
 * Menu DAO
 */
public interface MenuDao extends BaseDao<Menu, Integer> {
    /**
     * 改变显示
     * @param menuId
     * @param status
     * @return
     */
    long updateStatus(int menuId,int status);
    int updateStatus(String[] ids, int status);

}
