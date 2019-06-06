package com.hfzycj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by lili on 2017/2/14.
 * Relation  Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Relation implements Serializable {

    /**
     * RelationId
     */
    private int relation_id;

    /**
     * RelationCommunalId
     */
    private int relation_communal_id;

    /**
     * RelationParkId
     */
    private int relation_park_id;

}
