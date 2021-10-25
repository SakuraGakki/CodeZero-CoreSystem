package com.codezero.cms.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.pojo.daily.DailyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DailyMapper {

    Page<DailyInfo> getDailyInfoListPage(@Param("page") Page<DailyInfo> dailyInfoPage);

    List<DailyInfo> getDailyInfoList();

    void insertDailyInfo(DailyInfo dailyInfo);

    void updateDailyInfo(DailyInfo dailyInfo);

    void publishDailyInfo(@Param("id") String id);

    void cancelDailyInfo(@Param("id") String id);

}
