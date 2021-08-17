<template>
  <ul class="cb-slideshow">
    <li><span>Image 01</span><div><h3>Visualization</h3></div></li>
    <li><span>Image 02</span><div><h3>Big Data Management</h3></div></li>
    <li><span>Image 03</span><div><h3>Code Design</h3></div></li>
    <li><span>Image 04</span><div><h3>Analytics</h3></div></li>
    <li><span>Image 05</span><div><h3>Artificial Intelligence</h3></div></li>
    <li><span>Image 06</span><div><h3>Internet Applications</h3></div></li>
  </ul>
</template>

<script>

export default {
  name: 'HomePage',
  data () {
    return {
      title1:"",
      title2:"",
      title3:"",
      title4:"",
      title5:"",
      title6:"",
    }
  },
  mounted(){
    // 实现APP的高度占满全屏
    //$('.bg').height($(window).height())
  },
  created() {

  }

}
</script>
<style scoped>
  @import '../assets/css/demo.css';
  @import '../assets/css/loginStyle.css';

</style>
