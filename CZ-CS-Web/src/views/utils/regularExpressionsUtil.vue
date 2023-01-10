<template>
  <div>
    <br>
    <div style="margin-left: 40px"><h2>正则表达式大全</h2></div>
    <br><br>
    <Row type="flex" justify="space-around">
      <Col span="20">
        <Card>
          <div>
            <Form ref="formCustom"  :label-width="200" style="width: 80%">
              <FormItem label="待校验内容" prop="inputVal">
                <Input v-model="inputVal" type="textarea" :autosize="{minRows: 3,maxRows: 5}"></Input>
                <br>
                <div v-if="N">N:<Input v-model="n" @change="changeN()"></Input></div>
                <div v-if="M">M:<Input v-model="m" @change="changeM()"></Input></div>
                <Button type="primary" @click="checkVal()">校验</Button>
              </FormItem>
              <FormItem label="已选择正则表达式" prop="regType">
                <Input disabled v-model="regExp"></Input>
              </FormItem>
            </Form>
          </div>
          <div>
            <RadioGroup v-model="regExp" type="button" size="large" button-style="solid">
              <Radio v-for="item in regTypeList" :label="item.value" :key="item.value">
                <span  @click="checkNM(item.value)">{{item.label}}</span>
              </Radio>
            </RadioGroup>
          </div>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<script>

export default {
  name: "regularExpressionsUtil",
  data() {
    return {
      N:false,
      M:false,
      n:1,
      m:1,
      inputVal:"",
      regExp:"^[0-9]*$",
      regTypeList:[
        {
          label:"数字",
          value:"^[0-9]*$"
        },
        {
          label:"n位的数字",
          value:"^\\d{n}$"
        },
        {
          label:"至少n位的数字",
          value:"^\\d{n,}$"
        },
        {
          label:"m-n位的数字",
          value:"^\\d{m,n}$"
        },
        {
          label:"零和非零开头的数字",
          value:"^(0|[1-9][0-9]*)$"
        },
        {
          label:"非零开头的最多带两位小数的数字",
          value:"^([1-9][0-9]*)+(.[0-9]{1,2})?$"
        },
        {
          label:"带1-2位小数的正数或负数",
          value:"^(\\-)?\\d+(\\.\\d{1,2})?$"
        },
        {
          label:"正数、负数、和小数",
          value:"^(\\-|\\+)?\\d+(\\.\\d+)?$"
        },
        {
          label:"有两位小数的正实数",
          value:"^[0-9]+(.[0-9]{2})?$"
        },
        {
          label:"有1~3位小数的正实数",
          value:"^[0-9]+(.[0-9]{1,3})?$"
        },
        {
          label:"非零的正整数",
          value:"^[1-9]\\d*$"
        },
        {
          label:"非零的负整数",
          value:"^-[1-9]\\d*$"
        },
        {
          label:"非负整数",
          value:"^\\d+$"
        },
        {
          label:"非正整数",
          value:"^-[1-9]\\d*|0"
        },
        {
          label:"汉字",
          value:"^[\\u4e00-\\u9fa5]{0,}$"
        },
        {
          label:"英文和数字",
          value:"^[A-Za-z0-9]+$"
        },
        {
          label:"长度为3-20的所有字符",
          value:"^.{3,20}$"
        },
        {
          label:"由26个英文字母组成的字符串",
          value:"^[A-Za-z]+$"
        },
        {
          label:"由26个大写英文字母组成的字符串",
          value:"^[A-Z]+$"
        },
        {
          label:"由26个小写英文字母组成的字符串",
          value:"^[a-z]+$"
        },
        {
          label:"由数字、26个英文字母或者下划线组成的字符串",
          value:"^\\w+$ 或 ^\\w{3,20}$"
        },
        {
          label:"中文、英文、数字包括下划线",
          value:"^[\\u4E00-\\u9FA5A-Za-z0-9_]+$"
        },
        {
          label:"中文、英文、数字但不包括下划线等符号",
          value:"^[\\u4E00-\\u9FA5A-Za-z0-9]+$"
        },
        {
          label:"Email地址",
          value:"^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"
        },
        {
          label:"域名",
          value:"[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?"
        },
        {
          label:"InternetURL",
          value:"[a-zA-z]+://[^\\s]*"
        },
        {
          label:"手机号码",
          value:"^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$"
        },
        {
          label:"电话号码",
          value:"^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$"
        },
        {
          label:"国内电话号码",
          value:"\\d{3}-\\d{8}|\\d{4}-\\d{7}"
        },
        {
          label:"身份证号",
          value:"/^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$/"
        },
        {
          label:"帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)",
          value:"^[a-zA-Z][a-zA-Z0-9_]{4,15}$"
        },
        {
          label:"密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)",
          value:"^[a-zA-Z]\\w{5,17}$"
        },
        {
          label:"强密码(必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间)",
          value:"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$"
        },
        {
          label:"日期格式",
          value:"^\\d{4}-\\d{1,2}-\\d{1,2}"
        },
        {
          label:"一年的12个月(01～09和1～12)",
          value:"^(0?[1-9]|1[0-2])$"
        },
        {
          label:"一个月的31天(01～09和1～31)",
          value:"^((0?[1-9])|((1|2)[0-9])|30|31)$"
        },
        {
          label:"校验钱（精确到角或分）",
          value:"/(^[1-9]([0-9]+)?(\\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\\.[0-9]([0-9])?$)/"
        },
        {
          label:"xml文件",
          value:"^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\\\.[x|X][m|M][l|L]$"
        },
        {
          label:"中文字符的正则表达式",
          value:"[\u4e00-\u9fa5]"
        },
        {
          label:"空白行的正则表达式",
          value:"\\n\\s*\\r"
        },
        {
          label:"HTML标记的正则表达式",
          value:"<(\\S*?)[^>]*>.*?</\\1>|<.*? />"
        },
        {
          label:"首尾空白字符的正则表达式",
          value:"^\\s*|\\s*$或(^\\s*)|(\\s*$)"
        },
        {
          label:"腾讯QQ号（从10000开始）",
          value:"[1-9][0-9]{4,}"
        },
        {
          label:"中国邮政编码（6位数字）",
          value:"[1-9]\\d{5}(?!\\d)"
        },
        {
          label:"IP地址",
          value:"((?:(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d)\\\\.){3}(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d))"
        }
      ]
    }
  },
  methods: {
    checkVal(){
      let regExps = this.regExp
      if("^\\d{n}$"==this.regExp){
        regExps = "^\\d{" + this.n + "}$"
      }else if("^\\d{n,}$"==this.regExp){
        regExps = "^\\d{" + this.n + ",}$"
      }else if("^\\d{m,n}$"==this.regExp){
        regExps = "^\\d{" + this.m + "," +  this.n + "}$"
      }
      console.log("展示的正则：",this.regExp)
      console.log("校验的正则：",regExps)
      const reg = new RegExp(regExps)
      if(!reg.test(this.inputVal)){
        this.$Message.error("校验失败！");
      }else{
        this.$Message.success('校验成功！');
      }
    },
    checkNM(obj){
      console.log("触发点击",obj)
      switch (obj){
        case "^\\d{n}$":
          this.N = true
          this.M = false
          break
        case "^\\d{n,}$":
          this.N = true
          this.M = false
          break
        case "^\\d{m,n}$":
          this.N = true
          this.M = true
          break
        default:
          this.N = false
          this.M = false
          break
      }
      console.log(this.N,this.M)
    },
    changeN(){
      if(this.M){
        this.regExp = "^\\d{" + this.m + "," +  this.n + "}$"
      }else if(this.regExp.contains(",")){
        this.regExp = "^\\d{" + this.n + ",}$"
      }else{
        this.regExp = "^\\d{" + this.n + "}$"
      }
    },
    changeM(){
      this.regExp = "^\\d{" + this.m + "," +  this.n + "}$"
    }
  },
  created() {
  }

}
</script>

<style scoped>

</style>
