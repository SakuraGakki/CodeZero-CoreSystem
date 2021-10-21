package com.codezero.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.dao.ImageMapper;
import com.codezero.cms.dao.WechatMapper;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.wechat.WeChatRequest;
import com.codezero.cms.entity.wechat.WeChatResponse;
import com.codezero.cms.entity.wechat.WechatListAllPageRequest;
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


    public BaseResponse<Page<WeChatResponse>> getWechatArticleListPage(WechatListAllPageRequest wechatListAllPageRequest){
        BaseResponse baseResponse = new BaseResponse();
        Page page = new Page();
        if(wechatListAllPageRequest.getCurrent()!=null){
            page.setCurrent(wechatListAllPageRequest.getCurrent());
        }
        Page<WechatInfo> wechatInfoPage = wechatMapper.selectAllWechatArticlePage(page);
        List<WechatInfo> wechatInfoList = wechatInfoPage.getRecords();
        if(wechatInfoList!=null && !wechatInfoList.isEmpty()){
            Page<WeChatResponse> weChatResponsePage = new Page<>();
            weChatResponsePage.setTotal(wechatInfoPage.getTotal());
            weChatResponsePage.setCurrent(wechatInfoPage.getCurrent());
            weChatResponsePage.setSize(wechatInfoPage.getSize());
            List<WeChatResponse> list = transformAllWechatArticleList(wechatInfoList);
            weChatResponsePage.setRecords(list);
            baseResponse.setData(weChatResponsePage);
        }
        return baseResponse;
    }

    protected List<WeChatResponse> transformAllWechatArticleList(List<WechatInfo> list){
        List<WeChatResponse> weChatResponseList = new ArrayList<>();
        for(WechatInfo wechatInfo:list){
            WeChatResponse weChatResponse = new WeChatResponse();
            weChatResponse.setSeq(wechatInfo.getSeq());
            weChatResponse.setTitle(wechatInfo.getTitle());
            weChatResponse.setUrl(wechatInfo.getUrl());
            weChatResponse.setImgId(wechatInfo.getImgId());
            ImageInfo imageInfo = imageMapper.imageQueryById(weChatResponse.getImgId());
            weChatResponse.setImgUrl(imageInfo.getImageUrl());
            weChatResponse.setDate(wechatInfo.getDate());
            weChatResponse.setRead(wechatInfo.getRead());
            weChatResponseList.add(weChatResponse);
        }
        return weChatResponseList;
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
