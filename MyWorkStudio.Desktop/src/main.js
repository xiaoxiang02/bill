import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import '@/assets/css/style.css'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import "bootstrap-vue/dist/bootstrap-vue.css"

Vue.use(ElementUI);
Vue.config.productionTip = false

//全局变量
Vue.prototype.$axios = axios; //配置好后就可以全局引用了

new Vue({
  router,//挂载路由
  axios,
  render: h => h(App),
}).$mount('#app')
