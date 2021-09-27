package com.codezero.website.service.visitor;

import com.codezero.website.dao.VisitorMapper;
import com.codezero.website.entity.base.BaseResponse;
import com.codezero.website.entity.visitor.*;
import com.codezero.website.pojo.VisitorInfo;
import com.codezero.website.pojo.VisitorNum;
import com.codezero.website.utils.DateUtils;
import com.codezero.website.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitorService {

    @Autowired
    VisitorMapper visitorMapper;

    public BaseResponse<VisitorNumResponse> getNum(){
        BaseResponse<VisitorNumResponse> baseResponse = new BaseResponse();
        VisitorNum visitorNum = new VisitorNum();
        visitorNum.setDate(DateUtils.getDateByString());
        List<VisitorNum> list = visitorMapper.queryVisitorNum(visitorNum);
        VisitorNumResponse visitorNumResponse = new VisitorNumResponse();
        visitorNumResponse.setTotal(BigDecimal.valueOf(list.get(0).getTotal()));
        //如果当天没有访客则返回0
        if(list.size()==1){
            visitorNumResponse.setToday(BigDecimal.valueOf(0));
        }else{
            visitorNumResponse.setToday(BigDecimal.valueOf(list.get(1).getTotal()));
        }
        baseResponse.setData(visitorNumResponse);
        return baseResponse;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BaseResponse<String> uptNum(VisitorNumRequest visitorNumRequest){
        BaseResponse<String> baseResponse = new BaseResponse();
        try {
            //首先判断今天是否有数据
            VisitorNum visitorNum = new VisitorNum();
            visitorNum.setType("1");
            visitorNum.setDate(DateUtils.getDateByString());
            List<VisitorNum> visitorNumlist = visitorMapper.chkTodayData(visitorNum);
            //今日信息处理
            if(visitorNumlist.size()>0){
                //更新数据
                visitorNum = visitorNumlist.get(0);
                visitorNum.setTotal(visitorNum.getTotal()+1);
                visitorMapper.uptTodayVisitorNum(visitorNum);
            }else{
                //插入数据
                visitorNum.setTotal(1);
                visitorMapper.insertTodayVisitorNum(visitorNum);
            }
            //访问信息处理
            VisitorInfo visitorInfo = new VisitorInfo();
            visitorInfo.setIp(visitorNumRequest.getIp());
            visitorInfo.setCity(visitorNumRequest.getCity().replaceAll("  "," ").replaceAll(" ","-"));
            visitorInfo.setLastTime(DateUtils.getCurrentTime());
            List<VisitorInfo> visitorInfoList = visitorMapper.chkVisitor(visitorInfo);
            if(visitorInfoList.size()>0){
                //更新数据
                visitorMapper.uptVisitorInfo(visitorInfo);
            }else{
                //插入数据
                visitorMapper.insertVisitorInfo(visitorInfo);
            }
            //访问总数处理
            visitorMapper.uptTotalVisitorNum();
        }catch (Exception e){
            e.printStackTrace();
        }
        baseResponse.setData("更新数据成功！");
        return baseResponse;
    }

    public BaseResponse<List<TopFiveResponse>> topFiveList(){
        BaseResponse baseResponse = new BaseResponse();
        List<VisitorInfo> list = visitorMapper.topFiveList();
        List<TopFiveResponse> topFiveResponseList = new ArrayList<>();
        if(list!=null && !list.isEmpty()){
            topFiveResponseList = this.transformTopFiveList(list);
            baseResponse.setData(topFiveResponseList);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("TOP5没有查询到结果");
        }
        return baseResponse;
    }

    public BaseResponse<List<VisitorInfoResponse>> visitorInfoList(){
        BaseResponse baseResponse = new BaseResponse();
        List<VisitorInfo> list = visitorMapper.visitorInfoList();
        List<VisitorInfoResponse> visitorInfoResponseList = new ArrayList<>();
        if(list!=null && !list.isEmpty()){
            visitorInfoResponseList = transformVisitorList(list);
            baseResponse.setData(visitorInfoResponseList);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("访客信息没有查询到结果");
        }
        return baseResponse;
    }

    public BaseResponse<TenVisitorNumListResponse> tenVisitorNumList(){
        BaseResponse baseResponse = new BaseResponse();
        List<VisitorNum> list = visitorMapper.tenVisitorNumList();
        List<TenVisitorNumListResponse> tenVisitorNumListResponseList = new ArrayList<>();
        if(list != null && !list.isEmpty()){
            tenVisitorNumListResponseList = transformTenVisitorNumList(list);
            baseResponse.setData(tenVisitorNumListResponseList);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("为查询到近十日访客数量信息");
        }
        return baseResponse;
    }


    protected List<TopFiveResponse> transformTopFiveList(List<VisitorInfo> list){
        List<TopFiveResponse> topFiveResponseList = new ArrayList<>();
        for(VisitorInfo visitorInfo:list){
            TopFiveResponse topFiveResponse = new TopFiveResponse();
            topFiveResponse.setIp(visitorInfo.getIp());
            topFiveResponse.setCity(visitorInfo.getCity());
            topFiveResponse.setTotal(visitorInfo.getVisitorNum());
            topFiveResponseList.add(topFiveResponse);
        }
        return topFiveResponseList;
    }

    protected List<VisitorInfoResponse> transformVisitorList(List<VisitorInfo> list){
        List<VisitorInfoResponse> visitorInfoResponseList = new ArrayList<>();
        for(VisitorInfo visitorInfo:list){
            VisitorInfoResponse visitorInfoResponse = new VisitorInfoResponse();
            visitorInfoResponse.setIp(visitorInfo.getIp());
            String[] cityArray = visitorInfo.getCity().split("-");
            visitorInfoResponse.setCity(cityArray[cityArray.length-2]);
            visitorInfoResponse.setTotal(visitorInfo.getVisitorNum());
            visitorInfoResponse.setLastTime(visitorInfo.getLastTime());
            visitorInfoResponseList.add(visitorInfoResponse);
        }
        return visitorInfoResponseList;
    }

    protected List<TenVisitorNumListResponse> transformTenVisitorNumList(List<VisitorNum> list){
        List<TenVisitorNumListResponse> tenVisitorNumListResponseList = new ArrayList<>();
        for(VisitorNum visitorNum:list){
            TenVisitorNumListResponse tenVisitorNumListResponse = new TenVisitorNumListResponse();
            tenVisitorNumListResponse.setDate(visitorNum.getDate());
            tenVisitorNumListResponse.setTotal(visitorNum.getTotal());
            tenVisitorNumListResponseList.add(tenVisitorNumListResponse);
        }
        return tenVisitorNumListResponseList;
    }

}
