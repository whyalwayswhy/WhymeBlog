<template>
    <div>
        <Header></Header>

        <div class="mBlog">
            <h2>{{blog.title}}</h2>
            <el-button type="primary" icon="el-icon-edit" v-if="ownBlog">
                <router-link :to="{name: 'BlogEdit',params:{blogId:blog.id}}">
                  编辑
                </router-link>
            </el-button>

            <el-link icon="el-icon-delete" v-if="ownBlog" >
                <el-button type="danger" round @click="delBlog">
                    删除
                </el-button>
            </el-link>


            <el-divider></el-divider>
            <div class="markdown-body" v-html="blog.content"></div>
        </div>
    </div>
</template>

<script>
import 'github-markdown-css/github-markdown.css'
import Header from '../components/Header.vue'
export default {
  name:"BlogDetail.vue",
  components: {Header},
  data(){
      return{
          blog:{
              id:'',
              title:'',
              content:'',
          },
          ownBlog: false
      }
  },
  methods: {
    delBlog () {
      const blogId = this.$route.params.blogId
      const _this = this
      if (blogId) {
        this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            //要写成 + blogId 不能写成 '/blog/delete/{$blogId}'
          _this.$axios.post('/blog/delete/' + blogId, null, {
          // _this.$axios.post('http://47.103.37.158:8081/blog/delete/' + blogId, null, {
            headers: {
              "Authorization": localStorage.getItem("token")
            }
          }).then(res => {
            this.$message({
              type: 'success',
              message: res.data.data
            });
            _this.$router.push("/blogs")
          })

        }).catch(() => {

          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });

      }
    }
  },
  created(){
      const blogId = this.$route.params.blogId
      console.log(blogId)
      const _this = this
      this.$axios.get('/blog/'+ blogId).then(res =>{
      // this.$axios.get('http://47.103.37.158:8081/blog/'+ blogId).then(res =>{
          const blog = res.data.data
          _this.blog.id = blog.id
          _this.blog.title = blog.title
      
        var MarkdownIt = require("markdown-it")
        var md = new MarkdownIt()
        //重定向
        var result = md.render(blog.content)
        _this.blog.content = result
        //不能漏掉 this. 否则会校验不通过
        _this.ownBlog = (_this.blog.userId === _this.$store.getters.getUser.Id)
    })
  }

}
</script>

<style>
.mBlog{
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    width:100%;
    min-height: 700px;
    padding: 20px;
}

</style>