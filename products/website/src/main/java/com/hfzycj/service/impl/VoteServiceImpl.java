package com.hfzycj.service.impl;

import com.hfzycj.dao.VoteDao;
import com.hfzycj.dao.ItemDao;
import com.hfzycj.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hfzycj.domain.Vote;
import com.hfzycj.service.VoteService;

import java.util.Date;
import java.util.List;

/**
 * Vote Service Implementation
 */
@Service
public class VoteServiceImpl extends BaseServiceImpl<Vote, Integer> implements VoteService {
    @Autowired
    protected VoteDao voteDao;

    @Autowired
    protected ItemDao itemDao;

    @Override
    public void updateStatus(String[] ids, int status) {
        ((VoteDao) baseDao).updateStatus(ids, status);
    }

    @Override
    public void saveVoteInfo(Vote vote, List<Item> itemList) throws Exception {
        if (0 == vote.getVote_id()) {
            vote.setVote_create_time(new Date());
            vote.setVote_user_counts(100);
            long newestId = voteDao.saveReturnId(vote);
            vote.setVote_id(new Long(newestId).intValue());

        } else {
            voteDao.update(vote);
        }

        //保存选项
        for (Item item : itemList) {
            if (0 == item.getItem_id()) {
                item.setItem_vote_id(vote.getVote_id());
                item.setItem_vote_user_counts(20);
                itemDao.save(item);
            }else{
                itemDao.update(item);
            }
        }
    }
}
