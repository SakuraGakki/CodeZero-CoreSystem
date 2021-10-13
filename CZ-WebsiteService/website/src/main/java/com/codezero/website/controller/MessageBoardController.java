package com.codezero.website.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codezero.website.entity.base.BaseResponse;
import com.codezero.website.entity.base.PageRequest;
import com.codezero.website.entity.messageboard.MessageBoardAllResponse;
import com.codezero.website.entity.messageboard.MessageBoardRequest;
import com.codezero.website.entity.messageboard.MessageBoardResponse;
import com.codezero.website.entity.messageboard.MessageListAllPageRequest;
import com.codezero.website.service.messageboard.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

    /**
     * 获取留言列表（包含回复）
     * @param page
     * @return
     */
    @RequestMapping(value = "/getMessageBoardList", method = RequestMethod.POST, name = "获取留言列表（包含回复）")
    public BaseResponse<Page<MessageBoardResponse>> getMessageBoardList(PageRequest page){
        return messageBoardService.getMessageBoardList(page);
    }

    /**
     * 插入主留言信息
     * @param messageBoardRequest
     * @return
     */
    @RequestMapping(value = "/insertMessageBoardInfo", method = RequestMethod.POST, name = "插入主留言信息")
    public BaseResponse insertMessageBoardInfo(MessageBoardRequest messageBoardRequest){
        return messageBoardService.insertMessageBoardInfo(messageBoardRequest);
    }

    /**
     * 插入留言回复信息
     * @param messageBoardRequest
     * @return
     */
    @RequestMapping(value = "/insertMessageBoardSubInfo", method = RequestMethod.POST, name = "插入留言回复信息")
    public BaseResponse insertMessageBoardSubInfo(MessageBoardRequest messageBoardRequest){
        return messageBoardService.insertMessageBoardSubInfo(messageBoardRequest);
    }

    /**
     * 获取未回复留言列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/getMessageBoardUnreadList", method = RequestMethod.POST, name = "获取未回复留言列表")
    public BaseResponse<Page<MessageBoardResponse>> getMessageBoardUnreadList(PageRequest page){
        return messageBoardService.getMessageBoardUnreadList(page);
    }

    /**
     * 获取未读消息数量
     * @return
     */
    @RequestMapping(value = "/getUnreadMessageCount", method = RequestMethod.POST, name="获取未读消息数量")
    public BaseResponse getUnreadMessageCount(){
        return messageBoardService.getUnreadMessageCount();
    }

    /**
     * 获取所有留言（包含回复）
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMessageListAll", method = RequestMethod.POST, name = "获取所有留言（包含回复）")
    public BaseResponse<Page<MessageBoardAllResponse>> getMessageListAll(@RequestBody MessageListAllPageRequest request){
        return messageBoardService.getMessageBoardListAll(request);
    }

    /**
     * 站长新增留言&回复
     * @param messageBoardRequest
     * @return
     */
    @RequestMapping(value = "/adminInsertMessageBoardInfo", method = RequestMethod.POST, name = "站长新增留言&回复")
    public BaseResponse adminInsertMessageBoardInfo(@RequestBody MessageBoardRequest messageBoardRequest){
        //判断是新增留言还是回复留言
        if(null!=messageBoardRequest.getId() && messageBoardRequest.getId()!=0){
            //回复
            return messageBoardService.adminReplyMessageBoardInfo(messageBoardRequest);
        }else{
            //新增
            return messageBoardService.adminInsertMessageBoardInfo(messageBoardRequest);
        }
    }

    /**
     * 站长修改留言
     * @param messageBoardRequest
     * @return
     */
    @RequestMapping(value = "/adminUptMessageBoardInfo", method = RequestMethod.POST, name = "站长修改留言")
    public BaseResponse adminUptMessageBoardInfo(@RequestBody MessageBoardRequest messageBoardRequest){
        return messageBoardService.adminUptMessageBoardInfo(messageBoardRequest);
    }

    /**
     * 站长删除留言（逻辑删除）
     * @param messageBoardRequestList
     * @return
     */
    @RequestMapping(value = "/adminDeleteMessageBoardList", method = RequestMethod.POST, name = "站长删除留言（逻辑删除）")
    public BaseResponse adminDeleteMessageBoardList(@RequestBody List<MessageBoardRequest> messageBoardRequestList){
        return messageBoardService.adminDeleteMessageBoardList(messageBoardRequestList);
    }
}
