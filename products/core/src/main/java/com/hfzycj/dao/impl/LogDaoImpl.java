package com.hfzycj.dao.impl;

import com.hfzycj.dao.LogDao;
import com.hfzycj.domain.Log;
import org.springframework.stereotype.Repository;

/**
 * Log Dao Implementation
 */
@Repository
public class LogDaoImpl extends BaseDaoImpl<Log, Long, String, String> implements LogDao {

}
