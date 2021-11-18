// pages/message/message.js
import {formatTime} from '../../utils/util'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        current:"message",
        page:{
            current:1,
            size:3,
            total:100
        },
        messageContent: "",
        ip: wx.getStorageSync('userInfo').avatarUrl,
        city: wx.getStorageSync('userInfo').nickName,
        messageList: []
    },

    handleChange ({ detail }) {
        const type = detail.type;
        if (type === 'next') {
            this.setData({
                current: this.data.current + 1
            });
        } else if (type === 'prev') {
            this.setData({
                current: this.data.current - 1
            });
        }
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.queryMessageList()
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },
    queryMessageList() {
        let that = this
        wx.request({
            url: 'http://www.codezer0.cn:8000/website/messageboard/getMessageBoardList',
            data:{
                current:this.data.page.current,
                size:this.data.page.current
            },
            method:'POST',
            header: {
              'content-type': 'application/json' // 默认值
            },
            success (res) {
            if (res.data.status === 0) {      
                let total = Math.round(res.data.data.total/res.data.data.size)===0?1:Math.round(res.data.data.total/res.data.data.size)
                that.setData({messageList:res.data.data.records,page:{total:total,current:res.data.data.current,size:res.data.data.size}})
            } else {
                that.handleError("查询留言列表失败！")
            }
          }
      })
    },
    saveMessageInfo() {
        let that = this
        wx.request({
            url: 'http://www.codezer0.cn:8000/website/messageboard/adminInsertMessageBoardInfo',
            data:{
                ip: that.data.ip,
                city: that.data.city,
                date: formatTime('YYYY-MM-DD hh:mm:ss'),
                content: that.data.messageContent
            },
            method:'POST',
            header: {
              'content-type': 'application/json' // 默认值
            },
            success (res) {
            if (res.data.status === 0) {      
                that.queryMessageList()
                that.setData({
                    messageContent: ""
                })
            } else {
                that.handleError("留言保存失败！")
            }
          }
      })
    },
    handleChange ({ detail }) {
        if(['article','more'].indexOf(detail.key)>-1){
            $Message({
              content: '功能还未完成，敬请期待...',
              type: 'warning'
            });
            return;
        }
        wx.redirectTo({
          url: '../'+detail.key+'/'+detail.key,
        })
    },
    handleSuccess (message) {
        $Toast({
            content: message,
            type: 'success'
        });
    },
    handleError (message) {
        $Toast({
            content: message,
            type: 'error'
        });
    },
    saveButton(){
        this.saveMessageInfo()
    },
    bindKeyInput: function (e) {
        this.setData({
            messageContent: e.detail.value
        })
    },


})