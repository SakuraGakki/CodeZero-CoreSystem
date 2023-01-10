package com.codezero.cms.entity.book;

import lombok.Data;

@Data
public class BookResponse {
    /**
     * 书籍ID
     * @mock 1hjksdlf13123
     */
    private String bookId;
    /**
     * 书籍标题
     * @mock 书名1
     */
    private String bookTitle;
    /**
     * 作者
     * @mock CodeZero
     */
    private String author = "Code Zero";
    /**
     * 时间
     * @mock 2021-11-30 11:12:22
     */
    private String date;
    /**
     * 书籍子标题
     * @mock 书名2
     */
    private String bookSubTitle;
    /**
     * 阅读数
     * @mock 12
     */
    private String readNum;
    /**
     * 状态码
     * @mock 0
     */
    private String status;
    /**
     * 配图地址
     * @mock http://www.codezer0.cn:8011/123.jpg
     */
    private String imgUrl;
    /**
     * 配图ID
     * @mock jdfklsf12311231
     */
    private String imgId;
    /**
     * 书籍类型（封面样式）
     * @mock book1-Orange
     */
    private String bookType;
}
