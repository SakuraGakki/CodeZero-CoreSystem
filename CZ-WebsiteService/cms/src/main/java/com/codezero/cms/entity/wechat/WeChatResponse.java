package com.codezero.cms.entity.wechat;

import lombok.Data;

@Data
public class WeChatResponse {
    /**
     * 主键Id
     * @mock jfajdlj12312lklsd
     */
    private String seq;
    /**
     * 文章标题
     * @mock 哈哈哈
     */
    private String title;
    /**
     * 文章链接
     * @mock http://www.baidu.com
     */
    private String url;
    /**
     * 图片Id
     * @mock 1231231231fdsd
     */
    private String imgId;
    /**
     * 图片链接
     * @mock https://www.baidu.com/12312.png
     */
    private String imgUrl;
    /**
     * 发布时间
     * @mock 2021-10-09 12:12:56
     */
    private String date;
    /**
     * 阅读量
     * @mock 123
     */
    private Integer read;
}
