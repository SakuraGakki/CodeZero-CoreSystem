<template>
  <div>
    <br>
    <div style="margin-left: 40px"><h2>JSON格式在线转换</h2></div>
    <br>
    <Row type="flex" justify="space-around" >
      <Col span="10">
        <Card style="height: 600px">
          <p slot="title">转换前JSON字符串</p>
          <Input v-model="stringValue" type="textarea" :autosize="{minRows: 24,maxRows: 24}"  placeholder="JSON字符串" />
        </Card>
      </Col>
      <Col span="10">
        <Card style="height: 600px">
          <p slot="title">转换后JSON结构图</p>
          <json-viewer
            :value="stringValue?JSON.parse(stringValue):stringValue"
          :expand-depth="5"
          copyable
          boxed
          sort
            theme="myTheme"
          >
          </json-viewer>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<script>
  import $ajax from "axios";
  import Vue from "vue";

  export default {
    name: "jsonUtil",
    data() {
      return {
        ip:"",
        city:"",
        stringValue:"",
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
    },
    created() {
      this.getClientInfo()
    }
  }
</script>

<style lang="scss" scoped>
  .myTheme {
    background: #fff;
    white-space: nowrap;
    color: #525252;
    font-size: 14px;
    font-family: Consolas,Menlo,Courier,monospace;
    height: 510px;
    overflow: scroll;
  }
</style>
