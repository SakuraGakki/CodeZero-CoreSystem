package com.codezero.website.service.messageboard;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.website.dao.MessageBoardMapper;
import com.codezero.website.entity.base.BaseResponse;
import com.codezero.website.entity.base.PageRequest;
import com.codezero.website.entity.messageboard.MessageBoardRequest;
import com.codezero.website.entity.messageboard.MessageBoardResponse;
import com.codezero.website.entity.messageboard.MessageBoardSubResponse;
import com.codezero.website.pojo.messageboard.MessageBoard;
import com.codezero.website.utils.StringUtils;
import com.mysql.cj.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageBoardService {

    @Autowired
    MessageBoardMapper messageBoardMapper;

    public BaseResponse<Page<MessageBoardResponse>> getMessageBoardList(PageRequest pageRequest){
        BaseResponse baseResponse = new BaseResponse();
        Page page = new Page();
        if(pageRequest.getCurrent()!=null){
            page.setCurrent(pageRequest.getCurrent());
        }
        page.setSize(6);
        Page<MessageBoard> messageBoardPage = messageBoardMapper.selectMainMessage(page);
        List<MessageBoard> messageBoardList = messageBoardPage.getRecords();
        if(messageBoardList!=null && !messageBoardList.isEmpty()){
            Page<MessageBoardResponse> messageBoardResponsePage = new Page<>();
            messageBoardResponsePage.setTotal(messageBoardPage.getTotal());
            messageBoardResponsePage.setCurrent(messageBoardPage.getCurrent());
            messageBoardResponsePage.setSize(messageBoardPage.getSize());
            List<MessageBoardResponse> resultList = new ArrayList<>();
            for(MessageBoard messageBoard:messageBoardList){
                MessageBoardResponse messageBoardResponse = transformMessageBoardResponse(messageBoard);
                List<MessageBoardSubResponse> messageBoardSubResponseList = new ArrayList<>();
                MessageBoard messageBoardPojo = new MessageBoard();
                messageBoardPojo.setRelatedSeq(messageBoard.getSeq());
                List<MessageBoard> messageBoardSubList = messageBoardMapper.selectSubMessage(messageBoardPojo);
                if(messageBoardSubList!=null && !messageBoardSubList.isEmpty()){
                    for(MessageBoard messageBoardSub:messageBoardSubList){
                        MessageBoardSubResponse messageBoardSubResponse = transformMessageBoardSubResponse(messageBoardSub);
                        messageBoardSubResponseList.add(messageBoardSubResponse);
                    }
                }
                messageBoardResponse.setList(messageBoardSubResponseList);
                resultList.add(messageBoardResponse);
            }
            messageBoardResponsePage.setRecords(resultList);
            baseResponse.setData(messageBoardResponsePage);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("留言信息列表查询异常！");
        }
        return baseResponse;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BaseResponse insertMessageBoardInfo(MessageBoardRequest messageBoardRequest){
        BaseResponse baseResponse = new BaseResponse();
        MessageBoard messageBoard = transformMessageBoardRequest(messageBoardRequest);
        messageBoard.setMsgType("0");
        messageBoard.setStatus("0");
        messageBoardMapper.insertMessage(messageBoard);
        return baseResponse;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BaseResponse insertMessageBoardSubInfo(MessageBoardRequest messageBoardRequest){
        BaseResponse baseResponse = new BaseResponse();
        MessageBoard messageBoard = transformMessageBoardRequest(messageBoardRequest);
        messageBoard.setRelatedSeq(messageBoardRequest.getId());
        messageBoard.setStatus("0");
        messageBoard.setMsgType("1");
        messageBoardMapper.insertMessage(messageBoard);
        UptMessageStatusTread uptMessageStatusTread = new UptMessageStatusTread();
        uptMessageStatusTread.setSeq(messageBoardRequest.getId());
        uptMessageStatusTread.setStatus("2");
        uptMessageStatusTread.start();
        return baseResponse;
    }

    protected MessageBoard transformMessageBoardRequest(MessageBoardRequest messageBoardRequest){
        MessageBoard messageBoard = new MessageBoard();
        messageBoard.setIp(messageBoardRequest.getIp());
        messageBoard.setCity(messageBoardRequest.getCity());
        messageBoard.setDate(messageBoardRequest.getDate());
        messageBoard.setContent(messageBoardRequest.getContent());
        return messageBoard;
    }


    protected MessageBoardSubResponse transformMessageBoardSubResponse(MessageBoard messageBoard){
        MessageBoardSubResponse messageBoardSubResponse = new MessageBoardSubResponse();
        messageBoardSubResponse.setId(messageBoard.getSeq());
        messageBoardSubResponse.setIp(messageBoard.getIp());
        messageBoardSubResponse.setCity(messageBoard.getCity());
        messageBoardSubResponse.setDate(messageBoard.getDate());
        messageBoardSubResponse.setContent(messageBoard.getContent());
        return messageBoardSubResponse;
    }

    protected MessageBoardResponse transformMessageBoardResponse(MessageBoard messageBoard){
        MessageBoardResponse messageBoardResponse = new MessageBoardResponse();
        messageBoardResponse.setId(messageBoard.getSeq());
        messageBoardResponse.setIp(messageBoard.getIp());
        messageBoardResponse.setCity(messageBoard.getCity());
        messageBoardResponse.setDate(messageBoard.getDate());
        messageBoardResponse.setContent(messageBoard.getContent());
        return messageBoardResponse;
    }
}
