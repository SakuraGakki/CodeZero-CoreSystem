import fetch from './fetch.js'
import axios from 'axios'

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

// 获取公网IP
export function getIpInfo(){
  return axios.get('http://myip.ipip.net/',{})
}

const apiList = {
  login,
  getIpInfo
}

export default apiList
