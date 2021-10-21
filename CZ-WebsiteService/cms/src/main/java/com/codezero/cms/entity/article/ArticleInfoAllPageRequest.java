package com.codezero.cms.entity.article;

import lombok.Data;

@Data
public class ArticleInfoAllPageRequest {
    private Long current;
    private Long size;
    private String keyword;
}
