package com.codezero.cms.pojo.daily;

import lombok.Data;

@Data
public class DailyInfo {
    /**
     * 日常ID
     */
    private String seq;
    /**
     * 日常内容
     */
    private String content;
    /**
     * 配图Id
     */
    private String imageId;
    /**
     * 状态
     */
    private String status;
    /**
     * 时间
     */
    private String date;
}
