<template>
  <div>
    <div>
      <div style="background-color: #ececec; padding: 20px;">
        <a-row :gutter="16">
          <a-col :span="6">
            <a-card>
              <h1>{{welcome}} {{nickname()}}</h1>
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
                <a-button type="primary">
                  <span class="calender-span">现在是：{{calender.date}}  {{calender.nowTime}}</span>
                </a-button>
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
            <a-col :span="12">
              <a-card><img></a-card>
            </a-col>
            <a-col :span="12">123</a-col>
          </a-col>
          <a-col :span="6">
          </a-col>
        </a-row>
      </div>
      <br>
    </div>
    <div>
      <div style="background-color: #ececec; padding: 20px;">
        <a-row :gutter="16">
          <a-col :span="4">
            <a-collapse default-active-key="1" :bordered="false" accordion class="news-list">
              <a-collapse-panel key="1" header="This is panel header 1" :showArrow="false">
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
              <a-collapse-panel key="2" header="This is panel header 2" :showArrow="false">
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
              <a-collapse-panel key="3" header="This is panel header 3" :showArrow="false">
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
              <a-collapse-panel key="4" header="This is panel header 3" :showArrow="false">
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
          <a-col :span="4">
            <a-collapse default-active-key="1" :bordered="false" accordion class="news-list">
              <a-collapse-panel key="1" header="This is panel header 1" :showArrow="false">
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
              <a-collapse-panel key="2" header="This is panel header 2" :showArrow="false">
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
              <a-collapse-panel key="3" header="This is panel header 3" :showArrow="false">
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
              <a-collapse-panel key="4" header="This is panel header 3" :showArrow="false">
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
          <a-col :span="4">
            <a-collapse default-active-key="1" :bordered="false" accordion class="news-list">
              <a-collapse-panel key="1" header="This is panel header 1" :showArrow="false">
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
              <a-collapse-panel key="2" header="This is panel header 2" :showArrow="false">
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
              <a-collapse-panel key="3" header="This is panel header 3" :showArrow="false">
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
              <a-collapse-panel key="4" header="This is panel header 3" :showArrow="false">
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
          <a-col :span="4">
            <a-collapse default-active-key="1" :bordered="false" accordion class="news-list">
              <a-collapse-panel key="1" header="This is panel header 1" :showArrow="false">
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
              <a-collapse-panel key="2" header="This is panel header 2" :showArrow="false">
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
              <a-collapse-panel key="3" header="This is panel header 3" :showArrow="false">
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
              <a-collapse-panel key="4" header="This is panel header 3" :showArrow="false">
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
          <a-col :span="4">
            <a-collapse default-active-key="1" :bordered="false" accordion class="news-list">
              <a-collapse-panel key="1" header="This is panel header 1" :showArrow="false">
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
              <a-collapse-panel key="2" header="This is panel header 2" :showArrow="false">
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
              <a-collapse-panel key="3" header="This is panel header 3" :showArrow="false">
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
              <a-collapse-panel key="4" header="This is panel header 3" :showArrow="false">
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
          <a-col :span="4">
            <a-collapse default-active-key="1" :bordered="false" accordion class="news-list">
              <a-collapse-panel key="1" header="This is panel header 1" :showArrow="false">
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
              <a-collapse-panel key="2" header="This is panel header 2" :showArrow="false">
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
              <a-collapse-panel key="3" header="This is panel header 3" :showArrow="false">
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
              <a-collapse-panel key="4" header="This is panel header 3" :showArrow="false">
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
  const os = require('os')

  export default {
    name: 'IndexCommon',
    data() {
      return {
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
          today:{},
          future:[]
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
      }

    }
  }

</script>

<style scoped>
  h1 {
    font-size: 30px;
  }

  .calender {
    height: 60px;
    background-color: #FFFFFF;
    border-color: #FFFFFF;
  }

  .calender-span {
    color:#1890FF;
    font-size: 20px;
  }
  .weather{
    margin-right: 0px;
  }
</style>