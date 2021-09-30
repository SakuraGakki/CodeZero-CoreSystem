package com.codezero.website.pojo.messageboard;

import lombok.Data;

@Data
public class MessageBoard {
    /**
     * 序号ID
     */
    private Integer seq;
    /**
     * 类型
     */
    private String msgType;
    /**
     * IP
     */
    private String ip;
    /**
     * 城市
     */
    private String city;
    /**
     * 留言时间
     */
    private String date;
    /**
     * 留言内容
     */
    private String content;
    /**
     * 状态
     */
    private String status;
    /**
     * 关联ID
     */
    private Integer relatedSeq;
}
