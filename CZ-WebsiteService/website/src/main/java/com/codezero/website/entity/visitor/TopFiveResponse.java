package com.codezero.website.entity.visitor;

import lombok.Data;

@Data
public class TopFiveResponse {
    /**
     * 访客ip
     * @mock 127.0.0.1
     */
    private String ip;

    /**
     * 访客地址
     * @mock 中国 辽宁 沈阳
     */
    private String city;

    /**
     * 访问次数
     * @mock 100
     */
    private int total;
}
