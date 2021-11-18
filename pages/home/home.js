import {formatTime} from '../../utils/util'
import request from '../../utils/requestUtil'
const { $Message } = require('../../dist/base/index');
var app = getApp();
const baseUrl = app.globalData.baseUrl

Page({
    data: {
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
      notice:"欢迎访问CodeZero，电脑端请访问http://www.codezer0.cn 。",
      welcome:"欢迎您",
      backgroundUrl:"http://www.codezer0.cn:8011/images/wechatApp/index/logoHomePage.png",
      current:"home",
      latitude:0,
      longitude:0,
      country:"",
      province:"",
      city:"",
      weather:{
        imgSrc:"",
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
        this.setData({userInfo:wx.getStorageSync('userInfo')})
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
          that.getCity()
        }
       })
    },

    //根据经纬度坐标获取地理位置
    getCity(){
      let that = this
      wx.request({
        url: 'https://nominatim.openstreetmap.org/reverse?format=json&lat='+that.data.latitude+'&lon='+that.data.longitude+'&zom=18&addressdetails=1',
        header: {
          'content-type': 'application/json' // 默认值
        },
        success (res) {
          that.setData({
            country:res.data.display_name.split(",")[res.data.display_name.split(",").length-1],
            province:res.data.display_name.split(",")[res.data.display_name.split(",").length-3],
            city:res.data.display_name.split(",")[res.data.display_name.split(",").length-4]
          })
          that.getWeather()
        }
      })
    },

    //获取天气信息
    getWeather(){
      let that = this
        if(wx.getStorageSync(formatTime("YYYY-MM-DD")+that.data.country+that.data.province+that.data.city)){
          that.setData({weather:wx.getStorageSync(formatTime("YYYY-MM-DD")+that.data.country+that.data.province+that.data.city)})
        }else{
          // let cityName = encodeURI(this.data.city.substring(0,this.data.city.length-1))
          wx.request({
            url:"https://way.jd.com/jisuapi/weather?city="+this.data.city.substring(0,this.data.city.length-1)+"&appkey=f89330d75709e6c1029839d97aa0004c",
            header: {
              'content-type': 'application/json' // 默认值
            },
            success (res) {
              if(res.data.msg==='查询成功'){
                 //今天
                 that.setData({
                   weather:{
                     today:{
                       date:res.data.result.result.date,
                       temperature:res.data.result.result.templow+"~"+res.data.result.result.temphigh,
                       humidity:res.data.result.result.humidity,
                       weather:res.data.result.result.weather,
                       direct:res.data.result.result.winddirect,
                       power:res.data.result.result.windpower,
                       aqi:res.data.result.result.aqi.aqi
                     }}
                  })
                 wx.setStorageSync(formatTime("YYYY-MM-DD")+that.data.country+that.data.province+that.data.city,that.data.weather)
              }
            }
          })
        }
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
    newInfo(e){
      console.log("e",e)
      let url = e.currentTarget.dataset.url
      wx.navigateTo({
        url: "../webview/webview?url="+url,
      })
    },
    onClose(){
      wx.navigateBack()
    }
  })