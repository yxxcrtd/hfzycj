package com.hfzycj.dao.impl;

import com.hfzycj.dao.UserDao;
import com.hfzycj.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User Dao Implementation
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer, String, String> implements UserDao {

    public User findByName(String name) {
        String sql = "SELECT user_id, user_name, user_password FROM t_user WHERE user_name = ?";
        LOGGER.info(sql + " : " + name);
        List<User> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), new Object[] { name });
        return (null != list && 0 < list.size()) ? list.get(0) : null;
    }

    @Override
    public int updateStatus(String[] ids, int status) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE t_user SET user_status = ? WHERE user_id");
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
