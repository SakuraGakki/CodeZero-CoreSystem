// pages/articleViewer/articleViewer.js
import request from '../../utils/requestUtil'
var app = getApp();
const baseUrl = app.globalData.baseUrl
Page({

    /**
     * 页面的初始数据
     */
    data: {
        
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (option) {
        let that = this
        that._request = new request
        that._request.postRequest(baseUrl+"/cms/article/getArticleContentById",{"articleId":option.articleId},{'content-type': 'application/json'}).then(
          res=>{
            if(res.data.status===0){
                that.setData({
                    nodes:this.formatRichText(res.data.data.content),
                    title:option.articleTitle,
                    date:option.date
                })
            }
          }  
        )
        that.updateReadNum(option.articleId)
    },
    formatRichText(html){
        let newContent= html.replace(/<img[^>]*>/gi,function(match,capture){
            match = match.replace(/style="[^"]+"/gi, '').replace(/style='[^']+'/gi, '');
            match = match.replace(/width="[^"]+"/gi, '').replace(/width='[^']+'/gi, '');
            match = match.replace(/height="[^"]+"/gi, '').replace(/height='[^']+'/gi, '');
            return match;
        });
        newContent = newContent.replace(/style="[^"]+"/gi,function(match,capture){
            match = match.replace(/width:[^;]+;/gi, 'max-width:100%;').replace(/width:[^;]+;/gi, 'max-width:100%;');
            return match;
        });
        newContent = newContent.replace(/<br[^>]*\/>/gi, '');
        newContent = newContent.replace(/\<img/gi, '<img style="max-width:100%;height:auto;display:block;margin-top:0;margin-bottom:0;"');
        return newContent;
    },

    updateReadNum(id){
        this._request = new request
        this._request.postRequest(baseUrl+"/cms/article/addArticleReadNumWechat",{"articleId":id},{'content-type': 'application/json'})
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

    }
})