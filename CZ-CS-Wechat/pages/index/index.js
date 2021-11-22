// index.js
// 获取应用实例
const app = getApp()

Page({
  data: {
    logoUrl:"http://www.codezer0.cn:8011/images/wechatApp/index/logoHomePage.png",
    menu:"首页",
    title:"欢迎访问CodeZero",
    subTitles:['Visualization','DataManage','CodeDesign','Analytics','Intelligence','Applications']
  },
  // 点击进入首页
  goHomePage() {
    wx.navigateTo({
      url: '../home/home',
    })
  },
  
})

