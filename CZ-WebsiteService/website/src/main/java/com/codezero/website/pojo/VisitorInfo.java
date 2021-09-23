package com.codezero.website.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class VisitorInfo {
    /**
     * 序号
     */
    private Integer seq;
    /**
     * 客户端ip
     */
    private String ip;
    /**
     * 客户端所在地及网络运营商
     */
    private String city;
    /**
     * 最后一次访问时间
     */
    private String lastTime;
    /**
     * 总访问次数
     */
    private Integer visitorNum;
}
