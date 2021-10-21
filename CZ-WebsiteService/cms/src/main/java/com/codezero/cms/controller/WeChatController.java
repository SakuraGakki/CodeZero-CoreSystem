package com.codezero.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.wechat.WeChatRequest;
import com.codezero.cms.entity.wechat.WeChatResponse;
import com.codezero.cms.entity.wechat.WechatListAllPageRequest;
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


    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/wechatImageUpload", method = RequestMethod.POST, name = "图片上传")
    public @ResponseBody BaseResponse wechatImageUpload(@RequestParam("file") MultipartFile file){
        return imageService.wechatImageUpload(file);
    }

    /**
     * 图片替换上传
     * @param file
     * @param imgId
     * @return
     */
    @RequestMapping(value = "/wechatImageReplace", method = RequestMethod.POST, name = "图片替换上传")
    public @ResponseBody BaseResponse wechatImageReplace(@RequestParam("file") MultipartFile file,@RequestParam("imgId") String imgId){
        return imageService.wechatImageReplace(file,imgId);
    }

    /**
     * 新增微信文章
     * @param weChatRequest
     * @return
     */
    @RequestMapping(value = "/insertWechatArticle", method = RequestMethod.POST, name = "新增微信文章")
    public BaseResponse insertWechatArticle(@RequestBody WeChatRequest weChatRequest){
        return wechatService.insertWechatArticle(weChatRequest);
    }

    /**
     * 更新微信文章
     * @param weChatRequest
     * @return
     */
    @RequestMapping(value = "/updateWechatArticle", method = RequestMethod.POST, name = "更新微信文章")
    public BaseResponse updateWechatArticle(@RequestBody WeChatRequest weChatRequest){
        return wechatService.updatetWechatArticle(weChatRequest);
    }

    /**
     * 查询微信文章列表
     * @return
     */
    @RequestMapping(value = "/getWechatArticleList", method = RequestMethod.POST, name = "查询微信文章列表")
    public BaseResponse<List<WeChatResponse>> getWechatArticleList(){
        return wechatService.getWechatArticleList();
    }


    @RequestMapping(value = "/getWechatArticleListPage", method = RequestMethod.POST, name = "微信文章列表分页查询")
    public BaseResponse<Page<WeChatResponse>> getWechatArticleListPage(@RequestBody WechatListAllPageRequest wechatListAllPageRequest){
        return wechatService.getWechatArticleListPage(wechatListAllPageRequest);
    }
}
