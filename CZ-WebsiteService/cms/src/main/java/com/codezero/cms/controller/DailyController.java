package com.codezero.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.daily.DailyPageRequest;
import com.codezero.cms.entity.daily.DailyRequest;
import com.codezero.cms.entity.daily.DailyResponse;
import com.codezero.cms.service.DailyService;
import com.codezero.cms.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/cms/daily")
public class DailyController {
    @Autowired
    DailyService dailyService;
    @Autowired
    ImageService imageService;

    /**
     * 获取日常信息分页列表
     * @param dailyPageRequest
     * @return
     */
    @RequestMapping(value = "/getDailyInfoListPage",method = RequestMethod.POST, name = "获取日常信息分页列表")
    public BaseResponse<Page<DailyResponse>> getDailyInfoListPage(@RequestBody DailyPageRequest dailyPageRequest){
        return dailyService.getDailyInfoListPage(dailyPageRequest);
    }

    /**
     * 获取近期日常列表
     * @return
     */
    @RequestMapping(value = "/getDailyInfoList", method = RequestMethod.POST, name = "获取近期日常列表")
    public BaseResponse<List<DailyResponse>> getDailyInfoList(){
        return dailyService.getDailyInfoList();
    }

    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/dailyImageUpload", method = RequestMethod.POST, name = "图片上传")
    public @ResponseBody
    BaseResponse dailyImageUpload(@RequestParam("file") MultipartFile file){
        return imageService.dailyImageUpload(file);
    }

    /**
     * 图片替换上传
     * @param file
     * @param imgId
     * @return
     */
    @RequestMapping(value = "/dailyImageReplace", method = RequestMethod.POST, name = "图片替换上传")
    public @ResponseBody BaseResponse dailyImageReplace(@RequestParam("file") MultipartFile file,@RequestParam("imgId") String imgId){
        return imageService.dailyImageReplace(file,imgId);
    }

    /**
     * 新增日常信息
     * @param dailyRequest
     * @return
     */
    @RequestMapping(value = "/addDailyInfo", method = RequestMethod.POST, name = "新增日常信息")
    public BaseResponse addDailyInfo(@RequestBody DailyRequest dailyRequest){
        return dailyService.addDailyInfo(dailyRequest);
    }

    /**
     * 更新日常信息
     * @param dailyRequest
     * @return
     */
    @RequestMapping(value = "/uptDailyInfo", method = RequestMethod.POST, name = "更新日常信息")
    public BaseResponse uptDailyInfo(@RequestBody DailyRequest dailyRequest){
        return dailyService.uptDailyInfo(dailyRequest);
    }

    /**
     * 发布
     * @param dailyRequest
     * @return
     */
    @RequestMapping(value = "/publishDailyInfo", method = RequestMethod.POST, name = "发布")
    public BaseResponse publicDailyInfo(@RequestBody DailyRequest dailyRequest){
        return dailyService.publishDailyInfo(dailyRequest);
    }


    /**
     * 下架
     * @param dailyRequest
     * @return
     */
    @RequestMapping(value = "/cancelDailyInfo", method = RequestMethod.POST, name = "下架")
    public BaseResponse cancelDailyInfo(@RequestBody DailyRequest dailyRequest){
        return dailyService.cancelDailyInfo(dailyRequest);
    }

}
