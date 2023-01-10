package com.codezero.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.dao.BookMapper;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.book.*;
import com.codezero.cms.pojo.book.Books;
import com.codezero.cms.pojo.book.BooksContent;
import com.codezero.cms.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookMapper bookMapper;

    public BaseResponse<Page<BookResponse>> queryBookList(BookPageRequest request){
        BaseResponse<Page<BookResponse>> baseResponse = new BaseResponse<>();
        Page page = new Page();
        if(request.getCurrent()!=null){
            page.setCurrent(request.getCurrent());
        }
        Page<Books> booksPage = bookMapper.queryBooksList(page);
        List<Books> booksList = booksPage.getRecords();
        if(booksList!=null && !booksList.isEmpty()){
            Page<BookResponse> bookResponsePage = new Page<>();
            bookResponsePage.setCurrent(booksPage.getCurrent());
            bookResponsePage.setTotal(booksPage.getTotal());
            bookResponsePage.setSize(booksPage.getSize());
            List<BookResponse> bookResponseList = transformBooksToBooksResponse(booksList);
            bookResponsePage.setRecords(bookResponseList);
            baseResponse.setData(bookResponsePage);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("未查询到书籍记录！");
        }
        return baseResponse;
    }

    public BaseResponse<Page<BookContentResponse>> queryBookContent(BookPageRequest request){
        BaseResponse<Page<BookContentResponse>> baseResponse = new BaseResponse<>();
        Page page = new Page();
        if(request.getCurrent()!=null){
            page.setCurrent(request.getCurrent());
        }
        String bookId = request.getBookId();
        Page<BooksContent> booksContentPage = bookMapper.queryBooksContentList(page,bookId);
        List<BooksContent> booksContentList = booksContentPage.getRecords();
        if(booksContentList!=null && !booksContentList.isEmpty()){
            Page<BookContentResponse> bookContentResponsePage = new Page<>();
            bookContentResponsePage.setCurrent(booksContentPage.getCurrent());
            bookContentResponsePage.setTotal(booksContentPage.getTotal());
            bookContentResponsePage.setSize(booksContentPage.getSize());
            List<BookContentResponse> bookContentResponses = transformBooksContentToBooksContentResponse(booksContentList);
            bookContentResponsePage.setRecords(bookContentResponses);
            baseResponse.setData(bookContentResponsePage);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("未查询到目录及内容信息！");
        }
        return baseResponse;
    }

    public BaseResponse insertBook(BookRequest request){
        BaseResponse baseResponse = new BaseResponse();
        Books books = transformBooksRequestToBooks(request);
        int i = bookMapper.insertBook(books);
        if(i<=0){
            baseResponse.setStatus(-1);
            baseResponse.setMessage("新增书籍异常！");
        }
        return baseResponse;
    }

    public BaseResponse insertBookContent(BookContentRequest request){
        BaseResponse baseResponse = new BaseResponse();
        BooksContent booksContent = transformBookContentRequestToBookContent(request);
        int i = bookMapper.insertBookContent(booksContent);
        if(i<=0){
            baseResponse.setStatus(-1);
            baseResponse.setMessage("新增书籍内容异常！");
        }
        return baseResponse;
    }

    public BaseResponse publishBook(BookRequest request){
        BaseResponse baseResponse = new BaseResponse();
        String bookId = request.getBookId();
        int i = bookMapper.publishBook(bookId);
        if(i<=0){
            baseResponse.setStatus(-1);
            baseResponse.setMessage("发布书籍失败！");
        }
        return baseResponse;
    }

    public BaseResponse cancelBook(BookRequest request){
        BaseResponse baseResponse = new BaseResponse();
        String bookId = request.getBookId();
        int i = bookMapper.cancelBook(bookId);
        if(i<=0){
            baseResponse.setStatus(-1);
            baseResponse.setMessage("下架书籍失败！");
        }
        return baseResponse;
    }

    public BaseResponse<Page<BookResponse>> queryPublicBookList(BookPageRequest request){
        BaseResponse<Page<BookResponse>> baseResponse = new BaseResponse<>();
        Page page = new Page();
        if(request.getCurrent()!=null){
            page.setCurrent(request.getCurrent());
        }
        Page<Books> booksPage = bookMapper.queryBooksList(page);
        List<Books> booksList = booksPage.getRecords();
        if(booksList!=null && !booksList.isEmpty()){
            Page<BookResponse> bookResponsePage = new Page<>();
            bookResponsePage.setCurrent(booksPage.getCurrent());
            bookResponsePage.setTotal(booksPage.getTotal());
            bookResponsePage.setSize(booksPage.getSize());
            List<BookResponse> bookResponseList = transformBooksToBooksResponse(booksList);
            bookResponsePage.setRecords(bookResponseList);
            baseResponse.setData(bookResponsePage);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("未查询到书籍记录！");
        }
        return baseResponse;
    }

    protected BooksContent transformBookContentRequestToBookContent(BookContentRequest request){
        BooksContent booksContent = new BooksContent();
        booksContent.setBookId(request.getBookId());
        booksContent.setContentId(UUID.randomUUID().toString());
        booksContent.setContentTitle(request.getContentTitle());
        booksContent.setContentUrl(request.getContentUrl());
        return booksContent;
    }

    protected Books transformBooksRequestToBooks(BookRequest request){
        Books books = new Books();
        books.setAuthor("Code Zero");
        books.setDate(DateUtils.getCurrentTime());
        books.setBookId(UUID.randomUUID().toString());
        books.setBookTitle(request.getBookTitle());
        books.setBookSubTitle(request.getBookSubTitle());
        books.setBookType(request.getBookType());
        books.setStatus("0");
        books.setImgId(request.getImgId());
        books.setImgUrl(request.getImgUrl());
        return books;
    }

    protected List<BookContentResponse> transformBooksContentToBooksContentResponse(List<BooksContent> booksContentList){
        List<BookContentResponse> bookContentResponseList = new ArrayList<>();
        for(BooksContent booksContent:booksContentList){
            BookContentResponse bookContentResponse = new BookContentResponse();
            bookContentResponse.setBookId(booksContent.getBookId());
            bookContentResponse.setContentOrder(booksContent.getContentOrder());
            bookContentResponse.setContentId(booksContent.getContentId());
            bookContentResponse.setContentTitle(booksContent.getContentTitle());
            bookContentResponse.setContentUrl(booksContent.getContentUrl());
            bookContentResponseList.add(bookContentResponse);
        }
        return bookContentResponseList;
    }

    protected List<BookResponse> transformBooksToBooksResponse(List<Books> booksList){
        List<BookResponse> bookResponseList = new ArrayList<>();
        for(Books book : booksList){
            BookResponse bookResponse = new BookResponse();
            bookResponse.setBookId(book.getBookId());
            bookResponse.setBookTitle(book.getBookTitle());
            bookResponse.setBookSubTitle(book.getBookSubTitle());
            bookResponse.setAuthor(book.getAuthor());
            bookResponse.setDate(book.getDate());
            bookResponse.setImgId(book.getImgId());
            bookResponse.setImgUrl(book.getImgUrl());
            bookResponse.setReadNum(book.getReadNum());
            bookResponse.setStatus(book.getStatus());
            bookResponse.setBookType(book.getBookType());
            bookResponseList.add(bookResponse);
        }
        return bookResponseList;
    }
}
