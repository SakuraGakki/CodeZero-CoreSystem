package com.codezero.cms.pojo.book;

import lombok.Data;

@Data
public class BooksContent {
    private Integer contentOrder;
    private String bookId;
    private String contentId;
    private String contentTitle;
    private String contentUrl;
}
