package com.hfzycj.controller;

import com.hfzycj.annotation.SystemLog;
import com.hfzycj.domain.User;
import com.hfzycj.util.Constants;
import com.hfzycj.util.MD5;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Lgin
 */
@RestController
@RequestMapping("login")
public class LoginController extends BaseController {

    /**
     * Login
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @SystemLog(model = "登录", description = "登录页")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", new User());
        mav.setViewName("Login");
        return mav;
    }

    /**
     * Login Check
     */
    @RequestMapping(value = "check", method = RequestMethod.POST)
    @SystemLog(model = "登录", description = "登录检查")
    public ModelAndView check(@ModelAttribute("user") @Valid User user, HttpServletRequest request, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            if (null == user.getUser_name() || "".equals(user.getUser_name())) {
                redirectAttributes.addFlashAttribute("tips", "用户名不能为空！");
                return new ModelAndView("redirect:/login");
            }
            if (null == user.getUser_password() || "".equals(user.getUser_password())) {
                redirectAttributes.addFlashAttribute("tips", "密码不能为空！");
                return new ModelAndView("redirect:/login");
            }
        }

        User u = userService.findByName(user.getUser_name());
        if (null != u) {
            if (!MD5.toMD5(user.getUser_password() + Constants.USER_PASSWORD_SALT).equals(u.getUser_password())) {
                redirectAttributes.addFlashAttribute("tips", "用户名或密码不正确！");
                return new ModelAndView("redirect:/login");
            } else {
                request.getSession().setAttribute(Constants.SESSION_USER_KEY, u);
//				redisUtil.set(user.getUser_name(), u.getUser_password(), SESSION_EXPIRATION * 60 * 1000);
            }
        }
        return new ModelAndView("redirect:/manage");
    }

}
