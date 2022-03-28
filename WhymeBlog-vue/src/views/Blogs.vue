<template>
  <div>
      <Header></Header>
      
    <div class="block">
      <el-timeline>
        <!-- v-for 里面必须要定义一个 key -->
        <el-timeline-item v-bind:timestamp="blog.created" placement="top" v-for="(blog,index) in blogs":key="index">
          <el-card>
            <h4>
              <router-link :to="{name : 'BlogDetail',params:{blogId:blog.id}}">
                {{blog.title}}
              </router-link>
              

            </h4>
            <p>{{blog.description}}</p>
          </el-card>
        </el-timeline-item>
        
      </el-timeline>

      <el-pagination class="mPage"
        background
        layout="prev, pager, next"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="total"
        @current-change = page>
    </el-pagination>
  </div>

    </div>
</template>

<script>
import Header from "../components/Header";
export default {
  name:"Blogs.vue",
  components:{Header},
  data(){
    return{
      //这些是默认的设置
      blogs:{},
      currentPage:1,
      total:0,
      pageSize:5

    }
  },
  methods: {
    page(currentPage){
      const _this = this
      
      this.$axios.get('/blogs?currentPage=' + currentPage).then(res =>{
      // this.$axios.get('http://47.103.37.158:8081/blogs?currentPage=' + currentPage).then(res =>{
        console.log(res)
        _this.blogs = res.data.data.records
        _this.currentPage = res.data.data.current
        _this.total = res.data.data.total
        _this.pageSize = res.data.data.size

      })
    }
  },
  created(){
    //网页打开的时候 调用方法
    this.page(1)
  }
}
</script>
  

<style>
.mPage{
  margin: 0 auto;
  text-align: center;
}
</style>