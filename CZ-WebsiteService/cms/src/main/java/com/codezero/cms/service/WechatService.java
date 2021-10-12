package com.codezero.cms.service;

import com.codezero.cms.dao.ImageMapper;
import com.codezero.cms.dao.WechatMapper;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.wechat.WeChatRequest;
import com.codezero.cms.entity.wechat.WeChatResponse;
import com.codezero.cms.pojo.image.ImageInfo;
import com.codezero.cms.pojo.wechat.WechatInfo;
import com.codezero.cms.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WechatService {

    @Autowired
    WechatMapper wechatMapper;

    @Autowired
    ImageMapper imageMapper;

    public BaseResponse insertWechatArticle(WeChatRequest weChatRequest){
        BaseResponse baseResponse = new BaseResponse();
        WechatInfo wechatInfo = transformWechatRequestToWechatInfo(weChatRequest);
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString();
        wechatInfo.setSeq(uid);
        wechatInfo.setDate(DateUtils.getCurrentTime());
        wechatMapper.addWechatInfo(wechatInfo);
        return baseResponse;
    }

    public BaseResponse updatetWechatArticle(WeChatRequest weChatRequest){
        BaseResponse baseResponse = new BaseResponse();
        WechatInfo wechatInfo = transformWechatRequestToWechatInfo(weChatRequest);
        wechatInfo.setSeq(weChatRequest.getSeq());
        wechatMapper.uptWechatInfo(wechatInfo);
        return baseResponse;
    }

    public BaseResponse<List<WeChatResponse>> getWechatArticleList(){
        BaseResponse baseResponse = new BaseResponse();
        List<WechatInfo> list = wechatMapper.getWechatArticleList();
        List<WeChatResponse> weChatResponseList = new ArrayList<>();
        if(list!=null && !list.isEmpty()){
            for(WechatInfo wechatInfo:list){
                WeChatResponse weChatResponse = tranformWechatInfoToWechatResponse(wechatInfo);
                ImageInfo imageInfo = imageMapper.imageQueryById(weChatResponse.getImgId());
                weChatResponse.setImgUrl(imageInfo.getImageUrl());
                weChatResponseList.add(weChatResponse);
            }
            baseResponse.setData(weChatResponseList);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("微信文章列表查询错误！");
        }
        return baseResponse;
    }

    protected  WeChatResponse tranformWechatInfoToWechatResponse(WechatInfo wechatInfo){
        WeChatResponse weChatResponse = new WeChatResponse();
        weChatResponse.setSeq(wechatInfo.getSeq());
        weChatResponse.setTitle(wechatInfo.getTitle());
        weChatResponse.setUrl(wechatInfo.getUrl());
        weChatResponse.setImgId(wechatInfo.getImgId());
        weChatResponse.setDate(wechatInfo.getDate());
        weChatResponse.setRead(wechatInfo.getRead());
        return weChatResponse;
    }

    protected WechatInfo transformWechatRequestToWechatInfo(WeChatRequest weChatRequest){
        WechatInfo wechatInfo = new WechatInfo();
        wechatInfo.setTitle(weChatRequest.getTitle());
        wechatInfo.setUrl(weChatRequest.getUrl());
        wechatInfo.setImgId(weChatRequest.getImgId());
        wechatInfo.setDate(weChatRequest.getDate());
        wechatInfo.setRead(weChatRequest.getRead());
        return wechatInfo;
    }

}
