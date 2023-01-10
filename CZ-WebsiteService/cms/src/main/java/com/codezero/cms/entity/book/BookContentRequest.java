package com.codezero.cms.entity.book;

import lombok.Data;

@Data
public class BookContentRequest {
    private String bookId;
    private String contentId;
    private String contentTitle;
    private String contentUrl;
}
