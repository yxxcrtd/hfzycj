package com.hfzycj.repository;

import com.hfzycj.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

    // 前6条新闻
    List<News> findTop6ByOrderByIdDesc();

    // 所有type=？的新闻
    List<News> findByTypeOrderByIdDesc(int type);

}
