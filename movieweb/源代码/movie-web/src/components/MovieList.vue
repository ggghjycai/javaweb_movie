<template>
  <div class="col-12 col-md-9 col-lg-8 left-box">
    <div class="title">
      <div class="d-inline float-start head fs-4"><span >{{ title }}</span></div>
      <div class="d-inline float-end head fs-6">
        <span @click="search()" style="cursor: pointer">更多</span>
        <i class="bi bi-chevron-double-right"></i>
      </div>
    </div>
    <div>
      <div class="container-fluid hide-second-row">
        <div class="row">
          <div class="list-box col-4 col-md-3" v-for="(item, index) in movieList" :key="index" >
            <a class="list-a" @click="toDetail(item.movieId)">
              <div class="aspect-ratio">
                <img :src="`/static/img/movie_picture/${item.picture}`" class="bigImg">
                <img v-if="item.videoVip === 1" src="/static/img/icon/VIP-rightTop.png" class="vipImg">
              </div>
              <h3>{{ item.movieName }}</h3>
              <i class="d-none d-md-inline-block">{{ item.rating }}</i>
              <p class="d-none d-md-inline-block">{{ item.actor }}</p>
            </a>
          </div>
          <div class="col-12 d-md-none d-flex justify-content-center align-items-center">
            <button class="custom-button" @click="search()">查看更多</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router/router.js";

export default {
  props: {
    title: {
      type: String,
      required: true
    },
    request:{
      type: String,
      required: true,
    }
  },
  data(){
    return{
      detailUrl:"http://127.0.0.1:8090/index/detail?movieId=",
      movieList:[],
    }
  },
  mounted() {
    axios
        .get("http://127.0.0.1:8090/index/" + this.request)
        .then(res=>{
            this.movieList = res.data
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
    search(){
      router.push({
        path: "/search",
        query:{
          tag:0,
          text:"",
        }
      })
    }
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
  color: inherit;
}
</style>