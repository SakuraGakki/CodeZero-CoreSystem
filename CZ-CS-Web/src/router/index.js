import Vue from 'vue'
import Router from 'vue-router'
import homepage from '@/views/homepage'
import me from '@/views/me/me'
import MeIndex from '@/views/me/index'
import MeBlog from '@/views/me/blog'
import MeArcicle from '@/views/me/article'
import resources from '@/views/resources/resources'
import utils from '@/views/utils/utils'

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
          path: '/meblog',
          name: 'meblog',
          component: MeBlog
        },
        {
          path: '/mearcicle',
          name: 'mearcicle',
          component: MeArcicle
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
