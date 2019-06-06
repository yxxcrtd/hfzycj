package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * Link Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Link implements Serializable {

	/**
	 * LinkID
	 */
	private long link_id;

	/**
	 * LinkName
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String link_name;

	/**
	 * LinkUrl
	 */
	@Length(min = 1, max = 128, message = "{system.message.length.error}")
	private String link_url;

	/**
	 * LinkLogo
	 */
	private String link_logo;

	/**
	 * LinkStatus（默认0显示；1不显示）
	 */
	private int link_status;

	/**
	 * LinkOrderBy
	 */
	@Range(min = 1, max = 32, message = "{system.message.value.error}")
	private int link_order_by;

}
