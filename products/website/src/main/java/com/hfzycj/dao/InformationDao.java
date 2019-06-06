package com.hfzycj.dao;

import com.hfzycj.domain.Information;

import java.util.List;
import java.util.Map;

/**
 * Information DAO
 */
public interface InformationDao extends BaseDao<Information, Long> {

    Long saveReturnId(Information information);

    int updateStatus(String[] ids, int status);

    List<Map<String, Object>> getList(int type);

}
