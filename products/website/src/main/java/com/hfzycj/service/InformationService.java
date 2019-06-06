package com.hfzycj.service;

import com.hfzycj.domain.Information;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * Information Service Interface
 */
public interface InformationService extends BaseService<Information, Long> {

    Long saveReturnId(Information information) throws Exception;

    void saveInfo(Information information, String uploadPath, MultipartHttpServletRequest request) throws Exception;

    void updateStatus(String[] ids, int status);

    List<Map<String, Object>> getList(int type);

    String increase(long id, int opt) throws Exception;

}
