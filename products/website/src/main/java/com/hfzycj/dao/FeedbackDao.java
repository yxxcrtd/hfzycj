package com.hfzycj.dao;

import com.hfzycj.domain.Feedback;

/**
 * Feedback DAO
 */
public interface FeedbackDao extends BaseDao<Feedback, Integer> {

    int updateStatus(String[] ids, int status);

    int updateApproveStatus(int id, int approveStatus);
}
