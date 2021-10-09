import Home from '@/components/layout/Home'
import Index from '@/components/views/Index'

import MsgBoardList from '@/components/views/Message/MessageBoardList'


import Chart from '@/components/views/Chart'
import NotFound from '@/components/pages/NotFound'
import Register from '@/components/pages/Register'
import E404 from '@/components/pages/E404'
import Icon from '@/components/pages/Icon'
import Login from '@/components/pages/Login'
import Table from '@/components/views/Table'
import UserInfo from '@/components/views/UserInfo'
import List from '@/components/views/List'
import Filter from '@/components/pages/Filter'
import Lock from '@/components/layout/base/Lock'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
    redirect: '/index',
    children: [
      {
        path: '/index',
        name: '首页',
        component: Index,
        icon: 'icon-wujiaoxing',
        level: 1,
        meta: {
          requiresAuth: true // 是否需要登录
        }
      },
      {
        path: '/msgBoardList',
        name: '留言列表',
        component: MsgBoardList,
        meta: {
          requiresAuth: true // 是否需要登录
        }
      },
      {
        path: '/chart',
        name: 'chart',
        component: Chart,
        meta: {
          requiresAuth: true // 是否需要登录
        }
      },
      {
        path: '/list',
        name: 'list',
        component: List,
        meta: {
          requiresAuth: true // 是否需要登录
        }
      },
      {
        path: '/userInfo',
        name: 'userInfo',
        component: UserInfo,
        icon: 'icon-addpeople_fill',
        meta: {
          requiresAuth: true // 是否需要登录
        }
      },
      {
        path: '/table',
        name: 'table',
        component: Table,
        meta: {
          requiresAuth: true // 是否需要登录
        }
      },
      {
        path: '/icon',
        name: 'icon',
        component: Icon,
        meta: {
          requiresAuth: true // 是否需要登录
        }
      },
      {
        path: '/filter',
        name: 'filter',
        component: Filter,
        meta: {
          requiresAuth: true // 是否需要登录
        }
      },
      {
        path: '/notFound',
        name: 'notFound',
        component: NotFound,
        meta: {
          requiresAuth: true // 是否需要登录
        }
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/error404',
    name: 'error404',
    component: E404
  },
  {
    path: '/lock',
    name: 'lock',
    component: Lock
  },
  {
    path: '*',
    redirect: '/notFound'
  }

]

export default routes
