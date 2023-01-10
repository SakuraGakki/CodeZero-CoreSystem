package com.codezero.cms.pojo.book;

import lombok.Data;

@Data
public class Books {
    private String bookId;
    private String bookTitle;
    private String bookSubTitle;
    private String author;
    private String date;
    private String readNum;
    private String status;
    private String imgUrl;
    private String imgId;
    private String bookType;
}
