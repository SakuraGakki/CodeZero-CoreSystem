package com.codezero.cms.entity.user;

import lombok.Data;

/**
 * 用户信息响应对象
 */
@Data
public class UserResponse {
    /**
     * 用户ID
     * @mock userId
     */
    private String userId;

    /**
     * 用户名
     * @mock userName
     */
    private String userName;

    /**
     * 头像
     * @mock avatorUrl
     */
    private String avator;

    /**
     * 性别
     * @mock 1
     */
    private String sex;

    /**
     * 生日
     * @mock 2000-01-01
     */
    private String birthday;

    /**
     * 手机号
     * @mock 13000000000
     */
    private String phone;

    /**
     * 地址
     * @mock 辽宁省沈阳市
     */
    private String address;

    /**
     * Email
     * @mock abcd@qq.com
     */
    private String email;

    /**
     * 微信openId
     * @mock djklf13j1l31dfjsl12313
     */
    private String wechatId;

    /**
     * 上次登录ip
     * @mock 127.0.0.1
     */
    private String lastIp;

    /**
     * 上次登录地点
     * @mock 中国 辽宁 沈阳 联通
     */
    private String lastCity;

    /**
     * 上次登录时间
     * @mock 2021-01-01
     */
    private String lastTime;
}
