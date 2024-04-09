<template>
  <link type="text/css" rel="stylesheet" href="/static/css/top.css">
  <!-- 导航 -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <a class="navbar-brand nav_span" href="#">囧囧电影</a>
      <div class="button-md-sm d-flex align-items-center">
        <div class="dropdown mx-2" id="userBoxMdSm">
          <a class="nav-link" href="#" role="button" id="toLogin" data-bs-toggle="dropdown" aria-expanded="false" >
            <img class="rounded-circle w-30 h-30" src="/static/img/icon/member.png" width="30">
          </a>
          <ul v-if="isLogin" class="dropdown-menu" aria-labelledby="userDropdown">
            <li><a v-if="isLogin" class="dropdown-item" id="userCenter2" @click.prevent="toUser()">用户中心</a></li>
            <li><a v-if="isLogin" class="dropdown-item" id="logOut2" @click="logout">退出</a></li>
            <li><a v-if="!isLogin" class="dropdown-item" id="logOut2" @click="toLogin">登录</a></li>
          </ul>
        </div>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar">
          <span class="navbar-toggler-icon"></span>
        </button>
      </div>
      <div class="collapse navbar-collapse" id="navbar">
        <!--跳转选项-->
        <div>
          <ul class="navbar-nav mb-2 mb-lg-0 nav-a-box">
            <li class="nav-item"><a class="nav-link nav-link-a" href="#" @click.prevent="toIndex()" >首页</a></li>
            <li class="nav-item"><a class="nav-link nav-link-a" href="/rank" @click.prevent="toRank()" >排行榜</a></li>
          </ul>
        </div>
        <!--跳转选项结束-->
        <!-- 搜索栏 -->
        <div class="input-group mt-2 mb-2 search-box" id = "searchBox">
          <div class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" id="searchSelect" ref="searchSelect" >电影</div>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item option" @click="changeTag" >电影</a></li>
            <li><a class="dropdown-item option" @click="changeTag" >主演</a></li>
          </ul>
          <input type="text" class="form-control" placeholder="搜索" id="searchInput" ref="selectInput" v-model="text" @input="changeText" @keydown.enter="search" >
          <button class="btn btn-primary" type="submit" @click="search()">Go</button>
        </div>
        <!-- 搜索栏结束 -->
        <!--用户登录-->
        <div class="navbar-text ms-auto">
          <div class="dropdown" id="userBox">
            <a class="nav-link dropdown-toggle" href="#" role="button" id="toLogin2" data-bs-toggle="dropdown" aria-expanded="false" >
              <img class="rounded-circle mr-2 w-30 h-30" src="/static/img/icon/member.png" width="30">
              <span class="align-middle">Author</span>
            </a>
            <ul class="dropdown-menu" aria-labelledby="userDropdown">
              <li><a v-if="isLogin" class="dropdown-item" id="userCenter2" @click.prevent="toUser()">用户中心</a></li>
              <li><a v-if="isLogin" class="dropdown-item" id="logOut2" @click="logout">退出</a></li>
              <li><a v-if="!isLogin" class="dropdown-item" id="logOut2" @click="toLogin">登录</a></li>
            </ul>
          </div>
        </div>
        <!--用户登录结束-->
      </div>
    </div>
  </nav>
  <!-- 导航结束 -->
</template>

<script>
import axios from 'axios'
import router from "@/router/router.js";


export default {
  name: "MyNav",
  props:{
      oldText:{
        type:String,
        request:false
      },
      oldTag:{
        request:false
      }
  },
  data(){
    return{
      tag:0,
      text:"",
      isLogin:false,
    }
  },
  created() {
    axios
        .get("http://127.0.0.1:8090/index/isLogin")
        .then(res=>{
          if (res.data.result === "true"){
            this.isLogin = true
          }
          else {
            this.isLogin = false
          }
        })
  },
  mounted() {
    if (this.oldText !== null && this.oldText !== ""){
      this.text = this.oldText
      this.$refs.selectInput.innerText = this.text
    }
    if (this.oldTag !== null && this.oldTag !== ""){
      this.tag = this.oldTag
      if (this.tag === '0'){
        this.$refs.searchSelect.innerText = "电影";
      }
      else {
        this.$refs.searchSelect.innerText = "主演"
      }
    }
  },
  methods: {
    logout(){
      axios
          .get("http://127.0.0.1:8090/user/logout")
          .then(res=>{
            if(res.data === true){
              router.go(0)
            }
            else {
              alert("退出失败");
            }
          })
    },
    toLogin(){
      router.push("/login");
    },
    toIndex(){
        router.push("/");
    },
    toRank(){
      router.push("/rank");
    },
    toUser(){
      router.push("/user")
    },
    changeText(){
      this.$emit("changeText", this.text)
    },
    changeTag(event){
      this.$refs.searchSelect.innerText = event.target.innerText;
      if (this.$refs.searchSelect.innerText === "电影"){
        this.tag = 0
      }
      else {
        this.tag = 1
      }
      this.$emit("changeTag", this.tag)
    },
    search(){
      router.push({
        path: "/search",
        query:{
          tag:this.tag,
          text:this.text
        }
      })
    }
  },
}
</script>


<style>

</style>