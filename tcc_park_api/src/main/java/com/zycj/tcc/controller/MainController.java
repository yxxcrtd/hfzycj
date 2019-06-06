/**
 * 
 */
package com.zycj.tcc.controller;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zycj.tcc.service.SysAdminService;

/**
 * 主界面相关 Title: MainController.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月27日
 */
@Controller
public class MainController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired
	private SysAdminService saService;

	@RequestMapping("/top.htm")
	public ModelAndView top(HttpSession session) {
		logger.info("top:");
		return new ModelAndView("top", "model", getModel());
	}

	@RequestMapping("/left.htm")
	public ModelAndView left() {
		logger.info("left:");
		return new ModelAndView("left", "model", getModel());
	}

	@RequestMapping("/firstPage.htm")
	public ModelAndView firstPage() {
		logger.info("firstPage:");
		return new ModelAndView("firstPage", "model", getModel());
	}
}
