package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Logo Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Logo implements Serializable {

    /**
     * LogoID
     */
    private long logo_id;

    /**
     * LogoUrl Logo地址
     */
    @Length(min = 1, max = 128, message = "{message.length.error}")
    private String logo_url;

    /**
     * LogoTitle Logo标题
     */
    @NotBlank
    @Length(min = 1, max = 32, message = "{message.length.error}")
    private String logo_title;

    /**
     * LogoLogo Logo图片
     */
    private String logo_logo;

}
