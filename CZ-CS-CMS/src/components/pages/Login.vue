<template>
  <div class="login">
    <div class="bg">
    </div>
    <Modal class="login-modal" v-model="loginModal" width="360" :closable="false" :mask-closable="false">
      <div>
        <h2 style="margin:10px;color:#abcdef;text-align:center">
          <img :src="require('@/assets/img/logo.png')" alt="" height="50"></h2>
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate">
          <Form-item prop="userId">
            <Input v-model="formValidate.userId" placeholder="请输入姓名"></Input>
          </Form-item>
          <Form-item prop="password">
            <Input v-model="formValidate.password" @keyup.native.enter="handleSubmit('formValidate')" type="password" placeholder="请输入密码"></Input>
          </Form-item>

          <Row>
            <Col span="12">
            <Checkbox-group v-model="formValidate.remember">
              <Checkbox label="记住我"></Checkbox>
            </Checkbox-group>
            </Col>
            <Col span="12">
            <a style="float:right" @click="toRegister">新用户注册</a>
            </Col>
          </Row>

        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" size="large" long :loading="modal_loading" @click="handleSubmit('formValidate')">登录
        </Button>
        <small style="text-align: center;display:block" class="subText">©copyright by CodeZero</small>
      </div>
    </Modal>
  </div>
</template>
<script>
  import Cookies from 'js-cookie'
  export default {
    name: 'login',
    data () {
      return {
        loginModal: true,
        modal_loading: false,
        formValidate: {
          userId: '',
          password: '',
          ip:'',
          city:''
        },
        userInfo:{
          userName:'',
          phone:'',
          address:'',
          lastIp:'',
          lastCity:'',
          lastTime:'',
          sex:'',
          birthday:'',
          email:'',
          userId:'',
          avator:'',
          wechatId:''
        },
        ruleValidate: {
          userId: [
            {required: true, message: '姓名不能为空', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码错误', trigger: 'blur'}
          ]

        }
      }
    },
    created() {
      // 获取登录用户客户端信息
      this.$api.getIpInfo().then(res => {
        console.log(res.data)
        res.data = res.data.substring(6,res.data.length)
        this.formValidate.ip = res.data.substring(0,res.data.indexOf("  来自于："))
        this.formValidate.city = res.data.substring(res.data.indexOf("  来自于：")).replace("  来自于：","")
      }).catch(err => {
        this.$Message.error('获取客户端信息失败!')
      })
    },
    methods: {
      handleSubmit (name) { // login
        this.$refs[name].validate((valid) => {
          this.modal_loading = true
          if(valid){
            this.$api.login(this.formValidate).then(res => {
              this.modal_loading = false
              Cookies.remove("userInfo")
              if (res.status === 0) {
                this.$Message.success('登录成功!')
                this.$Notice.success({
                  title: '登录提示',
                  desc: res.data.userName+'，欢迎您！</br> 您上次登录的地点是：'+res.data.lastCity+'</br>'+'您上次登录的时间为：'+res.data.lastTime,
                  duration:10
                })
                this.getUserInfo(res.data)
                Cookies.set('token', this.formValidate.password)
                Cookies.set("userInfo",this.userInfo);
                this.$router.push('/index')
              } else {
                this.$Message.error('登录失败!')
                this.$Notice.error({
                  title: '登录提示',
                  desc: res.message
                })
              }
            }).catch(err => {
              this.modal_loading = false
              console.error('哎哟~！', err)
            })
          }else{
            this.modal_loading = false
            this.$Message.error('登录失败!')
            this.$Notice.warning({
              title: '登录提示',
              desc: '请输入用户名和密码！'
            })
          }
        })
      },
      toRegister () {
        this.$router.push('/register')
      },
      getUserInfo(data){
        this.userInfo.userId = data.userId
        this.userInfo.userName = data.userName
        this.userInfo.avator = data.avator
        this.userInfo.sex = data.sex
        this.userInfo.phone = data.phone
        this.userInfo.wechatId = data.wechatId
        this.userInfo.birthday = data.birthday
        this.userInfo.address = data.address
        this.userInfo.email = data.email
        this.userInfo.lastIp = data.lastIp
        this.userInfo.lastCity = data.lastCity
        this.userInfo.lastTime = data.lastTime
      }
    }
  }
</script>


