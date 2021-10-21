package com.codezero.cms.service;

import com.codezero.cms.dao.ImageMapper;
import com.codezero.cms.entity.Images.ImageResponse;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.pojo.image.ImageInfo;
import com.codezero.cms.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@Slf4j
@RefreshScope
public class ImageService {

    @Autowired
    ImageMapper imageMapper;
    @Value("${imageUploadUrl}")
    private String imageUploadUrl;
    @Value("${imageHttpUrl}")
    private String imageHttpUrl;

    public BaseResponse<ImageResponse> wechatImageUpload(MultipartFile file){
        BaseResponse baseResponse = new BaseResponse();
        if(file.isEmpty()){
            baseResponse.setStatus(-1);
            baseResponse.setMessage("上传图片文件为空！！");
            return baseResponse;
        }
        //图片Id
        String imageId = UUID.randomUUID().toString();
        //图片名
        String imageName = file.getOriginalFilename();
        //图片后缀名
        String imageExtension = imageName.substring(imageName.lastIndexOf("."));
        //应用场景
        String sceneType = "wechat";
        String sceneName = "微信文章配图";
        //图片大小
        Long imageSize = file.getSize();
        //图片路径
        String imageUrl = imageUploadUrl+"images/wechat/"+imageName;
        //图片类型
        String type = "0";
        //上传时间
        String date = DateUtils.getCurrentTime();
        File dest = new File(imageUrl);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try{
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
        }
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setImageId(imageId);
        imageInfo.setImageName(imageName);
        imageInfo.setImageUrl(imageUrl.replace(imageUploadUrl,imageHttpUrl));
        imageInfo.setDate(date);
        imageInfo.setImageExtension(imageExtension);
        imageInfo.setType(type);
        imageInfo.setImageSize(Math.round(imageSize/1024)+"KB");
        imageInfo.setSceneType(sceneType);
        imageInfo.setSceneName(sceneName);
        imageInfo.setRelatedId("");
        imageMapper.imageUpload(imageInfo);
        ImageResponse imageResponse = transformImageInfoToImageResponse(imageInfo);
        baseResponse.setData(imageResponse);
        return baseResponse;
    }

    public BaseResponse<ImageResponse> articleImageUpload(MultipartFile file){
        BaseResponse baseResponse = new BaseResponse();
        if(file.isEmpty()){
            baseResponse.setStatus(-1);
            baseResponse.setMessage("上传图片文件为空！！");
            return baseResponse;
        }
        //图片Id
        String imageId = UUID.randomUUID().toString();
        //图片名
        String imageName = file.getOriginalFilename();
        //图片后缀名
        String imageExtension = imageName.substring(imageName.lastIndexOf("."));
        //应用场景
        String sceneType = "article";
        String sceneName = "本站文章配图";
        //图片大小
        Long imageSize = file.getSize();
        //图片路径
        String imageUrl = imageUploadUrl+"images/article/"+imageName;
        //图片类型
        String type = "0";
        //上传时间
        String date = DateUtils.getCurrentTime();
        File dest = new File(imageUrl);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try{
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
        }
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setImageId(imageId);
        imageInfo.setImageName(imageName);
        imageInfo.setImageUrl(imageUrl.replace(imageUploadUrl,imageHttpUrl));
        imageInfo.setDate(date);
        imageInfo.setImageExtension(imageExtension);
        imageInfo.setType(type);
        imageInfo.setImageSize(Math.round(imageSize/1024)+"KB");
        imageInfo.setSceneType(sceneType);
        imageInfo.setSceneName(sceneName);
        imageInfo.setRelatedId("");
        imageMapper.imageUpload(imageInfo);
        ImageResponse imageResponse = transformImageInfoToImageResponse(imageInfo);
        baseResponse.setData(imageResponse);
        return baseResponse;
    }

    public BaseResponse<ImageResponse> wechatImageReplace(MultipartFile file,String imgId){
        BaseResponse baseResponse = new BaseResponse();
        if(file.isEmpty()){
            baseResponse.setStatus(-1);
            baseResponse.setMessage("上传图片文件为空！！");
            return baseResponse;
        }
        //图片名
        String imageName = file.getOriginalFilename();
        //图片后缀名
        String imageExtension = imageName.substring(imageName.lastIndexOf("."));
        //应用场景
        String sceneType = "wechat";
        String sceneName = "微信文章配图";
        //图片大小
        Long imageSize = file.getSize();
        //图片路径
        String imageUrl = imageUploadUrl+"images/wechat/"+imageName;
        //图片类型
        String type = "0";
        //上传时间
        String date = DateUtils.getCurrentTime();
        ImageInfo imageInfo = imageMapper.imageQueryById(imgId);
        String oldImageUrl = imageInfo.getImageUrl().replace(imageHttpUrl,imageUploadUrl);
        File oldImg = new File(oldImageUrl);
        if(oldImg.getAbsoluteFile().exists()){
            oldImg.delete();
        }
        File dest = new File(imageUrl);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try{
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
        }
        imageInfo.setImageName(imageName);
        imageInfo.setImageUrl(imageUrl.replace(imageUploadUrl,imageHttpUrl));
        imageInfo.setDate(date);
        imageInfo.setImageExtension(imageExtension);
        imageInfo.setType(type);
        imageInfo.setImageSize(Math.round(imageSize/1024)+"KB");
        imageInfo.setSceneType(sceneType);
        imageInfo.setSceneName(sceneName);
        imageInfo.setRelatedId("");
        imageMapper.imageUpdateById(imageInfo);
        ImageResponse imageResponse = transformImageInfoToImageResponse(imageInfo);
        baseResponse.setData(imageResponse);
        return baseResponse;
    }

    protected ImageResponse transformImageInfoToImageResponse(ImageInfo imageInfo){
        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setImageId(imageInfo.getImageId());
        imageResponse.setImageName(imageInfo.getImageName());
        imageResponse.setImageUrl(imageInfo.getImageUrl());
        imageResponse.setImageSize(imageInfo.getImageSize());
        imageResponse.setDate(imageInfo.getDate());
        imageResponse.setImageExtension(imageInfo.getImageExtension());
        imageResponse.setType(imageInfo.getType());
        imageResponse.setSceneType(imageInfo.getSceneType());
        imageResponse.setSceneName(imageInfo.getSceneName());
        imageResponse.setRelatedId(imageInfo.getRelatedId());
        return imageResponse;
    }

}
