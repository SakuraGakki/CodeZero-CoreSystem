// 接口配置
// 服务器接口根地址
var config = require('../../config')
var isProduction = process.env.NODE_ENV === 'production'
if (isProduction) {
  console.log('%c 如果你看到这条 log , 说明当前是生产环境', 'font-size:14px;color:#f00;background:#000')
} else {
  console.log('%c 如果你看到这条 log , 说明当前是开发环境', 'font-size:14px;color:#f00;background:#000')
}
// 服务器地址
// export const SERVER_BASE_URL = isProduction ? 'http://gank.io' : 'www.baidu.com'
export const SERVER_BASE_URL = isProduction ? config.build.baseServerUrl : config.dev.baseServerUrl

// export const SERVER_BASE_URL = 'http://gank.io/api'
// global.SERVER_BASE_URL='http://localhost:9999/fwone-central'
export const ERR_OK = 0

export const JuHe = {
  laohuangliUrl:"http://v.juhe.cn/laohuangli/d",
  laohuangliKey:"8b4efc5de984957674d5df29b32828ef",
  ipUrl:"http://apis.juhe.cn/ip/ipNewV3",
  ipKey:"f2bbbc87977dfc23066cf7d38f002897",
  weatherUrl:"http://apis.juhe.cn/simpleWeather/query",
  weatherKey:"1fec979225258fe5aeea1b2867766c46",
  weatherArray:[ { "wid": "00", "weather": "晴" }, { "wid": "01", "weather": "多云" }, { "wid": "02", "weather": "阴" }, { "wid": "03", "weather": "阵雨" }, { "wid": "04", "weather": "雷阵雨" }, { "wid": "05", "weather": "雷阵雨伴有冰雹" }, { "wid": "06", "weather": "雨夹雪" }, { "wid": "07", "weather": "小雨" }, { "wid": "08", "weather": "中雨" }, { "wid": "09", "weather": "大雨" }, { "wid": "10", "weather": "暴雨" }, { "wid": "11", "weather": "大暴雨" }, { "wid": "12", "weather": "特大暴雨" }, { "wid": "13", "weather": "阵雪" }, { "wid": "14", "weather": "小雪" }, { "wid": "15", "weather": "中雪" }, { "wid": "16", "weather": "大雪" }, { "wid": "17", "weather": "暴雪" }, { "wid": "18", "weather": "雾" }, { "wid": "19", "weather": "冻雨" }, { "wid": "20", "weather": "沙尘暴" }, { "wid": "21", "weather": "小到中雨" }, { "wid": "22", "weather": "中到大雨" }, { "wid": "23", "weather": "大到暴雨" }, { "wid": "24", "weather": "暴雨到大暴雨" }, { "wid": "25", "weather": "大暴雨到特大暴雨" }, { "wid": "26", "weather": "小到中雪" }, { "wid": "27", "weather": "中到大雪" }, { "wid": "28", "weather": "大到暴雪" }, { "wid": "29", "weather": "浮尘" }, { "wid": "30", "weather": "扬沙" }, { "wid": "31", "weather": "强沙尘暴" }, { "wid": "53", "weather": "霾" } ]
}

