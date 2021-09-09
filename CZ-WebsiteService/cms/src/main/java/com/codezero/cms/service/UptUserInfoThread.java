package com.codezero.cms.service;

import com.codezero.cms.dao.UserMapper;
import com.codezero.cms.pojo.user.UserInfo;
import com.codezero.cms.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 更新用户信息线程
 */
public class UptUserInfoThread extends Thread{

    UserMapper userMapper = ManageSpringBeans.getBean(UserMapper.class);

    private String userId;
    private String lastIp;
    private String lastCity;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setLastIp(String lastIp){
        this.lastIp = lastIp;
    }

    public void setLastCity(String lastCity){
        this.lastCity = lastCity;
    }

    @Override
    public void run() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(this.userId);
        userInfo.setLastIp(this.lastIp);
        userInfo.setLastCity(this.lastCity);
        userInfo.setLastTime(DateUtils.getCurrentTime());
        userMapper.uptLoginInfo(userInfo);
    }

}
