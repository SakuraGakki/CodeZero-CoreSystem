<template>
  <div>
    <br>
    <div style="margin-left: 40px"><h2>在线调色盘</h2></div>
    <br>
    <Row type="flex" justify="space-around" >
      <Col span="20">
        <Card style="height: 600px">
          <div class="colorSelector"
               @mouseup="mouseUp"
               @mousemove="mouseMove">
            <div class="selectorDiv">
              <!-- 基本颜色 -->
              <div class="baseColorDiv"
                   @mousedown="sliderDown">
                <div v-for="(item, index) in baseColorList"
                     :key="index"
                     class="baseColorItem"
                     :style="{backgroundColor: item}"
                     @click="baseColorItemClicked"></div>
                <div class="slideBox"
                     :style="{top: `${sliderTop}px`}"></div>
              </div>
              <!-- 基本颜色下选择详细颜色 -->
              <div class="detailColorDiv"
                   @mousedown="detailDown">
                <div v-for="(item, index) in detailColorList"
                     :key="index"
                     class="detailColorItem"
                     :style="{backgroundImage: `linear-gradient(to right, ${item[0]}, ${item[1]})`}"
                     @click="detailColorItemClicked"></div>
                <div class="detailSelector"
                     :style="{top: `${detailColorSelectorTop}px`, left: `${detailColorSelectorLeft}px`}"></div>
              </div>
            </div>
          </div>
          <Row type="flex" justify="space-around" >
            <Col span="8">
              <br>
              <Card style="">
                <p slot="title">预览颜色</p>
                <div class="colorResult"
                     :style="{backgroundColor: colorResultRgbStr || baseColorSeleced}"></div>
              </Card>
            </Col>
            <Col span="8">
              <br>
              <Card>
                <p slot="title">颜色参数</p>
                <span>RGB：</span>
                <Input v-model.lazy="colorResultRgbStr">
                  <span slot="prepend">点击后方按钮复制</span>
                  <Button slot="append"
                          icon="ios-browsers"
                          type="primary"
                          title="复制"
                          v-clipboard:copy="colorResultRgbStr"
                          v-clipboard:success="onCopy"
                          v-clipboard:error="onError"
                  >
                  </Button>
                </Input>
                <br>
                <span>16进制：</span>
                <Input v-model.lazy="colorResultHexStr">
                  <span slot="prepend">点击后方按钮复制</span>
                  <Button slot="append"
                          icon="ios-browsers"
                          type="primary"
                          title="复制"
                          v-clipboard:copy="colorResultHexStr"
                          v-clipboard:success="onCopy"
                          v-clipboard:error="onError"
                  >
                  </Button>
                </Input>
              </Card>
            </Col>
          </Row>
        </Card>
      </Col>

    </Row>
  </div>
</template>

<script>

  export default {
    name: "colorUtil",
    data () {
      return {
        baseColorList: [], // 基本颜色列表
        sliderAvailable: false, // 滑块是否可滑动
        sliderTop: 50, // 滑块距父组件的高度
        baseColorSeleced: null, // 选中的基本颜色
        detailColorList: [], // 详细颜色列表
        detailColorSelectorSliding: false, // 详细颜色选择器是否可滑动
        detailColorSelectorTop: -4,
        detailColorSelectorLeft: 356,
        detailColorSelectorPos: { // 详细颜色选择器距父组件的位置参数
          top: 0,
          left: 0
        },
        colorResultRgbStr: null, // 最终结果
        colorResultHexStr: null,
      }
    },
    mounted () {
      this.init()
    },
    watch: {
      colorResultRgbStr (val) {
        let R = val.match(/\d+(.\d+)?/g)[0] * 1
        let G = val.match(/\d+(.\d+)?/g)[1] * 1
        let B = val.match(/\d+(.\d+)?/g)[2] * 1
        this.colorResultRgbStr = `rgb(${R}, ${G}, ${B})`
        this.colorResultHexStr = `#${this.toHex(R, G, B)}`
      },
      colorResultHexStr (val) {
        let R = this.hexToRgb(val).r
        let G = this.hexToRgb(val).g
        let B = this.hexToRgb(val).b
        this.colorResultRgbStr = `rgb(${R}, ${G}, ${B})`
      }
    },
    methods: {
      init () {
        let r = 255
        let g = 0
        let b = 0
        let baseColorStr = ''
        for (let i = 0; i < 192; i++) {
          if (i === 0) {
            r = 255
            g = 0
            b = 0
          } else if (i > 0 && i <= 32) {
            r = 255
            g = i * 8 - 1
            b = 0
          } else if (i > 32 && i <= 64) {
            r = 255 - (i - 32) * 8 + 1
            g = 255
            b = 0
          } else if (i > 64 && i <= 96) {
            r = 0
            g = 255
            b = (i - 64) * 8 - 1
          } else if (i > 96 && i <= 128) {
            r = 0
            g = 255 - (i - 96) * 8 + 1
            b = 255
          } else if (i > 128 && i <= 160) {
            r = (i - 128) * 8 - 1
            g = 0
            b = 255
          } else {
            r = 255
            g = 0
            b = 255 - (i - 160) * 8 + 1
          }
          // baseColorStr = this.toHex(r, g, b)
          baseColorStr = `rgb(${r}, ${g}, ${b})`
          this.baseColorList.push(baseColorStr)
        }
        this.baseColorSeleced = this.baseColorList[this.sliderTop + 3]
        this.initDetailColors()
        let baseR = this.baseColorSeleced.match(/\d+(.\d+)?/g)[0] * 1
        let baseG = this.baseColorSeleced.match(/\d+(.\d+)?/g)[1] * 1
        let baseB = this.baseColorSeleced.match(/\d+(.\d+)?/g)[2] * 1
        this.colorResultRgbStr = `rgb(${baseR}, ${baseG}, ${baseB})`
        this.colorResultHexStr = `#${this.toHex(baseR, baseG, baseB)}`
      },
      // 十进制转换成16进制
      toHex (r, g, b) {
        let R = (Number(r).toString(16)).slice(-2).toUpperCase()
        let G = (Number(g).toString(16)).slice(-2).toUpperCase()
        let B = (Number(b).toString(16)).slice(-2).toUpperCase()
        if (R.length === 1) R = '0' + R
        if (G.length === 1) G = '0' + G
        if (B.length === 1) B = '0' + B
        return `${R}${G}${B}`
      },
      // 十六进制转换成十进制
      hexToRgb (hex) {
        var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex)
        return result ? {
          r: parseInt(result[1], 16),
          g: parseInt(result[2], 16),
          b: parseInt(result[3], 16)
        } : null
      },
      // 按下滑块
      sliderDown () {
        this.sliderAvailable = true
        this.mouseMove()
      },
      // 抬起鼠标
      mouseUp () {
        this.sliderAvailable = false
        this.detailColorSelectorSliding = false
      },
      // 移动鼠标
      mouseMove () {
        let event = window.event
        let mouseClientY = event.clientY // 鼠标基于浏览器Y坐标
        let mouseClientX = event.clientX // 鼠标基于浏览器X坐标
        // 滑动基本颜色滑块
        if (this.sliderAvailable) {
          let a = document.getElementsByClassName('baseColorDiv')[0]
          let baseColorDivClientY = this.getPosition(a).top // 基本颜色组Div基于浏览器Y坐标
          this.sliderTop = mouseClientY - baseColorDivClientY - 3
          if (this.sliderTop <= -3) this.sliderTop = -3
          if (this.sliderTop >= 188) this.sliderTop = 188
          this.baseColorSeleced = this.baseColorList[this.sliderTop + 3]
          this.initDetailColors()
          this.detailColorSelectorPosition(356, -4)
          this.detailColorSelected()
        }
        // 滑动详细颜色选择器
        if (this.detailColorSelectorSliding) {
          let a = document.getElementsByClassName('detailColorDiv')[0]
          let detailColorDivClientY = this.getPosition(a).top // 详细颜色Div基于浏览器Y坐标
          let detailColorDivClientX = this.getPosition(a).left // 详细颜色Div基于浏览器X坐标
          let SelectorTop = mouseClientY - detailColorDivClientY - 4
          let SelectorLeft = mouseClientX - detailColorDivClientX - 4
          this.detailColorSelectorPosition(SelectorLeft, SelectorTop)
          this.detailColorSelected()
        }
      },
      // 详细颜色选择器位置
      detailColorSelectorPosition (x, y) {
        this.detailColorSelectorLeft = x
        this.detailColorSelectorTop = y
        if (this.detailColorSelectorTop <= -4) this.detailColorSelectorTop = -4
        if (this.detailColorSelectorTop >= 188) this.detailColorSelectorTop = 188
        if (this.detailColorSelectorLeft <= -4) this.detailColorSelectorLeft = -4
        if (this.detailColorSelectorLeft >= 356) this.detailColorSelectorLeft = 356
        this.detailColorSelectorPos.top = this.detailColorSelectorTop + 4
        this.detailColorSelectorPos.left = this.detailColorSelectorLeft + 4
      },
      // 详细颜色数组构建
      initDetailColors () {
        let baseR = this.baseColorSeleced.match(/\d+(.\d+)?/g)[0] * 1
        let baseG = this.baseColorSeleced.match(/\d+(.\d+)?/g)[1] * 1
        let baseB = this.baseColorSeleced.match(/\d+(.\d+)?/g)[2] * 1
        this.detailColorList = []
        for (let i = 1; i <= 192; i++) {
          let xr = 255
          let detailRl = Math.round(xr - ((i / 192) * xr))
          xr = baseR
          let detailRr = Math.round(xr - ((i / 192) * xr))

          let xg = 255
          let detailGl = Math.round(xg - ((i / 192) * xg))
          xg = baseG
          let detailGr = Math.round(xg - ((i / 192) * xg))

          let xb = 255
          let detailBl = Math.round(xb - ((i / 192) * xb))
          xb = baseB
          let detailBr = Math.round(xb - ((i / 192) * xb))
          // let detailColorStr = this.toHex(detailR, detailG, detailB)
          let detailColorStr = [`rgb(${detailRl}, ${detailGl}, ${detailBl})`, `rgb(${detailRr}, ${detailGr}, ${detailBr})`]
          this.detailColorList.push(detailColorStr)
        }
      },
      // 获取元素到文档区域的坐标
      getPosition (element) {
        let actualLeft = element.offsetLeft
        let actualTop = element.offsetTop
        let current = element.offsetParent // 取得元素的offsetParent
        // 一直循环直到根元素
        while (current !== null) {
          actualLeft += current.offsetLeft
          actualTop += current.offsetTop
          current = current.offsetParent
        }
        // 返回包含left、top坐标的对象
        return {
          left: actualLeft,
          top: actualTop
        }
      },
      // 点击选择基本颜色
      baseColorItemClicked () { },
      // 点击选择详细颜色
      detailColorItemClicked () { },
      // 按下详细颜色
      detailDown () {
        this.detailColorSelectorSliding = true
        this.mouseMove()
      },
      // 绑定详细颜色
      detailColorSelected () {
        let baseR = this.baseColorSeleced.match(/\d+(.\d+)?/g)[0] * 1
        let baseG = this.baseColorSeleced.match(/\d+(.\d+)?/g)[1] * 1
        let baseB = this.baseColorSeleced.match(/\d+(.\d+)?/g)[2] * 1
        let top = this.detailColorSelectorPos.top
        let left = this.detailColorSelectorPos.left

        let xr = Math.round(255 - left / 360 * (255 - baseR))
        let detailR = Math.round(xr - ((top / 192) * xr))

        let xg = Math.round(255 - left / 360 * (255 - baseG))
        let detailG = Math.round(xg - ((top / 192) * xg))

        let xb = Math.round(255 - left / 360 * (255 - baseB))
        let detailB = Math.round(xb - ((top / 192) * xb))

        this.colorResultRgbStr = `rgb(${detailR}, ${detailG}, ${detailB})`
        this.colorResultHexStr = `#${this.toHex(detailR, detailG, detailB)}`
      },
      //点击复制
      onCopy(){
        this.$Message.success("复制成功")
      },
      onError(){
        this.$Message.error("复制失败")
      }
    }
  }
</script>

<style lang='less' scoped>
  .colorSelector {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    .selectorDiv {
      width: 415px;
      height: 240px;
      padding: 10px;
      border-radius: 4px;
      background-color: rgba(0, 0, 0, 0.2);
      display: flex;
      align-items: center;
      flex-wrap: wrap;
      .baseColorDiv {
        width: 15px;
        height: 192px;
        background-color: white;
        margin-right: 10px;
        position: relative;
        .baseColorItem {
          width: 100%;
          height: 1px;
          border: none;
          pointer-events: none;
        }
        .slideBox {
          width: 15px;
          height: 1px;
          border: 3px solid white;
          position: absolute;
          left: -3px;
          border-radius: 2px;
          cursor: pointer;
          // pointer-events: none;
        }
      }
      .detailColorDiv {
        width: 360px;
        height: 192px;
        border: 1px solid rgba(1, 1, 1, 0.3);
        background-color: white;
        display: flex;
        flex-wrap: wrap;
        position: relative;
        .detailColorItem {
          width: 360px;
          height: 1px;
          border: none;
          pointer-events: none;
        }
        .detailSelector {
          width: 3px;
          height: 3px;
          border: 2px solid white;
          border-radius: 50%;
          background-color: rgba(0, 0, 0, 0);
          position: absolute;
          cursor: pointer;
        }
      }
    }
  }
  .colorResult {
    width: 420px;
    height: 200px;
  }
  input {
    width: 126px;
    height: 24px;
    border: 1px solid rgba(0, 0, 0, 0.2);
    border-radius: 4px;
    outline: none;
    margin-top: 7px;
    font-size: 15px;
    font-weight: 700;
    color: rgba(0, 0, 0, 0.5);
    margin-right: 10px;
  }
</style>
