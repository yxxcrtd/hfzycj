package com.hfzycj.dao.impl;

import org.springframework.stereotype.Repository;

import com.hfzycj.dao.AttachmentDao;
import com.hfzycj.domain.Attachment;

/**
 * Attachment Dao Implementation
 */
@Repository
public class AttachmentDaoImpl extends BaseDaoImpl<Attachment, Long, String, String> implements AttachmentDao {

}
