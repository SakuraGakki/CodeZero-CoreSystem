<template>
  <div id="app">
    <ul class="indexMenu">
      <li class="menuList"><a><span class="menuFont">关于我们</span></a></li>
      <li class="menuList"><a><span class="menuFont">在线工具</span></a></li>
      <li class="menuList"><a><span class="menuFont">资源中心</span></a></li>
      <li class="menuList"><a><span class="menuFont">首页</span></a></li>
    </ul>
    <router-view/>
    <ul class="indexFoot">
      <span class="footFont">©2021 CodeZero</span>
    </ul>
  </div>
</template>

<script>
import homepage from './components/homepage'
export default {
  name: 'App',
  components: {
    homepage
  }
}
</script>

<style>
#app {
  width: 100%;
  height: 100%;
}
  .indexMenu{
    position: fixed;
    width: 100%;
    height: 80px;
    top: 0px;
    left: 0px;
    z-index: 1;
    background-color: rgba(0,0,0,0);
  }
  .menuList{
    list-style: none;
    float: right;
    right: 80px;
    margin:0 40px;
  }
  .menuFont{
    line-height: 80px;
    font-size: 20px;
    font-family:  "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
    color: #ffffff;
  }
  .indexFoot{
    position: absolute;
    width: 100%;
    height: 20px;
    bottom: 0px;
    left: 0px;
    z-index: 1;
    background-color: rgba(0,0,0,0);
  }
  .footFont{
    color: #FFFFFF;
    text-align: center;
    display:block;
  }

</style>
