package com.codezero.cms.entity.user;

import lombok.Data;

/**
 * 用户信息请求对象
 */
@Data
public class UserRequest {
    /**
     * 用户ID
     * @mock userId
     */
    private String userId;

    /**
     * 密码
     * @mock password
     */
    private String password;

    /**
     * 登录IP
     * @mock 127.0.0.1
     */
    private String ip;

    /**
     * 登录地点
     * @mock 中国 辽宁 沈阳 联通
     */
    private String city;

}
