package com.hfzycj.controller;

import com.hfzycj.configuration.Constants;
import com.hfzycj.domain.User;
import com.hfzycj.utils.MD5;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Login
 */
@RestController
@RequestMapping("login")
public class LoginController extends BaseController {

    /**
     * Login
     */
    @GetMapping("")
    ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", new User());
        mav.setViewName("Login");
        return mav;
    }

    /**
     * Login Check
     */
    @PostMapping("check")
    ModelAndView check(@ModelAttribute("user") @Valid User user, HttpServletRequest request, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            if (null == user.getUsername() || "".equals(user.getUsername())) {
                redirectAttributes.addFlashAttribute("tips", "用户名不能为空！");
                return new ModelAndView("redirect:/login");
            }
            if (null == user.getPassword() || "".equals(user.getPassword())) {
                redirectAttributes.addFlashAttribute("tips", "密码不能为空！");
                return new ModelAndView("redirect:/login");
            }
        }

        User u = userService.findByUsername(user.getUsername());
        if (null != u) {
            if (!MD5.toMD5(user.getPassword() + Constants.USER_PASSWORD_SALT).equals(u.getPassword())) {
                redirectAttributes.addFlashAttribute("tips", "用户名或密码不正确！");
                return new ModelAndView("redirect:/login");
            } else {
                request.getSession().setAttribute(Constants.SESSION_USER_KEY, u);
            }
        }
        return new ModelAndView("redirect:/manage");
    }

}
