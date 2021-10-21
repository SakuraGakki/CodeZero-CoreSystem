package com.codezero.cms.entity.article;

import lombok.Data;

@Data
public class ArticleRequest {
    /**
     * 文章ID
     * @mock 0439012-4jkljkl3123-1231231
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
     * 文章内容
     * @mock <Type>LongText
     */
    private String content;
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
