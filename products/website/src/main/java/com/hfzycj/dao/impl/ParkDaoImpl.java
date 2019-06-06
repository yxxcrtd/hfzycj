package com.hfzycj.dao.impl;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.hfzycj.dao.ParkDao;
import com.hfzycj.domain.Park;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Park Dao Implementation
 */
@Repository
public class ParkDaoImpl extends BaseDaoImpl<Park, Integer, String, String> implements ParkDao {

    @Override
    public Long saveReturnId(Park park) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("t_park").usingGeneratedKeyColumns("park_id");
        Map<String, Object> map = new HashMap<>();
        map.put("park_name", park.getPark_name());
        map.put("park_code", park.getPark_code());
        map.put("park_total", park.getPark_total());
        map.put("park_surplus", park.getPark_surplus());
        map.put("park_coordinate_x", park.getPark_coordinate_x());
        map.put("park_coordinate_y", park.getPark_coordinate_y());
        map.put("park_type", park.getPark_type());
        map.put("park_status", park.getPark_status());
        map.put("park_source", park.getPark_source());
        map.put("park_address", park.getPark_address());
        map.put("park_charge_code", park.getPark_charge_code());
        map.put("park_describe", park.getPark_describe());
        map.put("park_operating_company", park.getPark_operating_company());
        map.put("park_create_time", park.getPark_create_time());
        map.put("park_tel", park.getPark_tel());
        map.put("park_contact_name", park.getPark_contact_name());
        map.put("park_update_time", park.getPark_update_time());
        map.put("park_logo", park.getPark_logo());
        return Long.valueOf(insertActor.executeAndReturnKey(map).intValue());
    }

    @Override
    public int updateStatus(String[] ids, int status) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE t_park SET park_status = ? WHERE park_id");
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
    public void updateSurplus(String code, int surplus, int total) {
        StringBuffer sql = new StringBuffer("update t_park set park_surplus=").append(surplus);
        if (total != 0) {
            sql.append(" , park_total=").append(total);
        }
        sql.append(" where park_code=").append("'").append(code).append("'");
        jdbcTemplate.update(sql.toString(), null, null);
    }


}
