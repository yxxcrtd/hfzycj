package com.hfzycj.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Item Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Item implements Serializable {

    /**
     * itemID
     */
    private int item_id;

    /**
     * 投票id
     * itemVoteId
     */
    private int item_vote_id;

    /**
     * 投票选项主题
     * itemName
     */
    @Length(min = 1, max = 32, message = "{system.message.length.error}")
    private String item_name;

    /**
     * 投票选项的投票人数
     * itemVoteUserCounts
     */
    private int item_vote_user_counts;

    /**
     * 投票选项的下标
     * itemIndex
     */
    private int item_index;

}
