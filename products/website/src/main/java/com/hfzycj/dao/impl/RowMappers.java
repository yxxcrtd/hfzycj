package com.hfzycj.dao.impl;

import com.hfzycj.domain.Communal;
import com.hfzycj.domain.ParkCommunal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMappers {

    public static final class communalCommunalMapper implements RowMapper<Communal> {
        public Communal mapRow(ResultSet rs, int rowNum) throws SQLException {
            Communal communal = new Communal();
            communal.setCommunal_id(rs.getInt("communal_id"));
            communal.setCommunal_name(rs.getString("communal_name"));

            return communal;
        }
    }

}
