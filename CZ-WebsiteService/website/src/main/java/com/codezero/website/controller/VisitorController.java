package com.codezero.website.controller;

import com.codezero.website.entity.base.BaseResponse;
import com.codezero.website.entity.visitor.*;
import com.codezero.website.service.visitor.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 访客信息相关接口
 * @author codezero
 * @since 2021-09-09
 */
@RestController
@RequestMapping("website/visitor")
public class VisitorController {

    @Autowired
    VisitorService visitorService;

    /**
     * 获取访问数量信息
     * @return
     */
    @RequestMapping(value = "/getVisitNum", method = RequestMethod.POST, name = "获取访问数量信息")
    public BaseResponse<VisitorNumResponse> getVisitNum(){
        return visitorService.getNum();
    }

    /**
     * 更新访问量信息
     * @param visitorNumRequest
     * @return
     */
    @RequestMapping(value = "/uptVisitNum", method = RequestMethod.POST, name = "更新访问量信息")
    public BaseResponse<String> uptVisitNum(VisitorNumRequest visitorNumRequest){
        return visitorService.uptNum(visitorNumRequest);
    }

    /**
     * 访客top5列表
     * @return
     */
    @RequestMapping(value = "/topFiveList", method = RequestMethod.POST, name="访客TOP5列表")
    public BaseResponse<List<TopFiveResponse>> topFiveList(){
        return visitorService.topFiveList();
    }

    /**
     * 访客全部数据
     * @return
     */
    @RequestMapping(value = "/visitorInfoList", method = RequestMethod.POST, name="访客全部数据列表")
    public BaseResponse<List<VisitorInfoResponse>> visitorInfoList(){
        return visitorService.visitorInfoList();
    }

    /**
     * 每日访问数据列表（10天）
     * @return
     */
    @RequestMapping(value = "/tenVisitorNumList", method = RequestMethod.POST, name="获取每日访问数据列表（10天内）")
    public BaseResponse<TenVisitorNumListResponse> tenVisitorNumList(){
        return visitorService.tenVisitorNumList();
    }

}
