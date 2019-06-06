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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.Result;
import com.zycj.tcc.domain.criteria.NewsDetailCriteria;
import com.zycj.tcc.domain.vo.NewsDetailVo;
import com.zycj.tcc.exception.TccException;
import com.zycj.tcc.service.NewsDetailService;

/**
 * 资讯相关 Title: NewsController.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年3月27日
 */
@Controller
@RequestMapping("news")
public class NewsController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(NewsController.class);
	@Autowired
	private NewsDetailService newsService;

	@RequestMapping("/list.htm")
	public ModelAndView list(HttpSession session, NewsDetailCriteria criteria) {
		logger.info("NewsController list");
		List<NewsDetailVo> list = null;
		if (null != criteria) {
			list = newsService.list(getCurrentAdmin(session), criteria);
		} else {
			list = newsService.list(getCurrentAdmin(session),
					new NewsDetailCriteria());
		}
		Map<String, Object> model = getModel();
		model.put("list", list);
		model.put("criteria", criteria);
		return new ModelAndView("news/list", "model", model);
	}

	@RequestMapping("/toAdd.htm")
	public ModelAndView toAdd(HttpSession session) throws TccException,
			Exception {
		logger.info("NewsController toAdd");

		return new ModelAndView("news/add");
	}
	@RequestMapping("/toDetail.htm")
	public ModelAndView toDetail(HttpSession session, Integer id) throws TccException,
			Exception {
		logger.info("NewsController toDetail");
		Map<String, Object> model = getModel();
		String htmlSrc = newsService.getHtmlSrc(getCurrentAdmin(session), id);
		model.put("htmlSrc", htmlSrc);
		return new ModelAndView("news/detail","model", model);
	}

	@RequestMapping("/add.htm")
	public ModelAndView add(NewsDetailVo news, HttpSession session) {
		logger.info("NewsController add");
		Map<String, Object> model = null;
		Result result = null;
		try {
			newsService.add(getCurrentAdmin(session), news);
		} catch (TccException e) {
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("news", news);
			return new ModelAndView("news/add", "model", model);
		} catch (Exception e) {
			model = getModel();
			result = getResult();
			doException(model, result, e);
			model.put("news", news);
			return new ModelAndView("news/add", "model", model);
		}
		return new ModelAndView("redirect:list.htm");
	}

	@ResponseBody
	@RequestMapping("/online.htm")
	public Result online(HttpSession session, Integer id) {
		Result result = getResult();
		try {
			newsService.online(getCurrentAdmin(session), id);

		} catch (TccException e) {
			result.setCode(Constants.FAIL_RESULT);
			result.setExceptionClassName(e.getClass().toString());
			result.setExceptionMsg(e.getMessage());
			result.setMsg(e.getMessage());
		} catch (Exception e) {
			result.setCode(Constants.FAIL_RESULT);
			result.setExceptionClassName(e.getClass().toString());
			result.setExceptionMsg(e.getMessage());
			result.setMsg(e.getMessage());
		}
		return result;

	}

	@ResponseBody
	@RequestMapping("/outline.htm")
	public Result outline(HttpSession session, Integer id) {
		Result result = getResult();
		try {
			newsService.outline(getCurrentAdmin(session), id);

		} catch (TccException e) {
			result.setCode(Constants.FAIL_RESULT);
			result.setExceptionClassName(e.getClass().toString());
			result.setExceptionMsg(e.getMessage());
			result.setMsg(e.getMessage());
		} catch (Exception e) {
			result.setCode(Constants.FAIL_RESULT);
			result.setExceptionClassName(e.getClass().toString());
			result.setExceptionMsg(e.getMessage());
			result.setMsg(e.getMessage());
		}
		return result;

	}

	@ResponseBody
	@RequestMapping("/delete.htm")
	public Result delete(HttpSession session, Integer id) {
		Result result = getResult();
		try {
			newsService.delete(getCurrentAdmin(session), id);

		} catch (TccException e) {
			result.setCode(Constants.FAIL_RESULT);
			result.setExceptionClassName(e.getClass().toString());
			result.setExceptionMsg(e.getMessage());
			result.setMsg(e.getMessage());
		} catch (Exception e) {
			result.setCode(Constants.FAIL_RESULT);
			result.setExceptionClassName(e.getClass().toString());
			result.setExceptionMsg(e.getMessage());
			result.setMsg(e.getMessage());
		}
		return result;

	}
}
