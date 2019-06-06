package com.hfzycj.dao.impl;

import org.springframework.stereotype.Repository;

import com.hfzycj.dao.LinkDao;
import com.hfzycj.domain.Link;

/**
 * Link Dao Implementation
 */
@Repository
public class LinkDaoImpl extends BaseDaoImpl<Link, Long, String, String> implements LinkDao {

    @Override
    public int updateStatus(String[] ids, int status) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE t_link SET link_status = ? WHERE link_id");
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

}
