import fetch from './fetch.js'
import axios from 'axios'
import { JuHe } from './config'
import { getNowFormatDate } from '@/common/utils/dateUtils'
import Vue from "vue";

// 登录
export function login (data) {
  return fetch({
    url: '/cms/login',
    method: 'post',
    data:data,
    dataType: "json",
    contentType: "application/json"
  })
}

// 获取访客信息
export function getVisitorNum(){
  return fetch({
    url: '/website/visitor/getVisitNum',
    method: 'post'
  })
}

//获取首页top5列表数据
export function getTopFiveList(){
  return fetch({
    url:'/website/visitor/topFiveList',
    method: 'post'
  })
}

// 获取访客列表
export function getVisitorInfoList(){
  return fetch({
    url:'/website/visitor/visitorInfoList',
    method: 'post'
  })
}

// 获取10日内访客数量信息
export function getTenVisitNumList() {
  return fetch({
    url:'/website/visitor/tenVisitorNumList',
    method: 'post'
  })
}

//获取未读信息条数
export function getUnreadMessageCount(){
  return fetch({
    url:'/website/messageboard/getUnreadMessageCount',
    method:'post'
  })
}

//获取所有消息及回复列表
export function getMessageListAll(data){
  return fetch({
    url:'/website/messageboard/getMessageListAll',
    method:'post',
    data:data
  })
}

// 获取公网IP
export function getIpInfo(){
  return axios.get('http://myip.ipip.net/',{})
}

// 获取老黄历信息
export function calenderFromNet(){
  let calenderUrl = '/laohuangli/d?key=' + JuHe.laohuangliKey + '&date=' + getNowFormatDate("YYYY-MM-DD")
  return axios.get(calenderUrl,{})
}

//获取天气信息
export function weatherFromNet(city){
  let cityName = encodeURI(city)
  let weatherUrl = '/simpleWeather/query?key=' + JuHe.weatherKey + '&city=' + cityName
  return axios.get(weatherUrl,{})
}

//获取Ip信息
export function locationFromNet(ip){
  let ipUrl = '/ip/ipNewV3?key=' + JuHe.ipKey + '&ip=' + ip
  return axios.get(ipUrl,{})
}

//新增留言
export function addMessageBoardInfo(data){
  return fetch({
    url:'/website/messageboard/adminInsertMessageBoardInfo',
    method:'post',
    data:data
  })
}

//修改留言
export function uptMessageBoardInfo(data) {
  return fetch({
    url:'/website/messageboard/adminUptMessageBoardInfo',
    method:'post',
    data:data
  })
}

//删除留言
export function delMessageBoardInfo(data) {
  return fetch({
    url:'/website/messageboard/adminDeleteMessageBoardList',
    method:'post',
    data:data
  })
}

//上传微信文章配图
export function uploadWechatImage(data){
   return fetch({
    url:'/cms/wechat/wechatImageUpload',
    method:'post',
    data:data
  })
}

//变更微信文章配图
export function replaceWechatImage(data){
  return fetch({
    url:'/cms/wechat/wechatImageReplace',
    method:'post',
    data:data
  })
}

//新建微信文章
export function insertWechatArticle(data){
  return fetch({
    url:'/cms/wechat/insertWechatArticle',
    method:'post',
    data:data
  })
}

//修改微信文章
export function updateWechatArticle(data){
  return fetch({
    url:'/cms/wechat/updateWechatArticle',
    method:'post',
    data:data
  })
}

//微信文章列表查询
export function initWechatArticleList(){
  return fetch({
    url:'/cms/wechat/getWechatArticleList',
    method:'post'
  })
}

const apiList = {
  login,
  getVisitorNum,
  getIpInfo,
  calenderFromNet,
  weatherFromNet,
  locationFromNet,
  getTopFiveList,
  getVisitorInfoList,
  getTenVisitNumList,
  getUnreadMessageCount,
  getMessageListAll,
  addMessageBoardInfo,
  uptMessageBoardInfo,
  delMessageBoardInfo,
  uploadWechatImage,
  insertWechatArticle,
  initWechatArticleList,
  replaceWechatImage,
  updateWechatArticle
}

export default apiList
