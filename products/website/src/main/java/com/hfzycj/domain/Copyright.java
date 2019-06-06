package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Copyright Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Copyright implements Serializable {

	/**
	 * CopyrightID
	 */
	private long copyright_id;

	/**
	 * CopyrightOwner
	 */
	@NotBlank
	private String copyright_content;

}
