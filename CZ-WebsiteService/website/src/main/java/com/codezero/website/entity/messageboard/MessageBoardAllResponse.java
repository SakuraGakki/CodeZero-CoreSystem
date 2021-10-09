package com.codezero.website.entity.messageboard;

import lombok.Data;

import java.util.List;

@Data
public class MessageBoardAllResponse {
    /**
     * 留言id
     * @mock 1
     */
    private int id;
    /**
     * 留言类型
     * @mock 0
     */
    private String msgType;
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
    /**
     * 状态
     * @mock 0
     */
    private String status;
    /**
     * 关联id
     * @mock 0
     */
    private Integer relatedId;

}
