package com.hfzycj.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "t_news")
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 0 - 公司新闻；1 - 行业动态
    private int type;

    @NotBlank
    @Length(min = 1, max = 64, message = "长度必须在{min}-{max}之间")
    private String title;

    @NotBlank
    private String content;

    private int hit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date = new Date();

}
