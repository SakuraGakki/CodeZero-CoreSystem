<template>
  <div>
    <br>
    <Row type="flex" justify="space-around" style="width: 100%;height: calc(100vh - 150px)">
      <Col span="20">
        <Card style="width: 100%;height: calc(100%)">
          <p slot="title">文章详情</p>
          <div>
            <span style="font-size: 28px;text-align: center"><b>《{{articleTitle}}》</b></span>
            <span style="color: rgba(118,115,118,0.81)">发布时间：{{date}}</span>
            <span style="color: rgba(118,115,118,0.81)">阅读数：{{readNum}}</span>
            <br>
            <Tag v-for="(item,index) in tagList" :key="item" :name="item" :color="tagColor[index%4]">{{item}}</Tag>
          </div>
          <br><br>
          <div style="height: calc(100vh - 340px)">
            <p class="ql-editor" v-html="content"></p>
          </div>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<script>
  import "quill/dist/quill.core.css"
  import "quill/dist/quill.snow.css"
  import "quill/dist/quill.bubble.css"
  import $ajax from "axios";

  export default {
    name: "articleContent",
    data() {
      return {
        tagList:[], //标签列表（存值）
        articleId:"",
        articleTitle:"", //文章标题（存值）
        date:"",
        readNum:0,
        tagColor:['blue','green','red','yellow'],
        content: "", // 富文本编辑器默认内容（存值）
      };
    },
    computed: {
      //当前富文本实例
      editor() {
        return this.$refs.myQuillEditor.quill;
      }
    },
    methods: {

      //增加阅读数
      addReadNum(){
        $ajax.post('/cms/article/addArticleReadNum', {
          articleId:this.articleId
        })
      },

      //初始化查询文章内容
      initQuery(articleId){
        $ajax.post('/cms/article/articleContentById', {
          articleId:this.articleId
        }).then(res=>{
          if(res.data.status === 0){
            this.content = res.data.data.content;
            this.addReadNum()
          }
        })
      }
    },
    mounted() {
    },
    created() {
      this.articleId = this.$route.params.articleId
      this.articleTitle = this.$route.params.articleTitle
      this.readNum = this.$route.params.readNum
      this.date = this.$route.params.date
      this.tagList = this.$route.params.articleTag
      this.initQuery(this.articleId);
    }
  };
</script>


<style lang='scss'>
  .demo-spin-icon-load{
    animation: ani-demo-spin 1s linear infinite;
  }

  .editor {
    line-height: normal !important;
    height: 500px;
  }

  .ql-snow .ql-tooltip[data-mode="link"]::before {
    content: "请输入链接地址:";
  }

  .ql-snow .ql-tooltip.ql-editing a.ql-action::after {
    border-right: 0px;
    content: "保存";
    padding-right: 0px;
  }

  .ql-snow .ql-tooltip[data-mode="video"]::before {
    content: "请输入视频地址:";
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item::before {
    content: "14px";
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value="small"]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value="small"]::before {
    content: "10px";
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value="large"]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value="large"]::before {
    content: "18px";
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value="huge"]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value="huge"]::before {
    content: "32px";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item::before {
    content: "文本";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
    content: "标题1";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
    content: "标题2";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
    content: "标题3";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
    content: "标题4";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
    content: "标题5";
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
    content: "标题6";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item::before {
    content: "标准字体";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value="serif"]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value="serif"]::before {
    content: "衬线字体";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value="monospace"]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value="monospace"]::before {
    content: "等宽字体";
  }


  /* 编辑器内部出现滚动条 */
  .ql-container{
    overflow-y:auto;
    height:30rem!important;
  }

  /*滚动条整体样式*/
  .ql-container ::-webkit-scrollbar{
    width: 10px;/*竖向滚动条的宽度*/
    height: 10px;/*横向滚动条的高度*/
  }
  .ql-container ::-webkit-scrollbar-thumb{/*滚动条里面的小方块*/
    background: #666666;
    border-radius: 5px;
  }
  .ql-container ::-webkit-scrollbar-track{/*滚动条轨道的样式*/
    background: #ccc;
    border-radius: 5px;
  }

</style>
