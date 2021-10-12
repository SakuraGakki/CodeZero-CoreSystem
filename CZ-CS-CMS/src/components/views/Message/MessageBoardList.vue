<template>
  <div class="list">
    <!-- 搜索 v-show="state.searchState.show"-->
    <Row class="search-filter" :class="{'active':searchState}">
      <Col>
      <Card>
        <Form :label-width="80" inline>
          <Form-item label="检索条件">
            <Input v-model="condition.keyword" placeholder="请输入" style="width:187px"></Input>
          </Form-item>
          <Form-item label="状态">
            <Select v-model="condition.status" placeholder="全部" style="width:187px">
              <Option value="">全部</Option>
              <Option value="0">初始</Option>
              <Option value="1">已读</Option>
              <Option value="2">已回复</Option>
              <Option value="3">无效</Option>
            </Select>
          </Form-item>
          <div class="clearfix" style="border-top:1px solid #eee;margin-top:-15px;padding-top:10px">
          <span class="pull-right">
              <Button type="primary" icon="ios-search" shape="circle" @click="getMessageBoardListAll()">查询</Button>
              <Button type="primary" icon="ios-close-empty" shape="circle" @click="reset()">重置</Button>
              <Button type="primary" icon="ios-download-outline" shape="circle" @click="exportData(1)">导出数据</Button>
<!--              <Button type="primary" icon="ios-download-outline" shape="circle"-->
<!--                      @click="exportData(2)">导出排序和过滤后数据</Button>-->
            </span>

          </div>
        </Form>
      </Card>
      </Col>
    </Row>
    <!-- 搜索 /-->
    <Row>
      <Col>
      <Card>
        <div slot="title">
<!--          <Button type="dashed" @click.native="fixedHeader=!fixedHeader">-->
<!--            <Icon type="pin"></Icon>-->
<!--          </Button>-->
          <Button type="success" @click.native="edit(-1)">
            <Icon type="icon iconfont icon-document"></Icon>
            新增
          </Button>
          <Button type="primary" @click="searchShow" shape="circle" ><Icon type="funnel"></Icon> 筛选</Button>
          <Button type="error" v-if="selection.length>0" @click="delMessageBoardInfo()">
            <Icon type="trash-a"></Icon>
            批量删除
          </Button>
        </div>

        <a href="#" slot="extra" @click.prevent="refresh">
          <Icon type="ios-loop-strong"></Icon>
        </a>
        <Table :loading="loading2" :height="fixedHeader ? 300 : ''"
               :data="listData"
               :columns="columns" ref="table" @on-select="onSelect" @on-selection-change="onSelectionChange"></Table>
        <Page show-sizer show-elevator @on-change="pageChange" style="margin-top: 10px"
              @on-page-size-change="PageSizeChange" :total="condition.total" :page-size="condition.size"></Page>
      </Card>
      </Col>
    </Row>
    <!--删除提示  -->
    <Modal v-model="deleteTip" width="360">
      <p slot="header" style="color:#f60;text-align:center">
        <Icon type="information-circled"></Icon>
        <span>删除确认</span>
      </p>
      <div style="text-align:center">
        <p>此操作讲删除{{selection.length}}条数据,并不可恢复。</p>
        <p>是否继续删除？</p>
      </div>
      <div slot="footer">
        <Button type="error" size="large" long @click="deleteBatch">删除</Button>
      </div>
    </Modal>
    <!--删除提示 / -->
    <!-- 编辑 -->
    <Modal v-model="editModal">
      <p slot="header" style="text-align:center">
        <span v-if="currIndex==-1 && !isReply">新建留言</span>
        <span v-if="currIndex==-1 && isReply">回复留言</span>
        <span v-if="currIndex!=-1">编辑留言</span>
      </p>
      <div style="text-align:center">
        <Form  :label-width="80">
          <Form-item label="IP" v-if="currIndex!=-1">
            <Input v-model="message.ip"  disabled></Input>
          </Form-item>
          <Form-item label="用户" v-if="currIndex!=-1">
            <Input v-model="message.city" disabled></Input>
          </Form-item>
          <Form-item label="时间" v-if="currIndex!=-1">
            <Input v-model="message.date" disabled></Input>
          </Form-item>
          <Form-item label="留言" v-if="!isReply">
            <Input v-model="message.content" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
                   placeholder="请输入..."></Input>
          </Form-item>
          <Form-item label="回复" v-if="isReply">
            <Input v-model="message.content" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
                   placeholder="请输入..."></Input>
          </Form-item>
        </Form>
      </div>

      <div slot="footer">
        <Poptip placement="left" width="400px" height="200px">
          <span style="font-size: 24px;text-align: center;">😀</span>
          <div slot="content">
            <ul class="icon_lists clear">
              <li v-for="(item, index) in emoji" :key="index">
                <span style="font-size: 24px" @click="selectEmoji(item.text)">{{item.text}}</span>
              </li>
            </ul>
          </div>
        </Poptip>
        <Button type="success" size="large" v-if="currIndex!=-1"  long @click="uptMessageBoardInfo()" :loading="loading">
          保存
        </Button>
        <Button type="success" size="large" v-if="currIndex==-1 && !isReply"  long @click="addMessageBoardInfo()" :loading="loading">
          保存
        </Button>
        <Button type="success" size="large" v-if="currIndex==-1 && isReply"  long @click="replyMessageBoardInfo()" :loading="loading">
          保存
        </Button>
      </div>
    </Modal>
    <!-- 编辑/ -->
  </div>
</template>
<script>
  import elementResizeDetectorMaker from 'element-resize-detector'
  import {getNowFormatDate,getNowFormatTime} from '@/common/utils/dateUtils'
  import {EmojisCommon} from '@/common/utils/Emoji'
  var erd = elementResizeDetectorMaker()
  export default {
    name: 'msgBoardList',
    components: {},
    data () {
      return {
        emoji: EmojisCommon.map(emoji => ({text: emoji})),
        condition:{
          keyword:'',
          status:'',
          size:10,
          current:1,
          total:0,
        },
        replyId:0,
        isReply:false,
        searchState: false,
        editModal: false,
        deleteTip: false,
        loading2: false, // 分页loading
        fixedHeader: false, // 是否固定表头 @:height
        loading: false, // save
        message: {
          ip:'',
          city:'',
          date:'',
          content:''
        },
        currIndex: 0, // 当前编辑和新增的行号
        selection: [], // 表格选中项
        listData: [], // @:data
        columns: [
          { // @:columns
            type: 'selection', // 开启checkbox
            width: 60,
            align: 'center'
          },
          {
            title: '标识',
            key: 'msgType',
            width: 100,
            align: 'center',
            render:(h,params) => {
              const color = params.row.msgType == '0' ? 'blue' : 'green'
              const text = params.row.msgType == '0' ? '原文'+params.row.id : '回复'+params.row.relatedId
              // return h('Tag',text)`<Tag color="${color}">${text}</Tag>`;
              return h('tag', {
                props:{
                  color:color  //写你要创建的元素的标签名
                }
              }, text)       //写元素的内容
            }
          },
          {
            title: '用户',
            key: 'city',
            align: 'center'
          },
          {
            title: '发布日期',
            key: 'date',
            sortable: true,
            align: 'center'
          },
          {
            title: '内容',
            key: 'content',
            width: 400,
            align: 'center'
          },
          {
            title: '状态',
            key: 'status',
            align: 'center',
            render:(h,params) => {
              const color = params.row.status == '0' ? 'yellow' : params.row.status == '1' ?  'blue' : params.row.status == '2' ? 'green' : 'red'
              const text = params.row.status == '0' ? '初始' : params.row.status == '1' ? '已读' : params.row.status == '2' ? '已回复' : '失效'
              // return h('Tag',text)`<Tag color="${color}">${text}</Tag>`;
              return h('tag', {
                props:{
                  type:'dot',
                  color:color  //写你要创建的元素的标签名
                }
              }, text)       //写元素的内容
            }
          }, {
            title: '操作',
            key: 'action',
            width: 150,
            fixed: 'right',
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'text',
                    size: 'small'
                  },
                  style: {
                    color: '#5cadff'
                  },
                  on: {
                    click: () => {
                      this.edit(params.index)
                    }
                  }
                }, '编辑'),
                h('Button', {
                  props: {
                    type: 'text',
                    size: 'small'
                  },
                  style: {
                    marginRight: '5px',
                    color: '#5cadff'
                  },
                  on: {
                    click: () => {
                      this.reply(params.index)
                    }
                  }
                }, '回复'),
                h('Button', {
                  props: {
                    type: 'text',
                    size: 'small'
                  },
                  style: {
                    color: '#ff3300'
                  },
                  on: {
                    click: () => {
                      this.delMessageBoardInfo(params.row)
                    }
                  }
                }, '删除')
              ])
            }
          }
        ]
      }
    },
    watch: {
      /**
       * @params 监听参数变化重新获取数据
       * */
      params: {
        handler (val) {
          this.getMessageBoardListAll(val)
        },
        deep: true
      },
      fixedHeader: {
        handler (val) {
          if (val) {
            this.$Message.info('表头已固定')
          }
        }
      },
      message: {
        handler (val) {
          for (let i = 0; i < Object.values(val).length; i++) {
            if (Object.values(val)[i] === '') {
              this.saveDisabled = true
              return
            } else {
              this.saveDisabled = false
            }
          }
        },
        deep: true
      }
    },
    computed: {
      state () {
        return this.$store.state.app
      }
    },
    methods: {
      searchShow () {
        this.searchState = !this.searchState
      },
      /**
       * 刷新页面请求
       * */
      refresh () {
        this.getMessageBoardListAll(this.condition)
      },
      /**
       * @on-change 页码改变的回调，返回改变后的页码
       * */
      pageChange (page) {
        this.condition.current = page
      },
      /**
       * @on-page-size-change 切换每页条数时的回调，返回切换后的每页条数
       * */
      PageSizeChange (limit) {
        this.condition.size = limit
      },
      /**
       * 表格对应操作方法
       * @show 查看
       * @remove 删除
       * @edit
       */
      show (index) {
        this.currIndex = index
        this.message = this.listData[index]
        this.detailModal = true
        this.$Modal.info({
          title: '详情',
          content: `姓名：${this.listData[index].who}<br>平台：${this.listData[index].type}<br>描述：${this.listData[index].desc}`
        })
      },
      remove (index) {
        this.listData.splice(index, 1)
      },
      edit (index) {
        this.editModal = true
        this.currIndex = index
        if (index === -1) { // 新增
          this.message = {
            ip:'',
            city:'',
            date:'',
            content:''
          }
        } else { // 编辑
          this.isReply = false
          this.message = this.listData[index]
        }
      },
      reply (index) {
        this.currIndex = -1
        this.editModal = true
        this.isReply = true
        let item = this.listData[index]
        if(item.msgType=="0"){
          this.replyId = item.id
        }else{
          this.replyId = item.relatedId
        }
        this.message = {
          ip:'',
          city:'',
          date:'',
          content:''
        }
      },
      selectEmoji(emoji) {
        this.message.content += emoji
      },
      /**
       * 批量删除
       */
      deleteBatch () {
        this.deleteTip = false
        // ...
      },
      /**
       * 数据导出
       * @ type 1 原始数据 2过滤数据
       */
      exportData (type) {
        if (type === 1) {
          this.$refs.table.exportCsv({
            filename: '原始数据'
          })
        } else if (type === 2) {
          this.$refs.table.exportCsv({
            filename: '排序和过滤后的数据',
            original: false
          })
        }
      },
      /**
       * 多选
       * selection：已选项数据 row：刚选择的项数据
       */
      onSelect (selection, row) {
        console.log(selection,row)
      },
      onSelectionChange (selection) {
        this.selection = selection
      },

      /**
       * 获取留言及回复列表
       */
      getMessageBoardListAll(){
        this.$api.getMessageListAll(this.condition).then(res=>{
          if (res.status === 0) {
            this.listData = res.data.records
            this.condition.total = res.data.total
          }
        })
      },
      /**
       * 重置查询条件
       */
      reset(){
        this.condition = {
            keyword:'',
            status:'',
            size:10,
            current:1,
            total:0,
        }
        this.getMessageBoardListAll()
      },
      /**
       * 新增留言
       */
      addMessageBoardInfo(){
        this.loading = true
        let data = JSON.parse(JSON.stringify({
          ip:'',
          city:'站长',
          status:'2',
          date:getNowFormatDate("YYYY-MM-DD") + " " + getNowFormatTime("hh:mm:ss"),
          content:this.message.content
        }))
        this.$api.addMessageBoardInfo(data).then(res=>{
          if (res.status === 0) {
            this.loading = false
            this.$Message.info('保存成功')
            this.editModal = false
            this.getMessageBoardListAll(this.condition)
          }
        })
      },
      /**
       * 修改留言
       */
      uptMessageBoardInfo(){
        this.loading = true
        let data = this.message
        this.$api.uptMessageBoardInfo(data).then(res=>{
          if (res.status === 0) {
            this.loading = false
            this.$Message.info('保存成功')
            this.editModal = false
            this.getMessageBoardListAll(this.condition)
          }
        })
      },
      /**
       * 回复留言
       */
      replyMessageBoardInfo(){
        this.loading = true
        let data = JSON.parse(JSON.stringify({
          id:this.replyId,
          ip:'',
          city:'站长',
          date:getNowFormatDate("YYYY-MM-DD") + " " + getNowFormatTime("hh:mm:ss"),
          content:this.message.content
        }))
        this.$api.addMessageBoardInfo(data).then(res=>{
          if (res.status === 0) {
            this.loading = false
            this.$Message.info('保存成功')
            this.editModal = false
            this.getMessageBoardListAll(this.condition)
          }
        })
      },
      /**
       * 删除留言
       */
      delMessageBoardInfo(data){
        this.loading = true
        let list = new Array()
        if(this.selection.length>0){
          data = this.selection
        }else{
          list[0] = data
          data = list
        }
        this.$api.delMessageBoardInfo(data).then(res=>{
          if (res.status === 0) {
            this.loading = false
            this.$Message.info('保存成功')
            this.editModal = false
            this.getMessageBoardListAll(this.condition)
          }
        })
      }
    },
    created () {
      this.getMessageBoardListAll(this.condition)
    },
    mounted () {
      erd.listenTo(window, 'resize', this.handleResize)
    }
  }
</script>
<style lang="less" scoped>
  .search-filter {
    opacity: 0;
    display: none;
    overflow: hidden;
    transition: all 0.28s ease-out;
    &.active {
      opacity: 1;
      display: block;
    }
  }


  .icon_lists {
    width: 500px;
    height: 300px;
  }

  .icon_lists li {
    float: left;
    width: 40px;
    height: 40px;
    text-align: center;
    list-style: none !important;
  }

  /* 清除浮动 */
  .ks-clear:after, .clear:after {
    content: '\20';
    display: block;
    height: 0;
    clear: both;
  }

  .ks-clear, .clear {
    *zoom: 1;
  }
</style>

