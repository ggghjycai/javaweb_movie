<template>
  <div class="col-3 col-lg-4 right-box d-none d-md-block">
    <div>
      <div class="title">
        <div class="float-start head fs-4">
          <span>{{ title }}</span>
        </div>
      </div>
      <div class="container-fluid">
        <div class="row mt-1 mb-1" v-for="(item, index) in rankList" :key="index">
          <div class="col-1 p-0 d-flex justify-content-center align-items-center">
            <span class="text-center rank-span">{{ index+1 }}</span>
          </div>
          <div class="col-9"><a @click="toDetail(item.movieId)" >{{ item.movieName }}</a></div>
          <div class="col-2 p-0 text-center d-flex align-items-center hot">{{ item.rating }}</div>
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
      required: true,
    },
    request:{
      type: String,
      required: true,
    }
  },
  data(){
    return{
      detailUrl:"http://127.0.0.1:8090/index/detail?movieId=",
      rankList:[],
    }
  },
  mounted() {
    axios
        .get("http://127.0.0.1:8090/index/" + this.request)
        .then(res=>{
          this.rankList = res.data
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
a {
  text-decoration: none;
  color: inherit;
}
</style>