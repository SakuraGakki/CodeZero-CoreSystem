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
                                    <Poptip placement="right" width="400">
                                        <p class="subText">今天日期：</p>
                                        <h3>{{calender.date}}</h3>
                                        <div class="api" slot="content">
                                            <h3>老黄历</h3>
                                            <p><B> 阴历：</B>{{calender.huangli.yinli}}</p>
                                            <p><B> 彭祖百忌：</B>{{calender.huangli.baiji}}</p>
                                            <p><B> 吉神宜趋：</B>{{calender.huangli.jishen}}</p>
                                            <p><B> 凶神宜忌：</B>{{calender.huangli.xiongshen}}</p>
                                            <p><B> 五行：</B>{{calender.huangli.wuxing}}</p>
                                            <p><B> 冲煞：</B>{{calender.huangli.chongsha}}</p>
                                            <p><B> 宜：</B>{{calender.huangli.yi}}</p>
                                            <p><B> 忌：</B>{{calender.huangli.ji}}</p>
                                        </div>
                                    </Poptip>
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
                                    <Poptip placement="right" width="300">
                                        <p class="subText">今日天气：</p>
                                        <h3>{{weather.today.weather}}，{{weather.today.temperature}}，{{weather.today.direct}}{{weather.today.power}}</h3>
                                        <div class="api" slot="content">
                                            <h3>未来几天天气情况</h3><br>
                                            <p>{{weather.future[0].date}}:<br><b>{{weather.future[0].weather}}</b>----<b>{{weather.future[0].temperature}}</b>----<b>{{weather.future[0].direct}}</b></p>
                                            <br>
                                            <p>{{weather.future[1].date}}:<br><b>{{weather.future[1].weather}}</b>----<b>{{weather.future[1].temperature}}</b>----<b>{{weather.future[1].direct}}</b></p>
                                            <br>
                                            <p>{{weather.future[2].date}}:<br><b>{{weather.future[2].weather}}</b>----<b>{{weather.future[2].temperature}}</b>----<b>{{weather.future[2].direct}}</b></p>
                                            <br>
                                            <p>{{weather.future[3].date}}:<br><b>{{weather.future[3].weather}}</b>----<b>{{weather.future[3].temperature}}</b>----<b>{{weather.future[3].direct}}</b></p>
                                        </div>
                                    </Poptip>
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
                <Card style="height: 350px">
                    <p slot="title">
                        访客TOP5
                    </p>
                    <Table :columns="topCol" :data="topData"></Table>
                </Card>
            </Col>
            <Col :md='{span:8}'>
                <Card style="height: 350px">
                    <div style="height: 350px" ref="pieChart">
                        <IEcharts ref="pie" :option="pie" :resizable="true"
                                  :update-options="{notMerge:true}"></IEcharts>
                    </div>
                </Card>
            </Col>
            <Col :md='{span:8}'>
                <Card style="height: 350px">
                    <div style="height: 350px">
                        <IEcharts ref="line" :option="line" :resizable="true"></IEcharts>
                    </div>
                </Card>
            </Col>
        </Row>
    </div>
</template>
<script>
    import {getNowFormatDate, getNowFormatTime} from '@/common/utils/dateUtils'
    import {getEnumValue} from "@/common/utils/commonUtils";
    import {JuHe} from "@/api/config";
    import echarts from 'echarts'
    import {objArrayDuplicateSum, sort} from "../../common/utils/arrayUtils";
    import Cookies from 'js-cookie'
    import IEcharts from 'vue-echarts-v3/src/full.js'

    export default {
        name: 'index',
        components: {IEcharts},
        data() {
            return {
                params: {
                    page: 1,
                    limit: 10
                },
                colorList:[
                    {
                        c1: '#a18cd1',
                        c2: '#fbc2eb',
                    },
                    {
                        c1: '#f6d365',
                        c2: '#fda085',
                    },
                    {
                        c1: '#a1c4fd',
                        c2: '#c2e9fb',
                    },
                    {
                        c1: '#d4fc79',
                        c2: '#96e6a1',
                    },
                    {
                        c1: '#84fab0',
                        c2: '#8fd3f4',
                    },
                    {
                        c1: '#4facfe',
                        c2: '#00f2fe',
                    },
                    {
                        c1: '#30cfd0',
                        c2: '#330867',
                    },
                    {
                        c1: '#fad0c4',
                        c2: '#ffd1ff',
                    },
                    {
                        c1: '#f5f7fa',
                        c2: '#c3cfe2',
                    },
                    {
                        c1: '#434343',
                        c2: '#000000',
                    }
                ],
                pie: {
                    title: {
                        text: '访客分布'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    series: [{
                        name: '访问次数',
                        type: 'pie',
                        radius: [20, 100],
                        roseType: 'area',
                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                formatter: '{b}:{c}({d}%)',
                                textStyle: {
                                    fontSize: 12,
                                    color: '#1d7fcd',
                                    fontWeight: 600,
                                    fontFamily: 'Microsoft YaHei',
                                }
                            }
                        },
                        itemStyle: {
                            borderRadius: 5,
                        },
                        data: [],
                    }]
                },
                line: {
                    tooltip: {
                        trigger: 'axis',
                        position: function (pt) {
                            return [pt[0], '10%'];
                        }
                    },
                    title: {
                        text: '热度趋势'
                    },
                    toolbox: {},
                    xAxis: {
                        type: 'category',
                        data: []
                    },
                    yAxis: {
                        type: 'value',
                        boundaryGap: [0, '100%']
                    },
                    series: [
                        {
                            name: '访问量',
                            type: 'line',
                            smooth: true,
                            symbol: 'none',
                            areaStyle: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                    {
                                        offset: 0,
                                        color: 'rgba(213,72,120,0.8)'
                                    },
                                    {
                                        offset: 1,
                                        color: 'rgba(213,72,120,0.3)'
                                    }
                                ])
                            },
                            itemStyle: {
                                color: 'rgb(255, 70, 131)'
                            },
                            data: []
                        }
                    ]
                },
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
                topCol: [
                    {
                        title: 'IP',
                        key: 'ip',
                        width: 140
                    },
                    {
                        title: '地点',
                        key: 'city',
                        width: 222
                    },
                    {
                        title: '访问次数',
                        key: 'total',
                        width: 85
                    }
                ],
                topData: [],
                visitorInfoList: [],
                tenVisitorNumList: [],
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
                // console.log("从Cookies中获取的ip", this.ip)
                // console.log("从Cookies中获取的locationInfo", this.locationInfo)
                this.getWeatherInfo(this.locationInfo.City)
            },

            //获取当前天气
            getWeatherInfo(city) {
                let date = new Date()
                let h = date.getHours()
                if (localStorage.getItem("weatherInfo") && JSON.parse(localStorage.getItem("weatherInfo")).today.date === getNowFormatDate("YYYY-MM-DD")) {
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
                        this.visitor.hot = this.visitor.per > 15 ? (this.visitor.per > 30 ? (this.visitor.per > 45 ? (this.visitor.per > 60 ? (this.visitor.per >= 75 ? 5 : 0) : 4) : 3) : 2) : 1
                        // console.log("访客百分比", this.visitor.per)
                        // console.log("访客热度", this.visitor.hot)
                    }
                })
            },
            //访客top5
            getTopFive() {
                this.$api.getTopFiveList().then(res => {
                    if (res.status === 0) {
                        // console.log("Top5", res.data)
                        this.topData = res.data
                    }
                })
            },
            //转换访客列表数据
            transformVisitorData() {
                const that = this
                let pieDataArray = new Array()
                for (let i = 0; i < this.visitorInfoList.length; i++) {
                    pieDataArray.push(
                        {
                            name: this.visitorInfoList[i].city,
                            value: this.visitorInfoList[i].total,
                        }
                    )
                }
                let key = Object.keys(pieDataArray[0])[0]
                let val = Object.keys(pieDataArray[0])[1]
                pieDataArray = objArrayDuplicateSum(pieDataArray, key, val)
                for(let i=0;i<pieDataArray.length;i++){
                    pieDataArray[i].itemStyle ={
                        normal:{
                            color: new echarts.graphic.LinearGradient(1, 0, 0, 0, [{ //颜色渐变函数 前四个参数分别表示四个位置依次为左、下、右、上
                                offset: 0,
                                color: this.colorList[i].c1
                            }, {
                                offset: 1,
                                color: this.colorList[i].c2
                            }])
                        }
                    }
                }
                // console.log("pieDataArray", pieDataArray)
                that.pie.series[0].data = pieDataArray
                // console.log("pieOptions", this.pie)
            },
            //访客信息列表
            getVisitorInfoList() {
                this.$api.getVisitorInfoList().then(res => {
                    if (res.status === 0) {
                        // console.log("visitorInfoList", res.data)
                        this.visitorInfoList = res.data
                        this.transformVisitorData()
                    }
                })
            },
            //转换10日内访客数量信息列表数据
            transformTenVisitorNumData() {
                const that = this
                let lineDataArray = new Array()
                let lineXDataArray = new Array()
                this.tenVisitorNumList = sort(this.tenVisitorNumList)
                for (let i = 0; i < this.tenVisitorNumList.length; i++) {
                    lineXDataArray.push(this.tenVisitorNumList[i].date)
                    lineDataArray.push(this.tenVisitorNumList[i].total)
                }
                // console.log("that.line.xAxis.data", lineXDataArray)
                that.line.xAxis.data = lineXDataArray
                that.line.series[0].data = lineDataArray
            },
            //获取10日内访客数量信息列表
            getTenVisitNumList() {
                this.$api.getTenVisitNumList().then(res => {
                    if (res.status === 0) {
                        // console.log("tenVisitorNumList",res.data)
                        this.tenVisitorNumList = res.data
                        this.transformTenVisitorNumData()
                    }
                })
            },
            //获取老黄历信息
            getCalender(){
                if(localStorage.getItem("huangliInfo") && JSON.parse(localStorage.getItem("huangliInfo")).yangli === getNowFormatDate("YYYY-MM-DD")){
                    let huangliInfo = JSON.parse(localStorage.getItem("huangliInfo"))
                    this.calender.huangli.baiji = huangliInfo.baiji
                    this.calender.huangli.chongsha = huangliInfo.chongsha
                    this.calender.huangli.ji = huangliInfo.ji
                    this.calender.huangli.jishen = huangliInfo.jishen
                    this.calender.huangli.wuxing = huangliInfo.wuxing
                    this.calender.huangli.xiongshen = huangliInfo.xiongshen
                    this.calender.huangli.yi = huangliInfo.yi
                    this.calender.huangli.yinli = huangliInfo.yinli
                }else{
                    this.getCalenderFromNet()
                }
            },
            getCalenderFromNet() {
                this.$api.calenderFromNet().then(res => {
                    if(res.data.error_code==0){
                        localStorage.removeItem("huangliInfo")
                        this.calender.huangli.baiji = res.data.result.baiji
                        this.calender.huangli.chongsha = res.data.result.chongsha
                        this.calender.huangli.ji = res.data.result.ji
                        this.calender.huangli.jishen = res.data.result.jishen
                        this.calender.huangli.wuxing = res.data.result.wuxing
                        this.calender.huangli.xiongshen = res.data.result.xiongshen
                        this.calender.huangli.yi = res.data.result.yi
                        this.calender.huangli.yinli = res.data.result.yinli
                        localStorage.setItem("huangliInfo",JSON.stringify(res.data.result))
                    }
                })
            },

        },
        created() {
            this.getNowTime()
            this.getLocationFromCookies()
            this.getCalender()
            this.getVisitorNum()
            this.getTopFive()
            this.getVisitorInfoList()
            this.getTenVisitNumList()
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
