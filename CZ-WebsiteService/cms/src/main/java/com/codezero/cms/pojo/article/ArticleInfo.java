package com.codezero.cms.pojo.article;

import lombok.Data;

@Data
public class ArticleInfo {
    /**
     * 文章Id
     */
    private String articleId;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 文章标签
     */
    private String articleTag;
    /**
     * 阅读数
     */
    private Integer readNum;
    /**
     * 发布时间
     */
    private String date;
    /**
     * 状态
     */
    private String status;
}
