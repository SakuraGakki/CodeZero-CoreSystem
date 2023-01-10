package com.codezero.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.book.*;
import com.codezero.cms.service.BookService;
import com.codezero.cms.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cms/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/queryBookList", name = "获取书籍列表", method = RequestMethod.POST)
    public BaseResponse<Page<BookResponse>> queryBookList(@RequestBody BookPageRequest request){
        return bookService.queryBookList(request);
    }

    @RequestMapping(value = "/queryBookContent", name = "获取书籍目录及内容", method = RequestMethod.POST)
    public BaseResponse<Page<BookContentResponse>> queryBookContent(@RequestBody BookPageRequest request){
        return bookService.queryBookContent(request);
    }

    @RequestMapping(value = "/insertBook", name = "新建书籍", method = RequestMethod.POST)
    public BaseResponse insertBook(@RequestBody BookRequest request){
        return bookService.insertBook(request);
    }

    @RequestMapping(value = "/insertBookContent", name = "新建书籍内容", method = RequestMethod.POST)
    public BaseResponse insertBookContent(@RequestBody BookContentRequest request){
        return bookService.insertBookContent(request);
    }

    @RequestMapping(value = "/publishBook", name = "发布书籍", method = RequestMethod.POST)
    public BaseResponse publishBook(@RequestBody BookRequest request){
        return bookService.publishBook(request);
    }

    @RequestMapping(value = "/cancelBook", name = "下架书籍", method = RequestMethod.POST)
    public BaseResponse cancelBook(@RequestBody BookRequest request){
        return bookService.cancelBook(request);
    }

    @RequestMapping(value = "/queryPublicBookList", name = "查询已发布书籍列表", method = RequestMethod.POST)
    public BaseResponse<Page<BookResponse>> queryPublicBookList(BookPageRequest request){
        return bookService.queryPublicBookList(request);
    }

    @RequestMapping(value = "/queryBookContentByBookId", name = "查询已发布书籍目录及内容", method = RequestMethod.POST)
    public BaseResponse<Page<BookContentResponse>> queryBookContentByBookId(BookPageRequest request){
        return bookService.queryBookContent(request);
    }

    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/bookImageUpload", method = RequestMethod.POST, name = "图片上传")
    public @ResponseBody
    BaseResponse bookImageUpload(@RequestParam("file") MultipartFile file){
        return imageService.bookImageUpload(file);
    }

    /**
     * 图片替换上传
     * @param file
     * @param imgId
     * @return
     */
    @RequestMapping(value = "/bookImageReplace", method = RequestMethod.POST, name = "图片替换上传")
    public @ResponseBody BaseResponse bookImageReplace(@RequestParam("file") MultipartFile file,@RequestParam("imgId") String imgId){
        return imageService.bookImageReplace(file,imgId);
    }
}
