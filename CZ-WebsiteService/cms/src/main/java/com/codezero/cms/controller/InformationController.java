package com.codezero.cms.controller;

import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.information.*;
import com.codezero.cms.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 信息查询接口
 * @author codezero
 * @since 2021-11-18
 */
@RestController
@RequestMapping("cms")
public class InformationController {

    @Autowired
    InformationService informationService;

    @RequestMapping(value = "/getNewsInfo", name = "获取新闻列表", method = RequestMethod.POST)
    public BaseResponse<List<News>> getNewsInfo(){
        return informationService.getNewsInfo();
    }

    @RequestMapping(value = "/getKeywordsInfo", name = "获取热搜列表", method = RequestMethod.POST)
    public BaseResponse<List<Keywords>> getKeywordsInfo(){
        return informationService.getKeywordsInfo();
    }

    @RequestMapping(value = "/getDaysInfo", name = "获取黄历信息", method = RequestMethod.POST)
    public BaseResponse<Days> getDaysInfo(){
        return informationService.getDaysInfo();
    }

    @RequestMapping(value = "/getLocationAndWeather", name = "根据经纬度获取城市及天气", method = RequestMethod.POST)
    public BaseResponse<Weather> getLocationAndWeather(@RequestBody Location location){
        return informationService.getLocationAndWeather(location);
    }

}
