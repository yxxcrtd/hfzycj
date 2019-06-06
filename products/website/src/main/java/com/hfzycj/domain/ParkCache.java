package com.hfzycj.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParkCache implements Serializable {

    private int parkId;

    private int status;

    private String parkCode;

    private int surplus;

    private int total;

    private String name;

    private String address;

    private int countStatus;

}
