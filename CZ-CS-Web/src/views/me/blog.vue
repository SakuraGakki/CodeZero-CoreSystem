<template>
  <div class="me">
    <div class="me-blog-div">
      <div class="me-blog-body scrollbar">
        <!-- 最新动态 -->
        <Row style="background:rgba(238,238,238,0.5);padding:20px">
          <Col span="24">
            <Card shadow>
              <p slot="title" class="me-blog-title-font">最新动态</p>
              <Divider orientation="left">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="right">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="left">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="right">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="left">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="right">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="left">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="right">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="right">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="left">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="right">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="left">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
              <Divider orientation="right">Left Text</Divider>
              <p class="me-blog-content-font">
                这里记录了我在学习、工作中的所有编程积累、经验及总结。希望对大家的工作学习有所帮助，也希望能有更多的朋友通过本站了解编程知识，感受到代码的乐趣与魅力。</p>
            </Card>
          </Col>
        </Row>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "MeBlog",
    data() {
      return{

      }
    }
  }
</script>

<style scoped>
  .me{
    background-image: url("../../assets/img/me/background.jpg");
    background-size:100% 100%;
    background-repeat:no-repeat;
  }
  .me-blog-div {
    margin: 0 auto;
    width: 1200px;
    height: 100%;
    box-shadow: 2px 2px 10px #909090;
  }

  .me-blog-body {
    width: 100%;
    height: 800px;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0);
  }

  .me-blog-title-font {
    font-family: "楷体";
    font-size: 24px;
  }

  .me-blog-content-font {
    font-family: "宋体";
    font-size: 20px;
  }

  .scrollbar::-webkit-scrollbar {/*滚动条整体样式*/
    display: none;  /*控制滚动条显示隐藏*/
    width: 5px;     /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
  }
  .scrollbar::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    background: #535353;
  }
  .scrollbar::-webkit-scrollbar-track {/*滚动条里面轨道*/
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    border-radius: 10px;
    background: #EDEDED;
  }
</style>
