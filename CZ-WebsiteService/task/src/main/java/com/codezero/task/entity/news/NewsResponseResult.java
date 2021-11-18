package com.codezero.task.entity.news;

import lombok.Data;

import java.util.List;

@Data
public class NewsResponseResult {
    private String channel;
    private Integer num;
    private List<NewsResponseInfo> list;
}
