package com.codezero.cms.entity.book;

import lombok.Data;

@Data
public class BookRequest {
    private String bookId;
    private String bookTitle;
    private String author;
    private String date;
    private String bookSubTitle;
    private String readNum;
    private String status;
    private String imgUrl;
    private String imgId;
    private String bookType;
}
