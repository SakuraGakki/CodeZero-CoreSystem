// pages/message/message.js
import {formatTime} from '../../utils/util'
import request from '../../utils/requestUtil'
const { $Message } = require('../../dist/base/index');
var app = getApp();
const baseUrl = app.globalData.baseUrl
Page({

    /**
     * 页面的初始数据
     */
    data: {
        current:"message",
        page:{
            current:1,
            size:5,
            total:100
        },
        messageContent: "",
        ip: wx.getStorageSync('userInfo').avatarUrl,
        city: wx.getStorageSync('userInfo').nickName,
        messageList: []
    },

    pageChange ({ detail }) {
        console.log(detail)
        const type = detail.type;
        if (type === 'next') {
            this.setData({
                page:{current: this.data.page.current + 1}
            });
        } else if (type === 'prev') {
            this.setData({
                page:{current: this.data.page.current - 1}
            });
        }
        console.log("当前页码："+this.data.page.current)
        this.queryMessageList()
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
      this._request = new request
      this._request.postRequest(baseUrl+"/website/messageboard/getMessageBoardListWechat",{"current":this.data.page.current,
        "size":this.data.page.size},{'content-type': 'application/json'}).then(
          res=>{
              console.log("留言列表查询结果："+res)
            if(res.data.status===0){
            let total = Math.round(res.data.data.total/res.data.data.size)===0?1:Math.round(res.data.data.total/res.data.data.size)
            this.setData({messageList:res.data.data.records,page:{total:total,current:res.data.data.current,size:res.data.data.size}})
            }else {
                this.handleError("查询留言列表失败！")
            }
          }  
        )
    },
    saveMessageInfo() {
      if(this.data.messageContent){
        this._request = new request
        this._request.postRequest(baseUrl+"/website/messageboard/adminInsertMessageBoardInfo",{"ip": this.data.ip,
          "city": this.data.city,
          "date": formatTime('YYYY-MM-DD hh:mm:ss'),
          "content": this.data.messageContent},{'content-type': 'application/json'}).then(
            res=>{
              if(res.data.status===0){
                  this.queryMessageList()
                  this.setData({
                      messageContent: ""
                  })
              }else {
                  this.handleError("留言保存失败！")
              }
            }  
          )
      }else{
        $Message({
            content: '请输入留言内容再保存！',
            type: 'error'
        });
      }
      
    },
    handleChange ({ detail }) {
        if(['more'].indexOf(detail.key)>-1){
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