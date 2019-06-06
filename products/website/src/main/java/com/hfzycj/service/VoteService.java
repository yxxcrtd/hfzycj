package com.hfzycj.service;

import com.hfzycj.domain.Item;
import com.hfzycj.domain.Vote;

import java.util.List;

/**
 * Vote Service Interface
 */
public interface VoteService extends BaseService<Vote, Integer> {

    void updateStatus(String[] ids, int status);

    void saveVoteInfo(Vote vote, List<Item> itemList) throws Exception;

}
