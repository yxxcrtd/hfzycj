package com.hfzycj.dao;

import com.hfzycj.domain.Communal;

import java.util.List;

/**
 * Communal DAO
 */
public interface CommunalDao extends BaseDao<Communal, Integer> {
    int updateStatus(String[] ids, int status);

    List<Communal> findCommunalList(String whereString,int limit);
}
