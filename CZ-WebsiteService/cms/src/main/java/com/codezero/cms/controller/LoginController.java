package com.codezero.cms.controller;

import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.user.UserRequest;
import com.codezero.cms.entity.user.UserResponse;
import com.codezero.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CMS登录相关接口
 * @author codezero
 * @since 2021-09-09
 */
@RestController
@RequestMapping("cms")
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 用户登录接口
     * @param userRequest
     * @return
     */
    @RequestMapping(name = "用户登录接口",value = "/login",method = RequestMethod.POST)
    public BaseResponse<UserResponse> login(@RequestBody UserRequest userRequest){
        return userService.login(userRequest);
    }
}
