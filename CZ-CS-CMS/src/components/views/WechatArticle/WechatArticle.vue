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
                        <Button type="success" @click.native="edit(-1)">
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
        <!-- 编辑 -->
        <Modal v-model="editModal">
            <p slot="header" style="text-align:center">
                <span v-if="currIndex==-1">新建文章</span>
                <span v-if="currIndex!=-1">编辑文章</span>
            </p>
            <div style="text-align:center">
                <Form :label-width="80" :rules="ruleLine" :model="wechat" ref="wechatForm">
                    <FormItem label="文章标题" prop="title">
                        <Input v-model="wechat.title"></Input>
                    </FormItem>
                    <FormItem label="文章地址" prop="url">
                        <Input v-model="wechat.url"></Input>
                    </FormItem>
                    <FormItem label="配图地址" prop="imgUrl">
                        <div class="demo-upload-list" v-if="currIndex!=-1">
                            <img :src="wechat.imgUrl"/>
                        </div>
                        <div class="demo-upload-list" v-for="item in file">
                            <img :src="item.src"/>
                            <div class="demo-upload-list-cover" @click="handleRemove()">
                                <Icon type="ios-trash-outline" size="60"></Icon>
                            </div>
                        </div>
                        <div style="width: 110px;float: right">
                            <Button icon="ios-cloud-upload" type="success" style="width: 100px;"
                                    v-if="(file.length>0) && (!imageReady) && (currIndex==-1)" @click="upload()">上传
                            </Button>
                            <Button icon="ios-cloud-upload" type="success" style="width: 100px;"
                                    v-if="(file.length>0) && (!imageReady) && (currIndex!=-1)"
                                    @click="upload(wechat.imgId)">替换上传
                            </Button>
                        </div>
                        <div style="width: 110px;display:inline;float: right">
                            <Upload
                                    style="width: 110px;"
                                    ref="imgUpload"
                                    :before-upload="handleUpload"
                                    :action=action
                                    :format="['jpg','jpeg','png']"
                                    :on-format-error="uploadFormatError"
                            >
                                <Button icon="icon iconfont icon-tupian" v-if="!file.length>0">选择图片</Button>
                            </Upload>
                        </div>
                        <Input v-model="wechat.imgUrl" disabled></Input>
                    </FormItem>
                    <FormItem label="发布时间" v-if="currIndex!=-1">
                        <Input v-model="wechat.date" disabled></Input>
                    </FormItem>
                    <FormItem label="阅读数" v-if="currIndex!=-1">
                        <Input v-model="wechat.read"></Input>
                    </FormItem>
                </Form>
            </div>

            <div slot="footer">
                <Button type="success" size="large" v-if="currIndex!=-1" long @click="uptWechatInfo()"
                        :loading="loading">
                    保存
                </Button>
                <Button type="success" size="large" v-if="currIndex==-1" long @click="addWechatInfo()"
                        :loading="loading">
                    保存
                </Button>
            </div>
        </Modal>
        <!-- 编辑/ -->
    </div>
</template>
<script>
    import elementResizeDetectorMaker from 'element-resize-detector'
    import {getNowFormatDate, getNowFormatTime} from '@/common/utils/dateUtils'

    var erd = elementResizeDetectorMaker()
    export default {
        name: 'weChatArticle',
        components: {},
        data() {
            return {
                ruleLine: {
                    title: {
                        required: true,
                        message: '标题不能为空'
                    },
                    url: {
                        required: true,
                        message: '文章链接路径不能为空'
                    },
                    imgUrl: {
                        required: true,
                        message: '文章配图不能为空'
                    }
                },
                // 预览图片的src
                src: '',
                // 随便写个地址
                action: 'http://59.110.218.235:8000/cms/wechat/wechatImageUpload',
                // 上传文件
                file: [],
                formData: new FormData(),
                condition: {
                    size: 10,
                    current: 1,
                    total: 0,
                },
                imageReady: false,
                searchState: false,
                editModal: false,
                loading2: false, // 分页loading
                loading: false, // save
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
             * 上传类型错误提示
             **/
            uploadFormatError() {
                this.$Message.error('上传类型只能是jpg,jpeg,png');
            },

            /**
             * 获取上传文件,并去掉重复的文件
             **/
            handleUpload(file) {
                file.src = this.convertSrc(file)
                if (this.file.length > 0) {
                    this.formData = new FormData()
                    this.handleRemove()
                    this.file.push(file)
                    this.formData.append("file", file)
                } else {
                    this.file.push(file)
                    this.formData.append("file", file)
                }
                // false代表不上传到action的地址，true的话会报错，因为action地址是瞎写的，action地址不写会报错。
                return false;
            },

            /**
             * 上传
             **/
            upload(imgId) {
                if (this.file.length == 0) {
                    this.$Message.error('上传文件不能为空');
                    return;
                }
                if (imgId) {
                    this.formData.append("imgId", imgId)
                    this.$api.replaceWechatImage(this.formData).then(res => {
                        if (res.status == 0) {
                            this.wechat.imgUrl = res.data.imageUrl
                            this.wechat.imgId = res.data.imageId
                            this.imageReady = true
                        }
                    })
                } else {
                    this.$api.uploadWechatImage(this.formData).then(res => {
                        if (res.status == 0) {
                            this.wechat.imgUrl = res.data.imageUrl
                            this.wechat.imgId = res.data.imageId
                            this.imageReady = true
                        }
                    })
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
                this.$api.initWechatArticleList(this.condition).then(res => {
                    if (res.status == 0) {
                        this.listData = res.data.records
                        this.condition.total = res.data.total
                    }
                })
            },
            /**
             * 修改微信信息
             **/
            uptWechatInfo() {
                //先校验信息完整性
                if (this.$refs['wechatForm'].validate()) {
                    this.$api.updateWechatArticle(this.wechat).then(res => {
                        if (res.status == 0) {
                            this.$Message.success('修改成功')
                            this.editModal = false
                            this.initSearch()
                        }
                    })
                }
            },
            /**
             * 新增微信信息
             **/
            addWechatInfo() {
                //先校验信息完整性
                if (this.$refs['wechatForm'].validate()) {
                    this.$api.insertWechatArticle(this.wechat).then(res => {
                        if (res.status == 0) {
                            this.$Message.success('保存成功')
                            this.editModal = false
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

