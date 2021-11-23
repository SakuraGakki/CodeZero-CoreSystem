package com.codezero.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.entity.article.ArticleContentResponse;
import com.codezero.cms.entity.article.ArticleInfoAllPageRequest;
import com.codezero.cms.entity.article.ArticleInfoResponse;
import com.codezero.cms.entity.article.ArticleRequest;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.service.ArticleService;
import com.codezero.cms.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cms/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ImageService imageService;

    /**
     * 保存文章
     * @param articleRequest
     * @return
     */
    @RequestMapping(value = "/saveArticleInfo", method = RequestMethod.POST, name = "保存文章")
    public BaseResponse saveArticleInfo(@RequestBody ArticleRequest articleRequest){
        return articleService.saveArticleInfo(articleRequest);
    }

    /**
     * 文章列表查询（分页）
     * @param articleInfoAllPageRequest
     * @return
     */
    @RequestMapping(value = "/getAllArticleInfosList",method = RequestMethod.POST, name = "文章列表查询")
    public BaseResponse<Page<ArticleInfoResponse>> getAllArticleInfoList(@RequestBody ArticleInfoAllPageRequest articleInfoAllPageRequest){
        return articleService.getAllArticleInfoList(articleInfoAllPageRequest);
    }

    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/articleImageUpload", method = RequestMethod.POST, name = "图片上传")
    public @ResponseBody BaseResponse articleImageUpload(@RequestParam("file") MultipartFile file){
        return imageService.articleImageUpload(file);
    }

    /**
     * 根据文章ID获取详情（cms）
     * @param articleRequest
     * @return
     */
    @RequestMapping(value = "/getArticleContentById", method = RequestMethod.POST, name = "根据文章ID获取详情（cms）")
    public BaseResponse<ArticleContentResponse> getArticleContentById(@RequestBody ArticleRequest articleRequest){
        return articleService.getArticleContent(articleRequest);
    }

    /**
     * 根据文章ID获取详情（门户）
     * @param articleRequest
     * @return
     */
    @RequestMapping(value = "/articleContentById", method = RequestMethod.POST, name = "根据文章ID获取详情(门户)")
    public BaseResponse<ArticleContentResponse> articleContentById(ArticleRequest articleRequest){
        return articleService.getArticleContent(articleRequest);
    }

    /**
     * 更新文章
     * @param articleRequest
     * @return
     */
    @RequestMapping(value = "/updateArticleInfo", method = RequestMethod.POST, name = "更新文章")
    public BaseResponse updateArticleInfo(@RequestBody ArticleRequest articleRequest){
        return articleService.updateArticleInfo(articleRequest);
    }

    /**
     * 发布文章
     * @param articleRequest
     * @return
     */
    @RequestMapping(value = "/publishArticle", method = RequestMethod.POST, name = "发布文章")
    public BaseResponse publicArticle(@RequestBody ArticleRequest articleRequest){
        return articleService.publicArticle(articleRequest);
    }

    /**
     * 下架文章
     * @param articleRequest
     * @return
     */
    @RequestMapping(value = "/cancelArticle", method = RequestMethod.POST, name = "下架文章")
    public BaseResponse cancelArticle(@RequestBody ArticleRequest articleRequest){
        return articleService.cancelArticle(articleRequest);
    }

    /**
     * 已发布文章列表查询
     * @param articleInfoAllPageRequest
     * @return
     */
    @RequestMapping(value = "/getPublishArticleInfosList",method = RequestMethod.POST, name = "已发布文章列表查询")
    public BaseResponse<Page<ArticleInfoResponse>> getPublishArticleInfosList(ArticleInfoAllPageRequest articleInfoAllPageRequest){
        return articleService.getPublishArticleInfosList(articleInfoAllPageRequest);
    }

    /**
     * 增加文章阅读数
     * @param articleRequest
     * @return
     */
    @RequestMapping(value = "/addArticleReadNum", method = RequestMethod.POST, name = "增加文章阅读数")
    public BaseResponse addArticleReadNum(ArticleRequest articleRequest){
        return articleService.addArticleReadNum(articleRequest);
    }

    /**
     * 增加文章阅读数（微信小程序）
     * @param articleRequest
     * @return
     */
    @RequestMapping(value = "/addArticleReadNumWechat", method = RequestMethod.POST, name = "增加文章阅读数(微信小程序)")
    public BaseResponse addArticleReadNumWechat(@RequestBody ArticleRequest articleRequest){
        return articleService.addArticleReadNum(articleRequest);
    }
}
