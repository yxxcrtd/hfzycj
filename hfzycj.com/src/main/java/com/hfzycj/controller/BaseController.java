package com.hfzycj.controller;

import com.hfzycj.domain.News;
import com.hfzycj.domain.User;
import com.hfzycj.service.NewsService;
import com.hfzycj.service.UserService;
import com.hfzycj.utils.FileUtil;
import com.hfzycj.utils.Pager;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseController {

    protected static Logger log = LoggerFactory.getLogger(BaseController.class);

    protected @Value("${upload.path}") String UPLOAD_PATH;

    @Autowired
    Configuration configuration;

    protected Pager pager;

    protected User user;

    protected News news;

    @Autowired
    protected UserService userService;

    @Autowired
    protected NewsService newsService;

    protected Map<String, String> getNewsTypeMap() {
        Map<String, String> newsTypeMap = new LinkedHashMap<>();
        newsTypeMap.put("0", "公司新闻");
        newsTypeMap.put("1", "行业动态");
        return newsTypeMap;
    }

    protected void generateNewsHTML(Map<String, Object> map, Long newsId, int type) throws Exception {
        log.info("资讯分类是：{}", type);
        FileUtil.generateHTML(configuration, map, "site/IndexNews.html", UPLOAD_PATH + "index_news.html");
        log.info("生成的资讯文件名：{}", "news" + String.valueOf(type) + ".html");
        FileUtil.generateHTML(configuration, map, "site/News.html", UPLOAD_PATH + "news" + String.valueOf(type) + ".html");
        log.info("生成的资讯详情文件：{}", "news_" + newsId + ".html");
        FileUtil.generateHTML(configuration, map, "site/NewsDetail.html", UPLOAD_PATH + "news_" + newsId + ".html");
    }

}
