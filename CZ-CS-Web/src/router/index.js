import Vue from 'vue'
import Router from 'vue-router'
import homepage from '@/components/homepage'
import index from '@/components/index/index'
import me from '@/components/me/me'
import MeIndex from '@/components/me/index'
import resources from '@/components/resources/resources'
import utils from '@/components/utils/utils'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'homepage',
      component: homepage
    },
    {
      path: '/index',
      name: 'index',
      component: index
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
        }
      ]
    },
    {
      path: '/resources',
      name: 'resources',
      component: resources
    },
    {
      path: '/utils',
      name: 'utils',
      component: utils
    }
  ]
})
