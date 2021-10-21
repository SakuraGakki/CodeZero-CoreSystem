<template>
    <div class="list">
        <!-- 搜索 v-show="state.searchState.show"-->
        <Row class="search-filter" :class="{'active':searchState}">
            <Col>
                <Card>
                    <Form :label-width="80" inline>
                        <Form-item label="文章名称">
                            <Input v-model="condition.keyword" placeholder="请输入" style="width:187px"></Input>
                        </Form-item>
                        <div class="clearfix" style="border-top:1px solid #eee;margin-top:-15px;padding-top:10px">
          <span class="pull-right">
              <Button type="primary" icon="ios-search" shape="circle" @click="">查询</Button>
              <Button type="primary" icon="ios-close-empty" shape="circle" @click="reset()">重置</Button>
              <Button type="primary" icon="ios-download-outline" shape="circle" @click="exportData(1)">导出数据</Button>
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
                        <Button type="success" @click="add">
                            <Icon type="icon iconfont icon-document"></Icon>
                            新增
                        </Button>
                        <!--                        <Button type="primary" @click="searchShow" shape="circle">-->
                        <!--                            <Icon type="funnel"></Icon>-->
                        <!--                            筛选-->
                        <!--                        </Button>-->
                    </div>

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
    import {getNowFormatDate, getNowFormatTime} from '@/common/utils/dateUtils'

    var erd = elementResizeDetectorMaker()
    export default {
        name: 'articleList',
        components: {},
        data() {
            return {
                condition: {
                    size: 10,
                    current: 1,
                    total: 0,
                },
                loading2: false, // 分页loading
                currIndex: 0, // 当前编辑和新增的行号
                listData: [], // @:data
                columns: [
                    {
                        title: '文章标题',
                        key: 'articleTitle',
                        align: 'center',
                    },
                    {
                        title: '文章标签',
                        key: 'articleTag',
                        align: 'center',
                        render:(h,params) => {
                            let tagColor = ['blue','green','red','yellow']
                            let tagList = params.row.articleTag.split(",")
                            return h('ul', tagList.map(function (item,index) {
                                return h('Tag',{
                                    props:{
                                        color:tagColor[index%4]
                                    }
                                }, item)
                            }))
                        }
                    },
                    {
                        title: '阅读数',
                        key: 'readNum',
                        width:200,
                        align: 'center'
                    },
                    {
                        title: '创建日期',
                        key: 'date',
                        sortable: true,
                        width:120,
                        align: 'center'
                    },
                    {
                        title: '状态',
                        key: 'status',
                        align: 'center',
                        width:70,
                        render:(h,params) => {
                            const status = params.row.status == '0' ? '未发布' : '已发布'
                            return status
                        }
                    },
                    {
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
                                            this.preview(params.index)
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
                                            this.upt(params.index)
                                        }
                                    }
                                }, '编辑'),
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
                                            this.publish(params.index)
                                        }
                                    }
                                }, params.row.status==0?'发布':'下架')
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
            add(){
              this.$router.push('/newArticle')
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
             * 转换成SRC显示在页面上
             **/
            convertSrc(file) {
                return window.URL.createObjectURL(file);
            },
            /**
             * 微信文章列表初始化查询查询
             * */
            initSearch() {
                this.$api.initArticleList(this.condition).then(res => {
                    if (res.status == 0) {
                        this.listData = res.data.records
                        this.condition.total = res.data.total
                    }
                })
            },
            upt(index) {
                this.$router.push({
                        name: '新建文章',
                        params: this.listData[index]
                    })
            },
            preview(index) {
                this.$router.push({
                    name: '预览文章',
                    params: this.listData[index]
                })
            },
            publish(index){
                let obj = this.listData[index]
                let data = {articleId:obj.articleId}
                if(obj.status == "0"){
                    this.$api.publishArticle(data).then(res=>{
                        if(res.status === 0){
                            this.$Message.success("发布成功！")
                            this.initSearch()
                        }
                    })
                }else{
                    this.$api.cancelArticle(data).then(res=> {
                        if (res.status === 0) {
                            this.$Message.success("下架成功！")
                            this.initSearch()
                        }
                    })
                }
            }
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

