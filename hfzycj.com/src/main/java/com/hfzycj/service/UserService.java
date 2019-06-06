package com.hfzycj.service;

import com.hfzycj.domain.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List<User> findAllCount();

    User save(User user);

}
