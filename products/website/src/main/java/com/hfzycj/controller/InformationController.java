package com.hfzycj.controller;

import com.hfzycj.annotation.SystemLog;
import com.hfzycj.domain.Attachment;
import com.hfzycj.domain.Information;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Information Controller
 */
@RestController
@RequestMapping("manage/information")
public class InformationController extends BaseController {

    /**
     * List
     * @param p
     * @param k
     * @param s1
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @SystemLog(model = "资讯", description = "列表")
    public ModelAndView list(@RequestParam(value = "p", defaultValue = "1") int p,
                             @RequestParam(value = "k", required = false) String k,
                             @RequestParam(value = "s1", required = false) String s1,
                             @RequestParam(value = "s2", required = false) String s2) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("k", k);
        String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
        k = null == k ? "" : "information_title like " + like;
        mav.addObject("s1", s1);
        if (null != s1 && !"".equals(s1)) {
            k += " AND information_type = " + "'" + s1 + "'";
        }
        mav.addObject("s2", s2);
        if (null != s2 && !"".equals(s2)) {
            k += " AND information_status = " + s2;
        }
        Pager pager = new Pager();
        pager.setPageNo(p);
        int count = informationService.findAllCount(information, k);
        pager.setTotalCount(count);
        mav.addObject("list", informationService.findByPager(pager, k, "information_order_by, information_create_time DESC"));
        mav.addObject("pager", pager);
        mav.addObject("count", count);
        mav.addObject("type", null == s1 ? "" : s1); // 列表页面中首先显示全部
        mav.addObject("status", null == s2 ? "" : s2); // 列表页面中首先显示全部
        mav.addObject("active", "information");
        mav.setViewName("information/InformationList");
        mav.addObject("informationTypeMap", getInformationTypeMap());
        return mav;
    }

    /**
     * Edit
     *
     * @param informationId
     * @return
     */
    @RequestMapping("edit/{informationId:[\\d]+}")
    @SystemLog(model = "资讯", description = "编辑")
    public ModelAndView edit(@PathVariable(value = "informationId") int informationId, @RequestParam(value = "t", required = false) String t) {
        ModelAndView mav = new ModelAndView();
        List<Attachment> attachmentList = null;
        if (0 == informationId) {
            information = new Information();
            information.setInformation_id(informationId);
            information.setInformation_rule(0);
            if (null != t) {
                information.setInformation_type(t);
            }
        } else {
            information = informationService.findById(Long.valueOf(informationId));
            attachmentList = attachmentService.findAllList("attachment_obj_id = " + String.valueOf(informationId), null, 1);
        }
        mav.addObject("informationTypeMap", getInformationTypeMap());
        mav.addObject("informationRuleMap", getInformationRuleMap());
        mav.addObject("information", information);
        mav.addObject("attachmentList", attachmentList);
        mav.addObject("active", "information");
        mav.setViewName("information/InformationEdit");
        return mav;
    }

    /**
     * Save
     *
     * @param information
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @SystemLog(model = "资讯", description = "保存")
    public ModelAndView save(@ModelAttribute("information") @Valid Information information, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("informationTypeMap", getInformationTypeMap());
            mav.addObject("informationRuleMap", getInformationRuleMap());
            mav.addObject("information", information);
            mav.addObject("active", "information");
            mav.setViewName("information/InformationEdit");
            return mav;
        }

        List<MultipartFile> files = request.getFiles("file");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                information.setInformation_attachment_name(file.getOriginalFilename());
                information.setInformation_attachment_url(FileUtil.uploadFile(file, UPLOAD_PATH, false));
            }
        }

        // informationService.saveInfo(information, UPLOAD_PATH, request); // TODO

        if (0 == information.getInformation_id()) {
            informationService.save(information);
            redirectAttributes.addFlashAttribute("tips", "保存成功！");
        } else {
            informationService.update(information);
            redirectAttributes.addFlashAttribute("tips", "修改成功！");
        }

        Map<String, Object> map = new HashMap<>();
        //map.put("list", informationService.findAllList("information_type = '" + information.getInformation_type() + "' AND information_status = 0", "COALESCE(information_attachment_url, '') != '' DESC, information_order_by, information_create_time DESC", "news".equals(information.getInformation_type()) ? 7 : 4)); // COALESCE 返回参数中第一个非NULL值
        map.put("list", informationService.findAllList("information_type = '" + information.getInformation_type() + "' AND information_status = 0", "information_order_by, information_create_time DESC", Integer.valueOf(MAX_INFORMATION_COUNT)));
        map.put("request", request);
        for (Map.Entry<String, String> entry : getInformationTypeMap().entrySet()) {
            if (information.getInformation_type().equals(entry.getKey())) {
                map.put("title", entry.getValue());
            }
        }
        map.put("information", information);
        map.put("active", information.getInformation_type());
        map.put("informationRuleMap", getInformationRuleMap());
        try {
            generateHTML(map, information, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/manage/information");
    }

    /**
     * 更改状态
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @SystemLog(model = "资讯", description = "更改状态")
    public String updateStatus(HttpServletRequest request, @RequestParam("idList[]") String[] ids, @RequestParam("status") int status) throws Exception {
        if (null == ids || 0 == ids.length || StringUtils.isEmpty(ids[0])) {
            throw new Exception("更改资讯状态的参数不对！");
        } else {
            informationService.updateStatus(ids, status);
            return 0 == status ? "上线" : "下线";
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
    protected void generateHTML(Map<String, Object> map, Information information, HttpServletRequest request) throws Exception {
        FileUtil.generateHTML("WEB-INF/ftl/site", "InformationIndex.ftl", "index_" + information.getInformation_type() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH);
        FileUtil.generateHTML("WEB-INF/ftl/site", "Information.ftl", information.getInformation_type() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH);
        FileUtil.generateHTML("WEB-INF/ftl/site", "InformationDetails.ftl", "information_" + information.getInformation_id() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH);
        FileUtil.generateHTML("WEB-INF/ftl/site", "InformationDetailsH5Detail.ftl", "info_h5_" + information.getInformation_id() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH);
        if ("news".equals(information.getInformation_type())) {
            FileUtil.generateHTML("WEB-INF/ftl/site", "InformationPictures.ftl", "index_pic.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
        }
    }

}
