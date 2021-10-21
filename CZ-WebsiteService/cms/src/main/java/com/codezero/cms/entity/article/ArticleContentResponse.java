package com.codezero.cms.entity.article;

import lombok.Data;

@Data
public class ArticleContentResponse {
    /**
     * 文章ID
     * @mock 0439012-4jkljkl3123-1231231
     */
    private String articleId;
    /**
     * 文章内容
     * @mock <Type>LongText
     */
    private String content;
}
