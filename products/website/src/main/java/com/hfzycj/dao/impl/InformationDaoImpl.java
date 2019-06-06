package com.hfzycj.dao.impl;

import com.hfzycj.dao.InformationDao;
import com.hfzycj.domain.Information;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Information Dao Implementation
 */
@Repository
public class InformationDaoImpl extends BaseDaoImpl<Information, Long, String, String> implements InformationDao {

    public Long saveReturnId(Information information) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("t_information").usingGeneratedKeyColumns("information_id");
        Map<String, Object> map = new HashMap<>();
        map.put("information_type", information.getInformation_type());
        map.put("information_rule", information.getInformation_rule());
        map.put("information_title", information.getInformation_title());
        map.put("information_summary", information.getInformation_summary());
        map.put("information_create_time", new Date());
        map.put("information_content", information.getInformation_content());
        map.put("information_order_by", information.getInformation_order_by());
        map.put("information_status", information.getInformation_status());
        return Long.valueOf(insertActor.executeAndReturnKey(map).intValue());
    }

    public int updateStatus (String[] ids, int status) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE t_information SET information_status = ? WHERE information_id");
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
    public List<Map<String, Object>> getList (int type) {
        return null;
    }

}
