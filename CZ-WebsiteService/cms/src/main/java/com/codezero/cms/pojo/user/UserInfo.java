package com.codezero.cms.pojo.user;

import lombok.Data;

@Data
public class UserInfo {
    /**
     *  用户ID
     */
    private String userId;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String avator;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * Email
     */
    private String email;

    /**
     * 微信Id
     */
    private String wechatId;

    /**
     * 用户创建时间
     */
    private String createDate;

    /**
     * 用户更新时间
     */
    private String updateDate;

    /**
     * 用户上次登录IP
     */
    private String lastIp;

    /**
     * 用户上次登录地点
     */
    private String lastCity;

    /**
     * 用户上次登录时间
     */
    private String lastTime;

}
