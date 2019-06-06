package com.hfzycj.dao;

import com.hfzycj.domain.Park;

/**
 * Park DAO
 */
public interface ParkDao extends BaseDao<Park, Integer> {
    Long saveReturnId(Park park);

    int updateStatus(String[] ids, int status);

    void updateSurplus(String code, int surplus, int total);
}
