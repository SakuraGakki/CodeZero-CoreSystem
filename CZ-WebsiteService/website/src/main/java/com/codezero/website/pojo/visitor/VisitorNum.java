package com.codezero.website.pojo.visitor;

import lombok.Data;

@Data
public class VisitorNum {
    /**
     * 序号
     */
    private Integer seq;
    /**
     * 访问次数
     */
    private Integer total;
    /**
     * 访问类型：0：总计，1：单日计算
     */
    private String type;
    /**
     * 日期
     */
    private String date;
}
