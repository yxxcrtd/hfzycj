package com.hfzycj.service;

import com.hfzycj.domain.User;

/**
 * Manager Service Interface
 */
public interface UserService extends BaseService<User, Integer> {

	User findByName(String name);

	void updateStatus(String[] ids, int status);
}
