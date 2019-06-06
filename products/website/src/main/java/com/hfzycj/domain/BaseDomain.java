package com.hfzycj.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDomain implements Serializable {

    private Communal communal;

    private ParkCommunal parkCommunal;

}
