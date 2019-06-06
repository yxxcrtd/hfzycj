package com.hfzycj.controller;

import com.hfzycj.util.Pager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Log Controller
 */
@RestController
@RequestMapping("manage/log")
public class LogController extends BaseController {

    /**
     * List
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p,
                             @RequestParam(value = "k", required = false) String k,
                             @RequestParam(value = "k2", required = false) String k2,
                             @RequestParam(value = "k3", required = false) String k3,
                             @RequestParam(value = "startTime", required = false) String startTime,
                             @RequestParam(value = "endTime", required = false) String endTime) {
        ModelAndView mav = new ModelAndView();
        /**查询条件设置 */
        mav.addObject("k", k);
        String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
        k = null == k ? "1 = 1" : "log_model like " + like;
        mav.addObject("k2", k2);
        String like2 = "%".equals(k2) ? "'%\\%%'" : "'%" + k2 + "%'";
        k2 = null == k2 ? "" : " AND log_method like " + like2;
        k += k2;
        mav.addObject("k3", k3);
        String like3 = "%".equals(k3) ? "'%\\%%'" : "'%" + k3 + "%'";
        k3 = null == k3 ? "" : " AND log_description like " + like3;
        k += k3;
        mav.addObject("startTime", startTime);
        if(null == startTime || "".equals(startTime)){
            startTime = "";
        }else{
            startTime = " AND log_create_time >= " + "'" + startTime + "'";
        }
        k += startTime;
        mav.addObject("endTime", endTime);
        if(null == endTime || "".equals(endTime)){
            endTime = "";
        }else{
            endTime = " AND log_create_time <= " + "'" + endTime + "'";
        }
        k += endTime;
        /**分页 */
        Pager pager = new Pager();
        pager.setPageNo(p);
        int count = logService.findAllCount(log, k);
        pager.setTotalCount(count);
        mav.addObject("list", logService.findByPager(pager, k, "log_create_time DESC"));
        mav.addObject("pager", pager);
        mav.addObject("count", count);
        mav.addObject("active", "log");
        mav.setViewName("log/LogList");
        return mav;
    }

}
