package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Attachment Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Attachment implements Serializable {

    /**
     * AttachmentID
     */
    private long attachment_id;

    /**
     * AttachmentObjID 附件的对象ID
     */
    private long attachment_obj_id;

    /**
     * AttachmentName 附件原始名称
     */
    @NotBlank
    @Length(min = 1, max = 32, message = "{system.message.length.error}")
    private String attachment_name;

    /**
     * AttachmentUrl 附件的保存路径
     */
    private String attachment_url;

    /**
     * AttachmentCreateTime 附件创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date attachment_create_time = new Date();

}
