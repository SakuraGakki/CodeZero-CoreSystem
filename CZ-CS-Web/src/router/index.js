import Vue from 'vue'
import Router from 'vue-router'
import homepage from '@/views/homepage'
import me from '@/views/me/me'
import MeIndex from '@/views/me/index'
import MessageBoard from '@/views/message/msgboard'
import Utils from '@/views/utils/utils'
import jsonUtil from "@/views/utils/jsonUtil"
import qrcodeUtil from "@/views/utils/qrcodeUtil"
import encodeUtil from "@/views/utils/encodeUtil"
import colorUtil from "@/views/utils/colorUtil"
import ipUtil from "@/views/utils/ipUtil"
import regularExpressionsUtil from "@/views/utils/regularExpressionsUtil"
import ArticleList from "@/views/article/articleList"
import ArticleContent from "@/views/article/articleContent"


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'homepage',
      component: homepage
    },
    {
      path: '/me',
      name: 'me',
      component: me,
      children: [
        {
          path: '/meindex',
          name: 'meindex',
          component: MeIndex
        },
        {
          path: '/utils',
          name: 'utils',
          component: Utils,
        },
        {
          path:'/jsonUtil',
          name:'jsonUtil',
          component:jsonUtil
        },
        {
          path:'/qrcodeUtil',
          name:'qrcodeUtil',
          component:qrcodeUtil
        },
        {
          path:'/encodeUtil',
          name:'encodeUtil',
          component:encodeUtil
        },
        {
          path:'/colorUtil',
          name:'colorUtil',
          component:colorUtil
        },
        {
          path:'/ipUtil',
          name:'ipUtil',
          component:ipUtil
        },
        {
          path:'/regularExpressionsUtil',
          name:'regularExpressionsUtil',
          component:regularExpressionsUtil
        },
        // {
        //   path: '/messageboard',
        //   name: 'messageboard',
        //   component: MessageBoard
        // },
        {
          path: '/articleList',
          name: 'articleList',
          component: ArticleList
        },
        {
          path: '/articleContent',
          name: 'articleContent',
          component: ArticleContent
        }
      ]
    }
  ]
})
