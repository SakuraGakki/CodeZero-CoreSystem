package com.codezero.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.dao.ArticleMapper;
import com.codezero.cms.entity.article.ArticleContentResponse;
import com.codezero.cms.entity.article.ArticleInfoAllPageRequest;
import com.codezero.cms.entity.article.ArticleInfoResponse;
import com.codezero.cms.entity.article.ArticleRequest;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.pojo.article.ArticleContent;
import com.codezero.cms.pojo.article.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    public BaseResponse saveArticleInfo(ArticleRequest articleRequest){
        BaseResponse baseResponse = new BaseResponse();
        ArticleInfo articleInfo = transformArticleRequestToArticleInfo(articleRequest);
        ArticleContent articleContent = transformArticleRequestToArticleContent(articleRequest);
        String articleId = UUID.randomUUID().toString();
        articleInfo.setArticleId(articleId);
        articleContent.setArticleId(articleId);
        articleMapper.saveArticleInfo(articleInfo);
        articleMapper.saveArticleContent(articleContent);
        return baseResponse;
    }

    public BaseResponse updateArticleInfo(ArticleRequest articleRequest){
        BaseResponse baseResponse = new BaseResponse();
        ArticleInfo articleInfo = transformArticleRequestToArticleInfo(articleRequest);
        ArticleContent articleContent = transformArticleRequestToArticleContent(articleRequest);
        String articleId = articleRequest.getArticleId();
        articleInfo.setArticleId(articleId);
        articleContent.setArticleId(articleId);
        articleMapper.updateArticleInfo(articleInfo);
        articleMapper.updateArticleContent(articleContent);
        return baseResponse;
    }

    public BaseResponse publicArticle(ArticleRequest articleRequest){
        BaseResponse baseResponse = new BaseResponse();
        String articleId = articleRequest.getArticleId();
        articleMapper.publishArticle(articleId);
        return baseResponse;
    }

    public BaseResponse cancelArticle(ArticleRequest articleRequest){
        BaseResponse baseResponse = new BaseResponse();
        String articleId = articleRequest.getArticleId();
        articleMapper.cancelArticle(articleId);
        return baseResponse;
    }

    public BaseResponse<Page<ArticleInfoResponse>> getAllArticleInfoList(ArticleInfoAllPageRequest articleInfoAllPageRequest){
        BaseResponse baseResponse = new BaseResponse();
        Page page = new Page();
        if(articleInfoAllPageRequest.getCurrent()!=null){
            page.setCurrent(articleInfoAllPageRequest.getCurrent());
        }
        String keywords = articleInfoAllPageRequest.getKeyword();
        Page<ArticleInfo> articleInfoPage = articleMapper.getAllArticleInfoList(page,keywords);
        List<ArticleInfo> articleInfoList = articleInfoPage.getRecords();
        if(articleInfoList!=null && !articleInfoList.isEmpty()){
            Page<ArticleInfoResponse> articleInfoResponsePage = new Page<>();
            articleInfoResponsePage.setTotal(articleInfoPage.getTotal());
            articleInfoResponsePage.setCurrent(articleInfoPage.getCurrent());
            articleInfoResponsePage.setSize(articleInfoPage.getSize());
            List<ArticleInfoResponse> list = transformArticleInfoList(articleInfoList);
            articleInfoResponsePage.setRecords(list);
            baseResponse.setData(articleInfoResponsePage);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("所有文章信息列表查询异常！");
        }
        return baseResponse;
    }

    public BaseResponse<Page<ArticleInfoResponse>> getPublishArticleInfosList(ArticleInfoAllPageRequest articleInfoAllPageRequest){
        BaseResponse baseResponse = new BaseResponse();
        Page page = new Page();
        if(articleInfoAllPageRequest.getCurrent()!=null){
            page.setCurrent(articleInfoAllPageRequest.getCurrent());
            page.setSize(6);
        }
        String keywords = articleInfoAllPageRequest.getKeyword();
        Page<ArticleInfo> articleInfoPage = articleMapper.getPublishArticleInfoList(page,keywords);
        List<ArticleInfo> articleInfoList = articleInfoPage.getRecords();
        if(articleInfoList!=null && !articleInfoList.isEmpty()){
            Page<ArticleInfoResponse> articleInfoResponsePage = new Page<>();
            articleInfoResponsePage.setTotal(articleInfoPage.getTotal());
            articleInfoResponsePage.setCurrent(articleInfoPage.getCurrent());
            articleInfoResponsePage.setSize(6);
            List<ArticleInfoResponse> list = transformArticleInfoList(articleInfoList);
            articleInfoResponsePage.setRecords(list);
            baseResponse.setData(articleInfoResponsePage);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("发布文章信息列表查询异常！");
        }
        return baseResponse;
    }

    public BaseResponse addArticleReadNum(ArticleRequest articleRequest){
        BaseResponse baseResponse = new BaseResponse();
        String articleId = articleRequest.getArticleId();
        articleMapper.addArticleReadNum(articleId);
        return baseResponse;
    }

    public BaseResponse<ArticleContentResponse> getArticleContent(ArticleRequest articleRequest){
        BaseResponse baseResponse = new BaseResponse();
        String articleId = articleRequest.getArticleId();
        ArticleContent articleContent = articleMapper.getArticleContent(articleId);
        ArticleContentResponse articleContentResponse = new ArticleContentResponse();
        articleContentResponse.setArticleId(articleId);
        articleContentResponse.setContent(articleContent.getContent());
        baseResponse.setData(articleContentResponse);
        return baseResponse;
    }

    protected List<ArticleInfoResponse> transformArticleInfoList(List<ArticleInfo> articleInfoList){
        List<ArticleInfoResponse> articleInfoResponseList = new ArrayList<>();
        for(ArticleInfo articleInfo : articleInfoList){
            ArticleInfoResponse articleInfoResponse = new ArticleInfoResponse();
            articleInfoResponse.setArticleId(articleInfo.getArticleId());
            articleInfoResponse.setArticleTitle(articleInfo.getArticleTitle());
            articleInfoResponse.setArticleTag(articleInfo.getArticleTag());
            articleInfoResponse.setReadNum(articleInfo.getReadNum());
            articleInfoResponse.setDate(articleInfo.getDate());
            articleInfoResponse.setStatus(articleInfo.getStatus());
            articleInfoResponseList.add(articleInfoResponse);
        }
        return articleInfoResponseList;
    }

    protected ArticleInfo transformArticleRequestToArticleInfo(ArticleRequest articleRequest){
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setArticleId(articleRequest.getArticleId());
        articleInfo.setArticleTitle(articleRequest.getArticleTitle());
        articleInfo.setArticleTag(articleRequest.getArticleTag());
        articleInfo.setDate(articleRequest.getDate());
        articleInfo.setReadNum(articleRequest.getReadNum());
        articleInfo.setStatus(articleRequest.getStatus());
        return articleInfo;
    }

    protected  ArticleContent transformArticleRequestToArticleContent(ArticleRequest articleRequest){
        ArticleContent articleContent = new ArticleContent();
        articleContent.setArticleId(articleRequest.getArticleId());
        articleContent.setContent(articleRequest.getContent());
        return articleContent;
    }
}
