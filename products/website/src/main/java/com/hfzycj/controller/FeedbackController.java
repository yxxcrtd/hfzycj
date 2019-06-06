package com.hfzycj.controller;

import com.hfzycj.domain.Feedback;
import com.hfzycj.util.FileUtil;
import com.hfzycj.util.Pager;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Feedback Controller
 */
@RestController
@RequestMapping("manage/feedback")
public class FeedbackController extends BaseController {

    /**
     * List
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "p", defaultValue = "1") int p,
                             @RequestParam(value = "k", required = false) String k,
                             @RequestParam(value = "s1", required = false) String s1,
                             @RequestParam(value = "s2", required = false) String s2) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("k", k);
        String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
        k = null == k ? "" : "feedback_content like " + like;
        mav.addObject("s1", s1);
        if (null != s1 && !"".equals(s1)) {
            k += " AND feedback_status = " + s1;
        }
        mav.addObject("s2", s2);
        if (null != s2 && !"".equals(s2)) {
            k += " AND feedback_approve_status = " + s2;
        }
        Pager pager = new Pager();
        pager.setPageNo(p);
        int count = feedbackService.findAllCount(feedback, k);
        pager.setTotalCount(count);
        mav.addObject("list", feedbackService.findByPager(pager, k, "feedback_approve_status, feedback_create_time DESC"));
        mav.addObject("pager", pager);
        mav.addObject("count", count);
        mav.addObject("status", null == s1 ? "" : s1); // 列表页面中首先显示全部
        mav.addObject("approveStatus", null == s2 ? "" : s2); // 列表页面中首先显示全部
        mav.addObject("active", "feedback");
        mav.setViewName("feedback/FeedbackList");
        return mav;
    }

    /**
     * Edit
     *
     * @param feedbackId
     * @return
     */
    @RequestMapping("edit/{feedbackId:[\\d]+}")
    public ModelAndView edit(@PathVariable(value = "feedbackId") int feedbackId) {
        if (0 == feedbackId) {
            feedback = new Feedback();
            feedback.setFeedback_id(feedbackId);
        } else {
            feedback = feedbackService.findById(feedbackId);
        }
        return new ModelAndView("feedback/FeedbackEdit", "feedback", feedback);
    }

    /**
     * 更改状态
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    public String updateStatus(HttpServletRequest request, @RequestParam("idList[]") String[] ids, @RequestParam("status") int status) throws Exception {
        if (null == ids || 0 == ids.length || StringUtils.isEmpty(ids[0])) {
            throw new Exception("更改反馈状态的参数不对！");
        } else {
            feedbackService.updateStatus(ids, status);
            Map<String, Object> map = new HashMap<>();
            map.put("list", feedbackService.findAllList("feedback_status = 0", "", null));
            map.put("request", request);
            String s = 0 == status ? "显示" : "隐藏";
            generateHTML(map, request);
            return s;
        }
    }

    /**
     * 更改审核状态
     *
     * @param request
     * @param id
     * @param approveStatus
     * @throws Exception
     */
    @RequestMapping(value = "updateApproveStatus", method = RequestMethod.POST)
    public String updateApproveStatus(HttpServletRequest request, @RequestParam("id") int id, @RequestParam("approveStatus") int approveStatus) throws Exception {
        if (0 == id) {
            throw new Exception("提交反馈审核状态的参数不对！");
        } else {
            feedbackService.updateApproveStatus(id, approveStatus);
            Map<String, Object> map = new HashMap<>();
            map.put("list", feedbackService.findAllList("feedback_approve_status = 1", "feedback_create_time DESC", null));
            map.put("request", request);
            map.put("active", "feedback");
            String s = 1 == approveStatus ? "审核通过" : "待审核";
            generateHTML(map, request);
            return s;
        }
    }

    /**
     * 回复反馈信息
     *
     * @param feedback
     * @param id
     * @param replyContent
     * @return
     */
    @RequestMapping(value = "saveRelayContent", method = RequestMethod.POST)
    public String saveRelayContent(@ModelAttribute("feedback") @Valid Feedback feedback, @RequestParam(value = "id", required = false) int id, @RequestParam("replyContent") String replyContent, HttpServletRequest request) throws Exception {
        String s = "";
        if (0 != id) {
            feedback = feedbackService.findById(id);
            feedback.setFeedback_reply_content(replyContent);
            feedbackService.update(feedback);
            s = "回执成功";
        } else {
            s = "回执失败";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("feedbackList", feedbackService.findAllList("", "", null));
        map.put("active", "feedback");
        map.put("request", request);
        generateHTML(map, request);
        return s;
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
        FileUtil.generateHTML("WEB-INF/ftl/site", "Feedback.ftl", "feedback.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
    }

}