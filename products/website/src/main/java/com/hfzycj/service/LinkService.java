package com.hfzycj.service;

import com.hfzycj.domain.Link;

/**
 * Link Service Interface
 */
public interface LinkService extends BaseService<Link, Long> {

    void updateStatus(String[] ids, int status);

}
