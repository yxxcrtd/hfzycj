package com.hfzycj.service.impl;

import com.hfzycj.dao.CommunalDao;
import com.hfzycj.dao.MenuDao;
import com.hfzycj.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hfzycj.domain.Communal;
import com.hfzycj.service.CommunalService;

import java.util.*;

/**
 * Communal Service Implementation
 */
@Service
public class CommunalServiceImpl extends BaseServiceImpl<Communal, Integer> implements CommunalService {
    @Autowired
    protected CommunalDao communalDao;
    @Autowired
    protected MenuDao menuDao;

    @Override
    public void updateStatus(String[] ids, int status) {
        communalDao.updateStatus(ids, status);
    }

    @Override
    public List<Map<String, Object>> getMcList() {
        List<Menu> list = menuDao.findAllList("menu_status = 0 and menu_parentid=200000", "menu_orderby", null);
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (Menu menu : list) {
            map = new LinkedHashMap<String, Object>();
            map.put("id", menu.getMenu_id());
            map.put("pid", 1);
            map.put("name", menu.getMenu_title());
            map.put("url", menu.getMenu_url());
            listMap.add(map);
            List<Communal> list2 = communalDao.findAllList("communal_status = 0 and communal_menuid=".concat(String.valueOf(menu.getMenu_id())), "communal_orderby", null);
            if (null != list2) {
                int i = 1;
                for (Communal communal : list2) {
                    map = new LinkedHashMap<String, Object>();
                    map.put("id", communal.getCommunal_id());
                    map.put("pid", 2);//级别
                    map.put("name", communal.getCommunal_name());
                    map.put("url", communal.getCommunal_address());
                    map.put("order", i);
                    if(i%2==0) {
                        map.put("isbr", 1);
                    }
                    else
                    {
                        map.put("isbr", 0);
                    }
                    map.put("count", list2.size());
                    i++;
                    listMap.add(map);
                }
            }
        }
        return listMap;
    }

    @Override
    public List<Communal> findCommunalList(String whereString,int limit) {
        return ((CommunalDao) baseDao).findCommunalList(whereString,limit);
    }
}