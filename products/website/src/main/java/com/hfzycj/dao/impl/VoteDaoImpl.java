package com.hfzycj.dao.impl;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.hfzycj.dao.VoteDao;
import com.hfzycj.domain.Vote;

import java.util.HashMap;
import java.util.Map;

/**
 * Vote Dao Implementation
 */
@Repository
public class VoteDaoImpl extends BaseDaoImpl<Vote, Integer, String, String> implements VoteDao {

    @Override
    public int updateStatus(String[] ids, int status) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE t_vote SET vote_status = ? WHERE vote_id");
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
    public Long saveReturnId(Vote vote) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("t_vote").usingGeneratedKeyColumns("vote_id");
        Map<String, Object> map = new HashMap<>();
        map.put("vote_title", vote.getVote_title());
        map.put("vote_description", vote.getVote_description());
        map.put("vote_type", vote.getVote_type());
        map.put("vote_status", vote.getVote_status());
        map.put("vote_user_counts", vote.getVote_user_counts());
        map.put("vote_create_time", vote.getVote_create_time());
        map.put("vote_end_time", vote.getVote_end_time());
        return Long.valueOf(insertActor.executeAndReturnKey(map).intValue());
    }

}
