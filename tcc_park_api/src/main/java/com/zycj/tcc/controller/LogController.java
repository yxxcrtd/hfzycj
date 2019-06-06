/**
 * 
 */
package com.zycj.tcc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zycj.tcc.domain.criteria.LogSystemCriteria;
import com.zycj.tcc.domain.vo.LogSystemVo;
import com.zycj.tcc.service.LogService;

/**
 * 操作日志相关
* Title: LogController.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月13日
 */
@Controller
@RequestMapping("log")
public class LogController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(LogController.class);
	@Autowired
	private LogService logService;

	@RequestMapping("/list.htm")
	public ModelAndView list(HttpSession session, LogSystemCriteria criteria) {
		logger.info("LogController list");
		List<LogSystemVo> list = null;
		if (null != criteria) {
			list = logService.list(getCurrentAdmin(session), criteria);
		} else {
			list = logService.list(getCurrentAdmin(session),
					new LogSystemCriteria());
		}
		Map<String, Object> model = getModel();
		model.put("list", list);
		model.put("criteria", criteria);
		return new ModelAndView("log/list", "model", model);
	}
}
