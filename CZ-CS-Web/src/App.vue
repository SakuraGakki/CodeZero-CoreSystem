<template>
  <div id="app">
    <ul class="indexMenu" v-if="isHomepage">
      <li class="menuList"><a @click="goIndex('meindex')"><span class="menuFont">关于我</span></a></li>
      <li class="menuList"><a @click="goIndex('utils')"><span class="menuFont">在线工具</span></a></li>
      <li class="menuList"><a @click="goIndex('resources')"><span class="menuFont">资源中心</span></a></li>
      <li class="menuList"><a @click="goIndex('index')"><span class="menuFont">首页</span></a></li>
      <div class="logoDiv"><div class="logoImg"><img class="logo" src="./assets/logoHomePage.png"/></div><span class="logoTitle">欢迎访问CodeZero</span></div>
    </ul>
    <router-view/>
    <ul class="indexFoot" v-if="isHomepage">
      <span class="footFont">Copyright &copy; 2021 CodeZero</span>
    </ul>
  </div>
</template>

<script>
import homepage from './components/homepage'
export default {
  name: 'App',
  //监听
  watch:{
    '$route':'routerChange'
  },
  components: {
    homepage
  },
  data(){
    return{
      isHomepage:true
    }
  },
  created: function(){
    //页面初始化判断缓存中是否有首页标识
    if(this.$route.name === 'homepage'){
      this.isHomepage = true;
    }else{
      this.isHomepage = false;
    }
  },
  methods:{
    //导航跳转
    goIndex:function(routerName){
      this.$router.push({name:routerName});
    },
    //路由变化
    routerChange:function(to,from){
      console.log(to.path);
      console.log(from.path);
      if(from.path==='/'&&to.path!=='/'){
        this.isHomepage = false;
      }
      if(from.path!=='/'&&to.path==='/'){
        this.isHomepage = true;
      }
    },

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
  .logoDiv{
    margin-top:80px;
  }
  .logo{
    width: auto;
    height: auto;
    max-width: 25%;
    max-height: 25%;
  }
  .logoImg{
    margin-left: 80px;
  }
  .logoTitle{
    text-align: center;
    display:block;
    font-size: 100px;
    font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
    color: #ffffff;
  }

</style>
