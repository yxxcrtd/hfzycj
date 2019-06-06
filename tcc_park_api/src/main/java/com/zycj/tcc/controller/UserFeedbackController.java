/**
 * 
 */
package com.zycj.tcc.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.ExcelUtil;
import com.zycj.tcc.common.ExportConfig;
import com.zycj.tcc.domain.criteria.UserFeedbackCriteria;
import com.zycj.tcc.domain.vo.UserFeedbackVo;
import com.zycj.tcc.service.UserFeedbackService;

/**
 *会员相关
* Title: UserController.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年4月10日
 */
@Controller
@RequestMapping("feedback")
public class UserFeedbackController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserFeedbackController.class);
	@Autowired
	private UserFeedbackService userFeedbackService;

	@RequestMapping("/list.htm")
	public ModelAndView list(HttpSession session, UserFeedbackCriteria criteria) {
		logger.info("UserFeedbackController list");
		List<UserFeedbackVo> list = null;
		if (null != criteria) {
			list = userFeedbackService.list(getCurrentAdmin(session), criteria);
		} else {
			list = userFeedbackService.list(getCurrentAdmin(session),
					new UserFeedbackCriteria());
		}
		Map<String, Object> model = getModel();
		model.put("list", list);
		model.put("criteria", criteria);
		return new ModelAndView("feedback/list", "model", model);
	}

	@RequestMapping("/export.htm")
	public void export(HttpServletResponse resp,HttpSession session, UserFeedbackCriteria criteria) throws IOException {
		logger.info("UserFeedbackController export");
		List<UserFeedbackVo> list = null;
		if (null != criteria) {
			//不用分页
			criteria.setLimit(0);
			list = userFeedbackService.list(getCurrentAdmin(session), criteria);
		}
		ServletOutputStream out = resp.getOutputStream();
		ExcelUtil<UserFeedbackVo> excelUtil = new ExcelUtil<UserFeedbackVo>();
		String faleName = ExportConfig.USER_FEEDBACK_REPORT_NAME + '-'
				+ (new SimpleDateFormat(Constants.UNIQUE_FILE_NAME_TIME_FORMATE).format(new Date()));
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-disposition", "filename="
				+ new String((faleName + ".xls").getBytes("utf-8"), "iso8859-1"));
		excelUtil.export(list, faleName, ExportConfig.USER_FEEDBACK_REPORT_HEADERS, out, Constants.DEFAULT_TIME_FORMATE);
	}
}
