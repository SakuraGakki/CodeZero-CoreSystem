package com.codezero.website.controller;

import com.codezero.website.entity.base.BaseResponse;
import com.codezero.website.entity.visitor.VisitorNumRequest;
import com.codezero.website.service.visitor.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 访问信息相关接口
 */
@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    VisitorService visitorService;

    @RequestMapping(value = "/getVisitNum", method = RequestMethod.POST, name = "获取访问数量信息")
    public BaseResponse getVisitNum(){
        return visitorService.getNum();
    }

    @RequestMapping(value = "/uptVisitNum", method = RequestMethod.POST, name = "更新访问量信息")
    public BaseResponse uptVisitNum(VisitorNumRequest visitorNumRequest){
        return visitorService.uptNum(visitorNumRequest);
    }

}
