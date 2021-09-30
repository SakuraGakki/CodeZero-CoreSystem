package com.codezero.website.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.website.entity.base.BaseResponse;
import com.codezero.website.entity.base.PageRequest;
import com.codezero.website.entity.messageboard.MessageBoardRequest;
import com.codezero.website.entity.messageboard.MessageBoardResponse;
import com.codezero.website.service.messageboard.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 留言信息相关接口
 * @author codezero
 * @since 2021-09-29
 */
@RestController
@RequestMapping("website/messageboard")
public class MessageBoardController {

    @Autowired
    MessageBoardService messageBoardService;

    @RequestMapping(value = "/getMessageBoardList", method = RequestMethod.POST, name = "获取留言列表（包含回复）")
    public BaseResponse<Page<MessageBoardResponse>> getMessageBoardList(PageRequest page){
        return messageBoardService.getMessageBoardList(page);
    }

    @RequestMapping(value = "/insertMessageBoardInfo", method = RequestMethod.POST, name = "插入主留言信息")
    public BaseResponse insertMessageBoardInfo(MessageBoardRequest messageBoardRequest){
        return messageBoardService.insertMessageBoardInfo(messageBoardRequest);
    }

    @RequestMapping(value = "/insertMessageBoardSubInfo", method = RequestMethod.POST, name = "插入留言回复信息")
    public BaseResponse insertMessageBoardSubInfo(MessageBoardRequest messageBoardRequest){
        return messageBoardService.insertMessageBoardSubInfo(messageBoardRequest);
    }

}
