package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Log Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Log implements Serializable {

	/**
	 * LogID
	 */
	private long log_id;

	/**
	 * Log 模块名称
	 */
	private String log_model;

	/**
	 * Log 模块操作的描述
	 */
	private String log_description;

	/**
	 * Log 操作的用户ID
	 */
	private long log_user_id;

	/**
	 * Log 操作的用户登录名
	 */
	private String log_user_name;

	/**
	 * Log 操作的IP地址
	 */
	private String log_ip;

	/**
	 * Log 访问路径
	 */
	private String log_url;

	/**
	 * Log 访问方法
	 */
	private String log_method;

	/**
	 * Log 执行时间
	 */
	private long log_response_time;

	/**
	 * Log 访问详情
	 */
	private String log_detail;

	/**
	 * Log 操作时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date log_create_time;

}
