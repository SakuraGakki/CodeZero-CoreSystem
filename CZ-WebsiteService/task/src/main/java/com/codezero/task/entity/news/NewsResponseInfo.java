package com.codezero.task.entity.news;

import lombok.Data;

@Data
public class NewsResponseInfo {
    private String title;
    private String time;
    private String src;
    private String category;
    private String pic;
    private String url;
    private String weburl;
    private String content;
}
