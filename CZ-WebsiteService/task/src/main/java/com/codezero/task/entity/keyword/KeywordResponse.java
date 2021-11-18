package com.codezero.task.entity.keyword;

import lombok.Data;

@Data
public class KeywordResponse {
    private String code;
    private String charge;
    private String msg;
    private KeywordResponseBody result;
}
