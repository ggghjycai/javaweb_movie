<template>
  <!--导航-->
  <div id="top"></div>
  <!--导航结束-->
  <div class="container  index-box">
    <!-- 轮播 -->
    <div id="carousel" class="carousel slide mt-3 mb-3" data-bs-ride="carousel">

      <!-- 指示符 -->
      <div class="carousel-indicators">
        <button v-for="(item, index) in carouselList " :key="item" type="button" data-bs-target="#carousel" :data-bs-slide-to="index" :class="{'active':index === 0}"></button>
      </div>

      <!-- 轮播图片 -->
      <div class="carousel-inner">
        <div v-for="(item, index) in carouselList" :key="index" :class="{'carousel-item':true, 'active':index === 0}" @click="toDetail(item.movieId)" style="cursor: pointer" >
          <img :src="`/static/img/carousel/${item.picture}`" class="d-block" style="width:100%">
        </div>
      </div>

      <!-- 左右切换按钮 -->
      <button class="carousel-control-prev" type="button" data-bs-target="#carousel" data-bs-slide="prev">
        <span class="carousel-control-prev-icon"></span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carousel" data-bs-slide="next">
        <span class="carousel-control-next-icon"></span>
      </button>
    </div>
  </div>
    <!-- 轮播结束 -->
</template>

<script>
import axios from "axios";
import router from "@/router/router.js";
export default {
  name: "Carousel",
  data(){
    return{
      carouselList:[],
    }
  },
  mounted() {
    axios
        .get("http://127.0.0.1:8090/index/lunbo")
        .then(res=>{
          this.carouselList = res.data
        })
  },
  methods:{
    toDetail(movieId){
      router.push({
        path: "/detail",
        query:{
          movieId:movieId
        }
      })
    },
  }
}
</script>



<style scoped>

</style>