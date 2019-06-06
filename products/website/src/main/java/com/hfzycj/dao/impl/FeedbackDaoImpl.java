package com.hfzycj.dao.impl;

import org.springframework.stereotype.Repository;

import com.hfzycj.dao.FeedbackDao;
import com.hfzycj.domain.Feedback;

/**
 * Feedback Dao Implementation
 */
@Repository
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback, Integer, String, String> implements FeedbackDao {

    @Override
    public int updateStatus(String[] ids, int status) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE t_feedback SET feedback_status = ? WHERE feedback_id");
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

    /**
     * 修改反馈审核状态信息
     * @param id
     * @param approveStatus
     * @return
     */
    @Override
    public int updateApproveStatus(int id, int approveStatus) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE t_feedback SET feedback_approve_status = ? WHERE feedback_id = ?");
        LOGGER.info(sql.toString() + " : {}, {}", approveStatus, id);
        return jdbcTemplate.update(sql.toString(), new Object[] { approveStatus, id });
    }
}
