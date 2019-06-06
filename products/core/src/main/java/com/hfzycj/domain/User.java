package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * User Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

	/**
	 * 用户ID
	 */
	private long user_id;

	/**
	 * 用户名
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String user_name;

	/**
	 * 用户密码
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String user_password;

	/**
	 * 用户角色（系统默认是：1；其他内容用户都是：0；）
	 */
	private int user_role;
	
	/**
	 * 1，SEO权限
	 */
	private boolean user_seo;
	
}
