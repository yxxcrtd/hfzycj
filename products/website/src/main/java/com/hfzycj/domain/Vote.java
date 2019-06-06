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
 * Vote Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Vote implements Serializable {

	/**
	 * VoteID
	 */
	private int vote_id;

	/**
	 * 投票主题
	 * VoteTitle
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String vote_title;

	/**
	 * 投票说明
	 * VoteDescription
	 */
	@Length(min = 1, max = 256, message = "{system.message.length.error}")
	private String vote_description;

	/**
	 * 选择模式（0:单选；1：多选）
	 * VoteType
	 */
	private int vote_type;

	/**
	 * 投票状态 (0:显示；1：隐藏)
	 * VoteStatus
	 */
	private int vote_status;

	/**
	 * 投票人数
	 * VoteUserCounts
	 */
	private int vote_user_counts;

	/**
	 * 发起投票时间
	 * VoteCreateTime
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date vote_create_time;

	/**
	 * 投票截止时间(截止时间到分)
	 * VoteEndTime
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date vote_end_time;

}
