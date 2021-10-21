package com.codezero.cms.entity.article;

import lombok.Data;

@Data
public class ArticleInfoResponse {
    /**
     * 文章ID
     * @mock 2931-djfk1-23112123jkldkfs
     */
    private String articleId;
    /**
     * 文章标题
     * @mock 文章标题
     */
    private String articleTitle;
    /**
     * 文章标签
     * @mock "科技","软件开发","系统"
     */
    private String articleTag;
    /**
     * 阅读数
     * @mock 0
     */
    private Integer readNum;
    /**
     * 发布时间
     * @mock 2021-10-10 12:12:33
     */
    private String date;
    /**
     * 状态
     * @mock 0
     */
    private String status;
}
