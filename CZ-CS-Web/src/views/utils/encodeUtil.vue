<template>
  <Tabs type="card" style="margin-left: 20px;margin-top: 20px">
    <Tab-pane label="BASE64编码解码">
      <div>
      <br>
      <div style="margin-left: 40px"><h2>BASE64编码解码</h2></div>
      <br>
      <Row type="flex" justify="space-around" >
        <Col span="10">
          <Card style="height: 600px">
            <p slot="title">解码字符串</p>
            <Input v-model="sourceCodeString" type="textarea" :autosize="{minRows: 24,maxRows: 24}"  placeholder="解码字符串" />
          </Card>
        </Col>
        <Col>
          <Button type="primary" size="large" @click="base64Encode">Base64转码</Button>
          <br><br><br>
          <Button type="primary" size="large" @click="base64Decode">Base64解码</Button>
          <br><br><br>
          <Button type="success" size="large" @click="refreshBase64Code">清空</Button>
        </Col>
        <Col span="10">
          <Card style="height: 600px">
            <p slot="title">BASE64编码</p>
            <Input v-model="base64CodeString" type="textarea" :autosize="{minRows: 24,maxRows: 24}"  placeholder="BASE64编码" />
          </Card>
        </Col>
      </Row>
    </div>
    </Tab-pane>
    <Tab-pane label="UrlEncode&UrlDecode">
      <div>
        <br>
        <div style="margin-left: 40px"><h2>UrlEncode&UrlDecode</h2></div>
        <br>
        <Row type="flex" justify="space-around" >
          <Col span="10">
            <Card style="height: 600px">
              <p slot="title">URL</p>
              <Input v-model="urlCodeString" type="textarea" :autosize="{minRows: 24,maxRows: 24}"  placeholder="Url" />
            </Card>
          </Col>
          <Col>
            <Button type="primary" size="large" @click="urlEncode">URLEncode<Icon type="ios-arrow-thin-right"></Icon></Button>
            <br><br><br>
            <Button type="primary" size="large" @click="urlDecode"><Icon type="ios-arrow-thin-left"></Icon>URLDecode</Button>
            <br><br><br>
            <Button type="success" size="large" @click="refreshUrlCode"><Icon type="ios-reload"></Icon>清空</Button>
          </Col>
          <Col span="10">
            <Card style="height: 600px">
              <p slot="title">URLEncode</p>
              <Input v-model="urlEncodeString" type="textarea" :autosize="{minRows: 24,maxRows: 24}"  placeholder="UrlEncode" />
            </Card>
          </Col>
        </Row>
      </div>
    </Tab-pane>
  </Tabs>

</template>

<script>
  import $ajax from "axios"
  import Vue from "vue"
  import {Base64} from 'js-base64'
  import urlencode from "urlencode"

  export default {
    name: "encodeUtil",
    data() {
      return {
        ip:"",
        city:"",
        sourceCodeString:"",
        base64CodeString:"",
        urlCodeString:"",
        urlEncodeString:""
      }
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
      base64Encode(){
        this.base64CodeString = Base64.encode(this.sourceCodeString)
      },
      base64Decode(){
        this.sourceCodeString = Base64.decode(this.base64CodeString)
      },
      refreshBase64Code(){
        this.base64CodeString = ""
        this.sourceCodeString = ""
      },
      urlEncode(){
        this.urlEncodeString = urlencode(this.urlCodeString)
      },
      urlDecode(){
        this.urlCodeString = urlencode.decode(this.urlEncodeString)
      },
      refreshUrlCode(){
        this.urlEncodeString = ""
        this.urlCodeString = ""
      }
    },
    created() {
      this.getClientInfo()
    }
  }
</script>

