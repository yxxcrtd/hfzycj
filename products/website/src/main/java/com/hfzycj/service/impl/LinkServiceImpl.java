package com.hfzycj.service.impl;

import com.hfzycj.dao.LinkDao;
import com.hfzycj.domain.Link;
import com.hfzycj.service.LinkService;
import org.springframework.stereotype.Service;

/**
 * Link Service Implementation
 */
@Service
public class LinkServiceImpl extends BaseServiceImpl<Link, Long> implements LinkService {

    @Override
    public void updateStatus(String[] ids, int status) {
        ((LinkDao) baseDao).updateStatus(ids, status);
    }

}
