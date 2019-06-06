package com.tcc.park.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcc.park.api.common.SmsConfig;
import com.tcc.park.api.user.domain.NewsDetail;
import com.tcc.park.api.user.domain.NewsDetailExample;
import com.tcc.park.api.user.mybatis.NewsDetailMapper;
import com.tcc.park.api.util.DateUtil;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

	private static final Logger logger = Logger.getLogger(PageController.class);
	
	@Autowired
	private NewsDetailMapper newsDetailMapper;
	
	@RequestMapping("/api/page/1")
	public String registry() {
		return "1";
	}
	
	@RequestMapping("/api/page/list")
	public String list(HttpServletRequest request) {
		NewsDetailExample example = new NewsDetailExample();
		example.setOrderByClause(" up_time desc");
		example.or().andStatusEqualTo(1);
		List<NewsDetail> lists = newsDetailMapper.selectByExample(example);
		for (NewsDetail newsDetail : lists) {
			if (StringUtils.isNotBlank(newsDetail.getImgUrl())) {
				newsDetail.setImgUrl(SmsConfig.IMGURL+newsDetail.getImgUrl());
			}
			newsDetail.setNewsFrom(DateUtil.getDateStringByFormat(newsDetail.getUpTime(), null)+" "+newsDetail.getNewsFrom());
			newsDetail.setDetailUrl(SmsConfig.IMGURL+newsDetail.getDetailUrl());
		}
		request.setAttribute("allElems", lists);
		logger.info("listpage");
		return "list";
	}


	@RequestMapping("/api/pageList")
	public @ResponseBody
	String pageList(HttpServletRequest request) {
		NewsDetailExample example = new NewsDetailExample();
		example.setOrderByClause(" up_time desc");
		example.or().andStatusEqualTo(1);
		List<NewsDetail> lists = newsDetailMapper.selectByExample(example);
		for (NewsDetail newsDetail : lists) {
			if (StringUtils.isNotBlank(newsDetail.getImgUrl())) {
				newsDetail.setImgUrl(SmsConfig.IMGURL+newsDetail.getImgUrl());
			}
			newsDetail.setNewsFrom(DateUtil.getDateStringByFormat(newsDetail.getUpTime(), null)+" "+newsDetail.getNewsFrom());
			newsDetail.setDetailUrl(SmsConfig.IMGURL+newsDetail.getDetailUrl());
		}
		return com.alibaba.fastjson.JSONArray.toJSONString(lists);
	}
}
