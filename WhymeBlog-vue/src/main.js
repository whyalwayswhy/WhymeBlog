// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
// 存储
import store from './store'
import router from './router'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"

import axios from 'axios'

//导入axios.js文件
import "./axios"
//导入permission.js 路由拦截
import "./permission"

import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// use
Vue.use(mavonEditor)

//引用全局
Vue.prototype.$axios = axios 


Vue.use(Element)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
