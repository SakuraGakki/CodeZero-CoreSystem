import {formatTime} from '../../utils/util'
import request from '../../utils/requestUtil'
const { $Message } = require('../../dist/base/index');
var app = getApp();
const baseUrl = app.globalData.baseUrl

Page({
    data: {
      showContent:false,
      newsContent:"",
      actions: [
        {
            name: '点击授权登录',
            color: '#19be6b'
        },
        {
            name: '取消'
        }
      ],
      userInfo: {},
      hasUserInfo: wx.getStorageSync('userInfo')?true:false,
      notice:"欢迎访问CodeZero，电脑端请访问https://www.codezer0.cn 。",
      welcome:"欢迎您",
      backgroundUrl:"https://www.codezer0.cn:8011/images/wechatApp/index/logoHomePage.png",
      current:"home",
      latitude:0,
      longitude:0,
      country:"",
      province:"",
      city:"",
      weather:{
        today:{
          date:"",
          temperature:"",
          humidity:"",
          weather:"",
          direct:"",
          power:"",
          aqi:""
        }
      },
      news:[],
      keywords:[],
      holidayInfo:{}
    },
    onLoad() {
      if(wx.getStorageSync('userInfo')){
        this.setData({hasUserInfo:true,userInfo:wx.getStorageSync('userInfo')})
      }else{
        this.setData({hasUserInfo:false})
      }
      this.getWelcome()
      this.getLocation()
      this.getNews()
      this.getKeywords()
      this.getHoliday()
    },

    //根据时间调整欢迎语
    getWelcome() {
      let date = new Date()
      let h = date.getHours()
      switch (true) {
        case(h > 6 && h < 9):
          this.setData({welcome:"早上好"})
          break
        case(h >= 9 && h < 11):
          this.setData({welcome:"上午好"})
          break
        case(h >= 11 && h < 13):
          this.setData({welcome:"中午好"})
          break
        case(h >= 13 && h < 16):
          this.setData({welcome:"下午好"})
          break
        case(h >= 16 && h < 19):
          this.setData({welcome:"傍晚好"})
          break
        case(h >= 19 && h < 22):
          this.setData({welcome:"晚上好"})
          break
        case(h >= 22):
          this.setData({welcome:"夜深了，早点休息"})
          break
        default:
          this.setData({welcome:"夜深了，早点休息"})
          break
      }
    },
 
    //获取坐标信息
    getLocation() {
      let that = this
      //先获取地理未知信息
      wx.getLocation({
        type: 'wgs84',
        success (res) {
          that.setData({
            latitude:res.latitude,
            longitude:res.longitude
          })
          that.getCityAndWeather()
        }
       })
    },

    //查询地点及天气
    getCityAndWeather(){
      this._request = new request
      this._request.postRequest(baseUrl+"/cms/getLocationAndWeather",{"latitude":this.data.latitude,"longitude":this.data.longitude},{'content-type': 'application/json'}).then(
          res=>{
            if(res.data.status===0){
              this.setData({
                country:res.data.data.country,
                province:res.data.data.province,
                city:res.data.data.city,
                weather:{
                  today:{
                    date:res.data.data.date,
                    temperature:res.data.data.temperature,
                    humidity:res.data.data.humidity,
                    weather:res.data.data.weather,
                    direct:res.data.data.direct,
                    power:res.data.data.power,
                    aqi:res.data.data.aqi
                  }
                }
              })
            }
          }  
        )
    },

    //获取头条消息
    getNews(){
      this._request = new request
      this._request.postRequest(baseUrl+"/cms/getNewsInfo",{},{'content-type': 'application/json'}).then(
          res=>{
            if(res.data.status===0){
              this.setData({
                news:res.data.data
              })
            }
          }  
        )
    },

    //获取热搜
    getKeywords(){
      this._request = new request
      this._request.postRequest(baseUrl+"/cms/getKeywordsInfo",{},{'content-type': 'application/json'}).then(
          res=>{
            if(res.data.status===0){
              let list = new Array();
              list = res.data.data.splice(0,6)
              this.setData({
                keywords:list
              })
            }
          }  
        )
    },

    //获取节假日信息
    getHoliday(){
      this._request = new request
      this._request.postRequest(baseUrl+"/cms/getDaysInfo",{},{'content-type': 'application/json'}).then(
          res=>{
            if(res.data.status===0){
              this.setData({
                holidayInfo:{
                  year:res.data.data.date.split('-')[0],
                  month:res.data.data.date.split('-')[1],
                  day:res.data.data.date.split('-')[2],
                  week:res.data.data.week,
                  statusDesc:res.data.data.statusDesc,
                  gzYear:res.data.data.gzYear,
                  animal:res.data.data.animal,
                  lDate:res.data.data.ldate,
                  lMonth:res.data.data.lmonth,
                  suit:res.data.data.suit,
                  avoid:res.data.data.avoid
                }
              })
            }
          }  
        )
      
    },

    getUserProfile(e) {
      // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
      // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
      wx.getUserProfile({
        desc: '登录后浏览更多功能', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
        success: (res) => {
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
          wx.setStorageSync('userInfo', res.userInfo)
        }
      })
    },

    handleClick ({ detail }) {
        const index = detail.index;
        if (index === 0) {
            this.getUserProfile()
        } else {
            this.onClose()
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
    newInfo(e){
      console.log("e",e)
      // let url = e.currentTarget.dataset.url
      // wx.navigateTo({
      //   url: "../webview/webview?url="+url,
      // })
      let content = e.currentTarget.dataset.content
      this.setData({
        showContent:true,
        newsContent:content
      })
    },
    closeNewsContent(){
      this.setData({
        showContent:false,
        newsContent:""
      })
    },
    onClose(){
      wx.navigateBack()
    }
  })