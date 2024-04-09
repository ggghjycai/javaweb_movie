<template>
<link type="text/css" rel="stylesheet" href="/static/css/manage.css">
<div class="box">
<MyNav></MyNav>
<div class="container mt-3">
  <div class="row">
    <div class="left_nav col-lg-2 p-3">
      <!-- 导航内容 -->
      <ul>
        <li>
          <a href="/user" class="nav-link" @click.prevent="toUser()">返回用户中心</a>
        </li>
      </ul>
    </div>
    <div class="main col-lg-10 p-3" >
      <div class="d-flex justify-content-end mb-1">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addMovie">
          添加电影
        </button>
      </div>
      <!-- 主要内容区域 -->
    <div class="right-box-content">
    <div class="table-responsive">
      <table class="table table-bordered table-hover text-center">
        <thead class="title table-light">
        <tr>
          <th scope="col" class="col-1">序号</th>
          <th scope="col" class="col-8 col-md-4">电影名</th>
          <th scope="col" class="col-4">主演</th>
          <th scope="col" class="col-1">评分</th>
          <th scope="col" class="col-1">修改</th>
          <th scope="col" class="col-1">删除</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item, index) in movieList" :key="index">
          <td class="li01 ta_c"><b class="ptnob">{{ (pageNo-1) * pageSize + index+1 }}</b></td>
          <td class="li02 ta_c oh">
            <a @click="toDetail(item.movieId)" style="cursor: pointer" >{{ item.movieName }}</a>
          </td>
          <td class="li03 ta_c oh">
                    <span>
                      <a @click="search(1, each)" v-for="(each, i) in item.actorList" :key="i" style="cursor: pointer" >{{ each + ' ' }}</a>
                    </span>
          </td>
          <td class="ta_c">
            <span>{{ item.rating }}</span>
          </td>
          <td class="li04 ta_c">
            <span @click="showUpdateMovie(item.movieId)" >修改</span>
          </td>
          <td class="li04 ta_c">
            <span @click="deleteMovie(item.movieId)">删除</span>
          </td>
        </tr>
        </tbody>
      </table>
      <Page @getPageNo="getPageNo" :page-no="pageNo" :page-total="pageTotal" :visible-pages="visiblePages"></Page>
    </div>
  </div>

      <!-- 添加电影-->
      <div class="modal fade" id="addMovie" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" ref="addModalLabel">添加电影</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body container-fluid">
              <form class="row">
                <div class="mb-3">
                  <label for="movieName" class="form-label">电影名</label>
                  <input type="text" class="form-control" v-model="addData.movieName" ref="addMovieName">
                </div>
                <div class="mb-3">
                  <label for="releaseDate" class="form-label">上映日期</label>
                  <input type="date" class="form-control" v-model="addData.date" ref="addDate">
                </div>
                <div class="mb-3">
                  <label for="genre" class="form-label">基因</label>
                  <input type="text" class="form-control" v-model="addData.genre" ref="addGenre">
                </div>
                <div class="mb-3">
                  <label for="region" class="form-label">地区</label>
                  <input type="text" class="form-control" v-model="addData.area" ref="addArea">
                </div>
                <div class="mb-3">
                  <label for="image" class="form-label">类型</label>
                  <input type="text" class="form-control" v-model="addData.type">
                </div>
                <div class="mb-3">
                  <label for="image" class="form-label">演员</label>
                  <input type="text" class="form-control" v-model="addData.actor">
                </div>
                <div class="mb-3">
                  <label for="image" class="form-label">图片</label>
                  <input type="text" class="form-control" v-model="addData.picture" >
                </div>
                <div class="mb-3">
                  <label for="video" class="form-label">视频</label>
                  <input type="text" class="form-control" v-model="addData.videoUrl" ref="addVideoUrl">
                </div>
                <div class="mb-3">
                  <label for="vipPermission" class="form-label">VIP权限</label>
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="vipPermission" id="vipYes" value="0" v-model="addData.videoVip">
                    <label class="form-check-label" for="vipYes">
                      是
                    </label>
                  </div>
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="vipPermission" id="vipNo" value="0" checked v-model="addData.videoVip">
                    <label class="form-check-label" for="vipNo">
                      否
                    </label>
                  </div>
                </div>
                <div class="mb-3">
                  <label for="introduction" class="form-label">介绍</label>
                  <textarea class="form-control" ref="addIntroduction" v-model="addData.introduction" rows="3"></textarea>
                </div>
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
              <button type="submit" class="btn btn-primary"data-bs-dismiss="modal" @click="addMovie" >添加</button>
            </div>
          </div>
        </div>
      </div>

      <!-- 修改信息 -->
      <div class="modal fade" id="updateMovie" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="updateModalLabel">修改信息</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body container-fluid">
              <form class="row" >
                <div class="mb-3">
                  <label for="movieName" class="form-label">电影名</label>
                  <input type="text" class="form-control" v-model="update.movieName" >
                </div>
                <div class="mb-3">
                  <label for="releaseDate" class="form-label">上映日期</label>
                  <input type="date" class="form-control" v-model="update.date" >
                </div>
                <div class="mb-3">
                  <label for="genre" class="form-label">基因</label>
                  <input type="text" class="form-control" v-model="update.genre">
                </div>
                <div class="mb-3">
                  <label for="region" class="form-label">地区</label>
                  <input type="text" class="form-control" v-model="update.area">
                </div>
                <div class="mb-3">
                  <label for="image" class="form-label">类型</label>
                  <input type="text" class="form-control" v-model="update.type" >
                </div>
                <div class="mb-3">
                  <label for="image" class="form-label">演员</label>
                  <input type="text" class="form-control" v-model="update.actor" >
                </div>
                <div class="mb-3">
                  <label for="image" class="form-label">图片</label>
                  <input type="text" class="form-control" v-model="update.picture" >
                </div>
                <div class="mb-3">
                  <label for="video" class="form-label">视频</label>
                  <input type="text" class="form-control" v-model="update.videoUrl">
                </div>
                <div class="mb-3">
                  <label for="video" class="form-label">评分</label>
                  <input type="number" class="form-control" v-model="update.rating">
                </div>
                <div class="mb-3">
                  <label for="video" class="form-label">周播放量</label>
                  <input type="number" class="form-control" v-model="update.weekHits">
                </div>
                <div class="mb-3">
                  <label for="video" class="form-label">月播放量</label>
                  <input type="number" class="form-control" v-model="update.monthHits">
                </div>
                <div class="mb-3">
                  <label for="video" class="form-label">总播放量</label>
                  <input type="number" class="form-control" v-model="update.allHits">
                </div>
                <div class="mb-3">
                  <label for="vipPermission" class="form-label">VIP权限</label>
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="vipPermission" id="vipYes" value="1" v-model="update.videoVip" >
                    <label class="form-check-label" for="vipYes">
                      是
                    </label>
                  </div>
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="vipPermission" id="vipNo" value="0" checked v-model="update.videoVip">
                    <label class="form-check-label" for="vipNo">
                      否
                    </label>
                  </div>
                </div>
                <div class="mb-3">
                  <label for="introduction" class="form-label">介绍</label>
                  <textarea class="form-control" id="introduction" rows="3" v-model="update.introduction"></textarea>
                </div>
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
              <button type="submit" class="btn btn-primary" data-bs-dismiss="modal" @click="updateMovie(update.movieId)" >修改</button>
            </div>
          </div>
        </div>
      </div>
        </div>
    <!-- 信息弹窗 -->
    <div class="modal fade" id="messageModel">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <!-- 模态框头部 -->
          <div class="modal-header d-flex justify-content-center">
            <h6 class="modal-title ">{{ message.title }}</h6>
          </div>
          <!-- 模态框内容 -->
          <div class="modal-body d-flex justify-content-center">
            {{ message.content }}
          </div>
          <!-- 模态框底部 -->
          <div class="modal-footer">
            <button type="button" class="btn btn-danger" data-bs-dismiss="modal" @click="reload()" >关闭</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</div>
</div>
</template>

<script>
import router from "@/router/router.js";
import MyNav from "@/components/MyNav.vue";
import axios from "axios";
import Page from "@/components/Page.vue";
export default {
  components: {
    Page,
    MyNav
  },
  data(){
    return {
      movieList:[],
      pageTotal:0,
      pageNo:1,
      pageSize:24,
      update:{
        movieId:0,
        movieName:"",
        date:"",
        genre:"",
        area:"",
        type:"",
        actor:"",
        rating:0,
        weekHits:0,
        monthHits:0,
        allHits:0,
        picture:"",
        videoUrl:"",
        introduction:"",
        videoVip:0
      },
      addData:{
        movieName:"",
        date:"",
        genre:"",
        area:"",
        type:"",
        actor:"",
        picture:"",
        videoUrl:"",
        introduction:"",
        videoVip:0
      },
      message: {
        title: "",
        content: ""
      },
    };
  },
  mounted() {
    if (this.$route.query.pageNo !== undefined){
      this.pageNo = this.$route.query.pageNo
    }
    if (this.$route.query.pageSize !== undefined){
      this.pageSize = this.$route.query.pageSize;
    }
    axios
        .get("http://127.0.0.1:8090/index/search", {
          params:{
            tag:0,
            text:"",
            pageNo:this.pageNo,
            pageSize:this.pageSize,
            type:"全部",
            area:"全部",
            p:"最新",
            g:"全部"
          }
    })
        .then(res=>{
          this.movieList = res.data.data
          this.pageTotal = res.data.pageTotal
        })

  },
  methods:{
    getPageNo(res){
      this.getData(res, 24);
    },
    reload(){
      router.go(0);
    },
    toUser(){
      router.push("/user");
    },
    getData(pageNo = 1, pageSize = 24){
      router.push({
        path: "/manage",
        query:{
          pageNo:pageNo,
          pageSize:pageSize,
        }
      })
    },
    showUpdate(){
      const modal = new bootstrap.Modal(document.getElementById('updateMovie'));
      modal.show();
    },
    showMessage(title, content){
      this.message.title = title;
      this.message.content = content;
      const modal = new bootstrap.Modal(document.getElementById('messageModel'));
      modal.show();
    },
    showUpdateMovie(movieId){
        axios
            .get("http://127.0.0.1:8090/manage/update/request", {
              params:{
                movie_id:movieId
              }
            })
            .then(res=>{
              const d = new Date(res.data.date);
              let dy = d.getFullYear();
              let dh = d.getMonth()+1;
              let dd = d.getDay()+1;
              if (dh < 10){
                dh = "0" + dh;
              }
              if (dd < 10){
                dd = "0" + dd;
              }
              this.update.type = "";
              this.update.actor = "";
              this.update.movieId = movieId;
              this.update.movieName = res.data.movieName;
              this.update.genre = res.data.genre;
              this.update.area = res.data.productionPlace;
              this.update.date = dy + "-" + dh + "-" + dd;
              const typeList = res.data.typeList;
              for(let i = 0; i < typeList.length; i++){
                this.update.type += typeList[i] + " ";
              }
              this.update.type = this.update.type.substring(0, this.update.type.length-1)
              const actorList = res.data.actorList;
              for(let i = 0; i < actorList.length; i++){
                this.update.actor += actorList[i] + " ";
              }
              this.update.actor = this.update.actor.substring(0, this.update.actor.length-1)
              this.update.rating = res.data.rating;
              this.update.weekHits = res.data.weekHits;
              this.update.monthHits = res.data.monthHits;
              this.update.allHits = res.data.allHits;
              this.update.picture = res.data.picture;
              this.update.videoUrl = res.data.videoUrl;
              this.update.videoVip = res.data.videoVip;
              this.update.introduction = res.data.introduction;
              this.showUpdate()
            })
    },
    addMovie(){
      const typeList = this.addData.type.split(" ");
      const actorList = this.addData.actor.split(" ");
      const d = new Date(this.addData.date)
      let formData = new FormData();
      formData.append("movieName", this.addData.movieName);
      formData.append("genre", this.addData.genre);
      formData.append("productionPlace", this.addData.area);
      formData.append("date",d);
      formData.append("picture", this.addData.picture);
      formData.append("introduction", this.addData.introduction);
      formData.append("videoUrl", this.addData.videoUrl);
      formData.append("videoVip", this.addData.videoVip);
      formData.append("actorList", actorList);
      formData.append("typeList", typeList);
      axios
          .post("http://127.0.0.1:8090/manage/add",formData)
          .then(res=>{
            if (res.data.result === "添加成功"){
              this.showMessage("添加信息", "添加成功");
              router.replace("/manage");
            }
            else {
              this.showMessage("错误信息", "添加失败");
              this.addData.movieName = "";
              this.addData.genre = "";
              this.addData.area = "";
              this.addData.type = "";
              this.addData.actor = "";
              this.addData.date = "";
              this.addData.rating = 0;
              this.addData.weekHits = 0;
              this.addData.monthHits = 0;
              this.addData.allHits = 0;
              this.addData.picture = "";
              this.addData.videoUrl = "";
              this.addData.videoVip = 0;
              this.addData.introduction = "";
            }
          })
    },
    updateMovie(movieId){
      const typeList = this.update.type.split(" ");
      const actorList = this.update.actor.split(" ");
      const d = new Date(this.update.date)
      let formData = new FormData();
      formData.append("movieId", movieId);
      formData.append("movieName", this.update.movieName);
      formData.append("genre", this.update.genre);
      formData.append("productionPlace", this.update.area);
      formData.append("date",d);
      formData.append("rating", this.update.rating);
      formData.append("weekHits", this.update.monthHits);
      formData.append("monthHits", this.update.monthHits);
      formData.append("allHits", this.update.allHits);
      formData.append("picture", this.update.picture);
      formData.append("introduction", this.update.introduction);
      formData.append("videoUrl", this.update.videoUrl);
      formData.append("videoVip", this.update.videoVip);
      formData.append("actorList", actorList);
      formData.append("typeList", typeList);
      axios
          .post("http://127.0.0.1:8090/manage/update",formData)
          .then(res=>{
            if (res.data.result === "修改成功"){
              this.showMessage("修改信息", "修改成功");
            }
            else {
              this.showMessage("错误信息", "修改失败！");
            }
          })
    },
    deleteMovie(movieId){
      axios
          .get("http://localhost:8090/manage/delete", {
            params:{
              movieId:movieId
            }
          })
          .then(res=>{
            console.log(res.data.result)
            if (res.data.result === "删除成功"){
              this.showMessage("删除信息", "删除成功");
            }
            else {
              this.showMessage("错误信息", "删除失败");
            }
          })
    },
    toDetail(movieId){
      router.push({
        path: "/detail",
        query:{
          movieId:movieId
        }
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
  },
  computed:{
    visiblePages: function() {
      const pages = [];
      // 如果总页数小于等于6，则直接显示所有页数
      if (this.pageTotal <= 6) {
        for (let i = 1; i <= this.pageTotal; i++) {
          pages.push(i);
        }
      } else {
        // 否则，显示前三个和后三个页数，中间显示省略号
        for (let i = this.pageNo; i <= 3; i++) {
          pages.push(i);
        }
        pages.push('...');
        for (let i = this.pageTotal - 2; i <= this.pageTotal; i++) {
          pages.push(i);
        }
      }
      return pages;
    }
  }

};



</script>
<style scoped>


</style>