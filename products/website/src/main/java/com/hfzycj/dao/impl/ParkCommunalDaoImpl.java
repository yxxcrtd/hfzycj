package com.hfzycj.dao.impl;

import com.hfzycj.dao.ParkCommunalDao;
import com.hfzycj.domain.ParkCommunal;
import org.springframework.stereotype.Repository;

/**
 * Menu Dao Implementation
 */
@Repository
public class ParkCommunalDaoImpl extends BaseDaoImpl<ParkCommunal, Integer, String, String> implements ParkCommunalDao {
    @Override
    public void deleteByParkId(int parkid) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_parkcommunal WHERE parkcommunal_parkid=?");
        LOGGER.info(sql.toString());
        Object[] args = new Object[1];
        args[0] = parkid;
        jdbcTemplate.update(sql.toString(), args);
    }
}
