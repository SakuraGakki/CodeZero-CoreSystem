package com.codezero.cms.entity.book;

import lombok.Data;

@Data
public class BookPageRequest {
    private String bookId;
    private Long current;
    private Long size;
}
