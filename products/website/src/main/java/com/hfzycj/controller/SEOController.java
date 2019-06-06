package com.hfzycj.controller;

import com.hfzycj.annotation.SystemLog;
import com.hfzycj.domain.SEO;
import com.hfzycj.util.FileUtil;
import freemarker.template.TemplateException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

import static com.hfzycj.util.Constants.INT_ONE;

/**
 * SEO Controller
 */
@RestController
@RequestMapping("manage/seo")
public class SEOController extends BaseController {

    /**
     * Edit
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @SystemLog(model = "SEO", description = "编辑")
    public ModelAndView edit() {
        ModelAndView mav = new ModelAndView();
        seo = seoService.findById(Long.valueOf(INT_ONE));
        if (null == seo) {
            seo = new SEO();
        }
        mav.addObject("seo", seo);
        mav.addObject("active", "seo");
        mav.setViewName("seo/SEOEdit");
        return mav;
    }

    /**
     * Save
     *
     * @param seo
     * @param redirectAttributes
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @SystemLog(model = "SEO", description = "保存")
    public ModelAndView save(@ModelAttribute("seo") @Valid SEO seo, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return new ModelAndView("seo/SEOEdit", "seo", seo);
        }

        List<MultipartFile> files = request.getFiles("file");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                seo.setSeo_icon(FileUtil.uploadFile(file, UPLOAD_PATH, false));
            }
        }

        if (0 == seo.getSeo_id()) {
            seoService.save(seo);
            redirectAttributes.addFlashAttribute("tips", "保存成功！");
        } else {
            seoService.update(seo);
            redirectAttributes.addFlashAttribute("tips", "修改成功！");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("seo", seoService.findById(Long.valueOf(INT_ONE)));
        map.put("request", request);
        generateHTML(map, request);
        return new ModelAndView("redirect:/manage/seo");
    }

    /**
     * Generate HTML File
     *
     * @param map
     * @param request
     * @throws IOException
     * @throws TemplateException
     */
    private void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
        FileUtil.generateHTML("WEB-INF/ftl/site", "SEO.ftl", "seo.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
    }

}
