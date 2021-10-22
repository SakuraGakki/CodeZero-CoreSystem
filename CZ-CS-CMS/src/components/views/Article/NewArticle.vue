<template>
    <div>
        <Spin fix v-if="loading">
            <Icon type="load-c" size=18 class="demo-spin-icon-load"></Icon>
            <div>Loading</div>
        </Spin>
        <br>
        <Row type="flex" justify="space-around" style="width: 100%;height: calc(100vh - 150px)">
            <Col span="22">
                <Card style="width: 100%;height: calc(100%)">
                    <p slot="title">新建文章</p>
                    <Form :label-width="100">
                        <Form-item>
                            <span style="text-align: left;font-size: 16px">文章标题：</span>
                            <Input v-model="articleTitle" style="width: 400px" :maxlength="50" placeholder="请输入(不超过50个汉字)"></Input>
                            <div style="float: right" v-if="!this.articleId"><Button type="success" size="large" @click="saveArticleInfo">保存文章</Button></div>
                            <div style="float: right" v-if="this.articleId"><Button type="success" size="large" @click="updateArticleInfo">更新文章</Button></div>
                        </Form-item>
                        <Form-item>
                            <span style="text-align: left;font-size: 16px">标签列表：</span>
                            <Tag v-for="(item,index) in tagList" :key="item" :name="item" :color="tagColor[index%4]" closable @on-close="tagDel(index)">{{item}}</Tag>
                            <Button icon="ios-plus-empty" type="dashed" @click="tagAdd">添加标签</Button>
                        </Form-item>
                    </Form>
                    <quill-editor
                            v-model="content"
                            ref="myQuillEditor"
                            :options="editorOption"
                            @focus="onEditorFocus($event)"
                            @blur="onEditorBlur($event)"
                            @change="onEditorChange($event)"
                            class="editor"
                    ></quill-editor>
                    <div style="text-align:right;margin-right: 10px">
                        {{TiLength}}个字
                    </div>
                    <form action method="post" enctype="multipart/form-data" id="uploadFormMulti">
                        <input
                                style="display: none"
                                :id="uniqueId"
                                type="file"
                                name="file"
                                multiple
                                accept="image/jpg, image/jpeg, image/png, image/gif"
                                @change="uploadImg('uploadFormMulti')"
                        />
                    </form>
                </Card>
            </Col>
        </Row>
        <Modal
                v-model="addModel"
                title="输入标签内容"
                @on-ok="saveTag"
                @on-cancel="cancel">
            <Form :label-width="80">
                <Form-item label="标签内容">
                    <Input v-model="tagContent" :maxlength="8" placeholder="请输入(最多8个汉字)"></Input>
                </Form-item>
            </Form>
        </Modal>
    </div>
</template>

<script>
    import "quill/dist/quill.core.css"
    import "quill/dist/quill.snow.css"
    import "quill/dist/quill.bubble.css"
    import {getNowFormatDate, getNowFormatTime} from '@/common/utils/dateUtils'
    import { quillEditor } from "vue-quill-editor"


    // 工具栏配置
    const toolbarOptions = [
        ["bold", "italic", "underline", "strike"], // 加粗 斜体 下划线 删除线 -----['bold', 'italic', 'underline', 'strike']
        ["blockquote", "code-block"], // 引用  代码块-----['blockquote', 'code-block']
        [{ header: 1 }, { header: 2 }], // 1、2 级标题-----[{ header: 1 }, { header: 2 }]
        [{ list: "ordered" }, { list: "bullet" }], // 有序、无序列表-----[{ list: 'ordered' }, { list: 'bullet' }]
        [{ script: "sub" }, { script: "super" }], // 上标/下标-----[{ script: 'sub' }, { script: 'super' }]
        [{ indent: "-1" }, { indent: "+1" }], // 缩进-----[{ indent: '-1' }, { indent: '+1' }]
        [{ direction: "rtl" }], // 文本方向-----[{'direction': 'rtl'}]
        [{ size: ["small", false, "large", "huge"] }], // 字体大小-----[{ size: ['small', false, 'large', 'huge'] }]
        [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题-----[{ header: [1, 2, 3, 4, 5, 6, false] }]
        [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色-----[{ color: [] }, { background: [] }]
        [{ font: [] }], // 字体种类-----[{ font: [] }]
        [{ align: [] }], // 对齐方式-----[{ align: [] }]
        ["clean"], // 清除文本格式-----['clean']
        ["image", "video"] // 链接、图片、视频-----['link', 'image', 'video']
    ];

    export default {
        name: "newArticle",
        components: {
            quillEditor
        },
        data() {
            return {
                loading:false,
                TiLength:0,
                addModel:false,
                tagContent:"",
                tagList:[], //标签列表（存值）
                articleId:"",
                articleTitle:"", //文章标题（存值）
                tagColor:['blue','green','red','yellow'],
                uniqueId: "uniqueId",
                content: "", // 富文本编辑器默认内容（存值）
                editorOption: {
                    //  富文本编辑器配置
                    modules: {
                        toolbar: toolbarOptions
                    },
                    theme: "snow",
                    placeholder: "请输入正文"
                },
            };
        },
        computed: {
            //当前富文本实例
            editor() {
                return this.$refs.myQuillEditor.quill;
            }
        },
        methods: {
            // 准备富文本编辑器
            onEditorReady() {},
            // 富文本编辑器 失去焦点事件
            onEditorBlur() {
            },
            // 富文本编辑器 获得焦点事件
            onEditorFocus() {},
            // 富文本编辑器 内容改变事件
            onEditorChange(event) {
                event.quill.deleteText(2000,1);
                this.TiLength = event.quill.getLength()-1
            },
            uploadImg: async function() {
                let _this = this;
                //构造formData对象
                let formData = new FormData();
                formData.append("file", document.getElementById(_this.uniqueId).files[0]);

                try {
                    //调用上传文件接口
                    this.$api.uploadArticleImage(formData).then(res => {
                        //返回上传文件的地址
                        console.log("图片上传返回值",res)
                        if(res.status === 0){
                            let Range = _this.editor.getSelection();
                                //上传文件成功之后在富文本中回显(显示)
                                _this.editor.insertEmbed(
                                    Range != null ? Range.index : 0,
                                    "image",
                                    res.data.imageUrl
                                );
                        }else{
                            _this.$Message.error("图片上传失败！")
                        }
                        //成功之后,将文件的文本框的value置空
                        document.getElementById(_this.uniqueId).value = "";
                    });
                } catch ({ message: msg }) {
                    document.getElementById(_this.uniqueId).value = "";
                    _this.$Message.warning(msg);
                }
            },

            //添加标签
            tagAdd(){
                this.addModel = true
            },
            saveTag(){
                this.tagList.push(this.tagContent)
                this.addModel = false
                this.tagContent = ""
            },
            cancel(){
                this.addModel = false
                this.tagContent = ""
            },
            //删除标签
            tagDel(index){
                this.tagList.splice(index,1)
            },
            //保存文章内容
            saveArticleInfo(){
                let tags = ""
                if(this.tagList && this.tagList.length>0){
                    for(let i=0;i<this.tagList.length;i++){
                        if(i===0){
                            tags = this.tagList[i]
                        }else{
                            tags += ","+this.tagList[i]
                        }
                    }
                }
                let data = {
                    articleTitle:this.articleTitle,
                    articleTag:tags,
                    content:this.content,
                    readNum:0,
                    date:getNowFormatDate("YYYY-MM-DD") + " " + getNowFormatTime("hh:mm:ss"),
                    status:0
                }
                this.loading = true
                this.$api.insertArticle(data).then(
                    res=>{
                        this.loading = false
                        if(res.status === 0){
                            this.$Message.success("保存成功！")
                            this.$router.replace("/articleList")
                        }else{
                            this.$Message.error("文章保存失败！")
                        }
                    }
                )
            },
            updateArticleInfo(){
                let tags = ""
                if(this.tagList && this.tagList.length>0){
                    for(let i=0;i<this.tagList.length;i++){
                        if(i===0){
                            tags = this.tagList[i]
                        }else{
                            tags += ","+this.tagList[i]
                        }
                    }
                }
                let data = {
                    articleId:this.articleId,
                    articleTitle:this.articleTitle,
                    articleTag:tags,
                    content:this.content,
                    readNum:0,
                    date:getNowFormatDate("YYYY-MM-DD") + " " + getNowFormatTime("hh:mm:ss"),
                    status:0
                }
                this.loading = true
                this.$api.updateArticleInfo(data).then(
                    res=>{
                        this.loading = false
                        if(res.status === 0){
                            this.$Message.success("更新成功！")
                            this.$router.replace("/articleList")
                        }else{
                            this.$Message.error("文章更新失败！")
                        }
                    }
                )
            },
            //初始化查询文章内容
            initQuery(articleId){
                let data = {articleId:articleId}
                this.$api.getArticleContent(data).then(res=>{
                    if(res.status === 0){
                        this.content = res.data.content;
                    }
                })
            }
        },
        mounted() {
            //数据初始化
            this.TiLength =this.$refs.myQuillEditor.quill.getLength() - 1

            let _this = this;
            let imgHandler = async function(image) {

                if (image) {
                    let fileInput = document.getElementById(_this.uniqueId); //隐藏的file文本ID
                    fileInput.click(); //加一个触发事件
                }
            };
            _this.editor.getModule("toolbar").addHandler("image", imgHandler);
        },
        created() {
            if(this.$route.params.articleId){
                this.articleId = this.$route.params.articleId
                this.articleTitle = this.$route.params.articleTitle
                if(this.$route.params.articleTag){
                    if(this.$route.params.articleTag.indexOf(",")>-1){
                        this.tagList = this.$route.params.articleTag.split(",")
                    }else{
                        this.tagList.push(this.$route.params.articleTag)
                    }
                }
                this.initQuery(this.articleId);
            }
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