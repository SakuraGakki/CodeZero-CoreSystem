<template>
  <div>
    <br>
    <div style="margin-left: 40px"><h2>二维码在线生成</h2></div>
    <br>
    <Row type="flex" justify="space-around" >
      <Col span="10">
        <Card style="height: 600px">
          <p slot="title">二维码字符串</p>
          <Input v-model="qrdata.qrUrl" type="textarea" :autosize="{minRows: 24,maxRows: 24}" placeholder="二维码字符串" />
        </Card>
      </Col>
      <Col span="10">
        <Card style="height: 600px">
          <p slot="title">转换后二维码图片</p>
          <Button class="ivu-btn-success" style="position: absolute;right: 10px" @click="downPic()">下载图片</Button>
          <div>
            <qrcode
              id="myQrcode"
              :url="qrdata.qrUrl"
              :iconurl="qrdata.logo"
              :wid="qrdata.size"
              :hei="qrdata.size"
              :imgwid="qrdata.iconSize"
              :imghei="qrdata.iconSize"
              :color-dark="qrdata.colorF"
              :color-light="qrdata.colorB"
            >
            </qrcode>
          </div>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<script>
  import $ajax from "axios"
  import Vue from "vue"
  import $ from "jquery"

  export default {
    name: "qrcodeUtil",
    data() {
      return {
        ip:"",
        city:"",
        qrdata:{
          size:500,
          iconSize:100,
          qrUrl:"",
          logo:require("@/assets/img/logo-f.png"),
          colorF:"#000000",
          colorB:"#ffffff"
        }
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
      //下载为图片
      downPic(){
        let canvasData = $("#qrcode").children('img')
        let aLink = document.createElement('a');
        let parts = canvasData[0].currentSrc.split(';base64,');
        let contentType = parts[0].split(':')[1];
        let raw = window.atob(parts[1]);
        let rawLength = raw.length;
        let uInt8Array = new Uint8Array(rawLength);
        for (let i = 0; i < rawLength; ++i) {
          uInt8Array[i] = raw.charCodeAt(i);
        }
        let blob = new Blob([uInt8Array], {type: contentType});
        let evt = document.createEvent("HTMLEvents");
        evt.initEvent("click", true, true);//initEvent 不加后两个参数在FF下会报错  事件类型，是否冒泡，是否阻止浏览器的默认行为
        aLink.download = "downQrcodeImg." + contentType.split("/")[1];
        aLink.href = URL.createObjectURL(blob);
        // aLink.dispatchEvent(evt);
        //aLink.click()
        aLink.dispatchEvent(new MouseEvent('click', {bubbles: true, cancelable: true, view: window}));//兼容火狐
      }
    },
    created() {
      this.getClientInfo()
    }
  }

</script>

<style lang="scss" scoped>

</style>
