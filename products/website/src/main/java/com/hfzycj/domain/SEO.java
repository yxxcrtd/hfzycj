package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * SEO Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SEO implements Serializable {

    /**
     * SEO ID
     */
    private long seo_id;

    /**
     * SEO 标题
     */
    @NotBlank
    @Length(min = 1, max = 32, message = "{message.length.error}")
    private String seo_title;

    /**
     * SEO 关键词
     */
    @NotBlank
    @Length(min = 1, max = 128, message = "{message.length.error}")
    private String seo_keywords;

    /**
     * SEO 描述
     */
    @Length(max = 128, message = "{message.length.error}")
    private String seo_describe;

    /**
     * SEO ICON
     */
    private String seo_icon;

}
