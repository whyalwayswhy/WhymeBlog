import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex)

export default new Vuex.Store({
    //定义全局参数 其他页面可以直接获取state里面的内容
  state: {
    token:localStorage.getItem("token"),
    userInfo:JSON.parse(sessionStorage.getItem("userInfo"))
  },
  mutations: {
      //相当于实体类的set
    SET_TOKEN:(state,token) =>{
        state.token = token
        localStorage.setItem("token",token)
    },
    SET_USERINFO:(state,userInfo) =>{
        state.userInfo = userInfo
        //可以存入会话的sessionStorage里面 sessionStorage中只能存字符串 不能存入对象
        sessionStorage.setItem("userInfo",JSON.stringify(userInfo))
    },
    // 删除token和userInfo
    REMOVE_INFO:(state) =>{
        state.token = '';
        state.userInfo = {};
        localStorage.setItem('token','');
        sessionStorage.setItem("userInfo",JSON.stringify(''));
    },
  },
  getters:{
    //get 获取userInfo信息
    //配置一个getUser可以直接获取已经反序列化对象的一个userInfo
    getUser: state=>{
        return state.userInfo;
    },
    getToken:state=>{
        return state.token;
    }
  },
  actions: {
    
  },
  modules: {
    
  }
})
