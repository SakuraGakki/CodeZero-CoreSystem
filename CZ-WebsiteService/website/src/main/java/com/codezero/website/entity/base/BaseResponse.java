package com.codezero.website.entity.base;

import lombok.Data;

/**
 *
 */

@Data
public class BaseResponse {
    private int status = 0;
    private String message = "Success";
    private Object data;
}
