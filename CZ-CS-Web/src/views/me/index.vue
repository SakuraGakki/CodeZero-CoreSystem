<template>
  <div class="me">
    <!-- 里程碑展示 -->
    <div style="height: calc(100vh);width: 180px;z-index: 999;margin-left:50px;padding-left:10px;padding-top:20px;position: absolute;float: top">
      <span style="font-size: 18px;color:#13c2c2">里程碑</span>
      <br><br>
      <Timeline pending>
        <Timeline-item color="green">
          <p class="time">2021年8月11日</p>
          <p class="content">CodeZero主站上线</p>
        </Timeline-item>
        <Timeline-item color="green">
          <p class="time">2021年9月23日</p>
          <p class="content">CodeZero--CMS上线</p>
        </Timeline-item>
        <Timeline-item color="green">
          <p class="time">2021年9月30日</p>
          <p class="content">留言板功能上线(2021年10月26日公安备案需要该功能已删)</p>
        </Timeline-item>
        <Timeline-item color="green">
          <p class="time">2021年10月12日</p>
          <p class="content">CodeZero及CMS数据联通</p>
        </Timeline-item>
        <Timeline-item color="green">
          <p class="time">2021年10月19日</p>
          <p class="content">在线工具功能上线</p>
        </Timeline-item>
        <Timeline-item color="green">
          <p class="time">2021年10月21日</p>
          <p class="content">我的文章功能上线</p>
        </Timeline-item>
        <Timeline-item color="green">
          <p class="time">2021年10月25日</p>
          <p class="content">我的日常功能上线</p>
        </Timeline-item>
        <Timeline-item color="green">
          <p class="time">2021年10月26日</p>
          <p class="content">服务器及域名备案通过，www.codezer0.cn上线</p>
        </Timeline-item>
        <Timeline-item>
          <p class="time">未来</p>
          <p class="content">未来可期，仍将继续！</p>
        </Timeline-item>
      </Timeline>
    </div>
    <div class="me-index-div">
      <div class="me-index-header">
        <div class="head-img-div">
          <span class="head-poem">{{poem1}}<br>{{poem2}}</span>
          <img class="head-img" :src="headerImgSrc">
        </div>
      </div>
      <div class="me-index-body scrollbar">
        <!-- 基本信息 -->
        <Row style="background:rgba(238,238,238,0.5);padding:20px">
          <Col span="11">
            <Card shadow>
              <p slot="title" class="me-index-title-font">基本信息</p>
              <p class="me-index-content-font">姓名：CodeZero</p>
              <p class="me-index-content-font">擅长领域：Java、JavaScript、HTML</p>
              <p class="me-index-content-font">爱好：音乐、电影、动漫、编程</p>
              <p class="me-index-content-font">技能：微服务、node、Vue、Angular</p>
            </Card>
          </Col>
          <Col span="11" offset="2">
            <Card shadow>
              <p slot="title" class="me-index-title-font">社交平台信息</p>
              <p class="me-index-content-font">抖音：CodeZer0</p>
              <p class="me-index-content-font">公众号：二进制艺术</p>
              <p class="me-index-content-font">Email：739230137@qq.com</p>
              <p class="me-index-content-font">GitHub：https://github.com/SakuraGakki</p>
            </Card>
          </Col>
        </Row>
        <!-- 我的日常 -->
        <Row style="background:rgba(238,238,238,0.5);padding:20px">
          <Col span="24">
            <Card shadow>
              <p slot="title" class="me-index-title-font">我的日常</p>
              <div style="height: 300px;width: 100%">
                <Carousel v-model="value1" v-if="dynamicList"
                          :autoplay="setting.autoplay"
                          :autoplay-speed="setting.autoplaySpeed"
                          :dots="setting.dots"
                          :radius-dot="setting.radiusDot"
                          :trigger="setting.trigger"
                          :arrow="setting.arrow">
                  <Carousel-item v-for="item in dynamicList">
                    <div class="demo-carousel">
                      <Row type="flex" justify="space-around" >
                        <Col :span="12">
                          <img :src="item.imageUrl" style="height: 290px;width: 100%">
                        </Col>
                        <Col :span="12">
                          <Card style="height: 290px">
                            <P slot="title">时间：{{item.date}}</P>
                            <span style="font-size: 28px;">{{item.content}}</span>
                          </Card>
                        </Col>
                      </Row>
                    </div>
                  </Carousel-item>
                </Carousel>
              </div>
            </Card>
          </Col>
        </Row>
        <!-- 最新文章 -->
        <Row style="background:rgba(238,238,238,0.5);padding:20px">
          <Col span="24">
            <Card shadow>
              <p slot="title" class="me-index-title-font">公众号最新文章</p>
              <Row>
                <Col span="6" v-for="item in wechatArticleList">
                  <Card>
                    <div style="text-align: center">
                      <img :src="item.imgUrl" style="width: 250px;height: 100px"/>
                      <h3><a :href="item.url" target="_blank">{{item.title}}</a></h3>
                    </div>
                  </Card>
                </Col>
              </Row>
            </Card>
          </Col>
        </Row>
      <br>
      </div>
    </div>
  </div>
</template>

<script>
  import $ajax from 'axios'
  import Vue from "vue";

  const poemsArray = [
    ["长风破浪会有时","直挂云帆济沧海"],
    ["山重水复疑无路","柳暗花明又一村"],
    ["莫愁前路无知己","天下谁人不识君"],
    ["此情可待成追忆","只是当时已惘然"],
    ["春蚕到死丝方尽","蜡炬成灰泪始干"],
    ["身无彩凤双飞翼","心有灵犀一点通"],
    ["沉舟侧畔千帆过","病树前头万木春"],
    ["无边落木萧萧下","不尽长江滚滚来"],
    ["孤帆远影碧空尽","唯见长江天际流"],
    ["飞流直下三千尺","疑是银河落九天"]
  ];
  export default {
    name: "MeIndex",
    data() {
      return {
        baseImgUrl:'',
        value1:0,
        setting: {
          autoplay: true,
          autoplaySpeed: 3000,
          dots: 'inside',
          trigger: 'hover',
          arrow: 'hover'
        },
        ip:"",
        city:"",
        poem1:"",
        poem2:"",
        headerImgSrc:"https://www.codezer0:8011/images/avator.jpeg",
        wechatArticleList:[],
        dynamicList:[]
      }
    },
    created() {
      this.getPoem()
      this.getWechatArticleList()
      this.getClientInfo()
      this.getDailyList()
    },
    methods:{
      //获取客户端信息
      getClientInfo(){
        if(localStorage.hasOwnProperty("ip") && localStorage.hasOwnProperty("city")){
          this.ip = localStorage.getItem("ip")
          this.city = localStorage.getItem("city")
        }else{
          $ajax.get('http://myip.ipip.net/',{}).then(
            res => {
              res.data = res.data.substring(6,res.data.length)
              this.ip = res.data.substring(0,res.data.indexOf("  来自于："))
              this.city = res.data.substring(res.data.indexOf("  来自于：")).replace("  来自于：","")
              localStorage.setItem("ip",this.ip)
              localStorage.setItem("city",this.city)
              this.uptVisitorNum()
            }
          )
        }
        this.uptVisitorNum()
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
          }
        })
      },
      //获取页面右上方文字内容
      getPoem(){
        let random = Math.floor(Math.random()*10);
        this.poem1 = poemsArray[random][0];
        this.poem2 = poemsArray[random][1];
      },
      //主页从cms系统获取微信文章信息
      getWechatArticleList(){
        $ajax.post('/cms/wechat/getWechatArticleList').then(res=>{
          if(res.data.status == 0){
            this.wechatArticleList = res.data.data
          }
        })
      },
      //主页从CMS系统获取日常信息
      getDailyList(){
        $ajax.post('/cms/daily/getDailyInfoList').then(res=>{
          if(res.data.status == 0){
            this.dynamicList = res.data.data
          }
        })
      }
    }
  }
</script>

<style scoped>
  .me{
    background-image: url("../../assets/img/me/background.jpg");
    background-size:100% 100%;
    background-repeat:no-repeat;
  }
  .me-index-div {
    margin: 0 auto;
    width: 1200px;
    height: calc(100vh - 75px);
    box-shadow: 2px 2px 10px #909090;
  }

  .me-index-header {
    width: 100%;
    height: 180px;
    background-image: url("../../assets/img/me/bg1.jpg");
  }

  .head-img-div {
    position: absolute;
    width: 200px;
    height: 200px;
    border-radius: 200px;
    background-color: #3e3b3c;
    z-index: 1;
    left: 50%;
    top: 200px;
    transform: translate(-50%, -50%);
  }

  .head-img {
    width: 200px;
    height: 200px;
    border-radius: 200px;
  }

  .head-poem{
    display:block;
    position: absolute;
    right:-476px;
    top:0px;
    font-family: "楷体";
    font-size: 36px;
    /*文字竖排显示*/
    /*writing-mode: vertical-rl;!*从右向左 从左向右是 writing-mode: vertical-rl;*!*/
    height:260px;
    color:#FFFFFF;
  }

  .me-index-body {
    width: 100%;
    height:calc(100vh - 210px);
    overflow: auto;
    background-color: rgba(0, 0, 0, 0);
  }

  .me-index-title-font {
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
    font-size: 20px;
  }

  .me-index-content-font {
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
    font-size: 20px;
  }

  .scrollbar::-webkit-scrollbar {/*滚动条整体样式*/
    display: none;  /*控制滚动条显示隐藏*/
    width: 5px;     /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
  }
  .scrollbar::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    background: #535353;
  }
  .scrollbar::-webkit-scrollbar-track {/*滚动条里面轨道*/
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    border-radius: 10px;
    background: #EDEDED;
  }

  .time{
    color:#00ffff;
    font-size: 14px;
    font-weight: bold;
  }
  .content{
    color:#2db7f5;
    padding-left: 5px;
  }

  .dynamic{
    display: inline-block;
    line-height: 24px;
    padding: 5px 0;
    margin-right: 10px;
    word-wrap: break-word;
    word-break: break-all;
    overflow: hidden;
    color: #13c2c2;
    font-size: 30px;
  }
</style>
