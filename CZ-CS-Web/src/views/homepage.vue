<template>
  <ul class="cb-slideshow">
    <li><span></span><div><h3>Visualization</h3></div></li>
    <li><span></span><div><h3>Big Data Management</h3></div></li>
    <li><span></span><div><h3>Code Design</h3></div></li>
    <li><span></span><div><h3>Analytics</h3></div></li>
    <li><span></span><div><h3>Artificial Intelligence</h3></div></li>
    <li><span></span><div><h3>Internet Applications</h3></div></li>
  </ul>
</template>

<script>

import Vue from 'vue'
import $ajax from 'axios'
import Qs from 'qs'

$ajax.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
$ajax.defaults.transformRequest = [obj => Qs.stringify(obj)]

export default {
  name: 'HomePage',
  data () {
    return {
      ip:"",
      city:""
    }
  },
  mounted(){
    // 实现APP的高度占满全屏
    //$('.bg').height($(window).height())
  },
  created() {
    this.getClientInfo();
  },
  methods:{
    //获取客户端信息
    getClientInfo(){
      $ajax.get('http://myip.ipip.net/',{}).then(
        res => {
          res.data = res.data.substring(6,res.data.length)
          this.ip = res.data.substring(0,res.data.indexOf("  来自于："))
          this.city = res.data.substring(res.data.indexOf("  来自于：")).replace("  来自于：","")
          this.uptVisitorNum()
        }
      )
    },
    //更新访客信息
    uptVisitorNum(){
      $ajax.post(
        Vue.config.BaseUrl.ip + Vue.config.BaseUrl.uptVisitNum,
        {
          "ip":this.ip,
          "city":this.city
        },
        {}
      ).then(data => {
          // console.log("更新访问信息返回值",data)
          if(data.data.status === 0){
            localStorage.setItem("ip",this.ip)
            localStorage.setItem("city",this.city)
            this.getVisitorNum();
          }else{

          }
      })
    },
    //获取访客数量
    getVisitorNum(){
      $ajax.post(
        Vue.config.BaseUrl.ip + Vue.config.BaseUrl.getVisitNum,
        {}
      ).then(data => {
        // console.log("查询访客数量返回值",data)
        if(data.status === 0){

        }else{

        }
      })
    }
  }
}
</script>
<style scoped>
  @import '../assets/css/demo.css';
  @import '../assets/css/loginStyle.css';

</style>
