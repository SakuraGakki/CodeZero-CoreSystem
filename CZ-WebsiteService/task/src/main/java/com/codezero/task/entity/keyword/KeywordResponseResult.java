package com.codezero.task.entity.keyword;

import lombok.Data;

import java.util.List;

@Data
public class KeywordResponseResult {
    private String ret_code;
    private List<KeywordResponseInfo> list;
}
