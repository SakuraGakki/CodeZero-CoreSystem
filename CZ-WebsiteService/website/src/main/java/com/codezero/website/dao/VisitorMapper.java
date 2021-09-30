package com.codezero.website.dao;

import com.codezero.website.pojo.visitor.VisitorInfo;
import com.codezero.website.pojo.visitor.VisitorNum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VisitorMapper{

    /**
     * 判断是否有今天访问数量数据
     * @param visitorNum
     * @return
     */
    List<VisitorNum> chkTodayData(VisitorNum visitorNum);

    /**
     * 判断是否有该访客
     * @param visitorInfo
     * @return
     */
    List<VisitorInfo> chkVisitor(VisitorInfo visitorInfo);

    /**
     * 获取总访客数量及今日访客数量
     * @param visitorNum
     * @return
     */
    List<VisitorNum> queryVisitorNum(VisitorNum visitorNum);

    /**
     * 插入今日访客数量信息
     * @param visitorNum
     * @return
     */
    void insertTodayVisitorNum(VisitorNum visitorNum);

    /**
     * 插入访客信息
     * @param visitorInfo
     * @return
     */
    void insertVisitorInfo(VisitorInfo visitorInfo);

    /**
     * 更新今日访客数量信息
     * @param visitorNum
     * @return
     */
    void uptTodayVisitorNum(VisitorNum visitorNum);

    /**
     * 更新总访客数量信息
     * @return
     */
    void uptTotalVisitorNum();

    /**
     * 更新访客信息
     * @param visitorInfo
     * @return
     */
    void uptVisitorInfo(VisitorInfo visitorInfo);

    /**
     * top5列表数据
     * @return
     */
    List<VisitorInfo> topFiveList();

    /**
     * 访客列表信息
     * @return
     */
    List<VisitorInfo> visitorInfoList();

    /**
     * 最近十日内访客数量
     * @return
     */
    List<VisitorNum> tenVisitorNumList();

}
