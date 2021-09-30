package com.codezero.website.entity.messageboard;

import lombok.Data;

@Data
public class MessageBoardSubResponse {
    /**
     * 留言id
     * @mock 1
     */
    private int id;
    /**
     * 留言ip
     * @mock 127.0.0.1
     */
    private String ip;
    /**
     * 留言地址
     * @mock 中国 辽宁 沈阳 联通
     */
    private String city;
    /**
     * 留言时间
     * @mock 2021-09-29 00:00:00
     */
    private String date;
    /**
     * 留言内容
     * @mock 哈哈哈
     */
    private String content;
}
