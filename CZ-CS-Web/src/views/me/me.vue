<template>
  <div>
    <div class="me-header">
      <ul class="me-header-menu">
<!--        <router-link :to="{name: 'messageboard'}" tag="li" class="me-header-submenu"><span class="menuFont">留言</span></router-link>-->
        <router-link :to="{name: 'articleList'}" tag="li" class="me-header-submenu"><span class="menuFont">我的文章</span></router-link>
        <router-link :to="{name: 'utils'}" tag="li" class="me-header-submenu"><span class="menuFont">在线工具</span></router-link>
        <router-link :to="{name: 'meindex'}" tag="li" class="me-header-submenu"><span class="menuFont">主页</span></router-link>
      </ul>
    </div>
    <div>
      <router-view/>
    </div>
    <div style="background-color: #aca7a7">
      <ul class="me-footer">
        <span class="me-footer-font">
          <div  style="width: 100%; margin: -15px auto; padding: 10px 0px;background-color: #a1aab8"><a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=21010502000602" style="display: inline-block; text-decoration: none; text-align: center;"><img src="../../assets/img/beian.png" style="float: left;"> <p style="float: left; margin: 0px 0px 0px 5px; color: rgb(54, 53, 53); text-align: center; display: block;">辽公网安备 21010502000602号</p></a> <span style="display: inline-block; text-decoration: none; text-align: center; top: -6px; position: relative;"> &nbsp;&nbsp;&nbsp;&nbsp; ©2021 CodeZero  &nbsp;&nbsp;&nbsp;&nbsp;ICP备案号：
          <a  href="https://beian.miit.gov.cn/" target="_blank">辽ICP备2021010634号-1</a></span></div>
        </span>
      </ul>
    </div>
  </div>

</template>

<script>
  import MeIndex from './index'
  export default {
    name: "me",
    components: {
      MeIndex
    },
  }
</script>

<style scoped>
  .me-header{
    width: 100%;
    height: 60px;
    background-color: #41454b;
  }
  .me-header-menu{
    position: fixed;
    width: 100%;
    height: 60px;
    top: 0px;
    left: 0px;
    z-index: 1;
    background-color: rgba(0,0,0,0);
  }
  .me-header-submenu{
    list-style: none;
    float: right;
    right: 80px;
    margin:0 40px;
  }
  .menuFont{
    line-height: 60px;
    font-size: 20px;
    font-family:  "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
    color: #ffffff;
  }
  .me-footer{
    position: absolute;
    width: 100%;
    height: 20px;
    bottom: 0px;
    left: 0px;
    z-index: 1;
    background-color: rgba(0,0,0,0);
  }
  .me-footer-font{
    color: #363535;
    text-align: center;
    display:block;
  }
</style>
