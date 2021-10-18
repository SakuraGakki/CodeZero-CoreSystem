<template>
  <div>
    <br>
    <div style="margin-left: 40px"><h2>JSON格式在线转换</h2></div>
    <br>
    <Row type="flex" justify="space-around" >
      <Col span="10">
        <Card style="height: 600px">
          <p slot="title">转换前JSON字符串</p>
          <Input v-model="stringValue" type="textarea" :autosize="{minRows: 24,maxRows: 24}" @change="changeJson()" placeholder="JSON字符串" />
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
  export default {
    name: "jsonUtil",
    data() {
      return {
        stringValue:"",
      }
    },
    methods:{
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
