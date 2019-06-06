package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Information Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Information implements Serializable {

    /**
     * InformationID
     */
    private long information_id;

    /**
     * InformationType 类型（新闻资讯、通知公告、政策法规等）
     */
    @NotBlank
    private String information_type;

    /**
     * InformationRule 法规类型（0：地方法规；1：国家法规等）
     */
    private int information_rule;

    /**
     * InformationTitle 标题
     */
    @NotBlank
    @Length(min = 1, max = 64, message = "{system.message.length.error}")
    private String information_title;

    /**
     * InformationSummary 摘要
     */
    @Length(min = 1, max = 256, message = "{system.message.length.error}")
    private String information_summary;

    /**
     * InformationContent 内容
     */
    @NotBlank
    private String information_content;

    /**
     * InformationOrderBy 置顶排序
     */
    @Range(min = 0, max = 999, message = "{system.message.value.error}")
    private int information_order_by;

    /**
     * InformationStatus 状态（0：显示；1：不显示）
     */
    private int information_status;

    /**
     * InformationHit 点击量
     */
    private int information_hit;

    /**
     * InformationAttachmentName 附件原始名称
     */
    private String information_attachment_name;

    /**
     * InformationAttachmentUrl 附件地址
     */
    private String information_attachment_url;

    /**
     * InformationCreateTime 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date information_create_time = new Date();

}
