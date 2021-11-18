package com.codezero.task.dao;

import com.codezero.task.pojo.news.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper{
    int insertBatch(List<News> list);
}
