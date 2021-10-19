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
          path: '/messageboard',
          name: 'messageboard',
          component: MessageBoard
        }
      ]
    }
  ]
})
