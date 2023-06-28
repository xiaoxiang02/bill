<template>
  <header>
    <div class="container">
      <nav class="header-wrap">
        <a href="home.html"><img src="../../assets/logo.png" alt="logo"></a>
        <ul>
          <li>
            <router-link to='/home'>首页</router-link>
          </li>
          <li>
            <router-link to='/statistics'>统计</router-link>
          </li>
          <li>
            <router-link to='/account'>账户</router-link>
          </li>
        </ul>
      </nav>
      <div class="un">
        <div v-if="username == undefined">
          <a href="" @click.prevent="toLogin">未登录，请先登录</a>
        </div>
        <div class="user" v-else>
          <el-dropdown>
            <span class="el-dropdown-link">
              {{ username }}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      username: localStorage.getItem('username'),
    }
  },
  methods: {
    toLogin() {
      this.$router.push('/');
    },
    logout() {
      this.$confirm('你确定要退出吗', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true,
        closeOnPressEscape: false,//按下 ESC 键关闭弹窗
        closeOnClickModal: false,//点击遮罩关闭弹窗
        distinguishCancelAndClose: true,//区分取消与关闭
      }).then(() => {
        localStorage.clear();
        this.$router.push('/');
      }).catch((action) => {
        if (action === 'cancel') {
            //取消
        } else {
            //关闭弹窗
            return;
        }
      });
    }
  }
}
</script>

<style src="../../assets/css/header.css" scoped>
</style>