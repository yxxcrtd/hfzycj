package com.hfzycj.controller;

import com.hfzycj.domain.Attachment;
import com.hfzycj.domain.Category;
import com.hfzycj.domain.Communal;
import com.hfzycj.domain.Copyright;
import com.hfzycj.domain.Dictionary;
import com.hfzycj.domain.Feedback;
import com.hfzycj.domain.Information;
import com.hfzycj.domain.Item;
import com.hfzycj.domain.Link;
import com.hfzycj.domain.Log;
import com.hfzycj.domain.Logo;
import com.hfzycj.domain.Menu;
import com.hfzycj.domain.Park;
import com.hfzycj.domain.Relation;
import com.hfzycj.domain.SEO;
import com.hfzycj.domain.User;
import com.hfzycj.domain.Vote;
import com.hfzycj.service.AttachmentService;
import com.hfzycj.service.CategoryService;
import com.hfzycj.service.CommunalService;
import com.hfzycj.service.CopyrightService;
import com.hfzycj.service.DictionaryService;
import com.hfzycj.service.FeedbackService;
import com.hfzycj.service.InformationService;
import com.hfzycj.service.ItemService;
import com.hfzycj.service.LinkService;
import com.hfzycj.service.LogService;
import com.hfzycj.service.LogoService;
import com.hfzycj.service.MenuService;
import com.hfzycj.service.ParkCommunalService;
import com.hfzycj.service.ParkService;
import com.hfzycj.service.RelationService;
import com.hfzycj.service.SEOService;
import com.hfzycj.service.UserService;
import com.hfzycj.service.VoteService;
import com.hfzycj.util.Pager;
import com.hfzycj.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.hfzycj.util.FastjsonUtil.parseMap;

/**
 * Base Controller
 */
public class BaseController {

    /**
     * Log
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Value("${static.path}")
    public String STATIC_PATH;

    @Value("${upload.path}")
    public String UPLOAD_PATH;

    @Value("${code.path.java}")
    public String CODE_PATH_JAVA;

    @Value("${code.path.ftl}")
    public String CODE_PATH_FTL;

    @Value("${code.package.name}")
    public String PACKAGE_NAME;

    @Value("${session.expiration}")
    public int SESSION_EXPIRATION;

    @Value("${park.status.percert}")
    public int PARK_CACHE_PERCERT;

    private @Value("${json.information.type}") String JSON_INFORMATION_TYPE;
    private @Value("${json.information.rule}") String JSON_INFORMATION_RULE;
    protected @Value("${max.information.count}") String MAX_INFORMATION_COUNT;

    @Autowired
    protected RedisUtil redisUtil;

    /**
     * Pager Object
     */
    public Pager pager;

    /**
     * SEO Object
     */
    protected SEO seo;

    /**
     * Link Object
     */
    protected Link link;

    /**
     * Feedback Object
     */
    protected Feedback feedback;

    /**
     * Feedback Service
     */
    @Autowired
    protected FeedbackService feedbackService;

    /**
     * Category Object
     */
    protected Category category;

    /**
     * Information Object
     */
    protected Information information;

    /**
     * SEO Service
     */
    @Autowired
    protected SEOService seoService;

    /**
     * Link Service
     */
    @Autowired
    protected LinkService linkService;

    /**
     * Category Service
     */
    @Autowired
    protected CategoryService categoryService;

    /**
     * Information Service
     */
    @Autowired
    protected InformationService informationService;


    /**
     * Menu Object
     */
    protected Menu menu;

    /**
     * Menu Service
     */
    @Autowired
    protected MenuService menuService;

    /**
     * Menu Object
     */
    protected Attachment attachment;

    /**
     * Menu Service
     */
    @Autowired
    protected AttachmentService attachmentService;

    /**
     * Logo Object
     */
    protected Logo logo;

    /**
     * Menu Service
     */
    @Autowired
    protected LogoService logoService;

    /**
     * Dictionary Object
     */
    protected Dictionary dictionary;

    /**
     * Dictionary Service
     */
    @Autowired
    protected DictionaryService dictionaryService;

    /**
     * User Object
     */
    protected User user;

    /**
     * UserService
     */
    @Autowired
    protected UserService userService;

    /**
     * Communal Object
     */
    protected Communal communal;

    /**
     * Communal Service
     */
    @Autowired
    protected CommunalService communalService;

    /**
     * Copyright Object
     **/
    protected Copyright copyright;
    /**
     * Copyright Service
     **/
    @Autowired
    protected CopyrightService copyrightService;

    /**
     * Park Object
     */
    protected Park park;

    /**
     * Park Service
     */
    @Autowired
    protected ParkService parkService;

    /**
     * Relation Object
     */
    protected Relation relation;

    /**
     * Relation Service
     */
    @Autowired
    protected RelationService relationService;
    @Autowired
    protected ParkCommunalService parkCommunalService;

    /**
     * Log Object
     */
    protected Log log;

    /**
     * SEO Service
     */
    @Autowired
    protected LogService logService;

    /**
     * Vote Object
     */
    protected Vote vote;

    /**
     * Vote Service
     */
    @Autowired
    protected VoteService voteService;

    /**
     * Item Object
     */
    protected Item item;

    /**
     * Item Service
     */
    @Autowired
    protected ItemService itemService;

    /**
     * Return ModelAndView
     */
    protected ModelAndView mav(Object o, String s, String v) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(s, o);
        mav.addObject("active", s);
        mav.setViewName(v);
        return mav;
    }

    protected Map<String, String> getStatusMap() {
        Map<String, String> locationMap = new LinkedHashMap<>();
        locationMap.put("0", "显示");
        locationMap.put("1", "隐藏");
        return locationMap;
    }

    protected Map<String, String> getInformationTypeMap() {
        return (HashMap<String, String>) parseMap(JSON_INFORMATION_TYPE);
    }

    protected Map<String, String> getInformationRuleMap() {
        return (HashMap<String, String>) parseMap(JSON_INFORMATION_RULE);
    }

}
