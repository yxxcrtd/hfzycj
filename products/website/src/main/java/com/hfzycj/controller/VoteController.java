package com.hfzycj.controller;

import com.hfzycj.annotation.SystemLog;
import com.hfzycj.domain.Item;
import com.hfzycj.domain.Vote;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Vote Controller
 */
@RestController
@RequestMapping("manage/vote")
public class VoteController extends BaseController {

    /**
     * List
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @SystemLog(model = "投票", description = "显示列表")
    public ModelAndView list(@RequestParam(value = "p", defaultValue = "1") int p,
                             @RequestParam(value = "k", required = false) String k,
                             @RequestParam(value = "s1", required = false) String s1,
                             @RequestParam(value = "s2", required = false) String s2) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("k", k);
        String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
        k = null == k ? "1 = 1" : "vote_title like " + like;
        mav.addObject("s1", s1);
        if (null != s1 && !"".equals(s1)) {
            k += " AND vote_status = " + s1;
        }
        mav.addObject("s2", s2);
        if (null != s2 && !"".equals(s2)) {
            k += " AND vote_type = " + s2;
        }
        Pager pager = new Pager();
        pager.setPageNo(p);
        int count = voteService.findAllCount(vote, k);
        pager.setTotalCount(count);
        mav.addObject("list", voteService.findByPager(pager, k, "vote_create_time DESC"));
        mav.addObject("pager", pager);
        mav.addObject("count", count);
        mav.addObject("status", null == s1 ? "" : s1); // 列表页面中首先显示全部
        mav.addObject("active", "vote");
        mav.setViewName("vote/VoteList");
        return mav;
    }

    /**
     * Edit
     *
     * @param voteId
     * @return
     */
    @RequestMapping("edit/{voteId:[\\d]+}")
    @SystemLog(model = "投票", description = "编辑")
    public ModelAndView edit(@PathVariable(value = "voteId") int voteId) {
        ModelAndView mav = new ModelAndView();
        List<Item> itemList = null;
        if (0 == voteId) {
            vote = new Vote();
            vote.setVote_id(voteId);
            vote.setVote_create_time(new Date());
        } else {
            vote = voteService.findById(voteId);
            itemList = itemService.findAllList("item_vote_id = " + String.valueOf(voteId), "", null);
        }
        mav.addObject("vote", vote);
        mav.addObject("itemList", itemList);
        mav.addObject("active", "vote");
        mav.setViewName("vote/VoteEdit");
        return mav;
    }

    /**
     * Save
     *
     * @param vote
     * @return
     * @throws TemplateException
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @SystemLog(model = "投票", description = "保存")
    public String save(@ModelAttribute("vote") @Valid Vote vote, HttpServletRequest request,
                       @RequestParam(value = "content") ArrayList<String> itemNames,
                       @RequestParam(value = "contentEdit") ArrayList<String> itemNamesAndIds) throws Exception {
        List<Item> itemList = new ArrayList<>();
        List<Item> itemEditList = new ArrayList<>();
        Item item = null;
        int indexNum = 0;

         /*修改选项内容获取的值，将选项内容和id一一存入选项对象中*/
        if (0 != itemNamesAndIds.size()) {

            for (String itemNameAndId : itemNamesAndIds) {
                item = new Item();
                if (!"".equals(itemNameAndId.split("_")[1])) {
                    item = itemService.findById(Integer.valueOf(itemNameAndId.split("_")[1]));
                }
                item.setItem_name(itemNameAndId.split("_")[0]);
                itemEditList.add(item);
            }

            voteService.saveVoteInfo(vote, itemEditList);
        }

        if (0 == vote.getVote_id() || 0 != itemNames.size()) {

            /*某个投票下已有选项的个数*/
            if (0 != vote.getVote_id()) {
                indexNum = itemService.findAllCount(item, "item_vote_id = " + vote.getVote_id());
            }

            /*添加选项内容获取的值，将选项内容一一存入选项对象中*/
            if (0 != itemNames.size()) {
                for (String itemName : itemNames) {
                    item = new Item();
                    item.setItem_index(indexNum);
                    item.setItem_name(itemName);
                    itemList.add(item);
                    indexNum++;
                }
            }
            voteService.saveVoteInfo(vote, itemList);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("voteList", voteService.findAllList("", "", null));
        map.put("request", request);
        try {
            generateHTML(map, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "saveSuccess";
    }


    /**
     * 更改状态
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @SystemLog(model = "投票管理", description = "更改状态")
    public String updateStatus(HttpServletRequest request, @RequestParam("idList[]") String[] ids, @RequestParam("status") int status) throws Exception {
        if (null == ids || 0 == ids.length || StringUtils.isEmpty(ids[0])) {
            throw new Exception("更改投票管理状态的参数不对！");
        } else {
            voteService.updateStatus(ids, status);
            Map<String, Object> map = new HashMap<>();
            map.put("list", voteService.findAllList("vote_status = 0", "", null));
            map.put("request", request);
            String s = 0 == status ? "显示" : "隐藏";
            generateHTML(map, request);
            return s;
        }
    }

    /**
     * Generate HTML File
     *
     * @param map
     * @param request
     * @throws TemplateException
     */
    protected void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
        FileUtil.generateHTML("WEB-INF/ftl/site", "Vote.ftl", "vote.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
    }

}
