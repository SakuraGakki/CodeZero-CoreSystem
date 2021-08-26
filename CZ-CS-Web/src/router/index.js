import Vue from 'vue'
import Router from 'vue-router'
import homepage from '@/views/homepage'
import me from '@/views/me/me'
import MeIndex from '@/views/me/index'

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
        }
      ]
    }
  ]
})
