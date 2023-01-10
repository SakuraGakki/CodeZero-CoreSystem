<template>
  <div class="list">
    <Row>
      <Col>
        <Card>
          <a href="#" slot="extra" @click.prevent="refresh">
            <Icon type="ios-loop-strong"></Icon>
          </a>
          <Table :loading="loading2"
                 :data="listData"
                 :columns="columns" ref="table"
          ></Table>
          <Page show-sizer show-elevator @on-change="pageChange" style="margin-top: 10px"
                @on-page-size-change="PageSizeChange" :total="condition.total"
                :page-size="condition.size"></Page>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<script>
import elementResizeDetectorMaker from 'element-resize-detector'

var erd = elementResizeDetectorMaker()
export default {
  name: 'TaskList',
  components: {},
  data() {
    return {
      condition: {
        size: 10,
        current: 1,
        total: 0,
      },
      loading2: false, // 分页loading
      wechat: {
        title: '',
        url: '',
        imgUrl: '',
        imgId: '',
        date: '',
        read: 0
      },
      currIndex: 0, // 当前编辑和新增的行号
      listData: [], // @:data
      columns: [
        {
          title: '文章标题',
          key: 'title',
          align: 'center',
        },
        {
          title: '文章地址',
          key: 'url',
          align: 'center'
        },
        {
          title: '阅读数',
          key: 'read',
          align: 'center'
        },
        {
          title: '发布日期',
          key: 'date',
          sortable: true,
          align: 'center'
        },
        {
          title: '操作',
          key: 'action',
          width: 120,
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
                    this.edit(params.index, 1)
                  }
                }
              }, '详情'),
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
              }, '编辑')
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
      handler(val) {
        this.initSearch()
      },
      deep: true
    },
    message: {
      handler(val) {
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
    state() {
      return this.$store.state.app
    }
  },
  methods: {
    searchShow() {
      this.searchState = !this.searchState
    },
    /**
     * 刷新页面请求
     * */
    refresh() {
      this.initSearch()
    },
    /**
     * @on-change 页码改变的回调，返回改变后的页码
     * */
    pageChange(page) {
      this.condition.current = page
    },
    /**
     * @on-page-size-change 切换每页条数时的回调，返回切换后的每页条数
     * */
    PageSizeChange(limit) {
      this.condition.size = limit
    },
    remove(index) {
    },
    edit(index, type) {
      this.editModal = true
      this.currIndex = index
      if (type) {//详情
        this.editModal = false
        window.open(this.listData[index].url)
      } else {
        if (index === -1) { // 新增
          this.imageReady = false
          this.file = []
          this.formData = new FormData()
          this.wechat = {
            seq: '',
            title: '',
            url: '',
            imgUrl: '',
            date: '',
            read: 0
          }
        } else { // 编辑
          this.wechat = this.listData[index]
        }
      }

    },
    /**
     * 数据导出
     * @ type 1 原始数据 2过滤数据
     */
    exportData(type) {
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
     * 重置查询条件
     */
    reset() {
      this.condition = {
        size: 10,
        current: 1,
        total: 0,
      }
    },
    /**
     * 去除上传文件
     **/
    handleRemove() {
      this.imageReady = false
      this.file.splice(0, 1)
      this.$refs.imgUpload.clearFiles()
    },

    /**
     * 转换成SRC显示在页面上
     **/
    convertSrc(file) {
      return window.URL.createObjectURL(file);
    },
    /**
     * 微信文章列表初始化查询查询
     * */
    initSearch() {
      this.$api.initWechatArticleList(this.condition).then(res => {
        if (res.status == 0) {
          this.listData = res.data.records
          this.condition.total = res.data.total
        }
      })
    },
  },
  created() {
    this.initSearch()
  },
  mounted() {
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

.demo-upload-list {
  display: inline-block;
  width: 200px;
  height: 100px;
  text-align: center;
  line-height: 60px;
  border: 1px solid transparent;
  border-radius: 4px;
  overflow: hidden;
  background: #fff;
  position: relative;
  box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
  margin-right: 4px;
}

.demo-upload-list img {
  width: 100%;
  height: 100%;
}

.demo-upload-list-cover {
  display: none;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, .6);
}

.demo-upload-list:hover .demo-upload-list-cover {
  display: block;
}

.demo-upload-list-cover i {
  color: #fff;
  font-size: 20px;
  cursor: pointer;
  margin: 0 2px;
}

</style>