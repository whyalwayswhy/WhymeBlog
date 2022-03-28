import axios from 'axios'
import Element from 'element-ui'
import store from './store'
import router from './router'

axios.defaults.baseURL = "http://localhost:8081"
// axios.defaults.baseURL = "http://47.103.37.158:8081"

// 进行前置拦截和后置拦截
//前置拦截
axios.interceptors.request.use(config =>{
    //可以添加请求头的信息
    return config
})

//异常的时候出现弹窗提示 后置拦截
axios.interceptors.response.use(response =>{
    let res = response.data;

    console.log("-----------")
    console.log(res)
    console.log("-----------")
    //如果正常对内容 进行一个解析

    //如果不正常的话  根据code 弹窗告诉用户 异常是什么
    if(res.code == 200){
        return response
    }else{
        Element.Message.error('输入错误',{duration: 3 * 1000});
        return Promise.reject(response.data.msg)
    }
},
    //error的情况
    error =>{
        console.log(error)
        if(error.response.data){
            error.message = error.response.data.msg
        }

        if(error.response.status === 401){
            //跳转到登录页面 全局面尽量清空
            store.commit("REMOVE_INFO")
            router.push("/login")
        }else{
            //不是401就弹窗提示
            Element.Message.error(error.message,{duration: 3 * 1000});
            //阻止返回
            return Promise.reject(error)
        }
    }
)