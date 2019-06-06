package com.hfzycj.service;

import com.hfzycj.domain.Feedback;

/**
 * Feedback Service Interface
 */
public interface FeedbackService extends BaseService<Feedback, Integer> {

    void updateStatus(String[] ids, int status);

    void updateApproveStatus(int id, int approveStatus);
}
