package com.codezero.website.entity.visitor;

import lombok.Data;

@Data
public class VisitorNumRequest {
    /**
     * 客户端ip
     */
    private String ip;
    /**
     * 客户端所在地及网络运营商
     */
    private String city;
}
