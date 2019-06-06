package com.hfzycj.dao;

import com.hfzycj.domain.Link;

/**
 * Link DAO
 */
public interface LinkDao extends BaseDao<Link, Long> {

    int updateStatus(String[] ids, int status);

}
