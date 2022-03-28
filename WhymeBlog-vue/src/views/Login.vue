<template>
  <!-- template下只能有一对div标签 -->
  <div>
    <el-container>
        <el-header>
            <img class="mlogo" src="../assets/arceus.jpg" alt="logo图片">
        </el-header>
        <el-main>
            <!--  -->
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="用户名" prop="username">
    <el-input v-model="ruleForm.username"></el-input>
  </el-form-item>
  <el-form-item label="密码" prop="password">
    <el-input type="password" v-model="ruleForm.password"></el-input>
  </el-form-item>
  
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
            <!--  -->
        </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
    data() {
      return {
        ruleForm: {
          username: '',
          password: '',
          
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名称', trigger: 'blur' },
            { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'change' }
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('submit!');
            const _this = this
            // $axios 发起请求
            this.$axios.post('/login',this.ruleForm).then(res =>{
            // this.$axios.post('http://47.103.37.158:8081/login',this.ruleForm).then(res =>{
                
                const jwt = res.headers['authorization']
                const userInfo = res.data.data
                
                //把数据共享到store
                _this.$store.commit("SET_TOKEN",jwt)
                _this.$store.commit("SET_USERINFO",userInfo)

                //获取数据
                console.log(_this.$store.getters.getUser)

                //登录后 把数据跳转到相应的博客页面
                _this.$router.push("/blogs")
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
}
</script>

<style>
    .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  
  .el-main {
    /* background-color: #E9EEF3; */
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  
  .mlogo{
      height: 100%;
      
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  .demo-ruleForm{
      max-width: 500px;
      margin:0 auto;
  }
</style>