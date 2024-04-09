<template>
  <link type="text/css" rel="stylesheet" href="/static/css/user.css">
  <div class="box">
    <MyNav></MyNav>
    <div class="container mt-3" >
      <div class="row">
        <div class="left_nav col col-lg-2 p-3">
          <ul>
            <li class="ul-li" id="hot-rankings">
              <a href="/user/information" class="nav-link" @click.prevent="inforshow()">个人中心</a>
            </li>
            <li class="ul-li" >
              <a class="nav-linkl" @click="logout()">退出登录</a>
            </li>
            <li v-if="userType === 1" ref="userType">
              <a class="nav-linkl"  @click.prevent="toManage()">管理员模式</a>
            </li>
          </ul>
        </div>
        <div class="main col col-lg-10 p-3" >
          <!-- 顶部信息 -->
          <div class="llqtop">
            <div class="lll">用户中心</div>
          </div>
          <!-- 主要内容区域 -->
          <div class="llq01">
            <div class="llq02 d-inline-block mb-3">
              <a><img src="/static/img/icon/member.png"></a>
            </div>
            <div class="d-inline-block">
              <div class="llq03 d-block">
                <div>
                  <table class="signInTalbe">
                    <!-- 用户信息表格 -->
                    <tr>
                      <td class = "td1" id = "what">E-mail：</td>
                      <td class = "td2">
                        <span ref="userEmail"> {{userEmail}}</span>
                      </td>
                    </tr>
                    <tr v-if="pm === 1">
                      <td class = "td1">已成为会员：</td>
                      <td class = "td2">
                        <img src="/static/img/icon/VIP.png" class="vip-icon">
                      </td>
                    </tr>
                  </table>
                </div>

                <div id="membershipStatus" class="lll05"
                     ref="membershipStatus" v-show="pm === 0 && userType === 0">
                  <button id="becomeMemberBtn" class="lll06"
                          ref="becomeMemberBtn" @click="becomeMember">成为会员</button>
                </div>
              </div>
            </div>
          </div>

          <!-- 会员状态提示 -->
          <div ref="membershipModal" id="membershipModal" class="modal" style="z-index: 9999">
            <div class="modal-content">
              <span class="close" @click="closeMembershipPopup">&times;</span>
              <p>已成为会员！</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<!-- 正文 -->

<script>
import router from "@/router/router.js";
  import MyNav from "@/components/MyNav.vue";
  import axios from "axios";

  export default {
    components:{
      MyNav
    },
    data() {
      return {
        userEmail: '',
        pm:0,
        userType: 0,
        membershipModal:null,
        pmStatusRow:'',
      };
    },
    created() {
      axios.get("http://127.0.0.1:8090/user/information")
          .then(response => {
            const responseData = response.data;
            this.userEmailSpan.innerText = responseData.userEmail;
            if (responseData.pm === 1){
              this.pm = 1;
              // 已成为会员的逻辑
            }
            else{
              // 未成为会员的逻辑
              this.pm = 0;
            }
            if (responseData.userType === 1){
              this.userType = 1;
            }

            else {
              this.userType = 0;
            }
          })
          .catch(error => {
            console.error("Error:", error);
          });
    },
    mounted() {
      // 获取用户中心页面的相关元素
      this.userEmailSpan = this.$refs.userEmail;
      this.pmStatusRow = this.$refs.membershipStatus;
      this.membershipModal = this.$refs.membershipModal;
      this.userType = this.$refs.userType;
    },
    methods: {

      toManage(){
        router.push("/manage");
      },
      logout(){
        axios
            .get("http://127.0.0.1:8090/user/logout")
            .then(res=>{
              if(res.data === true){
                router.push("/")
              }
              else {
                alert("退出失败");
              }
            })
      },
      inforshow(){
        // 使用 axios 发送 GET 请求到后端
        // axios.get("http://127.0.0.1:8090/user/information")
        axios.get("http://127.0.0.1:8090/user/information")
            .then(response => {
              const responseData = response.data;
              // 成功回调函数，responseData 是后端返回的 JSON 对象
              console.log(responseData.userEmail);
              // 根据返回的数据更新页面元素

              this.userEmailSpan.innerText = responseData.userEmail;
              if (responseData.pm === 1){
                this.pm = 1;
                // 已成为会员的逻辑
                // this.pmStatusRow.innerHTML = '<td class="td1">已成为会员：</td><td class="td2"><img src="/img/icon/vip.png" class="vip-icon"></td>';
              }
              else{
                // 未成为会员的逻辑
                this.pm = 0;
                // this.pmStatusRow.innerHTML = '<button id="becomeMemberBtn" class="lll06" @click="becomeMember">成为会员</button>';
              }
              if (responseData.userType === 1){
                this.userType = 1;
                // this.userType.innerHTML = ' <a class="nav-linkl" href = "/manage"  @click.prevent="toManage()">管理员模式</a>';
              }

              else {
                  this.userType = 0;
              }
            })
            .catch(error => {
              // 失败回调函数，可以处理请求失败的情况
              console.error("Error:", error);
            });

        // 在页面加载时添加关闭会员状态提示框的点击事件监听器
        this.membershipModal.querySelector('.close').addEventListener('click', this.closeMembershipPopup);
      },

      becomeMember() {
          // 发送GET请求到后端，这里使用axios库
          // const response = axios.get("http://127.0.0.1:8090/user/become");
          axios
              .get("http://127.0.0.1:8090/user/become")
              .then(res=>{
                console.log(res.data)
                if(res.data === 1){
                  this.pm = 1;
                  // 隐藏 "成为会员" 按钮
                  this.$refs.becomeMemberBtn.style.display = 'none';
                  // 显示会员模态框
                  this.$refs.membershipModal.style.display = 'block';
                }
              })
      },
      closeMembershipPopup() {
        // 关闭会员状态提示框
        this.$refs.membershipModal.style.display = 'none';
      },
    },
  };

</script>

<style scoped>

</style>