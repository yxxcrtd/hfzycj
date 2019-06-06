package com.hfzycj.controller;

import com.hfzycj.annotation.SystemLog;
import com.hfzycj.domain.Park;
import com.hfzycj.domain.ParkCommunal;
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
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Park Controller
 */
@RestController
@RequestMapping("manage/park")
public class ParkController extends BaseController {

    /**
     * List
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @SystemLog(model = "停车场", description = "列表")
    public ModelAndView list(@RequestParam(value = "p", defaultValue = "1") int p, @RequestParam(value = "k", required = false) String k,
                             @RequestParam(value = "k2", required = false) String k2,
                             @RequestParam(value = "s1", required = false) String s1,
                             @RequestParam(value = "s2", required = false) String s2) {
        ModelAndView mav = new ModelAndView();

		// 输入框的内容模糊查询
        mav.addObject("k", k);
        String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
        k = null == k ? "" : "park_name like " + like;
        mav.addObject("k2", k2);
        String like2 = "%".equals(k2) ? "'%\\%%'" : "'%" + k2 + "%'";
        k2 = null == k2 ? "" : " AND park_code like " + like2;
        k += k2;

		/* 选择框的内容查询 */
        mav.addObject("s1", s1);
        if (null != s1 && !"".equals(s1)) {
            k += " AND park_status = " + s1;
        }
        mav.addObject("s2", s2);
        if (null != s2 && !"".equals(s2)) {
            k += " AND park_type = " + s2;
        }
        Pager pager = new Pager();
        pager.setPageNo(p);
        int count = parkService.findAllCount(park, k);
        pager.setTotalCount(count);
        mav.addObject("list", parkService.findByPager(pager, k, ""));
        mav.addObject("pager", pager);
        mav.addObject("count", count);
        mav.addObject("status", null == s1 ? "" : s1); // 列表页面中首先显示全部
        mav.addObject("type", null == s2 ? "" : s2); // 列表页面中首先显示全部
        mav.addObject("active", "park");
        mav.setViewName("park/ParkList");
        return mav;
    }

    /**
     * Edit
     */
    @RequestMapping("edit/{parkId:[\\d]+}")
    @SystemLog(model = "停车场", description = "编辑")
    public ModelAndView edit(@PathVariable(value = "parkId") int parkId) {
        if (0 == parkId) {
            park = new Park();
            park.setPark_id(parkId);
        } else {
            park = parkService.findById(parkId);
        }
        ModelAndView mav = new ModelAndView("park/ParkEdit");
        mav.addObject("park", park);
        List<Map<String, Object>> listMap = communalService.getMcList();
        List<ParkCommunal> pcList = parkCommunalService.findAllList(" parkcommunal_parkid=" + parkId + " ", null, null);
        StringBuffer sbfParkIds = new StringBuffer();
        int i = 0;
        if (null != pcList && pcList.size() > 0) {
            for (ParkCommunal parkCommunal : pcList) {
                try {
                    if (i == 0) {
                        sbfParkIds.append(parkCommunal.getParkcommunal_communalid());
                    } else {

                        sbfParkIds.append(",").append(parkCommunal.getParkcommunal_communalid());
                    }
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        mav.addObject("listMap", listMap);
        mav.addObject("pcList", pcList);
        mav.addObject("communal_ids", sbfParkIds.toString());
        return mav;
    }

    /**
     * Save
     *
     * @param park
     * @param redirectAttributes
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @SystemLog(model = "停车场", description = "保存")
    public ModelAndView save(@RequestParam(value = "communal_ids", required = false) String communal_ids, @ModelAttribute("park") @Valid Park park, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return new ModelAndView("park/ParkEdit", "park", park);
        }
        List<MultipartFile> files = request.getFiles("file");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                park.setPark_logo(FileUtil.uploadFile(file, UPLOAD_PATH, false));
            }
        }
        List<Integer> integerList = new LinkedList<>();
        if (null != communal_ids && !"".equals(communal_ids)) {
            String[] ids = communal_ids.split(",");
            for (String id : ids) {
                try {
                    integerList.add(Integer.valueOf(id));
                } catch (Exception ex) {
                }
            }
        }
        if (0 == park.getPark_id()) {
            park.setPark_create_time(new Date());
            park.setPark_update_time(new Date());
            park.setPark_surplus(0);
            parkService.saveParkInfo(park, integerList);
            redirectAttributes.addFlashAttribute("tips", "保存成功！");
        } else {
            park.setPark_update_time(new Date());
            parkService.saveParkInfo(park, integerList);
            redirectAttributes.addFlashAttribute("tips", "修改成功！");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("list", parkService.parkInit()); // 先更新缓存，然后返回 List
        map.put("request", request);
        map.put("active", "park");
        map.put("park", park);
        generateHTML(map, request, park);
        return new ModelAndView("redirect:/manage/park");
    }

    /**
     * Generate HTML File
     *
     * @param map
     * @param request
     * @throws IOException
     * @throws TemplateException
     */
    protected void generateHTML(Map<String, Object> map, HttpServletRequest request, Park park) throws Exception {
        // 生成首页的"停车场备案公示"
        FileUtil.generateHTML("WEB-INF/ftl/site", "IndexPark.ftl", "index_park.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

        // 生成二级页面
        // FileUtil.generateHTML("WEB-INF/ftl/site", "Park.ftl", "park.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

        // 生成停车场详情页
        FileUtil.generateHTML("WEB-INF/ftl/site", "ParkDetailPage.ftl", "park_" + park.getPark_id() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH);
    }

    /**
     * 更改状态
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @SystemLog(model = "停车场", description = "更改状态")
    public String updateStatus(HttpServletRequest request, @RequestParam("idList[]") String[] ids, @RequestParam("status") int status) throws Exception {
        if (null == ids || 0 == ids.length || StringUtils.isEmpty(ids[0])) {
            throw new Exception("更改停车场状态的参数不对！");
        } else {
            parkService.updateStatus(ids, status);
            //保存停车场数据时，更新缓存
            parkService.parkInit();

            Map<String, Object> map = new HashMap<>();
            map.put("list", parkService.findAllList("park_status = 0", "", null));
            map.put("request", request);

            Park park = null;
            for (String id : ids) {
                park = parkService.findById(Integer.valueOf(id));
                generateHTML(map, request, park);
            }
            return 0 == status ? "显示" : "隐藏";
        }
    }

}
