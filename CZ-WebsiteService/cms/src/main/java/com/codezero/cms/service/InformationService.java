package com.codezero.cms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.information.Days;
import com.codezero.cms.entity.information.Keywords;
import com.codezero.cms.entity.information.News;
import com.codezero.cms.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class InformationService {

    @Resource
    private RedisUtil redisUtil;

    public BaseResponse<List<News>> getNewsInfo(){
        BaseResponse<List<News>> baseResponse = new BaseResponse<>();
        List<News> newsList = new ArrayList<>();
        JSONArray listObj = JSONArray.parseArray(redisUtil.get("newsInfo").toString());

        for(Object obj:listObj){
            News news =  new News();
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
            news = JSONObject.toJavaObject(jsonObject, News.class);
            newsList.add(news);
        }
        baseResponse.setData(newsList);
        return baseResponse;
    }

    public BaseResponse<List<Keywords>> getKeywordsInfo(){
        BaseResponse<List<Keywords>> baseResponse = new BaseResponse<>();
        List<Keywords> keywordsList = new ArrayList<>();
        JSONArray listObj = JSONArray.parseArray(redisUtil.get("keywordsInfo").toString());

        for(Object obj:listObj){
            Keywords keywords =  new Keywords();
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
            keywords = JSONObject.toJavaObject(jsonObject, Keywords.class);
            keywordsList.add(keywords);
        }
        baseResponse.setData(keywordsList);
        return baseResponse;
    }

    public BaseResponse<Days> getDaysInfo(){
        BaseResponse<Days> baseResponse = new BaseResponse<>();
        Object obj = JSONObject.parseObject(redisUtil.get("calendarInfo").toString());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
        Days days = JSONObject.toJavaObject(jsonObject,Days.class);
        baseResponse.setData(days);
        return baseResponse;
    }

}
