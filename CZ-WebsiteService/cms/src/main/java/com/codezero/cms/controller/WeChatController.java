package com.codezero.cms.controller;

import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.wechat.WeChatRequest;
import com.codezero.cms.entity.wechat.WeChatResponse;
import com.codezero.cms.service.ImageService;
import com.codezero.cms.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/cms/wechat")
@Slf4j
public class WeChatController {

    @Autowired
    WechatService wechatService;

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/wechatImageUpload", method = RequestMethod.POST, name = "图片上传")
    public @ResponseBody BaseResponse wechatImageUpload(@RequestParam("file") MultipartFile file){
        return imageService.wechatImageUpload(file);
    }

    @RequestMapping(value = "/wechatImageReplace", method = RequestMethod.POST, name = "图片上传")
    public @ResponseBody BaseResponse wechatImageReplace(@RequestParam("file") MultipartFile file,@RequestParam("imgId") String imgId){
        return imageService.wechatImageReplace(file,imgId);
    }

    @RequestMapping(value = "/insertWechatArticle", method = RequestMethod.POST, name = "新增微信文章")
    public BaseResponse insertWechatArticle(@RequestBody WeChatRequest weChatRequest){
        return wechatService.insertWechatArticle(weChatRequest);
    }

    @RequestMapping(value = "/updateWechatArticle", method = RequestMethod.POST, name = "更新微信文章")
    public BaseResponse updateWechatArticle(@RequestBody WeChatRequest weChatRequest){
        return wechatService.updatetWechatArticle(weChatRequest);
    }

    @RequestMapping(value = "/getWechatArticleList", method = RequestMethod.POST, name = "查询微信文章列表")
    public BaseResponse<List<WeChatResponse>> getWechatArticleList(){
        return wechatService.getWechatArticleList();
    }

}
