
<template>
    <link type = "text/css" rel = "stylesheet" href = "/static/css/login.css" >

  <div class="container-fluid mt-3">
    <div class = "loading">

      <div class="row">
        <div class="col-12">
          <div class = "nav d-flex justify-content-center">
            <div class = "logoname  text-center title"  >囧囧电影</div>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-12 col-md-12 offset-md-0 col-lg-12 offset-lg-0">
          <div class = "center">
            <form @submit.prevent="Login">
              <table class="table">
                <tr>
                  <td class = "td1" id = "what">E-mail：</td>
                  <td class = "td2">
                    <input type = "text" id = "email" name="email" placeholder = "请输入E-mail" value = "a@163.com"
                           ref="email" autocomplete = "off"/>
                  </td>
                </tr>
                <tr>
                  <td class = "td1">密码：</td>
                  <td class = "td2">
                    <input type = "password" id = "password" name = "password" placeholder="请输入密码"
                           ref="password" autocomplete="off" />
                  </td>
                </tr>
              </table>

              <div class="mb-3">
                <div>
                  <select name="user" id = "user" v-model="userType">
                    <Option value = "0" >普通登录</Option>
                    <Option value = "1" >管理员登录</Option>
                  </select>
                </div>
              </div>

              <div class = "mb-3">
                  <input type = "submit" id = "login" value = "登录"  />
              </div>

            </form>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-12 col-md-12 offset-md-0 col-lg-12 offset-lg-0">
          <div class = "button">
            <div class = "b_right ">
              <a href = "/register" class="fs-4 text-center" @click.prevent="toRegister()" >免费注册</a>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
<!--  <script src="/path/to/your/bundle.js"></script>-->


</template>

<script>
import axios from "axios";
import router from "@/router/router.js";

export default {
  data(){
    return {
      userType: "0",
    };
  },

  methods: {
    toRegister(){
      router.push("/register")
    },


    Login() {
      const email = this.$refs.email.value;
      const password = this.$refs.password.value;
      const userType = this.userType;
      console.log(email,password,userType)

      axios.get("http://127.0.0.1:8090/index/doLogin",{
          // axios.get("/doLogin.json", {
            params: {
              email,
              password,
              userType,
          }
         })
          .then(response => {
            const result = response.data;
            if (result.result === "登录成功") {
              alert(result.result);
              router.push("/");
            } else {
              alert("登录失败！，请重新登录");
            }
          })
          .catch(error => {
            console.error("Error in login request", error);
          });
    }
  }
}

</script>

<style scoped>
form{
  margin-top: 10px;
}
</style>