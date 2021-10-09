package com.codezero.website.service.messageboard;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.website.dao.MessageBoardMapper;
import com.codezero.website.entity.base.BaseResponse;
import com.codezero.website.entity.base.PageRequest;
import com.codezero.website.entity.messageboard.*;
import com.codezero.website.pojo.messageboard.AllMessageBoard;
import com.codezero.website.pojo.messageboard.MessageBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
    public BaseResponse adminInsertMessageBoardInfo(MessageBoardRequest messageBoardRequest){
        BaseResponse baseResponse = new BaseResponse();
        MessageBoard messageBoard = transformMessageBoardRequest(messageBoardRequest);
        messageBoard.setMsgType("0");
        messageBoard.setStatus("1");
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

    @Transactional(propagation = Propagation.REQUIRED)
    public BaseResponse adminReplyMessageBoardInfo(MessageBoardRequest messageBoardRequest){
        BaseResponse baseResponse = new BaseResponse();
        MessageBoard messageBoard = transformMessageBoardRequest(messageBoardRequest);
        messageBoard.setRelatedSeq(messageBoardRequest.getId());
        messageBoard.setStatus("1");
        messageBoard.setMsgType("1");
        messageBoardMapper.insertMessage(messageBoard);
        UptMessageStatusTread uptMessageStatusTread = new UptMessageStatusTread();
        uptMessageStatusTread.setSeq(messageBoardRequest.getId());
        uptMessageStatusTread.setStatus("2");
        uptMessageStatusTread.start();
        return baseResponse;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BaseResponse adminUptMessageBoardInfo(MessageBoardRequest messageBoardRequest){
        BaseResponse baseResponse = new BaseResponse();
        MessageBoard messageBoard = transformMessageBoardRequest(messageBoardRequest);
        messageBoard.setSeq(messageBoardRequest.getId());
        messageBoardMapper.updateMessageContent(messageBoard);
        return baseResponse;
    }

    public BaseResponse adminDeleteMessageBoardList(List<MessageBoardRequest> list){
        BaseResponse baseResponse = new BaseResponse();
        Integer[] ids = new Integer[list.size()];
        for(int i=0;i<list.size();i++){
           ids[i] =  list.get(i).getId();
        }
        messageBoardMapper.deleteMessageBoardList(ids);
        return baseResponse;
    }

    public BaseResponse<Page<MessageBoardResponse>> getMessageBoardUnreadList(PageRequest pageRequest){
        BaseResponse baseResponse = new BaseResponse();
        Page page = new Page();
        if(pageRequest.getCurrent()!=null){
            page.setCurrent(pageRequest.getCurrent());
        }
        page.setSize(6);
        Page<MessageBoard> messageBoardPage = messageBoardMapper.selectUnreadMessage(page);
        List<MessageBoard> messageBoardList = messageBoardPage.getRecords();
        if(messageBoardList!=null && !messageBoardList.isEmpty()){
            Page<MessageBoardResponse> messageBoardResponsePage = new Page<>();
            messageBoardResponsePage.setTotal(messageBoardPage.getTotal());
            messageBoardResponsePage.setCurrent(messageBoardPage.getCurrent());
            messageBoardResponsePage.setSize(messageBoardPage.getSize());
            List<MessageBoardResponse> resultList = new ArrayList<>();
            for(MessageBoard messageBoard:messageBoardList){
                MessageBoardResponse messageBoardResponse = transformMessageBoardResponse(messageBoard);
                resultList.add(messageBoardResponse);
            }
            messageBoardResponsePage.setRecords(resultList);
            baseResponse.setData(messageBoardResponsePage);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("未回复留言信息列表查询异常！");
        }
        return baseResponse;
    }

    public BaseResponse getUnreadMessageCount(){
        BaseResponse baseResponse = new BaseResponse();
        int i = messageBoardMapper.selectUnreadMessageCount();
        baseResponse.setData(i);
        return baseResponse;
    }

    public BaseResponse<Page<MessageBoardAllResponse>> getMessageBoardListAll(MessageListAllPageRequest request){
        BaseResponse baseResponse = new BaseResponse();
        Page page = new Page();
        if(request.getCurrent()!=null){
            page.setCurrent(request.getCurrent());
        }
        AllMessageBoard allMessageBoard = new AllMessageBoard();
        allMessageBoard.setText(request.getKeyword());
        allMessageBoard.setStatus(request.getStatus());
        Page<MessageBoard> messageBoardPage = messageBoardMapper.selectAllMessage(page,allMessageBoard);
        List<MessageBoard> messageBoardList = messageBoardPage.getRecords();
        if(messageBoardList!=null && !messageBoardList.isEmpty()){
            Page<MessageBoardAllResponse> messageBoardAllResponsePage = new Page<>();
            messageBoardAllResponsePage.setTotal(messageBoardPage.getTotal());
            messageBoardAllResponsePage.setCurrent(messageBoardPage.getCurrent());
            messageBoardAllResponsePage.setSize(messageBoardPage.getSize());
            List<MessageBoardAllResponse> list = transformAllMessageBoardList(messageBoardList);
            messageBoardAllResponsePage.setRecords(list);
            baseResponse.setData(messageBoardAllResponsePage);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("所有留言信息列表查询异常！");
        }
        return baseResponse;
    }

    protected List<MessageBoardAllResponse> transformAllMessageBoardList(List<MessageBoard> list){
        List<MessageBoardAllResponse> messageBoardAllResponses = new ArrayList<>();
        for(MessageBoard messageBoard : list){
            MessageBoardAllResponse messageBoardAllResponse = new MessageBoardAllResponse();
            messageBoardAllResponse.setId(messageBoard.getSeq());
            messageBoardAllResponse.setIp(messageBoard.getIp());
            messageBoardAllResponse.setMsgType(messageBoard.getMsgType());
            messageBoardAllResponse.setCity(messageBoard.getCity());
            messageBoardAllResponse.setContent(messageBoard.getContent());
            messageBoardAllResponse.setDate(messageBoard.getDate());
            messageBoardAllResponse.setStatus(messageBoard.getStatus());
            messageBoardAllResponse.setRelatedId(messageBoard.getRelatedSeq());
            messageBoardAllResponses.add(messageBoardAllResponse);
        }
        return messageBoardAllResponses;
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
