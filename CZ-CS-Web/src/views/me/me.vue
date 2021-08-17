<template>
  <div>
    <div class="me-header">
      <ul class="me-header-menu">
<!--        <router-link :to="{name: 'meindex'}" tag="li" class="me-header-submenu"><span class="menuFont">我的留言板</span></router-link>-->
<!--        <router-link :to="{name: 'mearcicle'}" tag="li" class="me-header-submenu"><span class="menuFont">我的文章</span></router-link>-->
<!--        <router-link :to="{name: 'meblog'}" tag="li" class="me-header-submenu"><span class="menuFont">我的动态</span></router-link>-->
<!--        <router-link :to="{name: 'meindex'}" tag="li" class="me-header-submenu"><span class="menuFont">我的资料</span></router-link>-->
      </ul>
    </div>
    <div>
      <router-view/>
    </div>
    <ul class="me-footer">
      <span class="me-footer-font">©2021 CodeZero</span>
    </ul>
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
    height: 80px;
    background-color: #41454b;
  }
  .me-header-menu{
    position: fixed;
    width: 100%;
    height: 80px;
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
    line-height: 80px;
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
