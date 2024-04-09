<template>
  <div class="row mt-4 latest-box">
    <div class="col-12 left-box">
      <div class="title">
        <div class="d-inline float-start head fs-4"><span >最新影片</span></div>
        <div class="d-inline float-end head fs-6">
          <span @click="search()" style="cursor: pointer" >更多</span>
          <i class="bi bi-chevron-double-right"></i>
        </div>
      </div>
      <div>
        <div id="latestMovie" class="carousel slide container-fluid  hide-second-row" data-bs-ride="carousel">
          <div class="carousel-inner">
            <!-- 第一行 -->
            <div class="carousel-item active">
              <div class="row">
                <div class="list-box col-3" v-for="(item, index) in frontList" :key="index" >
                  <a class="list-a" @click="toDetail(item.movieId)">
                    <div class="aspect-ratio">
                      <img :src="`/static/img/movie_picture/${item.picture}`" class="bigImg">
                      <img v-if="item.video_vip === 1" src="/static/img/icon/VIP-rightTop.png" class="vipImg">
                    </div>
                    <h3>{{ item.movieName }}</h3>
                    <i class="d-none d-md-inline-block">{{ item.rating }}</i>
                    <p class="d-none d-md-inline-block">{{ item.actor }}</p>
                  </a>
                </div>
              </div>
            </div>
            <!-- 第二行 -->
            <div class="carousel-item">
              <div class="row">
                <div class="list-box col-3" v-for="(item, index) in laterList" :key="index" >
                  <a class="list-a" @click="toDetail(item.movieId)" >
                    <div class="aspect-ratio aspect-ratio-4x3">
                      <img :src="`/static/img/movie_picture/${item.picture}`" class="bigImg">
                    </div>
                    <h3>{{ item.movieName }}</h3>
                    <i class="d-none d-md-inline-block">{{ item.rating }}</i>
                    <p class="d-none d-md-inline-block">{{ item.actor }}</p>
                  </a>
                </div>
              </div>
            </div>
          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#latestMovie" data-bs-slide="prev">
            <span class="carousel-control-prev-icon"></span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#latestMovie" data-bs-slide="next">
            <span class="carousel-control-next-icon"></span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router/router.js";
export default {
  name: "MerryGoRound",
  data(){
    return{
      detailUrl:"http://127.0.0.1:8090/index/detail?movieId=",
      latestList:[],
      frontList:[],
      laterList:[]
    }
  },
  mounted() {
    axios
        .get("http://127.0.0.1:8090/index/latest")
        .then(res=>{
          this.latestList = res.data
          this.frontList = this.latestList.splice(0,4)
          this.laterList = this.latestList
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

</style>