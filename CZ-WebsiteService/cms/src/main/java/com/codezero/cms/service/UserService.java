package com.codezero.cms.service;

import com.codezero.cms.dao.UserMapper;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.user.UserRequest;
import com.codezero.cms.entity.user.UserResponse;
import com.codezero.cms.pojo.user.UserInfo;
import com.codezero.cms.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息服务类
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public BaseResponse<UserResponse> login(UserRequest userRequest){
        BaseResponse<UserResponse> baseResponse= new BaseResponse<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userRequest.getUserId());
        userInfo.setPassword(userRequest.getPassword());
        List<UserInfo> list = userMapper.login(userInfo);
        if(list!=null && list.size()>0){
            userInfo = list.get(0);
            UserResponse userResponse = transformUserInfo(userInfo);
            baseResponse.setData(userResponse);
            UptUserInfoThread uptUserInfoThread = new UptUserInfoThread();
            uptUserInfoThread.setUserId(userRequest.getUserId());
            uptUserInfoThread.setLastIp(userRequest.getIp());
            uptUserInfoThread.setLastCity(userRequest.getCity());
            uptUserInfoThread.start();
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("用户不存在或用户名密码错误！");
        }
        return baseResponse;
    }


    public UserResponse transformUserInfo(UserInfo userInfo){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(StringUtils.toString(userInfo.getUserId(),""));
        userResponse.setUserName(StringUtils.toString(userInfo.getUserName(),""));
        userResponse.setAvator(StringUtils.toString(userInfo.getAvator(),""));
        userResponse.setAddress(StringUtils.toString(userInfo.getAddress(),""));
        userResponse.setBirthday(StringUtils.toString(userInfo.getBirthday(),""));
        userResponse.setEmail(StringUtils.toString(userInfo.getEmail(),""));
        userResponse.setPhone(StringUtils.toString(userInfo.getPhone(),""));
        userResponse.setSex(StringUtils.toString(userInfo.getSex(),""));
        userResponse.setWechatId(StringUtils.toString(userInfo.getWechatId(),""));
        userResponse.setLastIp(StringUtils.toString(userInfo.getLastIp(),""));
        userResponse.setLastCity(StringUtils.toString(userInfo.getLastCity(),""));
        userResponse.setLastTime(StringUtils.toString(userInfo.getLastTime(),""));
        return userResponse;
    }

}
