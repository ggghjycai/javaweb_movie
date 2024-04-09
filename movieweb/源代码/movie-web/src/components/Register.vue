

<template>
  <link type="text/css" rel="stylesheet" href="/static/css/register.css">

  <div class="container-fluid mt-3">
    <div class="reg loading">
      <div class="row">
        <div class="col-12">
          <div class="nav d-flex justify-content-center">
            <div class="logoname text-center">囧囧电影</div>
          </div>
        </div>
      </div>

      <div class="row" ref="form_msg">
        <div class="col-12 col-md-12 offset-md-0 col-lg-12 offset-lg-0">
          <div class="center">
            <form @submit.prevent="register">
              <div class="mb-3" style="display: flex;justify-content: center">
                <tr>
                  <td class="td1 col-sm-2">E-mail：</td>
                  <td class= "td2 col-sm-9">
                    <input type="text" id="email" name="email" value="a@163.com"
                           placeholder="请输入E-mail" autocomplete="off"
                    ref="email" @blur="checkEmaill"/>
                  </td>
                  <td class="td3 col-sm-1"><span id="email_msg" ref="email_msg"></span></td>
                </tr>
              </div>
              <div class="mb-3" style="display: flex;justify-content: center">
                <tr>
                  <td class="td1 col-sm-2">密码：</td>
                  <td class="td2 col-sm-9">
                    <input type="password" id="password"
                           name="password" placeholder="请输入密码" autocomplete="off"
                    ref="password" @blur="checkPasswordl"/>
                  </td>
                  <td class="td3 col-sm-1"><span id="password_msg" ref="password_msg"></span></td>
                </tr>
              </div>
              <div class="mb-3" style="display: flex;justify-content: center">
                <tr>
                  <td class="td1 col-sm-2">确认密码：</td>
                  <td class="td2 col-sm-9">
                    <input type="password" id="repassword"
                           name="repassword" placeholder="请再次输入密码" autocomplete="off"
                    ref="repassword" @blur="checkRepasswordl"/>
                  </td>
                  <td class="td3 col-sm-1"><span id="repassword_msg" ref="repassword_msg"></span></td>
                </tr>
              </div>

              <div class="button text-center mb-3">
                <input type="submit" id="reg" value="注册"  />
              </div>
            </form>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-12 text-center">
          <div class="bottom ">
            <a href="/Login" class="fs-4" @click.prevent="toLogin()">返回登录</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import {ref} from 'vue';
import axios from 'axios';

import router from "@/router/router.js";
export default {

  methods: {

    toLogin(){
      router.push("/login")
    },

    checkEmaill(){
      const email = this.$refs.email.value;
      console.log("Inside checkEmail method");

      if (this.checkNull("email", "Email不能为空！") && this.checkEmail()) {
            axios.post("http://127.0.0.1:8090/index/checkemail", { email })
            // axios.get("/checkemail.json",{
            // })
            .then(response => {
              const result = response.data;
              if (result.result ==="该邮件可用！")
              this.$refs.email_msg.textContent = result.result;
              this.$refs.email_msg.style.color = "red";
            })
            .catch(error => {
              console.error("Error in email check request", error);
            });
      }
    },

    checkPasswordl(){
      this.checkPassword();
      this.checkLength("password");
    },

    checkRepasswordl(){
      this.checkPassword();
      this.checkLength("repassword");
    },

    register() {
      const email = this.$refs.email.value;
      const password = this.$refs.password.value;
      let flag = true;
      flag = this.checkNull("email", "Email不能为空！") && flag;
      flag = this.checkNull("password", "密码不能为空！") && flag;
      flag = this.checkNull("repassword", "确认密码不能为空！") && flag;
      flag = this.checkEmail() && flag;
      flag = this.checkPassword() && flag;
      if (flag) {
            axios
                .get("http://127.0.0.1:8090/index/doRegister", {
                params: {
                  email,
                  password
                  }
            })
            .then(res => {
              if (res.data === "注册成功") {
                alert(res.data);
                router.push("/login");
              }
            })
            .catch(error => {
              console.error("Error in registration request", error);
            });
      }
    },
    setMsg(name, msg) {
      this.$refs[`${name}_msg`].textContent = msg;
      this.$refs[`${name}_msg`].style.color = "red";
    },
    checkNull(name, msg) {
      const value = this.$refs[name].value;
      if (value.length === 0) {
        this.setMsg(name, msg);
        return false;
      } else {
        this.setMsg(name, "");
      }
      return true;
    },
    checkLength(name) {
      const value = this.$refs[name].value;
      if (value.length === 0) {
        this.setMsg(name, "密码不能为空！");
        return false;
      } else if (value.length < 6) {
        this.setMsg(name, "密码小于6！");
        return false;
      } else {
        this.setMsg(name, "");
      }
      return true;
    },
    checkEmail() {
      const email = this.$refs.email.value;
      const match = /^\w+@\w+(\.\w+)+$/;
      if (email.length > 0) {
        if (!match.test(email)) {
          this.setMsg("email", "email格式不正确！");
          return false;
        }
      }
      return true;
    },
    checkPassword() {
      const password = this.$refs.password.value;
      const repassword = this.$refs.repassword.value;
      if (password.length > 0 && repassword.length > 0) {
        if (password !== repassword) {
          this.setMsg("password", "两次密码不一致！");
          this.setMsg("repassword", "两次密码不一致！");
          return false;
        } else {
          this.setMsg("password", "");
          this.setMsg("repassword", "");
        }
      }
      return true;
    }
  }
}

</script>

<style scoped>

form{
  margin-top: 10px;
}
</style>