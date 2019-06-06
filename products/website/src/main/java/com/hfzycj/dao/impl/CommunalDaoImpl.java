package com.hfzycj.dao.impl;

import org.springframework.stereotype.Repository;

import com.hfzycj.dao.CommunalDao;
import com.hfzycj.domain.Communal;

import java.util.List;

/**
 * Communal Dao Implementation
 */
@Repository
public class CommunalDaoImpl extends BaseDaoImpl<Communal, Integer, String, String> implements CommunalDao {

    @Override
    public int updateStatus(String[] ids, int status) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE t_communal SET communal_status = ? WHERE communal_id");
        if (1 == ids.length) {
            sql.append(" = ? ");
        } else {
            sql.append(" in (");
            for (int i = 0; i < ids.length; i++) {
                sql.append("?,");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }
        LOGGER.info(sql.toString());

        Object[] args = new Object[ids.length + 1];
        int[] argTypes = new int[args.length];
        args[0] = status;
        argTypes[0] = 5;
        for (int i = 0; i < ids.length; i++) {
            args[i + 1] = Integer.parseInt(ids[i]);
            argTypes[i + 1] = 5;
        }
        return jdbcTemplate.update(sql.toString(), args);
    }

    @Override
    public List<Communal> findCommunalList(String whereString,int limit) {
//        whereString = " t_parkcommunal.parkcommunal_parkid = 4 ";
        StringBuffer sqlsbf = new StringBuffer("select t_communal.communal_id,t_communal.communal_name from  t_communal LEFT JOIN t_parkcommunal on t_communal.communal_id=t_parkcommunal.parkcommunal_communalid ");
        if (null != whereString && !"".equals(whereString)) {
            sqlsbf.append(" where ").append(whereString);
        }
        sqlsbf.append(" ORDER BY t_communal.communal_orderby ");
        sqlsbf.append(" limit   "+limit);
        LOGGER.info(sqlsbf.toString());
        return jdbcTemplate.query(sqlsbf.toString(), new RowMappers.communalCommunalMapper());
    }

}
