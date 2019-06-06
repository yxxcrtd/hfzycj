package com.hfzycj.controller;

import com.hfzycj.annotation.SystemLog;
import com.hfzycj.domain.Copyright;
import com.hfzycj.util.FileUtil;
import freemarker.template.TemplateException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.hfzycj.util.Constants.INT_ONE;

/**
 * Copyright Controller
 */
@RestController
@RequestMapping("manage/copyright")
public class CopyrightController extends BaseController {

    /**
     * Edit
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @SystemLog(model = "版权信息", description = "添加、编辑")
    public ModelAndView edit() {
        ModelAndView mav = new ModelAndView();
        copyright = copyrightService.findById(Long.valueOf(INT_ONE));
        if (null == copyright) {
            copyright = new Copyright();
        }
        mav.addObject("copyright", copyright);
        mav.addObject("active", "copyright");
        mav.setViewName("copyright/CopyrightEdit");
        return mav;
    }

    /**
     * Save
     *
     * @param copyright
     * @param redirectAttributes
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @SystemLog(model = "版权信息", description = "保存")
    public ModelAndView save(@ModelAttribute("copyright") @Valid Copyright copyright, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return new ModelAndView("copyright/CopyrightEdit", "copyright", copyright);
        }
        if (0 == copyright.getCopyright_id()) {
            copyrightService.save(copyright);
            redirectAttributes.addFlashAttribute("tips", "保存成功！");
        } else {
            copyrightService.update(copyright);
            redirectAttributes.addFlashAttribute("tips", "修改成功！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("copyright", copyrightService.findById(Long.valueOf(INT_ONE)));
        map.put("request", request);
        generateHTML(map, request);
        return new ModelAndView("redirect:/manage/copyright");
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
        FileUtil.generateHTML("WEB-INF/ftl/site", "Copyright.ftl", "copyright.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
    }

}
