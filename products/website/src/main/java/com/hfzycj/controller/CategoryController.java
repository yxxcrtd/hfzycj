package com.hfzycj.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hfzycj.domain.Category;
import com.hfzycj.util.FileUtil;
import com.hfzycj.util.Pager;
import freemarker.template.TemplateException;

/**
 * Category Controller
 */
@Controller
@RequestMapping("manage/category")
public class CategoryController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", defaultValue = "1") int p,
							 @RequestParam(value = "k", required = false) String k,
							 @RequestParam(value = "s1", required = false) String s1) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("k", k);
		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : "category_name like " + like;
		mav.addObject("s1", s1);
		if (null != s1 && !"".equals(s1)) {
			k += " AND category_status = " + s1;
		}
		Pager pager = new Pager();
		pager.setPageNo(p);
		int count = categoryService.findAllCount(category, k);
		pager.setTotalCount(count);
		mav.addObject("list", categoryService.findByPager(pager, k, ""));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("status", null == s1 ? "" : s1); // 列表页面中首先显示全部
		mav.addObject("active", "category");
		mav.setViewName("category/CategoryList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("edit/{categoryId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "categoryId") int categoryId) {
		if (0 == categoryId) {
			category = new Category();
			category.setCategory_id(categoryId);
		} else {
			category = categoryService.findById(categoryId);
		}
		return new ModelAndView("category/CategoryEdit", "category", category);
	}

	/**
	 * Save
	 * 
	 * @param category
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("category") @Valid Category category, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ModelAndView("category/CategoryEdit", "category", category);
		}

		if (0 == category.getCategory_id()) {
			categoryService.save(category);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			categoryService.update(category);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryList", categoryService.findAllList("", "", null));
		map.put("request", request);
		try {
			generateHTML(map, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/manage/category");
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
		FileUtil.generateHTML("WEB-INF/ftl/site", "Category.ftl", "category.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
	}

}
