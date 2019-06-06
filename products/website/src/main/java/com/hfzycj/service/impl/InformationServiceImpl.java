package com.hfzycj.service.impl;

import com.hfzycj.dao.AttachmentDao;
import com.hfzycj.dao.InformationDao;
import com.hfzycj.domain.Attachment;
import com.hfzycj.domain.Information;
import com.hfzycj.service.InformationService;
import com.hfzycj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Information Service Implementation
 */
@Service
public class InformationServiceImpl extends BaseServiceImpl<Information, Long> implements InformationService {

    public Long saveReturnId(Information information) throws Exception {
        return ((InformationDao) baseDao).saveReturnId(information);
    }

    @Autowired
    protected AttachmentDao attachmentDao;

    @Autowired
    protected InformationDao informationDao;

    @Override
    public void saveInfo(Information information, String uploadPath, MultipartHttpServletRequest request) throws Exception {

        if (0 == information.getInformation_id()) {
            long newestId = saveReturnId(information);
            List<Attachment> attachments = new ArrayList<>();
            List<MultipartFile> files = request.getFiles("file");
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    Attachment attachment = new Attachment();
                    attachment.setAttachment_obj_id(newestId);
                    attachment.setAttachment_name(file.getOriginalFilename());
                    attachment.setAttachment_url(FileUtil.uploadFile(file, uploadPath, false));
                    attachments.add(attachment);
                }
            }
            for (Attachment attachment : attachments) {
                if (null != attachment && 0 == attachment.getAttachment_id()) {
                    attachmentDao.save(attachment);
                }
            }
        } else {
            update(information);
        }
    }

    @Override
    public void updateStatus(String[] ids, int status) {
        ((InformationDao) baseDao).updateStatus(ids, status);
    }

    @Override
    public List<Map<String, Object>> getList (int type) {
        return informationDao.getList(type);
    }

    public String increase(long id, int opt) throws Exception {
        Information information = null;
        try {
            information = baseDao.findById(id);
            if (1 == opt) {
                information.setInformation_hit(information.getInformation_hit() + 1);
                baseDao.update(information);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(information.getInformation_hit());
    }

}
