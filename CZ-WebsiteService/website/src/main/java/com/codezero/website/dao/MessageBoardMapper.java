package com.codezero.website.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.website.pojo.messageboard.AllMessageBoard;
import com.codezero.website.pojo.messageboard.MessageBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 改变消息状态
     * @param messageBoard
     */
    void updateMessageStatus(MessageBoard messageBoard);

    /**
     * 获取未回复留言列表
     * @param page
     * @return
     */
    Page<MessageBoard> selectUnreadMessage(Page<MessageBoard> page);

    /**
     * 获取未读留言数量
     * @return
     */
    int selectUnreadMessageCount();

    /**
     * 查询所有留言信息（包含回复）
     * @return
     */
    Page<MessageBoard> selectAllMessage(@Param("page") Page<AllMessageBoard> page, @Param("allMessageBoard") AllMessageBoard allMessageBoard);

    /**
     * 更新留言内容
     * @param messageBoard
     */
    void updateMessageContent(MessageBoard messageBoard);

    /**
     * 批量删除留言
     * @param i
     */
    void deleteMessageBoardList(Integer[] i);
}
