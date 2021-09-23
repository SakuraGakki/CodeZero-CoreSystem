<template>
    <div class="index">
        <Row :gutter="8">
            <Col :md='{span:8}'>
                <Row>
                    <Col span="12" class="cardMessage" style="padding: 0">
                        <Card>
                            <Row>
                                <Col span="8">
                                    <Icon type="ios-calendar"></Icon>
                                </Col>
                                <Col span="16">
                                    <p class="subText">今天日期：</p>
                                    <h3>{{calender.date}}</h3>
                                </Col>
                            </Row>
                        </Card>
                    </Col>
                    <Col span="12" class="cardMessage" style="padding: 0">
                        <Card>
                            <Row>
                                <Col span="8">
                                    <Icon type="ios-time"></Icon>
                                </Col>
                                <Col span="16">
                                    <p class="subText">当前时间：</p>
                                    <h3>{{calender.nowTime}}</h3>
                                </Col>
                            </Row>
                        </Card>
                    </Col>
                    <Col span="24" class="cardMessage" style="padding: 0;">
                        <Card>
                            <Row>
                                <Col span="4">
                                    <img :src="weather.imgSrc" style="height: 55px;width: 55px;">
                                </Col>
                                <Col span="20">
                                    <p class="subText">今日天气：</p>
                                    <h3>
                                        {{weather.today.weather}}，{{weather.today.temperature}}，{{weather.today.direct}}{{weather.today.power}}</h3>
                                </Col>
                            </Row>
                        </Card>
                    </Col>
                </Row>
            </Col>
            <Col :md='{span:16}'>
                <Card>
                    <p slot="title">
                        今日热度
                    </p>
                    <a href="#" slot="extra" @click.prevent="refresh">
                        <Icon type="ios-loop-strong"></Icon>
                    </a>
                    <Rate show-text v-model="visitor.hot" :disabled="true">
                        <span style="color: #f5a623"><b>总访客数：{{ visitor.total }}</b></span>
                    </Rate>
                    <Progress :percent="visitor.per" status="active"><span style="color: #00a096"><b><br>今日访客：{{ visitor.today }}</b></span>
                    </Progress>
                </Card>
            </Col>
        </Row>

        <Row :gutter="8">
            <Col :md='{span:8}'>
                <Card style="height: 320px">
                    <p slot="title">
                        10个已经完成，2个待完成，1个正在进行中
                    </p>
                    <a href="#" slot="extra" @click.prevent="refresh">
                        <Icon type="ios-loop-strong"></Icon>
                    </a>
                    <Steps :current="2" direction="vertical" size="small">
                        <Step title="已完成" content="这里是该步骤的描述信息"></Step>
                        <Step title="已完成" content="这里是该步骤的描述信息"></Step>
                        <Step title="进行中" content="这里是该步骤的描述信息"></Step>
                        <Step title="待进行" content="这里是该步骤的描述信息"></Step>
                    </Steps>
                </Card>
            </Col>
            <Col :md='{span:8}'>
                <Card style="height: 320px">
                    <Table :columns="columns1" :data="data1" :show-header="false"></Table>
                </Card>
            </Col>
            <Col :md='{span:8}'>
                <Card style="height: 320px">
                    <p slot="title">
                        未读消息
                    </p>
                    <a href="#" slot="extra" @click.prevent="refresh">
                        <Icon type="ios-loop-strong"></Icon>
                    </a>
                    <Steps :current="2" direction="vertical" size="small">
                        <Step title="已完成" content="这里是该步骤的描述信息"></Step>
                        <Step title="已完成" content="这里是该步骤的描述信息"></Step>
                        <Step title="进行中" content="这里是该步骤的描述信息"></Step>
                        <Step title="待进行" content="这里是该步骤的描述信息"></Step>
                    </Steps>
                </Card>
            </Col>
        </Row>
    </div>
</template>
<script>
    import {getNowFormatDate, getNowFormatTime} from '@/common/utils/dateUtils'
    import {getEnumValue} from "@/common/utils/commonUtils";
    import {JuHe} from "@/api/config";
    import Cookies from 'js-cookie'
    import {getVisitorNum} from "../../api/api";

    export default {
        name: 'index',
        data() {
            return {
                params: {
                    page: 1,
                    limit: 10
                },
                valueCustomText: '隔壁老王',
                imgs: {
                    dafeng: require('../../assets/img/weather/dafeng.png'),
                    qing: require('../../assets/img/weather/qing.png'),
                    duoyun: require('../../assets/img/weather/duoyun.png'),
                    yin: require('../../assets/img/weather/yin.png'),
                    yuzhuanqing: require('../../assets/img/weather/yuzhuanqing.png'),
                    leizhenyu: require('../../assets/img/weather/leizhenyu.png'),
                    bingbao: require('../../assets/img/weather/bingbao.png'),
                    yujiaxue: require('../../assets/img/weather/yujiaxue.png'),
                    xiaoyu: require('../../assets/img/weather/xiaoyu.png'),
                    zhongyu: require('../../assets/img/weather/zhongyu.png'),
                    dayu: require('../../assets/img/weather/dayu.png'),
                    xiaoxue: require('../../assets/img/weather/xiaoxue.png'),
                    zhongxue: require('../../assets/img/weather/zhongxue.png'),
                    daxue: require('../../assets/img/weather/daxue.png'),
                    wu: require('../../assets/img/weather/wu.png'),
                    san: require('../../assets/img/weather/san.png'),
                    duoyunzhuanqing: require('../../assets/img/weather/duoyunzhuanqing.png'),
                },
                ip: "",
                locationInfo: {
                    Country: "",
                    Province: "",
                    City: "",
                    District: "",
                    Isp: "",
                },
                weather: {
                    imgSrc: "",
                    today: {
                        date: "",
                        temperature: "",
                        humidity: "",
                        weather: "",
                        wid: {
                            day: "",
                            dayImg: "",
                            night: "",
                            nightImg: "",
                        },
                        direct: "",
                        power: "",
                        aqi: ""
                    },
                    future: [
                        {
                            date: "",
                            temperature: "",
                            weather: "",
                            wid: {
                                day: "",
                                dayImg: "",
                                night: "",
                                nightImg: "",
                            },
                            direct: ""
                        },
                        {
                            date: "",
                            temperature: "",
                            weather: "",
                            wid: {
                                day: "",
                                dayImg: "",
                                night: "",
                                nightImg: "",
                            },
                            direct: ""
                        },
                        {
                            date: "",
                            temperature: "",
                            weather: "",
                            wid: {
                                day: "",
                                dayImg: "",
                                night: "",
                                nightImg: "",
                            },
                            direct: ""
                        },
                        {
                            date: "",
                            temperature: "",
                            weather: "",
                            wid: {
                                day: "",
                                dayImg: "",
                                night: "",
                                nightImg: "",
                            },
                            direct: ""
                        }
                    ]
                },
                calender: {
                    date: '',
                    nowTime: '',
                    huangli: {
                        baiji: "",
                        chongsha: "",
                        ji: "",
                        jishen: "",
                        wuxing: "",
                        xiongshen: "",
                        yi: "",
                        yinli: ""
                    }
                },
                visitor: {
                    today: 0,
                    total: 0,
                    per: 0,
                    hot: 0,
                },
                columns1: [
                    {
                        key: 'name'
                    },
                    {
                        key: 'age'
                    },
                    {
                        key: 'address'
                    }
                ],
                data1: [
                    {
                        name: '王小明',
                        age: 18,
                        address: '北京市朝阳区芍药居'
                    },
                    {
                        name: '张小刚',
                        age: 25,
                        address: '北京市海淀区西二旗'
                    },
                    {
                        name: '李小红',
                        age: 30,
                        address: '上海市浦东新区世纪大道'
                    },
                    {
                        name: '周小伟',
                        age: 26,
                        address: '深圳市南山区深南大道'
                    },
                    {
                        name: '赵小钱',
                        age: 27,
                        address: '武汉市武昌区区关山大道'
                    }
                ]

            }
        },
        computed: {},
        methods: {
            //获取服务器时间
            getNowTime() {
                this.calender.date = getNowFormatDate("YYYY") + '年' + getNowFormatDate("MM") + '月' + getNowFormatDate("DD") + '日'
                var _this = this
                this.timer = setInterval(() => {
                    _this.currentTime()
                }, 1000)
            },
            currentTime() {
                this.calender.nowTime = getNowFormatTime("hh:mm:ss")
            },

            //从cookie中获取用户信息
            getLocationFromCookies() {
                this.ip = Cookies.get("ip")
                this.locationInfo = JSON.parse(Cookies.get("locationInfo"))
                console.log("从Cookies中获取的ip", this.ip)
                console.log("从Cookies中获取的locationInfo", this.locationInfo)
                this.getWeatherInfo(this.locationInfo.City)
            },

            //获取当前天气
            getWeatherInfo(city) {
                let date = new Date()
                let h = date.getHours()
                if (localStorage.getItem("weatherInfo")) {
                    let weatherInfo = JSON.parse(localStorage.getItem("weatherInfo"))
                    if (weatherInfo.today.date && weatherInfo.today.date === getNowFormatDate("YYYY-MM-DD")) {
                        //今天
                        this.weather.today.date = getNowFormatDate("YYYY-MM-DD")
                        this.weather.imgSrc = h > 12 ? this.transformWeatherImg(getEnumValue(JuHe.weatherArray, "weather", "wid", weatherInfo.today.wid.night)) : this.transformWeatherImg(getEnumValue(JuHe.weatherArray, "weather", "wid", weatherInfo.today.wid.day))
                        this.weather.today.temperature = weatherInfo.today.temperature
                        this.weather.today.humidity = weatherInfo.today.humidity
                        this.weather.today.weather = weatherInfo.today.weather
                        this.weather.today.wid.day = weatherInfo.today.wid.day
                        this.weather.today.wid.dayImg = this.transformWeatherImg(getEnumValue(JuHe.weatherArray, "weather", "wid", weatherInfo.today.wid.day))
                        this.weather.today.wid.night = weatherInfo.today.wid.night
                        this.weather.today.wid.nightImg = this.transformWeatherImg(getEnumValue(JuHe.weatherArray, "weather", "wid", weatherInfo.today.wid.night))
                        this.weather.today.direct = weatherInfo.today.direct
                        this.weather.today.power = weatherInfo.today.power
                        this.weather.today.aqi = weatherInfo.today.aqi
                        //明天
                        this.weather.future[0].date = weatherInfo.future[0].date
                        this.weather.future[0].temperature = weatherInfo.future[0].temperature
                        this.weather.future[0].weather = weatherInfo.future[0].weather
                        this.weather.future[0].wid.day = weatherInfo.future[0].wid.day
                        this.weather.future[0].wid.dayImg = weatherInfo.future[0].wid.dayImg
                        this.weather.future[0].wid.night = weatherInfo.future[0].wid.night
                        this.weather.future[0].wid.nightImg = weatherInfo.future[0].wid.nightImg
                        this.weather.future[0].direct = weatherInfo.future[0].direct
                        //后天
                        this.weather.future[1].date = weatherInfo.future[1].date
                        this.weather.future[1].temperature = weatherInfo.future[1].temperature
                        this.weather.future[1].weather = weatherInfo.future[1].weather
                        this.weather.future[1].wid.day = weatherInfo.future[1].wid.day
                        this.weather.future[1].wid.dayImg = weatherInfo.future[1].wid.dayImg
                        this.weather.future[1].wid.night = weatherInfo.future[1].wid.night
                        this.weather.future[1].wid.nightImg = weatherInfo.future[1].wid.nightImg
                        this.weather.future[1].direct = weatherInfo.future[1].direct
                        //大后天
                        this.weather.future[2].date = weatherInfo.future[2].date
                        this.weather.future[2].temperature = weatherInfo.future[2].temperature
                        this.weather.future[2].weather = weatherInfo.future[2].weather
                        this.weather.future[2].wid.day = weatherInfo.future[2].wid.day
                        this.weather.future[2].wid.dayImg = weatherInfo.future[2].wid.dayImg
                        this.weather.future[2].wid.night = weatherInfo.future[2].wid.night
                        this.weather.future[2].wid.nightImg = weatherInfo.future[2].wid.nightImg
                        this.weather.future[2].direct = weatherInfo.future[2].direct
                        //大大后天
                        this.weather.future[3].date = weatherInfo.future[3].date
                        this.weather.future[3].temperature = weatherInfo.future[3].temperature
                        this.weather.future[3].weather = weatherInfo.future[3].weather
                        this.weather.future[3].wid.day = weatherInfo.future[3].wid.day
                        this.weather.future[3].wid.dayImg = weatherInfo.future[3].wid.dayImg
                        this.weather.future[3].wid.night = weatherInfo.future[3].wid.night
                        this.weather.future[3].wid.nightImg = weatherInfo.future[3].wid.nightImg
                        this.weather.future[3].direct = weatherInfo.future[3].direct
                    } else {
                        this.getWeatherFromNet(city)
                    }
                } else {
                    this.getWeatherFromNet(city)
                }
            },
            getWeatherFromNet(city) {
                this.$api.weatherFromNet(city).then(res => {
                    if (res.data.error_code == 0) {
                        localStorage.removeItem("weatherInfo")
                        //今天
                        this.weather.today.date = getNowFormatDate("YYYY-MM-DD")
                        this.weather.imgSrc = this.transformWeatherImg(res.data.result.realtime.wid)
                        this.weather.today.temperature = res.data.result.future[0].temperature
                        this.weather.today.humidity = res.data.result.realtime.humidity
                        this.weather.today.weather = res.data.result.future[0].weather
                        this.weather.today.wid.day = getEnumValue(JuHe.weatherArray, "wid", "weather", res.data.result.future[0].wid.day)
                        this.weather.today.wid.dayImg = this.transformWeatherImg(res.data.result.future[0].wid.day)
                        this.weather.today.wid.night = getEnumValue(JuHe.weatherArray, "wid", "weather", res.data.result.future[0].wid.night)
                        this.weather.today.wid.nightImg = this.transformWeatherImg(res.data.result.future[0].wid.night)
                        this.weather.today.direct = res.data.result.future[0].direct
                        this.weather.today.power = res.data.result.realtime.power
                        this.weather.today.aqi = res.data.result.realtime.aqi
                        //明天
                        this.weather.future[0].date = res.data.result.future[1].date
                        this.weather.future[0].temperature = res.data.result.future[1].temperature
                        this.weather.future[0].weather = res.data.result.future[1].weather
                        this.weather.future[0].wid.day = res.data.result.future[1].wid.day
                        this.weather.future[0].wid.dayImg = this.transformWeatherImg(res.data.result.future[1].wid.day)
                        this.weather.future[0].wid.night = res.data.result.future[1].wid.night
                        this.weather.future[0].wid.nightImg = this.transformWeatherImg(res.data.result.future[1].wid.night)
                        this.weather.future[0].direct = res.data.result.future[1].direct
                        //后天
                        this.weather.future[1].date = res.data.result.future[2].date
                        this.weather.future[1].temperature = res.data.result.future[2].temperature
                        this.weather.future[1].weather = res.data.result.future[2].weather
                        this.weather.future[1].wid.day = res.data.result.future[2].wid.day
                        this.weather.future[1].wid.dayImg = this.transformWeatherImg(res.data.result.future[2].wid.day)
                        this.weather.future[1].wid.night = res.data.result.future[2].wid.night
                        this.weather.future[1].wid.nightImg = this.transformWeatherImg(res.data.result.future[2].wid.night)
                        this.weather.future[1].direct = res.data.result.future[2].direct
                        //大后天
                        this.weather.future[2].date = res.data.result.future[3].date
                        this.weather.future[2].temperature = res.data.result.future[3].temperature
                        this.weather.future[2].weather = res.data.result.future[3].weather
                        this.weather.future[2].wid.day = res.data.result.future[3].wid.day
                        this.weather.future[2].wid.dayImg = this.transformWeatherImg(res.data.result.future[3].wid.day)
                        this.weather.future[2].wid.night = res.data.result.future[3].wid.night
                        this.weather.future[2].wid.nightImg = this.transformWeatherImg(res.data.result.future[3].wid.night)
                        this.weather.future[2].direct = res.data.result.future[3].direct
                        //大大后天
                        this.weather.future[3].date = res.data.result.future[4].date
                        this.weather.future[3].temperature = res.data.result.future[4].temperature
                        this.weather.future[3].weather = res.data.result.future[4].weather
                        this.weather.future[3].wid.day = res.data.result.future[4].wid.day
                        this.weather.future[3].wid.dayImg = this.transformWeatherImg(res.data.result.future[4].wid.day)
                        this.weather.future[3].wid.night = res.data.result.future[4].wid.night
                        this.weather.future[3].wid.nightImg = this.transformWeatherImg(res.data.result.future[4].wid.night)
                        this.weather.future[3].direct = res.data.result.future[4].direct
                        localStorage.setItem("weatherInfo", JSON.stringify(this.weather))
                    }
                })
            },
            //根据天气代码获取天气图片地址
            transformWeatherImg(code) {
                switch (code) {
                    //晴
                    case"00":
                        return this.imgs.qing
                    //多云
                    case"01":
                        return this.imgs.duoyun
                    //阴
                    case"02":
                        return this.imgs.yin
                    //阵雨
                    case"03":
                        return this.imgs.yuzhuanqing
                    //雷阵雨
                    case"04":
                        return this.imgs.leizhenyu
                    //雷阵雨伴有冰雹
                    case"05":
                        return this.imgs.bingbao
                    //雨夹雪&冻雨
                    case"06":
                        return this.imgs.yujiaxue
                    case"19":
                        return this.imgs.yujiaxue
                    //小雨
                    case"07":
                        return this.imgs.xiaoyu
                    //中雨&&小到中雨
                    case"08":
                        return this.imgs.zhongyu
                    case"21":
                        return this.imgs.zhongyu
                    //大雨&暴雨&大暴雨&特大暴雨&中到大雨&大到暴雨&暴雨到大暴雨&大暴雨到特大暴雨
                    case"09":
                        return this.imgs.dayu
                    case"10":
                        return this.imgs.dayu
                    case"11":
                        return this.imgs.dayu
                    case"12":
                        return this.imgs.dayu
                    case"22":
                        return this.imgs.dayu
                    case"23":
                        return this.imgs.dayu
                    case"24":
                        return this.imgs.dayu
                    case"25":
                        return this.imgs.dayu
                    //阵雪&小雪
                    case"13":
                        return this.imgs.xiaoxue
                    case"14":
                        return this.imgs.xiaoxue
                    //中雪&小到中雪
                    case"15":
                        return this.imgs.zhongxue
                    case"26":
                        return this.imgs.zhongxue
                    //大雪&暴雪&中到大雪&大到暴雪
                    case"16":
                        return this.imgs.daxue
                    case"17":
                        return this.imgs.daxue
                    case"27":
                        return this.imgs.daxue
                    case"28":
                        return this.imgs.daxue
                    //雾&霾
                    case"18":
                        return this.imgs.wu
                    case"53":
                        return this.imgs.wu
                    //沙尘暴&浮尘&扬沙
                    case"20":
                        return this.imgs.dafeng
                    case"29":
                        return this.imgs.dafeng
                    case"30":
                        return this.imgs.dafeng
                    case"31":
                        return this.imgs.dafeng
                    default:
                        return this.imgs.duoyunzhuanqing
                }
            },

            //获取访问次数
            getVisitorNum() {
                this.$api.getVisitorNum().then(res => {
                    if (res.status === 0) {
                        this.visitor.today = res.data.today
                        this.visitor.total = res.data.total
                        this.visitor.per = Math.round(this.visitor.today / this.visitor.total * 100 * 2) > 100 ? 100 : Math.round(this.visitor.today / this.visitor.total * 100 * 2)
                        this.visitor.hot = this.visitor.per > 0 ? (this.visitor.per > 20 ? (this.visitor.per > 40 ? (this.visitor.per > 60 ? (this.visitor.per >= 80 ? 5 : 0) : 4) : 3) : 2) : 1
                        console.log("访客百分比", this.visitor.per)
                        console.log("访客热度", this.visitor.hot)
                    }
                })
            }
        },
        created() {
            this.getNowTime()
            this.getLocationFromCookies()
            this.getVisitorNum()
        }
    }
</script>
<style lang="less" scoped>
    .cardMessage {
        .ivu-icon {
            font-size: 38px;
        }

        .subText {
            color: #9ea7b4;
        }
    }
</style>
