package com.codezero.website.entity.visitor;

import lombok.Data;

@Data
public class TenVisitorNumListResponse {
    /**
     * 日期
     * @mock 2021-09-09
     */
    private String date;
    /**
     * 访客数量
     * @mock 100
     */
    private int total;
}
