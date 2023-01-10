<template>
  <div><br>
    <Row type="flex" justify="space-around" >
      <Col span="4">
        <Card>
          <div style="text-align:center">
            <img style="width: 200px;height: 150px" src="../../assets/img/me/utils/json.jpeg">
            <router-link :to="{name: 'jsonUtil'}" tag="span"><h3>JSON数据在线格式化</h3></router-link>
          </div>
        </Card>
      </Col>
      <Col span="4">
        <Card>
          <div style="text-align:center">
            <img style="width: 200px;height: 150px" src="../../assets/img/me/utils/qrcode.jpeg">
            <router-link :to="{name: 'qrcodeUtil'}" tag="span"><h3>二维码在线生成器</h3></router-link>
          </div>
        </Card>
      </Col>
      <Col span="4">
        <Card>
          <div style="text-align:center">
            <img style="width: 200px;height: 150px" src="../../assets/img/me/utils/base64.jpeg">
            <router-link :to="{name: 'encodeUtil'}" tag="span"><h3>在线转码</h3></router-link>
          </div>
        </Card>
      </Col>
      <Col span="4">
        <Card>
          <div style="text-align:center">
            <img style="width: 200px;height: 150px" src="../../assets/img/me/utils/colorboard.jpeg">
            <router-link :to="{name: 'colorUtil'}" tag="span"><h3>在线调色板</h3></router-link>
          </div>
        </Card>
      </Col>
      <Col span="4">
        <Card>
          <div style="text-align:center">
            <img style="width: 200px;height: 150px" src="../../assets/img/me/utils/location.jpeg">
            <router-link :to="{name: 'ipUtil'}" tag="span"><h3>在线ip地址查询</h3></router-link>
          </div>
        </Card>
      </Col>
      <Col span="4">
        <Card>
          <div style="text-align:center">
            <img style="width: 200px;height: 150px" src="../../assets/img/me/utils/regExp.jpeg">
            <router-link :to="{name: 'regularExpressionsUtil'}" tag="span"><h3>正则表达式大全</h3></router-link>
          </div>
        </Card>
      </Col>
    </Row>
  </div>

</template>

<script>
  export default {
    name: "Utils",
    data() {
      return {}
    }
  }
</script>

<style scoped>

</style>
