package com.codezero.cms.entity.book;

import lombok.Data;

@Data
public class BookContentResponse {
    /**
     * 内容序号
     * @mock 0
     */
    private Integer contentOrder;
    /**
     * 书籍ID
     * @mock 123dfgjkdlsgj
     */
    private String bookId;
    /**
     * 内容标题
     * @mock 第一章 序
     */
    private String contentTitle;
    /**
     * 内容ID
     * @mock dkfjklsdfs231231
     */
    private String contentId;
    /**
     * 内容文件地址
     * @mock http://www.codezer0.cn:8011/123.md
     */
    private String contentUrl;
}
