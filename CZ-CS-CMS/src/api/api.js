import fetch from './fetch.js'
import axios from 'axios'
import { JuHe } from './config'
import { getNowFormatDate } from '@/common/utils/dateUtils'

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

//上传本站文章配图
export function uploadArticleImage(data){
  return fetch({
    url:'/cms/article/articleImageUpload',
    method:'post',
    data:data
  })
}

//新建本站文章
export function insertArticle(data){
  return fetch({
    url:'/cms/article/saveArticleInfo',
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
export function initWechatArticleList(data){
  return fetch({
    url:'/cms/wechat/getWechatArticleListPage',
    method:'post',
    data:data
  })
}

//本站文章列表
export function initArticleList(data){
  return fetch({
    url:'/cms/article/getAllArticleInfosList',
    method:'post',
    data:data
  })
}

export function getArticleContent(data){
  return fetch({
    url:'/cms/article/getArticleContentById',
    method:'post',
    data:data
  })
}

export function updateArticleInfo(data){
  return fetch({
    url:'/cms/article/updateArticleInfo',
    method:'post',
    data:data
  })
}

export function publishArticle(data){
  return fetch({
    url:'/cms/article/publishArticle',
    method:'post',
    data:data
  })
}

export function cancelArticle(data){
  return fetch({
    url:'/cms/article/cancelArticle',
    method:'post',
    data:data
  })
}

export function initDailyList(data){
  return fetch({
    url:'/cms/daily/getDailyInfoListPage',
    method:'post',
    data:data
  })
}

export function initBookList(data){
  return fetch({
    url:'/cms/book/queryBookList',
    method:'post',
    data:data
  })
}

export function initContentList(data){
  return fetch({
    url:'/cms/book/queryBookContentByBookId',
    method:'post',
    data:data
  })
}

export function replaceDailyImage(data){
  return fetch({
    url:'/cms/daily/dailyImageReplace',
    method:'post',
    data:data
  })
}

export function uploadDailyImage(data){
  return fetch({
    url:'/cms/daily/dailyImageUpload',
    method:'post',
    data:data
  })
}

export function replaceBookImage(data){
  return fetch({
    url:'/cms/book/bookImageReplace',
    method:'post',
    data:data
  })
}

export function uploadBookImage(data){
  return fetch({
    url:'/cms/book/bookImageUpload',
    method:'post',
    data:data
  })
}

export function updateDailyInfo(data){
  return fetch({
    url:'/cms/daily/uptDailyInfo',
    method:'post',
    data:data
  })
}

export function insertDailyInfo(data){
  return fetch({
    url:'/cms/daily/addDailyInfo',
    method:'post',
    data:data
  })
}

export function insertBookInfo(data){
  return fetch({
    url:'/cms/book/insertBook',
    method:'post',
    data:data
  })
}

export function publishDaily(data){
  return fetch({
    url:'/cms/daily/publishDailyInfo',
    method:'post',
    data:data
  })
}

export function cancelDaily(data){
  return fetch({
    url:'/cms/daily/cancelDailyInfo',
    method:'post',
    data:data
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
  updateWechatArticle,
  uploadArticleImage,
  insertArticle,
  initArticleList,
  getArticleContent,
  updateArticleInfo,
  publishArticle,
  cancelArticle,
  initDailyList,
  uploadDailyImage,
  replaceDailyImage,
  insertDailyInfo,
  updateDailyInfo,
  publishDaily,
  cancelDaily,
  insertBookInfo,
  initBookList,
  replaceBookImage,
  uploadBookImage,
  initContentList
}

export default apiList
