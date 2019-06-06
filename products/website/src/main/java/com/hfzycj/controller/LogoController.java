package com.hfzycj.controller;

import com.hfzycj.annotation.SystemLog;
import com.hfzycj.domain.Logo;
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
 * Logo Controller
 */
@RestController
@RequestMapping("manage/logo")
public class LogoController extends BaseController {

    /**
     * Edit
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @SystemLog(model = "Logo", description = "编辑")
    public ModelAndView edit() {
        ModelAndView mav = new ModelAndView();
        logo = logoService.findById(INT_ONE);
        if (null == logo) {
            logo = new Logo();
            logo.setLogo_url("/");
        }
        mav.addObject("logo", logo);
        mav.addObject("active", "logo");
        mav.setViewName("logo/LogoEdit");
        return mav;
    }

    /**
     * Save
     *
     * @param logo
     * @param redirectAttributes
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @SystemLog(model = "Logo", description = "保存")
    public ModelAndView save(@ModelAttribute("logo") @Valid Logo logo, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return new ModelAndView("logo/LogoEdit", "logo", logo);
        }
        List<MultipartFile> files = request.getFiles("file");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                logo.setLogo_logo(FileUtil.uploadFile(file, UPLOAD_PATH, false));
            }
        }

        if (0 == logo.getLogo_id()) {
            logoService.save(logo);
            redirectAttributes.addFlashAttribute("tips", "保存成功！");
        } else {
            logoService.update(logo);
            redirectAttributes.addFlashAttribute("tips", "修改成功！");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("logo", logoService.findById(INT_ONE));
        map.put("request", request);
        generateHTML(map, request);
        return new ModelAndView("redirect:/manage/logo");
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
        FileUtil.generateHTML("WEB-INF/ftl/site", "Logo.ftl", "logo.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
    }

}
