<template>
  <div class="iconfont">
    <Row>
      <Col>
      <Card>
        <div class="main markdown">
          <h1>IconFont 图标 <span style="font-size: 16px">点击图标复制代码到粘贴板</span></h1>
          复制标签 / 复制class
          <i-switch v-model="isClass">
            <Icon type="android-done" slot="open"></Icon>
            <Icon type="android-close" slot="close"></Icon>
          </i-switch>
          示例：<code v-if="!isClass"> &lt;i class=&quot;icon iconfont icon-law&quot; &gt;&lt;/i&gt;</code><code
          v-if="isClass">icon-law</code>
          <ul class="icon_lists clear">
            <li @click="clip(item)" v-for="item in iconfont">
              <i class="icon iconfont" :class="item.name"></i>
            </li>
          </ul>
          <ul class="icon_lists clear">
            <li class="emojiList" v-for="(item, index) in emoji" :key="index" >
              <span style="font-size: 50px" @click="selectEmoji(item.text)">{{item.text}}</span>
            </li>
          </ul>
          <br>
          <br>
          <input type="text-area" v-model="content" style="height: 100px;width: 200px"></input><button @click="showContent()">哈哈哈</button>
        </div>
      </Card>
      </Col>
    </Row>
  </div>
</template>
<script>
  import '@/assets/iconfont/iconfont.css'
  import Clipboard from 'clipboard'
  import iconfont from '@/assets/iconfont/icon.js'
  import {EmojisCommon} from "../../common/data/Emoji";

  export default {
    name: 'iconfont',
    components: {},
    data () {
      return {
        iconfont: iconfont,
        emoji:EmojisCommon.map(emoji => ({text: emoji})),
        isClass: false,
        content:""
      }
    },
    watch: {},
    computed: {},
    methods: {
      clip (type) {
        console.log(type)
        let icon
        if (this.isClass) {
          icon = `${type.name}`
        } else {
          icon = `<i class="icon iconfont ${type.name}"></i>`
        }
        const clipboard = new Clipboard('.icon', {
          text () {
            return icon
          }
        })
        clipboard.on('success', (e) => {
          e.clearSelection()
          clipboard.destroy()
          this.$Message.success('代码已经复制到剪贴板')
        })
      },
      selectEmoji(emoji){
        this.content += emoji
      },
      showContent(){
        alert(this.content)
        console.log(this.content)
      }
    },
    created () {
      console.log("emoji",EmojisCommon.map(emoji => ({text: emoji})))
    },
    mounted () {
    }
  }
</script>
<style scoped>
  @import '../../assets/iconfont/demo.css';
</style>
