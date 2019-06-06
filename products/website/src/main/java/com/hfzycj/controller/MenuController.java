package com.hfzycj.controller;
import com.hfzycj.annotation.SystemLog;
import com.hfzycj.domain.Dictionary;
import com.hfzycj.domain.Menu;
import com.hfzycj.util.FileUtil;
import com.hfzycj.util.Pager;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import java.util.*;
/**
 * Menu Controller
 */
@RestController
@RequestMapping("manage/menu")
public class MenuController extends BaseController {

    /**
     * Edit
     *
     * @param menuId
     * @return
     */
    @RequestMapping("edit/{menuId:[\\d]+}")
    @SystemLog(model = "菜单分类管理", description = "增加，修改")
    public ModelAndView edit(@PathVariable(value = "menuId") int menuId) {
        if (0 == menuId) {
            menu = new Menu();
            menu.setMenu_id(menuId);
//            menu.setMenu_createtime(new Date());
        } else {
            menu = menuService.findById(menuId);
        }
        ModelAndView mav = new ModelAndView("menu/MenuEdit");

        mav.addObject("statusMap", getStatusMap());
//        mav.setViewName("menu/MenuEdit");
        List<Dictionary> listDictionary = dictionaryService.findAllList("dictionary_key='menu_target'", null, null);
        List<Dictionary> listDictionary2 = dictionaryService.findAllList("dictionary_key='menu_category'", null, null);
        mav.addObject("listDictionary2", listDictionary2);
        mav.addObject("listDictionary", listDictionary);
        mav.addObject("menu", menu);
        mav.addObject("active", "menu");
        return mav;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @SystemLog(model = "菜单分类管理", description = "显示列表")
    public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p,
                             @RequestParam(value = "s_menu_category", required = false) String s_menu_category,
                             @RequestParam(value = "s_menu_title", required = false) String s_menu_title,
                             @RequestParam(value = "s_menu_status", required = false) String s_menu_status
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("s_menu_category", s_menu_category);
        mav.addObject("s_menu_title", s_menu_title);
        mav.addObject("s_menu_status", s_menu_status);

        String like = "";//"%".equals(s_menu_title) ? "'%\\%%'" : "'%" + s_menu_title + "%'";
        if (null == s_menu_title || "".equals(s_menu_title) || " ".equals(s_menu_title)) {
            like = "'%\\%%'";
        } else {
            like = "'%" + s_menu_title + "%'";
        }
        s_menu_title = (null == s_menu_title || "".equals(s_menu_title)) ? "1 = 1" : "menu_title like " + like;
        if (null != s_menu_category && !"".equals(s_menu_category)) {
            s_menu_title += " AND menu_parentid = " + s_menu_category;
        }
        if (null != s_menu_status && !"".equals(s_menu_status)) {
            s_menu_title += " AND menu_status = " + s_menu_status;
        }

        Pager pager = new Pager();
        pager.setPageNo(p);
        int count = menuService.findAllCount(menu, s_menu_title);
        pager.setTotalCount(count);
        List<Dictionary> listDictionary = dictionaryService.findAllList("dictionary_key='menu_category'", null, null);
        List<Menu> list = menuService.findByPager(pager, s_menu_title, "menu_orderby");
        // map.put("list", menuService.findAllList("link_status = 0", "menu_orderby"));
     /*   Map<Integer, Menu> mapList = new HashMap<Integer, Menu>();
        for (Menu menu : list) {
            mapList.put(menu.getMenu_id(),menu);

        }
        mav.addObject("mapList", mapList);*/
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (Menu menu : list) {
            map = new HashMap<String, Object>();
            map.put("menu_id", menu.getMenu_id());
            map.put("menu_createtime", menu.getMenu_createtime());
            map.put("menu_describe", menu.getMenu_describe());
            map.put("menu_parentid", menu.getMenu_parentid());
            map.put("menu_status", menu.getMenu_status());
            map.put("menu_target", menu.getMenu_target());
            map.put("menu_title", menu.getMenu_title());
            map.put("menu_url", menu.getMenu_url());
            map.put("menu_parentidtitle", getValue(String.valueOf(menu.getMenu_parentid()), listDictionary));
//            map.put(String.valueOf(menu.getMenu_id()).concat("_V"),"dddddddddddd");
            map.put("menu_orderby", menu.getMenu_orderby());
            listMap.add(map);
        }

        mav.addObject("list", list);
        mav.addObject("listMap", listMap);
        mav.addObject("listDictionary", listDictionary);
        mav.addObject("pager", pager);
        mav.addObject("count", count);
        mav.addObject("active", "menu");
        mav.setViewName("menu/MenuList");
        return mav;
    }

    private String getValue(String key, List<Dictionary> listDictionary) {
        String valueStr = "";
        for (Dictionary dictionary : listDictionary) {
            if (key.equals(dictionary.getDictionary_value())) {
                valueStr = dictionary.getDictionary_describe();
                break;
            }
        }
        return valueStr;
    }

    /**
     * Save
     *
     * @param menu
     * @param redirectAttributes
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @SystemLog(model = "菜单分类管理", description = "保存内容")
    public ModelAndView save(@ModelAttribute("menu") @Valid Menu menu, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request)
            throws Exception {
        if (result.hasErrors()) {
            StringBuffer stringBuffer=new StringBuffer();
            for(FieldError error : result.getFieldErrors()){
                stringBuffer.append("getField=").append(error.getField()).append(",");
                stringBuffer.append("getDefaultMessage=").append(error.getDefaultMessage()).append(",");
                stringBuffer.append("getCode=").append(error.getCode()).append("\n");
//                ErrorInfo errorInfo = new ErrorInfo();
//                errorInfo.setField(error.getField());
//                errorInfo.setInfo(error.getDefaultMessage());
//                errorInfo.setType(error.getCode());
//                errorInfoList.add(errorInfo);

            }
            System.out.println(stringBuffer);
            ModelAndView mav = new ModelAndView("menu/MenuEdit");
            List<Dictionary> listDictionary = dictionaryService.findAllList("dictionary_key='menu_target'", null, null);
            List<Dictionary> listDictionary2 = dictionaryService.findAllList("dictionary_key='menu_category'", null, null);
            mav.addObject("listDictionary2", listDictionary2);
            mav.addObject("listDictionary", listDictionary);
            mav.addObject("menu", menu);
            mav.addObject("statusMap", getStatusMap());
            return mav;
        }
        if (0 == menu.getMenu_id()) {
            menu.setMenu_createtime(new Date());
            menuService.save(menu);
            redirectAttributes.addFlashAttribute("tips", "保存成功！");
        } else {
            menuService.update(menu);
            redirectAttributes.addFlashAttribute("tips", "修改成功！");
        }
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("menu", menuService.findById(menu.getMenu_id()));
//        map.put("request", request);
        String title = 0 == menu.getMenu_id() ? "新增Menu：" : "修改Menu";
//        generateHTML(map, request);

        List<Map<String, Object>> listMap = communalService.getMcList();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("listMap", listMap);
        generateHTML(map, request);

        return new ModelAndView("redirect:/manage/menu");
    }

    /**
     * 更改状态
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @SystemLog(model = "菜单分类管理", description = "保存状态")
    public String updateStatus(HttpServletRequest request, @RequestParam("idList[]") String[] ids, @RequestParam("status") int status) throws Exception {
        if (null == ids || 0 == ids.length || StringUtils.isEmpty(ids[0])) {
            throw new Exception("更改友情链接状态的参数不对！");
        } else {
            menuService.updateStatus(ids, status);
            List<Dictionary> listDictionary = dictionaryService.findAllList("dictionary_key='menu_category'", null, null);
            List<Menu> list = menuService.findAllList("", "menu_orderby", null);
            List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            for (Menu menu : list) {
                map = new HashMap<String, Object>();
                map.put("menu_id", menu.getMenu_id());
                map.put("menu_createtime", menu.getMenu_createtime());
                map.put("menu_describe", menu.getMenu_describe());
                map.put("menu_parentid", menu.getMenu_parentid());
                map.put("menu_status", menu.getMenu_status());
                map.put("menu_target", menu.getMenu_target());
                map.put("menu_title", menu.getMenu_title());
                map.put("menu_url", menu.getMenu_url());
                map.put("menu_parentidtitle", getValue(String.valueOf(menu.getMenu_parentid()), listDictionary));
                map.put("menu_orderby", menu.getMenu_orderby());
                listMap.add(map);
            }
//            Map<String, Object> map2 = new HashMap<>();
//            map2.put("list", list);
//            map2.put("request", request);
            String s = 0 == status ? "显示" : "隐藏";
//            generateHTML(map2, request);
            List<Map<String, Object>> listMap2 = communalService.getMcList();
            Map<String, Object> map2 = new LinkedHashMap<>();
            map.put("listMap", listMap2);
            generateHTML(map2, request);
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
