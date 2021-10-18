<template>
  <div class="me">
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
        <!-- 最新动态 -->
        <Row style="background:rgba(238,238,238,0.5);padding:20px">
          <Col span="24">
            <Card shadow>
              <p slot="title" class="me-index-title-font">最新动态</p>
              <p class="me-index-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
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
        poem1:"",
        poem2:"",
        headerImgSrc:"http://59.110.218.235/images/avator.jpeg",
        wechatArticleList:[]
      }
    },
    created() {
      this.getPoem();
      this.getWechatArticleList()
    },
    methods:{
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
    height: calc(100vh);
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
</style>
