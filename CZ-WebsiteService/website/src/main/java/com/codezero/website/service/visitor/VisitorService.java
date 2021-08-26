package com.codezero.website.service.visitor;

import com.codezero.website.dao.VisitorMapper;
import com.codezero.website.entity.base.BaseResponse;
import com.codezero.website.entity.visitor.VisitorNumRequest;
import com.codezero.website.entity.visitor.VisitorNumResponse;
import com.codezero.website.pojo.VisitorInfo;
import com.codezero.website.pojo.VisitorNum;
import com.codezero.website.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VisitorService {

    @Autowired
    VisitorMapper visitorMapper;

    public BaseResponse getNum(){
        BaseResponse baseResponse = new BaseResponse();
        VisitorNum visitorNum = new VisitorNum();
        visitorNum.setDate(DateUtils.getDateByString());
        List<VisitorNum> list = visitorMapper.queryVisitorNum(visitorNum);
        VisitorNumResponse visitorNumResponse = new VisitorNumResponse();
        visitorNumResponse.setTotal(BigDecimal.valueOf(list.get(0).getTotal()));
        visitorNumResponse.setToday(BigDecimal.valueOf(list.get(1).getTotal()));
        baseResponse.setData(visitorNumResponse);
        return baseResponse;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BaseResponse uptNum(VisitorNumRequest visitorNumRequest){
        BaseResponse baseResponse = new BaseResponse();
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
            visitorInfo.setCity(visitorNumRequest.getCity());
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
        return baseResponse;
    }
}
