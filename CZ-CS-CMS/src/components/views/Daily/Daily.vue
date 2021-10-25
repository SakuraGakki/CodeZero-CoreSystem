<template>
    <div class="list">
        <Row>
            <Col>
                <Card>
                    <div slot="title">
                        <Button type="success" @click.native="edit(-1)">
                            <Icon type="icon iconfont icon-document"></Icon>
                            新增
                        </Button>
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
                <span v-if="currIndex==-1">新建日常</span>
                <span v-if="currIndex!=-1">编辑日常</span>
            </p>
            <div style="text-align:center">
                <Form :label-width="80" :rules="ruleLine" :model="daily" ref="dailyForm">
                    <FormItem label="日常内容" prop="content">
                        <Input v-model="daily.content" type="textarea" ></Input>
                        <Poptip placement="left" width="400px" height="200px">
                            <span style="font-size: 24px;">😀</span>
                            <div slot="content">
                                <ul class="icon_lists clear">
                                    <li v-for="(item, index) in emoji" :key="index">
                                        <span style="font-size: 24px" @click="selectEmoji(item.text)">{{item.text}}</span>
                                    </li>
                                </ul>
                            </div>
                        </Poptip>
                    </FormItem>
                    <FormItem label="配图地址">
                        <div class="demo-upload-list" v-if="currIndex!=-1">
                            <img :src="daily.imageUrl"/>
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
                                    @click="upload(daily.imageId)">替换上传
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
                        <Input v-model="daily.imageUrl" disabled></Input>
                    </FormItem>
                    <FormItem label="发布时间" v-if="currIndex!=-1">
                        <Input v-model="daily.date" disabled></Input>
                    </FormItem>
                </Form>
            </div>

            <div slot="footer">
                <Button type="success" size="large" v-if="currIndex!=-1" long @click="uptDailyInfo()"
                        :loading="loading">
                    保存
                </Button>
                <Button type="success" size="large" v-if="currIndex==-1" long @click="addDailyInfo()"
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
    import {EmojisCommon} from '@/common/utils/Emoji'
    import {getNowFormatDate, getNowFormatTime} from '@/common/utils/dateUtils'

    var erd = elementResizeDetectorMaker()
    export default {
        name: 'daily',
        components: {},
        data() {
            return {
                ruleLine: {
                    content: {
                        required: true,
                        message: '内容不能为空'
                    }
                },
                emoji: EmojisCommon.map(emoji => ({text: emoji})),
                // 预览图片的src
                src: '',
                // 随便写个地址
                action: 'http://59.110.218.235:8000/cms/daily/dailyImageUpload',
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
                daily: {
                    seq:'',
                    content: '',
                    imageUrl: '',
                    imageId: '',
                    date: '',
                    status: '',
                },
                currIndex: 0, // 当前编辑和新增的行号
                listData: [], // @:data
                columns: [
                    {
                        title: '日常内容',
                        key: 'content',
                        align: 'center',
                    },
                    {
                        title: '配图地址',
                        key: 'imageUrl',
                        align: 'center',
                    },
                    {
                        title: '发布日期',
                        key: 'date',
                        sortable: true,
                        align: 'center'
                    },
                    {
                        title: '状态',
                        key: 'status',
                        align: 'center',
                        render:(h,params) => {
                            return params.row.status == 0?"未发布":"已发布"
                        }
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
            selectEmoji(emoji) {
                this.daily.content += emoji
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
                        this.daily = {
                            seq:'',
                            content: '',
                            imageUrl: '',
                            imageId: '',
                            date: '',
                            status: ''
                        }
                    } else { // 编辑
                        this.daily = this.listData[index]
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
                    this.$api.replaceDailyImage(this.formData).then(res => {
                        if (res.status == 0) {
                            this.daily.imageUrl = res.data.imageUrl
                            this.daily.imageId = res.data.imageId
                            this.imageReady = true
                        }
                    })
                } else {
                    this.$api.uploadDailyImage(this.formData).then(res => {
                        if (res.status == 0) {
                            this.daily.imageUrl = res.data.imageUrl
                            this.daily.imageId = res.data.imageId
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
             * 列表初始化查询查询
             * */
            initSearch() {
                this.$api.initDailyList(this.condition).then(res => {
                    if (res.status == 0) {
                        this.listData = res.data.records
                        this.condition.total = res.data.total
                    }
                })
            },
            /**
             * 修改日常信息
             **/
            uptDailyInfo() {
                //先校验信息完整性
                if (this.$refs['dailyForm'].validate()) {
                    this.$api.updateDailyInfo(this.daily).then(res => {
                        if (res.status == 0) {
                            this.$Message.success('修改成功')
                            this.editModal = false
                            this.initSearch()
                        }
                    })
                }
            },
            /**
             * 新增日常信息
             **/
            addDailyInfo() {
                //先校验信息完整性
                if (this.$refs['dailyForm'].validate()) {
                    this.daily.date = getNowFormatDate("YYYY-MM-DD") + " " + getNowFormatTime("hh:mm:ss")
                    this.$api.insertDailyInfo(this.daily).then(res => {
                        if (res.status == 0) {
                            this.$Message.success('保存成功')
                            this.editModal = false
                            this.initSearch()
                        }
                    })
                }
            },
            publish(index) {
                let obj = this.listData[index]
                let data = {seq: obj.seq}
                if (obj.status == "0") {
                    this.$api.publishDaily(data).then(res => {
                        if (res.status === 0) {
                            this.$Message.success("发布成功！")
                            this.initSearch()
                        }
                    })
                } else {
                    this.$api.cancelDaily(data).then(res => {
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

