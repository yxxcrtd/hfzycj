package com.hfzycj.dao;

import com.hfzycj.domain.ParkCommunal;

/**
 * Created by Administrator on 2017/2/20.
 */
public interface ParkCommunalDao extends BaseDao<ParkCommunal, Integer> {
    void deleteByParkId(int parkid);
}
