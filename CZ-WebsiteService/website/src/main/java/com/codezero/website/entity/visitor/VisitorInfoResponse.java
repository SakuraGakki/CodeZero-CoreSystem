package com.codezero.website.entity.visitor;

import lombok.Data;

@Data
public class VisitorInfoResponse {
    /**
     * IP
     * @mock 127.0.0.1
     */
    private String ip;

    /**
     * 城市
     * @mock 中国 辽宁 沈阳
     */
    private String city;

    /**
     * 访问次数
     * @mock 100
     */
    private int total;

    /**
     * 最后访问时间
     * @mock 2021-09-26 09:36:21
     */
    private String lastTime;


}
