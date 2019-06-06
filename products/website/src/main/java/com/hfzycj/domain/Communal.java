package com.hfzycj.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Communal Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Communal extends BaseDomain {

    /**
     * CommunalID
     */
    private int communal_id;

    @Range(min = 1, max = Integer.MAX_VALUE, message = "errors.invalid.range")
    private int communal_menuid;
    /**
     * Communal
     */
    @NotBlank
    @Length(min = 1, max = 128, message = "{system.message.length.error}")
    private String communal_name;

    /**
     * Communal
     */
    @NotBlank
    @Length(min = 1, max = 128, message = "{system.message.length.error}")
    private String communal_address;

    /**
     * Communal
     */
    @NotBlank
    @Length(min = 1, max = 128, message = "{system.message.length.error}")
    private String communal_coordinate_x;

    @NotBlank
    @Length(min = 1, max = 128, message = "{system.message.length.error}")
    private String communal_coordinate_y;

    /**
     * Communal
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date communal_createtime;

//    @Range(min = 1, max = Integer.MAX_VALUE, message = "errors.invalid.range")
    private int communal_userid;
    /**
     * communal_status（0：禁用，1：启用）
     */
    @Range(min = 0, max = 1, message = "errors.invalid.range")
    private int communal_status;
    @Range(min = 0, max = 128, message = "errors.invalid.range")
    private int communal_orderby;
}