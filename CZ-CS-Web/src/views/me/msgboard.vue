<template>
  <div class="me">
    <div class="me-msgboard-div">
      <div class="me-msgboard-body scrollbar">
        <!-- 留言板 -->
        <Row style="background:rgba(238,238,238,0.5);padding:20px">
          <Col span="24">
            <Card shadow style="height: 760px">
              <p slot="title" class="me-msgboard-title-font">留言板</p>
              <Card>
                <textarea name="" v-model="messageContent" cols="108" rows="2"
                          style="resize: none;font-size: 20px"></textarea>
                <div style="text-align: right">
                  <Poptip placement="left" width="400px" height="200px">
                    <span style="font-size: 24px;text-align: center;">😀</span>
                    <div slot="content">
                      <ul class="icon_lists clear">
                        <li v-for="(item, index) in emoji" :key="index">
                          <span style="font-size: 24px" @click="selectEmoji(item.text)">{{item.text}}</span>
                        </li>
                      </ul>
                    </div>
                  </Poptip>
                  <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                  <Button type="info" @click="saveMessageInfo()">
                    <span
                      v-if="!loading">&nbsp;&nbsp;&nbsp;&nbsp;提&nbsp;&nbsp;&nbsp;&nbsp;交&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <span v-else>Loading...</span>
                  </Button>
                </div>
              </Card>
              <Card>
                <div v-for = "item in messageList">
                  <Divider>{{item.date}}--来自：{{item.city}}的留言</Divider>
                  <p>{{item.content}}</p>
                  <div style="position: absolute;right:50px" >
                    <Poptip placement="left" width="400px" height="200px" v-if="item.list.length>0">
                      <span style="font-size: 14px;text-align: center;">回复列表</span>
                      <div slot="content">
                        <div v-for = "res in item.list">
                          <span style="word-break:normal; width:500px; display:block; white-space:pre-wrap;word-wrap : break-word ;overflow: hidden;">回复：<b>{{res.content}}。</b></span>
                          <p>时间：{{res.date}}</p>
                          <p>-------------------------------------------</p>
                        </div>
                      </div>
                    </Poptip>
                  </div>

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
  import {EmojisCommon} from "../../utils/data/Emoji";
  import $ajax from "axios";
  import {BaseUrl} from "../../config/config"
  import {getNowFormatDate, getNowFormatTime} from "../../utils/dateUtils"
  import Qs from 'qs'

  $ajax.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
  $ajax.defaults.transformRequest = [obj => Qs.stringify(obj)]

  export default {
    name: "MessageBoard",
    data() {
      return {
        page:{
          current:1,
          size:6,
          total:100
        },
        loading: false,
        emoji: EmojisCommon.map(emoji => ({text: emoji})),
        messageContent: "",
        ip: localStorage.getItem("ip"),
        city: localStorage.getItem("city"),
        messageList: []
      }
    },
    methods: {
      selectEmoji(emoji) {
        this.messageContent += emoji
      },
      saveMessageInfo() {
        this.loading = true
        $ajax.post(BaseUrl.ip + BaseUrl.insertMessageInfo, {
          "ip": this.ip,
          "city": this.city,
          "date": getNowFormatDate("YYYY-MM-DD") + " " + getNowFormatTime("hh:mm:ss"),
          "content": this.messageContent
        }).then(
          res => {
            this.loading = false
            if (res.data.status === 0) {
              this.$Message.success('留言成功！')
              this.messageContent = ""
              this.queryMessageList()
            } else {
              this.$Message.error('留言失败！')
            }
          }
        )
      },
      queryMessageList() {
        $ajax.post(BaseUrl.ip + BaseUrl.queryMessageList, {
          current:this.page.current,
          size:this.page.size
        }).then(
          res => {
            if (res.data.status === 0) {
              this.messageList = res.data.data.records
              this.page.total = res.data.data.total
              // console.log("this.messageList",res.data.data)
            } else {
              this.$Message.error('查询留言列表失败！')
            }
          }
        )
      },
      pageChange(pageNum){
        this.page.current = pageNum
        this.queryMessageList();
      }
    },
    created() {
      this.queryMessageList()
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
    height: calc(100vh);
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
