import request from '../../utils/requestUtil'
const { $Message } = require('../../dist/base/index');
var app = getApp();
const baseUrl = app.globalData.baseUrl
Page({

    /**
     * 页面的初始数据
     */
    data: {
        current: 'local',
        page:{
            current:1,
            size:10,
            total:100
        },
        wpage:{
            current:1,
            size:10,
            total:100
        },
        articleList:[],
        wechatArticleList:[],
        colorList:['blue','green','red','yellow'],
        tagList:[]
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.queryArticleList()
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
        this.queryArticleList()
        this.queryWechatArticleList()
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

    queryArticleList() {
        this._request = new request
        this._request.postRequest(baseUrl+"/cms/article/getPublishArticleInfosList",{"current":this.data.page.current,
          "size":this.data.page.size},{'content-type': 'application/json'}).then(
            res=>{
              if(res.data.status===0){
                let total = Math.round(res.data.data.total/res.data.data.size)===0?1:Math.round(res.data.data.total/res.data.data.size)

                this.setData({articleList:res.data.data.records,page:{total:total,current:res.data.data.current}})

                let tags = new Array()
                for(let i=0;i<res.data.data.records.length;i++){
                    tags[i] = (res.data.data.records[i].articleTag.split(','))
                }

                this.setData({tagList:tags})
                
              }else {
                  this.handleError("查询本站文章列表失败！")
              }
            }  
          )
      },

      queryWechatArticleList() {
        this._request = new request
        this._request.postRequest(baseUrl+"/cms/wechat/getWechatArticleListPage",{"current":this.data.page.current,
          "size":this.data.page.size},{'content-type': 'application/json'}).then(
            res=>{
              if(res.data.status===0){
                let total = Math.round(res.data.data.total/res.data.data.size)===0?1:Math.round(res.data.data.total/res.data.data.size)

                this.setData({wechatArticleList:res.data.data.records,wpage:{total:total,current:res.data.data.current}})

              }else {
                  this.handleError("查询公众号文章列表失败！")
              }
            }  
          )
      },

    handleChange ({ detail }) {
        //判断上方tab页还是下方tab页
        if(['local','wechat'].indexOf(detail.key)>-1){
            this.setData({
                current: detail.key
            })
        }else{
            if(['more'].indexOf(detail.key)>-1){
                $Message({
                  content: '功能还未完成，敬请期待...',
                  type: 'warning'
                });
                return;
            }
            this.setData({
                current: detail.key
            });
            wx.redirectTo({
              url: '../'+detail.key+'/'+detail.key,
            })
        }
       
    },
    articleDetail(e){
        let articleId = e.currentTarget.dataset.articleid
        let articleTitle = e.currentTarget.dataset.articletitle
        let date = e.currentTarget.dataset.date
        wx.navigateTo({
          url: "../articleViewer/articleViewer?articleId="+articleId+"&articleTitle="+articleTitle+"&date="+date,
        })
      },
      wechatArticleDetail(e){
        let url = e.currentTarget.dataset.url
        wx.navigateTo({
            url: "../webview/webview?url="+url,
        })
      },
})