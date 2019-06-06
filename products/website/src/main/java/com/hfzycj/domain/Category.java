package com.hfzycj.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Category Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Category implements Serializable {

	/**
	 * CategoryID
	 */
	private int category_id;

	/**
	 * CategoryName 名称
	 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String category_name;

	/**
	 * CategoryStatus 状态（默认0显示；1不显示）
	 */
	private int category_status;

}
