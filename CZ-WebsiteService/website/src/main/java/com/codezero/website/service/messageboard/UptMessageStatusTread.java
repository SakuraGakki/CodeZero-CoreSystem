package com.codezero.website.service.messageboard;

import com.codezero.website.dao.MessageBoardMapper;
import com.codezero.website.pojo.messageboard.MessageBoard;
import com.codezero.website.utils.ManageSpringBeans;

/**
 * 更新消息状态线程
 */
public class UptMessageStatusTread extends Thread{

    MessageBoardMapper messageBoardMapper = ManageSpringBeans.getBean(MessageBoardMapper.class);

    private Integer seq;
    private String status;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void run() {
        MessageBoard messageBoard = new MessageBoard();
        messageBoard.setSeq(this.seq);
        messageBoard.setStatus(this.status);
        messageBoardMapper.updateMessageStatus(messageBoard);
    }
}
