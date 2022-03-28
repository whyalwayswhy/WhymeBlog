import Vue from 'vue'
import VueRouter from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Demo from '@/views/Demo.vue'
import Login from '@/views/Login.vue' 
import Blogs from '@/views/Blogs.vue' 
import BlogEdit from '@/views/BlogEdit.vue' 
import BlogDetail from '@/views/BlogDetail.vue'  

Vue.use(VueRouter)


  const routes = [
    {
      path: '/',
      name: 'Index',
      // 重定向到blogs页面
      redirect:{name:"Blogs"}
    },
    {
      path:'/blogs',
      name: 'Blogs',
      component: Blogs
    },
    {
      path:'/login',
      name: 'Login',
      component: Login
    },
    // 放在前面 会优先匹配
    {
      path:'/blog/add',
      name: 'BlogAdd',
      meta:{
        requireAuth:true
      },
      component: BlogEdit,
      
    },
    {
      path:'/Demo',
      name: 'Demo',
      component: Demo
    },
    {
      path:'/blog/:blogId',
      name: 'BlogDetail',
      component: BlogDetail
    },
    {
      path:'/blog/:blogId/edit',
      name: 'BlogEdit',
      meta:{
        requireAuth:true
      },
      component: BlogEdit,
    }
  ];
  const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
  })
  export default router

