package com.hfzycj.service.impl;

import com.hfzycj.dao.RelationDao;
import com.hfzycj.domain.Relation;
import com.hfzycj.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lili on 2017/2/14.
 */
@Service
public class RelationServiceImpl extends BaseServiceImpl<Relation, Integer> implements RelationService{

    @Autowired
    protected RelationDao relationDao;
}
