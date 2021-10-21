package com.codezero.cms.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.entity.wechat.WechatListAllPageRequest;
import com.codezero.cms.pojo.wechat.WechatInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WechatMapper {

    /**
     * 新增微信文章
     * @param wechatInfo
     */
    void addWechatInfo(WechatInfo wechatInfo);

    /**
     * 更新微信文章
     * @param wechatInfo
     */
    void uptWechatInfo(WechatInfo wechatInfo);

    /**
     * 查询首页微信文章列表
     * @return
     */
    List<WechatInfo> getWechatArticleList();

    /**
     * 分页查询微信文章列表
     * @param page
     * @return
     */
    Page<WechatInfo> selectAllWechatArticlePage(@Param("page") Page page);
}
