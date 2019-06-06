package com.hfzycj.dao;

import com.hfzycj.domain.User;

/**
 * User DAO
 */
public interface UserDao extends BaseDao<User, Integer> {

    User findByName(String name);

    int updateStatus(String[] ids, int status);
}
