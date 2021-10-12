package com.codezero.cms.pojo.image;

import lombok.Data;

@Data
public class ImageInfo {
    /**
     * 图片ID
     */
    private String imageId;
    /**
     * 图片名称
     */
    private String imageName;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 图片大小
     */
    private String imageSize;
    /**
     * 图片拓展名
     */
    private String imageExtension;
    /**
     * 图片上传时间
     */
    private String date;
    /**
     * 图片类型
     */
    private String type;
    /**
     * 图片关联原图ID
     */
    private String relatedId;
    /**
     * 图片应用场景
     */
    private String sceneType;
    /**
     * 图片应用场景名称
     */
    private String sceneName;
}
