<template>
  <div>
    <div>
      <div style="padding: 20px;">
        <a-row :gutter="16">
          <a-col :span="6">
            <a-card>
              <h1>{{welcome}} {{nickname()}}</h1>
              <br>
              <a-popover class="calender" title="老黄历" placement="right">
                <template slot="content">
                  <p>阴历：{{calender.huangli.yinli}}</p>
                  <p> 彭祖百忌：{{calender.huangli.baiji}}</p>
                  <p> 吉神宜趋：{{calender.huangli.jishen}}</p>
                  <p> 凶神宜忌：{{calender.huangli.xiongshen}}</p>
                  <p> 五行：{{calender.huangli.wuxing}}</p>
                  <p> 冲煞：{{calender.huangli.chongsha}}</p>
                  <p> 宜：{{calender.huangli.yi}}</p>
                  <p> 忌：{{calender.huangli.ji}}</p>
                </template>
                <span class="calender-span">现在是：{{calender.date}}  {{calender.nowTime}}</span>
              </a-popover>
            </a-card>
          </a-col>
          <a-col :span="6">
            <a-card>
              <h2>您的IP为：{{ip}}</h2>
              <h3>您所在的城市为：{{country}}-{{province}}-{{city}}</h3>
              <h2>您的网络运营商为：{{ISP}}</h2>
            </a-card>
          </a-col>
          <a-col :span="6">
              <a-card>
                <a-col :span="12">
                  <img :src="weather.imgSrc" class="mainWeather">
                  <a-popover class="calender" title="未来几天天气" placement="right">
                    <template slot="content">
                      <p>{{weather.future[0].date}}:{{weather.future[0].weather}}。温度：{{weather.future[0].temperature}}。{{weather.future[0].direct}}</p>
                      <p>{{weather.future[1].date}}:{{weather.future[1].weather}}。温度：{{weather.future[1].temperature}}。{{weather.future[1].direct}}</p>
                      <p>{{weather.future[2].date}}:{{weather.future[2].weather}}。温度：{{weather.future[2].temperature}}。{{weather.future[2].direct}}</p>
                      <p>{{weather.future[3].date}}:{{weather.future[3].weather}}。温度：{{weather.future[3].temperature}}。{{weather.future[3].direct}}</p>
                    </template>
                    <br>
                    <span class="weather-span">今天天气：{{weather.today.weather}}</span>
                  </a-popover>
                </a-col>
                <a-col :span="12">
                    <span>温度：{{weather.today.temperature}}</span><br>
                    <span>湿度：{{weather.today.humidity}}</span><span>&nbsp;&nbsp;&nbsp;&nbsp;空气质量：{{weather.today.aqi}}</span><br>
                    <span>{{weather.today.direct}}{{weather.today.power}}</span><br>
                    <span>白天：{{weather.today.wid.day}}</span><img class="subWeather" :src="weather.today.wid.dayImg"><br>
                    <span>晚上：{{weather.today.wid.night}}</span><img class="subWeather" :src="weather.today.wid.nightImg">
                </a-col>
              </a-card>
          </a-col>
          <a-col :span="6">
            <a-card>

            </a-card>
          </a-col>
        </a-row>
      </div>
    </div>
    <div>
      <div style="padding: 20px;">
        <a-row :gutter="16">
          <a-col :span="6">
            <a-collapse default-active-key="1" :bordered="false" accordion class="news-list">
              <a-collapse-panel key="1" header="This is panel header 1" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="2" header="This is panel header 2" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="3" header="This is panel header 3" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="4" header="This is panel header 3" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="5" header="This is panel header 3" :showArrow="false">
                <a-card hoverable style="width: 230px">
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 230px;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
            </a-collapse>
          </a-col>
          <a-col :span="6">
            <a-collapse default-active-key="1" :bordered="false" accordion class="news-list">
              <a-collapse-panel key="1" header="This is panel header 1" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="2" header="This is panel header 2" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="3" header="This is panel header 3" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="4" header="This is panel header 3" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="5" header="This is panel header 3" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
            </a-collapse>
          </a-col>
          <a-col :span="6">
            <a-collapse default-active-key="1" :bordered="false" accordion class="news-list">
              <a-collapse-panel key="1" header="This is panel header 1" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="2" header="This is panel header 2" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="3" header="This is panel header 3" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="4" header="This is panel header 3" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="5" header="This is panel header 3" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
            </a-collapse>
          </a-col>
          <a-col :span="6">
            <a-collapse default-active-key="1" :bordered="false" accordion class="news-list">
              <a-collapse-panel key="1" header="This is panel header 1" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="2" header="This is panel header 2" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="3" header="This is panel header 3" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="4" header="This is panel header 3" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
              <a-collapse-panel key="5" header="This is panel header 3" :showArrow="false">
                <a-card hoverable>
                  <img
                    slot="cover"
                    alt="example"
                    src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
                    style="width: 100%;height: 180px"
                  />
                  <a-card-meta title="Europe Street beat">
                    <template slot="description">
                      www.instagram.com
                    </template>
                  </a-card-meta>
                </a-card>
              </a-collapse-panel>
            </a-collapse>
          </a-col>
        </a-row>
      </div>


    </div>
  </div>
</template>

<script>
  import Vue from 'vue'
  import { mapGetters } from 'vuex'
  import { getNowFormatDate } from '@/utils/DateUtil'
  import axios from 'axios'
  import { getEnumValue } from '../../utils/util'

  const os = require('os')

  export default {
    name: 'IndexCommon',
    data() {
      return {
        imgs:{
          qing: require('../../assets/images/weather/qing.png'),
          duoyun: require('../../assets/images/weather/duoyun.png'),
          yin: require('../../assets/images/weather/yin.png'),
          yuzhuanqing: require('../../assets/images/weather/yuzhuanqing.png'),
          leizhenyu: require('../../assets/images/weather/leizhenyu.png'),
          bingbao: require('../../assets/images/weather/bingbao.png'),
          yujiaxue: require('../../assets/images/weather/yujiaxue.png'),
          xiaoyu: require('../../assets/images/weather/xiaoyu.png'),
          zhongyu: require('../../assets/images/weather/zhongyu.png'),
          dayu: require('../../assets/images/weather/dayu.png'),
          xiaoxue: require('../../assets/images/weather/xiaoxue.png'),
          zhongxue: require('../../assets/images/weather/zhongxue.png'),
          daxue: require('../../assets/images/weather/daxue.png'),
          wu: require('../../assets/images/weather/wu.png'),
          san: require('../../assets/images/weather/san.png'),
          duoyunzhuanqing: require('../../assets/images/weather/duoyunzhuanqing.png'),
        },
        welcome: '你好',
        ip: "",
        country:"",
        province:"",
        city:"",
        ISP:"",
        calender: {
          date: '',
          nowTime: '',
          huangli: {
            baiji:"",
            chongsha:"",
            ji:"",
            jishen:"",
            wuxing:"",
            xiongshen:"",
            yi:"",
            yinli:""
          }
        },
        weather:{
          imgSrc:"",
          today:{
            date:"",
            temperature:"",
            humidity:"",
            weather:"",
            wid:{
              day:"",
              dayImg:"",
              night:"",
              nightImg:"",
            },
            direct:"",
            power:"",
            aqi:""
          },
          future:[
            {
              date:"",
              temperature:"",
              weather:"",
              wid:{
                day:"",
                dayImg:"",
                night:"",
                nightImg:"",
              },
              direct:""
            },
            {
              date:"",
              temperature:"",
              weather:"",
              wid:{
                day:"",
                dayImg:"",
                night:"",
                nightImg:"",
              },
              direct:""
            },
            {
              date:"",
              temperature:"",
              weather:"",
              wid:{
                day:"",
                dayImg:"",
                night:"",
                nightImg:"",
              },
              direct:""
            },
            {
              date:"",
              temperature:"",
              weather:"",
              wid:{
                day:"",
                dayImg:"",
                night:"",
                nightImg:"",
              },
              direct:""
            }
          ]
        }
      }
    },
    created() {
      this.getWelcome()
      this.getCalender()
      this.getIp()
    },
    mounted() {
      this.getNowTime()
    },
    beforeDestroy() {
      clearInterval(this.timer)
    },
    methods: {
      //获取用户姓名
      ...mapGetters(['nickname']),
      //根据时间调整欢迎语
      getWelcome() {
        let date = new Date()
        let h = date.getHours()
        switch (true) {
          case(h > 6 && h < 11):
            this.welcome = '早上好，'
            break
          case(h >= 11 && h < 13):
            this.welcome = '中午好，'
            break
          case(h >= 13 && h < 16):
            this.welcome = '下午好，'
            break
          case(h >= 16 && h < 19):
            this.welcome = '傍晚好，'
            break
          case(h >= 19 && h < 22):
            this.welcome = '晚上好，'
            break
          case(h >= 22):
            this.welcome = '夜深了，早点休息！'
            break
          default:
            this.welcome = '夜深了，早点休息！'
            break
        }
      },
      //获取客户端IP
      getIp() {
        axios.get('/apis',{}).then(res => {
          res.data = res.data.substring(6,res.data.length)
          this.ip = res.data.substring(0,res.data.indexOf("  来自于："))
          this.getLocation(this.ip)
        })

      },
      getLocation(ip){
        //先从缓存中获取
        if(localStorage.getItem(ip)){
          let locationInfo = JSON.parse(localStorage.getItem(ip))
          this.country = locationInfo.Country
          this.province = locationInfo.Province
          this.city = locationInfo.City
          this.ISP = locationInfo.Isp
        }else{
          this.getLocationFromNet(ip)
        }
        this.getWeather(this.city)
      },
      //获取地址及运营商信息
      getLocationFromNet(ip){
        let ipUrl = Vue.prototype.JuHe.ipUrl + '?key=' + Vue.prototype.JuHe.ipKey + '&ip=' + ip
        axios.get(ipUrl,{
          dataType: 'jsonp',
          crossDomain: true,
          headers: {
            'X-Requested-With': 'XMLHttpRequest',
            'Content-Type': 'application/json; charset=UTF-8',
            'Access-Control-Allow-Origin': '*'
          }
        }).then(res => {
          if(res.data.error_code==0){
            localStorage.removeItem(ip)
            this.country = res.data.result.Country
            this.province = res.data.result.Province
            this.city = res.data.result.City
            this.ISP = res.data.result.Isp
            localStorage.setItem(ip,JSON.stringify(res.data.result))
          }
        })
      },
      getCalender(){
        if(localStorage.getItem("huangliInfo")){
          let huangliInfo = JSON.parse(localStorage.getItem("huangliInfo"))
          this.calender.huangli.baiji = huangliInfo.baiji
          this.calender.huangli.chongsha = huangliInfo.chongsha
          this.calender.huangli.ji = huangliInfo.ji
          this.calender.huangli.jishen = huangliInfo.jishen
          this.calender.huangli.wuxing = huangliInfo.wuxing
          this.calender.huangli.xiongshen = huangliInfo.xiongshen
          this.calender.huangli.yi = huangliInfo.yi
          this.calender.huangli.yinli = huangliInfo.yinli
        }else{
          this.getCalenderFromNet()
        }
      },
      getCalenderFromNet() {
        let calenderUrl = Vue.prototype.JuHe.laohuangliUrl + '?key=' + Vue.prototype.JuHe.laohuangliKey + '&date=' + getNowFormatDate()
        axios.get(calenderUrl, {
          dataType: 'jsonp',
          crossDomain: true,
          headers: {
            'X-Requested-With': 'XMLHttpRequest',
            'Content-Type': 'application/json; charset=UTF-8',
            'Access-Control-Allow-Origin': '*'
          }
        }).then(res => {
          if(res.data.error_code==0){
            localStorage.removeItem("huangliInfo")
            this.calender.huangli.baiji = res.data.result.baiji
            this.calender.huangli.chongsha = res.data.result.chongsha
            this.calender.huangli.ji = res.data.result.ji
            this.calender.huangli.jishen = res.data.result.jishen
            this.calender.huangli.wuxing = res.data.result.wuxing
            this.calender.huangli.xiongshen = res.data.result.xiongshen
            this.calender.huangli.yi = res.data.result.yi
            this.calender.huangli.yinli = res.data.result.yinli
            localStorage.setItem("huangliInfo",JSON.stringify(res.data.result))
          }
        })
      },
      getNowTime() {
        this.calender.date = getNowFormatDate().split('-')[0] + '年' + getNowFormatDate().split('-')[1] + '月' + getNowFormatDate().split('-')[2] + '日'
        var _this = this
        this.timer = setInterval(() => {
          _this.currentTime()
        }, 1000)
      },
      currentTime() {
        let hour = new Date().getHours()
        let minute = new Date().getMinutes()
        if (minute < 10) {
          minute = '0' + minute
        }
        let second = new Date().getSeconds()
        if (second < 10) {
          second = '0' + second
        }
        this.calender.nowTime = hour + ':' + minute + ':' + second
      },
      getWeather(city){
        let date = new Date()
        let h = date.getHours()
        if(localStorage.getItem("weatherInfo")){
          let weatherInfo = JSON.parse(localStorage.getItem("weatherInfo"))
          if(weatherInfo.today.date && weatherInfo.today.date === getNowFormatDate()){
            //今天
            this.weather.today.date = getNowFormatDate()
            this.weather.imgSrc = h>12?this.transformWeatherImg(getEnumValue(Vue.prototype.JuHe.weatherArray,"weather","wid",weatherInfo.today.wid.night)):this.transformWeatherImg(getEnumValue(Vue.prototype.JuHe.weatherArray,"weather","wid",weatherInfo.today.wid.day))
            this.weather.today.temperature = weatherInfo.today.temperature
            this.weather.today.humidity = weatherInfo.today.humidity
            this.weather.today.weather = weatherInfo.today.weather
            this.weather.today.wid.day = weatherInfo.today.wid.day
            this.weather.today.wid.dayImg = this.transformWeatherImg(getEnumValue(Vue.prototype.JuHe.weatherArray,"weather","wid",weatherInfo.today.wid.day))
            this.weather.today.wid.night = weatherInfo.today.wid.night
            this.weather.today.wid.nightImg = this.transformWeatherImg(getEnumValue(Vue.prototype.JuHe.weatherArray,"weather","wid",weatherInfo.today.wid.night))
            this.weather.today.direct = weatherInfo.today.direct
            this.weather.today.power = weatherInfo.today.power
            this.weather.today.aqi = weatherInfo.today.aqi
            //明天
            this.weather.future[0].date = weatherInfo.future[0].date
            this.weather.future[0].temperature = weatherInfo.future[0].temperature
            this.weather.future[0].weather = weatherInfo.future[0].weather
            this.weather.future[0].wid.day = weatherInfo.future[0].wid.day
            this.weather.future[0].wid.dayImg = weatherInfo.future[0].wid.dayImg
            this.weather.future[0].wid.night = weatherInfo.future[0].wid.night
            this.weather.future[0].wid.nightImg = weatherInfo.future[0].wid.nightImg
            this.weather.future[0].direct = weatherInfo.future[0].direct
            //后天
            this.weather.future[1].date = weatherInfo.future[1].date
            this.weather.future[1].temperature = weatherInfo.future[1].temperature
            this.weather.future[1].weather = weatherInfo.future[1].weather
            this.weather.future[1].wid.day = weatherInfo.future[1].wid.day
            this.weather.future[1].wid.dayImg = weatherInfo.future[1].wid.dayImg
            this.weather.future[1].wid.night = weatherInfo.future[1].wid.night
            this.weather.future[1].wid.nightImg = weatherInfo.future[1].wid.nightImg
            this.weather.future[1].direct = weatherInfo.future[1].direct
            //大后天
            this.weather.future[2].date = weatherInfo.future[2].date
            this.weather.future[2].temperature = weatherInfo.future[2].temperature
            this.weather.future[2].weather = weatherInfo.future[2].weather
            this.weather.future[2].wid.day = weatherInfo.future[2].wid.day
            this.weather.future[2].wid.dayImg = weatherInfo.future[2].wid.dayImg
            this.weather.future[2].wid.night = weatherInfo.future[2].wid.night
            this.weather.future[2].wid.nightImg = weatherInfo.future[2].wid.nightImg
            this.weather.future[2].direct = weatherInfo.future[2].direct
            //大大后天
            this.weather.future[3].date = weatherInfo.future[3].date
            this.weather.future[3].temperature = weatherInfo.future[3].temperature
            this.weather.future[3].weather = weatherInfo.future[3].weather
            this.weather.future[3].wid.day = weatherInfo.future[3].wid.day
            this.weather.future[3].wid.dayImg = weatherInfo.future[3].wid.dayImg
            this.weather.future[3].wid.night = weatherInfo.future[3].wid.night
            this.weather.future[3].wid.nightImg = weatherInfo.future[3].wid.nightImg
            this.weather.future[3].direct = weatherInfo.future[3].direct
          }else{
            this.getWeatherFromNet(city)
          }
        }else{
          this.getWeatherFromNet(city)
        }
      },
      getWeatherFromNet(city){
        let cityName = encodeURI(city.substring(0,city.length-1))
        let weatherUrl = Vue.prototype.JuHe.weatherUrl + '?key=' + Vue.prototype.JuHe.weatherKey + '&city=' + cityName
        axios.get(weatherUrl, {
          dataType: 'jsonp',
          crossDomain: true,
          headers: {
            'X-Requested-With': 'XMLHttpRequest',
            'Content-Type': 'application/json; charset=UTF-8',
            'Access-Control-Allow-Origin': '*'
          }
        }).then(res => {
          if(res.data.error_code==0){
            localStorage.removeItem("weatherInfo")
            //今天
            this.weather.today.date = getNowFormatDate()
            this.weather.imgSrc = this.transformWeatherImg(res.data.result.realtime.wid)
            this.weather.today.temperature = res.data.result.future[0].temperature
            this.weather.today.humidity = res.data.result.realtime.humidity
            this.weather.today.weather = res.data.result.future[0].weather
            this.weather.today.wid.day = getEnumValue(Vue.prototype.JuHe.weatherArray,"wid","weather",res.data.result.future[0].wid.day)
            this.weather.today.wid.dayImg = this.transformWeatherImg(res.data.result.future[0].wid.day)
            this.weather.today.wid.night = getEnumValue(Vue.prototype.JuHe.weatherArray,"wid","weather",res.data.result.future[0].wid.night)
            this.weather.today.wid.nightImg = this.transformWeatherImg(res.data.result.future[0].wid.night)
            this.weather.today.direct = res.data.result.future[0].direct
            this.weather.today.power = res.data.result.realtime.power
            this.weather.today.aqi = res.data.result.realtime.aqi
            //明天
            this.weather.future[0].date = res.data.result.future[1].date
            this.weather.future[0].temperature = res.data.result.future[1].temperature
            this.weather.future[0].weather = res.data.result.future[1].weather
            this.weather.future[0].wid.day = res.data.result.future[1].wid.day
            this.weather.future[0].wid.dayImg = this.transformWeatherImg(res.data.result.future[1].wid.day)
            this.weather.future[0].wid.night = res.data.result.future[1].wid.night
            this.weather.future[0].wid.nightImg = this.transformWeatherImg(res.data.result.future[1].wid.night)
            this.weather.future[0].direct = res.data.result.future[1].direct
            //后天
            this.weather.future[1].date = res.data.result.future[2].date
            this.weather.future[1].temperature = res.data.result.future[2].temperature
            this.weather.future[1].weather = res.data.result.future[2].weather
            this.weather.future[1].wid.day = res.data.result.future[2].wid.day
            this.weather.future[1].wid.dayImg = this.transformWeatherImg(res.data.result.future[2].wid.day)
            this.weather.future[1].wid.night = res.data.result.future[2].wid.night
            this.weather.future[1].wid.nightImg = this.transformWeatherImg(res.data.result.future[2].wid.night)
            this.weather.future[1].direct = res.data.result.future[2].direct
            //大后天
            this.weather.future[2].date = res.data.result.future[3].date
            this.weather.future[2].temperature = res.data.result.future[3].temperature
            this.weather.future[2].weather = res.data.result.future[3].weather
            this.weather.future[2].wid.day = res.data.result.future[3].wid.day
            this.weather.future[2].wid.dayImg = this.transformWeatherImg(res.data.result.future[3].wid.day)
            this.weather.future[2].wid.night = res.data.result.future[3].wid.night
            this.weather.future[2].wid.nightImg = this.transformWeatherImg(res.data.result.future[3].wid.night)
            this.weather.future[2].direct = res.data.result.future[3].direct
            //大大后天
            this.weather.future[3].date = res.data.result.future[4].date
            this.weather.future[3].temperature = res.data.result.future[4].temperature
            this.weather.future[3].weather = res.data.result.future[4].weather
            this.weather.future[3].wid.day = res.data.result.future[4].wid.day
            this.weather.future[3].wid.dayImg = this.transformWeatherImg(res.data.result.future[4].wid.day)
            this.weather.future[3].wid.night = res.data.result.future[4].wid.night
            this.weather.future[3].wid.nightImg = this.transformWeatherImg(res.data.result.future[4].wid.night)
            this.weather.future[3].direct = res.data.result.future[4].direct
            localStorage.setItem("weatherInfo",JSON.stringify(this.weather))
          }
        })
      },
      transformWeatherImg(code){
        switch(code){
          //晴
          case"00":
            return this.imgs.qing
          //多云
          case"01":
            return this.imgs.duoyun
          //阴
          case"02":
            return this.imgs.yin
          //阵雨
          case"03":
            return this.imgs.yuzhuanqing
          //雷阵雨
          case"04":
            return this.imgs.leizhenyu
          //雷阵雨伴有冰雹
          case"05":
            return this.imgs.bingbao
          //雨夹雪&冻雨
          case"06":
            return this.imgs.yujiaxue
          case"19":
            return this.imgs.yujiaxue
          //小雨
          case"07":
            return this.imgs.xiaoyu
          //中雨
          case"08":
            return this.imgs.zhongyu
          //大雨&暴雨&大暴雨&特大暴雨
          case"09":
            return this.imgs.dayu
          case"10":
            return this.imgs.dayu
          case"11":
            return this.imgs.dayu
          case"12":
            return this.imgs.dayu
          //阵雪&小雪
          case"13":
            return this.imgs.xiaoxue
          case"14":
            return this.imgs.xiaoxue
          //中雪
          case"15":
            return this.imgs.zhongxue
          //大雪&暴雪
          case"16":
            return this.imgs.daxue
          case"17":
            return this.imgs.daxue
          //雾
          case"18":
            return this.imgs.wu
          //沙尘暴
          case"20":
            return this.imgs.san
          default:
            return this.imgs.duoyunzhuanqing
        }
      }

    }
  }

</script>

<style scoped>
  h1 {
    font-size: 30px;
  }

  .calender {
    height: 54px;
    margin-left: -20px;
    background-color: #FFFFFF;
    border-color: #FFFFFF;
  }

  .calender-span {
    margin-left: -6px;
    color:#1890FF;
    font-size: 22px;
  }

  .mainWeather{
    height: 120px;
    width: 120px;
    margin-top:-22px;
  }
  .subWeather{
    height: 30px;
    width: 30px;
  }

  .weather-span {
    color: #1890ff;
    font-size: 16px;
    margin-left: -20px;
  }
</style>