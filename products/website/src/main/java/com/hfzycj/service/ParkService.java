package com.hfzycj.service;

import com.hfzycj.domain.Park;
import com.hfzycj.domain.ParkCache;

import java.util.List;

/**
 * Park Service Interface
 */
public interface ParkService extends BaseService<Park, Integer> {

    void saveParkInfo(Park park, List<Integer> communal_id) throws Exception;

    void updateStatus(String[] ids, int status);

    void updateSurplus(String code, int surplus, int total);

    List<Park> parkInit();

    List<ParkCache> parkCacheList(int parkfree, int parkShort, int parkOffline);

}
