package com.codezero.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.dao.DailyMapper;
import com.codezero.cms.dao.ImageMapper;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.daily.DailyPageRequest;
import com.codezero.cms.entity.daily.DailyRequest;
import com.codezero.cms.entity.daily.DailyResponse;
import com.codezero.cms.pojo.daily.DailyInfo;
import com.codezero.cms.pojo.image.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DailyService {

    @Autowired
    DailyMapper dailyMapper;
    @Autowired
    ImageMapper imageMapper;

    public BaseResponse<Page<DailyResponse>> getDailyInfoListPage(DailyPageRequest dailyPageRequest){
        BaseResponse baseResponse = new BaseResponse();
        Page page = new Page();
        if(dailyPageRequest.getCurrent()!=null){
            page.setCurrent(dailyPageRequest.getCurrent());
        }
        Page<DailyInfo> dailyInfoPage = dailyMapper.getDailyInfoListPage(page);
        List<DailyInfo> dailyInfoList = dailyInfoPage.getRecords();
        if(dailyInfoList!=null && !dailyInfoList.isEmpty()){
            Page<DailyResponse> dailyResponsePage = new Page<>();
            List<DailyResponse> dailyResponseList = transformDailyInfoListToDailyResponseList(dailyInfoList);
            dailyResponsePage.setCurrent(dailyInfoPage.getCurrent());
            dailyResponsePage.setSize(dailyInfoPage.getSize());
            dailyResponsePage.setTotal(dailyInfoPage.getTotal());
            dailyResponsePage.setRecords(dailyResponseList);
            baseResponse.setData(dailyResponsePage);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("日常列表查询无结果！");
        }
        return baseResponse;
    }

    public BaseResponse<List<DailyResponse>> getDailyInfoList(){
        BaseResponse baseResponse = new BaseResponse();
        List<DailyInfo> dailyInfoList = dailyMapper.getDailyInfoList();
        if(dailyInfoList!=null && !dailyInfoList.isEmpty()){
            List<DailyResponse> dailyResponseList = transformDailyInfoListToDailyResponseList(dailyInfoList);
            baseResponse.setData(dailyResponseList);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("最近日常列表查询无结果！");
        }

        return baseResponse;
    }

    public BaseResponse addDailyInfo(DailyRequest dailyRequest){
        BaseResponse baseResponse = new BaseResponse();
        DailyInfo dailyInfo = transformDailyRequestToDailyInfo(dailyRequest);
        String uuid = UUID.randomUUID().toString();
        dailyInfo.setSeq(uuid);
        dailyInfo.setStatus("0");
        dailyMapper.insertDailyInfo(dailyInfo);
        return baseResponse;
    }

    public BaseResponse uptDailyInfo(DailyRequest dailyRequest){
        BaseResponse baseResponse = new BaseResponse();
        DailyInfo dailyInfo = transformDailyRequestToDailyInfo(dailyRequest);
        dailyMapper.updateDailyInfo(dailyInfo);
        return baseResponse;
    }

    public BaseResponse publishDailyInfo(DailyRequest dailyRequest){
        BaseResponse baseResponse = new BaseResponse();
        String seq = dailyRequest.getSeq();
        dailyMapper.publishDailyInfo(seq);
        return baseResponse;
    }

    public BaseResponse cancelDailyInfo(DailyRequest dailyRequest){
        BaseResponse baseResponse = new BaseResponse();
        String seq = dailyRequest.getSeq();
        dailyMapper.cancelDailyInfo(seq);
        return baseResponse;
    }

    protected DailyInfo transformDailyRequestToDailyInfo(DailyRequest dailyRequest){
        DailyInfo dailyInfo = new DailyInfo();
        dailyInfo.setSeq(dailyRequest.getSeq());
        dailyInfo.setContent(dailyRequest.getContent());
        dailyInfo.setDate(dailyRequest.getDate());
        dailyInfo.setImageId(dailyRequest.getImageId());
        dailyInfo.setStatus(dailyRequest.getStatus());
        return dailyInfo;
    }

    protected List<DailyResponse> transformDailyInfoListToDailyResponseList(List<DailyInfo> dailyInfoList){
        List<DailyResponse> dailyResponseList = new ArrayList<>();
        for(DailyInfo dailyInfo:dailyInfoList){
            DailyResponse dailyResponse = new DailyResponse();
            dailyResponse.setSeq(dailyInfo.getSeq());
            dailyResponse.setContent(dailyInfo.getContent());
            dailyResponse.setImageId(dailyInfo.getImageId());
            if(dailyInfo.getImageId()!=null){
                ImageInfo imageInfo = imageMapper.imageQueryById(dailyInfo.getImageId());
                dailyResponse.setImageUrl(imageInfo.getImageUrl());
            }
            dailyResponse.setDate(dailyInfo.getDate());
            dailyResponse.setStatus(dailyInfo.getStatus());
            dailyResponseList.add(dailyResponse);
        }
        return dailyResponseList;
    }

}
