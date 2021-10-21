package com.codezero.cms.pojo.article;

import lombok.Data;

@Data
public class ArticleContent {
    /**
     * 文章ID
     */
    private String articleId;
    /**
     * 文章内容
     */
    private String content;
}
