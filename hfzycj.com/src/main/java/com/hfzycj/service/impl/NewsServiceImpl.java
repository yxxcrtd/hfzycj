package com.hfzycj.service.impl;

import com.hfzycj.domain.News;
import com.hfzycj.repository.NewsRepository;
import com.hfzycj.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private static Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Resource
    private NewsRepository newsRepository;

    public News findById(Long id) {
        return newsRepository.findOne(id);
    }

    public Page<News> findByTitle(Pageable pageable, String title) {
        return newsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.like(root.get("title"), "%" + title + "%");
            return criteriaBuilder.and(predicate);
        }, pageable);
    }

    @Transactional
    public News save(News news) {
        return newsRepository.save(news);
    }

    public List<News> findTop6News() {
        return newsRepository.findTop6ByOrderByIdDesc();
    }

    public List<News> findByTypeOrderByIdDesc(int type) {
        return newsRepository.findByTypeOrderByIdDesc(type);
    }

    public String increase(Long id) {
        News news = null;
        try {
            news = findById(id);
            news.setHit(news.getHit() + 1);
            save(news);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(news.getHit());
    }

}
