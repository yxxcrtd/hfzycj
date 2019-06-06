package com.hfzycj.service;

import com.hfzycj.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {

    News findById(Long id);

    Page<News> findByTitle(Pageable pageable, String title);

    News save(News news);

    List<News> findTop6News();

    List<News> findByTypeOrderByIdDesc(int type);

    String increase(Long id);

}
