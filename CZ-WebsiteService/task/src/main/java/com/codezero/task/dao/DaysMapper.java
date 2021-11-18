package com.codezero.task.dao;

import com.codezero.task.pojo.days.Days;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DaysMapper {
    int insert(Days days);
}
