package com.hfzycj.controller;

import com.alibaba.fastjson.JSONObject;
import com.hfzycj.annotation.SystemLog;
import com.hfzycj.domain.Communal;
import com.hfzycj.domain.Feedback;
import com.hfzycj.domain.Information;
import com.hfzycj.domain.Park;
import com.hfzycj.domain.ParkCache;
import com.hfzycj.util.FileUtil;
import com.hfzycj.util.Pager;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.hfzycj.util.DateUtil.*;

/**
 * Menu Controller
 */
@RestController
@RequestMapping("site")
public class SiteController extends BaseController {

    /**
     * 首页标签列表接口
     *
     * @return
     */
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
//    @SystemLog(model = "菜单分类管理", description = "显示公共资源菜单及资源")
    public ModelAndView menucomclist(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Map<String, Object>> listMap = communalService.getMcList();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("listMap", listMap);
        mav.addObject("listMap", listMap);
        mav.setViewName("menu/MenuInList");
        return mav;
    }


    /**
     * 读取停车场缓存数据
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/parkcache", method = RequestMethod.GET)
//    @SystemLog(model = "菜单分类管理", description = "显示公共资源菜单及资源")
    public ModelAndView parkCache(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<ParkCache> listMap = parkService.parkCacheList(PARK_CACHE_PERCERT, 0, 0);
        mav.addObject("listMap", listMap);
        mav.setViewName("site/parkcache");
        return mav;
    }

    /**
     * 停车场缓存数接
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/parkcachedata", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String parkCacheData(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        List<ParkCache> listMap = parkService.parkCacheList(PARK_CACHE_PERCERT, 0, 0);
        LOGGER.info("首页展示的停车场数据：" + listMap.size());
        jsonObject.put("listMap", listMap);
        return jsonObject.toString();
    }

    /**
     * 停车场搜索（标签）
     *
     * @return
     */
//    @RequestMapping(value = "/parkcachedata", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    @RequestMapping(value = "/parksearch", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    public ModelAndView parkSearch(@RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "p", required = false) Integer p) {
        ModelAndView mav = new ModelAndView();
        StringBuffer sqlSbf = new StringBuffer(" park_status = 0 ");
        if (null != keyword && !"".equals(keyword)) {
            sqlSbf.append("and (");
            sqlSbf.append("  park_name like ").append("'%").append(keyword).append("%'");
            sqlSbf.append(" or ");
            sqlSbf.append(" park_id in (");
            sqlSbf.append(" select t_parkcommunal.parkcommunal_parkid from  t_parkcommunal LEFT JOIN t_communal on t_communal.communal_id=t_parkcommunal.parkcommunal_communalid where t_communal.communal_name ");
            sqlSbf.append("LIKE ").append("'%").append(keyword).append("%'").append(")");
            sqlSbf.append(")");
        }
        //停车场列表
        Pager pager = new Pager();
        if (null == p || p == 0) {
            p = 1;
        }
        pager.setPageSize(4);
        pager.setPageNo(p);
        int count = parkService.findAllCount(park, sqlSbf.toString());
        pager.setTotalCount(count);
        List<Park> parkList = parkService.findByPager(pager, sqlSbf.toString(), " park_source ");
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<>();
        List<Communal> communalLink = null;
        for (Park park : parkList) {
            map = new HashMap<>();
            map.put("id", park.getPark_id());
            map.put("name", park.getPark_name());
            map.put("address", park.getPark_address());
            map.put("surplus", park.getPark_surplus());
            map.put("total", park.getPark_total());
            communalLink = communalService.findCommunalList(" t_parkcommunal.parkcommunal_parkid =" + park.getPark_id(), 3);
            map.put("communalList" + park.getPark_id(), communalLink);
            map.put("communalListkey", "communalList" + park.getPark_id());
            listMap.add(map);
        }
        mav.addObject("parkList", listMap);
        mav.addObject("keyword", keyword);
        mav.addObject("p", p);

        mav.addObject("pager", pager);
        mav.addObject("count", count);
        mav.addObject("active", "park");

        Map<String, Object> mapSearchForm = new HashMap<String, Object>();
        mapSearchForm.put("keyword", keyword);
        mapSearchForm.put("p", p);
        mav.addObject("mapSearchForm", mapSearchForm);
        mav.setViewName("park/ParkSearch");
        return mav;
    }


    @ResponseBody
    @RequestMapping(value = "/parksearchtml", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    public ModelAndView parkSearchHtml(@RequestParam(value = "keyword", required = false) String keyword,
                                       @RequestParam(value = "p", required = false) Integer p) {
        ModelAndView mav = new ModelAndView();
        StringBuffer sqlSbf = new StringBuffer(" park_status = 0 ");
        if (null != keyword && !"".equals(keyword)) {
            sqlSbf.append("and (");
            sqlSbf.append("  park_name like ").append("'%").append(keyword).append("%'");
            sqlSbf.append(" or ");
            sqlSbf.append(" park_id in (");
            sqlSbf.append(" select t_parkcommunal.parkcommunal_parkid from  t_parkcommunal LEFT JOIN t_communal on t_communal.communal_id=t_parkcommunal.parkcommunal_communalid where t_communal.communal_name ");
            sqlSbf.append("LIKE ").append("'%").append(keyword).append("%'").append(")");
            sqlSbf.append(")");
        }
        //停车场列表
        Pager pager = new Pager();
        if (null == p || p == 0) {
            p = 1;
        }
        pager.setPageSize(4);
        pager.setPageNo(p);
        int count = parkService.findAllCount(park, sqlSbf.toString());
        pager.setTotalCount(count);
        List<Park> parkList = parkService.findByPager(pager, sqlSbf.toString(), " park_source ");
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<>();
        List<Communal> communalLink = null;
        for (Park park : parkList) {
            map = new HashMap<>();
            map.put("id", park.getPark_id());
            map.put("name", park.getPark_name());
            map.put("address", park.getPark_address());
            map.put("surplus", park.getPark_surplus());
            map.put("total", park.getPark_total());
            communalLink = communalService.findCommunalList(" t_parkcommunal.parkcommunal_parkid =" + park.getPark_id(), 2);
            map.put("communalList" + park.getPark_id(), communalLink);
            map.put("communalListkey", "communalList" + park.getPark_id());
            listMap.add(map);
        }
        mav.addObject("parkList", listMap);
        mav.addObject("keyword", keyword);
        mav.addObject("p", p);

        mav.addObject("pager", pager);
        mav.addObject("count", count);
        mav.addObject("active", "park");

        Map<String, Object> mapSearchForm = new HashMap<String, Object>();
        mapSearchForm.put("keyword", keyword);
        mapSearchForm.put("p", p);
        mav.addObject("mapSearchForm", mapSearchForm);
        mav.setViewName("park/ParkSearchHtml");
        return mav;
    }

    /**
     * 停车场列表数据
     *
     * @return
     */
//    @RequestMapping(value = "/park", method = RequestMethod.GET)
//    public ModelAndView parkList() {
//        ModelAndView mav = new ModelAndView();
//        List<Park> list = parkService.findAllList("park_status = 0", "", null);
//        List<Map<String, Object>> listMap = new ArrayList<>();
//        List<Map<String, Object>> listMap2 = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
//        for (Park park : list) {
//            map = new LinkedHashMap<>();
//            map.put("id", park.getPark_id());
//            map.put("name", park.getPark_name());
//            map.put("address", park.getPark_address());
//            listMap.add(map);
//            //根据停车场id获取所有的记录
//            List<ParkCommunal> listRelation = relationService.findAllList("parkcommunal_parkid=".concat(String.valueOf(park.getPark_id())), "", null);
//            StringBuffer ids = new StringBuffer("");
//            List<Communal> list2 = new ArrayList<>();
//            if (null != listRelation && 0 != listRelation.size()) {
//                int j = 1;
//                for (Relation relation : listRelation) {
//                    if (j <= listRelation.size() - 1) {
//                        ids.append(relation.getRelation_communal_id() + ",");
//                    } else {
//                        ids.append(relation.getRelation_communal_id());
//                    }
//                    j++;
//                }
//                list2 = communalService.findAllList("communal_status = 0 and communal_id in(".concat(ids.toString()) + ")", "", null);
//            }
//            if (null != list2) {
//                int i = 1;
//                for (Communal communal : list2) {
//                    map = new LinkedHashMap<String, Object>();
//                    map.put("name", communal.getCommunal_name());
//                    i++;
//                    listMap2.add(map);
//                }
//            }
//        }
//        mav.addObject("listMap", listMap);
//        mav.addObject("listMap2", listMap2);
//        mav.setViewName("park/ParkInList");
//        return mav;
//    }

    @RequestMapping(value = "/parkDetailPage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView parkDetailPage(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "id", required = true) int id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Pager pager = new Pager();
        pager.setPageNo(pageNo);
        pager.setPageSize(5);
        int count = feedbackService.findAllCount(feedback, "feedback_category=0");
        pager.setTotalCount(count);
        Park park = parkService.findById(id);
        List<Feedback> feedbackList = feedbackService.findByPager(pager, "feedback_category=0", "feedback_create_time DESC");
        List<Communal> communalLink = communalService.findCommunalList(" t_parkcommunal.parkcommunal_parkid =" + id, 2);
//        List<Feedback> feedbackList = feedbackService.findAllList("feedback_category=0", "feedback_create_time DESC", null);
        mav.addObject("pager", pager);
        mav.addObject("park", park);
        mav.addObject("feedbackList", feedbackList);
        mav.addObject("communalLink", communalLink);
        mav.setViewName("Park/ParkDetailPage");
        return mav;
    }

    /**
     * 更新停车场车位数接口
     *
     * @return
     * @throws Exception http://localhost:8080/site/park_surplus?code=1101060011&surplus=100&total=200
     */
    @ResponseBody
    @RequestMapping(value = "/park_surplus", method = {RequestMethod.GET, RequestMethod.POST})
    public String parkSurplus(
            @RequestParam(value = "surplus", required = true, defaultValue = "1") int surplus,
            @RequestParam(value = "total", required = true, defaultValue = "1") int total,
            @RequestParam(value = "code", required = false) String code
    ) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            if (null == code || "".equals(code)) {
                jsonObject = new JSONObject();
                jsonObject.put("cmd", 101);
                jsonObject.put("msg", "停车场编号为空，请确认！");
                return jsonObject.toString();
            }
            if (surplus < 0) {
                jsonObject = new JSONObject();

                jsonObject.put("cmd", 101);
                jsonObject.put("msg", "停车场剩余车位数不能为零");
                return jsonObject.toString();
            }
            if (total <= 0) {
                jsonObject = new JSONObject();
                jsonObject.put("cmd", 101);
                jsonObject.put("msg", "停车场车位总数不能为零");
                return jsonObject.toString();
            }
            if (surplus > total && total > 0) {
                jsonObject = new JSONObject();
                jsonObject.put("cmd", 101);
                jsonObject.put("msg", "剩余车位数，不能大于总车位数！");
                return jsonObject.toString();
            }
            parkService.updateSurplus(code, surplus, total);
            jsonObject = new JSONObject();
            jsonObject.put("cmd", 0);
            jsonObject.put("msg", "成功更新车位数！");
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject = new JSONObject();
            jsonObject.put("cmd", 500);
            jsonObject.put("msg", "服务异常");
            return jsonObject.toString();
        } finally {
            jsonObject.clear();
        }
    }

    @RequestMapping(value = "/information", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView infoList(@RequestParam(value = "type") int type) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<>();
        List<Information> inforList = informationService.findAllList("information_type = " + type, "information_order_by, information_create_time DESC", null);
        if (type == 1) {
            map.put("newsList", inforList);
            mav.addObject("newsList", inforList);
        } else if (type == 2) {
            map.put("noticeList", inforList);
            mav.addObject("noticeList", inforList);
        } else {
            map.put("regulations", inforList);
            mav.addObject("regulations", inforList);
        }
        mav.setViewName("information/InformationInList");
        return mav;
    }

    /**
     * Generate HTML File
     *
     * @param map
     * @param request
     * @throws IOException
     * @throws TemplateException
     */
    protected void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
        FileUtil.generateHTML("WEB-INF/ftl/menu", "MenuInList.ftl", "mclist.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
    }

    /**
     * 反馈提交保存接口
     *
     * @return
     */
    @RequestMapping(value = "feedbackSave", method = RequestMethod.POST)
    @SystemLog(model = "反馈", description = "反馈提交保存")
    public String feedbackSave(@RequestParam("category") int category, @RequestParam("feed") String feed, @RequestParam("name") String name, @RequestParam("phone") String phone) {
        JSONObject jsonObject = new JSONObject();
        try {
            Feedback feedback = new Feedback();
            feedback.setFeedback_name(name);
            feedback.setFeedback_tel(phone);
            feedback.setFeedback_content(feed);
            feedback.setFeedback_reply_content("");
            feedback.setFeedback_approve_status(0);
            feedback.setFeedback_category(category);
            feedback.setFeedback_create_time(new Date());
            feedbackService.save(feedback);
            jsonObject.put("result", "success");
            jsonObject.put("message", "反馈成功，请等待管理员审核！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 公共监督列表页
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/feedbackList", method = RequestMethod.GET)
    public ModelAndView feedbackPage(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Feedback> feedbackList = feedbackService.findAllList("feedback_status=0 and feedback_approve_status=1", "feedback_create_time DESC", null);
        mav.addObject("feedbackList", feedbackList);
        mav.setViewName("feedback/FeedbackPage");
        return mav;
    }

    /**
     * View
     *
     * @throws Exception
     */
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String view(@RequestParam(value = "id") long id, @RequestParam(value = "opt") int opt) throws Exception {
        return informationService.increase(id, opt);
    }

    /**
     * Pretty Time
     *
     * @throws Exception
     */
    @RequestMapping(value = "pretty", method = RequestMethod.GET)
    public String prettyTime(@RequestParam(value = "time") String time) throws Exception {
        return getPrettyTime(getDate(time, YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 资讯列表  http://localhost:8087/site/getInformationList?pageNo=1&pageSize=4
     *
     * @param pageNo   显示第几页
     * @param pageSize 每页显示的数目
     * @return
     */
    @RequestMapping(value = "/getInformationList", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView getInformationList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "2") int pageSize) {
        ModelAndView mav = new ModelAndView();
        Pager pager = new Pager();
        pager.setPageNo(pageNo);
        pager.setPageSize(pageSize);
        int count = informationService.findAllCount(information, "");
        pager.setTotalCount(count);
        List<Information> informationList = informationService.findByPager(pager, "", "information_order_by, information_create_time DESC");
        mav.addObject("informationList", informationList);
        mav.addObject("pager", pager);
        mav.setViewName("site/InformationDetailsH5");
        return mav;
    }

}
