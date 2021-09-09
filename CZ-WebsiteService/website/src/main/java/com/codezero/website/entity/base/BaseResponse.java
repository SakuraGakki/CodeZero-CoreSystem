package com.codezero.website.entity.base;

import lombok.Data;

/**
 * 通用响应对象
 */

@Data
public class BaseResponse<T> {
    /**
     * 状态码
     * @mock 0
     */
    private int status = 0;
    /**
     * 响应消息
     * @mock Success
     */
    private String message = "Success";
    /**
     * 响应对象
     */
    private T data;
}
