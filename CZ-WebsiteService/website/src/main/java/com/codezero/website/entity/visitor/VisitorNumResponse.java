package com.codezero.website.entity.visitor;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VisitorNumResponse {
    /**
     * 总访客数
     * @mock 100
     */
    private BigDecimal total;
    /**
     * 今日访客数
     * @mock 10
     */
    private BigDecimal today;
}
