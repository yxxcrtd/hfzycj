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
 * Menu Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Menu implements Serializable {

    /**
     * Menu ID
     */
    private int menu_id;
    /**
     * Menu menu_pid
     */
    @Range(min = 1, max = Integer.MAX_VALUE, message = "errors.invalid.range")
    private int menu_parentid;

    /**
     * menu 标题
     */
//    @NotBlank
    @Length(min = 1, max = 20, message = "{system.message.length.error}")
    private String menu_title;

    /**
     * menu 菜单地址
     */
    @Length(min = 1, max = 200, message = "{system.message.length.error}")
    private String menu_url;

    /**
     * menu 菜单打开方式
     */
    @NotBlank
    private String menu_target;

    /**
     * menu 菜单描述
     */
    private String menu_describe;

    /**
     * menu menu_state状态（0：禁用，1：启用）
     */
    private int menu_status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date menu_createtime = new Date();

    /**
     * 操作员ID
     */
    private int menu_userid;

    /**
     * 排序
     */
    private int menu_orderby;

}
