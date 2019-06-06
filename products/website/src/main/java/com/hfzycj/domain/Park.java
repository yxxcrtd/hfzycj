package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Park Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Park implements Serializable {

    /**
     * ParkID
     */
    private int park_id;

    /**
     * ParkName
     */
    @Length(min = 1, max = 32, message = "{system.message.length.error}")
    private String park_name;

    /**
     * ParkCode
     */
    @Length(min = 1, max = 32, message = "{system.message.length.error}")
    private String park_code;

    /**
     * ParkTotal
     */
    @Range(min = 1, max = 999, message = "{system.message.value.error}")
    private int park_total;

    /**
     * ParkSurplus
     */
    private int park_surplus;

    /**
     * ParkCoordinateX
     */
    @Length(min = 1, max = 128, message = "{system.message.length.error}")
    private String park_coordinate_x;

    /**
     * ParkCoordinateY
     */
    @Length(min = 1, max = 128, message = "{system.message.length.error}")
    private String park_coordinate_y;

    /**
     * ParkType
     */
    private int park_type;

    /**
     * ParkStatus
     */
    private int park_status;

    /**
     * ParkSource
     */
    private int park_source;

    /**
     * ParkAddress
     */
    @Length(min = 1, max = 128, message = "{system.message.length.error}")
    private String park_address;

    /**
     * ParkChargeCode
     */
    @Length(min = 0, max = 32, message = "{system.message.length.error}")
    private String park_charge_code;

    /**
     * ParkDescribe
     */
    @Length(min = 1, max = 128, message = "{system.message.length.error}")
    private String park_describe;

    /**
     * ParkOperatingCompany
     */
    @Length(min = 0, max = 128, message = "{system.message.length.error}")
    private String park_operating_company;

    /**
     * ParkCreateTime
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date park_create_time;

    /**
     * ParkTel
     */
    @Length(min = 1, max = 32, message = "{system.message.length.error}")
    private String park_tel;

    /**
     * ParkContactName
     */
    @Length(min = 1, max = 32, message = "{system.message.length.error}")
    private String park_contact_name;

    /**
     * ParkUpdateTime
     */
    private Date park_update_time;

    /**
     * ParkLogo
     */
    private String park_logo;

}
