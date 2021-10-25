<template>
  <div class="me">
    <div class="me-msgboard-div">
      <div class="me-msgboard-body scrollbar">
        <!-- 留言板 -->
        <Row style="background:rgba(238,238,238,0.5);padding:20px">
          <Col span="24">
            <Card shadow style="height: calc(100vh - 60px)">
              <p slot="title" class="me-msgboard-title-font">我的文章</p>
              <Card>
                <div v-for = "item in articleList">
                  <Divider><router-link :to="{name: 'articleContent',params:item}">《{{item.articleTitle}}》</router-link></Divider>
                  标签：<Tag v-for="(item,index) in item.articleTag" :key="item" :name="item" :color="tagColor[index%4]">{{item}}</Tag><br>
                  <span>发布时间：{{item.date}}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>阅读数：{{item.readNum}}</span>
                </div>
                <br>
                <Page :total="page.total" :page-size="page.size" @on-change="pageChange" show-total></Page>
              </Card>
            </Card>
          </Col>
        </Row>
      </div>
    </div>
  </div>
</template>

<script>
  import $ajax from "axios";
  import Qs from 'qs'
  import Vue from "vue";

  $ajax.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
  $ajax.defaults.transformRequest = [obj => Qs.stringify(obj)]

  export default {
    name: "ArticleList",
    data() {
      return {
        page:{
          current:1,
          size:6,
          total:100
        },
        tagColor:['blue','green','red','yellow'],
        loading: false,
        ip: localStorage.getItem("ip"),
        city: localStorage.getItem("city"),
        articleList: []
      }
    },
    methods: {
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
      queryArticleList() {
        $ajax.post('/cms/article/getPublishArticleInfosList', {
          current:this.page.current,
        }).then(
          res => {
            if (res.data.status === 0) {
              this.articleList = res.data.data.records
              for(let i=0;i<this.articleList.length;i++){
                if(this.articleList[i].articleTag){
                  if(this.articleList[i].articleTag.indexOf(",")>-1){
                    this.articleList[i].articleTag = this.articleList[i].articleTag.split(",")
                  }else{
                    let array = new Array()
                    array.push(this.articleList[i].articleTag)
                    this.articleList[i].articleTag = array
                  }
                }
              }
              this.page.total = res.data.data.total
              // console.log("this.messageList",res.data.data)
            } else {
              this.$Message.error('文章留言列表失败！')
            }
          }
        )
      },
      pageChange(pageNum){
        this.page.current = pageNum
        this.queryArticleList();
      }
    },
    created() {
      this.getClientInfo()
      this.queryArticleList()
    }
  }
</script>

<style scoped>
  .me {
    background-image: url("../../assets/img/me/background.jpg");
    background-size: 100% 100%;
    background-repeat: no-repeat;
  }

  .me-msgboard-div {
    margin: 0 auto;
    width: 1200px;
    height: calc(100vh);
    box-shadow: 2px 2px 10px #909090;
  }

  .me-msgboard-body {
    width: 100%;
    height: calc(100vh - 60px);
    overflow: auto;
    background-color: rgba(0, 0, 0, 0);
  }

  .me-msgboard-title-font {
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    font-size: 20px;
  }

  .scrollbar::-webkit-scrollbar { /*滚动条整体样式*/
    display: none; /*控制滚动条显示隐藏*/
    width: 5px; /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
  }

  .scrollbar::-webkit-scrollbar-thumb { /*滚动条里面小方块*/
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    background: #535353;
  }

  .scrollbar::-webkit-scrollbar-track { /*滚动条里面轨道*/
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    border-radius: 10px;
    background: #EDEDED;
  }

  .icon_lists {
    width: 500px;
    height: 300px;
  }

  .icon_lists li {
    float: left;
    width: 40px;
    height: 40px;
    text-align: center;
    list-style: none !important;
  }

  /* 清除浮动 */
  .ks-clear:after, .clear:after {
    content: '\20';
    display: block;
    height: 0;
    clear: both;
  }

  .ks-clear, .clear {
    *zoom: 1;
  }
</style>
