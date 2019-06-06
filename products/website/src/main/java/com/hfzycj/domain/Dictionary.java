package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Dictionary Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Dictionary implements Serializable {
    /**
     * dictionary ID
     */
    private int dictionary_id;

    /**
     * dictionary KEY
     */
    @NotBlank
    @Length(min = 1, max = 20, message = "{message.length.error}")
    private String dictionary_key;

    /**
     * dictionary VALUE
     */
    @NotBlank
    @Length(min = 1, max = 200, message = "{message.length.error}")
    private String dictionary_value;

    /**
     * dictionary 描述
     */
    @Length(max = 128, message = "{message.length.error}")
    private String dictionary_describe;

    /**
     * dictionary 状态
     */
    private int dictionary_state;

}
