package com.codezero.cms.dao;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.pojo.article.ArticleContent;
import com.codezero.cms.pojo.article.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {

    void saveArticleInfo(ArticleInfo articleInfo);

    void saveArticleContent(ArticleContent articleContent);

    Page<ArticleInfo> getAllArticleInfoList(@Param("page") Page<ArticleInfo> articleInfoPage,@Param("keywords") String keywords);

    Page<ArticleInfo> getPublishArticleInfoList(@Param("page") Page<ArticleInfo> articleInfoPage,@Param("keywords") String keywords);

    ArticleContent getArticleContent(@Param("id") String id);

    void updateArticleInfo(ArticleInfo articleInfo);

    void updateArticleContent(ArticleContent articleContent);

    void addArticleReadNum(@Param("id") String id);

    void publishArticle(@Param("id") String id);

    void cancelArticle(@Param("id") String id);
}
