package com.hfzycj.dao;

import com.hfzycj.domain.Vote;

/**
 * Vote DAO
 */
public interface VoteDao extends BaseDao<Vote, Integer> {
    Long saveReturnId(Vote vote);
    int updateStatus(String[] ids, int status);

}
