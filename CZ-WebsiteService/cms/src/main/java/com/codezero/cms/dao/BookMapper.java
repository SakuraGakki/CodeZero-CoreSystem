package com.codezero.cms.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.cms.pojo.book.Books;
import com.codezero.cms.pojo.book.BooksContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookMapper {

    Page<Books> queryBooksList(@Param("page") Page<Books> page);

    Page<BooksContent> queryBooksContentList(@Param("page") Page<BooksContent> page,@Param("bookId") String bookId);

    int insertBook(Books books);

    int insertBookContent(BooksContent booksContent);

    int publishBook(@Param("bookId") String bookId);

    int cancelBook(@Param("bookId") String bookId);

    Page<Books> queryPublicBookList(@Param("page") Page<Books> page);
}
