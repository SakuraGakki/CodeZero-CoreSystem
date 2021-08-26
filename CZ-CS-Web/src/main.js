// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import router from './router'
import $ajax from 'axios'

/* 引入viewui */
import ViewUI from 'view-design'
import 'view-design/dist/styles/iview.css'

import { BaseUrl } from "./config/config";


Vue.use(ViewUI)
Vue.use(VueRouter)
Vue.use($ajax)
Vue.config.productionTip = false
Vue.config.BaseUrl = BaseUrl

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
