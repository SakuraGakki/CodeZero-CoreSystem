package com.codezero.website.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.website.pojo.messageboard.MessageBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageBoardMapper {

    /**
     * 插入留言信息
     * @param messageBoard
     */
    void insertMessage(MessageBoard messageBoard);

    /**
     * 查询主留言信息
     * @return
     */
    Page<MessageBoard> selectMainMessage(Page<MessageBoard> page);

    /**
     * 查询回复留言信息
     * @param messageBoard
     * @return
     */
    List<MessageBoard> selectSubMessage(MessageBoard messageBoard);

    /**
     * 改变信息状态
     * @param messageBoard
     */
    void updateMessageStatus(MessageBoard messageBoard);
}
