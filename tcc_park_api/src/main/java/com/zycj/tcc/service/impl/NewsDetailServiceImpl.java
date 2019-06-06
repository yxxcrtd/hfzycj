/**
 * 
 */
package com.zycj.tcc.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycj.tcc.common.Constants;
import com.zycj.tcc.common.NewsConfig;
import com.zycj.tcc.dao.NewsDetailMapper;
import com.zycj.tcc.domain.NewsDetail;
import com.zycj.tcc.domain.criteria.NewsDetailCriteria;
import com.zycj.tcc.domain.vo.LogSystemVo;
import com.zycj.tcc.domain.vo.NewsDetailVo;
import com.zycj.tcc.domain.vo.SysAdminVo;
import com.zycj.tcc.exception.TccException;
import com.zycj.tcc.service.LogService;
import com.zycj.tcc.service.NewsDetailService;

/**
 * Title: NewsDetailServiceImpl.java Description: 城泊二期 Company: zycj
 * 
 * @author wangy
 * @date 2015年4月9日
 */
@Service("NewsDetailService")
public class NewsDetailServiceImpl implements NewsDetailService {
	private static final Logger logger = LoggerFactory
			.getLogger(SysAdminServiceImpl.class);
	@Autowired
	private NewsDetailMapper newsMapper;
	@Autowired
	private LogService logService;

	@Override
	public List<NewsDetailVo> list(SysAdminVo currAdmin,
			NewsDetailCriteria criteria) {
		logger.info("newsDetail list" + criteria);
		// 获取 总记录数
		int count = newsMapper.count(criteria);
		criteria.setCount(count);
		if (count > 0) {
			criteria.setCurrentPage(criteria.getPage());
			return newsMapper.list(criteria);
		}
		return null;
	}

	@Override
	public void add(SysAdminVo currAdmin, NewsDetailVo news)
			throws TccException, Exception {
		// 基本参数校验
		if (null == news) {
			throw new TccException("资讯数据不完整，操作失败");
		}
		if (StringUtils.isBlank(news.getTitle())) {
			throw new TccException("标题不能为空，操作失败");
		}
		if (StringUtils.isBlank(news.getBrief())) {
			throw new TccException("简介不能为空，操作失败");
		}
		if (StringUtils.isBlank(news.getUpTimeStr())) {
			throw new TccException("发布日期不能为空，操作失败");
		}
		if (StringUtils.isBlank(news.getNewsFrom())) {
			throw new TccException("资讯来源不能为空，操作失败");
		}
		if (StringUtils.isBlank(news.getEditorValue())) {
			throw new TccException("资讯内容不能为空，操作失败");
		}
		Date date = new Date();
		String fileName = new SimpleDateFormat(
				Constants.UNIQUE_FILE_NAME_TIME_FORMATE).format(new Date());
		String path =NewsConfig.ROOT_PATH + NewsConfig.HTML_PATH + NewsConfig.SEPARATE_CHAR
				+ fileName + NewsConfig.HTML_FILE_SUFFIX;
		File f = new File(path);// 新建一个文件对象
		FileWriter fw;
		try {
			fw = new FileWriter(f);// 新建一个FileWriter
			String HtmlStr = getHtmlStr(news);
			fw.write(HtmlStr);// 将字符串写入到指定的路径下的文件中
			fw.close();

		} catch (IOException e) {
			throw new TccException("创建资讯静态页面出错，操作失败");
		}
		// 插入数据库数据
		news.setContent(news.getEditorValue());
		//保存相对路径
		news.setDetailUrl(NewsConfig.HTML_PATH + NewsConfig.SEPARATE_CHAR
				+ fileName + NewsConfig.HTML_FILE_SUFFIX);
		news.setStatus(Constants.NEWS_IS_NEW);
		// 设置上线日期
		news.setUpTime(new SimpleDateFormat(Constants.DEFAULT_DATE_FORMATE)
				.parse(news.getUpTimeStr()));
		news.setCreateTime(date);
		int rows = newsMapper.insert(news);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.ADD);
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("标题：").append(news.getTitle()).append("，简介：")
				.append(news.getBrief()).append("，发布日期：")
				.append(news.getUpTimeStr()).append("，来源：")
				.append(news.getNewsFrom());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}
	}

	private String getHtmlStr(NewsDetailVo news) throws TccException {
		String htmlModelStr = NewsConfig.HTML_MODEL_STR;
		if (StringUtils.isBlank(htmlModelStr)) {
			throw new TccException("静态HTML文件模板数据有误，操作失败");
		}
		String contentStr = news.getEditorValue();
		if (contentStr.indexOf("<img") != -1) {
			//获取获取第一张图片作为，该咨询的标题图片
			String imagePath = NewsConfig.IMAGE_PATH+StringUtils.substringBetween(contentStr,"ueditor/jsp/image","\"");
			news.setImgUrl(imagePath);
			String rootPath = StringUtils.substringBetween(contentStr,
					"src=\"", "/image");
			// 替换图片路径
			contentStr = contentStr.replaceAll(rootPath, "..");
			// 给图片添加样式
			contentStr = contentStr.replaceAll("<img", "<img" + ' ' + "class="
					+ '\'' + NewsConfig.IMAGE_CLASS + '\'');
		}
		// 判断是否有1级标题
		if (contentStr.indexOf("<h1") != -1) {
			contentStr = contentStr.replaceAll("<h1", "<h1" + ' ' + "class="
					+ '\'' + NewsConfig.H_CLASS + '\'');
			contentStr = contentStr.replaceAll("<h1", "<p");
			contentStr = contentStr.replaceAll("</h1>", "</p>");
		}
		// 替换 html title
		htmlModelStr = htmlModelStr
				.replaceAll(NewsConfig.HTML_TITLE, NewsConfig.HTML_TITLE_NAME)
				.replaceAll(NewsConfig.NEWS_TITLE, news.getTitle())
				.replaceAll(NewsConfig.NEWS_UP_TIME, news.getUpTimeStr())
				.replaceAll(NewsConfig.NEWS_FROM, news.getNewsFrom())
				.replaceAll(NewsConfig.NEWS_CONTENT, contentStr);
		return htmlModelStr;
	}

	@Override
	public void online(SysAdminVo currAdmin, Integer id) throws TccException,
			Exception {
		if (null == id) {
			throw new TccException("资讯数据不完整，操作失败");
		}
		NewsDetail news = newsMapper.selectByPrimaryKey(id);
		if (null == news) {
			throw new TccException("资讯信息不存在，操作失败");
		}
		int oldStatus = news.getStatus();
		// 只有新生成的和已下限的资讯才可以执行上线操作
		if (Constants.NEWS_IS_ONLINE == oldStatus) {
			throw new TccException("资讯已经处于上线状态，操作失败");
		}
		// 设置 资讯状态
		news.setStatus(Constants.NEWS_IS_ONLINE);
		news.setModifyTime(new Date());
		int rows = newsMapper.updateByPrimaryKeySelective(news);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.UPDATE_STATUS);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("标题：").append(news.getTitle()).append("，状态：");
		if (Constants.NEWS_IS_NEW == oldStatus) {
			valueBeforeBuff.append("新生成");
		}
		if (Constants.NEWS_IS_OUTLINE == oldStatus) {
			valueBeforeBuff.append("下线");
		}
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("标题：").append(news.getTitle()).append("，状态：上线");
		log.setValueBefore(valueBeforeBuff.toString());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}
	}

	@Override
	public void outline(SysAdminVo currAdmin, Integer id) throws TccException,
			Exception {
		if (null == id) {
			throw new TccException("资讯数据不完整，操作失败");
		}
		NewsDetail news = newsMapper.selectByPrimaryKey(id);
		if (null == news) {
			throw new TccException("资讯信息不存在，操作失败");
		}
		// 只有新生成的和已下限的资讯才可以执行上线操作
		if (Constants.NEWS_IS_ONLINE != news.getStatus()) {
			throw new TccException("只有处于上线状态的资讯才允许下线操作，操作失败");
		}
		// 设置 资讯状态
		news.setStatus(Constants.NEWS_IS_OUTLINE);
		news.setModifyTime(new Date());;
		int rows = newsMapper.updateByPrimaryKeySelective(news);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.UPDATE_STATUS);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("标题：").append(news.getTitle()).append("，状态：上线");
		StringBuffer valueAfterBuff = new StringBuffer();
		valueAfterBuff.append("标题：").append(news.getTitle()).append("，状态：下线");
		log.setValueBefore(valueBeforeBuff.toString());
		log.setValueAfter(valueAfterBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}

	}

	@Override
	public void delete(SysAdminVo currAdmin, Integer id) throws TccException,
			Exception {
		if (null == id) {
			throw new TccException("资讯数据不完整，操作失败");
		}
		NewsDetail news = newsMapper.selectByPrimaryKey(id);
		if (null == news) {
			throw new TccException("资讯信息不存在，操作失败");
		}
		// 只有新生成的和已下限的资讯才可以执行上线操作
		if (Constants.NEWS_IS_ONLINE == news.getStatus()) {
			throw new TccException("处于上线状态的资讯不允许删除，操作失败");
		}
		// 设置 资讯状态
		int rows = newsMapper.deleteByPrimaryKey(id);
		if (Constants.ONE_RETURN_ROWS != rows) {
			throw new TccException("操作数据库出错，操作失败");
		}
		// 记录操作日志
		LogSystemVo log = new LogSystemVo();
		log.setOperateType(Constants.DELETE);
		StringBuffer valueBeforeBuff = new StringBuffer();
		valueBeforeBuff.append("标题：").append(news.getTitle());
		log.setValueBefore(valueBeforeBuff.toString());
		try {
			logService.add(currAdmin, log);
		} catch (Exception e) {
			throw new TccException("记录操作日志出错，操作失败");
		}
	}

	@Override
	public String getHtmlSrc(SysAdminVo currAdmin, Integer id)
			throws TccException, Exception {
		NewsDetail news = newsMapper.selectByPrimaryKey(id);
		return NewsConfig.HTML_SRC+news.getDetailUrl();
	};
}