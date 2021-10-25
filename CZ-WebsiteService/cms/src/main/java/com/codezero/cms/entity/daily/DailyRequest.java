package com.codezero.cms.entity.daily;

import lombok.Data;

@Data
public class DailyRequest {
    /**
     * 日常ID
     * @mock jdklf-fdadf-1231df-dfsd
     */
    private String seq;
    /**
     * 日常内容
     * @mock 今天天气真好
     */
    private String content;
    /**
     * 配图Id
     * @mock dfd1231231dfsd
     */
    private String imageId;
    /**
     * 状态
     * @mock 1
     */
    private String status;
    /**
     * 时间
     * @mock 2021-10-22 13:12:33
     */
    private String date;
}
