package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Feedback Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Feedback implements Serializable {

    /**
     * FeedbackID
     */
    private int feedback_id;

    /**
     * FeedbackName
     */
    @Length(min = 1, max = 32, message = "{system.message.length.error}")
    private String feedback_name;

    /**
     * FeedbackTel
     */
    @Length(min = 1, max = 32, message = "{system.message.length.error}")
    private String feedback_tel;

    /**
     * FeedbackContent
     */
    private String feedback_content;

    /**
     * FeedbackReplyContent
     */
    private String feedback_reply_content;

    /**
     * FeedbackApproveStatus
     */
    private int feedback_approve_status;

    /**
     * FeedbackCategory
     */
    private int feedback_category;

    /**
     * FeedbackCreateTime
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date feedback_create_time = new Date();

}
