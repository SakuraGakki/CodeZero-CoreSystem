package com.codezero.task.entity.keyword;

import lombok.Data;

@Data
public class KeywordResponseBody {
    private String showapi_res_error;
    private String showapi_res_id;
    private String showapi_res_code;
    private String showapi_fee_num;
    private KeywordResponseResult showapi_res_body;
}
