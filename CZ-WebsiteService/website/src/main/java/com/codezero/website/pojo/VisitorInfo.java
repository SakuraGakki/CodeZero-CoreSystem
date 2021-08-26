package com.codezero.website.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class VisitorInfo {
    private Integer seq;
    private String ip;
    private String city;
    private String lastTime;
}
