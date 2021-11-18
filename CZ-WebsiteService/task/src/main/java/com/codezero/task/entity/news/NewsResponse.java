package com.codezero.task.entity.news;

import lombok.Data;

@Data
public class NewsResponse {
    private String code;
    private Boolean charge;
    private String msg;
    private NewsResponseBody result;
}
