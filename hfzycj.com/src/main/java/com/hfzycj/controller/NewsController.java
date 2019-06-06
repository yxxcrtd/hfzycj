package com.hfzycj.controller;

import com.alibaba.fastjson.JSONObject;
import com.hfzycj.domain.News;
import com.hfzycj.utils.FileUtil;
import com.hfzycj.utils.Pager;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.domain.Sort.Order;

@RestController
@EnableAutoConfiguration
@RequestMapping("manage/news")
public class NewsController extends BaseController {

    @GetMapping("")
    ModelAndView list(@RequestParam(value = "p", defaultValue = "1") int p, @RequestParam(value = "k", defaultValue = "", required = false) String k) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("k", k);
        Sort sort = new Sort(new Order(DESC, "date"), new Order(DESC, "id"));
        Pageable pageable = new PageRequest(p - 1, 15, sort);
        Page<News> newsPages = newsService.findByTitle(pageable, k);
        pager = new Pager();
        pager.setPageNo(p);
        long count = newsPages.getTotalElements();
        pager.setTotalCount(count);
        mav.addObject("count", count);
        mav.addObject("pager", pager);
        mav.addObject("list", newsPages.getContent());
        mav.addObject("active", "news");
        mav.addObject("newsTypeMap", getNewsTypeMap());
        mav.setViewName("news/NewsList");
        return mav;
    }
//    @GetMapping("getList")
//    Page<News> getNewsByPageable(@RequestParam(value = "p", defaultValue = "0") int p, @RequestParam(value = "k", defaultValue = "", required = false) String k) {
//        Sort sort = new Sort(new Order(DESC, "orderBy"), new Order(DESC, "id"));
//        Pageable pageable = new PageRequest(p, 15, sort);
//        return newsService.findByTitle(pageable, k);
//    }
//    public Page<News> getEntryByPageable(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
//        return newsService.getNewsByPageable(pageable);
//    }

    /**
     * 编辑
     */
    @GetMapping("edit/{id:[\\d]+}")
    ModelAndView edit(@PathVariable(value = "id") int id) {
        ModelAndView mav = new ModelAndView();
        if (0 == id) {
            news = new News();
            news.setId(0L);
        } else {
            news = newsService.findById(Long.valueOf(id));
        }
        mav.addObject("news", null == news ? new News() : news);
        mav.addObject("active", "news");
        mav.addObject("newsTypeMap", getNewsTypeMap());
        mav.setViewName("news/NewsEdit");
        return mav;
    }

    /**
     * 保存
     */
    @PostMapping("save")
    ModelAndView save(@ModelAttribute("news") @Valid News news, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("news", news);
            mav.addObject("active", "news");
            mav.setViewName("news/newsEdit");
            return mav;
        }

        try {
            News newNews = newsService.save(news);
            redirectAttributes.addFlashAttribute("tips", "保存成功！");

            // 生成静态文件
            Map<String, Object> map = new HashMap<>();
            map.put("news", newNews);
            map.put("top6NewsList", newsService.findTop6News());
            map.put("allTypeNewsList", newsService.findByTypeOrderByIdDesc(newNews.getType()));
            generateNewsHTML(map, newNews.getId(), newNews.getType());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ModelAndView("redirect:/manage/news");
    }

    /**
     * 图片上传
     */
    @PostMapping("uploadImage")
    String upload(MultipartHttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String imageName = "";
        List<MultipartFile> files = request.getFiles("imgFile");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                imageName = FileUtil.uploadFile(file, UPLOAD_PATH, false);
                log.info("上传图片的全路径是：{}", UPLOAD_PATH + imageName);
            }
        }
        jsonObject.put("error", 0);
        jsonObject.put("url", new StringBuffer().append("/upload/").append(imageName).toString());
        return jsonObject.toJSONString();
    }

}
