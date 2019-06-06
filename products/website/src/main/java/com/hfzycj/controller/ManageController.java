package com.hfzycj.controller;

import com.hfzycj.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Manage Controller
 */
@Controller
@RequestMapping("manage")
public class ManageController extends BaseController {

    /**
     * Manage
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);
        mav.addObject("active", "manage");
        mav.setViewName("Manage");
        return mav;
    }

    /**
     * Top
     *
     * @return
     */
    @RequestMapping(value = "top", method = RequestMethod.GET)
    public ModelAndView top(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", request.getSession().getAttribute(Constants.SESSION_USER_KEY));
        mav.setViewName("Top");
        return mav;
    }

    /**
     * Menu
     *
     * @return
     */
    @RequestMapping(value = "navigate", method = RequestMethod.GET)
    public ModelAndView menu(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", request.getSession().getAttribute(Constants.SESSION_USER_KEY));
        mav.addObject("informationTypeMap", getInformationTypeMap());
        mav.setViewName("Menu");
        return mav;
    }

    /**
     * Main
     *
     * @return
     */
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main() {
        return "Main";
    }

    /**
     * Manage Login
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login() {
        return new ModelAndView("redirect:/manage");
    }

}
