package com.hfzycj.dao.impl;

import com.hfzycj.dao.MenuDao;
import com.hfzycj.domain.Menu;
import org.springframework.stereotype.Repository;

/**
 * Menu Dao Implementation
 */
@Repository
public class MenuDaoImpl extends BaseDaoImpl<Menu, Integer, String, String> implements MenuDao {
    @Override
    public long updateStatus(int menuId, int status) {
        StringBuffer sql = new StringBuffer("update t_menu set menu_status=").append(status).append(" where menu_id=").append(menuId);
        return (long) jdbcTemplate.update(sql.toString(), null, null);
    }

    @Override
    public int updateStatus(String[] ids, int status) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE t_menu SET menu_status = ? WHERE menu_id");
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
