<template>
  <div class="login-container">
    <form>
      <h1>登录</h1>
      <div class="form-group">
        <input type="text" id="employeeNumber" v-model="loginForm.employeeNumber" placeholder="工号" required>
      </div>
      <div class="form-group">
        <input type="password" id="password" v-model="loginForm.password" placeholder="密码" required>
      </div>
      <button type="submit" @click.prevent="login">登录</button>
    </form>
  </div>
</template>

<script>
import {login} from '@/api/login';

export default {
  name: 'Login',
  data() {
    return {
      loginForm:{
        employeeNumber: '',
        password: ''
      }
    }
  },
  methods: {
    login() {
      login(this.loginForm) 
        .then(response => {
          if (response.data.success) {
            // 登录成功，跳转到首页
            console.log(response.data) //返回登录信息
            localStorage.setItem('token', response.data.data.token)
            localStorage.setItem('username', response.data.data.username)
            this.$router.push('/home');        
          } else {
            alert('登录失败，请检查工号和密码');
          }
        })
        .catch(error => {
          console.log(error);
          alert('登录失败，请稍后再试');
        });
    }
  }
}
</script>

<style src="../assets/css/login.css" scoped>
  
</style>