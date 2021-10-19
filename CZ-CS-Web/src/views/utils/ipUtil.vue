<template>
  <div>
    <br>
    <div style="margin-left: 40px"><h2>在线ip地址查询</h2></div>
    <br><br>
    <Row type="flex" justify="space-around">
      <Col span="20">
        <Card>
          <p slot="title">本地IP信息</p>
          <span style="text-align:center"><h2>本地IP：{{ip}}</h2></span>
          <span style="text-align:center"><h2>所在地：{{city}}</h2></span>
        </Card>
      </Col>
    </Row>
    <br><br>
    <Row type="flex" justify="space-around">
      <Col span="20">
        <Card>
          <p slot="title">查询IP信息</p>
          <div style="text-align: center">
            <span
              style="font-size: 28px;font-family:Helvetica Neue,Helvetica,PingFang SC,Hiragino Sans GB,Microsoft YaHei,微软雅黑,Arial,sans-serif;">输入IP：</span>
            <Input size="large" style="width: 300px"></Input>
            <Button size="large" type="success" @click="queryCity">查询</Button>
          </div>
          <br><br>
          <div style="text-align: center" v-if="this.searchCity">
            <span
              style="font-size: 28px;font-family:Helvetica Neue,Helvetica,PingFang SC,Hiragino Sans GB,Microsoft YaHei,微软雅黑,Arial,sans-serif;">所在地：{{searchCity}}</span>
          </div>
        </Card>
      </Col>
    </Row>
  </div>

</template>

<script>

  import $ajax from "axios";
  import Vue from "vue";

  export default {
    name: "ipUtil",
    data() {
      return {
        ip: "",
        city: "",
        searchIp: "",
        searchCity: ""
      }
    },
    methods: {
      //获取客户端信息
      getClientInfo() {
        if (localStorage.hasOwnProperty("ip") && localStorage.hasOwnProperty("city")) {
          this.ip = localStorage.getItem("ip")
          this.city = localStorage.getItem("city")
        } else {
          $ajax.get('http://myip.ipip.net/', {}).then(
            res => {
              res.data = res.data.substring(6, res.data.length)
              this.ip = res.data.substring(0, res.data.indexOf("  来自于："))
              this.city = res.data.substring(res.data.indexOf("  来自于：")).replace("  来自于：", "")
              localStorage.setItem("ip", this.ip)
              localStorage.setItem("city", this.city)
              this.uptVisitorNum()
            }
          )
        }

      },
      //更新访客信息
      uptVisitorNum() {
        $ajax.post(
          Vue.config.BaseUrl.ip + Vue.config.BaseUrl.uptVisitNum,
          {
            "ip": this.ip,
            "city": this.city
          },
          {}
        ).then(data => {
          // console.log("更新访问信息返回值",data)
          if (data.data.status === 0) {
            localStorage.setItem("ip", this.ip)
            localStorage.setItem("city", this.city)
          }
        })
      },
      queryCity(){
        $ajax.get('/ip/ipNewV3?ip=36.130.31.195&key=f2bbbc87977dfc23066cf7d38f002897', {}).then(
          res => {
            console.log("ip查询返回值",res)
            if(res.data.error_code===0){
              this.searchCity = res.data.result.Country+"-"+res.data.result.Province+"-"+res.data.result.City+"-"+res.data.result.Isp
            }else{
              this.$Message.error("IP查询失败，或者超过本日查询次数！")
            }
          }
        )
      }
    },
    created() {
      this.getClientInfo()
    }

  }
</script>

<style scoped>

</style>
