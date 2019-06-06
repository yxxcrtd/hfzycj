package com.hfzycj.controller;

import com.hfzycj.annotation.SystemLog;
import com.hfzycj.domain.Link;
import com.hfzycj.util.FileUtil;
import com.hfzycj.util.Pager;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Link Controller
 */
@RestController
@RequestMapping("manage/link")
public class LinkController extends BaseController {

    /**
     * List
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @SystemLog(model = "友情链接", description = "显示列表")
    public ModelAndView list(@RequestParam(value = "p", defaultValue = "1") int p,
                             @RequestParam(value = "k", required = false) String k,
                             @RequestParam(value = "s1", required = false) String s1) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("k", k);
        String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
        k = null == k ? "1 = 1" : "link_name like " + like;
        mav.addObject("s1", s1);
        if (null != s1 && !"".equals(s1)) {
            k += " AND link_status = " + s1;
        }
        Pager pager = new Pager();
        pager.setPageNo(p);
        int count = linkService.findAllCount(link, k);
        pager.setTotalCount(count);
        mav.addObject("list", linkService.findByPager(pager, k, "link_status, link_order_by"));
        mav.addObject("pager", pager);
        mav.addObject("count", count);
        mav.addObject("status", null == s1 ? "" : s1); // 列表页面中首先显示全部
        mav.addObject("active", "link");
        mav.setViewName("link/LinkList");
        return mav;
    }

    /**
     * Edit
     *
     * @param linkId
     * @return
     */
    @RequestMapping("edit/{linkId:[\\d]+}")
    @SystemLog(model = "友情链接", description = "编辑")
    public ModelAndView edit(@PathVariable(value = "linkId") long linkId) {
        if (0 == linkId) {
            link = new Link();
            link.setLink_id(linkId);
        } else {
            link = linkService.findById(linkId);
        }
        return new ModelAndView("link/LinkEdit", "link", link);
    }

    /**
     * Save
     *
     * @param link
     * @param redirectAttributes
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @SystemLog(model = "友情链接", description = "保存")
    public ModelAndView save(@ModelAttribute("link") @Valid Link link, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (result.hasErrors()) {
            return new ModelAndView("link/LinkEdit", "link", link);
        }

        if (0 == link.getLink_id()) {
            linkService.save(link);
            redirectAttributes.addFlashAttribute("tips", "保存成功！");
        } else {
            linkService.update(link);
            redirectAttributes.addFlashAttribute("tips", "修改成功！");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("list", linkService.findAllList("", "link_order_by", null));
        map.put("request", request);
        try {
            generateHTML(map, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/manage/link");
    }

    /**
     * 更改状态
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @SystemLog(model = "友情链接", description = "更改状态")
    public String updateStatus(HttpServletRequest request, @RequestParam("idList[]") String[] ids, @RequestParam("status") int status) throws Exception {
        if (null == ids || 0 == ids.length || StringUtils.isEmpty(ids[0])) {
            throw new Exception("更改友情链接状态的参数不对！");
        } else {
            linkService.updateStatus(ids, status);
            Map<String, Object> map = new HashMap<>();
            map.put("list", linkService.findAllList("link_status = 0", "link_order_by", null));
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
     * @throws IOException
     * @throws TemplateException
     */
    protected void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
        FileUtil.generateHTML("WEB-INF/ftl/site", "Link.ftl", "link.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
    }

}
