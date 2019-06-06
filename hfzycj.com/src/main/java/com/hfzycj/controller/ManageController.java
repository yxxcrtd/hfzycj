package com.hfzycj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Manage Controller
 */
@RestController
@RequestMapping("manage")
public class ManageController extends BaseController {

    /**
     * Manage
     *
     * @return
     */
    @GetMapping("")
    ModelAndView showLogin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("active", "manage");
        mav.setViewName("Manage");
        return mav;
    }

    /**
     * Top
     *
     * @return
     */
    @GetMapping("top")
    ModelAndView top() {
        return new ModelAndView("Top");
    }

    /**
     * Menu
     *
     * @return
     */
    @GetMapping("menu")
    ModelAndView menu() {
        return new ModelAndView("Menu");
    }

    /**
     * Main
     *
     * @return
     */
    @GetMapping("main")
    String main() {
        return "Main";
    }

    /**
     * Manage Login
     *
     * @return
     */
    @PostMapping("login")
    ModelAndView login() {
        return new ModelAndView("redirect:/manage");
    }

}
