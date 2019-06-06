package com.hfzycj.controller;

import com.hfzycj.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Generate Code Controller
 */
@RestController
@RequestMapping("code")
public class _GenerateCodeController extends BaseController {

	/**
	 * Code Generate
	 */
	@RequestMapping(value = "{object}", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public String code(@PathVariable(value = "object") String object, HttpServletRequest request) throws Exception {
		String returnString = "<p>您输入的单词是：<span style='color: #FF0000;'>" + object + "</span></p>";
		
		Map<String, Object> map = new HashMap<>();
		object = StringUtils.capitalize(object);
		map.put("obj", object);
		map.put("request", request);
		map.put("packageName", PACKAGE_NAME);
		
		// 1，生成 domain 对象
		FileUtil.generateHTML("WEB-INF/ftl/_code", "Object.ftl", object + ".java", map, request.getSession().getServletContext(), CODE_PATH_JAVA + File.separator + "domain" + File.separator);
		returnString += "<p>" + CODE_PATH_JAVA + "domain" + File.separator + "<span style='color: #FF0000;'>" + object + ".java</span> 生成成功！</p>";
		
		// 2，生成 Object 的 Controller
		FileUtil.generateHTML("WEB-INF/ftl/_code", "ObjectController.ftl", object + "Controller.java", map, request.getSession().getServletContext(), CODE_PATH_JAVA + File.separator + "controller" + File.separator);
		returnString += "<p>" + CODE_PATH_JAVA + "service" + File.separator + "<span style='color: #FF0000;'>" + object + "Controller.java</span> 生成成功！</p>";
		
		// 3，生成 Object 的 Sercie 接口
		FileUtil.generateHTML("WEB-INF/ftl/_code", "ObjectService.ftl", object + "Service.java", map, request.getSession().getServletContext(), CODE_PATH_JAVA + File.separator + "service" + File.separator);
		returnString += "<p>" + CODE_PATH_JAVA + "service" + File.separator + "<span style='color: #FF0000;'>" + object + "Service.java</span> 生成成功！</p>";
		
		// 4，生成 Object 的 Sercie Implement 接口
		FileUtil.generateHTML("WEB-INF/ftl/_code", "ObjectServiceImpl.ftl", object + "ServiceImpl.java", map, request.getSession().getServletContext(), CODE_PATH_JAVA + File.separator + "service" + File.separator + "impl" + File.separator);
		returnString += "<p>" + CODE_PATH_JAVA + "service" + File.separator + "impl" + File.separator + "<span style='color: #FF0000;'>" + object + "ServiceImpl.java</span> 生成成功！</p>";
		
		// 5，生成 Object 的 Dao 接口
		FileUtil.generateHTML("WEB-INF/ftl/_code", "ObjectDao.ftl", object + "Dao.java", map, request.getSession().getServletContext(), CODE_PATH_JAVA + File.separator + "dao" + File.separator);
		returnString += "<p>" + CODE_PATH_JAVA + "dao" + File.separator + "<span style='color: #FF0000;'>" + object + "Dao.java</span> 生成成功！</p>";
		
		// 6，生成 Object 的 Dao Implement 接口
		FileUtil.generateHTML("WEB-INF/ftl/_code", "ObjectDaoImpl.ftl", object + "DaoImpl.java", map, request.getSession().getServletContext(), CODE_PATH_JAVA + File.separator + "dao" + File.separator + "impl" + File.separator);
		returnString += "<p>" + CODE_PATH_JAVA + "dao" + File.separator + "impl" + File.separator + "<span style='color: #FF0000;'>"+ object + "DaoImpl.java</span> 生成成功！</p>";
		
		// 7，生成 Object 的 列表ftl
		FileUtil.generateHTML("WEB-INF/ftl/_code", "ObjectList.ftl", object + "List.ftl", map, request.getSession().getServletContext(), CODE_PATH_FTL + File.separator + StringUtils.uncapitalize(object) + File.separator);
		returnString += "<p>" + CODE_PATH_FTL + StringUtils.uncapitalize(object) + File.separator + "<span style='color: #FF0000;'>"+ object + "List.ftl</span> 生成成功！</p>";
		
		// 8，生成 Object 的  编辑ftl
		FileUtil.generateHTML("WEB-INF/ftl/_code", "ObjectEdit.ftl", object + "Edit.ftl", map, request.getSession().getServletContext(), CODE_PATH_FTL + File.separator + StringUtils.uncapitalize(object) + File.separator);
		returnString += "<p>" + CODE_PATH_FTL + StringUtils.uncapitalize(object) + File.separator + "<span style='color: #FF0000;'>"+ object + "Edit.ftl</span> 生成成功！</p>";
		
		return returnString;
	}

}
