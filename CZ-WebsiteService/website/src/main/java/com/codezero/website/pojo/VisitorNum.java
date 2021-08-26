package com.codezero.website.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class VisitorNum {
    private Integer seq;
    private Integer total;
    private String type;
    private String date;
}
