package com.codezero.website.pojo.messageboard;

import lombok.Data;

@Data
public class AllMessageBoard {
    /**
     * 检索内容
     */
    private String text;
    /**
     * 状态
     */
    private String status;
}
