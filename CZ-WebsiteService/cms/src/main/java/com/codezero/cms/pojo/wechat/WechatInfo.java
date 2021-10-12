package com.codezero.cms.pojo.wechat;

import lombok.Data;

@Data
public class WechatInfo {
    /**
     * 主键Id
     */
    private String seq;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章链接
     */
    private String url;
    /**
     * 图片链接
     */
    private String imgId;
    /**
     * 发布时间
     */
    private String date;
    /**
     * 阅读量
     */
    private Integer read;
}
