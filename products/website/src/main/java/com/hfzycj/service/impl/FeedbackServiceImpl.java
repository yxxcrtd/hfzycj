package com.hfzycj.service.impl;

import com.hfzycj.dao.FeedbackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hfzycj.domain.Feedback;
import com.hfzycj.service.FeedbackService;

/**
 * Feedback Service Implementation
 */
@Service
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback, Integer> implements FeedbackService {

    @Autowired
    protected FeedbackDao feedbackDao;

    @Override
    public void updateStatus(String[] ids, int status) {
        feedbackDao.updateStatus(ids, status);
    }

    @Override
    public void updateApproveStatus(int id, int approveStatus) {
        feedbackDao.updateApproveStatus(id, approveStatus);
    }

}
