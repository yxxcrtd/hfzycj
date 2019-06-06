package com.hfzycj.service.impl;
import com.hfzycj.dao.MenuDao;
import com.hfzycj.domain.Menu;
import com.hfzycj.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Menu Service Implementation
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, Integer> implements MenuService {
    @Autowired
    protected MenuDao menuDao;
    @Override
    public void updateStatus(String[] ids, int status) {
        menuDao.updateStatus(ids, status);
    }
}
