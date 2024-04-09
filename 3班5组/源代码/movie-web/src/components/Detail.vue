<template>
  <MyNav></MyNav>
  <link type = "text/css" rel = "stylesheet" href = "/static/css/video-content.css" >
  <link type = "text/css" rel = "stylesheet" href = "/static/css/video-info.css" >
  <link type = "text/css" rel = "stylesheet" href = "/static/css/popup.css" >
  <div class="content-box">
    <!-- 模态框 -->
    <div ref="myModel" class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">权限不够，请开通会员</h5>
            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">关闭</button>
          </div>
          <div class="modal-body d-flex justify-content-center">
            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="openMembership()">开通会员</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 视频-->
    <div class="playerBox">
      <div class="ratio ratio-16x9 playerBox-player">
        <div class="player-wrapper">
          <div id = "vodPlayer" class="play-media">
            <div class="player-body run">
              <div id = "live_SWF" class="player-swf">
                <div class="mplayer">
                  <div class="playerContainer" v-if="movie.limitation">
<!--                    <video id = "videoPlay" ref="videoPlay" class="currentVideo" preload="auto" :src="'/static/video/' + movie.videoUrl"></video>-->
                    <VideoPlayer ref="videoPlayer" v-if="movie !== ''" :video-src="'/static/video/'+movie.videoUrl"></VideoPlayer>

                  </div>
                  <div class="playerContainer" v-if="!movie.limitation">
                    <img src="/static/img/icon/limitation.png" class="currentVideo currentImg">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--视频结束-->
    <!--infoBox-->
    <div class="playerBox-info">
      <div class="playerBox-info-content">
        <!-- 标题+年份+评分 -->
        <div class="playerBox-info-title">
          <h1 class="playerBox-info-name playerBox-info-cnName" >{{ movie.movieName }}</h1>
          <span class="playerBox-info-year" >{{ movie.date }}</span>
          <span class="playerBox-info-grade" id="InfoScore" >{{ movie.rating }}</span>
        </div>
        <!-- 简介 -->
        <div class="playerBox-info-intro" id = "playerBoxInfoIntro">
          <p>剧情简介：</p>
          <span id="playerBoxIntroCon" class="playerBox-intro-con" >
                        <b>{{ movie.introduction }}</b>
                    </span>
          <!--图片、导演、类型等资料-->
          <div class="playerBox-intro-ctx" id = "playerBoxIntroCtx" style="display: block">
            <div class="playerBox-info-vodAvatar">
              <a href="#">
                <img :src="`/static/img/movie_picture/${movie.picture}`">
              </a>
            </div>
            <div class="playerBox-info-vodDetails">
              <ul class="playerBox-info-vodInfoList">
                <li class="playerBox-info-vodInfoItem">
                  <b>主演：&nbsp;</b>
                  <span class="playerBox-info-vodMp">
                    <a class="actor-option" v-for="(item, index) in movie.actorList" :key="index" @click="search(1, item)" >{{ item + ' ' }}</a>
                  </span>
                </li>
                <li class="playerBox-info-vodInfoItem">
                  <b>类型：&nbsp;</b>
                  <a class="type-option" v-for="(item, index) in movie.typeList" :key="index" @click="search(0, '', item)" >{{ item + ' ' }}</a>
                </li>
                <li class="playerBox-info-vodInfoItem">
                  <b>地区：&nbsp;</b>
                  <a class="area-option" @click="search(0, '', '全部', movie.productionPlace)" >{{ movie.productionPlace }}</a>
                </li>
                <li class="playerBox-info-vodInfoItem">
                  <b>基因：&nbsp;</b>
                  <a class="genre-option" @click="search(0, '', '全部', '全部' , movie.genre)" >{{ movie.genre }}</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--infoBox结束-->


</template>

<script>
import axios from "axios";
import MyNav from "@/components/MyNav.vue";
import router from "@/router/router.js";
import VideoPlayer from "@/components/Video.vue";
export default {
  name: "Rank",
  components: { VideoPlayer, MyNav},
  data() {
    return {
      movie: "",
      movieId:0,
    }
  },
  created() {
    {
      if (this.$route.query.movieId !== undefined) {
        this.movieId = this.$route.query.movieId;
      }
    }
    axios
        .get("http://127.0.0.1:8090/index/detail", {
          params: {
            movieId: this.movieId,
          }
        })
        .then(res => {
          if (!res.data.limitation) {
            res.data.videoUrl = "";
          }
          this.movie = res.data
          if (!this.movie.limitation){
            const myModal = new bootstrap.Modal(document.getElementById('myModal'));
            myModal.show();
          }
        })
  },
  methods: {
    openMembership(){
      axios.get("http://127.0.0.1:8090/index/isLogin")
          .then(res=>{
            if (res.data.result === "true"){
              router.push("/user");
            }
            else router.push("/login");
          })
    },
      search(
          tag = 0,
          text = "",
          type = "全部",
          area = "全部",
          g = "全部",
          p = "最热"
      ){
        const pageNo = 1;
        const pageSize = 24;
        router.push({
          path: "/search",
          query:{
            tag:tag,
            text:text,
            pageNo:pageNo,
            pageSize:pageSize,
            type:type,
            area:area,
            g:g,
            p:p
          }
        })
    },

}
}
</script>

<style scoped>

</style>