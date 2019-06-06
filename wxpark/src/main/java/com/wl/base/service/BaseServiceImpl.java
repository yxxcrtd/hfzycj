package com.wl.base.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.base.dao.IBaseDAO;
import com.wl.base.exception.ServiceException;
import com.wl.base.model.BaseBean;
import com.wl.base.model.Page;

@Service
public class BaseServiceImpl implements IBaseService {

    @Autowired
    private IBaseDAO baseDAO;

    /**
     * 添加
     *
     * @param sqlId
     * @param paramObj
     * @return
     */
    public int add(String sqlId, Object paramObj) {
        if (sqlId == null || "".equals(sqlId)) {
            throw new IllegalArgumentException("传入的sqlId参数不能为空.");
        }
        return this.baseDAO.insert(sqlId, paramObj);
    }

    /**
     * 修改
     *
     * @param sqlId
     * @param paramObj
     * @return
     */
    public int modify(String sqlId, Object paramObj) {
        if (sqlId == null || "".equals(sqlId)) {
            throw new IllegalArgumentException("传入的sqlId参数不能为空.");
        }
        return this.baseDAO.update(sqlId, paramObj);
    }

    /**
     * 删除
     *
     * @param sqlId
     * @param paramObj
     * @return
     */
    public int remove(String sqlId, Object paramObj) {
        if (sqlId == null || "".equals(sqlId)) {
            throw new IllegalArgumentException("传入的sqlId参数不能为空.");
        }
        return this.baseDAO.delete(sqlId, paramObj);
    }

    /**
     * 返回一个对象
     *
     * @param sqlId
     * @param paramObj
     * @return
     */
    public Object query(String sqlId, Object paramObj) {
        if (sqlId == null || "".equals(sqlId)) {
            throw new IllegalArgumentException("传入的sqlId参数不能为空.");
        }
        return this.baseDAO.selectOne(sqlId, paramObj);
    }

    /**
     * 返回一个对象列表
     *
     * @param sqlId
     * @param paramObj
     * @return
     */
    public List<Object> queryForList(String sqlId, Object paramObj) {
        if (sqlId == null || "".equals(sqlId)) {
            throw new IllegalArgumentException("传入的sqlId参数不能为空.");
        }
        return this.baseDAO.selectList(sqlId, paramObj);
    }

    /**
     * 得到Page对象
     *
     * @param sqlId
     * @param paramObj         查询参数
     * @param pageIndex        翻页索引
     * @param pageSize         每页显示的行数
     * @param isWithTotalCount 是否查询总行数
     * @return page
     */
    public Page queryForPage(String sqlId, Object paramObj, int pageIndex, int pageSize, boolean isWithTotalCount) {
        return this.baseDAO.selectPageList(sqlId, paramObj, pageIndex, pageSize, isWithTotalCount);
    }

    /**
     * 得到Page对象
     *
     * @param sqlId
     * @param paramObj  查询参数
     * @param pageIndex 翻页索引
     * @param pageSize  每页显示的行数
     * @return page
     */
    public Page queryForPage(String sqlId, Object paramObj, int pageIndex, int pageSize) {
        return this.queryForPage(sqlId, paramObj, pageIndex, pageSize, true);
    }

    /**
     * 得到分页对象
     *
     * @param sqlId
     * @param bean  其他Bean继承该Bean进行分页
     * @return
     */
    public Page queryForPage(String sqlId, BaseBean bean) {
        if (bean == null) {
            bean = new BaseBean();
        }
        return this.queryForPage(sqlId, bean, bean.getPage(), bean.getRows(), bean.getIsCount() == 0 ? false : true);
    }

    /**
     * 把ids格式化成以逗号分隔的字符串,供sql的in查询
     *
     * @param ids
     * @return
     */
    public String format2InSQL(String ids) {
        if (ids == null || "".equals(ids)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        String[] strArr = ids.split(",");
        int len = strArr.length;
        for (int i = 0; i < len; i++) {
            if (i == len - 1) {
                sb.append("'" + strArr[i] + "'");
            } else {
                sb.append("'" + strArr[i] + "',");
            }
        }
        return sb.toString();
    }

    /**
     * 根据业务ID删除文件
     *
     * @param ref_id
     */
    public void delFileByRefId(String ref_id) {
        if (StringUtils.isBlank(ref_id)) {
            return;
        }
        try {
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("ref_id", ref_id);
            //删除源文件
            List<String> list = (List) this.baseDAO.selectList("com.wl.mdms.news.findFilePathList", paramMap);
            if (list != null && list.size() > 0) {
                for (String path : list) {
                    File file = new File(path);
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
            //删除数据库记录
            this.baseDAO.delete("com.wl.mdms.news.delFileInfo", paramMap);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * url验证
     *
     * @param str
     * @return
     */
    public String urlEnodeUTF8(String str) {
        String result = str;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
