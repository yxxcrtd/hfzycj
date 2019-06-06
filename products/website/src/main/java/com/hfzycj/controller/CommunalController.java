package com.hfzycj.controller;

import com.hfzycj.annotation.SystemLog;
import com.hfzycj.domain.Communal;
import com.hfzycj.domain.Dictionary;
import com.hfzycj.domain.Menu;
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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Communal Controller
 */
@RestController
@RequestMapping("manage/communal")
public class CommunalController extends BaseController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @SystemLog(model = "公共资源管理", description = "显示列表")
    public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p,
                             @RequestParam(value = "s_communal_menuid", required = false) String s_communal_menuid,
                             @RequestParam(value = "s_name", required = false) String s_name,
                             @RequestParam(value = "s_status", required = false) String s_status
    ) {
        ModelAndView mav = new ModelAndView("communal/CommunalList");
        mav.addObject("s_communal_menuid", s_communal_menuid);
        mav.addObject("s_name", s_name);
        mav.addObject("s_status", s_status);
        StringBuffer sbfWhere = new StringBuffer();
        if (null == s_name || "".equals(s_name) || " ".equals(s_name)) {
            sbfWhere.append(" 1 = 1 ");
        } else {
            sbfWhere.append(" communal_name like ").append("'%").append(s_name).append("%'");
        }
        if (null != s_communal_menuid && !"".equals(s_communal_menuid)) {
            sbfWhere.append(" and communal_menuid=").append(s_communal_menuid);
        }
        if (null != s_status && !"".equals(s_status)) {
            sbfWhere.append(" and communal_status=").append(s_status);
        }
        Pager pager = new Pager();
        pager.setPageNo(p);
        int count = communalService.findAllCount(communal, sbfWhere.toString());
        pager.setTotalCount(count);
        List<Communal> list = communalService.findByPager(pager, sbfWhere.toString(), "communal_orderby");
        List<Menu> menuList = menuService.findAllList("menu_parentid=200000", null, null);
        mav.addObject("list", list);
        mav.addObject("menuList", menuList);
        mav.addObject("pager", pager);
        mav.addObject("count", count);
        mav.addObject("active", "communal");
        mav.addObject("sbfWhere", sbfWhere);
        mav.setViewName("communal/CommunalList");
        return mav;
    }

    /**
     * Edit
     *
     * @return
     */
    @RequestMapping("edit/{communalId:[\\d]+}")
    @SystemLog(model = "公共资源管理", description = "添加，修改")
    public ModelAndView edit(@PathVariable(value = "communalId") int communalId) {
        if (0 == communalId) {
            communal = new Communal();
            communal.setCommunal_id(communalId);
        } else {
            communal = communalService.findById(communalId);
        }
        ModelAndView mav = new ModelAndView("communal/CommunalEdit");
        List<Dictionary> listDictionary = dictionaryService.findAllList("dictionary_key='menu_target'", null, null);
        List<Menu> menuList = menuService.findAllList("menu_parentid=200000", null, null);
        mav.addObject("menuList", menuList);
        mav.addObject("listDictionary", listDictionary);
        mav.addObject("communal", communal);
        mav.addObject("statusMap", getStatusMap());
        mav.addObject("active", "communal");
        return mav;
    }

    /**
     * Save
     *
     * @param redirectAttributes
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @SystemLog(model = "公共资源管理", description = "保存")
    public ModelAndView save(@ModelAttribute("communal") @Valid Communal communal, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request)
            throws Exception {
        if (result.hasErrors()) {
//            System.out.println(result.toString());
            ModelAndView mav = new ModelAndView("communal/CommunalEdit");
            List<Dictionary> listDictionary = dictionaryService.findAllList("dictionary_key='menu_target'", null, null);
            List<Menu> menuList = menuService.findAllList("menu_parentid=200000", null, null);
            mav.addObject("menuList", menuList);
            mav.addObject("listDictionary", listDictionary);
            mav.addObject("communal", communal);
            List<Map<String, Object>> listMap = communalService.getMcList();
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("listMap", listMap);
            generateHTML(map, request);
            return mav;
//            return new ModelAndView("communal/CommunalEdit", "communal", communal);
        }
//        List<MultipartFile> files = request.getFiles("file");
//        for (MultipartFile file : files) {
//            if (!file.isEmpty()) {
//                seo.setSeo_icon(FileUtil.uploadFile(file, UPLOAD_PATH, false));
//            }
//        }
        if (0 == communal.getCommunal_id()) {
            communal.setCommunal_createtime(new Date());
            communalService.save(communal);
            redirectAttributes.addFlashAttribute("tips", "保存成功！");
        } else {
            communal.setCommunal_createtime(new Date());
            communalService.update(communal);
            redirectAttributes.addFlashAttribute("tips", "修改成功！");
        }
//        List<Dictionary> listDictionary=dictionaryService.findAllList("dictionary_key='menu_target'",null);
//        mav.addObject("listDictionary", listDictionary);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("communal", communalService.findById(communal.getCommunal_id()));
//        map.put("request", request);
//        String title = 0 == communal.getCommunal_id() ? "新增Communal：" : "修改Communal";
//        generateHTML(map, request);

        List<Map<String, Object>> listMap = communalService.getMcList();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("listMap", listMap);
        generateHTML(map, request);

        return new ModelAndView("redirect:/manage/communal");
    }
    /**
     * 更改状态
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @SystemLog(model = "公共资源管理", description = "修改状态")
    public String updateStatus(HttpServletRequest request, @RequestParam("idList[]") String[] ids, @RequestParam("status") int status) throws Exception {
        if (null == ids || 0 == ids.length || StringUtils.isEmpty(ids[0])) {
            throw new Exception("更改友情链接状态的参数不对！");
        } else {
            communalService.updateStatus(ids, status);

            List<Map<String, Object>> listMap = communalService.getMcList();
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("listMap", listMap);
            generateHTML(map, request);
//            Map<String, Object> map2 = new HashMap<>();
//            map2.put("list", list);
//            map2.put("request", request);
            String s = 0 == status ? "显示" : "隐藏";
//            generateHTML(map2, request);
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
        FileUtil.generateHTML("WEB-INF/ftl/menu", "MenuInList.ftl", "mclist.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
    }
}