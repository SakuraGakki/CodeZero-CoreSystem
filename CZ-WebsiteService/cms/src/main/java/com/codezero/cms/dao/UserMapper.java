package com.codezero.cms.dao;

import com.codezero.cms.pojo.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserInfo> login(UserInfo userInfo);

    int uptLoginInfo(UserInfo userInfo);

}
