package com.codezero.cms.entity.Images;

import lombok.Data;

@Data
public class ImageResponse {
    /**
     * 图片Id
     * @mock 0ca44154-5e7e-463b-b687-ab89f5e3455d
     */
    private String imageId;
    /**
     * 图片名称
     * @mock test
     */
    private String imageName;
    /**
     * 图片地址
     * @mock https://www.baidu.com/123.png
     */
    private String imageUrl;
    /**
     * 图片大小
     * @mock 1234KB
     */
    private String imageSize;
    /**
     * 图片拓展名
     * @mock png
     */
    private String imageExtension;
    /**
     * 图片上传时间
     * @mock 2020-01-01 11:00:00
     */
    private String date;
    /**
     * 图片类型
     * @mock 0
     */
    private String type;
    /**
     * 图片关联原图ID
     * @mock 4124124124
     */
    private String relatedId;
    /**
     * 图片应用场景
     * @mock wechat
     */
    private String sceneType;
    /**
     * 图片应用场景名称
     * @mock 微信文章配图
     */
    private String sceneName;
}
