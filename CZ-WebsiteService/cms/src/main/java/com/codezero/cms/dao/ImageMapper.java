package com.codezero.cms.dao;

import com.codezero.cms.pojo.image.ImageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageMapper {

    /**
     * 图片上传
     * @return
     */
    void imageUpload(ImageInfo imageInfo);

    /**
     * 根据图片ID查询图片
     * @param imageId
     * @return
     */
    ImageInfo imageQueryById(@Param("imageId") String imageId);

    /**
     * 根据图片ID替换更新图片
     * @param imageInfo
     */
    void imageUpdateById(ImageInfo imageInfo);

}
