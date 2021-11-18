package com.codezero.task.entity.news;

import lombok.Data;

@Data
public class NewsResponseBody {
    private Integer status;
    private String msg;
    private NewsResponseResult result;
}
