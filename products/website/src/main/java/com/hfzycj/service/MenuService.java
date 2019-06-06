package com.hfzycj.service;


import com.hfzycj.domain.Menu;

/**
 * Menu Service Interface
 */
public interface MenuService extends BaseService<Menu, Integer> {
    void updateStatus(String[] ids, int status);



}
