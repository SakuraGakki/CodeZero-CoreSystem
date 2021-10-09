package com.codezero.website.entity.messageboard;

import lombok.Data;

@Data
public class MessageListAllPageRequest {
    private Long current;
    private Long size;
    private String keyword;
    private String status;
}
