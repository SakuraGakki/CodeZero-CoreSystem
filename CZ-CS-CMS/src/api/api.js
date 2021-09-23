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

//
export function locationFromNet(ip){
  let ipUrl = '/ip/ipNewV3?key=' + JuHe.ipKey + '&ip=' + ip
  return axios.get(ipUrl,{})
}

const apiList = {
  login,
  getVisitorNum,
  getIpInfo,
  calenderFromNet,
  weatherFromNet,
  locationFromNet
}

export default apiList
