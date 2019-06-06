package com.hfzycj.service;

import com.hfzycj.domain.Communal;

import java.util.List;
import java.util.Map;

/**
 * Communal Service Interface
 */
public interface CommunalService extends BaseService<Communal, Integer> {
    void updateStatus(String[] ids, int status);

    List<Map<String, Object>> getMcList();

    List<Communal> findCommunalList(String whereString,int limit);

}
