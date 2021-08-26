package com.codezero.website.entity.visitor;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VisitorNumResponse {
    private BigDecimal total;
    private BigDecimal today;
}
