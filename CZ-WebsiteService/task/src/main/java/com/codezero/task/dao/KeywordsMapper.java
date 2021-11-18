package com.codezero.task.dao;

import com.codezero.task.pojo.keywords.Keywords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KeywordsMapper {
    int insertBatch(List<Keywords> list);
}
