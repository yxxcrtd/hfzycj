package com.hfzycj.service.impl;

import com.hfzycj.dao.UserDao;
import com.hfzycj.domain.User;
import com.hfzycj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User Service Implementation
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Autowired
    protected UserDao userDao;

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public void updateStatus(String[] ids, int status) {
        userDao.updateStatus(ids, status);
    }

}
