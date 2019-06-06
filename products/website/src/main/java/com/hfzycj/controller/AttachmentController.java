package com.hfzycj.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hfzycj.domain.Attachment;
import com.hfzycj.util.FileUtil;
import com.hfzycj.util.Pager;
import freemarker.template.TemplateException;

/**
 * Attachment Controller
 */
@RestController
@RequestMapping("manage/attachment")
public class AttachmentController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p) {
		ModelAndView mav = new ModelAndView();
		Pager pager = new Pager();
		pager.setPageNo(p);
		int count = attachmentService.findAllCount(attachment, "");
		pager.setTotalCount(count);
		mav.addObject("list", attachmentService.findByPager(pager, "", "attachment_location, attachment_orderby"));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("active", "attachment");
		mav.setViewName("attachment/AttachmentList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param attachmentId
	 * @return
	 */
	@RequestMapping("edit/{attachmentId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "attachmentId") int attachmentId) {
		if (0 == attachmentId) {
			attachment = new Attachment();
			attachment.setAttachment_id(attachmentId);
		} else {
			attachment = attachmentService.findById(Long.valueOf(attachmentId));
		}
		return new ModelAndView("attachment/AttachmentEdit", "attachment", attachment);
	}

	/**
	 * Save
	 * 
	 * @param attachment
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("attachment") @Valid Attachment attachment, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ModelAndView("attachment/AttachmentEdit", "attachment", attachment);
		}

		if (0 == attachment.getAttachment_id()) {
			attachmentService.save(attachment);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			attachmentService.update(attachment);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("attachmentList", attachmentService.findAllList("", "AttachmentOrderby", null));
		map.put("request", request);
		try {
			generateHTML(map, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/manage/attachment");
	}

	/**
	 * Generate HTML File
	 * 
	 * @param map
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
		FileUtil.generateHTML("WEB-INF/ftl/site", "Attachment.ftl", "attachment.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
	}

}
